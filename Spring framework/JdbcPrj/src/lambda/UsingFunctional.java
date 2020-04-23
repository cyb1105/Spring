package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class UsingFunctional {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		list.add("java");
		list.add("scalar");
		list.add("python");

		for (String value : list) {
			System.out.println(value);
		}
		
		//anonymous inner class
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String value) {
				System.out.println(value);
				
			}			
		});
		//Lambda식
		list.forEach(val -> System.out.println(val));
		list.forEach(System.out::println);
		//
		List<Student> stuList = List.of(new Student("홍길동", 100), new Student("둘리", 200), new Student("펭수", 300));
		//1
		stuList.forEach(new Consumer<Student>() {
			@Override
			public void accept(Student stu) {
				System.out.println(stu);				
			}			
		});
		//2
		stuList.forEach(stu->System.out.println(stu));
		//void accept<T,t>
		stuList.forEach(stu -> stu.setName("스프링"));
		//3
		stuList.forEach(System.out::println);
		
		
		List<Student> stuList2 = List.of(new Student("자바", 10), new Student("코틀린", 20), new Student("스칼라", 30));
		//List -> Stream 변환
		//학생번호 20보다 큰 학생이름을 List<String> 출력하세요
		Stream<Student> stream = stuList2.stream();
		
		stuList2.stream()  //Stream<Student>
		//boolean  test(T t)
		.filter(stu -> stu.getId() >= 20)
		.map(stu -> stu.getName())
		.forEach(stu -> System.out.println(stu));
	}

}

class Student{
	private String name;
	private int id;
	
	
	
	public Student(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + "]";
	}
	
}









