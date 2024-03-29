package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name", length = 50)
	private String firstName;
	
	@Column(
			name = "last_name",
			length = 60,
			nullable = false
		)
	private String lastName;
	
	@Column(length = 1000)
	private String note;

	@Column(nullable = false)
	private int age;
	//    • age – Non-negative number. Required
	
	@Column(name = "magic_wand_creator", length =  100)
	private String magicWandCreator;

	@Column(name = "magic_wand_size")
	private int magicWandSize;

	@Column(name = "deposit_group", length = 20)
	private String depositGroup;
	
	@Column(name = "deposit_start_date")
	private Date depositStartDate;

	@Column(name = "deposit_amount")
	private double depositAmount;
	
	@Column(name = "deposit_interest")
	private double depositInterest;

	@Column(name = "deposit_charge")
	private double depositCharge;
	
	@Column(name = "deposit_expiration_date")
	private Date depositExpirationDate;

	@Column(name = "is_deposit_expired")
	private boolean isDepositExpired;

	public WizardDeposits() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws Exception {
		if (age > 0) {
			this.age = age;
		} else {
			throw new Exception();
		}
	}

	public String getMagicWandCreator() {
		return magicWandCreator;
	}

	public void setMagicWandCreator(String magicWandCreator) {
		this.magicWandCreator = magicWandCreator;
	}

	public int getMagicWandSize() {
		return magicWandSize;
	}

	public void setMagicWandSize(int magicWandSize) {
		this.magicWandSize = magicWandSize;
	}

	public String getDepositGroup() {
		return depositGroup;
	}

	public void setDepositGroup(String depositGroup) {
		this.depositGroup = depositGroup;
	}

	public Date getDepositStartDate() {
		return depositStartDate;
	}

	public void setDepositStartDate(Date depositStartDate) {
		this.depositStartDate = depositStartDate;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public double getDepositInterest() {
		return depositInterest;
	}

	public void setDepositInterest(double depositInterest) {
		this.depositInterest = depositInterest;
	}

	public double getDepositCharge() {
		return depositCharge;
	}

	public void setDepositCharge(double depositCharge) {
		this.depositCharge = depositCharge;
	}

	public Date getDepositExpirationDate() {
		return depositExpirationDate;
	}

	public void setDepositExpirationDate(Date depositExpirationDate) {
		this.depositExpirationDate = depositExpirationDate;
	}

	public boolean isDepositExpired() {
		return isDepositExpired;
	}

	public void setDepositExpired(boolean isDepositExpired) {
		this.isDepositExpired = isDepositExpired;
	}
}