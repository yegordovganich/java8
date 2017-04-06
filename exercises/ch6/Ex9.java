package exercises.ch6;

import java.util.Arrays;

/**
 * Created by y.dovganich on 04.04.2017.
 */
/*
9. You can use the parallelPrefix method to parallelize the computation of Fibonacci
numbers. We use the fact that the nth Fibonacci number is the top
left coefficient of Fn, where F = ( {1 1} {1 0} ) . Make an array filled with 2 × 2 matrices.
Define a Matrix class with a multiplication method, use parallelSetAll to
make an array of matrices, and use parallelPrefix to multiply them.
 */
public class Ex9 {
    public static void main(String[] args) {
        Matrix[] array = new Matrix[10];
        int[][] defaultMatrix = {{1, 1}, {1, 0}};
        Arrays.parallelSetAll(array, __ -> new Matrix(defaultMatrix));
        Arrays.parallelPrefix(array, Matrix::multiply);

        Arrays.asList(array).forEach(m -> System.out.println(m.m[0][0]));
    }

    private static class Matrix {
        private int[][] m;

        Matrix(int[][] m) {
            this.m = m;
        }

        Matrix multiply(Matrix that) {
            return new Matrix(new int[][]
                {{  this.m[0][0] * that.m[0][0] + this.m[0][1] * that.m[1][0],
                    this.m[0][0] * that.m[0][1] + this.m[0][1] * that.m[1][1]
                }, {
                    this.m[1][0] * that.m[0][0] + this.m[1][1] * that.m[1][0],
                    this.m[1][0] * that.m[0][1] + this.m[1][1] * that.m[1][1]
                }}
            );
        }
    }
}
