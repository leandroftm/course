package model.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) throws ParseException {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(contract.getDate());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		double value = contract.getTotalValue() / months;
		
		for(int i = 1; i <= months; i++) {
			
			double amount = onlinePaymentService.paymentFee(onlinePaymentService.interest(value, i));
			
			int day = calendar.get(Calendar.DATE);
			int month = calendar.get(Calendar.MONTH) + i + 1;
			int year = calendar.get(Calendar.YEAR);
			
			Date dueDate = sdf.parse(day + "/" + month + "/" + year);
			
			contract.addInstallment(new Installment(dueDate, amount));
		}
	}
}
