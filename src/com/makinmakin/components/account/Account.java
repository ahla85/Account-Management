package com.makinmakin.components.account;

import java.time.Year;
import java.util.HashMap;

import com.json.JSONObject;

/**
 * Sebuah kelas yang mendefinsikan sebuah Account. 1. Email 2. Date 3. Nick
 * description 4. Description 5. Username 6. Month 7. Years 8. Numberphone 9.
 * Password 10. Recovery account 11. Services.
 */
public class Account extends HashMap<String, Object> {

	/**
	 * Instance objek Account kosong.
	 */
	public Account() {
		setUserName(null);
		setServices(null);
		setNickDescription(null);
		setDescription(null);
		setEmail(null);
		setRecoveryAccount(null);
		setPassword(null);
		setNumberPhone(null);
		setDate(null);
		setMonth(null);
		setYears(null);
	}
	
	public Account(Account account) {
		setUserName(account.getUserName());
		setServices(account.getServices());
		setNickDescription(account.getNickDescription());
		setDescription(account.getDescription());
		setEmail(account.getEmail());
		setRecoveryAccount(account.getRecoveryAccount());
		setPassword(account.getPassword());
		setNumberPhone(account.getUserName());
		setDate(account.getDate());
		setMonth(account.getMonth());
		setYears(account.getUserName());
	}
	
	public Account(JSONObject jSONObject) {
		setUserName((String) jSONObject.get("user-name"));
		setServices((String) jSONObject.get("services"));
		setDescription((String) jSONObject.get("description"));
		setNickDescription((String) jSONObject.get("nick-description"));
		setEmail((String) jSONObject.get("email"));
		setRecoveryAccount((String) jSONObject.get("recovery-account"));
		setPassword((String) jSONObject.get("password"));
		setNumberPhone((String) jSONObject.get("number-phone"));
		setDate((Long) jSONObject.get("date"));
		setMonth((Long) jSONObject.get("month"));
		setYears(String.valueOf(jSONObject.get("years")));
	}


	/* --> Setters <-- */

	public void set(String key, Object value) {
		if (key != null) {
			switch (key) {
				case "email":
					setEmail((String) value);
					break;
				case "date":
					setDate((Long) value);
					break;
				case "nick-description":
					setNickDescription((String) value);
					break;
				case "descrition":
					setDescription((String) value);
					break;
				case "user-name":
					setUserName((String) value);
					break;
				case "services":
					setServices((String) value);
					break;
				case "month":
					setMonth((Long) value);
					break;
				case "years":
					setYears((String) value);
					break;
				case "number-phone":
					setNumberPhone((String) value);
					break;
				case "password":
					setPassword((String) value);
					break;
				case "recovery-account":
					setRecoveryAccount((String) value);
					break;
				default:
					break;
			}
		}
	}

	public void setEmail(String email) {
		if (email == null || email.equalsIgnoreCase("") || email.length() > 40)
			super.put("email", null);
		else
			super.put("email", email);
	}

	public void setDate(Long date) {
		if (date == null || date < 1 || date > 31)
			super.put("date", null);
		else
			super.put("date", date);
	}

	public void setNickDescription(String nickDescription) {
		if (nickDescription == null || nickDescription.equalsIgnoreCase("") || nickDescription.length() > 80)
			super.put("nick-description", null);
		else
			super.put("nick-description", nickDescription);
	}

	public void setDescription(String description) {
		if (description == null || description.equalsIgnoreCase(""))
			super.put("description", null);
		else
			super.put("description", description);
	}

	public void setUserName(String userName) {
		if (userName == null || userName.equalsIgnoreCase(""))
			super.put("user-name", null);
		else
			super.put("user-name", userName);
	}

	/**
	 *  Daftar layanan yang digunakan untuk login dengan akun ini.
	 */
	public void setServices(String services) {
		if (services == null || services.length() == 0)
			super.put("services", null);
		else
			super.put("services", services);
	}

	public void setMonth(Long month) {
		if (month == null || month < 1 || month > 12)
			super.put("month", null);
		else
			super.put("month", month);
	}

	public void setYears(String years) {
		if (years != null)
			try {
				Year.parse(years);
				super.put("years", years);
			} catch (Exception e) {
				super.put("years", null);
			}
		else 
			super.put("years", null);
	}

	public void setNumberPhone(String numberPhone) {
		if (numberPhone == null || numberPhone.equalsIgnoreCase("") || numberPhone.length() > 20)
			super.put("number-phone", null);
		else
			super.put("number-phone", numberPhone);
	}

	public void setPassword(String password) {
		if (password == null || password.equalsIgnoreCase("") || password.length() > 30)
			super.put("password", null);
		else
			super.put("password", password);
	}

	public void setRecoveryAccount(String recoveryAccount) {
		if (recoveryAccount == null || recoveryAccount.equalsIgnoreCase("") || recoveryAccount.length() > 40)
			super.put("recovery-account", null);
		else
			super.put("recovery-account", recoveryAccount);
	}


	/* --> Getters <-- */

	public String getNickDescription() {
		return (String) super.get("nick-description");
	}

	public String getDescription() {
		return (String) super.get("description");
	}

	public String getEmail() {
		return (String) super.get("email");
	}

	public String getRecoveryAccount() {
		return (String) super.get("recovery-account");
	}

	public String getPassword() {
		return (String) super.get("password");
	}

	public String getUserName() {
		return (String) super.get("user-name");
	}

	public String getServices() {
		return (String) super.get("services");
	}

	public String getNumberPhone() {
		return (String) super.get("number-phone");
	}

	public Long getDate() {
		return (Long) super.get("date");
	}

	public Long getMonth() {
		return (Long) super.get("month");
	}

	public String getYears() {
		return (String) super.get("years");
	}


	/* --> Utils <-- */
	
	public void print() {
		System.out.printf("%-20s --> %s\n", "Username", this.getUserName());
		System.out.printf("%-20s --> %s\n", "Deskripsi pendek", this.getNickDescription());
		System.out.printf("%-20s --> %s\n", "Deskripsi", this.getDescription());
		System.out.printf("%-20s --> %s\n", "Email", this.getEmail());
		System.out.printf("%-20s --> %s\n", "Services", this.getServices());
		System.out.printf("%-20s --> %s\n", "Penyembuhan", this.getRecoveryAccount());
		System.out.printf("%-20s --> %s\n", "Password", this.getPassword());
		System.out.printf("%-20s --> %s\n", "Nomor HP", this.getNumberPhone());
		System.out.printf("%-20s --> %d\n", "Tanggal", this.getDate());
		System.out.printf("%-20s --> %d\n", "Bulan", this.getMonth());
		System.out.printf("%-20s --> %s\n", "Tahun", this.getYears());
	}

	public String toString() {
		System.out.println(super.toString());
		System.exit(1);
		return super.toString();
	}

}