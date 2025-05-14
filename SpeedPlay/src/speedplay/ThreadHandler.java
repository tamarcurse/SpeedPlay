/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package speedplay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tichnut
 */
public class ThreadHandler extends Thread {

    Socket socket;
    ObjectInputStream din;
    ObjectOutputStream dout;
    String name;
    Player player;
    private Question question;
    //private ShareadLock shareadLock;
    private boolean isFinish;
    private Predicate<Object> notfiyFunc;
    private LockMutex myLock;
    

//    public boolean IsFinish() {
//        return isFinish;
//    }
    public ThreadHandler(Socket socket,Predicate<Object> notfiyFunc,LockMutex myLock) throws ClassNotFoundException {
        this.socket = socket;
        this.notfiyFunc=notfiyFunc;
        this.myLock=myLock;
       //this.shareadLock = shareadLock;
        try {
            din = new ObjectInputStream(socket.getInputStream());
            dout = new ObjectOutputStream(socket.getOutputStream());
            this.name = (String) din.readObject();
            System.out.println(this.name + "is worked");
            player = new Player(name, 0);
            start();
        } catch (IOException ex) {
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;
    }

    public Player getPlayer() {
        return player;
    }

    public void sendQeustion(Question question) {
        try {
            InfoFromServer infoFromServer = new InfoFromServer(question, ETypeInfo.question);
            this.question = question;
            dout.writeObject(infoFromServer);
        } catch (IOException ex) {
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    void sendPlayer(ArrayList<Player> playerList) {
//        try {
//            InfoFromServer infoFromServer = new InfoFromServer(playerList, ETypeInfo.players);
//            System.out.println("infoFromServer: " + infoFromServer);
//            dout.writeObject(infoFromServer);
//        } catch (IOException ex) {
//            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
        void sendWinner(String winner) {
            InfoFromServer infoFromServer = new InfoFromServer(winner, ETypeInfo.winner);
        try {
            dout.writeObject(infoFromServer);
        } catch (IOException ex) {
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            //שמירת נתוני השחקן

            //notify();
            //isFinish = true;
            // while (!ServerManager.lock.tryLock());
            //notify();
            //מקבל מספר תשובה
            int res;
            while (true) {
                res = (int) din.readObject();
               
                if (question.getNumRight() == res) {
                    player.addPoint(5);
                    //dout.writeBoolean(true);
                    
                } else {
                    player.subPoint(10);
                    //dout.writeBoolean(false);
                    

                }
               //notfiyFunc.test(null);
              myLock.unLock();
                
            }

        } catch (IOException ex) {
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void sendFinishPlay() {
        InfoFromServer info=new InfoFromServer(null,ETypeInfo.endPlay);
        try {
            dout.writeObject(info);
        } catch (IOException ex) {
            Logger.getLogger(ThreadHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
