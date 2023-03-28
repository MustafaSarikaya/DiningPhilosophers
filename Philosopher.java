
public class Philosopher extends Thread {
	private GraphicTable table;
	private Chopstick left;
	private Chopstick right;
	private int ID;
	final int timeThink_max = 5000;
	final int timeNextFork = 100;
	final int timeEat_max = 5000;

	
	Philosopher(int ID, GraphicTable table, Chopstick left, Chopstick right) {
		this.ID = ID;
		this.table = table;
		this.left = left;
		this.right = right;
		setName("Philosopher "+ID);
	}
	
	public void run() {
		try {
			while(true){
				// Tell the table GUI that I am thinking
				table.isThinking(ID);
				// Print to console that I am thinking
				System.out.println(getName()+" thinks");
				
				// Let the thread sleep (in order to simulate thinking time)
				
				sleep((long)(Math.random()*timeThink_max));
				
				// Done with thinking
				System.out.println(getName()+" finished thinking"); 
				
				// and now I am hungry!
				System.out.println(getName()+" is hungry"); 
				// Tell the GUI I am hungry...
				table.isHungry(ID);
				
				if (this.ID % 2 == 1) {
					getInLineOdd();
				} else {
					getInLineEven();
				} 
			} 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void getInLineEven() throws InterruptedException {
		try {
			// Let's try to get the right chopstick
			System.out.println(getName()+" wants left chopstick");
			if(right.take()) {
				// Tell the GUI that I took the right chopstick
				table.takeChopstick(ID, right.getID());
				System.out.println(getName()+" got right chopstick");

				// Let's try to get the left chopstick
				if (left.take()){
					// Got it!
					table.takeChopstick(ID, left.getID());
					System.out.println(getName()+" got left chopstick");
					
					// I'll eat now
					eat();

					// I'll release the right chopstick
					table.releaseChopstick(ID, left.getID());
					left.release();
					System.out.println(getName()+" released left chopstick");
				}
				// I'll release the left chopstick
				table.releaseChopstick(ID, right.getID());
				right.release();
				System.out.println(getName()+" released right chopstick");
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getInLineOdd() throws InterruptedException {
		try {
			// Let's try to get the left chopstick
			System.out.println(getName()+" wants left chopstick");
			if(left.take()) {
				// Tell the GUI that I took the left chopstick
				table.takeChopstick(ID, left.getID());
				System.out.println(getName()+" got left chopstick");

				// Let's try to get the right chopstick
				if (right.take()){
					// Got it!
					table.takeChopstick(ID, right.getID());
					System.out.println(getName()+" got right chopstick");
					
					// I'll eat now
					eat();

					// I'll release the right chopstick
					table.releaseChopstick(ID, right.getID());
					right.release();
					System.out.println(getName()+" released right chopstick");
				}
				// I'll release the left chopstick
				table.releaseChopstick(ID, left.getID());
				left.release();
				System.out.println(getName()+" released left chopstick");
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void eat() throws InterruptedException {
		try {
		// Sweet taste of steamed rice....
		System.out.println(getName()+" eats");
		
		sleep((long)(Math.random()*timeEat_max));
		
		// Ok, I am really full now
		System.out.println(getName()+" finished eating");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
