package cj.gui;

import cj.calculo.Grafica3D;
import cj.calculo.IntegralDobre;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.*;


public class Calculadora extends JFrame{
    private JPanel panel1;
    private JTextField txtFormula;
    private JSpinner limSupx;
    private JRadioButton areaRadioButton;
    private JRadioButton volumenRadioButton;
    private JSpinner limSupY;
    private JSpinner limInfX;
    private JSpinner limInfY;
    private JButton btnCalcular;
    private JLabel lblResultado;

    public Calculadora() {
        initComponents();
        btnCalcular.addActionListener((e) -> {

            String formula = txtFormula.getText();
            String tipo = areaRadioButton.isSelected() ? "area" : "volume";
            double a= (double)(int) limSupx.getValue();
            double b= (double)(int) limSupY.getValue();
            double c= (double)(int) limInfX.getValue();
            double d= (double)(int) limInfY.getValue();

            float xmin= (float) (int) limSupx.getValue();
            float xmax= (float)(int) limSupY.getValue();
            float ymin= (float)(int) limInfX.getValue();
            float ymax= (float)(int) limInfY.getValue();
            IntegralDobre calculo= new IntegralDobre();
            double resultado= calculo.calcularIntegral(formula,a,b,c,d,tipo);
            lblResultado.setText(String.format("Resultado: %.6f",resultado));
            if (tipo.equals("volumen")) Grafica3D.crearPanelGrafico(formula,xmax,xmin,ymax,ymin);

        });
    }

    private void initComponents() {
        setContentPane(panel1);
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(600, 600);
        setVisible(true);
        JSpinner campoA=new JSpinner(new SpinnerNumberModel(0.0, -100.0, 100.0, 0.1));
        limSupx.add(campoA);
        JSpinner campoB=new JSpinner(new SpinnerNumberModel(0.0, -100.0, 100.0, 0.1));
        limInfX.add(campoB);
        JSpinner campoC =new JSpinner(new SpinnerNumberModel(0.0, -100.0, 100.0, 0.1));
        limInfY.add(campoC);
        JSpinner campoD=new JSpinner(new SpinnerNumberModel(0.0, -100.0, 100.0, 0.1));
        limSupY.add(campoD);
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        Calculadora calculadora = new Calculadora();
    }
}
