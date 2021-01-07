package com.educacionit.programa;

import javax.swing.JOptionPane;

import com.educacionit.calculadora.Calculadora;

public class AppPrincipal {
	
	public static void main(String[] args) {
		
		int opcionIngresada= 0, num1 = 0, num2 = 0;
		
		do {
			opcionIngresada = Integer.parseInt(JOptionPane.showInputDialog("ingrese la opcion \n 1-Sumar "
					+ "\n 2-Restar"));
		} while (opcionIngresada != 1 && opcionIngresada != 2);		
		
		num1 = Integer.parseInt(JOptionPane.showInputDialog("ingrese el numero 1"));
		num2 = Integer.parseInt(JOptionPane.showInputDialog("ingrese el numero 2"));
		
		if (opcionIngresada == 1) {
			JOptionPane.showMessageDialog(null, "El resultado es " + Calculadora.suma(num1, num2));
		} else {
			JOptionPane.showMessageDialog(null, "El resultado es " + Calculadora.resta(num1, num2));
		}
	}

}
