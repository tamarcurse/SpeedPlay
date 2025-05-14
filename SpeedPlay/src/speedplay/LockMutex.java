/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package speedplay;

/**
 *
 * @author Tichnut
 */
public class LockMutex {

    int lock;
    public LockMutex() {
        lock=1;
    }
    public synchronized void Lock() throws InterruptedException{
        if(lock==0)
            wait();
        lock=0;
           
    }
    public synchronized void unLock(){
        lock=1;
        notify();
    }

   
    
}
