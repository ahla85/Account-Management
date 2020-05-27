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
 * 8. Born (date/month/years (String))
 */
public class Account extends HashMap<String, Object> {

	/**
	 * Instance objek Account kosong.
	 */
	public Account() {
		for (Account.Key key : Account.Key.values())
			this.put(key.getKey(), null);
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
		setUserName((String) jSONObject.get(Key.USER_NAME_KEYWORD.getKey()));
		setServices((ArrayList<String>) jSONObject.get(Key.SERVICES_KEYWORD.getKey()));
		setDescription((String) jSONObject.get(Key.DESKRIPTION_KEYWORD.getKey()));
		setEmail((String) jSONObject.get(Key.EMAIL_KEYWORD.getKey()));
		setRecoveryAccount((String) jSONObject.get(Key.RECOVERY_ACCOUNT_KEYWORD.getKey()));
		setPassword((String) jSONObject.get(Key.PASSWORD_KEYWORD.getKey()));
		setNumberPhone((String) jSONObject.get(Key.NUMBER_PHONE_KEYWORD.getKey()));
		setBorn((String) jSONObject.get(Key.USER_BORN_KEYWORD.getKey()));
	}


	/* --> Setters <-- */

	public void set(String key, Object value) throws AccountComponentsInvalidException {
		if (key != null)
			for (Key kunci : Account.Key.values()) {
				this.put(key, value);
			}
	}

	/**
	 *  Mendefinisikan tanggal/bulan/tahun user lahir (bersifat opsional).
	 */
	public void setBorn(String born) {
		this.put(Key.USER_BORN_KEYWORD.getKey(), born);	
	}

	/**
	 *  Set alamat email (bersifat wajib).
	 * 
	 * @exception AccountComponentsInvalidException  Apabila panjang email pada parameter lebih dari 40 karakter.
	 * 												Atau jika email isinya adalah null.
	 */
	public void setEmail(String email) throws AccountComponentsInvalidException {
		if (email == null || email.equalsIgnoreCase("")) {
			this.put(Key.EMAIL_KEYWORD.getKey(), null);
			throw new AccountComponentsInvalidException("Email tidak boleh null.");
		}
		
		if (email.length() > 40) {
			this.put(Key.EMAIL_KEYWORD.getKey(), null);
			throw new AccountComponentsInvalidException("Panjang karakter email tidak boleh lebih dari 40 karakter.");
		}

		this.put(Key.EMAIL_KEYWORD.getKey(), email);
	}

	/**
	 *  Set deskripsi akun (bersifat opsional).
	 */
	public void setDescription(String description) {
		this.put(Key.DESKRIPTION_KEYWORD.getKey(), description);
	}

	/**
	 * Set usrname akun (bersifat opsional).
	 */
	public void setUserName(String userName) {
		this.put(Key.USER_NAME_KEYWORD.getKey(), userName);
	}

	/**
	 *  Daftar layanan yang digunakan untuk login dengan akun ini (bersifat opsional).
	 */
	public void setServices(ArrayList<String> services) {
		this.put(Key.SERVICES_KEYWORD.getKey(), services);
	}

	/**
	 *  Set nomor HP yang digunakan untuk login untuk akun ini (bersifat opsional).
	 * 
	 * @exception AccountComponentsInvalidException  Apabila panjang parameter lebih dari 20.
	 */
	public void setNumberPhone(String numberPhone) throws AccountComponentsInvalidException {
		if (numberPhone != null && numberPhone.length() > 20) {
			this.put(Key.NUMBER_PHONE_KEYWORD.getKey(), null);
			throw new AccountComponentsInvalidException("Panjang karakter nomor HP tidak boleh lebih dari 20 karakter.");
		} else
			this.put(Key.NUMBER_PHONE_KEYWORD.getKey(), numberPhone);
	}

	/**
	 *  Set password yang digunakan untuk login untuk akun ini (bersifat opsional).
	 * 
	 * @exception AccountComponentsInvalidException Apabila panjang parameter lebih
	 *                                              dari 30.
	 */
	public void setPassword(String password) throws AccountComponentsInvalidException {
		if (password != null && password.length() > 30) {
			this.put(Key.PASSWORD_KEYWORD.getKey(), null);
			throw new AccountComponentsInvalidException("Panjang karakter password tidak boleh lebih dari 30 karakter.");
		} else
			this.put(Key.PASSWORD_KEYWORD.getKey(), password);
	}

	/**
	 *  Set email akun yang digunakan untuk pemulihan akun ini (bersifat opsional).
	 * 
	 * @exception AccountComponentsInvalidException Apabila panjang parameter lebih
	 *                                              dari 40.
	 */
	public void setRecoveryAccount(String recoveryAccount) throws AccountComponentsInvalidException {
		if (recoveryAccount != null && recoveryAccount.length() > 40) {
			this.put(Key.RECOVERY_ACCOUNT_KEYWORD.getKey(), null);
			throw new AccountComponentsInvalidException("Panjang karakter akun penyembuhan tidak boleh lebih dari 40 karakter.");
		} else
			this.put(Key.RECOVERY_ACCOUNT_KEYWORD.getKey(), recoveryAccount);
	}


	/* --> Getters <-- */

	public String getBorn() {
		return (String) get(Key.USER_BORN_KEYWORD.getKey());
	}

	public String getDescription() {
		return (String) super.get(Key.DESKRIPTION_KEYWORD.getKey());
	}

	public String getEmail() {
		return (String) super.get(Key.EMAIL_KEYWORD.getKey());
	}

	public String getRecoveryAccount() {
		return (String) super.get(Key.RECOVERY_ACCOUNT_KEYWORD.getKey());
	}

	public String getPassword() {
		return (String) super.get(Key.PASSWORD_KEYWORD.getKey());
	}

	public String getUserName() {
		return (String) super.get(Key.USER_NAME_KEYWORD.getKey());
	}

	public ArrayList<String> getServices() {
		return (ArrayList<String>) super.get(Key.SERVICES_KEYWORD.getKey());
	}

	public String getNumberPhone() {
		return (String) super.get(Key.NUMBER_PHONE_KEYWORD.getKey());
	}


	/* --> Utils <-- */

	/**
	 *  Semua key komponen-komponen kelas Account.
	 */
	public static enum Key {
		
		USER_NAME_KEYWORD("user-name"),
		DESKRIPTION_KEYWORD("description"),
		EMAIL_KEYWORD("email"),
		RECOVERY_ACCOUNT_KEYWORD("recovery-account"),
		PASSWORD_KEYWORD("password"),
		SERVICES_KEYWORD("services"),
		NUMBER_PHONE_KEYWORD("number-phone"),
		USER_BORN_KEYWORD("user-born");

		private String key;
		Key(String key) {
			this.key = key;
		}

		public String getKey() {
			return this.key;
		}
	}


	/* --> Overrides <-- */

	@Override
	public Object put(String key, Object value) {
		if (value == null || value.toString().equalsIgnoreCase(""))
			return super.put(key, null);
		else
			return super.put(key, value);
	}

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