package View;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Bài tập 1,2 chương 4. lập trình đồng thời");
		System.out.println("Main gọi Thread1 !");
		//------
		callTwoThread();
		//------
		System.out.println("Main kết thúc gọi Thread1 !");
		System.out.println("\n\n------------------Main gọi Thread2 !");
		//------
		callTwoThread2();
		//------
		System.out.println("Main kết thúc gọi Thread2 !");
	}
	static void callone() {
		MyThread1 m1 = new MyThread1();
		m1.run();
	}
	static void callOneWithThread() {
		MyThread1 m1 = new MyThread1();
		Thread t1 = new Thread(m1);
		t1.setName("Thread1 số 1");
		t1.setPriority(Thread.MAX_PRIORITY);
		t1.start();
	}
	static void callTwoThread() {
		MyThread1 m1 = new MyThread1();
		Thread t1 = new Thread(m1);
		t1.setName("Thread1 so 1");t1.setPriority(Thread.MAX_PRIORITY);
		MyThread1 m2 = new MyThread1();
		Thread t2 = new Thread(m2);
		t2.setName("Thread1 so 2");
		t2.setPriority(Thread.MIN_PRIORITY);
		// start 2 Threads
		t1.start();
		t2.start();
	}
	static void callTwoThread2() {
		MyThread2 m1 = new MyThread2(0);
		Thread t1 = new Thread(m1);
		t1.setName("Thread2 số 1");
		t1.setPriority(Thread.MAX_PRIORITY);
		
		MyThread2 m2 = new MyThread2(0);
		Thread t2 = new Thread(m2);
		t2.setName("Thread2 số 1");
		t2.setPriority(Thread.MIN_PRIORITY);
		
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		t2.start();
	}
	
}
