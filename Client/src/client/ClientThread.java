/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.BufferedReader;

/**
 *
 * @author Alessandro
 */
public class ClientThread extends Thread{
    private BufferedReader in = null;

    public ClientThread(BufferedReader in) {
        this.in = in;
        this.start();
    }

    @Override
    public void run() {
        //questo thread gestisce i messggi che arrivano al client senza metterlo direttamente in attesa
        while (true) {
            try {
                System.out.println(in.readLine());
            } catch (Exception e) {
                System.out.println("error: "+e.getMessage());
            }
        }
    }
}
