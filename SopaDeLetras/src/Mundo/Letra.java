/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

/**
 *
 * @author Darwin W. Diaz Simon
 */
public class Letra {
   
    private char letra;

  
    private boolean marcada;

  
    public Letra( String pLetra ) throws Exception
    {
        if( pLetra.length( ) != 1 )
        {
            throw new Exception( "La letra solamente puede tener un carácter" );
        }
        String letraMayuscula = pLetra.toUpperCase( );

        char caracter = letraMayuscula.charAt( 0 );
        if( caracter > 'Z' || caracter < 'A' )
        {
            throw new Exception( "Debe ser una letra" );
        }

        letra = caracter;
    }

  
    public char darLetra( )
    {
        return letra;
    }

  
    public void marcar( )
    {
        marcada = true;
    }

   
    public boolean darMarcada( )
    {
        return marcada;
    }

  
    public String toString( )
    {
        return "" + darLetra( );
    }

    
    public boolean esIgual( Letra pOtra )
    {
        boolean iguales = false;
        if( pOtra.letra == letra )
        {
            iguales = true;
        }
        return iguales;
    }
}
