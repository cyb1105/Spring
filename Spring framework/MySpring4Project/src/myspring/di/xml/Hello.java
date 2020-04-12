package myspring.di.xml;

import java.util.List;
import java.util.Map;

public class Hello {
	String name;
	Printer printer;
	List<String> names;
	Map<String, Integer> ages;

	public Hello() {
		System.out.println("hello default construct called...");
	}

	public Hello(String name, Printer printer) {
		System.out.println("overloading constructor called...");
		this.name = name;
		this.printer = printer;
	}

	public List<String> getNames() {
		return this.names;
	}

	public void setNames(List<String> list) {
		this.names = list;
	}

	public void setName(String name) {
		System.out.println("hello setName() called.."+name);
		this.name = name;
	}

	public void setPrinter(Printer printer) {
		System.out.println("hello setPrinter() called.." + printer.getClass().getName());
		this.printer = printer;
	}
	
	public void setAges(Map<String, Integer> ages) {
		this.ages = ages;
	}
	public Map<String, Integer> getAges() {
		return ages;
	}

	public String sayHello() {
		return "Hello " + name;
	}

	public void print() {
		this.printer.print(sayHello());
	}

}
