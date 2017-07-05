package Trabajo1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main extends JFrame{
    
    File f = null;
    File fileO = null;
    File fileM = null;
    JLabel modificado;
    JLabel original;
    BufferedImage bfImage = null;
    BufferedImage BfImagenF = null;
    BufferedImage BfImagenO = null;
    static public int CondicionalImg = 0;
    public int media=-1;
    public int mediana=-1;
    public int moda=-1;
    public int varianza=-1;
    JMenu estadistica = new JMenu("Estadistica");
    JMenu transformaciongeo = new JMenu("Transformaciones Geometricas");
    JMenu segmentacion = new JMenu("Segmentación");
    JMenu bordes = new JMenu("Detección de Bordes");
    JMenu morfologiamat = new JMenu("Morfología Matematica");
    JMenu figuras = new JMenu("Reconocer Figuras");
    JMenu proyecto = new JMenu("Proyecto");
    ButtonGroup bg = new ButtonGroup();
    JRadioButton tco = new JRadioButton("Trabajar con Imagen Original");//trabajar con orginial
    JRadioButton tcm = new JRadioButton("Trabajar con Imagen Modificada");//trabajar con modificada
    JScrollPane imgFinal = new JScrollPane();
    JScrollPane imgOriginal = new JScrollPane();
    JLabel Media=new JLabel("Média: ");
    JLabel Mediana=new JLabel("Mediana: ");
    JLabel Moda=new JLabel("Moda: ");
    JLabel Varianza=new JLabel("Varianza: ");
    
    public Main(){	
                super("Visión por Computador");
                
                //Barra Menu
                JMenuBar barraMenu = new JMenuBar();
                setJMenuBar(barraMenu);
                OyenteMenu menu = new OyenteMenu();
		JMenu archivo = new JMenu("Archivo");
		barraMenu.add(archivo);
                JMenuItem abrir = new JMenuItem("Abrir");
                abrir.addActionListener(menu);
                archivo.add(abrir);
                JMenuItem cerrar = new JMenuItem("Cerrar");
                cerrar.addActionListener(menu);
                archivo.add(cerrar);
		barraMenu.add(estadistica);
                estadistica.setEnabled(false);
                JMenuItem histograma = new JMenuItem("Histograma");
                histograma.addActionListener(menu);
		estadistica.add(histograma);
                JMenuItem mmedia = new JMenuItem("Media");
                mmedia.addActionListener(menu);
                estadistica.add(mmedia);
                JMenuItem mmediana = new JMenuItem("Mediana");
                mmediana.addActionListener(menu);
                estadistica.add(mmediana);
                JMenuItem mmoda = new JMenuItem("Moda");
                mmoda.addActionListener(menu);
                estadistica.add(mmoda);
                JMenuItem mvarianza = new JMenuItem("Varianza");
                mvarianza.addActionListener(menu);
                estadistica.add(mvarianza);
                JMenu cambios = new JMenu("Cambios");
                JMenuItem mayormedia = new JMenuItem("Valores >= 150 reciben media");
                mayormedia.addActionListener(menu);
                cambios.add(mayormedia);
                JMenuItem mayormediana = new JMenuItem("Valores >= 150 reciben mediana");
                mayormediana.addActionListener(menu);
                cambios.add(mayormediana);
                JMenuItem mayormoda = new JMenuItem("Valores >= 150 reciben moda");
                mayormoda.addActionListener(menu);
                cambios.add(mayormoda);
                JMenuItem mediablanco = new JMenuItem("Valores >= media reciben blanco");
                mediablanco.addActionListener(menu);
                cambios.add(mediablanco);
                JMenuItem blanconegro = new JMenuItem("< mediana negro, > media blanco");
                blanconegro.addActionListener(menu);
                cambios.add(blanconegro);
                estadistica.add(cambios);
		barraMenu.add(transformaciongeo);
                transformaciongeo.setEnabled(false);
                JMenuItem reflejox = new JMenuItem("Reflejo eje x");
                reflejox.addActionListener(menu);
		transformaciongeo.add(reflejox);
                JMenuItem reflejoy = new JMenuItem("Reflejo eje y");
                reflejoy.addActionListener(menu);
                transformaciongeo.add(reflejoy);
                JMenuItem traslacion = new JMenuItem("Traslacion");
                traslacion.addActionListener(menu);
                transformaciongeo.add(traslacion);
                JMenu cescala = new JMenu("Cambio escala");
                JMenuItem ampliar = new JMenuItem("Ampliar");
                ampliar.addActionListener(menu);
                cescala.add(ampliar);
                JMenuItem reducir = new JMenuItem("Reducir");
                reducir.addActionListener(menu);
                cescala.add(reducir);
                transformaciongeo.add(cescala);
                JMenuItem rotar = new JMenuItem("Rotar");
                rotar.addActionListener(menu);
                transformaciongeo.add(rotar);
                JMenuItem libre = new JMenuItem("Libre");
                libre.addActionListener(menu);
                transformaciongeo.add(libre);
                barraMenu.add(segmentacion);
                segmentacion.setEnabled(false);
                JMenuItem brillo = new JMenuItem("Brillo");
                brillo.addActionListener(menu);
                segmentacion.add(brillo);
                JMenuItem contraste = new JMenuItem("Contraste");
                contraste.addActionListener(menu);
                segmentacion.add(contraste);
                JMenu transftongris = new JMenu("Transformar en tonalidades de gris");
                JMenuItem promsimple = new JMenuItem("Promedio Simple");
                promsimple.addActionListener(menu);
                transftongris.add(promsimple);
                JMenuItem promponderado1 = new JMenuItem("Promedio Ponderado 1");
                promponderado1.addActionListener(menu);
                transftongris.add(promponderado1);
                JMenuItem promponderado2 = new JMenuItem("Promedio Ponderado 2");
                promponderado2.addActionListener(menu);
                transftongris.add(promponderado2);
                segmentacion.add(transftongris);
                JMenu fsuavizado = new JMenu("Filtros de Suavizado");
                JMenuItem fpromedio = new JMenuItem("Filtro Promedio");
                fpromedio.addActionListener(menu);
                fsuavizado.add(fpromedio);
                JMenuItem fmediana = new JMenuItem("Filtro Mediana");
                fmediana.addActionListener(menu);
                fsuavizado.add(fmediana);
                JMenuItem fmoda = new JMenuItem("Filtro Moda");
                fmoda.addActionListener(menu);
                fsuavizado.add(fmoda);
                JMenuItem fgauss = new JMenuItem("Filtro Gauss");
                fgauss.addActionListener(menu);
                fsuavizado.add(fgauss);
                segmentacion.add(fsuavizado);
                JMenuItem limitarizacion = new JMenuItem("Limitarización");
                limitarizacion.addActionListener(menu);
                segmentacion.add(limitarizacion);
                barraMenu.add(bordes);
                bordes.setEnabled(false);
                JMenuItem roberts = new JMenuItem("Roberts");
                roberts.addActionListener(menu);
                bordes.add(roberts);
                JMenuItem sobel = new JMenuItem("Sobel");
                sobel.addActionListener(menu);
                bordes.add(sobel);
                JMenuItem operadorKirsch = new JMenuItem("Operador de Kirsch");
                operadorKirsch.addActionListener(menu);
                bordes.add(operadorKirsch);
                JMenuItem operadorRobinson = new JMenuItem("Operador de Robinson");
                operadorRobinson.addActionListener(menu);
                bordes.add(operadorRobinson);
                barraMenu.add(morfologiamat);
                morfologiamat.setEnabled(false);
                JMenuItem dilatacion = new JMenuItem("Dilatación");
                dilatacion.addActionListener(menu);
                morfologiamat.add(dilatacion);
                JMenuItem erosion = new JMenuItem("Erosión");
                erosion.addActionListener(menu);
                morfologiamat.add(erosion);
                JMenuItem abertura = new JMenuItem("Abertura");
                abertura.addActionListener(menu);
                morfologiamat.add(abertura);
                JMenuItem cierre = new JMenuItem("Cierre");
                cierre.addActionListener(menu);
                morfologiamat.add(cierre);
                JMenuItem esqueleto = new JMenuItem("Esqueletización");
                esqueleto.addActionListener(menu);
                morfologiamat.add(esqueleto);
                barraMenu.add(figuras);
                figuras.setEnabled(false);
                JMenu cuadrado = new JMenu("Cuadrado");
                JMenuItem areacua = new JMenuItem("Área");
                areacua.addActionListener(menu);
                cuadrado.add(areacua);
                JMenuItem perimetrocua = new JMenuItem("Perimetro");
                perimetrocua.addActionListener(menu);
                cuadrado.add(perimetrocua);
                figuras.add(cuadrado);
                JMenu circulos = new JMenu("Círculos");
                JMenuItem areacir = new JMenuItem("Área círculo mayor");
                areacir.addActionListener(menu);
                circulos.add(areacir);
                JMenuItem perimetrocir = new JMenuItem("Perimetro círculo menor");
                perimetrocir.addActionListener(menu);
                circulos.add(perimetrocir);
                figuras.add(circulos);
                JMenuItem circularidad = new JMenuItem("Circularidad de los 3 Circulos");
                circularidad.addActionListener(menu);
                circulos.add(circularidad);
                barraMenu.add(proyecto);
                proyecto.setEnabled(false);
                JMenuItem placa = new JMenuItem("Placas");
                placa.addActionListener(menu);
                proyecto.add(placa);
                JMenuItem rotular = new JMenuItem("Rotular");
                rotular.addActionListener(menu);
                proyecto.add(rotular);
                JMenuItem pintar = new JMenuItem("Pintar");
                pintar.addActionListener(menu);
                proyecto.add(pintar);
                
                
                //grupo de botones
                tco.addActionListener(menu);
                tcm.addActionListener(menu);
                bg.add(tco);
                bg.add(tcm);
                
                
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1150, 720);
		setVisible(true);
		setResizable(false);
		setLayout(null);
                
                //estadisticas
                Media.setBounds(100, 5, 100, 25);
		Media.setVisible(true);
		Mediana.setBounds(300, 5, 100, 25);
		Mediana.setVisible(true);
		Varianza.setBounds(700, 5, 150, 25);
		Varianza.setVisible(true);
		Moda.setBounds(500, 5, 200, 25);
		Moda.setVisible(true);
                getContentPane().add(Media);
                getContentPane().add(Mediana);
                getContentPane().add(Varianza);
                getContentPane().add(Moda);
                
                //botones
                tco.setBounds(100,35,300,25);
                tco.setVisible(true);
                tco.setSelected(true);
                tco.setEnabled(false);
                tcm.setBounds(700,35,300,25);
                tcm.setVisible(true);
                tco.setSelected(false);               
                tcm.setEnabled(false);
                getContentPane().add(tco);
                getContentPane().add(tcm);
                
                //imagenes
                imgOriginal.setBounds(10, 65, 550, 600);
                imgFinal.setBounds(570, 65, 550, 600);
                getContentPane().add(imgOriginal);
                getContentPane().add(imgFinal);
    }
    
    public static void main(String[] args) {
        Main ventana = new Main();
    }
    
    class OyenteMenu implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==tco || CondicionalImg==0){
                bfImage=BfImagenO;
                CondicionalImg=0;
            }
            if(e.getSource()==tcm || CondicionalImg==1){
                bfImage=BfImagenF;
                CondicionalImg=1;
            }
            Metodos metodos = new Metodos(bfImage);
            String comandoAccionm = (String)e.getActionCommand();
            if(e.getSource() instanceof JMenuItem)
            if(comandoAccionm.equals("Original")){
                CondicionalImg=0;
                System.out.println(CondicionalImg);
            }
            if(comandoAccionm.equals("Final")){
                CondicionalImg=1;
                System.out.println(CondicionalImg);
            }
            if(comandoAccionm.equals("Histograma")){
                if(f!=null){
                   try {
                        metodos.mostraHistograma();
                    } catch (IOException exep) {
                        exep.printStackTrace();
                    } 
                }
                else{
                    JOptionPane.showMessageDialog(null, "Cargue la imagen primero");
                }
            }
            if(comandoAccionm.equals("Media")){
                try {
                        media=metodos.CalcularMedia();
                        Media.setText("Média: "+media);
                } catch (IOException exep) {	
                        exep.printStackTrace();
                }		
            }
            if(comandoAccionm.equals("Mediana")){
                try {
                    mediana=metodos.CalcularMediana();
                    Mediana.setText("Mediana: "+mediana);
                } catch (IOException exep) {
                    exep.printStackTrace();
                }

            }
            if(comandoAccionm.equals("Moda")){
                try {
                        moda=metodos.CalcularModa();
                        Moda.setText("Moda: "+moda);
                } catch (IOException exep) {
                    exep.printStackTrace();
                }
            }
            if(comandoAccionm.equals("Varianza")){
                if(media!=-1){
                    try {
                            varianza=metodos.CalcularVarianza(media);
                            Varianza.setText("Varianza: "+varianza);
                    } catch (IOException exep) {

                            exep.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Calcule la Media primero");
                }
            }    
            if(comandoAccionm.equals("Abrir")){
                JFileChooser jf = new JFileChooser();//clase del explorador
                File diretorio = new File("C://");
                jf.setCurrentDirectory(diretorio);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg", "PNG", "png");
                jf.setFileFilter(filter);
                if(jf.showOpenDialog(getParent())== JFileChooser.APPROVE_OPTION) {  //Verifica si el usuario dio "ok"
                    File file= jf.getSelectedFile();
                    modificado= new JLabel(new ImageIcon(file.toString()));
                    original=modificado;
                    f=file;
                    try {
                        BfImagenO = ImageIO.read(file);
                        BfImagenF = ImageIO.read(file);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "No se puede abrir la imagen");
                    }
                    imgOriginal.setViewportView(modificado);		        		
                    imgFinal.setViewportView(null);
                    validate();
                    media=-1;
                    mediana=-1;
                    moda=-1;
                    varianza=-1;
                    Media.setText("Média: ");
                    Mediana.setText("Mediana: ");
                    Moda.setText("Moda: ");
                    Varianza.setText("Varianza: ");
                    transformaciongeo.setEnabled(true);
                    estadistica.setEnabled(true);
                    segmentacion.setEnabled(true);
                    bordes.setEnabled(true);
                    morfologiamat.setEnabled(true);
                    figuras.setEnabled(true);
                    proyecto.setEnabled(true);
                    tco.setEnabled(true);
                    tcm.setEnabled(true);
                    try {
                        bfImage=ImageIO.read(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if(comandoAccionm.equals("Cerrar")){
                dispose();
            }
            if(comandoAccionm.equals("Valores >= 150 reciben media")){
                if(media>=0){
                    try {
                        bfImage=metodos.recibeMedia(media);
                        BfImagenF=bfImage;
                    } catch (IOException exep) {
                        exep.printStackTrace();
                    }
                    ImageIcon ico = new ImageIcon(bfImage);
                    JLabel imageLabe = new JLabel(ico);
                    modificado = new JLabel(ico);
                    imgFinal.setViewportView(imageLabe);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Calcule la Media primero");
                }
            }
            if(comandoAccionm.equals("Valores >= 150 reciben mediana")){
                if(mediana>=0){
                    try {
                        bfImage=metodos.recibeMediana(mediana);
                        BfImagenF=bfImage;
                    } catch (IOException exep) {
                        exep.printStackTrace();
                    }
                    ImageIcon ico = new ImageIcon(bfImage);
                    modificado = new JLabel(ico);
                    imgFinal.setViewportView(modificado);
                }else{
                    JOptionPane.showMessageDialog(null, "Calcule la Mediana primero");
                }
            }
            if(comandoAccionm.equals("Valores >= 150 reciben moda")){
                if(moda>=0){
                    try{
                        bfImage=metodos.recibeModa(moda);
                        BfImagenF=bfImage;
                    }catch (IOException exep){
                        exep.printStackTrace();
                    }
                    ImageIcon ico = new ImageIcon(bfImage);
                    modificado = new JLabel(ico);
                    imgFinal.setViewportView(modificado);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Calcule la Moda primero");
                }
            }
            if(comandoAccionm.equals("Valores >= media reciben blanco")){
                if(media>=0){
                    try {
                        bfImage=metodos.recibeMediaBlanco(media);
                        BfImagenF=bfImage;
                    } catch (IOException exep) {
                        exep.printStackTrace();
                    }
                    ImageIcon ico = new ImageIcon(bfImage);
                    JLabel imageLabe = new JLabel(ico);
                    modificado = new JLabel(ico);
                    imgFinal.setViewportView(imageLabe);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Calcule la Media primero");
                }
            }
            if(comandoAccionm.equals("< mediana negro, > media blanco")){
                if(mediana>=0){
                    try{
                       bfImage=metodos.recibeMedianayMedia(mediana,media);
                       BfImagenF=bfImage;
                    }catch (IOException exep){
                        exep.printStackTrace();
                    }
                    ImageIcon ico = new ImageIcon(bfImage);
                    modificado = new JLabel(ico);
                    imgFinal.setViewportView(modificado);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Calcule la Mediana primero");
                }
            }
            if(comandoAccionm.equals("Reflejo eje x")){
                    try{
                       bfImage=metodos.Reflejoejex();
                       BfImagenF=bfImage;
                    }catch (IOException exep){
                        exep.printStackTrace();
                    }
                    ImageIcon ico = new ImageIcon(bfImage);
                    modificado = new JLabel(ico);
                    imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Reflejo eje y")){
                try{
                   bfImage=metodos.Reflejoejey();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Traslacion")){
                int ejex = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca traslación","Mover en eje x",JOptionPane.QUESTION_MESSAGE));
                int ejey = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca traslación","Mover en eje y",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Traslacion(ejex, ejey);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Ampliar")){
                int ampliacion = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca Escala de ampliación","Ampliación",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Ampliar(ampliacion);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Reducir")){
                double reduccion = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca Escala de reducción","Reducción",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Reducir(reduccion);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Rotar")){
                double angulo = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca el angulo de rotacion","Rotación",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Rotar(angulo);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Libre")){
                double pos00 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [0][0] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                double pos01 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [0][1] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                double pos02 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [0][2] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                double pos10 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [1][0] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                double pos11 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [1][1] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                double pos12 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [1][2] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                double pos20 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [2][0] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                double pos21 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [2][1] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                double pos22 = Double.parseDouble(JOptionPane.showInputDialog(null,"Introduzca posicion [2][2] de la matriz","Transformación Libre",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Libre(pos00,pos01,pos02,pos10,pos11,pos12,pos20,pos21,pos22);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Promedio Simple")){
                try{
                   bfImage=metodos.PromedioSimple();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Promedio Ponderado 1")){
                try{
                   bfImage=metodos.PromedioP1();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Promedio Ponderado 2")){
                try{
                   bfImage=metodos.PromedioP2();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Brillo")){
                float brillo = Float.parseFloat(JOptionPane.showInputDialog(null,"Introduzca el brillo","Brillo",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Brillo(brillo);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Contraste")){
                float contraste = Float.parseFloat(JOptionPane.showInputDialog(null,"Introduzca el brillo","Brillo",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Contraste(contraste);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Filtro Promedio")){
                try{
                   bfImage=metodos.FiltroPromedio();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Filtro Mediana")){
                try{
                   bfImage=metodos.FiltroPromedio();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Filtro Moda")){
                try{
                   bfImage=metodos.FiltroPromedio();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Filtro Gauss")){
                try{
                   bfImage=metodos.FiltroGauss();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Limitarización")){
                int umbral = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca el umbral","Umbral",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Limitarizacion(umbral);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Roberts")){
                int umbral = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca el umbral","Umbral",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Roberts(umbral);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Sobel")){
                int umbral = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca el umbral","Umbral",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Sobel(umbral);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Operador de Kirsch")){
                int umbral = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca el umbral","Umbral",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Kirsch(umbral);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Operador de Robinson")){
                int umbral = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca el umbral","Umbral",JOptionPane.QUESTION_MESSAGE));
                try{
                   bfImage=metodos.Robinson(umbral);
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Dilatación")){
                try{
                   bfImage=metodos.Dilatacion();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Erosión")){
                try{
                   bfImage=metodos.Erosion();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Abertura")){
                try{
                   bfImage=metodos.Erosion();
                   metodos.Imagen=bfImage;
                   bfImage=metodos.Dilatacion();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Cierre")){
                try{
                   bfImage=metodos.Dilatacion();
                   metodos.Imagen=bfImage;
                   bfImage=metodos.Erosion();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Esqueletización")){
                try{
                   bfImage=metodos.Esqueletizacion();
                   BfImagenF=bfImage;
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Área")){
                try {
                    metodos.AreaCuadrado();
                } catch (IOException exep) {
                    exep.printStackTrace();
                }
            }
            if(comandoAccionm.equals("Perimetro")){
                try {
                    metodos.PerimetroCuadrado();
                } catch (IOException exep) {
                    exep.printStackTrace();
                }
            }
            if(comandoAccionm.equals("Área círculo mayor")){
                try {
                metodos.AreaCirculo();
                } catch (IOException exep) {
                    exep.printStackTrace();
                }            
            }
            if(comandoAccionm.equals("Perimetro círculo menor")){
                try {
                metodos.PerimetroCirculo();
                } catch (IOException exep) {
                    exep.printStackTrace();
                }            
            }
            if(comandoAccionm.equals("Placas")){
                try {
                bfImage=metodos.Placas();
                metodos.Imagen=bfImage;
                bfImage=metodos.Dilatacion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Dilatacion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Dilatacion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Dilatacion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Erosion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Erosion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Erosion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Erosion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Erosion();
                metodos.Imagen=bfImage;
                bfImage=metodos.Pintar();
                BfImagenF=bfImage;
                
                } catch (IOException exep) {
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
            if(comandoAccionm.equals("Rotular")){
                try {
                metodos.EncontrarFiguras();
                } catch (IOException exep) {
                    exep.printStackTrace();
                }
            }
            if(comandoAccionm.equals("Pintar")){
                try{
                bfImage=metodos.Pintar();
                BfImagenF=bfImage;
                } catch (IOException exep) {
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
        }
    }
    
}
