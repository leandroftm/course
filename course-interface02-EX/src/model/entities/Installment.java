package model.entities;

import java.util.Calendar;
import java.util.Date;

public class Installment {
	
	private Date dueDate;
	private Double amount;
	
	public Installment() {
		
	}

	public Installment(Date dueDate, Double amount) {
		this.dueDate = dueDate;
		this.amount = amount;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return convertDate(getDueDate()) + " - " + getAmount();
	}
	
	private static String convertDate(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int day = calendar.get(Calendar.DATE);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);

		return day + "/" + month + "/" + year;

	}
	
}
