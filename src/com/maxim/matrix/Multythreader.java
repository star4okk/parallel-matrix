package com.maxim.matrix;

public class Multythreader implements Runnable {
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final double[][] result;
    private final int row;

    public Multythreader(double[][] result, double[][] matrix1, double[][] matrix2, int row) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
    }

    @Override
    public void run() {
        multyply();
    }

    public void multyply() {
        for (int i = 0; i < matrix2[0].length; i++) {
            result[row][i] = 0;
            for (int j = 0; j < matrix1[row].length; j++) {
                result[row][i] += matrix1[row][j] * matrix2[j][i];
            }
        }
    }
}
