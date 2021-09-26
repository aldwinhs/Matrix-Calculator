package mtrx;



public class Determinant {

    public double result;
    //konstruktor
    

    // METHOD

    //mainMatrix adalah matrix yang ingin dicari determinannya
    public double detKofaktor(Matrix mainMatrix){
        /* Prekondisi: Matriks persegi berukuran NxN */
        /* Menghitung nilai kofaktor matriks */
        int i,j,k,x,y;
        Matrix temp = new Matrix(mainMatrix.row-1, mainMatrix.col -1);
        double[][] elmt = mainMatrix.content;
        if (mainMatrix.row == 1) return elmt[0][0];

        else if (mainMatrix.row == 2) return (elmt[0][0]*elmt[1][1] - elmt[0][1] * elmt[1][0]);

        //jika matriks berukuran 3x3 atau lebih
        else{
            double result = 0;
            for (i=0; i< mainMatrix.row; i++){
                x = 0;
                for (j=1; j<mainMatrix.col;j++){
                    y=0;
                    for (k=0; k<mainMatrix.row; k++){
                            if (k!=i){
                            temp.content[x][y] = elmt[j][k];
                            y++;
                        }    
                    }
                    x++; 
                }
                if (i%2==1)result -= mainMatrix.content[0][i]*detKofaktor(temp);
                else result += mainMatrix.content[0][i]*detKofaktor(temp);
                
                
            }
            return result;
        } 
        


    }

    public double detKofaktor1(Matrix matrix) {
        /* KAMUS */
        Matrix minorMatrix;
        double det;
        int j;
        /* ALGORTIMA */
        // det = 0;
        // for (j = 0; j < matrix.getColEff(); j++) {
        //     minorMatrix = new Matrix(matrix);
        //     minorMatrix.deleteRow(0);
        //     minorMatrix.deleteCol(j);
        //     minorMatrix.displayMatrix();
        //     System.out.println();
        //     matrix.displayMatrix();
        //     System.out.println();
            
        //     // det += (j % 2 == 0 ? 1 : -1) * minorMatrix.getElement(0, j) * detKofaktor(minorMatrix);
        //     // System.out.println(det);
        // }
        // return det;
        
        if (matrix.getColEff() == 1) return matrix.getElement(0, 0);
        else {
            det = 0;
            for (j = 0; j < matrix.getColEff(); j++) {
                minorMatrix = new Matrix(matrix);
                minorMatrix.deleteRow(0);
                minorMatrix.deleteCol(j);
                det += (j % 2 == 0 ? 1 : -1) * matrix.getElement(0, j) * detKofaktor1(minorMatrix);
            }

            return det;
        }
    }

}


    







   


    

