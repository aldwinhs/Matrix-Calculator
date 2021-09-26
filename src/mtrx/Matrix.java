package mtrx;


import java.util.Scanner;


public class Matrix {

    //inisiasi variabel
    public int row , col ;
    public double[][] content;
    
    //double supaya bisa untuk pecahan

    // KONSTRUKTOR
    public Matrix() {
        this.row = 100;
        this.col = 100;
    }
    public Matrix(int nRow, int nCol) {
        this.row = nRow;
        this.col = nCol;
        this.content = new double[nRow][nCol];
    }
    public Matrix(Matrix matrix) {
        this.row = matrix.row;
        this.col = matrix.col;
        this.content = new double[matrix.row][matrix.col];
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.col; j++) {
                this.content[i][j] = matrix.content[i][j];
            }
        }
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
    public void deleteRow(int idxRow) {
        // Prekondisi: idxRow merupakan index valid effektif
        /* KAMUS */
        int i, j;
        /* ALGORITMA */
        setRowEff(getRowEff() - 1);
        for (i = idxRow; i < getRowEff(); i++) {
            for (j = 0; j < getColEff(); j++) {
                setElement(i, j, getElement(i + 1, j));
            }
        }
    }
    public void deleteCol(int idxCol) {
        // Prekondisi: idxCol merupakan index valid effektif
        /* KAMUS */
        int i, j;
        /* ALGORITMA */
        setColEff(getColEff() - 1);
        for (i = 0; i < getRowEff(); i++) {
            for (j = idxCol; j < getColEff(); j++) {
                setElement(i, j, getElement(i, j + 1));
            }
        }
    }

    //cek apakah elemen dalam satu baris = 0 . 
    //dicek sampai kolom-1, karena hanya digunakan untuk SPL
    public boolean rowIsZero(int row){
        int j = 0;
        boolean flag = true;
        while (flag && j<this.col-1) {
            if (this.content[row-1][j] != 0) flag = false;
            else j++; 
        }
        return flag;
    }

}
