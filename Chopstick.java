import java.util.concurrent.Semaphore;

public class Chopstick {
	private int ID;
 	private Semaphore semaphore = new Semaphore(1, true);


	Chopstick(int ID) {
		  this.ID = ID;
	
	}
	
	private boolean isTaken = false;

    public synchronized void take() throws InterruptedException {
        while (isTaken) {
            wait();
        }
        isTaken = true;
		semaphore.acquire();
    }

    public synchronized void release() {
        isTaken = false;
        notify();
		semaphore.release();
    }
	    
	public int getID() {
	    return(ID);
	}
}
