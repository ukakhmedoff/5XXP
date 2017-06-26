package ru.snatcher.pxx.data.vo;

import com.google.gson.annotations.SerializedName;

public class Filters {

	@SerializedName("category")
	public boolean category;
	@SerializedName("exclude")
	public boolean exclude;

	public Filters(boolean category, boolean exclude) {
		super();
		this.category = category;
		this.exclude = exclude;
	}
}