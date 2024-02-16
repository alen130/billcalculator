package application;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private double billTotal;
	private LocalDate movedDate;
	private static int billCount = 1;
	private double FirstgasReading;
	private double FirstelectricityReading;
	private int id;
	public Bill(Customer customer, double billTotal, double FirstgasReading,double FirstelectricityReading,LocalDate movedDate
			) {
		this.customer = customer;
		this.billTotal = billTotal;
		this.FirstgasReading = FirstgasReading;
		this.FirstelectricityReading = FirstelectricityReading;
		this.movedDate = movedDate;
		this.id = billCount++;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getBillAmount() {
		return billTotal;
	}

	public void setBillAmount(double billTotal) {
		this.billTotal = billTotal;
	}

	public LocalDate getmovedDate() {
		return movedDate;
	}

	public void setGeneratedDate(LocalDate movedDate) {
		this.movedDate = movedDate;
	}

	public double getFirstgasReading() {
		return FirstgasReading;
	}

	public void setFirstgasReading(double meterReading) {
		this.FirstgasReading = meterReading;
	}

	public double getFirstelectricityReading() {
		return FirstelectricityReading;
	}

	public void setFirstelectricityReading(double electricityReading) {
		this.FirstelectricityReading = electricityReading;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getBillCount() {
		return billCount;
	}

	public static void setBillCount(int billCount) {
		Bill.billCount = billCount;
	}

	@Override
	public String toString() {
		return "Bill [customer =" + customer.getName() + ",bill = " + billTotal + ",movedDate = "
				+ movedDate + ", id =" + id + " ]";
	}
	
	
	public String printInvoice() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	    String str = "    Here is the bill     \n";
	    System.out.println("     INVOICE     ");
	    str += "Bill Id: " + id + "\n";
	    System.out.println("Bill Id: " + id);
	    str += "Customer Name: " + customer.getName() + "\n";
	    System.out.println("Customer: " + customer.getName());
	    str += "Bill_printed_Date: " + formatter.format(movedDate) + "\n";
	    System.out.println("Date: " + formatter.format(movedDate));

	    long diffInDays = ChronoUnit.DAYS.between(customer.getDateMovedIn(), movedDate);
	    str += "Total number of days: " + diffInDays + "\n";
	    System.out.println("Total number of days: " + diffInDays);

	    DecimalFormat format = new DecimalFormat("0.00");
	    double electricityAmount = FirstelectricityReading * 0.19349;
	    double gasAmount = FirstgasReading * 0.03797;

	    str += "Electricity Amount: £" + format.format(electricityAmount) + "\n";
	    System.out.println("Electricity Amount: £" + format.format(electricityAmount));

	    str += "Gas Amount: £" + format.format(gasAmount) + "\n";
	    System.out.println("Gas Amount: £" + format.format(gasAmount));

	    str += "Total amount to pay: £" + format.format((electricityAmount + gasAmount)) + "\n";
	    System.out.println("Total amount to pay: £" + format.format((electricityAmount + gasAmount)));

	    return str;
	}


}
