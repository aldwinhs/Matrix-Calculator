package mtrx;

public class Eliminasi {
    
    /* INISIALISASI VARIABEL */

    /* KONSTRUKTOR */

    /* METHOD */
    public Matrix metodeGauss(Matrix matrix) {
        /* KAMUS */
        Matrix matrixHasil = new Matrix(matrix);
        int[] lenght = new int[matrix.getRowEff()];
        int i, j, k;
        /* ALGORITMA */
        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            j = 0;
            while (j < matrixHasil.getColEff() && matrixHasil.getElement(i, j) == 0) {
                lenght[i] = lenght[i] + 1;
                j++;
            }
        }
 
        // One by one move boundary of unsorted subarray
        for (i = 0; i < matrixHasil.getRowEff() - 1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (j = i + 1; j < matrixHasil.getRowEff(); j++)
                if (lenght[j] < lenght[min_idx])
                    min_idx = j;
 
            // Swap the found minimum element with the first
            // element
            int temp = lenght[min_idx];
            lenght[min_idx] = lenght[i];
            lenght[i] = temp;

            // Swap row
            matrixHasil.swapRow(i, min_idx);
        }
        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            System.out.println(lenght[i]);
        }

        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            j = 0;
            while (j < i) {
                if (lenght[i] == lenght[j]) {
                    matrixHasil.plusMinusRow(i, j, 1, matrixHasil.getElement(i, lenght[i]), false);
                    lenght[i] = lenght[i] + 1;
                    k = lenght[i];
                    while (k < matrixHasil.getColEff() && matrixHasil.getElement(i, k) == 0) {
                        lenght[i] = lenght[i] + 1;
                        k++;
                    }
                }
                j++;
            }
            if (lenght[i] < matrixHasil.getColEff()) matrixHasil.multiplyRow(i, 1/matrixHasil.getElement(i, lenght[i]));
        }
        return matrixHasil;
    }
}
