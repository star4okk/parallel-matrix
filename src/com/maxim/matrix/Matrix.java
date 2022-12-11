package com.maxim.matrix;

public final class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] values;

    public int getColumns() {
        return columns;
    }

    public double[][] getValues() {
        return values;
    }

    public Matrix(double[][] values) {
        this.rows = values.length;
        this.columns = values[0].length;
        this.values = new double[rows][columns];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                this.values[i][j] = values[i][j];
            }
        }
    }

    public Matrix multyply(double[][] values) {
        return multyply(new Matrix(values));
    }

    public Matrix multyply(Matrix matrix) {
        double[][] secondMatrix = matrix.getValues();
        int rowsCount = this.rows;
        int columnsCount = secondMatrix[0].length;
        int secondMatrixRowsCount = secondMatrix.length;
        double[][] values = new double[rowsCount][columnsCount];

        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j < columnsCount; ++j) {
                for (int k = 0; k < secondMatrixRowsCount; ++k) {
                    values[i][j] += this.values[i][k] * secondMatrix[k][j];
                }
            }
        }

        return new Matrix(values);
    }
}
