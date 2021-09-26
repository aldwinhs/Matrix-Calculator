package mtrx;



public class Determinant {

    public double result;
    // konstruktor
    // METHOD

    public double detKofaktor(Matrix matrix) {
        /* KAMUS */
        Matrix minorMatrix;
        double det;
        int j;
        /* ALGORTIMA */
        if (matrix.getColEff() == 1) return matrix.getElement(0, 0);
        else if (matrix.row == 2) return (matrix.getElement(0,0)*matrix.getElement(1,1) - matrix.getElement(0,1)  * matrix.getElement(1,0));
        else {
            det = 0;
            for (j = 0; j < matrix.getColEff(); j++) {
                minorMatrix = new Matrix(matrix);
                minorMatrix.deleteRow(0);
                minorMatrix.deleteCol(j);
                det += (j % 2 == 0 ? 1 : -1) * matrix.getElement(0, j) * detKofaktor(minorMatrix);
            }

            return det;
        }
    }
    public double detReduksiBaris(Matrix matriks){
        int nRow = matriks.row;
        double result = 1;
        int count = 0;

            double elmt[][] = matriks.content;

            for (int k = 0; k<nRow; k++){
                int i_max = k;
                double v_max =  elmt[k][k];

                for (int i = k+1; i<nRow; i++){
                    if (Math.abs(elmt[i][k]) > v_max) {
                        v_max = elmt[i][k];
                        i_max = i;
                    }
                }

                if (i_max != k) {
                    matriks.swapRow( k, i_max);
                    count++;
                }
                for (int i = k+1 ; i<nRow; i++){

                    //multiplier 2 sebagai koefisien baris pengurang
                    double multiplier2= elmt[i][k] / elmt[k][k];
                    // pengurangan baris bawah dengan baris atas
                    matriks.plusMinusRow(i, k, 1, multiplier2, false);
                    // bentuk segitiga bawah
                    elmt[i][k] = 0;
                }
            }
        //Perkalian diagonal Utama untuk menghasilkan determinan
        for (int i = 0; i< matriks.row ; i++){
                result *= matriks.content[i][i];
        }
        if (count%2 == 1)  return -result;
        else  return result;
    
}   
}