package model.services;

public class PaypalService implements OnlinePaymentService {
	
	private static final double FEE_PERCENTAGE = 0.02;
	private static final double MONTHLY_INTEREST = 0.01;


	@Override
	public Double paymentFee(Double amount) {

		amount = Double.parseDouble(String.format("%.2f", amount + (amount * FEE_PERCENTAGE)));
		
		return amount;
	}

	@Override
	public Double interest(Double amount, Integer months) {

		amount = Double.parseDouble(String.format("%.2f", amount + ((amount * MONTHLY_INTEREST) * months)));

		return amount;
	}

}
