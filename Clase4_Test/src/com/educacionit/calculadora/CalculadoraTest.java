package com.educacionit.calculadora;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculadoraTest {
	
	@Test
	public void testSuma() {
		
		int resultado = Calculadora.suma(2, 3);
		int esperado = 5;
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testResta() {
		int resultado = Calculadora.resta(3, 2);
		int esperado = 1;
		assertEquals(esperado, resultado);
	}

}
