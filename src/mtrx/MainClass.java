package mtrx;
import java.util.Scanner;
import java.lang.Math;


public class MainClass {

    public static FileManager InputFile = new FileManager();
    public static String namaFile;
    public static Determinant hasilDeterminan = new Determinant(); 
    public static Interpolasi hasilInterpolasi = new Interpolasi();
    public static Eliminasi hasilEliminasi = new Eliminasi();
    public static Regresi hasilRegresi = new Regresi();;
    public static SPL hasilSPL = new SPL();
    public static int option;
    public static Matrix InputMatrix = new Matrix();
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        Menu();
    }    

    public static int cekSolusi(Matrix matriks) {
        int i;
        int kondisi;

        Matrix ujiMatrix = new Matrix(matriks);
        for (i = ujiMatrix.getLastIdxRow(); i >= 0; i--) {
            if (ujiMatrix.rowIsZero(i)) ujiMatrix.deleteRow(i);
        }
        //ujiMatrix.displayMatrix();
        
        
        Matrix temp = new Matrix(ujiMatrix);
        temp.deleteCol(ujiMatrix.getLastIdxCol());
        

        //ujiMatrix 1 2 3

        //tidak ada solusi
        if (temp.rowIsZero(temp.getLastIdxRow()) && ujiMatrix.getElement(ujiMatrix.getLastIdxRow(), ujiMatrix.getLastIdxCol()) != 0) {
            kondisi = 1;
        }

        //solusi banyak
        else if (temp.getRowEff()!=temp.getColEff()) {
            kondisi = 2;
        }

        //solusi unik
        else kondisi = 3;

        return kondisi;
    } 

    public static void Menu() {

        System.out.println("==============MENU==============");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");
        System.out.println("============================");
        System.out.print("Masukkan pilihan anda: ");
        option = scan.nextInt();

        while (option < 1 | option > 6) {
            System.out.print("Masukan tidak valid!\n Masukkan pilihan di antara 1-6: ");
            option = scan.nextInt();
        }
            
        if (option==1) MenuSPL();
        else if (option==2) MenuDet();
        else if (option ==3) MenuInverse();
        else if (option == 4) MenuInterpolasi();
        else if (option ==5) MenuRegresi();
        else System.out.println("============================");
        
    }
    
    public static void MenuSPL() {
            System.out.println("");
            System.out.println("============Menu Sistem Persamaan Linier============");
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Kaidah Cramer");
            System.out.println("5. Keluar ke Main Menu");
            System.out.println("============================");
            System.out.print("Masukkan pilihan anda: ");
            option = scan.nextInt();

            if (option==1){
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.println("3. Keluar ke Main Menu");
                System.out.println("============================");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                Matrix InputMatrix = new Matrix();
                //Input dari Keyboard
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.print("Masukkan nama File: ");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }
                else Menu();


                Matrix tempMatrix = hasilEliminasi.getMatrixEselonBaris(InputMatrix);
                System.out.println("==============Matrix Eselon Baris==============");
                tempMatrix.displayMatrix();
                System.out.println("===============================================");
                int validasiSolusi = cekSolusi(tempMatrix);
                if ( validasiSolusi ==1) {
                    System.out.println("SPL tidak memiliki solusi!");
                    InputFile.writeString("SPL tidak memiliki solusi!");
                } 
                else if (validasiSolusi ==2) {
                    String[] solution = hasilSPL.solusiBanyak(hasilEliminasi.getMatrixEselonBaris(InputMatrix));
                    for (int i = 0; i<solution.length; i++) {
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                    InputFile.writeStringFile(solution);
                } 
                else {
                    double[] solution = hasilSPL.BackwardSubstitution(hasilEliminasi.getMatrixEselonBaris(InputMatrix));
                    for (int i = 0; i<solution.length; i++) {
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                    InputFile.writeDoubleFile(solution);
                }
               
            } 
            else if (option==2) {
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.println("3. Keluar ke Main Menu");
                System.out.println("============================");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                
                Matrix InputMatrix = new Matrix();
                //Input dari Keyboard
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.print("Masukkan nama File: ");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }
                else Menu();
                Matrix tempMatrix = hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix);
                System.out.println("==========Matrix Eselon Baris Tereduksi==========");
                tempMatrix.displayMatrix();
                System.out.println("===============================================");
                if (cekSolusi(tempMatrix)==1){
                    System.out.println("SPL tidak memiliki solusi!");
                    InputFile.writeString("SPL tidak memiliki solusi!");
                } 
                else if (cekSolusi(tempMatrix) ==2){
                    String[] solution = hasilSPL.solusiBanyak(tempMatrix);
                    for (int i = 0; i<solution.length; i++){
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                    InputFile.writeStringFile(solution);
                } 
                else {
                    double[] solution = hasilSPL.BackwardSubstitution(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix));
                    for (int i = 0; i<solution.length; i++){
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                    InputFile.writeDoubleFile(solution);    
                }
        }
            else if (option==3){
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.println("3. Keluar ke Main Menu");
                System.out.println("============================");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                //Input dari Keyboard atau File
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented N x (N+1)");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.print("Masukkan nama File: ");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }
                else Menu();
                
                if (InputMatrix.getLastIdxCol() == InputMatrix.getRowEff()){
                    if (hasilDeterminan.detKofaktor(InputMatrix) != 0){
                        double[] solution = hasilSPL.inverseMethod(InputMatrix);
                        for (int i = 0; i<solution.length; i++){
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                        }
                        InputFile.writeDoubleFile(solution);
                    }
                    else {
                        //Determinan = 0
                        System.out.println("Matriks tidak memiliki balikan, karena determinan = 0!");
                        InputFile.writeString("Matriks tidak memiliki balikan, karena determinan = 0!");
                    }
                }
                else {
                    System.out.println("Metode Keluaran Hanya menerima Matriks dengan n Peubah dan n Persamaan!");
                }
            }
            else if (option==4){
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.println("3. Keluar ke Main Menu");
                System.out.println("============================");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                //Input dari Keyboard atau File
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented N x (N+1)");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.print("Masukkan nama File: ");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }
                else Menu();
                if (InputMatrix.getLastIdxCol() == InputMatrix.getRowEff()){
                    double[] solution = hasilSPL.cramerMethod(InputMatrix);
                    for (int i = 0; i<solution.length; i++){
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                    InputFile.writeDoubleFile(solution);
                }
                else {
                    System.out.println("Metode Cramer Hanya menerima Matriks dengan n Peubah dan n Persamaan!");
                    InputFile.writeString("Metode Cramer Hanya menerima Matriks dengan n Peubah dan n Persamaan!");
                }
            }   
            
            else Menu();
            
    }

    public static void MenuDet() {
        System.out.println("");
        System.out.println("==============Menu Determinan==============");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode kofaktor");
        System.out.println("3. Keluar ke Main Menu");
        System.out.println("============================");
        System.out.print("Masukkan pilihan anda: ");
        option = scan.nextInt();
        if (option==1){
            System.out.println("");
            System.out.println("============================");
            System.out.println("Pilih jenis masukan");
            System.out.println("1. Masukan dari keyboard");
            System.out.println("2. Masukan dari file");
            System.out.println("3. Keluar ke Main Menu");
            System.out.println("============================");
            System.out.print("Masukkan pilihan anda: ");
            option = scan.nextInt();

            //Input dari Keyboard
            if (option == 1) {
                System.out.println("Masukkan matriks augmented NxN");
                InputMatrix.readMatrix();
            } else if (option == 2) {
                System.out.print("Masukkan nama File: ");
                InputFile.readFile();
                InputMatrix = new Matrix(InputFile.matriksForm);
            }
            else Menu();
            double[] solution = new double[1];
            
            if (InputMatrix.isSquare()){
                solution[0] = hasilDeterminan.detReduksiBaris(InputMatrix);
                
                if ( Math.abs(solution[0])< 0.0000000001) solution[0] =0;
                System.out.println("Determinan dari Matriks adalah : " + solution[0]);  
                InputFile.writeDoubleFile(solution);
            } 
            else {
                System.out.println("Matriks tidak memiliki determinan karena bukan matriks persegi");
                InputFile.writeString("Matriks tidak memiliki determinan karena bukan matriks persegi");
            }
        }

        else if (option==2){
            System.out.println("");
            System.out.println("============================");
            System.out.println("Pilih jenis masukan");
            System.out.println("1. Masukan dari keyboard");
            System.out.println("2. Masukan dari file");
            System.out.println("3. Keluar ke Main Menu");
            System.out.println("============================");
            System.out.print("Masukkan pilihan anda: ");
            option = scan.nextInt();

            //Input dari Keyboard
            if (option == 1) {
                System.out.println("Masukkan matriks augmented NxN");
                InputMatrix.readMatrix();
            } else if (option == 2) {
                System.out.print("Masukkan nama File: ");
                InputFile.readFile();
                InputMatrix = new Matrix(InputFile.matriksForm);
            }
            else Menu();
            double[] solution = new double[1];
            if (InputMatrix.isSquare()){
                solution[0] = hasilDeterminan.detKofaktor(InputMatrix);  
                System.out.println("Determinan dari Matriks adalah : " + solution[0]);  
                InputFile.writeDoubleFile(solution);
            } 
            else {
                System.out.println("Matriks tidak memiliki determinan karena bukan matriks persegi");
                InputFile.writeString("Matriks tidak memiliki determinan karena bukan matriks persegi");
            }
        }
        else Menu();
    }

    public static void MenuInverse() {
        System.out.println("");
        System.out.println("==============Menu Balikan Matriks==============");
        System.out.println("1. Metode Eliminasi Gauss Jordan");
        System.out.println("2. Metode Kofaktor");
        System.out.println("3. Keluar ke Main Menu");
        System.out.println("============================");
        System.out.print("Masukkan pilihan anda: ");
        option = scan.nextInt();

        if (option == 1){
            System.out.println("");
            System.out.println("============================");
            System.out.println("Pilih jenis masukan");
            System.out.println("1. Masukan dari keyboard");
            System.out.println("2. Masukan dari file");
            System.out.println("3. Keluar ke Main Menu");
            System.out.println("============================");
            System.out.print("Masukkan pilihan anda: ");
            option = scan.nextInt();
            if (option == 1) {
                System.out.println("Masukkan matriks augmented NxN");
                InputMatrix.readMatrix();
            }
            else if (option == 2) {
                System.out.print("Masukkan nama File: ");
                InputFile.readFile();
                InputMatrix = new Matrix(InputFile.matriksForm);
            }
            else Menu();
            Matrix hasilInverse = new Matrix();
            if (InputMatrix.isSquare()){
                if(hasilDeterminan.detKofaktor(InputMatrix) != 0){
                    hasilInverse = hasilEliminasi.inverseGaussJordanMethod(InputMatrix);
                    System.out.println("Matriks Balikan : ");
                    hasilInverse.displayMatrix();
                    System.out.println("============================");
                    InputFile.writeMatrixFile(hasilInverse);
                }
                else{
                    System.out.println("Matriks tidak memiliki balikan karena determinan = 0");
                    System.out.println("============================");
                    InputFile.writeString("Matriks tidak memiliki balikan karena determinan = 0");
                }
                
            }
            else{
                System.out.println("Hanya Matriks persegi yang memiliki balikan!");
                System.out.println("============================");
            }
        }

        else if (option == 2){
            System.out.println("");
            System.out.println("============================");
            System.out.println("Pilih jenis masukan");
            System.out.println("1. Masukan dari keyboard");
            System.out.println("2. Masukan dari file");
            System.out.println("3. Keluar ke Main Menu");
            System.out.println("============================");
            System.out.print("Masukkan pilihan anda: ");
            option = scan.nextInt();
            if (option == 1) {
                System.out.println("Masukkan matriks augmented NxN");
                InputMatrix.readMatrix();
            }
            else if (option == 2) {
                System.out.print("Masukkan nama File: ");
                InputFile.readFile();
                InputMatrix = new Matrix(InputFile.matriksForm);
            }
            else Menu();
            Matrix hasilInverse = new Matrix();
            if (InputMatrix.isSquare()){
                if(hasilDeterminan.detKofaktor(InputMatrix) != 0){
                    hasilInverse = hasilEliminasi.inverseMinorCofactorMethod(InputMatrix);
                    System.out.println("Matriks Balikan : ");
                    hasilInverse.displayMatrix();
                    System.out.println("============================");
                    InputFile.writeMatrixFile(hasilInverse);
                }
                else{
                    System.out.println("Matriks tidak memiliki balikan karena determinan = 0");
                    System.out.println("============================");
                    InputFile.writeString("Matriks tidak memiliki balikan karena determinan = 0");
                }
                
            }
            else{
                System.out.println("Hanya Matriks persegi yang memiliki balikan!");
                System.out.println("============================");
            }
        }
        else Menu();
    
    }

    public static void MenuInterpolasi() {
        System.out.println("");
        System.out.println("==============Menu Interpolasi Polinom==============");
        System.out.println("Pilih jenis masukan");
        System.out.println("1. Masukan dari keyboard");
        System.out.println("2. Masukan dari file");
        System.out.println("3. Keluar ke Main Menu");
        System.out.println("============================");
        System.out.print("Masukkan pilihan anda: ");
        option = scan.nextInt();

        //Input dari Keyboard
        if (option == 1) {
            System.out.println("Masukkan matriks augmented N (banyak titik) x 2");
            InputMatrix.readMatrix();
        }
        else if (option == 2) {
            System.out.print("Masukkan nama File: ");
            InputFile.readFile();
            InputMatrix = new Matrix(InputFile.matriksForm);
        }
        else Menu();
        double[] solution = hasilInterpolasi.SolveInterpolasi(InputMatrix, InputMatrix.getRowEff());
        System.out.println("============================");
        System.out.print("Polinom Interpolasi yang melalui semua titik adalah p" + InputMatrix.getLastIdxRow() +"(x) =");
        for (int i=0; i< solution.length; i++){
            if (i==0) System.out.print(solution[i]);
            else{
                if (solution[i] > 0){
                    if (i==1) System.out.print(" + " +  solution[i] + "x");
                    else System.out.print(" + " + solution[i] + "x^" +i);
                }
                else{
                    if (i==1) System.out.print(solution[i] + "x");
                    else System.out.print(solution[i] + "x^" +i);
                }
            }   
        }
        System.out.println("");
        System.out.println("============================");
        int NTaksiran,i;
        System.out.println("");
        System.out.print("Masukkan N jumlah taksiran : ");
        NTaksiran = scan.nextInt();
        double[] taksiran = new double[NTaksiran];
        for (i=0; i<NTaksiran; i++){
            taksiran[i] = scan.nextDouble();
        }
        double[] hasilTaksir = new double[NTaksiran];
        for (i=0; i<NTaksiran; i++){
            hasilTaksir[i] = hasilInterpolasi.SolveTaksiran(solution, taksiran[i]);
            System.out.println("P" + InputMatrix.getLastIdxRow() +"(" +taksiran[i]+") = " + hasilTaksir[i]);
        }

        InputFile.writeInterpolasi(solution, taksiran, hasilTaksir);
    }

    public static void MenuRegresi() {
        System.out.println("");
        System.out.println("============Menu Regresi Linier============");
        System.out.println("Pilih jenis masukan");
        System.out.println("1. Masukan dari keyboard");
        System.out.println("2. Masukan dari file");
        System.out.println("3. Keluar ke Main Menu");
        System.out.println("============================");
        System.out.print("Masukkan pilihan anda: ");
        option = scan.nextInt();

        //Input dari Keyboard
        if (option == 1) {
            System.out.println("Masukkan matriks augmented ");
            InputMatrix.readMatrix();
        }
        else if (option == 2) {
            System.out.print("Masukkan nama File: ");
            InputFile.readFile();
            InputMatrix = new Matrix(InputFile.matriksForm);
        }
        else Menu();
        
        Matrix Regres = new Matrix(hasilRegresi.NormalEstimation(InputMatrix, InputMatrix.getRowEff(), InputMatrix.getLastIdxCol()));
        double[] solution = hasilSPL.BackwardSubstitution(hasilEliminasi.getMatrixEselonBarisTereduksi(Regres));
        System.out.println("Diperoleh SPL untuk mencari Regresi dalam bentuk matrix sebagai berikut: ");
        Regres.displayMatrix();
        System.out.println("Bentuk regresi dari hasil penyelesaian SPL diatas ialah");
        System.out.print("y = ");
        System.out.println("============================");
        for(int i=0; i <solution.length; i++){
            if (solution[i]> 0){
                if (i==0) System.out.print(String.valueOf((solution[i])));
                else System.out.print(String.valueOf( " + " + (solution[i]) +"x" + (i)));
                
            }
            else if (solution[i] < 0){
                if (i==0) System.out.print(String.valueOf((solution[i])));
                else System.out.print(String.valueOf((solution[i]) +"x" + (i)));
            }
            
        }


        System.out.println("============================");
        int NTaksiran,i;
        System.out.println("");
        NTaksiran = solution.length-1;
        double[] taksiran = new double[NTaksiran];
        for (i=0; i<NTaksiran; i++){
            System.out.print("Masukan x"+(i+1)+": ");
            taksiran[i] = scan.nextDouble();
        }
        double hasilTaksir;
        hasilTaksir = hasilRegresi.TaksiranRegresi(solution, taksiran);
        System.out.println("F(x) = " + hasilTaksir);

        InputFile.writeRegresi(solution, taksiran, hasilTaksir, Regres);

        
    }

}

    
        
    




