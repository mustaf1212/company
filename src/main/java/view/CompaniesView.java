package view;

import java.util.List;

import models.Company;

public class CompaniesView {

	private static double count;

	public static String printCompanies(List<Company> companies) {
		StringBuilder result = new StringBuilder();
		for (Company item : companies) {
			print(item, 0, result);
		}
		return result.toString();
	}

	private static void print(Company item, int startIndex, StringBuilder result) {
		startIndex++;
		result.append("id:"+item.getId()+" ");
		result.append(readLine(startIndex));
		result.append(item.getName() + " | " + item.getEstimatedEarnings() + "k | ");
		coutTotalEarnings(item);
		result.append(count + "k<br>\n");
		count = 0;
		if (!item.getSabsidiaryCompanies().isEmpty()) {
			for (Company c : item.getSabsidiaryCompanies()) {
				print(c, startIndex, result);
			}
		}
	}

	private static String readLine(int count) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < count; i++) {
			line.append("-");
		}
		return line.toString();
	}

	public static void coutTotalEarnings(Company item) {
		count += item.getEstimatedEarnings();
		if (!item.getSabsidiaryCompanies().isEmpty()) {
			for (Company c : item.getSabsidiaryCompanies()) {
				coutTotalEarnings(c);
			}
		}
	}
}
