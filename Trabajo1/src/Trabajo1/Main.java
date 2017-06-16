package Trabajo1;

import Trabajo1.Metodos;
import java.awt.Component;
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
    JLabel modificado;
    JLabel original;
    BufferedImage bfImage = null;
    public int media=-1;
    public int mediana=-1;
    public int moda=-1;
    public int varianza=-1;
    JMenu estadistica = new JMenu("Estadistica");
    JMenu transformaciongeo = new JMenu("Transformaciones Geometricas");
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
                
                
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1200, 720);
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
                
                //imagenes
                imgOriginal.setBounds(10, 35, 550, 600);
                imgFinal.setBounds(570, 35, 550, 600);
                getContentPane().add(imgOriginal);
                getContentPane().add(imgFinal);
    }
    
    public static void main(String[] args) {
        Main ventana = new Main();
    }
    
    class OyenteMenu implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Metodos metodos = new Metodos(f);
            String comandoAccionm = (String)e.getActionCommand();
            if(e.getSource() instanceof JMenuItem)
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
                //dispose(); cierra la ventana
                //Main.Ventana= new VentanaPrincipal();			
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
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");
                jf.setFileFilter(filter);
                if(jf.showOpenDialog(getParent())== JFileChooser.APPROVE_OPTION) {  //Verifica si el usuario dio "ok"
                    File file= jf.getSelectedFile();
                    modificado= new JLabel(new ImageIcon(file.toString()));
                    original=modificado;
                    f=file;
                    imgOriginal.setViewportView(modificado);		        		
                    imgFinal.setViewportView(null);
                    validate();
                    //Salvar.setEnabled(true);
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
                if(media!=-1){
                    try {
                        bfImage=metodos.recibeMedia(media);
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
                if(mediana>0){
                    try {
                        bfImage=metodos.recibeMediana(mediana);
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
                if(moda>0){
                    try{
                        bfImage=metodos.recibeModa(moda);
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
                if(media!=-1){
                    try {
                        bfImage=metodos.recibeMediaBlanco(media);
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
                if(mediana>0){
                    try{
                       bfImage=metodos.recibeMedianayMedia(mediana,media); 
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
                }catch (IOException exep){
                    exep.printStackTrace();
                }
                ImageIcon ico = new ImageIcon(bfImage);
                modificado = new JLabel(ico);
                imgFinal.setViewportView(modificado);
            }
        }
    }
    
}
