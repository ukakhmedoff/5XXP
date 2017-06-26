package ru.snatcher.pxx.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Main {

	@SerializedName("current_page")
	public long currentPage;
	@SerializedName("total_pages")
	public long totalPages;
	@SerializedName("total_items")
	public long totalItems;
	@SerializedName("photos")
	public List<Photo> photos;
	@SerializedName("filters")
	public Filters filters;
	@SerializedName("feature")
	public String feature;

	public Main(long currentPage, long totalPages, long totalItems, List<Photo> photos,
			Filters filters, String feature) {
		super();
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
		this.photos = photos;
		this.filters = filters;
		this.feature = feature;
	}

}