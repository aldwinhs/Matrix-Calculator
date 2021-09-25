package mtrx;

public class MainClass {
    public static void main(String[] args){
        Matrix tes = new Matrix();
        tes.readMatrix();
        tes.displayMatrix();

        System.out.print("----------------------------------\n");
        System.out.print("coba buat matriks reduksi\n");
        System.out.print("----------------------------------\n");
        SPL tesGauss = new SPL();
        tesGauss.Gauss(tes).displayMatrix();;
        System.out.print("----------------------------------\n");
        Determinant detMatriks = new Determinant();
        System.out.print("----------------------------------\n");
        System.out.print("det faktor \n");
        System.out.print("----------------------------------\n");
        System.out.print(detMatriks.detKofaktor(tes)+ 1);
    }
}
