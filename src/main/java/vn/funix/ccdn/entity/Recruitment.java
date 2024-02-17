package vn.funix.ccdn.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="recruitment")
public class Recruitment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="address")
	private String address;
	
	@Column(name="created_at")
	private String createdAt;
	
	@Column(name="description")
	private String description;
	
	@Column(name="experience")
	private String experience;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="rank")
	private String rank;
	
	@Column(name="salary")
	private String salary;
	
	@Column(name="status")
	private int status;
	
	@Column(name="title")
	private String title;
	
	@Column(name="type")
	private String type;
	
	@Column(name="view")
	private int view;
	
	@JoinColumn(name="category_id")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Category category;
	
	@JoinColumn(name="company_id")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Company company;
	
	@Column(name="deadline")
	private String deadline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public Category getCategoryId() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "Recruitment [id=" + id + ", address=" + address + ", createdAt=" + createdAt + ", description="
				+ description + ", experience=" + experience + ", quantity=" + quantity + ", rank=" + rank + ", salary="
				+ salary + ", status=" + status + ", title=" + title + ", type=" + type + ", view=" + view
				+ ", categoryId=" + category.getId() + ", companyId=" + company + ", deadline=" + deadline + "]";
	}
	
	
}
