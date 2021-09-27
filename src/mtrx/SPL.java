package mtrx;

import java.lang.Math;

public class SPL {
    
    //Method untuk menghasilkan Matrik tereduksi dengan metode gauss
    public Matrix Gauss(Matrix matriks){
        int nRow = matriks.row;
        
        

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
        return TempMatrix;
        
    }

    
    public void SolveGauss(Matrix matriks){
        Matrix result = Gauss(matriks);
        double elmt[][] = result.content;
        
        //Kasus 1 : Tidak ada solusi
        if (result.rowIsZero(result.row) && elmt[result.row-1][result.col-2] !=0 ){
            System.out.print("SPL Tidak memiliki solusi!");
        }

       
        else{
            double solusi [] = new double[result.col-1];
            
            //Cek apakah Jumlah persamaan (baris) cukup untuk mendapatkan solusi unik
            int RowRed=0;
            for (int j=0 ; j<result.col; j++){
                if (result.rowIsZero(j)) RowRed++;
            }

            //kasus 2 : solusi unik
            if (RowRed >= result.col-1){
                for (int i = RowRed-1; i>=0; i--){
                    
                    for (int j=result.col-2 ; j>= 0  ; j--){
                        solusi[i] += solusi[j] * elmt[i][j];
                    }
                }
            }

            
        } 

        

    }


    public double[] BackwardSubstitution(Matrix matrix) {
        int j,k;
        double [] solusi = new double[matrix.getColEff()-1];

        //i =3
            //j=3
        for ( j = matrix.getColEff()-2; j >= 0 ; j--) {
            solusi[j] = matrix.getElement(j, matrix.getColEff()-1);
            for ( k = matrix.getColEff()-2; k > j; k--) {
                solusi [j] -= matrix.getElement(j, k) * solusi[k];
                }
            }

        return solusi;
    }

    

}
