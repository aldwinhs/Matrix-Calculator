package mtrx;

import java.lang.Math;

public class Interpolasi {
    
    public double[] result;

    public double[] SolveInterpolasi (Matrix matriks, int N){
        
        int i,j,k;
        SPL solusi = new SPL();

        //Buat persamaan polinom dalam bentuk augmented matrix
        Matrix PoliMatrix = new Matrix(N,N+1);
        k = 0;
        for (i = 0; i<N; i++){
            PoliMatrix.setElement(i, N, matriks.getElement(i, 1));
            for (j=0; j< N; j++){
                PoliMatrix.setElement(i, j, Math.pow(matriks.getElement(k, 0), j));
               
            }
            k++;
        }
        PoliMatrix.displayMatrix();
        //Selesaikan SPL
        return (solusi.inverseMethod(PoliMatrix));
       
        
    }

    public double SolveTaksiran (double[] solusi, double x){
        double fx = 0;
        int i;

        for (i = 0; i < solusi.length ; i++){
            fx += Math.pow(x,i)*solusi[i];
        }

        return fx;
    }

}


