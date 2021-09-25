package mtrx;

import java.lang.*;

public class SPL {
    
    //Method untuk menghasilkan Matrik tereduksi dengan metode gauss
    public Matrix Gauss(Matrix matriks){
        int nRow = matriks.row;
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

            if (i_max != k) matriks.swapRow( k, i_max);
            
            for (int i = k+1 ; i<nRow; i++){

                //multiplier 2 sebagai koefisien baris pengurang
                double multiplier2= elmt[i][k] / elmt[k][k];
                /* subtract fth multiple of corresponding
                   kth row element*/
                matriks.plusMinusRow(i, k, 1, multiplier2, false);
                /* filling lower triangular matrix with
                        * zeros*/
                elmt[i][k] = 0;
            }
           
        
        }
        return matriks;
        
    }


}
