package cj.gui;

import cj.calculo.Grafica3D;
import cj.calculo.IntegralDobre;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;


import javax.swing.*;
import java.awt.*;


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
    private JButton btnEjem1;
    private JButton btnEjem2;
    private JButton btnEjem3;
    private JButton btnAyuda;

    public Calculadora() {
        initComponents();
        setContentPane(panel1);
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(600, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        ImageIcon icon= new ImageIcon(getClass().getResource("/img/ayuda.png"));
        Image scale= icon.getImage().getScaledInstance(btnAyuda.getWidth(), btnAyuda.getHeight(), Image.SCALE_SMOOTH);
        btnAyuda.setIcon(new ImageIcon(scale));
        btnEjem1.addActionListener((e)->{
            txtFormula.setText("x^2-y^2");
            Integer limiteP=1;
            Integer limiteI=-1;
            limSupx.setValue(limiteP);
            limSupY.setValue(limiteI);
            limInfX.setValue(limiteP);
            limInfY.setValue(limiteI);
            volumenRadioButton.setSelected(true);

        });
        btnCalcular.addActionListener((e) -> {
            String formula = txtFormula.getText();
            double a= (double)(int) limSupx.getValue();
            double b= (double)(int) limSupY.getValue();
            double c= (double)(int) limInfX.getValue();
            double d= (double)(int) limInfY.getValue();
            String tipo = areaRadioButton.isSelected() ? "area" : "volumen";
            if(!formula.equals("")&&a!=0&&b!=0&&c!=0&&d!=0){
                float xmin= (float) (int) limSupx.getValue();
                float xmax= (float)(int) limSupY.getValue();
                float ymin= (float)(int) limInfX.getValue();
                float ymax= (float)(int) limInfY.getValue();
                IntegralDobre calculo= new IntegralDobre();
                double resultado= calculo.calcularIntegral(formula,a,b,c,d,tipo);
                lblResultado.setText(String.format("Resultado: %.6f",resultado));
                if(tipo.equals("volumen")) Grafica3D.crearPanelGrafico(formula,xmax,xmin,ymax,ymin);
            }else{
                JOptionPane.showOptionDialog(this,"un campo esta vacio o tiene cero!","Alerta",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
            }

        });
        btnEjem2.addActionListener((e)->{
            txtFormula.setText("sin(x) * cos(y)");
            Integer limiteP=6;
            Integer limiteI=-6;
            limSupx.setValue(limiteP);
            limSupY.setValue(limiteI);
            limInfX.setValue(limiteP);
            limInfY.setValue(limiteI);
            volumenRadioButton.setSelected(true);
        });
        btnEjem3.addActionListener((e)->{
            txtFormula.setText("exp(-x^2 - y^2)");
            Integer limiteP=1;
            Integer limiteI=-1;
            limSupx.setValue(limiteP);
            limSupY.setValue(limiteI);
            limInfX.setValue(limiteP);
            limInfY.setValue(limiteI);
            volumenRadioButton.setSelected(true);
        });
        btnAyuda.addActionListener((e)->{
            int opcion= JOptionPane.showOptionDialog(
                    this,
                    String.format(
                            "Ayuda de Sintaxis Matematica\n" +
                                    "\n" +
                                    "" +
                                    "  '+'  Suma\n" +
                                    "  '-'  Resta\n" +
                                    "  '*'  Multiplicacion\n" +
                                    "  '/'  Division'\n\n"
                    ),
                    "Cuadro de Ayuda",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"Funciones Algebraicas","Funciones Trigonometricas"},null);
            switch(opcion){
                case 0:
                    JOptionPane.showOptionDialog(
                            this,
                            " sqrt(x):   Raíz cuadrada\n" +
                                    "  abs(x):   Valor absoluto\n" +
                                    "  exp(x):   Exponencial (e^x)\n" +
                                    "  ln(x):   Logaritmo natural\n" +
                                    "  log(x):   Logaritmo en base 10\n" +
                                    "  round(x):   Redondea al entero más cercano\n" +
                                    "  floor(x):   Redondea hacia abajo\n" +
                                    "  ceil(x):   Redondea hacia arriba\n\n" +
                                    "Ejemplo:  x^2 + y^2"
                            ,
                            "Simbolos Algebraicos",
                            JOptionPane.CLOSED_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null, null, null);
                    break;
                case 1:
                    JOptionPane.showOptionDialog(
                            this,
                            "  sen(x):  Seno\n" +
                                    "  cos(x):  Coseno\n" +
                                    "  tan(x):  Tangente\n" +
                                    "  asin(x):  Arcoseno\n" +
                                    "  acos(x):  Arcocoseno\n" +
                                    "  atan(x):  Arcotangente\n\n" +
                                    "Ejemplo:  sin(x) * cos(y)"
                            ,
                            "Simbolos Trigonometricas",
                            JOptionPane.CLOSED_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null, null, null);
                    break;

                default: break;
            }

        });
    }

    private void initComponents() {

        limSupx.setModel(new SpinnerNumberModel(0.0, -100.0, 100.0, 0.1));
        limInfX.setModel(new SpinnerNumberModel(0.0, -100.0, 100.0, 0.1));
        limSupY.setModel(new SpinnerNumberModel(0.0, -100.0, 100.0, 0.1));
        limInfY.setModel(new SpinnerNumberModel(0.0, -100.0, 100.0, 0.1));

    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        new Calculadora();
    }
}
