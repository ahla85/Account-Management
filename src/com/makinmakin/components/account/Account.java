package com.makinmakin.components.account;

import java.time.Year;
import java.util.HashMap;
import java.util.ArrayList;

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
		// setDate(null);
		// setMonth(null);
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
		// setDate(account.getDate());
		// setMonth(account.getMonth());
		setYears(account.getUserName());
	}
	
	public Account(JSONObject jSONObject) {
		setUserName((String) jSONObject.get(USER_NAME_KEYWORD));
		setServices((ArrayList<String>) jSONObject.get(SERVICES_KEYWORD));
		setDescription((String) jSONObject.get(DESCRIPTION_KEYWORD));
		setNickDescription((String) jSONObject.get(NICK_DESCRIPTION_KEYWORD));
		setEmail((String) jSONObject.get(EMAIL_KEYWORD));
		setRecoveryAccount((String) jSONObject.get(RECOVERY_ACCOUNT_KEYWORD));
		setPassword((String) jSONObject.get(PASSWORD_KEYWORD));
		setNumberPhone((String) jSONObject.get(NUMBER_PHONE_KEYWORD));
		// setDate((Object) jSONObject.get(DATE_KEYWORD));
		// setMonth((Object) jSONObject.get(MONTH_KEYWORD));
		setYears(String.valueOf(jSONObject.get(YEARS_KEYWORD)));
	}


	/* --> Setters <-- */

	public void set(String key, Object value) {
		if (key != null) {
			switch (key) {
				case EMAIL_KEYWORD:
					setEmail((String) value);
					break;
				// case DATE_KEYWORD:
				// 	setDate((Object) value);
					// break;
				case NICK_DESCRIPTION_KEYWORD:
					setNickDescription((String) value);
					break;
				case DESCRIPTION_KEYWORD:
					setDescription((String) value);
					break;
				case USER_NAME_KEYWORD:
					setUserName((String) value);
					break;
				case SERVICES_KEYWORD:
					System.out.println("value = " + value);
					setServices((ArrayList<String>) value);
					break;
				// case MONTH_KEYWORD:
				// 	setMonth((Object) value);
				// 	break;
				case YEARS_KEYWORD:
					setYears((String) value);
					break;
				case NUMBER_PHONE_KEYWORD:
					setNumberPhone((String) value);
					break;
				case PASSWORD_KEYWORD:
					setPassword((String) value);
					break;
				case RECOVERY_ACCOUNT_KEYWORD:
					setRecoveryAccount((String) value);
					break;
				default:
					break;
			}
		}
	}

	public void setEmail(String email) {
		if (email == null || email.equalsIgnoreCase("") || email.length() > 40)
			super.put(EMAIL_KEYWORD, null);
		else
			super.put(EMAIL_KEYWORD, email);
	}

	/**
	 *  Tanggal pemilik akun lahir. 
	 * @param date
	 */
	// public void setDate(Object date) {
	// 	if (date instanceof Long) {
	// 		Long var = (Long) date;
	// 		if ((var >= 1 && var <= 31)) {
	// 			super.put(DATE_KEYWORD, var);
	// 			return;
	// 		}
	// 	}
	// 	super.put(DATE_KEYWORD, null);
	// }

	public void setNickDescription(String nickDescription) {
		if (nickDescription == null || nickDescription.equalsIgnoreCase("") || nickDescription.length() > 80)
			super.put(NICK_DESCRIPTION_KEYWORD, null);
		else
			super.put(NICK_DESCRIPTION_KEYWORD, nickDescription);
	}

	public void setDescription(String description) {
		if (description == null || description.equalsIgnoreCase(""))
			super.put(DESCRIPTION_KEYWORD, null);
		else
			super.put(DESCRIPTION_KEYWORD, description);
	}

	public void setUserName(String userName) {
		if (userName == null || userName.equalsIgnoreCase(""))
			super.put(USER_NAME_KEYWORD, null);
		else
			super.put(USER_NAME_KEYWORD, userName);
	}

	/**
	 *  Daftar layanan yang digunakan untuk login dengan akun ini.
	 */
	public void setServices(ArrayList<String> services) {
		if (services == null || services.size() == 0)
			super.put(SERVICES_KEYWORD, null);
		else
			super.put(SERVICES_KEYWORD, services);
	}

	// public void setMonth(Object month) {
	// 	if (month instanceof Long) {
	// 		Long var = (Long) month;
	// 		if ((var >= 1 && var <= 12)) {
	// 			super.put(MONTH_KEYWORD, var);
	// 			return;
	// 		}
	// 	}
	// 	super.put(MONTH_KEYWORD, null);
	// }

	public void setYears(String years) {
		if (years != null)
			try {
				Year.parse(years);
				super.put(YEARS_KEYWORD, years);
			} catch (Exception e) {
				super.put(YEARS_KEYWORD, null);
			}
		else 
			super.put(YEARS_KEYWORD, null);
	}

	public void setNumberPhone(String numberPhone) {
		if (numberPhone == null || numberPhone.equalsIgnoreCase("") || numberPhone.length() > 20)
			super.put(NUMBER_PHONE_KEYWORD, null);
		else
			super.put(NUMBER_PHONE_KEYWORD, numberPhone);
	}

	public void setPassword(String password) {
		if (password == null || password.equalsIgnoreCase("") || password.length() > 30)
			super.put(PASSWORD_KEYWORD, null);
		else
			super.put(PASSWORD_KEYWORD, password);
	}

	public void setRecoveryAccount(String recoveryAccount) {
		if (recoveryAccount == null || recoveryAccount.equalsIgnoreCase("") || recoveryAccount.length() > 40)
			super.put(RECOVERY_ACCOUNT_KEYWORD, null);
		else
			super.put(RECOVERY_ACCOUNT_KEYWORD, recoveryAccount);
	}


	/* --> Getters <-- */

	public String getNickDescription() {
		return (String) super.get(NICK_DESCRIPTION_KEYWORD);
	}

	public String getDescription() {
		return (String) super.get(DESCRIPTION_KEYWORD);
	}

	public String getEmail() {
		return (String) super.get(EMAIL_KEYWORD);
	}

	public String getRecoveryAccount() {
		return (String) super.get(RECOVERY_ACCOUNT_KEYWORD);
	}

	public String getPassword() {
		return (String) super.get(PASSWORD_KEYWORD);
	}

	public String getUserName() {
		return (String) super.get(USER_NAME_KEYWORD);
	}

	public ArrayList<String> getServices() {
		return (ArrayList<String>) super.get(SERVICES_KEYWORD);
	}

	public String getNumberPhone() {
		return (String) super.get(NUMBER_PHONE_KEYWORD);
	}

	// public Long getDate() {
	// 	return (Long) super.get(DATE_KEYWORD);
	// }

	// public Long getMonth() {
	// 	return (Long) super.get(MONTH_KEYWORD);
	// }

	public String getYears() {
		return (String) super.get(YEARS_KEYWORD);
	}


	/* --> Utils <-- */

	public static final String USER_NAME_KEYWORD = "user-name";
	public static final String NICK_DESCRIPTION_KEYWORD = "nick-description";
	public static final String DESCRIPTION_KEYWORD = "descripiton";
	public static final String EMAIL_KEYWORD = "email";
	public static final String RECOVERY_ACCOUNT_KEYWORD = "recovery-account";
	public static final String PASSWORD_KEYWORD = "password";
	public static final String SERVICES_KEYWORD = "services";
	public static final String NUMBER_PHONE_KEYWORD = "number-phone";
	// public static final String DATE_KEYWORD = "date";
	// public static final String MONTH_KEYWORD = "month";
	public static final String YEARS_KEYWORD = "years";
	
	public void print() {
		System.out.printf("%-20s --> %s\n", "Username", this.getUserName());
		System.out.printf("%-20s --> %s\n", "Deskripsi pendek", this.getNickDescription());
		System.out.printf("%-20s --> %s\n", "Deskripsi", this.getDescription());
		System.out.printf("%-20s --> %s\n", "Email", this.getEmail());
		System.out.printf("%-20s --> %s\n", "Services", this.getServices());
		System.out.printf("%-20s --> %s\n", "Penyembuhan", this.getRecoveryAccount());
		System.out.printf("%-20s --> %s\n", "Password", this.getPassword());
		System.out.printf("%-20s --> %s\n", "Nomor HP", this.getNumberPhone());
		// System.out.printf("%-20s --> %d\n", "Tanggal", this.getDate());
		// System.out.printf("%-20s --> %d\n", "Bulan", this.getMonth());
		System.out.printf("%-20s --> %s\n", "Tahun", this.getYears());
	}
}