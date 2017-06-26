package ru.snatcher.pxx.data.vo;

import com.google.gson.annotations.SerializedName;

public class PhotoInfo {

	@SerializedName("photo")
	public Photo photo;

	public PhotoInfo(Photo photo) {
		super();
		this.photo = photo;
	}

}