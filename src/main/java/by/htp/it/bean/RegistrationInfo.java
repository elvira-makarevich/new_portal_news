package by.htp.it.bean;

import java.io.Serializable;

public class RegistrationInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private String login;
	private String email;
	private String password;
	private String newPassword;

	public RegistrationInfo() {
		super();
	}

	public RegistrationInfo(String name, String surname, String login, String email, String password,
			String newPassword) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.password = password;
		this.newPassword = newPassword;
	}

	public RegistrationInfo(String name, String surname, String login, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public RegistrationInfo(String password, String newPassword) {
		super();
		this.password = password;
		this.newPassword = newPassword;
	}

	public RegistrationInfo(String login, String password, String newPassword) {
		this.login = login;
		this.password = password;
		this.newPassword = newPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		RegistrationInfo other = (RegistrationInfo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [name=" + name + ", surname=" + surname + ", login=" + login + ", email="
				+ email + ", password=" + password + ", newPassword=" + newPassword + "]";
	}
}