package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		CarRental carRental = rentalData(sc);
		RentalService rentalService = rentalServiceData(sc);

		invoice(rentalService, carRental);

		sc.close();
	}

	public static CarRental rentalData(Scanner sc) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String model = sc.nextLine();

		System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
		Date start = sdf.parse(sc.nextLine());

		System.out.print("Return (dd/MM/yyyy hh:ss): ");
		Date finish = sdf.parse(sc.nextLine());

		return new CarRental(start, finish, new Vehicle(model));
	}

	public static RentalService rentalServiceData(Scanner sc) {
		System.out.print("Enter price per hour: ");
		Double priceHour = Double.parseDouble(sc.nextLine());

		System.out.print("Enter price per day: ");
		Double priceDay = Double.parseDouble(sc.nextLine());
		
		return new RentalService(priceHour, priceDay, new BrazilTaxService());
	}

	public static void invoice(RentalService rentalService, CarRental carRental) {
		rentalService.processInvoice(carRental);
		
		System.out.println("INVOICE:");
		System.out.print("Basic payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.print("\nTax: " + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.print("\nTotal payment: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));
	}

}
