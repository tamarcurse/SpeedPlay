/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tichnut
 */
public class Client{
//
//    private int port;
//    ObjectInputStream dataIn;
//    ObjectOutputStream dataOut;
//    String ip;
//    Socket socket;
//  //  Scanner scanner;
//
//    public Client(int port, String ip) throws IOException {
//        this.port = port;
//        this.ip = ip;
//        
//        socket = new Socket(ip, port);
//         loginFram fram=new loginFram(this);
//         fram.show();
//        Thread readThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        dataIn = new ObjectInputStream(socket.getInputStream());
//                        System.out.println("תשובת השרת: " + dataIn.readUTF());
//                    } catch (IOException ex) {
//                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//
//            }
//        });
//        Thread WriteThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        System.out.println("הכנס משפט שאתה רוצה לשלוח לשרת");
//                        dataOut = new ObjectOutputStream(socket.getOutputStream());
//                        //System.out.println("תשובת השרת: " + dataIn.readUTF());
////                        scanner = new Scanner(System.in);
////                        dataOut.writeUTF(scanner.nextLine());
//                    } catch (IOException ex) {
//                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//
//            }
//        });
//        readThread.start();
//        WriteThread.start();
//        
//
//    }

    public static void main(String[] args) {
      loginFram l=new loginFram();
      l.setVisible(true);
    }

    

}
