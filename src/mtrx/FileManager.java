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

   

    public void writeString(String pesan){
        int pilihan;
        System.out.println("Apakah Anda ingin Menyimpan jawaban ke File? ");
        System.out.println("(1 = Iya atau 0 = Tidak)");
        pilihan = scan.nextInt();

        if (pilihan == 1){
            try{
                String fileName = scan.next();
                BufferedWriter fileWriter =  new BufferedWriter(new FileWriter("test/"+ fileName + ".txt", true));
                    fileWriter.write(pesan);
                    fileWriter.newLine();
                
                fileWriter.flush();
                fileWriter.close();
                
            }
            catch(Exception e){
                System.out.println("Error");
                e.printStackTrace();
            }
        }
    }

    public void writeStringFile(String[] solusi){
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
                    fileWriter.write(("x" + (i+1 + ": " + solusi[i])));
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
        
    public void writeInterpolasi(double[] solusi, double[] taksiran, double[] hasilTaksiran){
        int pilihan;
        System.out.println("Apakah Anda ingin Menyimpan jawaban ke File? ");
        System.out.println("(1 = Iya atau 0 = Tidak)");
        pilihan = scan.nextInt();

        if (pilihan == 1){
            try{
                String fileName = scan.next();
                BufferedWriter fileWriter =  new BufferedWriter(new FileWriter("test/"+ fileName + ".txt", true));
                int i,j;
                /*
                fileWriter.write("Diperoleh SPL untuk mencari Persamaan Interpolasi dalam bentuk matrix sebagai berikut: ");
                fileWriter.newLine();
                for(i=0; i <matriks.row; i++){
                    for( j =0; j<matriks.col; j++){
                        if (j != matriks.getLastIdxCol()) fileWriter.write(String.valueOf(matriks.getElement(i, j))+ " ");
                        else fileWriter.write(String.valueOf(matriks.getElement(i, j))+ " ");
                    }
                    fileWriter.newLine();
                }
                */
                fileWriter.newLine();
                fileWriter.write("Bentuk persamaan polinom dari hasil penyelesaian SPL diatas ialah");
                fileWriter.newLine();
                fileWriter.write("P(x) = ");
                for ( i=0; i< solusi.length; i++){
                    if (i==0) fileWriter.write(String.valueOf(solusi[i]));
                    else{
                        if (solusi[i] > 0){
                            if (i==1) fileWriter.write (String.valueOf(" + " +  solusi[i] + "x"));
                            else fileWriter.write(String.valueOf(" + " + solusi[i] + "x^" +i));
                        }
                        else{
                            if (i==1) fileWriter.write(String.valueOf(solusi[i] + "x"));
                            else fileWriter.write(String.valueOf(solusi[i] + "x^" +i));
                        }
                    }    
                }
                fileWriter.newLine();
                fileWriter.write("Nilai taksiran : ");
                for(i=0; i <taksiran.length; i++){

                    fileWriter.write(String.valueOf(("P" + solusi.length +"(" +taksiran[i]+") = " + hasilTaksiran[i])));
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

    public void writeRegresi (double[] solusi, double [] taksiran, double[] hasilTaksiran, Matrix matriks){
        int pilihan;
        System.out.println("Apakah Anda ingin Menyimpan jawaban ke File? ");
        System.out.println("(1 = Iya atau 0 = Tidak)");
        pilihan = scan.nextInt();

        if (pilihan == 1){
            try{
                String fileName = scan.next();
                BufferedWriter fileWriter =  new BufferedWriter(new FileWriter("test/"+ fileName + ".txt", true));
                
                fileWriter.write("Diperoleh SPL untuk mencari Regresi dalam bentuk matrix sebagai berikut: ");
                fileWriter.newLine();
                int i,j;
                
                for(i=0; i <matriks.row; i++){
                    for( j =0; j<matriks.col; j++){
                        if (j != matriks.getLastIdxCol()) fileWriter.write(String.valueOf(matriks.getElement(i, j))+ " ");
                        else fileWriter.write(String.valueOf(matriks.getElement(i, j))+ " ");
                    }
                    fileWriter.newLine();
                }
                fileWriter.newLine();

                
                fileWriter.write("Bentuk regresi dari hasil penyelesaian SPL diatas ialah");
                fileWriter.newLine();
                fileWriter.write("y = ");
               
                for(i=0; i <solusi.length; i++){
                    if (solusi[i]> 0){
                        if (i==0) fileWriter.write(String.valueOf((solusi[i])));
                        else fileWriter.write(String.valueOf( " + " + (solusi[i]) +"x" + (i)));
                        
                    }
                    else if (solusi[i] < 0){
                        if (i==0) fileWriter.write(String.valueOf((solusi[i])));
                        else fileWriter.write(String.valueOf((solusi[i]) +"x" + (i)));
                        
                    }
                    
                }
                fileWriter.newLine();
                fileWriter.write("Nilai taksiran : ");
                for(i=0; i <solusi.length; i++){
                    fileWriter.write(String.valueOf(("y" + "(" +taksiran[i]+") = " + hasilTaksiran[i])));
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
