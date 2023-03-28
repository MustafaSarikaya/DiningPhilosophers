
public class Chopstick {
	private int ID;


	Chopstick(int ID) {
		  this.ID = ID;
	
	}
	
	private boolean isTaken = false;

    public synchronized void take() throws InterruptedException {
        while (isTaken) {
            wait();
        }
        isTaken = true;
    }

    public synchronized void release() {
        isTaken = false;
        notify();
    }
	    
	public int getID() {
	    return(ID);
	}
}
