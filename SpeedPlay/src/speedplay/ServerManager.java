/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package speedplay;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Tichnut
 */
public class ServerManager extends Thread {

    public LockMutex myLock;

    private Vector<ThreadHandler> listOfClients = new Vector<>();
    private int counter = 0;
    Socket socket;
    ServerSocket serverSocket;
    int port;
    private static ServerManager serverManager = null;
    CreateQuestionRand createQuestionRand;
    ArrayList<Player> playerList;
    private boolean isFinishEnterPlay = false;
    private boolean isFinishPlay = false;

    //public static ReentrantLock locK;
    // private ManegerStartFram manegerStartFram;
    // private  ShareadLock shareadLock;
    public void setIsFinishEnterPlay(boolean isFinishEnterPlay) {
        this.isFinishEnterPlay = isFinishEnterPlay;

    }

    public ServerManager(int port) {
        try {
            //shareadLock=new ShareadLock();
            this.port = port;
            playerList = new ArrayList<>();
            serverSocket = new ServerSocket(port);
            createQuestionRand = CreateQuestionRand.getCreateQuestionRand();
            myLock = new LockMutex();
            //manegerStartFram=new ManegerStartFram();
            //manegerStartFram.show(true);

            start();
        } catch (IOException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setIsFinishPlay(boolean isFinishPlay) {
        this.isFinishPlay = isFinishPlay;
    }

    public static ServerManager getServerSocket(int port) {
        if (serverManager == null) {
            serverManager = new ServerManager(port);
        }
        return serverManager;
    }

    public synchronized void SendNotfiy() {
        notify();

    }

    @Override
    public void run() {
        //Timer timer = new Timer();
        //timer.start();
        Thread netWorkClient = new Thread(() -> {
            try {
                while (!isFinishEnterPlay) {
                    sleep(1000);
                }
                myLock.Lock();
                while (!isFinishPlay) {
                    System.out.println("playerList: " + playerList);
                    //listOfClients.forEach(c -> c.sendPlayer(playerList));
                    int Pointwinner = playerList.stream().max((p1, p2) -> p1.getPoints() - p2.getPoints()).get().getPoints();
                    String winner = "";
                   // playerList.stream().filter(p -> p.getPoints() == Pointwinner).map(p -> p.getName() + "\n").collect(Collectors.toList());

                    for (Player p : playerList) {
                        if (p.getPoints() == Pointwinner) {
                            winner += p.getName() + "\n";
                        }

                    }
                    StringBuffer bwinner=new StringBuffer(winner.substring(0, winner.length()-2));
                    listOfClients.forEach(c -> c.sendWinner(bwinner.toString()));
                    
                    Question question = createQuestionRand.getNextQuestion();
                    listOfClients.forEach(c -> c.sendQeustion(question));
                    //  listOfClients.forEach(t -> t.start());
                    //חכה שמישהו יענה תשובה נכונה
                    //או שגויה
//                    while (!myLock.tryLock()) {
//                       sleep(10);
//                    }
                    myLock.Lock();
//                    synchronized (this) {
//                        this.wait();
//                    }
                    playerList = (ArrayList<Player>) listOfClients.stream().map(c -> c.player).collect(Collectors.toList());
                }
                listOfClients.forEach(c -> c.sendFinishPlay());

            } catch (InterruptedException ex) {
                Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        netWorkClient.start();

        while (!isFinishEnterPlay) {
            try {
                socket = serverSocket.accept();
                System.out.println("dgfdgfdf");
                if (isFinishEnterPlay) {
                    break;
                }
                ThreadHandler th = new ThreadHandler(socket, (t) -> {
                    SendNotfiy();
                    return true;
                }, myLock);

                counter++;
                listOfClients.add(th);

                //חכה שהשחקן יגמור להכניס את השם שלו
                //sleep(200);
                playerList.add(th.getPlayer());
            } catch (IOException ex) {
                Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("כל השחקנים נצטרפו למשחק");
            //boolean stop = false;
            //הכנסת השם
            /*while (!stop) {
            try {
                wait();
                //...אם מישהו ישלח סיגנל אחרון במהלך הבדיקה
                lock.lock();
                stop = listOfClients.stream().allMatch(c -> c.IsFinish());
                lock.unlock();
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/

        }
    }
}
