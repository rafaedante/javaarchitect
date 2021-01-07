package com.educacionit.asserts;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.educacionit.calculadora.Calculadora;

public class AssertTest {
	
	// JUnit Assert
	
	// Boolean
	//assertTrue(condition)
	//assertFalse(condition)
	
	//Objeto Null
	//assertNull(object)
	//assertNotNull(object)
	
	//objetos identicos
	//assertSame(expected, actual)
	//assertNotSame(expected, actual)
	
	//equals
	//assertEquals(expected, actual)
	
	//Array equals
	//assertArrayEquals(expected, actual)
	
	@Test
	public void test() {
		Calculadora micalculadoraUno = new Calculadora();
		Calculadora micalculadoraDos = new Calculadora();
		
		String st1 = "hola";
		String st2 = "hola";
		
		assertEquals(st1,st2);
		
		//assertSame(micalculadoraUno, micalculadoraDos);
	}
	
	

}
