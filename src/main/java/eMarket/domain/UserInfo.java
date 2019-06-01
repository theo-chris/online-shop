package eMarket.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name="users")
public class UserInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	
	@Column(unique=true, nullable=false)
	String login;
	@Column(unique=true, nullable=false)
	String password;
	@Transient
	String password2;
	String forenames;
	String lastnames;
	@Transient
	String userType;
	@Column(nullable=false)
	private int enabled = 1;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="role", referencedColumnName="id")
	private Role role;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getForenames() {
		return forenames;
	}
	public void setForenames(String forenames) {
		this.forenames = forenames;
	}
	public String getLastnames() {
		return lastnames;
	}
	public void setLastnames(String lastnames) {
		this.lastnames = lastnames;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int isEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserInfo [login=" + login + ", password=" + password + ", password2=" + password2 + ", forenames="
				+ forenames + ", lastnames=" + lastnames + ", userType=" + userType + ", role=" + role + "]";
	}	
	
}
