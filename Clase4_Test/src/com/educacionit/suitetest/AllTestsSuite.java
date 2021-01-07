package com.educacionit.suitetest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.educacionit.calculadora.CalculadoraTest;
import com.educacionit.calculadorados.CalculadoraDosTest;
import com.educacionit.testparametrizados.CalculadoraParametroTest;

@RunWith(Suite.class)
@SuiteClasses({
	CalculadoraTest.class,
	CalculadoraDosTest.class,
	CalculadoraParametroTest.class
})
public class AllTestsSuite {

}
