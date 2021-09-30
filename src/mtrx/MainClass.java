package mtrx;
import java.util.Scanner;


public class MainClass {

    public static FileManager InputFile;
    public static String namaFile;
    public static double[] solution;
    public static Determinant hasilDeterminan;
    public static Interpolasi hasilInterpolasi;
    public static Eliminasi hasilEliminasi;
    public static Regresi hasilRegresi;
    public static SPL hasilSPL;
    public static int option;
    public Matrix InputMatrix;
    public Matrix MatrixUji;
    public static Scanner scan = new Scanner(System.in);

    public int cekSolusi(Matrix matriks) {
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


    public void Menu() {

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
        
        if (option==1) MenuSPL();
        else if (option==2) MenuDet();
        else if (option ==3) MenuInverse();
        else if (option == 4) MenuInter();
        else if (option ==5) MenuRegresi();
        else System.out.println("============================");
        }
    }
    
    public void MenuSPL() {
        
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

                //Input dari Keyboard
                if (option == 1) {
                    System.out.println("Masukkan matriks augmented");
                    InputMatrix.readMatrix();
                } else if (option == 2) {
                    System.out.println("Masukkan nama File");
                    InputFile.readFile();
                    InputMatrix = new Matrix(InputFile.matriksForm);
                }
                
                if (cekSolusi(hasilEliminasi.getMatrixEselonBaris(InputMatrix)) ==1) {
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
            } else if (option==2) {
                    System.out.println("Masukkan matriks augmented");
                    InputMatrix.readMatrix();
                    if (cekSolusi(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix)) ==1){
                        System.out.println("SPL tidak memiliki solusi!");
                    } else if (cekSolusi(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix)) ==2){
                        String[] solution = hasilSPL.solusiBanyak(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix));
                        for (int i = 0; i<solution.length; i++){
                            System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                            System.out.print("\n");
                        }
                    } else {
                        solution = hasilSPL.BackwardSubstitution(hasilEliminasi.getMatrixEselonBarisTereduksi(InputMatrix));
                        for (int i = 0; i<solution.length; i++){
                            System.out.print("x"+ (i+1) + " : "+ solution[i]) ;
                            System.out.print("\n");
                        }
                    }
                    InputFile.writeDoubleFile(solution);
            }

            else if (option==3){
                    if (InputMatrix.getLastIdxRow() == InputMatrix.getColEff()){
                        
                    }
                }
                
            }

    }

    public void MenuDet() {}

    public void MenuInverse() {}

    public void MenuInter() {}

    public void MenuRegresi() {}

    public void NewLine() {}

    public static void main(String[] args){


        
        
        
        //FileManager InputFile= new FileManager();
        
        
        

        //SPL
        if (option ==1){
            //prosedur spl()
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

            //pilih Gauss
            if (option==1){
                System.out.println("");
                System.out.println("============================");
                System.out.println("Pilih jenis masukan");
                System.out.println("1. Masukan dari keyboard");
                System.out.println("2. Masukan dari file");
                System.out.print("Masukkan pilihan anda: ");
                option = scan.nextInt();

                if (option==1){
                    System.out.println("============================");
                    matriks.readMatrix();
                    
                    if (hasilEliminasi.getMatrixEselonBaris(matriks).rowIsZero())
                    
                    hasilSPL.BackwardSubstitution(hasilEliminasi.getMatrixEselonBaris(matriks));
                }
            }
        }

        

        // Matrix InputKeyboard = new Matrix();

        /* ujicoba inputan (File & Keyboard) */
        //InputFile.readFile("tesGauss.txt");

        InputFile.readFile(fileName);
        //tes.readMatrix();

        /* ujicoba output matrix */
        InputFile.matriksForm.displayMatrix();
        NewLine();
        //InputKeyboard.displayMatrix();
        
        //tes swap
       

        
        /* Ujicoba Eliminasi cara Gauss  dan Gauss Jordan dengan inputFile*/
        Eliminasi tesEliminasi = new Eliminasi();
        tesEliminasi.getMatrixEselonBarisTereduksi(InputFile.matriksForm).displayMatrix();
        NewLine();
        //tesEliminasi.getMatrixEselonBaris(InputFile.matriksForm).displayMatrix();
        NewLine();
        //tesEliminasi.metodeinverse(InputFile.matriksForm).displayMatrix();
        NewLine();
        
        /*ujicoba SPL dengan 4 metode */
        SPL solusiFile = new SPL();
        //double[] x =solusiFile.BackwardSubstitution(tesEliminasi.getMatrixEselonBarisTereduksi(InputFile.matriksForm));
        //double[] x =solusiFile.BackwardSubstitution(tesEliminasi.getMatrixEselonBaris(InputFile.matriksForm));
        //double[] x =solusiFile.cramerMethod(InputFile.matriksForm);
        //double[] x =solusiFile.inverseMethod(InputFile.matriksForm);

        String[] x = solusiFile.solusiBanyak(tesEliminasi.getMatrixEselonBarisTereduksi(InputFile.matriksForm));
        NewLine();

        /*ujicoba interpolasi*/
        //Interpolasi SoalInter = new Interpolasi();

        /* khusus interpolasi
        Matrix matrixInter = new Matrix(InputFile.matriksForm);
        matrixInter.deleteCol(matrixInter.getLastIdxRow());
        double[] x = SoalInter.SolveInterpolasi(InputFile.matriksForm, InputFile.count);
        */
        
        
        
        for (int i = 0; i<x.length; i++){
            System.out.print("solusi x"+ (i+1) + " : "+ x[i]) ;
            System.out.print("\n");
        }
        /*
        double[] taksir = new double[1];
        taksir[0] = SoalInter.SolveTaksiran( x,9.2);
        fileName = scan.next();
        Untuk WriteFile, ntar dicek sudah ada file dengan nama sama/belum
        untuk solusi SPL
        InputFile.writeMatrixFile(fileName, x);
        
        untuk solusi determinan, taksiran
        InputFile.writeDoubleFile(fileName, taksir);
        scan.close();
        */
    }

    
        /* Untuk interpolasi bikin matrix n x 2 */
}    
        
    




