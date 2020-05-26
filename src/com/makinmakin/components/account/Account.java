package com.makinmakin.components.account;

import java.util.HashMap;
import java.util.ArrayList;

import com.makinmakin.utils.AccountComponentsInvalidException;
import com.json.JSONObject;

/**
 * Sebuah kelas yang mendefinsikan sebuah Account. 
 * 1. Email (String)
 * 2. Description (String) 
 * 3. Username (String) 
 * 4. Numberphone (String) 
 * 5. Password (String) 
 * 6. Recovery account (String) 
 * 7. Services (ArrayList<String>).
 * 8. Born (date/month/years (integer))
 */
public class Account extends HashMap<String, Object> {

	/**
	 * Instance objek Account kosong.
	 */
	public Account() {
		try {
			setUserName(null);
			setServices(null);
			setDescription(null);
			setEmail(null);
			setRecoveryAccount(null);
			setPassword(null);
			setNumberPhone(null);
			setBorn(null);
		} catch (AccountComponentsInvalidException ignored) {}
	}
	
	public Account(Account account) throws AccountComponentsInvalidException {
		setUserName(account.getUserName());
		setServices(account.getServices());
		setDescription(account.getDescription());
		setEmail(account.getEmail());
		setRecoveryAccount(account.getRecoveryAccount());
		setPassword(account.getPassword());
		setNumberPhone(account.getUserName());
		setBorn(account.getBorn());
	}
	
	public Account(JSONObject jSONObject) throws AccountComponentsInvalidException {
		setUserName((String) jSONObject.get(USER_NAME_KEYWORD));
		setServices((ArrayList<String>) jSONObject.get(SERVICES_KEYWORD));
		setDescription((String) jSONObject.get(DESCRIPTION_KEYWORD));
		setEmail((String) jSONObject.get(EMAIL_KEYWORD));
		setRecoveryAccount((String) jSONObject.get(RECOVERY_ACCOUNT_KEYWORD));
		setPassword((String) jSONObject.get(PASSWORD_KEYWORD));
		setNumberPhone((String) jSONObject.get(NUMBER_PHONE_KEYWORD));
	}


	/* --> Setters <-- */

	public void set(String key, Object value) throws AccountComponentsInvalidException {
		if (key != null) {
			switch (key) {
				case EMAIL_KEYWORD:
					setEmail((String) value);
					break;
				case DESCRIPTION_KEYWORD:
					setDescription((String) value);
					break;
				case USER_NAME_KEYWORD:
					setUserName((String) value);
					break;
				case SERVICES_KEYWORD:
					ArrayList<String> layanan = new ArrayList<>();
					for (String service : value.toString().split(","))
						layanan.add(service);
					setServices(layanan);
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
				case BORN_KEYWORD:
					setBorn((String) value);
				default:
					break;
			}
		}
	}

	/**
	 *  Mendefinisikan tanggal/bulan/tahun user lahir.
	 */
	public void setBorn(String born) {
		if (born == null || born.equalsIgnoreCase(""))
			super.put(BORN_KEYWORD, null);
		else
			super.put(BORN_KEYWORD, born);	
	}

	public void setEmail(String email) throws AccountComponentsInvalidException {
		if (email == null || email.equalsIgnoreCase("") || email.length() > 40) {
			super.put(EMAIL_KEYWORD, null);
			if (email.length() > 40)
				throw new AccountComponentsInvalidException("Panjang karakter email tidak boleh lebih dari 40 karakter.");
		} else
			super.put(EMAIL_KEYWORD, email);
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

	public void setNumberPhone(String numberPhone) {
		if (numberPhone == null || numberPhone.equalsIgnoreCase("") || numberPhone.length() > 20)
			super.put(NUMBER_PHONE_KEYWORD, null);
		else
			super.put(NUMBER_PHONE_KEYWORD, numberPhone);
	}

	public void setPassword(String password) throws AccountComponentsInvalidException {
		if (password == null || password.equalsIgnoreCase("") || password.length() > 30) {
			super.put(PASSWORD_KEYWORD, null);
			if (password.length() > 30)
				throw new AccountComponentsInvalidException("Panjang karakter password tidak boleh lebih dari 40 karakter.");
		} else
			super.put(PASSWORD_KEYWORD, password);
	}

	public void setRecoveryAccount(String recoveryAccount) throws AccountComponentsInvalidException {
		if (recoveryAccount == null || recoveryAccount.equalsIgnoreCase("") || recoveryAccount.length() > 40) {
			super.put(RECOVERY_ACCOUNT_KEYWORD, null);
			if (recoveryAccount.length() > 40)
				throw new AccountComponentsInvalidException("Panjang karakter akun penyembuhan tidak boleh lebih dari 40 karakter.");
		} else
			super.put(RECOVERY_ACCOUNT_KEYWORD, recoveryAccount);
	}


	/* --> Getters <-- */

	public String getBorn() {
		return (String) super.get(BORN_KEYWORD);
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


	/* --> Utils <-- */

	public static final String USER_NAME_KEYWORD = "user-name";
	public static final String DESCRIPTION_KEYWORD = "descripiton";
	public static final String EMAIL_KEYWORD = "email";
	public static final String RECOVERY_ACCOUNT_KEYWORD = "recovery-account";
	public static final String PASSWORD_KEYWORD = "password";
	public static final String SERVICES_KEYWORD = "services";
	public static final String NUMBER_PHONE_KEYWORD = "number-phone";
	public static final String BORN_KEYWORD = "user-born";
	
	public void print() {
		System.out.printf("%-20s --> %s\n", "Username", this.getUserName());
		System.out.printf("%-20s --> %s\n", "Deskripsi", this.getDescription());
		System.out.printf("%-20s --> %s\n", "Email", this.getEmail());
		System.out.printf("%-20s --> %s\n", "Services", this.getServices());
		System.out.printf("%-20s --> %s\n", "Penyembuhan", this.getRecoveryAccount());
		System.out.printf("%-20s --> %s\n", "Password", this.getPassword());
		System.out.printf("%-20s --> %s\n", "Nomor HP", this.getNumberPhone());
		System.out.printf("%-20s --> %s\n", "Lahir", this.getBorn());
	}
}