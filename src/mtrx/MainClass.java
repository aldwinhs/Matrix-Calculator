package mtrx;
import java.util.Scanner;




public class MainClass {

    public static FileManager InputFile;
    public static String namaFile;
    public static double[] solution;
    public static double[] taksiran;
    public static double[] hasilTaksir;
    public static Determinant hasilDeterminan = new Determinant(); 
    public static Interpolasi hasilInterpolasi = new Interpolasi();
    public static Eliminasi hasilEliminasi = new Eliminasi();
    public static Regresi hasilRegresi = new Regresi();;
    public static SPL hasilSPL = new SPL();
    public static int option;
    public static Matrix InputMatrix;
    public Matrix MatrixUji;
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        Menu();
    }    

    public static int cekSolusi(Matrix matriks) {
        int i;
        int kondisi;

        for (i = matriks.getLastIdxRow(); i >= 0; i--) {
            if (matriks.rowIsZero(i)) matriks.deleteRow(i);
        }

        Matrix temp = new Matrix(matriks);
        temp.deleteCol(matriks.getLastIdxCol());
        //tidak ada solusi
        if (temp.rowIsZero(temp.getLastIdxRow()) && matriks.getElement(matriks.getLastIdxRow(), matriks.getLastIdxCol()) != 0) {
            kondisi = 1;
        }

        //solusi banyak
        else if (matriks.getLastIdxCol()!=matriks.getRowEff()) {
            kondisi = 2;
        }

        //solusi unik
        else kondisi = 3;

        return kondisi;
    } 

    public static void Menu() {

        System.out.println("============================");
        System.out.println("MENU");
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
        else if (option == 4) MenuInter();
        else if (option ==5) MenuRegresi();
        else System.out.println("============================");
        
    }
    
    public static void MenuSPL() {
            System.out.println("");
            System.out.println("============================");
            System.out.println("MENU Sistem Persamaan Linier");
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Kaidah Cramer");
            System.out.println("5. Balik ke Main Menu");
            System.out.println("============================");
            System.out.print("Masukkan pilihan anda: ");
            option = scan.nextInt();

            if (option==1){
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                Matrix InputMatrix = new Matrix();
                //Input dari Keyboard
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.println("Masukkan nama File");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }
                Matrix tempMatrix = hasilEliminasi.getMatrixEselonBaris(InputMatrix);
                if (cekSolusi(tempMatrix) ==1) {
                    System.out.println("SPL tidak memiliki solusi!");
                } else if (cekSolusi(hasilEliminasi.getMatrixEselonBaris(InputMatrix)) ==2) {
                    String[] solution = hasilSPL.solusiBanyak(hasilEliminasi.getMatrixEselonBaris(InputMatrix));
                    for (int i = 0; i<solution.length; i++) {
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                } else {
                    solution = hasilSPL.BackwardSubstitution(hasilEliminasi.getMatrixEselonBaris(InputMatrix));
                    for (int i = 0; i<solution.length; i++) {
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                }
                InputFile.writeDoubleFile(solution);
            } 
            else if (option==2) {
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                //Input dari Keyboard
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.println("Masukkan nama File");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }

                if (cekSolusi(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix)) ==1){
                    System.out.println("SPL tidak memiliki solusi!");
                } 
                else if (cekSolusi(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix)) ==2){
                    String[] solution = hasilSPL.solusiBanyak(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix));
                    for (int i = 0; i<solution.length; i++){
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                } 
                else {
                    solution = hasilSPL.BackwardSubstitution(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix));
                    for (int i = 0; i<solution.length; i++){
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                }
                InputFile.writeDoubleFile(solution);
        }
            else if (option==3){
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                //Input dari Keyboard atau File
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.println("Masukkan nama File");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }

                if (InputMatrix.getLastIdxRow() == InputMatrix.getColEff()){
                    if (hasilDeterminan.detKofaktor(InputMatrix) != 0){
                        solution = hasilSPL.inverseMethod(InputMatrix);
                        for (int i = 0; i<solution.length; i++){
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                        }
                    }
                    else //Determinan = 0
                    System.out.println("Matriks tidak memiliki balikan, karena determinan = 0!");
                    
                    InputFile.writeDoubleFile(solution);
                }
                else {
                    System.out.println("Metode Balikan Hanya menerima Matriks dengan n Peubah dan n Persamaan!");
                }
            }
            else if (option==4){
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                //Input dari Keyboard atau File
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.println("Masukkan nama File");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }

                if (InputMatrix.getLastIdxRow() == InputMatrix.getColEff()){
                    solution = hasilSPL.cramerMethod(InputMatrix);
                    for (int i = 0; i<solution.length; i++){
                        System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                        System.out.print("\n");
                    }
                    InputFile.writeDoubleFile(solution);
                }
                else {
                    System.out.println("Metode Cramer Hanya menerima Matriks dengan n Peubah dan n Persamaan!");
                }
            }   
            
            else {
                Menu();
            }
    }

    public static void MenuDet() {
        System.out.println("");
        System.out.println("============================");
        System.out.println("MENU Determinan");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode kofaktor");
        System.out.println("3. Balik ke Main Menu");
        System.out.println("============================");
        System.out.print("Masukkan pilihan anda: ");
        option = scan.nextInt();
        if (option==1){
            System.out.println("");
            System.out.println("============================");
            System.out.println("Pilih jenis masukan");
            System.out.println("1. Masukan dari keyboard");
            System.out.println("2. Masukan dari file");
            System.out.print("Masukkan pilihan anda: ");
            option = scan.nextInt();

            //Input dari Keyboard
            if (option == 1) {
                System.out.println("Masukkan matriks augmented");
                InputMatrix.readMatrix();
            } else if (option == 2) {
                System.out.println("Masukkan nama File");
                InputFile.readFile();
                InputMatrix = new Matrix(InputFile.matriksForm);
            }

            if (InputMatrix.isSquare())solution[0] = hasilDeterminan.detReduksiBaris(InputMatrix);  
            else System.out.println("Matriks tidak memiliki determinan karena bukan matriks persegi");
            InputFile.writeDoubleFile(solution);
        }

        else if (option==2){
            System.out.println("");
            System.out.println("============================");
            System.out.println("Pilih jenis masukan");
            System.out.println("1. Masukan dari keyboard");
            System.out.println("2. Masukan dari file");
            System.out.print("Masukkan pilihan anda: ");
            option = scan.nextInt();

            //Input dari Keyboard
            if (option == 1) {
                System.out.println("Masukkan matriks augmented");
                InputMatrix.readMatrix();
            } else if (option == 2) {
                System.out.println("Masukkan nama File");
                InputFile.readFile();
                InputMatrix = new Matrix(InputFile.matriksForm);
            }
            if (InputMatrix.isSquare())solution[0] = hasilDeterminan.detKofaktor(InputMatrix);  
            else System.out.println("Matriks tidak memiliki determinan karena bukan matriks persegi");
            InputFile.writeDoubleFile(solution);
        }
        else Menu();
    }

    public static void MenuInverse() {}

    public static void MenuInter() {
        System.out.println("");
        System.out.println("============================");
        System.out.println("Pilih jenis masukan");
        System.out.println("1. Masukan dari keyboard");
        System.out.println("2. Masukan dari file");
        System.out.print("Masukkan pilihan anda: ");
        option = scan.nextInt();

        //Input dari Keyboard
        if (option == 1) {
            System.out.println("Masukkan matriks augmented 2x2");
            InputMatrix.readMatrix();
        }
        else if (option == 2) {
            System.out.println("Masukkan nama File");
            InputFile.readFile();
            InputMatrix = new Matrix(InputFile.matriksForm);
        }
        solution = hasilInterpolasi.SolveInterpolasi(InputMatrix, InputMatrix.getRowEff());
        System.out.print("Polinom Interpolasi yang melalui semua titik adalah p" + InputMatrix.getLastIdxRow() +"(x) =");
        for (int i=0; i< solution.length; i++){
            if (i==0) System.out.print(solution[i]);

            if (solution[i] > 0){
                if (i==1) System.out.print(" + " +  solution[i] + "x" +i);
                else System.out.print(" + " + solution[i] + "x^" +i);
            }
            else{
                 if (i==1) System.out.print(solution[i] + "x" +i);
                else System.out.print(solution[i] + "x^" +i);
            }
        }
        int NTaksiran,i;
        System.out.println("");
        System.out.print("Masukkan N jumlah taksiran : ");
        NTaksiran = scan.nextInt();
        for (i=0; i<NTaksiran; i++){
            taksiran[i] = scan.nextDouble();
        }

        for (i=0; i<NTaksiran; i++){
            hasilTaksir[i] = hasilInterpolasi.SolveTaksiran(solution, taksiran[i]);
            System.out.println("P" + InputMatrix.getLastIdxRow() +"(" +taksiran[i]+") = " + hasilTaksir[i]);
        }

        InputFile.writeInterpolasi(solution, taksiran, hasilTaksir);
    }

    public static void MenuRegresi() {}

}

    
        
    




