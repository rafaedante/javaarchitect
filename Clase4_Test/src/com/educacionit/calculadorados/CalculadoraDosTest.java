package com.educacionit.calculadorados;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculadoraDosTest {
	
	static CalculadoraDos calc;
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("   BeforeClass()   ");
		calc = new CalculadoraDos();
	}
	
	@Before
	public void before() {
		System.out.println("   Before()   ");
		calc = new CalculadoraDos(); // no forma parte de la prueba es una precondicion para ejecutar el test
	}
	
	@After
	public void after() {
		System.out.println("   After()   ");
		calc.clear();
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("   AfterClass()   ");
		calc.clear();
	}
	
	@Test
	public void testAnsSuma() {
		System.out.println("   Ejecutando test: TestAnsSuma()   ");
		int result = calc.add(3, 2);
		assertEquals(5, result);
	}
	
	@Test
	public void testDiv() {
		System.out.println("   Ejecutando test: TestAnsDivision()   ");
		int result = calc.div(6, 2) ;
		assertEquals(3, result);
	}
	
	@Test(expected = ArithmeticException.class)
	public void testDivPorCero() {
		System.out.println("   Ejecutando test: TestDivisionPorCero()   ");
		int result = calc.div(6, 0) ;
	}
	
	@Test(timeout = 2000)
	public void testAlgoritmoOptimo() {
		System.out.println("   Ejecutando test: TestAlgoritmoOptimo()   ");
		calc.operacionOptima();
	}

}
