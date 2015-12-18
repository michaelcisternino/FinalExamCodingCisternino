package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.makery.address.model.Rate;
import domain.RateDomainModel;

public class RateBLLTest {

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
		Rate r = new Rate();
		double x = r.getPayment(30, 700, 300000);
		assertEquals(1432.25, x, .1);
	}
	
	

}
