package mtrx;

public class Eliminasi {
    
    /* INISIALISASI VARIABEL */
    int[] lenght;

    /* KONSTRUKTOR */

    /* METHOD */
    public Matrix sortMatrix(Matrix matrix) {
         /* KAMUS */
         Matrix matrixHasil = new Matrix(matrix);
         lenght = new int[matrix.getRowEff()];
         int i, j;
         /* ALGORITMA */
         for (i = 0; i < matrixHasil.getRowEff(); i++) {
             j = 0;
             lenght[i] = 0;
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

         return matrixHasil;
    }

    public Matrix getMatrixEselonBaris(Matrix matrix) {
        /* KAMUS */
        Matrix matrixHasil = new Matrix(matrix);
        lenght = new int[matrix.getRowEff()];
        int i, j, k;
        matrixHasil = new Matrix(sortMatrix(matrixHasil));
        /* ALGORITMA */
        // for (i = 0; i < matrixHasil.getRowEff(); i++) {
        //     j = 0;
        //     while (j < matrixHasil.getColEff() && matrixHasil.getElement(i, j) == 0) {
        //         lenght[i] = lenght[i] + 1;
        //         j++;
        //     }
        // }
 
        // // One by one move boundary of unsorted subarray
        // for (i = 0; i < matrixHasil.getRowEff() - 1; i++)
        // {
        //     // Find the minimum element in unsorted array
        //     int min_idx = i;
        //     for (j = i + 1; j < matrixHasil.getRowEff(); j++)
        //         if (lenght[j] < lenght[min_idx])
        //             min_idx = j;
 
        //     // Swap the found minimum element with the first
        //     // element
        //     int temp = lenght[min_idx];
        //     lenght[min_idx] = lenght[i];
        //     lenght[i] = temp;

        //     // Swap row
        //     matrixHasil.swapRow(i, min_idx);
        // }
        // // matrixHasil.displayMatrix();
        // // for (i = 0; i < matrixHasil.getRowEff(); i++) {
        // //     //System.out.println(lenght[i]);
        // // }

        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            j = 0;
            // PROCEDURE SORT
            // matrixHasil = sortMatrix(matrixHasil);
            while (j < i) {
                // matrixHasil = new Matrix(sortMatrix(matrixHasil));
                // matrixHasil.displayMatrix();
                
                if (lenght[i] == lenght[j]) {
                    if (lenght[i] < matrixHasil.getColEff()) matrixHasil.plusMinusRow(i, j, 1, matrixHasil.getElement(i, lenght[i]), false);
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
            // matrixHasil.displayMatrix();
            // System.out.println();
        }
        matrixHasil = new Matrix(sortMatrix(matrixHasil));
        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            j = 0;
            // PROCEDURE SORT
            // matrixHasil = sortMatrix(matrixHasil);
            while (j < i) {
                // matrixHasil = new Matrix(sortMatrix(matrixHasil));
                // matrixHasil.displayMatrix();
                
                if (lenght[i] == lenght[j]) {
                    if (lenght[i] < matrixHasil.getColEff()) matrixHasil.plusMinusRow(i, j, 1, matrixHasil.getElement(i, lenght[i]), false);
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
            // matrixHasil.displayMatrix();
            // System.out.println();
        }
        return matrixHasil;
    }

    public Matrix getMatrixEselonBarisTereduksi(Matrix matrix) {
        Matrix matrixHasil = new Matrix(getMatrixEselonBaris(matrix));

        int i,j,k;
        boolean satuNotFound;
        double multiplier;

        for (i = 1; i< matrixHasil.getRowEff(); i++){
            j = 0;
            satuNotFound = true;
            while (satuNotFound && j<matrixHasil.getLastIdxCol()) {
                if (matrixHasil.getElement(i, j) == 1) {
                    satuNotFound = false;
                    for ( k = 0; k < i; k++) {
                        multiplier = matrixHasil.getElement(k, j);
                        matrixHasil.plusMinusRow(k, i, 1, multiplier, false);
                        
                    }
                } else {
                    j++;
                }
            }
        }
        return matrixHasil;
    }


    public Matrix inverseGaussJordanMethod(Matrix matrix){
        // Matrix matrixHasil = new Matrix(matrix);
        // int nRow = matrixHasil.getRowEff();
        // int nCol = matrixHasil.getColEff();

        // matrixHasil.addIdentity();

        /* KAMUS */
        Matrix matrixHasil = new Matrix(matrix);
        int[] lenght = new int[matrix.getRowEff()];
        int i, j, k;
        /* ALGORITMA */
        matrixHasil.addIdentity();

        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            j = 0;
            while (j < matrixHasil.getColEff() / 2 && matrixHasil.getElement(i, j) == 0) {
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
            //System.out.println(lenght[i]);
        }

        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            j = 0;
            while (j < i) {
                if (lenght[i] == lenght[j]) {
                    matrixHasil.plusMinusRow(i, j, 1, matrixHasil.getElement(i, lenght[i]), false);
                    lenght[i] = lenght[i] + 1;
                    k = lenght[i];
                    while (k < matrixHasil.getColEff() / 2 && matrixHasil.getElement(i, k) == 0) {
                        lenght[i] = lenght[i] + 1;
                        k++;
                    }
                }
                j++;
            }
            if (lenght[i] < matrixHasil.getColEff() / 2) matrixHasil.multiplyRow(i, 1/matrixHasil.getElement(i, lenght[i]));
        }

        boolean satuNotFound;

        for (i = 1; i< matrixHasil.getRowEff(); i++){
            j = 0;
            satuNotFound = true;
            while (satuNotFound && j<matrixHasil.getColEff() / 2) {
                if (matrixHasil.getElement(i, j) == 1) {
                    satuNotFound = false;
                    for ( k = 0; k < i; k++) {
                        matrixHasil.plusMinusRow(k, j, 1, matrixHasil.getElement(k, j), false);
                    }
                } else {
                    j++;
                }
            }
        }

        for (i = 0; i < matrix.getColEff(); i++) {
            matrixHasil.deleteCol(0);
        }

        return matrixHasil;
    }

    public Matrix inverseMinorCofactorMethod (Matrix matrix) {
        /* KAMUS */
        Determinant determinant = new Determinant();
        Matrix matrixHasil = new Matrix(matrix);
        Matrix tempMatrix;
        int i, j;
        double multiplier;
        /* ALGORITMA */
        // MATRIX MINOR COFACTOR
        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            for (j = 0; j < matrixHasil.getColEff(); j++) {
                tempMatrix = new Matrix(matrix);
                tempMatrix.deleteCol(j);
                tempMatrix.deleteRow(i);
                matrixHasil.setElement(i, j, ((i + j) % 2 == 0 ? 1 : -1) * determinant.detKofaktor(tempMatrix));
            }
        }

        // MATRIX ADJOINT
        matrixHasil.transpose();

        // KALI 1/DETERMINANT
        multiplier = 1 / determinant.detKofaktor(matrix);
        for (i = 0; i < matrixHasil.getRowEff(); i++) {
            matrixHasil.multiplyRow(i, multiplier);
        }

        return matrixHasil;
    }
        
}
