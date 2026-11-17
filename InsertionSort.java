public class InsertionSort {
    public static void insertionSort(Veiculos[] meusVeiculos) {
       int tamanho = meusVeiculos.length;

       for (int i = 1; i < tamanho; i++){  //i começa em 1
        Veiculos chave = meusVeiculos[i];
        int j = i - 1;  //j - i no começo é = 0, então j = 0

            while (j >= 0 && meusVeiculos[j].getDiaria() > chave.getDiaria()) {
                meusVeiculos[j + 1] = meusVeiculos[j];
                j--;
            }
        meusVeiculos[j + 1] = chave;
       }
    }
}
