package mtrx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;



public class FileManager {
    
    Scanner scan= new Scanner(System.in);

    Matrix matriksForm = new Matrix(100,100);
    

    public void readFile(String fileName){
        try{
            int nRow = 0 , nCol = 0;
            Scanner scanFile = new Scanner(new BufferedReader(new FileReader("test/" +fileName)));
            while(scanFile.hasNextLine()) {
                nRow++;                                                     //hitung jumlah baris
                nCol = scanFile.nextLine().trim().split(" ").length;        //hitung kolom dengan pemisah elemen matriks dengan spasi
                

            Scanner FileToMatrix = new Scanner(new BufferedReader(new FileReader("test/"+fileName)));
            matriksForm.setRowEff(nRow);
            matriksForm.setColEff(nCol);
            //Konstruktor matriks dari file
            
            for (int i = 0; i<nRow; i++){
                
                String[] line = FileToMatrix.nextLine().trim().split(" ");
                for (int j=0; j<nCol;j++){
                    if (FileToMatrix.hasNextDouble());
                    Double x = Double.parseDouble(line[j]);
                    matriksForm.setElement(i,j, x);
                        }
                    }
                }
            }   

        catch (FileNotFoundException e){
            System.out.println("Tidak ada file yang sesuai!");
            e.printStackTrace();
        }
        
    }

    public void writeMatrixFile(String fileName, Matrix matriks){
        try{
            BufferedWriter fileWriter =  new BufferedWriter(new FileWriter("test/"+ fileName + ".txt", true));
            int i,j;
            
            for(i=0; i <matriks.row; i++){
                for( j =0; j<matriks.col; j++){
                    if (j != matriks.getLastIdxCol()) fileWriter.write(String.valueOf(matriks.getElement(i, j))+ " ");
                    else fileWriter.write(String.valueOf(matriks.getElement(i, j))+ " ");
                }
                fileWriter.newLine();
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    public void writeMatrixFile(String fileName, double[] solusi){
        try{
            BufferedWriter fileWriter =  new BufferedWriter(new FileWriter("test/"+ fileName + ".txt", true));
            int i;
            
            for(i=0; i <solusi.length; i++){
                fileWriter.write(String.valueOf("x" + (i+1 + ": " + solusi[i])));
                fileWriter.newLine();
            }
            fileWriter.flush();
            fileWriter.close();
            
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }

    }
        

}
