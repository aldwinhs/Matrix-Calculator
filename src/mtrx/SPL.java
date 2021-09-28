package mtrx;


public class SPL {
    
    public double[] BackwardSubstitution(Matrix matriks) {
        int j,k;
        double [] solusi = new double[matriks.getColEff()-1];
        

        for ( j = matriks.getColEff()-2; j >= 0 ; j--) {
            solusi[j] = matriks.getElement(j, matriks.getColEff()-1);
            for ( k = matriks.getColEff()-2; k > j; k--) {
                solusi [j] -= matriks.getElement(j, k) * solusi[k];
                }
            }

        return solusi;
    }
   

    public double[] cramerMethod(Matrix matriks) {
        /* KAMUS */
        Matrix matriksD = new Matrix(matriks);
        Matrix matriksDx;
        Determinant determinant = new Determinant();
        double[] solusi = new double[matriks.getRowEff()];
        /* ALGORITMA */
        matriksD.deleteCol(matriksD.getLastIdxCol());

        for (int i = 0; i < matriks.getRowEff(); i++) {
            matriksDx = new Matrix(matriks);
            matriksDx.swapCol(i, matriksDx.getLastIdxCol());
            matriksDx.deleteCol(matriksDx.getLastIdxCol());
            solusi[i] = determinant.detKofaktor(matriksDx) / determinant.detKofaktor(matriksD);
        }
        
        return solusi;
    }

    public double[] inverseMethod(Matrix matriks){
        int i;
        double[] solusi = new double[matriks.getRowEff()];
        Matrix konstan = new Matrix(matriks.row, 1);
        Matrix temp = new Matrix(matriks);

        //Copy elemen pada kolom terakhir matriks ke konstang=
        for (i =0; i<matriks.row; i++){
            konstan.setElement(i, 1, matriks.getElement(i, matriks.getLastIdxCol()));
        }

        //Cari matriks yang merepresentasikan peubah
        temp.deleteCol(temp.getLastIdxCol());
        Eliminasi tempInverse = new Eliminasi();

        for (i = 0; i<temp.getRowEff(); i++){
            solusi[i] = tempInverse.metodeinverse(temp).multiplyMatrix(konstan).getElement(i, 1);
        }
        return solusi;

    }
    

}
