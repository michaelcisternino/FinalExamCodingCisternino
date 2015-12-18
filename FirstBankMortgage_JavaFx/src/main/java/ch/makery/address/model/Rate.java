package ch.makery.address.model;

import domain.RateDomainModel;
import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;

import java.util.Scanner;

public class Rate extends RateDomainModel {
	
	public double getPayment(int term, int credScore, double houseCost) {
	{
		boolean t;
		double f = 0;
		double PV;
		double r;
		double x = RateDAL.getRate(credScore) * .01;
		double n = term * 1.0;
		
		double PMT;
		//pmt is a loan formula from FinanceLib
		PV = houseCost;
		r = x / 12;
		n = n * 12;
		t = false;
		
		PMT = FinanceLib.pmt(r,  n,  PV, f, t);
		return (PMT * -1);
		
	}
}
}
