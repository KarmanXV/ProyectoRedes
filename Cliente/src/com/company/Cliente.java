package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente
{

    public static void main(String[] args) throws IOException
    {
        InetAddress direccion = InetAddress.getLocalHost();
        Socket sock = null;
        String linea;
        BufferedReader br = null;
        BufferedReader is = null;
        PrintWriter os = null;

        try
        {
            sock = new Socket(direccion, 5555);
            br = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            os = new PrintWriter(sock.getOutputStream());
        }
        catch (IOException e)
        {
            System.out.println("Excepcion de I/O: " + e);
        }

        System.out.println("Diga algo (con 'quit' sale)");

        String resp;

        try
        {
            linea = br.readLine();

            while(linea.compareTo("quit") != 0)
            {
                os.println(linea);
                os.flush();
                resp = is.readLine();
                System.out.println("Respuesta: " + resp);
                linea = br.readLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Excepcion de I/O: " + e);
        }

        //Solo para terminar
        is.close();
        os.close();
        br.close();
        sock.close();
        System.out.println("Conexion cerrada");
    }
}
