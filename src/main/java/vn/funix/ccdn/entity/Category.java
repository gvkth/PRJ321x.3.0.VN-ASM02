package vn.funix.ccdn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column (name="number_choose")
	private Integer numberChoose;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberChoose() {
		return numberChoose;
	}

	public void setNumberChoose(Integer numberChoose) {
		this.numberChoose = numberChoose;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", numberChoose=" + numberChoose + "]";
	}

	public Category() {
		super();
		numberChoose = 0;
	}
	
	
	
}
