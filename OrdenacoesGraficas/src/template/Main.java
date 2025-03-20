package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.ArrayList;
import java.util.List;

/**
 * Visualização de quatro algoritmos de ordenação
 *
 * @author Prof. Dr. David Buzatto
 */
public class Main extends EngineFrame {

    private int[] arraySelection, arrayInsertion, arrayBubble, arrayMerge;
    private List<int[]> listaSelection, listaInsertion, listaBubble, listaMerge;
    private int posSelection, posInsertion, posBubble, posMerge;

    private double tempoParaMudar;
    private double contadorTempo;
    int fontsize;

    public Main() {
        super(800, 450, "Ordenações", 60, true);
    }

    @Override
    public void create() {
        arraySelection = new int[]{9, 10, 5, 6, 3, 1, 2, 8};
        arrayInsertion = arraySelection.clone();
        arrayBubble = arraySelection.clone();
        arrayMerge = arraySelection.clone();

        listaSelection = new ArrayList<>();
        listaInsertion = new ArrayList<>();
        listaBubble = new ArrayList<>();
        listaMerge = new ArrayList<>();

        tempoParaMudar = 1;
        copiarArray(listaSelection, arraySelection);
        copiarArray(listaInsertion, arrayInsertion);
        copiarArray(listaBubble, arrayBubble);
        copiarArray(listaMerge, arrayMerge);

        ordenarSelection(arraySelection);
        Insertion(arrayInsertion);
        Bubble(arrayBubble);
        MergeSort(arrayMerge);
        fontsize = 20;
        
    }

    @Override
    public void update(double delta) {
        contadorTempo += delta;
        if (contadorTempo > tempoParaMudar) {
            contadorTempo = 0;
            if (posSelection < listaSelection.size() - 1) {
                posSelection++;
            }
            if (posInsertion < listaInsertion.size() - 1) {
                posInsertion++;
            }
            if (posBubble < listaBubble.size() - 1) {
                posBubble++;
            }
            if (posMerge < listaMerge.size() - 1) {
                posMerge++;
            }
        }
    }

    @Override
    public void draw() {
        clearBackground(WHITE);

        desenharArray(listaSelection.get(posSelection), 40, getScreenHeight() / 4 + 50, 20, 5, 10);
        desenharArray(listaInsertion.get(posInsertion), getScreenWidth() / 2 + 40, getScreenHeight() / 4 +50, 20, 5, 10);
        desenharArray(listaBubble.get(posBubble), 40, (3 * getScreenHeight()) / 4 + 50, 20, 5, 10);
        desenharArray(listaMerge.get(posMerge), getScreenWidth() / 2 + 40, (3 * getScreenHeight()) / 4 + 50, 20, 5, 10);
        
        drawLine(getScreenWidth() / 2, 0, getScreenWidth() / 2, getScreenHeight(), BLACK);
        drawLine(0, getScreenHeight() / 2, getScreenWidth(), getScreenHeight() / 2, BLACK);
        drawText("- SELECTION", getScreenWidth()/4 + 30 , 20,fontsize, BLACK );
        drawText("- INSERTION", getScreenWidth()/4 * 3 + 30, 20, fontsize, BLACK);
        drawText("- BUBBLE", getScreenWidth()/4 + 30, getScreenHeight()/ 2 + 20, fontsize, BLACK);
        drawText("- MERGE", getScreenWidth()/4 * 3 + 30, getScreenHeight()/ 2 + 20, fontsize, BLACK);

        
        
    }

    private void copiarArray(List<int[]> lista, int[] array) {
        int[] novoArray = array.clone();
        lista.add(novoArray);
    }

    private void desenharArray(int[] array, int x, int y, int largura, int espacamento, int tamanhoPedaco) {
        for (int i = 0; i < array.length; i++) {
            fillRectangle(x + i * (largura + espacamento), y - array[i] * tamanhoPedaco, largura, array[i] * tamanhoPedaco, DARKBLUE);
        }
    }

    private void ordenarSelection(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[menor]) {
                    menor = j;
                }
            }
            int t = array[i];
            array[i] = array[menor];
            array[menor] = t;
            copiarArray(listaSelection, array);
        }
    }

    private void Insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int chave = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j];
                j--;
                
            }
            array[j + 1] = chave;
            copiarArray(listaInsertion, array);
        }
    }

    private void Bubble(int[] array) {
        boolean trocado;
        for (int i = 0; i < array.length - 1; i++) {
            trocado = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    
                    trocado = true;
                }
                
            }
            if (!trocado) break;
            copiarArray(listaBubble, array);
        }
    }

    private void MergeSort(int[] array) {
        mergeSortInteiro(array, 0, array.length - 1);
    }

    private void mergeSortInteiro(int[] array, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergeSortInteiro(array, esquerda, meio);
            mergeSortInteiro(array, meio + 1, direita);
            merge(array, esquerda, meio, direita);
            copiarArray(listaMerge, array);
        }
    }

    private void merge(int[] array, int esquerda, int meio, int direita) {
        int[] aux = array.clone();
        int i = esquerda, j = meio + 1, k = esquerda;

        while (i <= meio && j <= direita) {
            if (aux[i] <= aux[j]) {
                array[k++] = aux[i++];
            } else {
                array[k++] = aux[j++];
            }
            
        }
        while (i <= meio) {
            array[k++] = aux[i++];
            
        }
        while (j <= direita) {
            array[k++] = aux[j++];
            
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
