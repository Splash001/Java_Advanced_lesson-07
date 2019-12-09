package ua.lviv.lgs.domain;

public class Journal {
	private Integer id;
	private String journalName;
	private String journalDescription;
	private Double price;
	private String isbn;
	private Integer quantity;

	public Journal(Integer id, String journalName, String journalDescription, Double price, String isbn,
			Integer quantity) {
		this.id = id;
		this.journalName = journalName;
		this.journalDescription = journalDescription;
		this.price = price;
		this.isbn = isbn;
		this.quantity = quantity;
	}

	public Journal(String journalName, String journalDescription, Double price, String isbn, Integer quantity) {
		this.journalName = journalName;
		this.journalDescription = journalDescription;
		this.price = price;
		this.isbn = isbn;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	public String getJournalDescription() {
		return journalDescription;
	}

	public void setJournalDescription(String journalDescription) {
		this.journalDescription = journalDescription;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((journalDescription == null) ? 0 : journalDescription.hashCode());
		result = prime * result + ((journalName == null) ? 0 : journalName.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		Journal other = (Journal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (journalDescription == null) {
			if (other.journalDescription != null)
				return false;
		} else if (!journalDescription.equals(other.journalDescription))
			return false;
		if (journalName == null) {
			if (other.journalName != null)
				return false;
		} else if (!journalName.equals(other.journalName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Journal [id=" + id + ", journalName=" + journalName + ", journalDescription=" + journalDescription
				+ ", price=" + price + ", isbn=" + isbn + ", quantity=" + quantity + "]";
	}
}
