/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Alessandro
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String argv[]) {
        BufferedReader in = null;
        PrintStream out = null;
        Socket socket = null;
        String message = "";
        Scanner s = new Scanner(System.in);
        boolean condition = false;
        
        System.out.println("nome utente: ");
        String username = s.nextLine();
        try {
            // open a socket connection
            socket = new Socket("129.152.16.174", 9999);
            // Apre i canali I/O
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
            // Legge dal server
            //aspetta che ci sia almeno un altro client collegato al server
            
            out.println("setname "+username);
            
            /*while (!condition) {
                System.out.println("waiting...");
                condition = in.readLine().equalsIgnoreCase("started");
            }*/

            System.out.println("conversation started");
            //chiede all'utente la porta del client con cui vuole parlare(tutto questo è stato testato in locale dallo stesso computer)
            //viene creato un thread che gestirà i messaggi in ingresso senza bloccare la possibilità di mandare messaggi
            new ClientThread(in);
            //inizia a mandare messaggi all'altro client fino a quando non scrive termina
            while (condition) {
                System.out.print("insert messages: ");
                message = s.nextLine();
                condition = !message.equalsIgnoreCase("termina");
                out.println(message);
            }
            s.close();
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
    }
    
}
