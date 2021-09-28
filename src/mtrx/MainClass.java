package mtrx;

public class MainClass {

    public static void NewLine(){
        System.out.println("--------------------------------------------------------");
    }

    public static void main(String[] args){
        /* Inisiasi */
        FileManager InputFile= new FileManager();
        //Matrix InputKeyboard = new Matrix();

        /* ujicoba inputan (File & Keyboard) */
        //InputFile.readFile("tesGauss.txt");
        InputFile.readFile("tesInverse.txt");
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
       tesEliminasi.getMatrixEselonBaris(InputFile.matriksForm).displayMatrix();
       NewLine();
       tesEliminasi.metodeinverse(InputFile.matriksForm).displayMatrix();
       NewLine();

       /*ujicoba SPL dengan 4 metode */
       SPL solusiFile = new SPL();
       //double[] x =solusiFile.BackwardSubstitution(tesEliminasi.getMatrixEselonBarisTereduksi(InputFile.matriksForm));
       //double[] x =solusiFile.BackwardSubstitution(tesEliminasi.getMatrixEselonBaris(InputFile.matriksForm));
       double[] x =solusiFile.cramerMethod(InputFile.matriksForm);
       //double[] x =solusiFile.inverseMethod(InputFile.matriksForm);
       NewLine();
       
        for (int i = 0; i<x.length; i++){
            System.out.print("solusi x"+ (i+1) + " : "+ x[i]) ;
            System.out.print("\n");
        }
        
    }
}    
        
    




