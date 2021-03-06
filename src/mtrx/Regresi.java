package mtrx;

public class Regresi {
    public Matrix NormalEstimation(Matrix nilai, int n, int m){
        int i, j, k; double sum;
        Matrix hasil = new Matrix(m+1, m+2);
        hasil.setElement(0, 0, n);
        for ( j = 1; j < hasil.getColEff(); j++) {
            sum = 0;
            for ( k = 0; k < n; k++) {
                sum += nilai.getElement(k, j-1);
            }
            hasil.setElement(0, j, sum);
        }
        for ( i = 1; i < hasil.getRowEff(); i++) {
            hasil.setElement(i, 0, hasil.getElement(0, i));
        }
        for ( i = 1; i < hasil.getRowEff(); i++) {
            for ( j = 1; j < hasil.getColEff(); j++) {
                sum = 0;
                for ( k = 0; k < n; k++) {
                    sum += nilai.getElement(k, j-1) * nilai.getElement(k, i-1);
                }
                hasil.setElement(i, j, sum);
            }
        }

        return hasil;
    }

    public double TaksiranRegresi (double[] solusi, double[] x){
        double fx = solusi[0];
        int i;

        for (i = 1; i < solusi.length ; i++){
            fx += x[i-1]*solusi[i];
        }

        return fx;

    }

}
