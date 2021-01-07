package com.educacionit.calculadorados;

public class CalculadoraDos {
	
	private int ans;
	
	public CalculadoraDos() {
		
	}
	
	public int add(int a, int b) {
		ans  = a + b;
		return ans;
	}
	
	public int sub(int a, int b) {
		ans = a - b;
		return ans;		
	}
	
	public int div(int a, int b) { // division que puede generar un error
		if(b == 0) {
			throw new ArithmeticException("no se puede dividir por 0 en numeros reales");
		}
		ans = a / b;
		return ans;
	}
	
	public int sub(int v) {
		ans -= v;
		return ans;
	}
	
	public int ans() {
		return ans;
	}
	
	public void clear() {
		ans = 0;
	}
	
	public void operacionOptima() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
