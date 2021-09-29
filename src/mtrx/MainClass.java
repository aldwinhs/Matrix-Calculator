package mtrx;
import java.util.Scanner;


public class MainClass {

    public static void NewLine(){
        System.out.println("--------------------------------------------------------");
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        

        /* Inisiasi */
        FileManager InputFile= new FileManager();
        String fileName;
        //Matrix InputKeyboard = new Matrix();

        /* ujicoba inputan (File & Keyboard) */
        //InputFile.readFile("tesGauss.txt");
        fileName = scan.next();
        InputFile.readFile(fileName);
        //tes.readMatrix();

        /* ujicoba output matrix */
        InputFile.matriksForm.displayMatrix();
        NewLine();
        //InputKeyboard.displayMatrix();
        
        //tes swap
       

        
        /* Ujicoba Eliminasi cara Gauss  dan Gauss Jordan dengan inputFile*/
        Eliminasi tesEliminasi = new Eliminasi();
        //tesEliminasi.getMatrixEselonBarisTereduksi(InputFile.matriksForm).displayMatrix();
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
        NewLine();

       /*ujicoba interpolasi*/
        Interpolasi SoalInter = new Interpolasi();
        /*khusus interpolasi
        Matrix matrixInter = new Matrix(InputFile.matriksForm);
        matrixInter.deleteCol(matrixInter.getLastIdxRow());

        */
        double[] x = SoalInter.SolveInterpolasi(InputFile.matriksForm, InputFile.count);
        for (int i = 0; i<x.length; i++){
            System.out.print("solusi x"+ (i+1) + " : "+ x[i]) ;
            System.out.print("\n");
        }
        double[] taksir = new double[1];
        taksir[0] = SoalInter.SolveTaksiran( x,9.2);
        fileName = scan.next();
        //Untuk WriteFile, ntar dicek sudah ada file dengan nama sama/belum
        //untuk solusi SPL
        //InputFile.writeMatrixFile(fileName, x);
        
        //untuk solusi determinan, taksiran
        InputFile.writeDoubleFile(fileName, taksir);
        scan.close();
        
    }

        /* Untuk interpolasi bikin matrix n x 2 */
}    
        
    




