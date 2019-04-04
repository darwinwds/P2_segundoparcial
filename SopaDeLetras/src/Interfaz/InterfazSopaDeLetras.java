/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Mundo.SopaDeLetras;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import Mundo.SopaDeLetras;
/**
 *
 *@author Darwin W. Diaz Simon
 */
public class InterfazSopaDeLetras extends JFrame
{
    
    private SopaDeLetras sopaDeLetras;

    
  

   
    private PanelResultado panelControles;


    private PanelLetras panelLetras;

   
    private PanelExtension panelExtension;

  
    public InterfazSopaDeLetras( )
    {
        setSize( 600, 700 );
        // Inicializar los paneles
        JPanel panelArriba = new JPanel( new BorderLayout( ) );
       
        panelExtension = new PanelExtension( this );
        panelControles = new PanelResultado( );
        panelLetras = new PanelLetras( this );

        // Ubicar los paneles
       
        panelArriba.add( panelControles, BorderLayout.SOUTH );
        panelArriba.add( panelLetras, BorderLayout.CENTER );
        add( panelArriba, BorderLayout.CENTER );
        add( panelExtension, BorderLayout.SOUTH );

        setTitle( "Sopa de Letras" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        setResizable( false );
    }

 
    public void cargar( )
    {
        // Pregunta por el archivo de la sopa de letras
        JFileChooser selector = new JFileChooser( "./data" );
        selector.showOpenDialog( this );
        File archivo = selector.getSelectedFile( );
        if( archivo != null )
        {
            try
            {
                sopaDeLetras = new SopaDeLetras( archivo );
                panelLetras.inicializar( sopaDeLetras.darLetras( ), sopaDeLetras.darNumeroPalabrasRestantes( ) );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, "Error al cargar el archivo!", "Error", JOptionPane.INFORMATION_MESSAGE );
                System.exit( 0 );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar un archivo para poder jugar.", "Error", JOptionPane.INFORMATION_MESSAGE );
            System.exit( 0 );
        }
    }
  public void enviarJugada( int pX1, int pY1, int pX2, int pY2 )
    {
        try
        {
            String palabra = sopaDeLetras.buscarPalabra( pX1, pY1, pX2, pY2 );
            if( palabra != null )
            {
                panelControles.cambiarMensaje( "PALABRA ENCONTRADA: " + palabra, true );
                panelLetras.actualizar( sopaDeLetras.darLetras( ), sopaDeLetras.darNumeroPalabrasRestantes( ) );
                if( sopaDeLetras.gano( ) )
                {
                    JOptionPane.showMessageDialog( this, "Ganó!", "Juego terminado", JOptionPane.INFORMATION_MESSAGE );
                }
            }
            else
            {
                panelControles.cambiarMensaje( "INTENTA OTRA VEZ", false );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ) );
        }
    }

   
    public void reqFuncOpcion1( )
    {
        String resultado = sopaDeLetras.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    
    public void reqFuncOpcion2( )
    {
        String resultado = sopaDeLetras.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    
    public static void main( String[] pArgs )
    {
        try
        {
        
            InterfazSopaDeLetras fsopa = new InterfazSopaDeLetras( );
            fsopa.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

}