/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Darwin W. Diaz Simon
 */
public class PanelResultado extends JPanel
{
  
    private JLabel labMensaje;

    public PanelResultado( )
    {
        setLayout( new BorderLayout( ) );
        labMensaje = new JLabel( " " );
        labMensaje.setFont( new Font( "Courier", Font.BOLD, 20 ) );
        labMensaje.setHorizontalAlignment( JLabel.CENTER );
        add( labMensaje, BorderLayout.CENTER );
    }

 
    public void cambiarMensaje( String pMensaje, boolean pCorrecta )
    {
        labMensaje.setText( pMensaje );
        if( pCorrecta )
        {
            labMensaje.setForeground( Color.GREEN );
        }
        else
        {
            labMensaje.setForeground( Color.RED );
        }
    }
}
