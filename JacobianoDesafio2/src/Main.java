// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Definir la matriz A y el vector b del sistema Ax = b
        double[][] A = {
                {0.52, 0.20, 0.25},
                {0.30, 0.50, 0.20},
                {0.18, 0.30, 0.55}
        };
        double[] b = {4800, 5810, 5690};

        // Parámetros del método de Jacobi
        int n = b.length;
        double[] x = new double[n]; // Solución inicial
        double[] xNew = new double[n]; // Solución para la siguiente iteración
        double tolerance = 1e-6; // Tolerancia para la convergencia
        int maxIterations = 100; // Número máximo de iteraciones

        // Método de Jacobi
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Iterar sobre cada ecuación
            for (int i = 0; i < n; i++) {
                double sum1 = 0;
                double sum2 = 0;

                // Calcular la suma de los términos que no están en la diagonal
                for (int j = 0; j < n; j++) {
                    if (j < i) {
                        sum1 += A[i][j] * x[j];
                    } else if (j > i) {
                        sum2 += A[i][j] * x[j];
                    }
                }

                // Calcular el nuevo valor de x[i]
                xNew[i] = (b[i] - sum1 - sum2) / A[i][i];
            }

            // Verificar la convergencia
            boolean converged = true;
            for (int i = 0; i < n; i++) {
                if (Math.abs(xNew[i] - x[i]) > tolerance) {
                    converged = false;
                    break;
                }
            }

            // Actualizar x
            System.arraycopy(xNew, 0, x, 0, n);

            if (converged) {
                System.out.println("Converged after " + (iteration + 1) + " iterations.");
                break;
            }
        }

        // Mostrar resultados
        System.out.println("Solution:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.4f%n", i + 1, x[i]);
        }
    }
}