package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

import com.assignment.expense.entity.Expense;

public class DatePredicate implements Predicate<Expense> {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	private int month;
	private Date fromDate, toDate;
	
	public DatePredicate(int month) {
//		this.month = month;
	}

	public DatePredicate(String fromDate, String toDate) /* throws ParseException */ {
		try {
			this.fromDate = sdf.parse(fromDate);
			this.toDate = sdf.parse(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean test(Expense expense) {
		Date date = expense.getDate();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Calendar from = Calendar.getInstance();
		from.setTime(fromDate);
		Calendar to = Calendar.getInstance();
		to.setTime(toDate);
		return (c.after(from) || c.equals(from)) && (c.before(to) || c.equals(to));
	}

}
