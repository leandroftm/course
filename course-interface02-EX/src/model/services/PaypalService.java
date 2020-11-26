package model.services;

public class PaypalService implements OnlinePaymentService {

	@Override
	public Double paymentFee(Double amount) {

		amount = Double.parseDouble(String.format("%.2f", amount + (amount * 0.02)));
		
		return amount;
	}

	@Override
	public Double interest(Double amount, Integer months) {

		amount = Double.parseDouble(String.format("%.2f", amount + ((amount * 0.01) * months)));

		return amount;
	}

}
