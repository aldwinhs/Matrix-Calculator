package mtrx;


import java.util.Scanner;


public class Matrix {

    //inisiasi variabel
    private int row , col ;
    private double[][] content;
    
    //double supaya bisa untuk pecahan

    // KONSTRUKTOR
    public Matrix() {
        row = 1;
        col = 0;
    }
    public Matrix(int nRow, int nCol) {
        this.row = nRow;
        this.col = nCol;
        this.content = new double[nRow][nCol];
    }
    public Matrix(Matrix matrix) {
        this.row = matrix.row;
        this.col = matrix.col;
        this.content = matrix.content;
    }

    // SELEKTOR
    public int getRowEff() {
        return this.row;
    }
    public int getColEff() {
        return this.col;
    }
    public int getLastIdxRow() {
        return this.row - 1;
    }
    public int getLastIdxCol() {
        return this.col - 1;
    }
    public double getElement(int row, int col) {
        return this.content[row][col];
    }
    public void setElement(int row, int col, double x) {
        this.content[row][col] = x;
    }
    public void setColEff(int col) {
        /* KAMUS */

        /* ALGORITMA */
        this.col = col;
    }
    public void setRowEff(int row) {
        /* KAMUS */

        /* ALGORITMA */
        this.row = row;
    }

    // INPUT/OUTPUT
    public void readMatrix() {
        Scanner scan = new Scanner(System.in);
        int nRow,nCol;

        System.out.println("Masukkan nilai N*M");
            nRow = scan.nextInt();
            nCol = scan.nextInt();

        this.content = new double[nRow][nCol];
        this.row=nRow;
        this.col=nCol;
        
        for (int i=0; i<nRow; i++){
            for (int j=0; j<nCol; j++){
                this.content[i][j] = scan.nextDouble();       
            }
        }
        scan.close();
    }

    public void displayMatrix() {
        int i,j;
        for (i = 0; i<this.row; i++ ){
            for (j = 0; j< this.col; j++){
                
                if(j == this.col-1) System.out.println(content[i][j]);
                else System.out.print(content[i][j] + " ");
            }
        }
    }

    /* OPERASI */
    public void swapRow(int idxRow1, int idxRow2) {
        /* KAMUS */
        int j;
        double temp;
        /* ALGORITMA */
        for (j = 0; j < getColEff(); j++) {
            temp = getElement(idxRow1, j);
            setElement(idxRow1, j, getElement(idxRow2, j));
            setElement(idxRow2, j, temp);
        }
    }
    public void multiplyRow(int idxRow, double multiplier) {
        /* KAMUS */
        int j;
        /* ALGORITMA */
        for (j = 0; j < getColEff(); j++) {
            setElement(idxRow, j, multiplier * getElement(idxRow, j));
        }
    }
    public void plusMinusRow(int idxRow1, int idxRow2, double multiplier1, double multiplier2, boolean operator) {
        /* KAMUS */
        int j;
        /* ALGORITMA */
        if (operator) {
            for (j = 0; j < getColEff(); j++) {
                setElement(idxRow1, j, getElement(idxRow1, j) * multiplier1 + getElement(idxRow2, j) * multiplier2);
            }
        } else {
            for (j = 0; j < getColEff(); j++) {
                setElement(idxRow1, j, getElement(idxRow1, j) * multiplier1 - getElement(idxRow2, j) * multiplier2);
            }
        }
    }
    public boolean isSquare() {
        /* KAMUS */

        /* ALGORITMA */
        return getColEff() == getRowEff();
    }
    public void addIdentity() {
        /* KAMUS */
        int i, j;
        /* ALGORITMA */
        setColEff(2 * getColEff());
        for (i = 0; i < getRowEff(); i++) {
            for (j = getColEff() / 2 ; j < getColEff(); j++) {
                if (i == j - getColEff() / 2) {
                    setElement(i, j, 1);
                } else {
                    setElement(i, j, 0);
                }
            }
        }
    }
    public boolean isIdentity() {
        /* KAMUS */
        int i, j;
        /* ALGORTIMA */
        for (i = 0; i < getRowEff(); i++) {
            for (j = 0; j < getColEff(); j++) {
                if (i == j) {
                    if (getElement(i, j) != 1) return false;
                } else {
                    if (getElement(i, j) != 0) return false;
                }
            }
        }
        return true;
    }
    public void transpose() {
        /* KAMUS */
        int i, j;
        double temp;
        /* ALGORITMA */
        for (i = 0; i < getRowEff(); i++) {
            for (j = i + 1; j < getColEff(); j++) {
                temp = getElement(i, j);
                setElement(i, j, getElement(j, i));
                setElement(j, i, temp);
            }
        }
    }
}
