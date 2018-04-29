package uo.asw.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uo.asw.tests.business.FilterTest;
import uo.asw.tests.business.RIncidencePTest;
import uo.asw.tests.cucumber.CucumberTest;

@RunWith(Suite.class)
@SuiteClasses({
	
	RIncidencePTest.class
	,FilterTest.class,
	CucumberTest.class
	
})
public class AllTests { }
