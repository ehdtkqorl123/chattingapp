package chatting.paulshin.ca.chattingapp.data.model;

/**
 * Created by paulshin on 2017-03-25.
 */

public class Bill extends Message {
	private String accountNo;
	private double productPrice;
	private double fees;
	private String dueDate;
	private double totalDue;

	public Bill(int id, String text, long timestamp, String accountNo, double productPrice, double fees, String dueDate, double totalDue) {
		super(id, text, timestamp, false);
		this.accountNo = accountNo;
		this.productPrice = productPrice;
		this.fees = fees;
		this.dueDate = dueDate;
		this.totalDue = totalDue;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public double getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(double totalDue) {
		this.totalDue = totalDue;
	}
}
