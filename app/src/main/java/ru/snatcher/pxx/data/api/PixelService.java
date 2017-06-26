package ru.snatcher.pxx.data.api;

import android.arch.lifecycle.LiveData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.snatcher.pxx.data.vo.Main;
import ru.snatcher.pxx.data.vo.PhotoInfo;

/**
 * REST API access points
 */
public interface PixelService {

	String CONSUMER_KEY = "P2Y7q7ivKWGcTWbnnU7JiNZFfkYi19KaYjdrxsig";

	@GET("photos?consumer_key=" + CONSUMER_KEY)
	LiveData<ApiResponse<Main>> getPhotos(@Query("page") long offset,
			@Query("feature") String feature);

	@GET("photos/{id}?consumer_key=" + CONSUMER_KEY)
	LiveData<ApiResponse<PhotoInfo>> getPhoto(@Path("id") long photoId);
}
