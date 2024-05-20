package View;

public class MyThread2 implements Runnable {

    

    public MyThread2(int i) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i <= 10; i++) {
            if (i % 2 != 0) { // Kiểm tra nếu số là lẻ
                System.out.println("Id: " + Thread.currentThread().getId() + "\tName: " + Thread.currentThread().getName() + "\t Priority: " + Thread.currentThread().getPriority() + "\t[v] " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread2(1));
        thread.start();
    }
}
