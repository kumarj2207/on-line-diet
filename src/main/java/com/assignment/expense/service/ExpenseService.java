package com.assignment.expense.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.assignment.expense.entity.Expense;
import com.assignment.expense.entity.Mode;

//interface In {
//	Employee getEmployee(int id, String name, String country, String gender, String doj, String dob, String dept,
//			double salary);
//}
//
interface ExpenseFactory {
	Expense getExpense(String partyName, double amount, String description, Date date, Mode mode,
						short category);
}

@Service
public class ExpenseService {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
////	static Function<String, Employee> fx = s -> {
////		In i = Employee::new;
////		String[] arr = s.split(",");
////		return i.getEmployee(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[5], arr[6], arr[7],
////				Double.parseDouble(arr[4]));
////	};
//	
	static Function<String, Expense> ex = s -> {
		//System.out.println(s);
		ExpenseFactory ef = Expense::new;
		String[] arr = s.split(",");
		Mode mode = null;
		if (arr[4].trim().equalsIgnoreCase("CSH")) {
			mode = Mode.CSH;
		} else if (arr[4].trim().equalsIgnoreCase("PTM")) {
			mode = Mode.PTM;
		}  else if (arr[4].trim().equalsIgnoreCase("CC")) {
			mode = Mode.CC;
		}
		
		try {
			Date txDate = sdf.parse(arr[3].trim());
			return ef.getExpense(arr[0].trim(), Double.parseDouble(arr[1].trim()), arr[2].trim(), txDate, mode,
					Short.parseShort(arr[5].trim()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	};
//
////	static Function<Employee, Boolean> fx2 = emp -> {
////		return emp.getCountry().equalsIgnoreCase("us");
////	};
//
//	static Function<Employee, String> fx3 = emp -> {
//		return "THAILAND";
//	};
//
//	static BiConsumer<String, List<Employee>> bc1 = (country, emps) -> {
//		StringBuilder sb = new StringBuilder();
//		for (Employee employee : emps) {
//			sb.append(employee.getName().toUpperCase()).append(",");
//		}
//		System.out.println("The employee(s) lives in " + country.toUpperCase() + " is/are " + sb.toString());
//	};
//
//	static BiConsumer<Boolean, List<Employee>> bc2 = (country, emps) -> {
//		StringBuilder sb = new StringBuilder();
//		for (Employee employee : emps) {
//			sb.append(employee.getName().toUpperCase()).append(",");
//		}
//		if (Boolean.TRUE.equals(country)) {
//			System.out.println("The employee(s) lives in US is/are " + sb.toString());
//		} else {
//			System.out.println("The employee(s) NOT lives in US is/are " + sb.toString());
//		}
//	};
//
////	static Predicate<Employee> p = emp -> {
////		return emp.getCountry().equalsIgnoreCase("us");
////	};
//
	public List<Expense> getAllExpense() throws IOException {
		Path path = Paths.get("F:\\docs\\expenses\\expense.csv");
		Stream<String> is = Files.lines(path);
		List<Expense> expenses = is.map(ex).collect(Collectors.toList());
		is.close();
		System.out.println(expenses.size());
		return expenses;
	}
//	
	public static void main(String[] args) throws IOException {
	
	
	FileInputStream file = new FileInputStream(new File("F:\\docs\\expenses\\expense_2021.xls"));
	Workbook workbook = new HSSFWorkbook(file);
	for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
		System.out.println(workbook.getSheetAt(i).getSheetName());
	}
	Sheet sheet = workbook.getSheetAt(1);
	for (int i = 40; i <= sheet.getLastRowNum(); i++) {
		Row row = sheet.getRow(i);
		Cell cell = row.getCell(0);
		System.out.print(cell.getRichStringCellValue().getString());
		cell = row.getCell(1);
		System.out.print(cell.getNumericCellValue());
		cell = row.getCell(2);
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			System.out.print("-");
		 } else {
			 System.out.print((int)cell.getNumericCellValue());	
		}
		
		cell = row.getCell(3);
		System.out.print(cell.getRichStringCellValue().getString());
		cell = row.getCell(4);
		System.out.print(cell.getRichStringCellValue().getString());
		cell = row.getCell(5);
		System.out.println(cell.getRichStringCellValue().getString());
	}
	workbook.close();
//		Path path = Paths.get("F:\\docs\\expenses\\expense.csv");
//		Stream<String> is = Files.lines(path);
//		List<Expense> le = is.map(ex).collect(Collectors.toList());
//		is.close();
//		Predicate<Expense> datePredicate = new DatePredicate(args[0], args[1]);
//		Predicate<Expense> modePredicate = x -> x.getMode() == Mode.CC;
//		Map<Short, Double> m = le.stream().filter(datePredicate.and(modePredicate)).collect(
//				Collectors.groupingBy(Expense::getCategory,
//						              Collectors.summingDouble(Expense::getAmount)));
//		Map<String, Double> m1 = new HashMap<>();
//		for(Entry<Short, Double> entry : m.entrySet()) {
//			m1.put(Category.getCategory(entry.getKey().intValue()), entry.getValue());
//		}
//		m1.forEach((k,v) -> System.out.println(k + " = " + v));
//		double total = 0.0;
//		for (Double amount : m1.values()) {
//			total += amount;
//		}
//		System.out.println("TOTAL = " + total);
//		/*System.out.println(le.stream().filter(dp).collect(
//				Collectors.groupingBy(Expense::getCategory,
//						               Collectors.groupingBy(Expense::getMode,
//						            		   Collectors.summingDouble(Expense::getAmount))
//				)));*/
//		//le.stream().filter(dp).map(x->x.getDate().toString() + " = " + x.getAmount() + " = " + x.getMode()).forEach(System.out::println);
//		
	}
//
}
