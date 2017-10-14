package com.company;

import com.company.Servidor;

import java.io.*;
import java.net.Socket;

/**
 * Created by B55519 on 13/10/2017.
 */

public class ServidorThread extends Thread
{
    BufferedReader br = null;
    PrintWriter pw = null;
    Socket sock = null;
    String linea;


    public ServidorThread(Socket ss)
    {
        sock = ss;
    }


    public void run()
    {
        try
        {
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pw = new PrintWriter(sock.getOutputStream());
        }
        catch(IOException e)
        {
            System.out.println("Excepcion de I/O: " + e);
        }

        try
        {
            linea = br .readLine();
            while(linea.compareTo("quit") != 0)
            {
                pw.println(linea);
                pw.flush();
                System.out.println("Respuesta: " + linea);
                linea = br.readLine();
            }
        }
        //Hay varios posibles errores
        catch (IOException e)
        {
            linea = this.getName(); //Nombre del thread
            System.out.println("Excepcion de I/O: " + linea + " abortado");
        }
        catch(NullPointerException e)
        {
            linea = this.getName(); //Nombre del thread
            System.out.println("Cliente" + linea + " cerrado");
        }

        finally
        {
            try
            {
                //System.out.println("Cerrando conexion...");
                if (br != null)
                    br.close();

                if (pw != null)
                    pw.close();

                if (sock != null)
                    sock.close();

            }
            catch(IOException ie)
            {
                System.out.println("Error cerrando la conexion");
            }
        }
    }
}
