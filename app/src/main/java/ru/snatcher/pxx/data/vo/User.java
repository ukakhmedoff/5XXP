package ru.snatcher.pxx.data.vo;


import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("id")
	public long id;
	@SerializedName("username")
	public String username;
	@SerializedName("firstname")
	public String firstname;
	@SerializedName("lastname")
	public String lastname;
	@SerializedName("city")
	public String city;
	@SerializedName("country")
	public String country;
	@SerializedName("fullname")
	public String fullname;
	@SerializedName("userpic_url")
	public String userpicUrl;
	@SerializedName("upgrade_status")
	public long upgradeStatus;

	public User(long id, String username, String firstname, String lastname, String city, String country, String fullname, String userpicUrl, long upgradeStatus) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.country = country;
		this.fullname = fullname;
		this.userpicUrl = userpicUrl;
		this.upgradeStatus = upgradeStatus;
	}
}