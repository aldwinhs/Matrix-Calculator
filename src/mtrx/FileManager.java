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
    public int count;

    public void readFile(){
        try{
            String fileName = scan.next();
            int nRow = 0 , nCol = 0;
            
            Scanner scanFile = new Scanner(new BufferedReader(new FileReader("test/" +fileName + ".txt")));
            while(scanFile.hasNextLine()) {
                nRow++;                                                     //hitung jumlah baris
                nCol = scanFile.nextLine().trim().split(" ").length;        //hitung kolom dengan pemisah elemen matriks dengan spasi
                

            Scanner FileToMatrix = new Scanner(new BufferedReader(new FileReader("test/"+fileName + ".txt")));
            matriksForm.setRowEff(nRow);
            matriksForm.setColEff(nCol);
            this.count = nRow;
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

    public void writeMatrixFile(Matrix matriks){
        int pilihan;
        System.out.println("Apakah Anda ingin Menyimpan jawaban ke File? ");
        System.out.println("(1 = Iya atau 0 = Tidak)");
        pilihan = scan.nextInt();

        if (pilihan == 1){
            try{
                String fileName = scan.next();
                BufferedWriter fileWriter =  new BufferedWriter(new FileWriter("test/"+ fileName + ".txt"));
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
    }

    public void writeDoubleFile(double[] solusi){
        int pilihan;
        System.out.println("Apakah Anda ingin Menyimpan jawaban ke File? ");
        System.out.println("(1 = Iya atau 0 = Tidak)");
        pilihan = scan.nextInt();

        if (pilihan == 1){
            try{
                String fileName = scan.next();
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
        

}
