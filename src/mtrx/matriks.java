package mtrx;


import java.util.Scanner;


public class matriks {

    //inisiasi variabel
    private int row , col ;
    private double[][] content;
    
    //double supaya bisa untuk pecahan

    //konstruktor
    public matriks(){
        row = 1;
        col = 0;
    }

    public matriks(int nRow, int nCol){
        this.row=nRow;
        this.col=nCol;
        this.content = new double[nRow][nCol];
    }


    //selektor
    public int getRowEff(){
        return this.row;
    }
    public int getColEff(){
        return this.col;
    }
    public int getLastIdxRow(){
        return (this.row-1);
    }
    public int getLastIdxCol(){
        return(this.col-1);
    }
    public double getElement(int row, int col){
        return this.content[row][col];
    }
    public void setElement(int row, int col, double x){
        this.content[row][col] = x;
    }

    //Input/output
    public void readMatrix(){
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

    public void displayMatrix(){
        int i,j;
        for (i = 0; i<this.row; i++ ){
            for (j = 0; j< this.col; j++){
                
                if(j == this.col-1) System.out.println(content[i][j]);
                else System.out.print(content[i][j] + " ");
            }
        }
    }


}
