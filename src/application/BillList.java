package application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

	public class BillList implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private List<Bill> billList;

		public BillList() {
			billList = new ArrayList<Bill>();
		}

		public List<Bill> getBills() {
			return billList;
		}

		public void addBill(Bill b) {
			billList.add(b);
		}

		public void setBillList(List<Bill> billList) {
			this.billList = billList;
		}
		
	}

