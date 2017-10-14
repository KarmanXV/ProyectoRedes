package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor
{
    static final int puerto = 5555;

    public static void main(String[] args) throws IOException
    {
        Socket sock = null;
        ServerSocket ss = null;
        System.out.println("Esperando...");

        try
        {
            ss = new ServerSocket(5555); // can also use static final PORT_NUM , when defined
        }
        catch(IOException e)
        {
            System.out.println("Excepcion de I/O: " + e);
        }

        while(true)
        {
            try
            {
                sock = ss.accept();
                System.out.println("Conexion establecida");
                new ServidorThread(sock).start();
            }
            catch(Exception e)
            {
                System.out.println("Excepcion de I/O: " + e);
            }
        }
    }
}
