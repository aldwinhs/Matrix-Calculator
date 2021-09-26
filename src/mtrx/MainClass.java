package mtrx;

public class MainClass {
    public static void main(String[] args){
        Matrix tes = new Matrix();
        tes.readMatrix();
        tes.displayMatrix();
        // Determinant detMatriks = new Determinant();
        // SPL tesGauss = new SPL();


        // System.out.print("----------------------------------\n");
        // System.out.print("coba buat matriks reduksi\n");
        // tesGauss.Gauss(tes).displayMatrix();
        // System.out.print("----------------------------------\n");
        // System.out.print("det reduksi baris : ");
        // System.out.print(detMatriks.detReduksiBaris(tes));
        
        // System.out.print("\n----------------------------------\n");
        // System.out.print("det faktor : ");
        // System.out.print(detMatriks.detKofaktor(tes));

        Eliminasi eliminasi = new Eliminasi();
        tes = eliminasi.metodeGauss(tes);
        tes.displayMatrix();
    }
}



