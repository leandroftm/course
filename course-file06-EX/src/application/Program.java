package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		// F:\temp\ws.eclipse\course-java\course\course-file06-EX\src\source.csv
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<Product>();

		System.out.println("Enter file path: ");
		File path = new File(sc.nextLine());
		System.out.println();

		String folderPath = path.getParent();
		boolean file = new File(folderPath + "\\out").mkdir();

		if (!file) {
			
			System.out.println("Error on the file path.");

		} else {
			folderPath += "\\out\\sumary.csv";

			try (BufferedReader br = new BufferedReader(new FileReader(path))) {
				String line = br.readLine();

				while (line != null) {
					String[] products = line.split(",");
					list.add(new Product(products[0], Double.parseDouble(products[1]), Integer.parseInt(products[2])));
					line = br.readLine();
				}

				try (BufferedWriter bw = new BufferedWriter(new FileWriter(folderPath))) {
					System.out.println("Products list: ");
					for (Product product : list) {
						bw.write(product.totalPrice());
						System.out.println(product.totalPrice());
						bw.newLine();
					}
					System.out.println();
					System.out.println("File created at: " + folderPath);

				} catch (IOException e) {
					System.out.println("Error while writing file: " + e.getMessage());
				}

			} catch (IOException e) {
				System.out.println("Error while reading file: " + e.getMessage());
			}
		}

		sc.close();

	}

}
