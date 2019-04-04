/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 *
 * @author Darwin W. Diaz Simon
 */
public class SopaDeLetras {
   
    private Letra[][] letras;

   
    private ArrayList<Palabra> diccionario;

    
    private ArrayList<Palabra> encontradas;

   
    public SopaDeLetras( File pArchivoSopa ) throws Exception
    {
        cargarSopa( pArchivoSopa );
        encontradas = new ArrayList<Palabra>( );
    }

    
    public int darMaxY( )
    {
        return letras[ 0 ].length;
    }

   
    public Letra[][] darLetras( )
    {
        return letras;
    }

    
    public int darNumeroTotalPalabras( )
    {
        return diccionario.size( );
    }

  
    public int darMaxX( )
    {
        return letras.length;
    }

    
    private void cargarSopa( File pArchivoSopa ) throws Exception
    {
        // Cargar las letras del archivo de propiedades
        Properties infoSopa = new Properties( );
        InputStream inputLetras = new FileInputStream( pArchivoSopa );
        infoSopa.load( inputLetras );

        // Cargar las letras
        int columnas = Integer.parseInt( infoSopa.getProperty( "sopaDeLetras.columnas" ) );
        int filas = Integer.parseInt( infoSopa.getProperty( "sopaDeLetras.filas" ) );

        letras = new Letra[columnas][filas];

        for( int i = 1; i <= filas; i++ )
        {
            String cadenaLetras = infoSopa.getProperty( "sopaDeLetras.fila" + i );
            StringTokenizer st = new StringTokenizer( cadenaLetras, " " );
            for( int j = 1; j <= columnas; j++ )
            {
                String strLetra = st.nextToken( );
                Letra letra = new Letra( strLetra );
                letras[ j - 1 ][ i - 1 ] = letra;
            }
        }

        // Cargar el diccionario del archivo de propiedades
        int palabras = Integer.parseInt( infoSopa.getProperty( "sopaDeLetras.numPalabras" ) );

        diccionario = new ArrayList<Palabra>( );
        for( int i = 1; i <= palabras; i++ )
        {
            String palabra = infoSopa.getProperty( "sopaDeLetras.palabra" + i );
            Palabra pal = new Palabra( palabra );
            diccionario.add( pal );
        }
    }

   
    public String buscarPalabra( int pX1, int pY1, int pX2, int pY2 ) throws Exception
    {
        // Verificar que las coordenadas están dentro de la sopa de letras
        if( pX1 < 1 || pY1 < 1 || pX1 > darMaxX( ) || pY1 > darMaxY( ) )
        {
            throw new Exception( "La posición indicada no se encuentra dentro de la sopa de letras" );
        }

        if( pX2 < 1 || pY2 < 1 || pX2 > darMaxX( ) || pY2 > darMaxY( ) )
        {
            throw new Exception( "La posición indicada no se encuentra dentro de la sopa de letras" );
        }

        // Verificar que las posiciones formen una línea recta
        boolean horizontal = false, vertical = false, diagonal = false;

        if( pY1 == pY2 && pX1 != pX2 )
        {
            horizontal = true;
        }
        else if( pX1 == pX2 && pY1 != pY2 )
        {
            vertical = true;
        }
        else if( Math.abs( pX1 - pX2 ) == Math.abs( pY1 - pY2 ) )
        {
            diagonal = true;
        }

        if( !horizontal && !vertical && !diagonal )
        {
            throw new Exception( "Las posiciones deben formar una línea recta" );
        }

        // Comparar la palabra con el diccionario a ver si existe o si ya ha sido encontrada
        Palabra nuevaPalabra = darPalabra( pX1, pY1, pX2, pY2 );
        boolean encontroPalabra = false;

        for( int i = 0; i < darNumeroTotalPalabras( ) && !encontroPalabra; i++ )
        {
            Palabra pal = diccionario.get( i );
            if( pal.sonIguales( nuevaPalabra ) && !fueEncontrada( pal ) )
            {
                encontradas.add( pal );
                encontroPalabra = true;
            }
        }

        String strPalabra = null;
        if( encontroPalabra )
        {
            strPalabra = nuevaPalabra.toString( );
            marcarLetras( pX1, pY1, pX2, pY2 );
        }

        return strPalabra;
    }

   
    private Palabra darPalabra( int pX1, int pY1, int pX2, int pY2 )
    {
        int cuantasLetras = Math.max( Math.abs( pX1 - pX2 ), Math.abs( pY1 - pY2 ) ) + 1;

        int deltaX = 1;
        if( pX2 < pX1 )
        {
            deltaX = -1;
        }
        else if( pX1 == pX2 )
        {
            deltaX = 0;
        }

        int deltaY = 1;
        if( pY2 < pY1 )
        {
            deltaY = -1;
        }
        else if( pY1 == pY2 )
        {
            deltaY = 0;
        }

        ArrayList<Letra> listaLetras = new ArrayList<Letra>( );

        int x = pX1;
        int y = pY1;
        int numLetra = 0;

        while( numLetra < cuantasLetras )
        {
            Letra l = letras[ x - 1 ][ y - 1 ];
            listaLetras.add( l );

            numLetra++;
            x += deltaX;
            y += deltaY;
        }
        return new Palabra( listaLetras );
    }

  
    private void marcarLetras( int pX1, int pY1, int pX2, int pY2 )
    {
        int cuantasLetras = Math.max( Math.abs( pX1 - pX2 ), Math.abs( pY1 - pY2 ) ) + 1;

        int deltaX = 1;
        if( pX2 < pX1 )
        {
            deltaX = -1;
        }
        else if( pX1 == pX2 )
        {
            deltaX = 0;
        }

        int deltaY = 1;
        if( pY2 < pY1 )
        {
            deltaY = -1;
        }
        else if( pY1 == pY2 )
        {
            deltaY = 0;
        }

        int x = pX1;
        int y = pY1;
        int numLetra = 0;

        while( numLetra < cuantasLetras )
        {
            Letra l = letras[ x - 1 ][ y - 1 ];
            l.marcar( );

            numLetra++;
            x += deltaX;
            y += deltaY;
        }
    }

  
    private boolean fueEncontrada( Palabra pPalabra )
    {
        boolean encontrada = false;

        int cuantos = encontradas.size( );
        for( int i = 0; i < cuantos && !encontrada; i++ )
        {
            Palabra pal2 = encontradas.get( i );
            if( pPalabra.sonIguales( pal2 ) )
            {
                encontrada = true;
            }
        }
        return encontrada;
    }

  
    public boolean gano( )
    {
        return darNumeroPalabrasRestantes( ) == 0;
    }

    
    public int darNumeroPalabrasRestantes( )
    {
        int numeroPalabrasRestantes = diccionario.size( ) - encontradas.size( );
        return numeroPalabrasRestantes;
    }

   
    public String metodo1( )
    {
        return "Respuesta 1";
    }

   
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}
