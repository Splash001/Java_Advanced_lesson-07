package ua.lviv.lgs.domain;

import java.sql.Date;

public class BucketOrder {

	private Integer id;
	private Integer customerId;
	private Integer bookId;
	private Date purchaseDate;

	public BucketOrder(int id, int customerId, int bookId, Date purchaseDate) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.bookId = bookId;
		this.purchaseDate = purchaseDate;
	}

	public BucketOrder(int customerId, int bookId, Date date) {
		super();
		this.customerId = customerId;
		this.bookId = bookId;
		this.purchaseDate = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + customerId;
		result = prime * result + id;
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BucketOrder other = (BucketOrder) obj;
		if (bookId != other.bookId)
			return false;
		if (customerId != other.customerId)
			return false;
		if (id != other.id)
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BucketOrder [id=" + id + ", customerId=" + customerId + ", bookId=" + bookId + ", purchaseDate="
				+ purchaseDate + "]";
	}

}