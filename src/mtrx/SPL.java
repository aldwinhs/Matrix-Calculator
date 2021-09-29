package mtrx;

public class SPL {
    
    public double[] BackwardSubstitution(Matrix matriks) {
        int j,k;
        double [] solusi = new double[matriks.getColEff()-1];
        

        for ( j = matriks.getColEff()-2; j >= 0 ; j--) {
            solusi[j] = matriks.getElement(j, matriks.getColEff()-1);
            for ( k = matriks.getColEff()-2; k > j; k--) {
                solusi [j] -= matriks.getElement(j, k) * solusi[k];
                }
            }

        return solusi;
    }
   
    public String[] solusiBanyak(Matrix matrix) {
        /* KAMUS */
        Matrix solusi = new Matrix(matrix.getColEff() - 1, matrix.getColEff());
        String[] solusiParametrik = new String[matrix.getColEff() - 1];
        int i, j, k;
        char[] varParametrik = new char[]{'s', 't', 'u', 'w', 'x',
                                          'v', 'w', 'x', 'y', 'z', 
                                          'a', 'b', 'c', 'd', 'e',
                                          'f', 'g', 'i', 'j', 'k',
                                          'k', 'm', 'n', 'o', 'p',
                                          'q', 'r',};
        /* ALGORIMA */
        // INISIALISASI ARRAY SOLUSI
        for (i = 0; i < solusi.getRowEff(); i++) {
            solusi.setElement(i, i + 1, 1);
        }

        // BACKWARD SUBSTITUTION
        for (i = matrix.getLastIdxRow(); i > -1; i--) {
            j = 0;
            while ((j < matrix.getLastIdxCol()) && (matrix.getElement(i, j) == 0)) {
                j++;
            }
            if (j != matrix.getLastIdxCol()) {
                solusi.setElement(j, j + 1, 0);
                solusi.setElement(j, 0, matrix.getElement(i, matrix.getLastIdxCol()));

                for (k = 0; k < matrix.getLastIdxCol(); k++) {
                    if (j != k) {
                        solusi.plusMinusRow(j, k, 1, matrix.getElement(i, k), false);
                    }
                }
            }
        }
        
        // CHANGE TO PARAMETRIC
        char[] varParametrikFix = new char[solusi.getRowEff()];
        j = 0;
        for (i = 0; i < solusi.getRowEff(); i++) {
            if (solusi.getElement(i, i + 1) == 1) {
                varParametrikFix[i] = varParametrik[j];
                j++;
            }
        }
        for (i = 0; i < solusi.getRowEff(); i++) {
            solusiParametrik[i] = "";
            for (j = 1; j < solusi.getLastIdxCol(); j++) {
                if (solusi.getElement(i, j) != 0) {
                    if (solusi.getElement(i, j) == 1) solusiParametrik[i] += varParametrikFix[j - 1] + " + ";
                    else if (solusi.getElement(i, j) == -1) {
                        if (solusiParametrik[i] != "") {
                            solusiParametrik[i] = solusiParametrik[i].substring(0, solusiParametrik[i].length() - 3);
                            solusiParametrik[i] += " - ";
                            solusiParametrik[i] += varParametrikFix[j - 1] + " + ";
                        } else {
                            solusiParametrik[i] += varParametrikFix[j - 1] + " + ";
                        }
                    }
                    else if (solusi.getElement(i, j) < 0) {
                        if (solusiParametrik[i] != "") {
                            solusiParametrik[i] = solusiParametrik[i].substring(0, solusiParametrik[i].length() - 3);
                            solusiParametrik[i] += " - ";
                            solusiParametrik[i] += Double.toString(Math.abs(solusi.getElement(i, j))) + varParametrikFix[j - 1] + " + ";
                        } else {
                            solusiParametrik[i] += Double.toString(solusi.getElement(i, j)) + varParametrikFix[j - 1] + " + ";
                        }
                    } else {
                        solusiParametrik[i] += Double.toString(solusi.getElement(i, j)) + varParametrikFix[j - 1] + " + ";
                    }
                }
            }
            if (solusiParametrik[i] == "") {
                solusiParametrik[i] += Double.toString(solusi.getElement(i, 0));
            } else {
                if (solusi.getElement(i, 0) != 0) {
                    if (solusi.getElement(i, 0) < 0) {
                        if (solusiParametrik[i] != "") {
                            solusiParametrik[i] = solusiParametrik[i].substring(0, solusiParametrik[i].length() - 3);
                            solusiParametrik[i] += " - ";
                            solusiParametrik[i] += Double.toString(Math.abs(solusi.getElement(i, 0)));
                        } else {
                            solusiParametrik[i] += Double.toString(solusi.getElement(i, 0));
                        }
                    } else {
                        solusiParametrik[i] += Double.toString(solusi.getElement(i, 0));
                    }
                } else {
                    if (solusiParametrik[i] != "") solusiParametrik[i] = solusiParametrik[i].substring(0, solusiParametrik[i].length() - 3);
                }
            }
        }
        return solusiParametrik;
    }

    public double[] cramerMethod(Matrix matriks) {
        /* KAMUS */
        Matrix matriksD = new Matrix(matriks);
        Matrix matriksDx;
        Determinant determinant = new Determinant();
        double[] solusi = new double[matriks.getRowEff()];
        /* ALGORITMA */
        matriksD.deleteCol(matriksD.getLastIdxCol());

        for (int i = 0; i < matriks.getRowEff(); i++) {
            matriksDx = new Matrix(matriks);
            matriksDx.swapCol(i, matriksDx.getLastIdxCol());
            matriksDx.deleteCol(matriksDx.getLastIdxCol());
            solusi[i] = determinant.detKofaktor(matriksDx) / determinant.detKofaktor(matriksD);
        }
        
        return solusi;
    }

    public double[] inverseMethod(Matrix matriks){
        int i;
        double[] solusi = new double[matriks.getRowEff()];
        Matrix konstan = new Matrix(matriks.row, 1);
        Matrix temp = new Matrix(matriks);

        //Copy elemen pada kolom terakhir matriks ke konstan
        for (i =0; i<matriks.row; i++){
            konstan.setElement(i, 0, matriks.getElement(i, matriks.getLastIdxCol()));
        }
        //Cari matriks yang merepresentasikan peubah
        temp.deleteCol(temp.getLastIdxCol());
        
        Eliminasi tempInverse = new Eliminasi();
        //tempInverse.metodeinverse(temp).multiplyMatrix(konstan).displayMatrix();
        for (i = 0; i<temp.getRowEff(); i++){
            solusi[i] = tempInverse.metodeinverse(temp).multiplyMatrix(konstan).getElement(i, 0);
        }
        return solusi;

    }
    

}
