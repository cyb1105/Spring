package lambda;

public class UsingLambda {
	public static void main(String[] args) {
		//1Thread생성
		//1.
		Thread t1 = new Thread(new MyRunnable());
		t1.setName("둘리");
		t1.start();
		
		//2.Thread클래스 생성
		//Anonymous Inner Class 익명 클래스 형태로
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		t2.setName("길동");
		t2.start();
		
		//3.Lambda식 형태로
		Thread t3 = new Thread(()->System.out.println(Thread.currentThread().getName()));
		t3.setName("자바");
		t3.start();
	}

}

class MyRunnable implements Runnable{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		
	}
	
}