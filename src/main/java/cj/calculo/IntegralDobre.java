package cj.calculo;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

public class IntegralDobre {

    public IntegralDobre() {}

    public double calcularIntegral(String funcionEst, double a, double b, double c, double d, String tipoCalculo) {

        License.iConfirmNonCommercialUse("Estudiante de ingenieria de software- proyecto de software");

        // Si el tipo de cálculo es "área", entonces f(x,y) = 1
        if (tipoCalculo.equalsIgnoreCase("area")) {
            funcionEst = "1";
        }


        // Crear expresión y definir argumentos x, y
        Expression expr = new Expression(funcionEst);
        Argument xArg = new Argument("x");
        Argument yArg = new Argument("y");
        expr.addArguments(xArg, yArg);

        int n = 100; // número de divisiones en x
        int m = 100; // número de divisiones en y

        double dx = (b - a) / n;
        double dy = (d - c) / m;
        double suma = 0.0;

        // Regla del punto medio
        for (int i = 0; i < n; i++) {
            double x = a + (i + 0.5) * dx;
            xArg.setArgumentValue(x);

            for (int j = 0; j < m; j++) {
                double y = c + (j + 0.5) * dy;
                yArg.setArgumentValue(y);

                double valor = expr.calculate();

                if (!Double.isNaN(valor)) {
                    suma += valor;
                } else {
                    System.out.println("NaN en x=" + x + ", y=" + y);
                }
            }
        }

        return suma * dx * dy;
    }

}
