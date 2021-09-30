package mtrx;


import java.util.Scanner;




public class Matrix {

    //inisiasi variabel
    public int row , col ;
    public double[][] content;
    
    //double supaya bisa untuk pecahan

    // KONSTRUKTOR
    public Matrix() {
        this.row = 0;
        this.col = 0;
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
        this.content[row][col] = x + 0;
    }
    public void setColEff(int col) {
        /* KAMUS */
        Matrix temp = new Matrix(this);
        int minCol;
        /* ALGORITMA */
        this.col = col;
        this.content = new double[this.row][this.col];

        if (this.col < temp.col) {
            minCol = this.col;
        } else {
            minCol = temp.col;
        }

        for (int i = 0; i < temp.getRowEff(); i++) {
            for (int j = 0; j < minCol; j++) {
                this.content[i][j] = temp.content[i][j];
            }
        }
    }
    public void setRowEff(int row) {
        /* KAMUS */
        Matrix temp = new Matrix(this);
        int minRow;
        /* ALGORITMA */
        this.row = row;
        this.content = new double[this.row][this.col];

        if (this.row < temp.row) {
            minRow = this.row;
        } else {
            minRow = temp.row;
        }

        for (int i = 0; i < minRow; i++) {
            for (int j = 0; j < temp.getColEff(); j++) {
                this.content[i][j] = temp.content[i][j];
            }
        }
    }

    // INPUT/OUTPUT
    public void readMatrix() {
        Scanner scan = new Scanner(System.in);
        int nRow,nCol;

        System.out.println("Masukkan nilai N x M");
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
    public void swapCol(int idxCol1, int idxCol2) {
        /* KAMUS */
        int j;
        double temp;
        /* ALGORITMA */
        for (j = 0; j < getRowEff(); j++) {
            temp = getElement(j, idxCol1);
            setElement(j, idxCol1, getElement(j, idxCol2));
            setElement(j, idxCol2, temp);
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
                // System.out.println(j);
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
        for (i = idxRow; i < getRowEff() - 1; i++) {
            for (j = 0; j < getColEff(); j++) {
                setElement(i, j, getElement(i + 1, j));
            }
        }
        setRowEff(getRowEff() - 1);
    }
    public void deleteCol(int idxCol) {
        // Prekondisi: idxCol merupakan index valid effektif
        /* KAMUS */
        int i, j;
        /* ALGORITMA */
        for (i = 0; i < getRowEff(); i++) {
            for (j = idxCol; j < getColEff() - 1; j++) {
                setElement(i, j, getElement(i, j + 1));
            }
        }
        setColEff(getColEff() - 1);
    }

    //cek apakah elemen dalam satu baris = 0 . 
    //dicek sampai kolom-1, karena hanya digunakan untuk SPL
    public boolean rowIsZero(int row){
        int j = 0;
        boolean flag = true;
        while (flag && j<this.col-1) {
            if (this.content[row][j] != 0) flag = false;
            else j++; 
        }
        return flag;
    }

    public Matrix multiplyMatrix (Matrix matriks){
        /* Prekondisi : Ukuran kolom efektif this.matrix= ukuran baris efektif matriks*/
        /* Mengirim hasil perkalian matriks: salinan this.matriks * matriks */
        int i,j,k;
        double temp;
        Matrix MatrixHasil = new Matrix(this.row , matriks.col);

        for (i=0 ; i<MatrixHasil.row; i++){
            for (j=0 ; j<MatrixHasil.col; j++){
                temp =0;
                for(k=0 ; k<matriks.row;k++){
                    temp += this.getElement(i, k) * matriks.getElement(k, j); 
                    
                }
                MatrixHasil.setElement(i, j, temp);
            }
        }  
        return MatrixHasil;  
    }

}
