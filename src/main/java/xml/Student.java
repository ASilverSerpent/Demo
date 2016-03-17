package xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private int id;
	private String name;
	private int age;
	private Classroom calssroom;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Classroom getCalssroom() {
		return calssroom;
	}

	public void setCalssroom(Classroom calssroom) {
		this.calssroom = calssroom;
	}

	public Student(int id, String name, int age, Classroom calssroom) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.calssroom = calssroom;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
}
