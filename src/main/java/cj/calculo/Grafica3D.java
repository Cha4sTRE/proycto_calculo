package cj.calculo;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.factories.AWTChartFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.BoundingBox3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.SurfaceBuilder;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.mariuszgromada.math.mxparser.*;

public class Grafica3D {

    public static void crearPanelGrafico(String formula, float xMin, float xMax, float yMin, float yMax) {
        // Define el evaluador con mXparser
        Function funcion = new Function("f(x, y) = " + formula);

        // Mapper que transforma los valores en Z evaluando la función
        Mapper xmapper = new Mapper() {
            public double f(double x, double y) {
                return funcion.calculate(x, y);
            }
        };

        // Define el rango y la densidad de la malla
        Range rangeX = new Range((float) xMin, (float) xMax);
        Range rangeY = new Range((float) yMin, (float) yMax);
        int steps = 60;

        // Construye la superficie
        Shape surface = new SurfaceBuilder().orthonormal(new OrthonormalGrid(rangeX, steps, rangeY, steps), xmapper);
        surface.setColor(Color.BLUE);
        surface.setWireframeDisplayed(true);
        surface.setWireframeColor(Color.BLACK);

        // Crea el gráfico correctamente
        AWTChartFactory factory = new AWTChartFactory();
        Chart chart = factory.newChart(Quality.Advanced()).addMouse().getThread().getChart();
        chart.getScene().getGraph().add(surface);
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax()));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);
        chart.getView().setBoundManual(new BoundingBox3d(xMin, xMax, yMin, yMax, -10, 10));
        chart.open("Gráfica 3D", 800, 600);
    }


}
