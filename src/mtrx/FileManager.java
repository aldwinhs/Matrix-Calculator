package mtrx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
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
        

}
