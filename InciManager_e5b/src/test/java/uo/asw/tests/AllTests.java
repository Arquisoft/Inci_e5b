package uo.asw.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uo.asw.tests.business.PruebaJSON2String;
import uo.asw.tests.cucumber.CucumberTest;

@RunWith(Suite.class)
@SuiteClasses({
	
	PruebaJSON2String.class,
	CucumberTest.class
	
})
public class AllTests { }