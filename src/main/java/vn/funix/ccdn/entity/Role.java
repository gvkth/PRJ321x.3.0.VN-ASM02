package vn.funix.ccdn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	
	public static final int ROLE_ADMIN=1;
	public static final int ROLE_USER = 2;
	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="role_name")
	private String roleName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		if(id==ROLE_ADMIN)
			this.roleName = "ADMIN";
		else if (id==ROLE_USER)
			this.roleName = "USER";
		else
			this.roleName = "UNKNOWN";
	}

	public String getRoleName() {
		return roleName;
	}

	public Role(int id) {
		setId(id);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}

	public Role() {
		setId(1);
	}
	
	
	
}
