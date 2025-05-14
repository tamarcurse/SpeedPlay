/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import speedplay.InfoFromServer;

/**
 *
 * @author Tichnut
 */
public class ClientSend {

    private int port;
    ObjectInputStream dataIn;
    ObjectOutputStream dataOut;
    String ip;
    Socket socket;
    //private Thread readThread;
    //private Thread WriteThread;
    private Object objectFromServer;
    //  Scanner scanner;

    public ClientSend(int port, String ip) throws IOException {
        this.port = port;
        this.ip = ip;

        socket = new Socket(ip, port);

        dataOut = new ObjectOutputStream(socket.getOutputStream());
        dataIn = new ObjectInputStream(socket.getInputStream());
        //loginFram fram=new loginFram(this);
        //fram.show();
//        readThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    
//                    
//                    while (true) {
//                        try {
//                            try {
//                                objectFromServer = dataIn.readObject();
//                                
//                                //notify();
//                                //System.out.println("תשובת השרת: " + dataIn.readUTF());
//                            } catch (ClassNotFoundException ex) {
//                                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            
//                        } catch (IOException ex) {
//                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//        });
//         WriteThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        System.out.println("הכנס משפט שאתה רוצה לשלוח לשרת");
//                        
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
        //readThread.start();
        // WriteThread.start();

    }

    public void SendToServer(Object object) {
        
        try {
            dataOut.writeObject(object);
        } catch (IOException ex) {
            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public InfoFromServer ReadFromSever(){
        try {
            return (InfoFromServer) dataIn.readObject();
                    } catch (IOException ex) {
            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public Question getQuestion() {
//        try {
//            wait();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
