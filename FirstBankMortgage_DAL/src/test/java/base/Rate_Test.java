package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

public class Rate_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void rateTest() {
		double r;
			r = RateDAL.getRate(700);
			assertTrue(r == 4);
		
		double x;
			x = RateDAL.getRate(650);
			assertTrue(x == 4.5);
		
		double f;
			f = RateDAL.getRate(500);
		}
	
	@Test
	public void rowTest() {
		ArrayList<RateDomainModel> rates = RateDAL.getRates();
		assertTrue(rates.size() == 5);
	}
	
	

}
