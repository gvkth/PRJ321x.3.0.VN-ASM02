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

import vn.funix.ccdn.dto.UserDonationDTO;
import vn.funix.ccdn.utilities.Utility;

@Entity
@Table(name = "user_donation")
public class UserDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST )
    @JoinColumn(name = "user_id")
    private User userMakeADonate;

    @ManyToOne(cascade = CascadeType.PERSIST )
    @JoinColumn(name = "donation_id")
    private Donation donationBeDonated;

    @Column(name = "text")
    private String text;

    @Column(name="money")
    private int money;
    
    @Column(name="name")
    private String name;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="created")
    private String created;
    
    @ManyToOne(cascade = CascadeType.PERSIST )
    @JoinColumn(name="status")
    private UserDonationStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return userMakeADonate;
	}

	public void setUser(User user) {
		this.userMakeADonate = user;
	}

	public Donation getDonation() {
		return donationBeDonated;
	}

	public void setDonation(Donation donation) {
		this.donationBeDonated = donation;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	

	public UserDonationStatus getUserDonationStatus() {
		return status;
	}
	
	public String getStatusCode() {
		if(status!=null)
			return status.getStatusCode();
		return "Không xác định";
	}
	
	public int getStatusID() {
		if(status!=null)
			return status.getId();
		return -1;
	}

	public void setStatus(UserDonationStatus status) {
		this.status = status;
	}

	public UserDonation(UserDonationDTO userDonationDTO,User userMakeDonation,Donation donationBeDonated) {
		super();
		setUser(userMakeDonation);
		//setName(userDonationDTO.getFullName());
		setDonation(donationBeDonated);
		setText(userDonationDTO.getText());
		setMoney(userDonationDTO.getMoney());
		setCreated(Utility.getCurrentTimeString());
		setStatus(new UserDonationStatus(UserDonationStatus.STATUS_CHOXACNHAN));
	}

	public UserDonation() {
		super();
		setUser(null);
		//setName("Không biết tên");
		setDonation(null);
		setText("Chưa khởi tạo");
		setMoney(0);
		
	}
	
	

}

