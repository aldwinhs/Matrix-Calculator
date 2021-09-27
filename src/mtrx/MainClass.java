package mtrx;

public class MainClass {
    public static void main(String[] args){
        //Matrix tes = new Matrix();

        FileManager tesFile = new FileManager();
        tesFile.readFile("tes.txt");

        tesFile.matriksForm.displayMatrix();
        Determinant detMatriks = new Determinant();
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
       
        
        Eliminasi eliminasi = new Eliminasi();
        System.out.print("\n----------------------------------\n");
        
        eliminasi.metodeGauss(tesFile.matriksForm).displayMatrix();;
        System.out.print("\n----------------------------------\n");
        System.out.print("----------------------------------\n");
        System.out.print("-----------------Gauss JORDAN-----------------\n");
        eliminasi.metodeGaussJordan(tesFile.matriksForm).displayMatrix();
        System.out.print("\n----------------------------------\n");
        
        System.out.print("\n----------------------------------\n");
        //tesFile.matriksForm.displayMatrix();
        
        

    }
}



