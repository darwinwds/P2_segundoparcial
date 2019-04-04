/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Mundo.Letra;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Darwin W. Diaz Simon
 */
@SuppressWarnings("serial")
public class PanelLetras extends JPanel implements ActionListener
{
  
    private static final Color UNSELECTED = Color.BLACK;

   
    private static final Color NUMERO = Color.BLUE;

   
    public enum Estado {
        /**
         * Representa que no se ha hecho click sobre ninguna letra.
         */
        NINGUNA,
        /**
         * Representa que se ha seleccionado la primera letra.
         */
        PRIMERA
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal.
     */
    private InterfazSopaDeLetras principal;

    /**
     * Es el estado actual del juego.
     */
    private Estado estadoActual;

    /**
     * Es la fila seleccionada actualmente.
     */
    private int fila;

    /**
     * Es la columna seleccionada actualmente.
     */
    private int columna;

 
   
    private JButton[][] letras;

    /**
     * Es el borde del panel.
     */
    private TitledBorder borde;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

 
    public PanelLetras( InterfazSopaDeLetras pPrincipal )
    {
        borde = new TitledBorder( "Sopa de Letras" );
        setBorder( borde );
        principal = pPrincipal;
    }

    
    public void inicializar( Letra[][] pModeloLetras, int pPalabrasRestantes )
    {
        // limpiar el panel
        removeAll( );

        // Iniciar el layout de grilla
        int filas = pModeloLetras[ 0 ].length, columnas = pModeloLetras.length;
        setLayout( new GridLayout( filas + 1, columnas + 1 ) );
        letras = new JButton[columnas][filas];

        add( new JLabel( " " ) );
        for( int x = 0; x < columnas; x++ )
        {
            JLabel labNumero = new JLabel( "" + ( x + 1 ) );
            labNumero.setHorizontalAlignment( JLabel.CENTER );
            labNumero.setForeground( NUMERO );
            add( labNumero );
        }

        for( int y = 0; y < filas; y++ )
        {
            JLabel labNumero = new JLabel( "" + ( y + 1 ) );
            labNumero.setHorizontalAlignment( JLabel.CENTER );
            labNumero.setForeground( NUMERO );
            add( labNumero );

            for( int x = 0; x < columnas; x++ )
            {
                Letra letra = pModeloLetras[ x ][ y ];
                JButton btnLetra = new JButton( letra.toString( ) );
                btnLetra.setForeground( UNSELECTED );
                btnLetra.setBackground( Color.WHITE );
                btnLetra.setBorder( new LineBorder( Color.GRAY ) );
                btnLetra.setFont( new Font( "Times", Font.BOLD, 16 ) );
                btnLetra.setHorizontalAlignment( JLabel.CENTER );
                btnLetra.setActionCommand( x + "-" + y );
                btnLetra.addActionListener( this );
                letras[ x ][ y ] = btnLetra;
                add( btnLetra ); // Las letras se agregan por filas
            }
        }
        borde.setTitle( "Sopa de Letras: quedan " + pPalabrasRestantes );
        validate( );

        estadoActual = Estado.NINGUNA;
        fila = -1;
        columna = -1;
    }

   
    public void actualizar( Letra[][] pModeloLetras, int pPalabrasRestantes )
    {
        int filas = pModeloLetras[ 0 ].length;
        int columnas = pModeloLetras.length;

        for( int y = 0; y < filas; y++ )
        {
            for( int x = 0; x < columnas; x++ )
            {
                Letra letra = pModeloLetras[ x ][ y ];
                JButton btnLetra = letras[ x ][ y ];
                if( letra.darMarcada( ) )
                {
                    btnLetra.setBackground( Color.GREEN );
                }
            }
        }
        borde.setTitle( "Sopa de Letras: quedan " + pPalabrasRestantes );
        validate( );
    }

   
    public void actionPerformed( ActionEvent pEvento )
    {

        String[] coordenadas = pEvento.getActionCommand( ).split( "-" );
        int laFila = Integer.parseInt( coordenadas[ 0 ] );
        int laColumna = Integer.parseInt( coordenadas[ 1 ] );
        if( estadoActual == Estado.NINGUNA )
        {
            estadoActual = Estado.PRIMERA;
            fila = laFila;
            columna = laColumna;
            letras[ fila ][ columna ].setBackground( Color.YELLOW );
        }
        else if( laFila == fila && laColumna == columna && estadoActual == Estado.PRIMERA )
        {
            estadoActual = Estado.NINGUNA;
            letras[ fila ][ columna ].setBackground( Color.WHITE );
            fila = -1;
            columna = -1;
        }
        else
        {
            estadoActual = Estado.NINGUNA;
            letras[ fila ][ columna ].setBackground( Color.WHITE );
            principal.enviarJugada( fila + 1, columna + 1, laFila + 1, laColumna + 1 );
            fila = -1;
            columna = -1;
        }
    }
}