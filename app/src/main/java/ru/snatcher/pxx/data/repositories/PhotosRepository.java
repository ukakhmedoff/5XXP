package ru.snatcher.pxx.data.repositories;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.snatcher.pxx.AppExecutors;
import ru.snatcher.pxx.data.api.ApiResponse;
import ru.snatcher.pxx.data.api.PixelService;
import ru.snatcher.pxx.data.vo.Main;
import ru.snatcher.pxx.data.vo.PhotoInfo;
import ru.snatcher.pxx.data.vo.Resource;

public class PhotosRepository {

	private final AppExecutors appExecutors;
	private PixelService mService;

	@Inject
	PhotosRepository(AppExecutors appExecutors, final PixelService service) {
		mService = service;
		this.appExecutors = appExecutors;
	}

	public LiveData<Resource<Main>> getPhotos(long offset, String feature) {
		return new NetworkBoundResource<Main>(appExecutors) {

			@NonNull
			@Override
			protected LiveData<ApiResponse<Main>> createCall() {
				return mService.getPhotos(offset, feature);
			}
		}.asLiveData();
	}

	public LiveData<Resource<PhotoInfo>> getPhoto(final long photoId) {
		return new NetworkBoundResource<PhotoInfo>(appExecutors) {

			@NonNull
			@Override
			protected LiveData<ApiResponse<PhotoInfo>> createCall() {
				return mService.getPhoto(photoId);
			}
		}.asLiveData();
	}

}
