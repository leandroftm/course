package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		try (Scanner sc = new Scanner(System.in)) {

			Locale.setDefault(Locale.US);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			System.out.println("Enter contract data");
			System.out.print("Number: ");
			int number = sc.nextInt();
			sc.nextLine();

			System.out.print("Date (dd/MM/yyyy): ");
			Date date = sdf.parse(sc.nextLine());

			System.out.print("Contract value: ");
			double contractValue = sc.nextDouble();

			System.out.print("Enter the number of installments: ");
			int installments = sc.nextInt();

			ContractService contractService = new ContractService(new PaypalService());
			Contract contract = new Contract(number, date, contractValue);
			contractService.processContract(contract, installments);

			System.out.println("Installments:");
			for (Installment installment : contract.getInstallment()) {
				System.out.println(installment);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
