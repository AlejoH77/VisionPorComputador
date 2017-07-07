package Trabajo1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

public class Metodos {

    public BufferedImage Imagen = null;
    public int[][] Matriz = null;
    public boolean[][] MatrizBool = null;
    public int[][] MRotulacion;
    public int[][] MatrizR = null;
    public int[][] MatrizG = null;
    public int[][] MatrizB = null;
    public int[] ArregloObjetos = null;
    public int[][] MatrizTransformacion = new int[3][3];
    public double[][] MatrizTransformacionD = new double[3][3];
    public int[][] Filtro = null;
    public int[][] xKernel = new int[3][3];
    public int[][] yKernel = new int[3][3];
    public int mayori;
    public int menori;
    public int mayorj;
    public int menorj;
    public int a = 0;
    //Mascaras Kirsch
    public int[][] Mascara1K = {{5, -3, -3}, {5, 0, -3}, {5, -3, -3}};
    public int[][] Mascara2K = {{-3, -3, -3}, {5, 0, -3}, {5, 5, -3}};
    public int[][] Mascara3K = {{-3, -3, -3}, {-3, 0, -3}, {5, 5, 5}};
    public int[][] Mascara4K = {{-3, -3, -3}, {-3, 0, 5}, {-3, 5, 5}};
    public int[][] Mascara5K = {{-3, -3, 5}, {-3, 0, 5}, {-3, -3, 5}};
    public int[][] Mascara6K = {{-3, 5, 5}, {-3, 0, 5}, {-3, -3, -3}};
    public int[][] Mascara7K = {{5, 5, 5}, {-3, 0, -3}, {-3, -3, -3}};
    public int[][] Mascara8K = {{5, 5, -3}, {5, 0, -3}, {-3, -3, -3}};
    //Mascaras Robinson
    public int[][] Mascara1R = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
    public int[][] Mascara2R = {{0, -1, -2}, {1, 0, -1}, {2, 1, 0}};
    public int[][] Mascara3R = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
    public int[][] Mascara4R = {{-2, -1, 0}, {-1, 0, 1}, {0, 1, 2}};
    public int[][] Mascara5R = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
    public int[][] Mascara6R = {{0, 1, 2}, {-1, 0, 1}, {-2, -1, 0}};
    public int[][] Mascara7R = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};
    public int[][] Mascara8R = {{2, 1, 0}, {1, 0, -1}, {0, -1, -2}};

    public Metodos(BufferedImage imagen) {//constructor
        Imagen = imagen;
    }

    public void MatrizIdentidad(int[][] matriz) {
        MatrizTransformacion[0][0] = 1;
        MatrizTransformacion[0][1] = 0;
        MatrizTransformacion[0][2] = 0;
        MatrizTransformacion[1][0] = 0;
        MatrizTransformacion[1][1] = 1;
        MatrizTransformacion[1][2] = 0;
        MatrizTransformacion[2][0] = 0;
        MatrizTransformacion[2][1] = 0;
        MatrizTransformacion[2][2] = 1;
    }

    public void MatrizIdentidadD(double[][] matriz) {
        MatrizTransformacionD[0][0] = 1;
        MatrizTransformacionD[0][1] = 0;
        MatrizTransformacionD[0][2] = 0;
        MatrizTransformacionD[1][0] = 0;
        MatrizTransformacionD[1][1] = 1;
        MatrizTransformacionD[1][2] = 0;
        MatrizTransformacionD[2][0] = 0;
        MatrizTransformacionD[2][1] = 0;
        MatrizTransformacionD[2][2] = 1;
    }

    public int MultPColumna(int[][] matriz, int valor1, int valor2, int valor3) {
        int total = 0;
        total = total + matriz[0][0] * valor1;
        total = total + matriz[1][0] * valor2;
        total = total + matriz[2][0] * valor3;
        return total;
    }

    public int MultSColumna(int[][] matriz, int valor1, int valor2, int valor3) {
        int total = 0;
        total = total + matriz[0][1] * valor1;
        total = total + matriz[1][1] * valor2;
        total = total + matriz[2][1] * valor3;
        return total;
    }

    public double DMultPColumna(double[][] matriz, int valor1, int valor2, int valor3) {
        double total = 0;
        total = total + matriz[0][0] * valor1;
        total = total + matriz[1][0] * valor2;
        total = total + matriz[2][0] * valor3;
        return total;
    }

    public double DMultSColumna(double[][] matriz, int valor1, int valor2, int valor3) {
        double total = 0;
        total = total + matriz[0][1] * valor1;
        total = total + matriz[1][1] * valor2;
        total = total + matriz[2][1] * valor3;
        return total;
    }

    public void MatricesRoberts() {
        xKernel[0][0] = 0;
        xKernel[0][1] = 0;
        xKernel[0][2] = 0;
        xKernel[1][0] = 0;
        xKernel[1][1] = -1;
        xKernel[1][2] = 0;
        xKernel[2][0] = 0;
        xKernel[2][1] = 0;
        xKernel[2][2] = 1;
        yKernel[0][0] = 0;
        yKernel[0][1] = 0;
        yKernel[0][2] = 0;
        yKernel[1][0] = 0;
        yKernel[1][1] = 0;
        yKernel[1][2] = -1;
        yKernel[2][0] = 0;
        yKernel[2][1] = 1;
        yKernel[2][2] = 0;
    }

    public void MatricesSobel() {
        xKernel[0][0] = 1;
        xKernel[0][1] = 0;
        xKernel[0][2] = -1;
        xKernel[1][0] = 2;
        xKernel[1][1] = 0;
        xKernel[1][2] = -2;
        xKernel[2][0] = 1;
        xKernel[2][1] = 0;
        xKernel[2][2] = -1;
        yKernel[0][0] = 1;
        yKernel[0][1] = 2;
        yKernel[0][2] = 1;
        yKernel[1][0] = 0;
        yKernel[1][1] = 0;
        yKernel[1][2] = 0;
        yKernel[2][0] = -1;
        yKernel[2][1] = -2;
        yKernel[2][2] = -1;
    }

    public int Convolucion(int i, int j, int[][] matriz) {
        int total;
        total = Matriz[i - 1][j - 1] * matriz[0][0];
        total = total + Matriz[i][j - 1] * matriz[0][1];
        total = total + Matriz[i + 1][j - 1] * matriz[0][2];
        total = total + Matriz[i - 1][j] * matriz[1][0];
        total = total + Matriz[i][j] * matriz[1][1];
        total = total + Matriz[i + 1][j] * matriz[1][2];
        total = total + Matriz[i - 1][j + 1] * matriz[2][0];
        total = total + Matriz[i][j + 1] * matriz[2][1];
        total = total + Matriz[i + 1][j + 1] * matriz[2][2];
        return total;
    }

    public int OFiltroProm(int i, int j, int parametro) {//operaciones filtro promedio
        int resultado;
        resultado = (Matriz[i - 1][j - 1] * parametro) + (Matriz[i][j - 1] * parametro) + (Matriz[i + 1][j - 1] * parametro) + (Matriz[i - 1][j] * parametro) + (Matriz[i][j] * parametro) + (Matriz[i + 1][j] * parametro) + (Matriz[i - 1][j + 1] * parametro) + (Matriz[i][j + 1] * parametro) + (Matriz[i + 1][j + 1] * parametro);
        resultado = (resultado) / 9;
        return resultado;
    }

    public int OFiltroMediana(int i, int j) {//operaciones filtro mediana
        int mediana;
        int[] vector = new int[9];
        vector[0] = Matriz[i - 1][j - 1];
        vector[1] = Matriz[i][j - 1];
        vector[2] = Matriz[i + 1][j - 1];
        vector[3] = Matriz[i - 1][j];
        vector[4] = Matriz[i][j];
        vector[5] = Matriz[i + 1][j];
        vector[6] = Matriz[i - 1][j + 1];
        vector[7] = Matriz[i][j + 1];
        vector[8] = Matriz[i + 1][j + 1];
        Arrays.sort(vector);
        mediana = vector[4];
        return mediana;
    }

    public int OFiltroModa(int i, int j) {
        int moda = 0;
        int cantveces = 0;
        int[] vector = new int[255];
        vector[Matriz[i - 1][j - 1]]++;
        vector[Matriz[i][j - 1]]++;
        vector[Matriz[i + 1][j - 1]]++;
        vector[Matriz[i - 1][j]]++;
        vector[Matriz[i][j]]++;
        vector[Matriz[i + 1][j]]++;
        vector[Matriz[i - 1][j + 1]]++;
        vector[Matriz[i][j + 1]]++;
        vector[Matriz[i + 1][j + 1]]++;
        for (int a = 0; a < vector.length; a++) {
            if (vector[a] > cantveces) {
                cantveces = vector[a];
                moda = a;
            }
        }
        return moda;
    }

    public int OFiltroGauss(int i, int j, int Matriz[][]) {//operaciones filtro promedio
        int resultado;
        resultado = (Matriz[i - 1][j - 1] * 1) + (Matriz[i][j - 1] * 2) + (Matriz[i + 1][j - 1] * 1) + (Matriz[i - 1][j] * 2) + (Matriz[i][j] * 4) + (Matriz[i + 1][j] * 2) + (Matriz[i - 1][j + 1] * 1) + (Matriz[i][j + 1] * 2) + (Matriz[i + 1][j + 1] * 1);
        resultado = (resultado) / 16;
        return resultado;
    }

    public int OKirsch(int i, int j) {
        int mg1, mg2, mg3, mg4, mg5, mg6, mg7, mg8;
        mg1 = Convolucion(i, j, Mascara1K);
        mg2 = Convolucion(i, j, Mascara2K);
        mg3 = Convolucion(i, j, Mascara3K);
        mg4 = Convolucion(i, j, Mascara4K);
        mg5 = Convolucion(i, j, Mascara5K);
        mg6 = Convolucion(i, j, Mascara6K);
        mg7 = Convolucion(i, j, Mascara7K);
        mg8 = Convolucion(i, j, Mascara8K);
        int MagnitudGradiente = mg1;
        MagnitudGradiente = (MagnitudGradiente < mg2) ? mg2 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg3) ? mg3 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg4) ? mg4 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg5) ? mg5 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg6) ? mg6 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg7) ? mg7 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg8) ? mg8 : MagnitudGradiente;
        return MagnitudGradiente;
    }

    public int ORobinson(int i, int j) {
        int mg1, mg2, mg3, mg4, mg5, mg6, mg7, mg8;
        mg1 = Convolucion(i, j, Mascara1R);
        mg2 = Convolucion(i, j, Mascara2R);
        mg3 = Convolucion(i, j, Mascara3R);
        mg4 = Convolucion(i, j, Mascara4R);
        mg5 = Convolucion(i, j, Mascara5R);
        mg6 = Convolucion(i, j, Mascara6R);
        mg7 = Convolucion(i, j, Mascara7R);
        mg8 = Convolucion(i, j, Mascara8R);
        int MagnitudGradiente = mg1;
        MagnitudGradiente = (MagnitudGradiente < mg2) ? mg2 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg3) ? mg3 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg4) ? mg4 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg5) ? mg5 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg6) ? mg6 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg7) ? mg7 : MagnitudGradiente;
        MagnitudGradiente = (MagnitudGradiente < mg8) ? mg8 : MagnitudGradiente;
        return MagnitudGradiente;
    }

    public int ODilatacion(int i, int j, int[][] Matriz) {
        int maximo;
        maximo = Matriz[i - 1][j - 1];
        if (maximo < Matriz[i][j - 1]) {
            maximo = Matriz[i][j - 1];
        }
        if (maximo < Matriz[i + 1][j - 1]) {
            maximo = Matriz[i + 1][j - 1];
        }
        if (maximo < Matriz[i - 1][j]) {
            maximo = Matriz[i - 1][j];
        }
        if (maximo < Matriz[i][j]) {
            maximo = Matriz[i][j];
        }
        if (maximo < Matriz[i + 1][j]) {
            maximo = Matriz[i + 1][j];
        }
        if (maximo < Matriz[i - 1][j + 1]) {
            maximo = Matriz[i - 1][j];
        }
        if (maximo < Matriz[i][j + 1]) {
            maximo = Matriz[i][j];
        }
        if (maximo < Matriz[i + 1][j + 1]) {
            maximo = Matriz[i + 1][j];
        }
        return maximo;
    }

    public int OErosion(int i, int j, int[][] Matriz) {
        int minimo;
        minimo = Matriz[i - 1][j - 1];
        if (minimo > Matriz[i][j - 1]) {
            minimo = Matriz[i][j - 1];
        }
        if (minimo > Matriz[i + 1][j - 1]) {
            minimo = Matriz[i + 1][j - 1];
        }
        if (minimo > Matriz[i - 1][j]) {
            minimo = Matriz[i - 1][j];
        }
        if (minimo > Matriz[i][j]) {
            minimo = Matriz[i][j];
        }
        if (minimo > Matriz[i + 1][j]) {
            minimo = Matriz[i + 1][j];
        }
        if (minimo > Matriz[i - 1][j + 1]) {
            minimo = Matriz[i - 1][j];
        }
        if (minimo > Matriz[i][j + 1]) {
            minimo = Matriz[i][j];
        }
        if (minimo > Matriz[i + 1][j + 1]) {
            minimo = Matriz[i + 1][j];
        }
        return minimo;
    }

    public int Conectividad(int i, int j, int[][] Matriz) {//devuelve la conectividad
        int conectividad = 0;
        if (Matriz[i - 1][j - 1] == 255 && Matriz[i][j - 1] == 0) {
            conectividad++;
        }
        if (Matriz[i][j - 1] == 255 && Matriz[i + 1][j - 1] == 0) {
            conectividad++;
        }
        if (Matriz[i + 1][j - 1] == 255 && Matriz[i + 1][j] == 0) {
            conectividad++;
        }
        if (Matriz[i + 1][j] == 255 && Matriz[i + 1][j + 1] == 0) {
            conectividad++;
        }
        if (Matriz[i + 1][j + 1] == 255 && Matriz[i][j + 1] == 0) {
            conectividad++;
        }
        if (Matriz[i][j + 1] == 255 && Matriz[i - 1][j + 1] == 0) {
            conectividad++;
        }
        if (Matriz[i - 1][j + 1] == 255 && Matriz[i - 1][j] == 0) {
            conectividad++;
        }
        if (Matriz[i - 1][j] == 255 && Matriz[i - 1][j - 1] == 0) {
            conectividad++;
        }
        return conectividad;
    }

    public int PixelesNegros(int i, int j, int[][] Matriz) {//devuelve la conectividad
        int negros = 0;
        if (Matriz[i - 1][j - 1] == 0) {
            negros++;
        }
        if (Matriz[i][j - 1] == 0) {
            negros++;
        }
        if (Matriz[i + 1][j - 1] == 0) {
            negros++;
        }
        if (Matriz[i + 1][j] == 0) {
            negros++;
        }
        if (Matriz[i + 1][j + 1] == 0) {
            negros++;
        }
        if (Matriz[i][j + 1] == 0) {
            negros++;
        }
        if (Matriz[i - 1][j + 1] == 0) {
            negros++;
        }
        if (Matriz[i - 1][j] == 0) {
            negros++;
        }
        return negros;
    }

    public void ZhangSuenP1(int i, int j, int[][] Matriz) {
        int conectividad = Conectividad(i, j, Matriz);
        if (conectividad == 1) {
            int pixelesNegros = PixelesNegros(i, j, Matriz);
            if (pixelesNegros >= 2 && pixelesNegros < 7) {
                if (Matriz[i][j - 1] == 255 || Matriz[i + 1][j] == 255 || Matriz[i][j + 1] == 255) {
                    if (Matriz[i + 1][j] == 255 || Matriz[i][j + 1] == 255 || Matriz[i - 1][j] == 255) {
                        MatrizBool[i][j] = true;
                    }
                }
            }
        }
    }

    public void ZhangSuenP2(int i, int j, int[][] Matriz) {
        int conectividad = Conectividad(i, j, Matriz);
        if (conectividad == 1) {
            int pixelesNegros = PixelesNegros(i, j, Matriz);
            if (pixelesNegros >= 2 && pixelesNegros < 7) {
                if (Matriz[i - 1][j] == 255 || Matriz[i][j - 1] == 255 || Matriz[i + 1][j] == 255) {
                    if (Matriz[i - 1][j] == 255 || Matriz[i][j - 1] == 255 || Matriz[i][j + 1] == 255) {
                        MatrizBool[i][j] = true;
                    }
                }
            }
        }
    }

    public int CalcularMedia() throws IOException {
        BufferedImage bfImagen = Imagen;
        int media = 0;
        int h = 0;
        for (int i = 0; i < bfImagen.getWidth(); i++) {
            for (int j = 0; j < bfImagen.getHeight(); j++) {
                Color c = new Color(bfImagen.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                media = media + TonoGris;
                h++;
            }
        }
        media = media / h;
        //JOptionPane.showMessageDialog(null, "Média: " + media);
        return media;
    }

    public int CalcularMediana() throws IOException {
        BufferedImage imagen = Imagen;
        int[][] Matriz = new int[imagen.getWidth()][imagen.getHeight()];
        int[] VectorGrises = new int[(imagen.getWidth() * imagen.getHeight())];
        int h = 0;
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                Color c = new Color(imagen.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                Matriz[i][j] = TonoGris;
                VectorGrises[h] = Matriz[i][j];
                h++;
            }
        }
        int mediana = 0;
        Arrays.sort(VectorGrises);
        mediana = VectorGrises[h / 2];
        //JOptionPane.showMessageDialog(null, "Mediana: " + mediana);
        return mediana;
    }

    public int CalcularModa() throws IOException {
        int[] vector = new int[256];
        BufferedImage imagen = Imagen;
        int w = imagen.getWidth();
        int h = imagen.getHeight();
        Matriz = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color c = new Color(imagen.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                Matriz[i][j] = TonoGris;
                vector[TonoGris]++;
            }
        }
        int moda = 0;
        int cantveces = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] > cantveces) {
                cantveces = vector[i];
                moda = i;
            }
        }
        //JOptionPane.showMessageDialog(null, "Moda: " + moda);
        return moda;
    }

    public int CalcularVarianza(int media) throws IOException {
        int resta = 0;
        int pixel = 0;
        int mediaPixel = 0;
        int totalMatriz = 0;
        int varianza = 0;
        BufferedImage imagen = Imagen;
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        Matriz = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagen.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                c = new Color(TonoGris, TonoGris, TonoGris);
                Matriz[i][j] = TonoGris;
                //calcula la sumatoria
                resta = Matriz[i][j] - media;
                totalMatriz = totalMatriz + (int) Math.pow(resta, 2);
            }
        }
        varianza = totalMatriz / (ancho * alto);
        //JOptionPane.showMessageDialog(null, "Varianza: " + varianza);
        return varianza;
    }

    public void AreaCuadrado() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        int area = 0;
        Matriz = CalcularMatriz(Imagen);
        for (int j = 0; j < alto; j++) {
            for (int i = 0; i < ancho; i++) {
                if (Matriz[i][j] == 0) {
                    area++;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Área: " + area);
    }

    public void PerimetroCuadrado() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        int perimetro = 0;
        boolean cond = false;
        Matriz = CalcularMatriz(Imagen);
        for (int j = 0; j < alto; j++) {
            for (int i = 0; i < ancho; i++) {
                if (Matriz[i][j] == 0) {
                    cond = true;
                    perimetro++;
                }
            }
            if (cond == true) {
                j = alto;
            }
        }
        perimetro = perimetro * 4;
        JOptionPane.showMessageDialog(null, "Perimetro: " + perimetro);
    }

    public void EncontrarFiguras() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        int conta = 1;
        MatrizBool = new boolean[ancho][alto];
        MRotulacion = new int[ancho][alto];
        Matriz = CalcularMatriz(Imagen);
        for (int j = 1; j < alto - 1; j++) {
            for (int i = 1; i < ancho - 1; i++) {
                if (Matriz[i][j] == 255 && MatrizBool[i][j] == false) {
                    RotularRec(i, j, conta);
                    conta++;
                }
            }
        }
        ArregloObjetos = new int[conta];
        int nobjeto;
        for (int j = 0; j < alto; j++) {
            for (int i = 0; i < ancho; i++) {
                nobjeto = MRotulacion[i][j] - 1;
                if (nobjeto >= 0) {
                    ArregloObjetos[nobjeto]++;
                }
            }
        }
        /*System.out.println(conta);
        System.out.println("");
        for (int j = 1; j < alto - 1; j++) {
            for (int i = 1; i < ancho - 1; i++) {
                System.out.print(MRotulacion[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");*/
    }

    public void RotularRec(int i, int j, int conta) {
        MatrizBool[i][j] = true;
        MRotulacion[i][j] = conta;
        if (i - 1 > 0 && Matriz[i - 1][j] == 255 && MatrizBool[i - 1][j] == false) {
            RotularRec(i - 1, j, conta);
        }
        if (i + 1 < Imagen.getWidth() && Matriz[i + 1][j] == 255 && MatrizBool[i + 1][j] == false) {
            RotularRec(i + 1, j, conta);
        }
        if (j - 1 > 0 && Matriz[i][j - 1] == 255 && MatrizBool[i][j - 1] == false) {
            RotularRec(i, j - 1, conta);
        }
        if (j + 1 < Imagen.getHeight() && Matriz[i][j + 1] == 255 && MatrizBool[i][j + 1] == false) {
            RotularRec(i, j + 1, conta);
        }
    }

    public void AreaCirculo() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        EncontrarFiguras();
        int areac1 = 0;
        int areac2 = 0;
        int areac3 = 0;
        for (int j = 0; j < alto; j++) {
            for (int i = 0; i < ancho; i++) {
                if (MRotulacion[i][j] == 1) {
                    areac1++;
                }
                if (MRotulacion[i][j] == 2) {
                    areac2++;
                }
                if (MRotulacion[i][j] == 3) {
                    areac3++;
                }
            }
        }
        if (areac1 > areac2 && areac1 > areac3) {
            JOptionPane.showMessageDialog(null, "Área: " + areac1);
        }
        if (areac2 > areac1 && areac2 > areac3) {
            JOptionPane.showMessageDialog(null, "Área: " + areac2);
        }
        if (areac3 > areac1 && areac3 > areac2) {
            JOptionPane.showMessageDialog(null, "Área: " + areac3);
        }
    }

    public void PerimetroCirculo() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        MatrizBool = new boolean[ancho][alto];
        MRotulacion = new int[ancho][alto];
        EncontrarFiguras();
        double radio;
        double perimetro;
        int areac = 0;
        int areac1 = 0;
        int areac2 = 0;
        int areac3 = 0;
        for (int j = 0; j < alto; j++) {
            for (int i = 0; i < ancho; i++) {
                if (MRotulacion[i][j] == 1) {
                    areac1++;
                }
                if (MRotulacion[i][j] == 2) {
                    areac2++;
                }
                if (MRotulacion[i][j] == 3) {
                    areac3++;
                }
            }
        }
        if (areac1 > areac2 && areac1 > areac3) {
            areac = areac1;
        }
        if (areac2 > areac1 && areac2 > areac3) {
            areac = areac2;
        }
        if (areac3 > areac1 && areac3 > areac2) {
            areac = areac3;
        }
        radio = areac / Math.PI; // sabiendo que area= pi * (r*r)
        radio = Math.sqrt(radio);
        perimetro = 2 * Math.PI * radio;
        JOptionPane.showMessageDialog(null, "Perimetro círculo Pequeño: " + perimetro);
    }

    public int[][] CalcularMatriz(BufferedImage Imagen) throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        int[][] Matriz = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(Imagen.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                c = new Color(TonoGris, TonoGris, TonoGris);
                Matriz[i][j] = TonoGris;
            }
        }
        return Matriz;
    }

    public BufferedImage ImagenGris() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), Imagen.getType());
        Matriz = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(Imagen.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
                Matriz[i][j] = TonoGris;
            }
        }
        return imagenF;
    }

    public void MatricesColores(BufferedImage Imagen) {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        MatrizR = new int[ancho][alto];
        MatrizG = new int[ancho][alto];
        MatrizB = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(Imagen.getRGB(i, j));
                MatrizR[i][j] = c.getRed();
                MatrizG[i][j] = c.getGreen();
                MatrizB[i][j] = c.getBlue();
            }
        }
    }

    public BufferedImage recibeMedia(int media) throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                if (TonoGris >= 150) {
                    c = new Color(media, media, media);
                } else {
                    c = new Color(TonoGris, TonoGris, TonoGris);
                }
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage recibeMediana(int mediana) throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                if (TonoGris >= 150) {
                    c = new Color(mediana, mediana, mediana);
                } else {
                    c = new Color(TonoGris, TonoGris, TonoGris);
                }
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage recibeModa(int moda) throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                if (TonoGris >= 150) {
                    c = new Color(moda, moda, moda);
                } else {
                    c = new Color(TonoGris, TonoGris, TonoGris);
                }
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage recibeMediaBlanco(int media) throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), imagenO.getType());
        Matriz = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                if (TonoGris > media) {
                    c = new Color(255, 255, 255);
                } else {
                    c = new Color(TonoGris, TonoGris, TonoGris);
                }
                imagenF.setRGB(i, j, c.getRGB());
                Matriz[i][j] = TonoGris;
            }
        }
        return imagenF;
    }

    public BufferedImage recibeMedianayMedia(int mediana, int media) throws IOException {
        BufferedImage imagenO = Imagen;
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                if (TonoGris > media) {
                    c = new Color(255, 255, 255);
                } else if (TonoGris < mediana) {
                    c = new Color(0, 0, 0);
                } else {
                    c = new Color(TonoGris, TonoGris, TonoGris);
                }
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Reflejoejex() throws IOException {
        MatrizIdentidad(MatrizTransformacion);
        MatrizTransformacion[0][0] = -1;
        BufferedImage imagenO = Imagen;
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), imagenO.getType());
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = MultPColumna(MatrizTransformacion, i, j, 1) + (ancho - 1);
                imagenF.setRGB(ni, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Reflejoejey() throws IOException {
        MatrizIdentidad(MatrizTransformacion);
        MatrizTransformacion[1][1] = -1;
        BufferedImage imagenO = Imagen;
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), imagenO.getType());
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int nj = MultSColumna(MatrizTransformacion, i, j, 1) + (alto - 1);
                imagenF.setRGB(i, nj, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Traslacion(int x, int y) throws IOException {
        MatrizIdentidad(MatrizTransformacion);
        BufferedImage imagenO = Imagen;
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = i + y;
                int nj = j + x;
                if (ni < ancho && nj < alto && ni >= 0 && nj >= 0) {
                    imagenF.setRGB(ni, nj, c.getRGB());
                }
            }
        }
        return imagenF;
    }

    public BufferedImage Ampliar(int ampliacion) throws IOException {
        MatrizIdentidad(MatrizTransformacion);
        MatrizTransformacion[0][0] = ampliacion;
        MatrizTransformacion[1][1] = ampliacion;
        //BufferedImage imagenO = ImageIO.read(file);
        BufferedImage imagenO = Imagen;
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(ancho * ampliacion, alto * ampliacion, imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = i * ampliacion;
                int nj = j * ampliacion;
                imagenF.setRGB(ni, nj, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Reducir(double reduccion) throws IOException {
        BufferedImage imagenO = Imagen;
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        int nalto = alto / (int) reduccion;
        int nancho = ancho / (int) reduccion;
        BufferedImage imagenF = new BufferedImage(nancho, nalto, imagenO.getType());
        reduccion = 1 / reduccion;
        MatrizIdentidadD(MatrizTransformacionD);
        MatrizTransformacionD[0][0] = reduccion;
        MatrizTransformacionD[1][1] = reduccion;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = (int) (i * reduccion);
                int nj = (int) (j * reduccion);
                if (ni < nancho && nj < nalto && ni >= 0 && nj >= 0) {
                    imagenF.setRGB(ni, nj, c.getRGB());
                }
            }
        }
        return imagenF;
    }

    public BufferedImage Rotar(double angulo) throws IOException {
        MatrizIdentidadD(MatrizTransformacionD);
        BufferedImage imagenO = Imagen;
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        double radianes = (angulo * Math.PI) / 180;
        int centrox = imagenO.getHeight() / 2;
        int centroy = imagenO.getWidth() / 2;
        double nejex = centrox - centrox * Math.cos(radianes) - centroy * Math.sin(radianes);
        double nejey = centroy + centrox * Math.sin(radianes) - centroy * Math.cos(radianes);
        MatrizTransformacionD[0][0] = Math.cos(radianes);
        MatrizTransformacionD[0][1] = -Math.sin(radianes);
        MatrizTransformacionD[1][1] = Math.cos(radianes);
        MatrizTransformacionD[1][0] = Math.sin(radianes);
        MatrizTransformacionD[2][0] = nejex;
        MatrizTransformacionD[2][1] = nejey;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(0, 0, 0);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = (int) (DMultPColumna(MatrizTransformacionD, i, j, 1));
                int nj = (int) (DMultSColumna(MatrizTransformacionD, i, j, 1));
                if (ni < ancho && nj < alto && ni >= 0 && nj >= 0) {
                    imagenF.setRGB(ni, nj, c.getRGB());
                }
            }
        }
        return imagenF;
    }

    public BufferedImage Libre(double pos00, double pos01, double pos02, double pos10, double pos11, double pos12, double pos20, double pos21, double pos22) throws IOException {
        BufferedImage imagenO = Imagen;
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        MatrizTransformacionD[0][0] = pos00;
        MatrizTransformacionD[0][1] = pos01;
        MatrizTransformacionD[0][2] = pos02;
        MatrizTransformacionD[1][0] = pos10;
        MatrizTransformacionD[1][1] = pos11;
        MatrizTransformacionD[1][2] = pos12;
        MatrizTransformacionD[2][0] = pos20;
        MatrizTransformacionD[2][1] = pos21;
        MatrizTransformacionD[2][2] = pos22;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = (int) (DMultPColumna(MatrizTransformacionD, i, j, 1));
                int nj = (int) (DMultSColumna(MatrizTransformacionD, i, j, 1));
                if (ni < ancho && nj < alto && ni >= 0 && nj >= 0) {
                    imagenF.setRGB(ni, nj, c.getRGB());
                }
            }
        }
        return imagenF;
    }

    public BufferedImage PromedioSimple() throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage PromedioP1() throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int TonoGris = (int) (c.getRed() * 0.2125 + c.getGreen() * 0.7154 + 0.0721 * c.getBlue());
                TonoGris = (TonoGris >= 255) ? 255 : TonoGris;
                c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage PromedioP2() throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int TonoGris = (int) (c.getRed() * 0.5 + c.getGreen() * 0.419 + 0.081 * c.getBlue());
                TonoGris = (TonoGris >= 255) ? 255 : TonoGris;
                c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Brillo(float brillo) throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int rojo = (int) (c.getRed() + brillo);
                int verde = (int) (c.getGreen() + brillo);
                int azul = (int) (c.getBlue() + brillo);
                rojo = (rojo > 255) ? 255 : rojo;
                rojo = (rojo < 0) ? 0 : rojo;
                verde = (verde > 255) ? 255 : verde;
                verde = (verde < 0) ? 0 : verde;
                azul = (azul > 255) ? 255 : azul;
                azul = (azul < 0) ? 0 : azul;
                c = new Color(rojo, verde, azul);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Contraste(float contraste) throws IOException {
        BufferedImage imagenO = Imagen;
        int ancho = imagenO.getWidth();
        int alto = imagenO.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagenO.getRGB(i, j));
                int rojo = (int) (c.getRed() * contraste);
                int verde = (int) (c.getGreen() * contraste);
                int azul = (int) (c.getBlue() * contraste);
                rojo = (rojo > 255) ? 255 : rojo;
                rojo = (rojo < 0) ? 0 : rojo;
                verde = (verde > 255) ? 255 : verde;
                verde = (verde < 0) ? 0 : verde;
                azul = (azul > 255) ? 255 : azul;
                azul = (azul < 0) ? 0 : azul;
                c = new Color(rojo, verde, azul);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage FiltroPromedio() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int TonoGris = OFiltroProm(i, j, 1);
                TonoGris = (TonoGris > 255) ? 255 : TonoGris;
                Color c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage FiltroMediana() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int TonoGris = OFiltroMediana(i, j);
                Color c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage FiltroModa() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int TonoGris = OFiltroModa(i, j);
                TonoGris = (TonoGris > 255) ? 255 : TonoGris;
                Color c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage FiltroGauss() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        MatricesColores(Imagen);
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int rojo = OFiltroGauss(i, j, MatrizR);
                int verde = OFiltroGauss(i, j, MatrizG);
                int azul = OFiltroGauss(i, j, MatrizB);
                rojo = (rojo > 255) ? 255 : rojo;
                rojo = (rojo < 0) ? 0 : rojo;
                verde = (verde > 255) ? 255 : verde;
                verde = (verde < 0) ? 0 : verde;
                azul = (azul > 255) ? 255 : azul;
                azul = (azul < 0) ? 0 : azul;
                Color c = new Color(rojo, verde, azul);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Limitarizacion(int umbral) throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        Matriz = CalcularMatriz(imagenF);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int TonoGris = Matriz[i][j];
                if (TonoGris > umbral) {
                    TonoGris = 255;
                } else {
                    TonoGris = 0;
                }
                Color c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Roberts(int umbral) throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        Matriz = CalcularMatriz(imagenF);
        MatricesRoberts();
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int Gradientex = Convolucion(i, j, xKernel);
                int Gradientey = Convolucion(i, j, yKernel);
                int Gradiente = (int) Math.sqrt(Math.pow(Gradientex, 2) + Math.pow(Gradientey, 2));
                int pixel = 0;
                if (Gradiente > umbral) {
                    pixel = 255;
                }
                Color c = new Color(pixel, pixel, pixel);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Sobel(int umbral) throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        Matriz = CalcularMatriz(imagenF);
        MatricesSobel();
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int Gradientex = Convolucion(i, j, xKernel);
                int Gradientey = Convolucion(i, j, yKernel);
                int Gradiente = (int) Math.sqrt(Math.pow(Gradientex, 2) + Math.pow(Gradientey, 2));
                int pixel = 0;
                if (Gradiente > umbral) {
                    pixel = 255;
                }
                Color c = new Color(pixel, pixel, pixel);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Kirsch(int umbral) throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        Matriz = CalcularMatriz(imagenF);
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int Gradiente = OKirsch(i, j);
                int pixel = 0;
                if (Gradiente > umbral) {
                    pixel = 255;
                }
                Color c = new Color(pixel, pixel, pixel);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Robinson(int umbral) throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        Matriz = CalcularMatriz(imagenF);
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int Gradiente = ORobinson(i, j);
                int pixel = 0;
                if (Gradiente > umbral) {
                    pixel = 255;
                }
                Color c = new Color(pixel, pixel, pixel);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Dilatacion() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        Matriz = CalcularMatriz(imagenF);
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int pixel = ODilatacion(i, j, Matriz);
                Color c = new Color(pixel, pixel, pixel);
                imagenF.setRGB(i - 1, j - 1, c.getRGB());
                imagenF.setRGB(i, j - 1, c.getRGB());
                imagenF.setRGB(i + 1, j - 1, c.getRGB());
                imagenF.setRGB(i - 1, j, c.getRGB());
                imagenF.setRGB(i, j, c.getRGB());
                imagenF.setRGB(i + 1, j, c.getRGB());
                imagenF.setRGB(i - 1, j + 1, c.getRGB());
                imagenF.setRGB(i, j + 1, c.getRGB());
                imagenF.setRGB(i + 1, j + 1, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Erosion() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        Matriz = CalcularMatriz(imagenF);
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                int pixel = OErosion(i, j, Matriz);
                Color c = new Color(pixel, pixel, pixel);
                imagenF.setRGB(i - 1, j - 1, c.getRGB());
                imagenF.setRGB(i, j - 1, c.getRGB());
                imagenF.setRGB(i + 1, j - 1, c.getRGB());
                imagenF.setRGB(i - 1, j, c.getRGB());
                imagenF.setRGB(i, j, c.getRGB());
                imagenF.setRGB(i + 1, j, c.getRGB());
                imagenF.setRGB(i - 1, j + 1, c.getRGB());
                imagenF.setRGB(i, j + 1, c.getRGB());
                imagenF.setRGB(i + 1, j + 1, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Esqueletizacion() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        MatrizBool = new boolean[ancho][alto];
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = ImagenGris();
        Color c = new Color(255, 255, 255);
        Matriz = CalcularMatriz(imagenF);
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                ZhangSuenP1(i, j, Matriz);
            }
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                if (MatrizBool[i][j] == true) {
                    imagenF.setRGB(i, j, c.getRGB());
                    MatrizBool[i][j] = false;
                }
            }
        }
        //segunda condicion
        Matriz = CalcularMatriz(imagenF);
        for (int i = 1; i < ancho - 1; i++) {
            for (int j = 1; j < alto - 1; j++) {
                ZhangSuenP2(i, j, Matriz);
            }
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                if (MatrizBool[i][j] == true) {
                    imagenF.setRGB(i, j, c.getRGB());
                }
            }
        }
        return imagenF;
    }

    public void mostraHistograma() throws IOException {
        BufferedImage imagen = Imagen;
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        Matriz = new int[Imagen.getWidth()][Imagen.getHeight()];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(imagen.getRGB(i, j));
                int TonoGris = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                c = new Color(TonoGris, TonoGris, TonoGris);
                imagenF.setRGB(i, j, c.getRGB());
                Matriz[i][j] = TonoGris;
            }
        }
        Histograma hist = new Histograma();
        hist.GenerarHistograma(imagenF, Matriz);
    }

    public BufferedImage Placas() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = RGBtoYIQ(Imagen);
        MatricesColores(imagenF);
        Color cb = new Color(255, 255, 255);
        Color cn = new Color(0, 0, 0);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                if (MatrizR[i][j] <= 170 && MatrizR[i][j] >= 110 && MatrizG[i][j] <= 105 && MatrizG[i][j] >= 45 && MatrizB[i][j] <= 40 && MatrizB[i][j] >= 0) {
                    imagenF.setRGB(i, j, cb.getRGB());
                } else {
                    imagenF.setRGB(i, j, cn.getRGB());
                }
            }
        }
        return imagenF;
    }

    public BufferedImage RGBtoYIQ(BufferedImage Imagen) throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        MatricesColores(Imagen);
        Color c;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int rojo = MatrizR[i][j];
                int verde = MatrizG[i][j];
                int azul = MatrizB[i][j];
                int Y = (int) (0.299 * rojo + 0.587 * verde + 0.114 * azul);
                int I = (int) (0.596 * rojo - 0.274 * verde - 0.322 * azul);
                int Q = (int) (0.211 * rojo - 0.523 * verde + 0.312 * azul);
                Y = (Y > 255) ? 255 : Y;
                Y = (Y < 0) ? 0 : Y;
                I = (I > 255) ? 255 : I;
                I = (I < 0) ? 0 : I;
                Q = (Q > 255) ? 255 : Q;
                Q = (Q < 0) ? 0 : Q;
                c = new Color(Y, I, Q);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }

    public BufferedImage Pintar() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        EncontrarFiguras();
        int objetoM = 0;
        for (int cvec = 0; cvec < ArregloObjetos.length; cvec++) {// cvec:cantidad de veces
            if (ArregloObjetos[cvec] > objetoM) {
                objetoM = cvec;
            }
        }
        Color c;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                if (MRotulacion[i][j] == objetoM + 1) {
                    c = new Color(255, 255, 255);
                    imagenF.setRGB(i, j, c.getRGB());
                }
            }
        }
        return imagenF;
    }

    public void Rectangulo(BufferedImage Imagen) throws IOException {
        menori = Imagen.getWidth();
        mayori = 0;
        menorj = Imagen.getHeight();
        mayorj = 0;
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), Imagen.getType());
        Matriz = CalcularMatriz(Imagen);
        for (int i = 0; i < Imagen.getWidth(); i++) {
            for (int j = 0; j < Imagen.getHeight(); j++) {
                if (Matriz[i][j] == 255) {
                    if (i < menori) {
                        menori = i;
                    }
                    if (j < menorj) {
                        menorj = j;
                    }
                    if (i > mayori) {
                        mayori = i;
                    }
                    if (j > mayorj) {
                        mayorj = j;
                    }
                }
            }
        }
    }

    public BufferedImage Recortar(BufferedImage Imagen) throws IOException {
        /*menori-=10;
        menorj-=10;
        mayori+=15;
        mayorj+=15;*/
        menori-=Imagen.getWidth()*0.03;
        menorj-=Imagen.getHeight()*0.03;
        mayori+=Imagen.getWidth()*0.06;
        mayorj+=Imagen.getHeight()*0.06;
        if(menori<0){
            menori=0;
        }
        if(menorj<0){
            menorj=0;
        }
        if(mayori>=Imagen.getWidth()){
            mayori=Imagen.getWidth()-1;
        }
        if(mayorj>=Imagen.getHeight()){
            mayorj=Imagen.getHeight()-1;
        }
        int alto = (mayorj - menorj);
        int ancho = (mayori - menori);
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        for (int i = menori; i < mayori; i++) {
            for (int j = menorj; j < mayorj; j++) {
                Color c = new Color(Imagen.getRGB(i, j));
                imagenF.setRGB(i-menori, j-menorj, c.getRGB());
            }
        }
        return imagenF;
    }
    
    public BufferedImage PrepararPlacas() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenP = new BufferedImage(ancho, alto, Imagen.getType());
        BufferedImage imagenF = new BufferedImage(ancho, alto, Imagen.getType());
        imagenF = Ampliar(2);
        Imagen=imagenF;
        imagenF=FiltroGauss();
        /*int media=CalcularMedia()+10;
        Imagen=imagenF;
        imagenF=Limitarizacion(media);*/
        Imagen=imagenF;
        imagenF=Negativo();
        return imagenF;
    }
    
    public BufferedImage Negativo() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), Imagen.getType());
        Color cn = new Color(0,0,0);
        Color cb = new Color(255,255,255);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Color c = new Color(Imagen.getRGB(i, j));
                int rojo = c.getRed();
                int verde = c.getGreen();
                int azul = c.getBlue();
                int nrojo = 255-rojo;
                int nverde = 255-verde;
                int nazul = 255-azul;
                c = new Color(nrojo, nverde, nazul);
                imagenF.setRGB(i, j, c.getRGB());
            }
        }
        return imagenF;
    }
    
    public BufferedImage RotularYPintar() throws IOException {
        int ancho = Imagen.getWidth();
        int alto = Imagen.getHeight();
        EncontrarFiguras();
        BufferedImage imagenF = new BufferedImage(Imagen.getWidth(), Imagen.getHeight(), Imagen.getType());
        Color[] colores = new Color[ArregloObjetos.length];
        Random rn = new Random();
        for(int h=0;h<colores.length;h++){
            colores[h] = new Color(rn.nextInt(255),rn.nextInt(255),rn.nextInt(255));
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int numeroObj = MRotulacion[i][j]-1;
                if(numeroObj>=0){
                    imagenF.setRGB(i, j, colores[numeroObj].getRGB());
                }
                
            }
        }
        return imagenF;
    }
}
