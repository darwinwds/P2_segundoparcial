/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Darwin W. Diaz Simon
 */

public class PanelExtension extends JPanel implements ActionListener
{
    private static final String CARGAR = "Cargar";

  
    private static final String OPCION_1 = "Opcion1";

   
    private static final String OPCION_2 = "Opcion2";

    
    private InterfazSopaDeLetras principal;

  
    private JButton btnCargar;

    private JButton botonOpcion1;

    
    private JButton botonOpcion2;

  
    public PanelExtension( InterfazSopaDeLetras pPrincipal )
    {
        principal = pPrincipal;
        btnCargar = new JButton( "Cargar" );
        btnCargar.setActionCommand( CARGAR );
        btnCargar.addActionListener( this );
        add( btnCargar );

        botonOpcion1 = new JButton( "Opción 1" );
        botonOpcion1.setActionCommand( OPCION_1 );
        botonOpcion1.addActionListener( this );
        add( botonOpcion1 );

        botonOpcion2 = new JButton( "Opción 2" );
        botonOpcion2.setActionCommand( OPCION_2 );
        botonOpcion2.addActionListener( this );
        add( botonOpcion2 );
    }

 
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( OPCION_1 ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( comando.equals( OPCION_2 ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( comando.equals( CARGAR ) )
        {
            principal.cargar( );
        }
    }
}