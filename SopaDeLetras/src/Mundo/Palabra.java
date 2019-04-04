/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import java.util.ArrayList;

/**
 *
 * @author Darwin W. Diaz Simon
 */
public class Palabra {
   
    private ArrayList<Letra> letras;

    public Palabra( ArrayList<Letra> pLetras )
    {
        letras = new ArrayList<Letra>( );
        for( int i = 0; i < pLetras.size( ); i++ )
        {
            Letra letra = pLetras.get( i );
            letras.add( letra );
        }
    }

   
    public Palabra( String pPalabra ) throws Exception
    {
        if( pPalabra.length( ) <= 1 )
            throw new Exception( "Las palabras deben tener al menos 2 caracteres" );

        pPalabra = pPalabra.toUpperCase( );

        letras = new ArrayList<Letra>( );
        int numLetras = pPalabra.length( );
        for( int i = 0; i < numLetras; i++ )
        {
            Letra letra = new Letra( "" + pPalabra.charAt( i ) );
            letras.add( letra );
        }
    }

 
    public int darTamanio( )
    {
        return letras.size( );
    }

    
    public boolean sonIguales( Palabra pOtra )
    {
        boolean iguales = true;

        if( pOtra.letras.size( ) != letras.size( ) )
        {
            iguales = false;
        }
        else
        {
            int tamano = letras.size( );
            for( int i = 0; i < tamano && iguales; i++ )
            {
                Letra l1 = pOtra.letras.get( i );
                Letra l2 = letras.get( i );
                if( !l1.esIgual( l2 ) )
                {
                    iguales = false;
                }
            }
        }
        return iguales;
    }

  
    public String toString( )
    {
        String cadena = "";
        for( Letra letra : letras )
        {
            cadena += letra.darLetra( );
        }
        return cadena;
    }
}
