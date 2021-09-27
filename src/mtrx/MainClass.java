package mtrx;

public class MainClass {
    public static void main(String[] args){
        Matrix tes = new Matrix();
        //tes.readMatrix();

        FileManager tesFile = new FileManager();
        tesFile.readFile("tes.txt");
        // tesFile.matriksForm.addIdentity();
        tesFile.matriksForm.displayMatrix();

        // tesFile.matriksForm.displayMatrix();
        // Determinant detMatriks = new Determinant();
        // SPL tesGauss = new SPL();


        // System.out.print("----------------------------------\n");
        // System.out.print("coba buat matriks reduksi\n");
        // tesGauss.Gauss(tes).displayMatrix();
        // System.out.print("----------------------------------\n");
        // System.out.print("det reduksi baris : ");
        
        
        // System.out.print("\n----------------------------------\n");
        // System.out.print("det faktor : ");
        // System.out.print(detMatriks.detKofaktor(tes));
        //System.out.println("\nDeterminan : ");
        //System.out.println(detMatriks.detReduksiBaris(tesFile.matriksForm));
       
        
        // Eliminasi eliminasi = new Eliminasi();
        // System.out.print("\n----------------------------------\n");
        
        // eliminasi.getMatrixEselonBaris(tesFile.matriksForm).displayMatrix();;
        // System.out.print("\n----------------------------------\n");
        // System.out.print("----------------------------------\n");
        // System.out.print("-----------------Gauss JORDAN-----------------\n");
        // eliminasi.getMatrixEselonBarisTereduksi(tesFile.matriksForm).displayMatrix();
        // System.out.print("\n----------------------------------\n");
        
        // System.out.print("\n----------------------------------\n");
        //tesFile.matriksForm.displayMatrix();
        
        Eliminasi tesEliminasi = new Eliminasi();
        SPL solusiFile = new SPL();

        System.out.print("\n----Matriks tereduksi baris\n");
        tesEliminasi.getMatrixEselonBarisTereduksi(tesFile.matriksForm).displayMatrix();

        
        double[] x =solusiFile.BackwardSubstitution(tesEliminasi.getMatrixEselonBaris(tesFile.matriksForm));
        for (int i = 0; i<x.length; i++){
            System.out.print("solusi x : "+ x[i]) ;
            System.out.print("\n");
        }
        
    }
}    
        
    




