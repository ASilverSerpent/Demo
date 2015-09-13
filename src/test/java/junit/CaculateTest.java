package junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CaculateTest {
	private static Calculate calculate ;

	@BeforeClass
	public static void init() {
		calculate = new Calculate();
	}
	@AfterClass
	public static void destory() {
		
	}
	
	@Before
	public void before() {
		
	}
	@After
	public void after() {
		
	}
	
	@Test
	public void testAdd() {
		assertEquals(6, calculate.add(2, 4));
	}
	
	@Test
	@Ignore //忽略此方法
	public void testSubtract() {
		assertEquals(3, calculate.subtract(5, 2));
	}
	
	@Test(timeout = 1000)
	public void testMultiply() {
		assertEquals(4, calculate.multiply(2, 2));
	}
	
	@Test(expected = ArithmeticException.class)
	public void testDivide() {
		assertEquals(4, calculate.divide(8, 0));
	}
}
