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

            Matrix TempMatrix = new Matrix(matriks);
            
            for (int k = 0; k<nRow; k++){
                int row_max= k;      //row yang dijadikan prioritas
                double elmt_max =  TempMatrix.getElement(k, k);

                for (int i = k+1; i<nRow; i++){
                    if (Math.abs(TempMatrix.getElement(i, k)) > elmt_max) {
                        elmt_max = TempMatrix.getElement(i, k);
                        row_max= i;
                        
                        
                    }
                }

                if (row_max != k) {
                    TempMatrix.swapRow( k, row_max);
                    count++;
                   
                    
                }
                for (int i = k+1 ; i<nRow; i++){

                    //multiplier 2 sebagai koefisien baris pengurang
                    double multiplier2= TempMatrix.getElement(i, k) / TempMatrix.getElement(k, k);
                    // pengurangan baris bawah dengan baris atas
                    TempMatrix.plusMinusRow(i, k, 1, multiplier2, false);
                    // bentuk segitiga bawah
                    TempMatrix.setElement(i, k, 0) ;
                    
                  
                }
            }
        //Perkalian diagonal Utama untuk menghasilkan determinan
        for (int i = 0; i< TempMatrix.row ; i++){
                result *= TempMatrix.content[i][i];
        }
        if (count%2 == 1)  return -result;
        else  return result;
    
}   
}