package com.maxim.matrix;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double[][] values1 = new double[][]{{1, 2}, {1, 2}};
        double[][] values2 = new double[][]{{3, 4}, {3, 4}};
        Matrix matrix = new Matrix(values1);
        Matrix matrix2 = new Matrix(values2);

        Date start = new Date();
        multypli(values1, values2);
        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());

        Date startNew = new Date();
        multypli(matrix, matrix2);
        Date endNew = new Date();
        System.out.println(startNew.getTime() - endNew.getTime());
    }

    public static Matrix multypli(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        List<Thread> threads = new ArrayList<>();
        int rowCounts = firstMatrix.length;
        for (int i = 0; i < rowCounts; i++) {
            Multythreader task = new Multythreader(result, firstMatrix, secondMatrix, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            if (threads.size() % 10 == 0) {
                waitForThreads(threads);
            }
        }

        return new Matrix(result);
    }

    public static void output(Matrix matrix) {
        {
            for (double[] row : matrix.getValues())
                System.out.println(Arrays.toString(row));
        }
    }

    public static Matrix multypli(Matrix firstMatrix, Matrix secondMatrix) {
        return multypli(firstMatrix.getValues(), secondMatrix.getValues());
    }

    private static void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
