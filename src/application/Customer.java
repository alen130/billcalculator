package application;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable{
	

		private static final long serialVersionUID = 1L;
	private String name;
		private String address;
		private double electricReading;
		private double gasReading;
		private LocalDate dateMovedIn;
		private int id;
		private static int customerCount = 0;

		public Customer() {
			name = "";
			address = "";
			electricReading = 0.0;
			gasReading = 0.0;
			dateMovedIn = null;
			this.id = customerCount++;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public double getElectricReading() {
			return electricReading;
		}

		public void setElectricReading(double electricReading) {
			this.electricReading = electricReading;
		}

		public double getGasReading() {
			return gasReading;
		}

		public void setGasReading(double gasReading) {
			this.gasReading = gasReading;
		}

		public LocalDate getDateMovedIn() {
			return dateMovedIn;
		}

		public void setDateMovedIn(LocalDate dateMovedIn) {
			this.dateMovedIn = dateMovedIn;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public static int getCustomerCount() {
			return customerCount;
		}

		public static void setCustomerCount(int customerCount) {
			Customer.customerCount = customerCount;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "Customer [name=" + name + ", address=" + address
					+ ", date MovedIn=" + dateMovedIn + ", customerId=" + id + "]";
		}
}
