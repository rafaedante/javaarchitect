package com.educacionit.testparametrizados;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.educacionit.calculadorados.CalculadoraDos;

@RunWith(value = Parameterized.class)
public class CalculadoraParametroTest {
	
	@Parameters
	public static Iterable<Object[]> getData() {
		List<Object[]> obj = new ArrayList<>();
		
		obj.add(new Object[] {3, 1, 4});
		obj.add(new Object[] {3, 2, 5});
		obj.add(new Object[] {3, 3, 6});
		obj.add(new Object[] {23, 1, 24});
		
		return obj;
		
	//	return Arrays.asList(new Object [][] {
	//		{3,1,4,},{3,2,5},{3,3,6},{23,1,24}
	//	});
		
	}
	
	private int a , b, expected;

	public CalculadoraParametroTest(int a, int b, int expected) {
		super();
		this.a = a;
		this.b = b;
		this.expected = expected;
	}
	
	@Test
	public void testAdd() {
		CalculadoraDos calc = new CalculadoraDos();
		int resultado = calc.add(a, b);
		System.out.println("Ejecutando el test con " + a + " + " + b + " = " + resultado);
		assertEquals(expected, resultado);
	}
	
	
	
	
}
