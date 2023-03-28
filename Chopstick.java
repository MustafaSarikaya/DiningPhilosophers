
public class Chopstick {
	private int ID;


	Chopstick(int ID) {
		  this.ID = ID;
	
	}

	public synchronized boolean tryAcquire(int waitTime) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;
		
		while (elapsedTime < waitTime) {
			if (take()) {
				return true;
			}
			
			long remainingTime = waitTime - elapsedTime;
			this.wait(remainingTime);
			
			elapsedTime = System.currentTimeMillis() - startTime;
		}
		
		return false;
	}
	
	private boolean isTaken = false;

    public synchronized Boolean take() throws InterruptedException {
        while (isTaken) {
            wait();
        }
        isTaken = true;
		return isTaken;				
    }

    public synchronized Boolean release() {
        isTaken = false;
        notify();
		return !isTaken;
    }
	    
	public int getID() {
	    return(ID);
	}
}
