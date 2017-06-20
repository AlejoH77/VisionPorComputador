package Trabajo1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
    public File file = null;
    public int[][] Matriz = null;
    public int[][] MatrizTransformacion = new int[3][3];
    public double[][] MatrizTransformacionD = new double[3][3];
    public int[][] Filtro = null;
    public int[][] xKernel = null;
    public int[][] yKernel = null;
    public int a = 0;
    
    public Metodos(File f){//constructor
        file=f;     
    }
    
    public void MatrizIdentidad(int[][] matriz){
        MatrizTransformacion[0][0]=1;
        MatrizTransformacion[0][1]=0;
        MatrizTransformacion[0][2]=0;
        MatrizTransformacion[1][0]=0;
        MatrizTransformacion[1][1]=1;
        MatrizTransformacion[1][2]=0;
        MatrizTransformacion[2][0]=0;
        MatrizTransformacion[2][1]=0;
        MatrizTransformacion[2][2]=1;
    }
    
    public void MatrizIdentidadD(double[][] matriz){
        MatrizTransformacionD[0][0]=1;
        MatrizTransformacionD[0][1]=0;
        MatrizTransformacionD[0][2]=0;
        MatrizTransformacionD[1][0]=0;
        MatrizTransformacionD[1][1]=1;
        MatrizTransformacionD[1][2]=0;
        MatrizTransformacionD[2][0]=0;
        MatrizTransformacionD[2][1]=0;
        MatrizTransformacionD[2][2]=1;
    }
    
    public int MultPColumna(int[][] matriz, int valor1, int valor2, int valor3){
        int total=0;
        total=total+matriz[0][0]*valor1;
        total=total+matriz[1][0]*valor2;
        total=total+matriz[2][0]*valor3;
        return total;
    }
    
    public int MultSColumna(int[][] matriz, int valor1, int valor2, int valor3){
        int total=0;
        total=total+matriz[0][1]*valor1;
        total=total+matriz[1][1]*valor2;
        total=total+matriz[2][1]*valor3;
        return total;
    }
    
    public double DMultPColumna(double[][] matriz, int valor1, int valor2, int valor3){
        double total=0;
        total=total+matriz[0][0]*valor1;
        total=total+matriz[1][0]*valor2;
        total=total+matriz[2][0]*valor3;
        return total;
    }
    
    public double DMultSColumna(double[][] matriz, int valor1, int valor2, int valor3){
        double total=0;
        total=total+matriz[0][1]*valor1;
        total=total+matriz[1][1]*valor2;
        total=total+matriz[2][1]*valor3;
        return total;
    }
    
    public BufferedImage ImgTonoGris() throws IOException {		
        BufferedImage imagen = ImageIO.read(file);
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        Matriz=new int[ancho][alto];
        for(int i=0; i<ancho;i++){
                for(int j=0; j<alto; j++){
                        Color c = new Color(imagen.getRGB(i, j));
                        int TonoGris =(c.getRed()+c.getGreen()+c.getBlue())/3;
                        c = new Color(TonoGris,TonoGris,TonoGris);
                        imagen.setRGB(i, j, c.getRGB());
                        Matriz[i][j]= TonoGris;
                }
        }
        return imagen;   
    }
    
    public int CalcularMedia() throws IOException{	
        BufferedImage bfImagen = ImageIO.read(file);
        int media=0;
        int h=0;
        for(int i=0; i<bfImagen.getWidth();i++){
            for(int j=0; j<bfImagen.getHeight(); j++){
                Color c = new Color(bfImagen.getRGB(i, j));
                int TonoGris = (c.getRed()+c.getGreen()+c.getBlue())/3;
                media = media+TonoGris;
                h++;
            }
        }
        media=media/h;
        JOptionPane.showMessageDialog(null, "Média: "+media);
        return media;
    }
    
    public int CalcularMediana()throws IOException{	
            BufferedImage imagen = ImageIO.read(file);
            int[][] Matriz = new int[imagen.getWidth()][imagen.getHeight()];
            int[] VectorGrises = new int[(imagen.getWidth()*imagen.getHeight())];
            int h=0;
            for(int i=0;i<imagen.getWidth();i++){
                    for(int j=0;j<imagen.getHeight();j++){
                            Color c = new Color(imagen.getRGB(i, j));
                            int TonoGris =(c.getRed()+c.getGreen()+c.getBlue())/3;
                            Matriz[i][j]=TonoGris;
                            VectorGrises[h]=Matriz[i][j];
                            h++;
                    }
            }
            int mediana=0;
            Arrays.sort(VectorGrises);
            mediana=VectorGrises[h/2];
            JOptionPane.showMessageDialog(null, "Mediana: "+mediana);
            return mediana;
    }
    
    public int CalcularModa()throws IOException{
            int[] vector = new int[256];
            BufferedImage imagen = ImageIO.read(file);
            int w = imagen.getWidth();
            int h = imagen.getHeight();
            Matriz=new int[w][h];
            for(int i=0; i<w;i++){
                for(int j=0; j<h; j++){
                    Color c=new Color(imagen.getRGB(i, j));
                    int TonoGris=(c.getRed()+c.getGreen()+c.getBlue())/3;
                    Matriz[i][j]=TonoGris;
                    vector[TonoGris]++;
                }
            }
            int moda=0;
            int cantveces=0;
            for(int i=0;i<vector.length;i++){
                if(vector[i]>cantveces){
                    cantveces=vector[i];
                    moda=i;
                }
            }
            JOptionPane.showMessageDialog(null, "Moda: "+moda);   
            return moda;
    }
    
    public int CalcularVarianza(int media)throws IOException{
        int resta=0;
        int pixel=0;
        int mediaPixel=0;
        int totalMatriz=0;
        int varianza=0;
        BufferedImage imagen = ImageIO.read(file);
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        Matriz=new int[ancho][alto];
        for(int i=0; i<ancho;i++){
            for(int j=0; j<alto; j++){
                Color c = new Color(imagen.getRGB(i, j));
                int TonoGris = (c.getRed()+c.getGreen()+c.getBlue())/3;
                c = new Color(TonoGris,TonoGris,TonoGris);
                imagen.setRGB(i, j, c.getRGB()); 
                Matriz[i][j]=TonoGris;
                //calcula la sumatoria
                resta=Matriz[i][j]-media;
                totalMatriz=totalMatriz+(int)Math.pow(resta,2);
            }
        }
        varianza = totalMatriz/(ancho*alto);
        JOptionPane.showMessageDialog(null, "Varianza: "+varianza);
        return varianza;
    }
    
    public BufferedImage recibeMedia(int media) throws IOException {	
        BufferedImage imagem = ImageIO.read(file);
        int ancho = imagem.getWidth();
        int alto = imagem.getHeight();
        Matriz = new int[ancho][alto];
        for(int i=0; i<ancho;i++){
                for(int j=0; j<alto; j++){
                    Color c = new Color(imagem.getRGB(i, j));
                    int TonoGris=(c.getRed()+c.getGreen()+c.getBlue())/3;
                    if(TonoGris>=150){
                            c=new Color (media, media, media);
                    }else{
                            c= new Color(TonoGris,TonoGris,TonoGris);
                    }
                    imagem.setRGB(i, j, c.getRGB());
                    Matriz[i][j]=TonoGris;
                }
        }
        return imagem;
    }
    
    public BufferedImage recibeMediana(int mediana) throws IOException{
        BufferedImage imagen = ImageIO.read(file);
        int w = imagen.getWidth();
        int h = imagen.getHeight();
        Matriz=new int[w][h];
        for(int i=0; i<w;i++){
            for(int j=0; j<h; j++){
                Color c=new Color(imagen.getRGB(i, j));
                int TonoGris = (c.getRed()+c.getGreen()+c.getBlue())/3;
                if(TonoGris>=150){
                    c=new Color(mediana,mediana,mediana);
                }else{
                    c= new Color(TonoGris,TonoGris,TonoGris);
                }
                imagen.setRGB(i, j, c.getRGB());
                Matriz[i][j]=TonoGris;
            }
        }
        return imagen;
    }
    
    public BufferedImage recibeModa(int moda) throws IOException{
        BufferedImage imagen = ImageIO.read(file);
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        Matriz=new int[ancho][alto];
        for(int i=0; i<ancho;i++){
            for(int j=0; j<alto; j++){
                Color c=new Color(imagen.getRGB(i, j));
                int TonoGris = (c.getRed()+c.getGreen()+c.getBlue())/3;
                if(TonoGris>=150){
                    c=new Color(moda,moda,moda);
                }else{
                    c= new Color(TonoGris,TonoGris,TonoGris);
                }
                imagen.setRGB(i, j, c.getRGB());
                Matriz[i][j]=TonoGris;
            }
        }
        return imagen;
    }
    
    public BufferedImage recibeMediaBlanco(int media) throws IOException {	
        BufferedImage imagem = ImageIO.read(file);
        int ancho = imagem.getWidth();
        int alto = imagem.getHeight();
        Matriz = new int[ancho][alto];
        for(int i=0; i<ancho;i++){
                for(int j=0; j<alto; j++){
                    Color c = new Color(imagem.getRGB(i, j));
                    int TonoGris=(c.getRed()+c.getGreen()+c.getBlue())/3;
                    if(TonoGris>media){
                            c=new Color (255, 255, 255);
                    }else{
                            c= new Color(TonoGris,TonoGris,TonoGris);
                    }
                    imagem.setRGB(i, j, c.getRGB());
                    Matriz[i][j]=TonoGris;
                }
        }
        return imagem;
    }
    
    public BufferedImage recibeMedianayMedia(int mediana, int media) throws IOException{
        BufferedImage imagen = ImageIO.read(file);
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        Matriz = new int [ancho][alto];
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(imagen.getRGB(i, j));
                int TonoGris = (c.getRed()+c.getGreen()+c.getBlue())/3;
                if(TonoGris>media){
                    c = new Color(255,255,255);
                }
                else if(TonoGris<mediana){
                    c = new Color(0,0,0);
                }
                else{
                    c = new Color(TonoGris,TonoGris,TonoGris);
                }
                imagen.setRGB(i, j, c.getRGB());
                Matriz[i][j]=TonoGris;
            }
        }
        return imagen;
    }
    
    public BufferedImage Reflejoejex() throws IOException{
        MatrizIdentidad(MatrizTransformacion);
        MatrizTransformacion[0][0]=-1;
        BufferedImage imagenO = ImageIO.read(file);
        BufferedImage imagenF = ImageIO.read(file);
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = MultPColumna(MatrizTransformacion,i,j,1) + (ancho-1);
                imagenF.setRGB(ni, j, c.getRGB());
            }
        }
        return imagenF;
    }
    
    public BufferedImage Reflejoejey() throws IOException{
        MatrizIdentidad(MatrizTransformacion);
        MatrizTransformacion[1][1]=-1;
        BufferedImage imagenO = ImageIO.read(file);
        BufferedImage imagenF = ImageIO.read(file);
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(imagenO.getRGB(i, j));
                int nj = MultSColumna(MatrizTransformacion,i,j,1) + (alto-1);
                imagenF.setRGB(i, nj, c.getRGB());
            }
        }
        return imagenF;
    }
    
    public BufferedImage Traslacion(int x, int y) throws IOException{
        MatrizIdentidad(MatrizTransformacion);
        BufferedImage imagenO = ImageIO.read(file);
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = i+y;
                int nj = j+x;
                if(ni<ancho && nj<alto && ni>=0 && nj>=0){
                   imagenF.setRGB(ni, nj, c.getRGB()); 
                }              
            }
        }
        return imagenF;
    }
    
    public BufferedImage Ampliar(int ampliacion) throws IOException{
        MatrizIdentidad(MatrizTransformacion);
        MatrizTransformacion[0][0]=ampliacion;
        MatrizTransformacion[1][1]=ampliacion;
        BufferedImage imagenO = ImageIO.read(file);     
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(ancho*ampliacion, alto*ampliacion, imagenO.getType());
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = i*ampliacion;
                int nj = j*ampliacion;
                imagenF.setRGB(ni, nj, c.getRGB());               
            }
        }
        return imagenF;
    }
    
    public BufferedImage Reducir(double reduccion) throws IOException{
        BufferedImage imagenO = ImageIO.read(file);
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        int nalto =alto/(int)reduccion;
        int nancho = ancho/(int)reduccion;
        BufferedImage imagenF = new BufferedImage(nancho, nalto, imagenO.getType());
        reduccion=1/reduccion;
        MatrizIdentidadD(MatrizTransformacionD);
        MatrizTransformacionD[0][0]=reduccion;
        MatrizTransformacionD[1][1]=reduccion;
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = (int) (i*reduccion);
                int nj = (int) (j*reduccion);
                if(ni<nancho && nj<nalto && ni>=0 && nj>=0){
                    imagenF.setRGB(ni, nj, c.getRGB());
                }                              
            }
        }
        return imagenF;
    }
    
    public BufferedImage Rotar(double angulo) throws IOException{
        MatrizIdentidadD(MatrizTransformacionD);
        BufferedImage imagenO = ImageIO.read(file);
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        double radianes = (angulo*Math.PI)/180;
        int centrox = imagenO.getHeight()/2;
        int centroy = imagenO.getWidth()/2;
        double nejex = centrox-centrox*Math.cos(radianes)-centroy*Math.sin(radianes);
        double nejey = centroy+centrox*Math.sin(radianes)-centroy*Math.cos(radianes);
        MatrizTransformacionD[0][0]=Math.cos(radianes);
        MatrizTransformacionD[0][1]=-Math.sin(radianes);
        MatrizTransformacionD[1][1]=Math.cos(radianes);
        MatrizTransformacionD[1][0]=Math.sin(radianes);
        MatrizTransformacionD[2][0]=nejex;
        MatrizTransformacionD[2][1]=nejey;     
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(0,0,0);
                imagenF.setRGB(i, j, c.getRGB());             
            }
        }
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = (int) (DMultPColumna(MatrizTransformacionD,i,j,1));
                int nj = (int) (DMultSColumna(MatrizTransformacionD,i,j,1));
                if(ni<ancho && nj<alto && ni>=0 && nj>=0){
                    imagenF.setRGB(ni, nj, c.getRGB());
                }                              
            }
        }
        return imagenF;
    }
    
    public BufferedImage Libre(double pos00,double pos01,double pos02,double pos10,double pos11,double pos12,double pos20,double pos21,double pos22) throws IOException{
        BufferedImage imagenO = ImageIO.read(file);
        int alto = imagenO.getHeight();
        int ancho = imagenO.getWidth();
        BufferedImage imagenF = new BufferedImage(ancho, alto, imagenO.getType());
        MatrizTransformacionD[0][0]=pos00;
        MatrizTransformacionD[0][1]=pos01;
        MatrizTransformacionD[0][2]=pos02;
        MatrizTransformacionD[1][0]=pos10;
        MatrizTransformacionD[1][1]=pos11;
        MatrizTransformacionD[1][2]=pos12;
        MatrizTransformacionD[2][0]=pos20;
        MatrizTransformacionD[2][1]=pos21;
        MatrizTransformacionD[2][2]=pos22;
        for(int i=0;i<ancho;i++){
            for(int j=0;j<alto;j++){
                Color c = new Color(imagenO.getRGB(i, j));
                int ni = (int) (DMultPColumna(MatrizTransformacionD,i,j,1));
                int nj = (int) (DMultSColumna(MatrizTransformacionD,i,j,1));
                if(ni<ancho && nj<alto && ni>=0 && nj>=0){
                    imagenF.setRGB(ni, nj, c.getRGB());
                }                              
            }
        }
        return imagenF;
    }
     
    public void mostraHistograma() throws IOException{
        BufferedImage imagen = ImageIO.read(file);
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        Matriz=new int[ancho][alto];
        for(int i=0; i<ancho;i++){
            for(int j=0; j<alto; j++){
                Color c=new Color(imagen.getRGB(i, j));
                int TonoGris=(c.getRed()+c.getGreen()+c.getBlue())/3;
                c= new Color(TonoGris,TonoGris,TonoGris);
                imagen.setRGB(i, j, c.getRGB());
                Matriz[i][j]=TonoGris;
            }
        }
        Histograma hist = new Histograma();
        hist.GenerarHistograma(imagen,Matriz);
    }
}
