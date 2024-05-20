package View;

public class MyThread1 implements Runnable {

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i <= 10; i++) {
			System.out.println("Id: " + Thread.currentThread().getId() + "\tName: " + Thread.currentThread().getName() + "\t Priority: "+ Thread.currentThread().getPriority() + "\t[v]" + i);
		try {
			Thread.sleep(500);
			
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		}
		
	}

}
