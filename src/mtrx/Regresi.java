package mtrx;

import java.util.Scanner;

public class Regresi {
    public Matrix NormalEstimation(){
        Scanner obj = new Scanner(System.in);
        int i, j, k, n, m; double input, sum;
        System.out.println("Masukan jumlah data:");
        n = obj.nextInt();
        System.out.println("Masukan jumlah variasi:");
        m = obj.nextInt();
        Matrix nilai = new Matrix(n, m+1);
        for ( i = 0; i < nilai.getRowEff(); i++) {
            for ( j = 0; j < nilai.getColEff(); j++) {
                input = obj.nextDouble();
                nilai.setElement(i, j, input);
            }
        }
        obj.close();
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
            }
        }

        return hasil;
    }
}
