package xml;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class JaxbDemo {

	@Test
	public void test1() {
		try {
			JAXBContext ctx = JAXBContext.newInstance(Student.class);
			Marshaller marshaller = ctx.createMarshaller();
			Student stu = new Student(1, "張三", 21, new Classroom(1, "軟件技術", 2008));
			marshaller.marshal(stu, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2(){
		try {
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>21</age><calssroom><grade>2008</grade><id>1</id><name>軟件技術</name></calssroom><id>1</id><name>張三</name></student>";
			JAXBContext ctx = JAXBContext.newInstance(Student.class);
			Unmarshaller um = ctx.createUnmarshaller();
			Student stu = (Student) um.unmarshal(new StringReader(xml));
			System.out.println(stu.getName() + stu.getCalssroom().getName());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
