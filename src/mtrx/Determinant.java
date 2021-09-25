package mtrx;


import mtrx.Matrix;


public class Determinant {

    // INSIALISASI VARIABEL
    private Matrix matrix;
    private double determinant;

    // KONSTRUKTOR
    public Determinant() {
        this.matrix = new Matrix();
    }
    public Determinant(Matrix matrix) {
        this.matrix = new Matrix(matrix);
    }

    // SELEKTOR
    public Matrix getMatrix() {
        return this.matrix;
    }

    // METHOD
    public double determinantKofaktor() {
        /* KAMUS */
        double det;
        Determinant minor;
        int j;
        /* ALGORITMA */
        if (getMatrix().getColEff() == 1) {
            return getMatrix().getElement(0, 0);
        } else {
            det = 0;
            for (j = 0; j < getMatrix().getColEff(); j++) {
                minor = new Determinant(getMatrix());
                minor.getMatrix().deleteRow(0);
                minor.getMatrix().deleteCol(j);

                det += (j % 2 == 0 ? 1 : -1) * getMatrix().getElement(0, j) * minor.determinantKofaktor();
            }
            return det;
        }
    }
}