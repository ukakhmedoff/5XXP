package ru.snatcher.pxx.data.vo;

import com.google.gson.annotations.SerializedName;

public class Photo {

	@SerializedName("id")
	public long id;
	@SerializedName("name")
	public String name;
	@SerializedName("description")
	public String description;
	@SerializedName("times_viewed")
	public long timesViewed;
	@SerializedName("rating")
	public String rating;
	@SerializedName("created_at")
	public String createdAt;
	@SerializedName("category")
	public long category;
	@SerializedName("privacy")
	public boolean privacy;
	@SerializedName("width")
	public long width;
	@SerializedName("height")
	public long height;
	@SerializedName("votes_count")
	public long votesCount;
	@SerializedName("comments_count")
	public long commentsCount;
	@SerializedName("nsfw")
	public boolean nsfw;
	@SerializedName("image_url")
	public String imageUrl;
	@SerializedName("user")
	public User user;

	public Photo(long id, String name, String description, long timesViewed, String rating,
			String createdAt, long category, boolean privacy, long width, long height,
			long votesCount, long commentsCount, boolean nsfw, String imageUrl, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.timesViewed = timesViewed;
		this.rating = rating;
		this.createdAt = createdAt;
		this.category = category;
		this.privacy = privacy;
		this.width = width;
		this.height = height;
		this.votesCount = votesCount;
		this.commentsCount = commentsCount;
		this.nsfw = nsfw;
		this.imageUrl = imageUrl;
		this.user = user;
	}
}