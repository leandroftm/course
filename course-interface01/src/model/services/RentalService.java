package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double priceHour;
	private Double priceDay;

	private BrazilTaxService taxService;

	public RentalService(Double priceHour, Double priceDay, BrazilTaxService taxService) {
		this.priceDay = priceDay;
		this.priceHour = priceHour;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();

		double hours = (double) (t2 - t1) / 1000 / 60 / 60;
		double basicPayment;

		if (hours <= 12.0) {
			basicPayment = priceHour * Math.ceil(hours);
		} else {
			basicPayment = priceDay * Math.ceil(hours / 24);
		}

		double tax = taxService.tax(basicPayment);

		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
}
