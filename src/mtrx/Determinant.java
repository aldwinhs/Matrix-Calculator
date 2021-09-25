package mtrx;



public class Determinant {

    public double result;
    //konstruktor
    public Determinant() {
        this.result = 0;
    }

    // METHOD

    //mainMatrix adalah matrix yang ingin dicari determinannya
    public double detKofaktor(Matrix mainMatrix){
        /* Prekondisi: Matriks persegi berukuran NxN */
        /* Menghitung nilai kofaktor matriks */
        int i,j,k,x,y;
        Matrix temp = new Matrix(mainMatrix.row-1, mainMatrix.col -1);
        double[][] elmt = temp.content;

        if (temp.row == 1) return elmt[0][0];

        else if (temp.row == 2) return (elmt[0][0]*elmt[1][1] - elmt[0][1] * elmt[1][0]);

        //jika matriks berukuran 3x3 atau lebih
        else{
            double result = 0;
            for (i=0; i< mainMatrix.row; i++){
                x = 0;
                for (j=1; j<mainMatrix.col;j++){
                    y=0;
                    for (k=0; k<mainMatrix.row; k++){
                            if (k!=i){
                            elmt[x][y] = elmt[j][k];
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

}


    







   


    

