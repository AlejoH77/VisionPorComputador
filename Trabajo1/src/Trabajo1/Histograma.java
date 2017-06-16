package Trabajo1;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

public class Histograma {
    
    public Histograma() {

    }
    public static void GenerarHistograma(BufferedImage imagen, int[][] matriz) {
            HistogramDataset datoshistograma = new HistogramDataset();
            double[] vector = new double[imagen.getWidth()*imagen.getHeight()];
            int cont = 0;
            for (int i = 0; i < imagen.getWidth(); i++) {
                for(int j=0; j < imagen.getHeight(); j++) {
                    vector[cont] = matriz[i][j];
                    cont++;
                }
            }

            // Interfaz del histograma
            datoshistograma.addSeries("Quantidade de pixel", vector, 256, 0, 256);

            /* crea el histograma */
            JFreeChart histo = ChartFactory.createHistogram(
            "Histograma de Imagen",
            "Tonalidades Negro y Blanco",
            "Cantidad de Pixeles",
            datoshistograma,
            PlotOrientation.VERTICAL,
            false,
            true,
            true);

            /* Saca la venatana con el histograma */
            ChartPanel chartpanel = new ChartPanel(histo);
            chartpanel.setBounds(5,5,800,600);
            JDialog painel = new JDialog();
            painel.setResizable(false);
            painel.setBounds(115,55,816,638);
            painel.setTitle("Visión por Computador");
            painel.setLayout(null);
            painel.setVisible(true);
            painel.add(chartpanel);
    }
}
