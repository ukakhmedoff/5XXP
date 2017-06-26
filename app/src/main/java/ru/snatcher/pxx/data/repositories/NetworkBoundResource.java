package ru.snatcher.pxx.data.repositories;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import ru.snatcher.pxx.AppExecutors;
import ru.snatcher.pxx.data.api.ApiResponse;
import ru.snatcher.pxx.data.vo.Resource;

public abstract class NetworkBoundResource<T> {
	private final AppExecutors appExecutors;

	private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

	@MainThread
	NetworkBoundResource(AppExecutors appExecutors) {
		this.appExecutors = appExecutors;
		result.setValue(Resource.loading(null));
		fetchFromNetwork();
	}

	private void fetchFromNetwork() {
		LiveData<ApiResponse<T>> apiResponse = createCall();
		result.addSource(apiResponse, response -> {
			if (response != null && response.isSuccessful()) {
				appExecutors.diskIO().execute(() -> appExecutors.mainThread().execute(() ->
						result.setValue(Resource.success(getResponseBody(response)))));
			}
		});
	}

	private T getResponseBody(ApiResponse<? extends T> pApiResponse) {
		return pApiResponse.body;
	}

	LiveData<Resource<T>> asLiveData() {
		return result;
	}

	@NonNull
	@MainThread
	protected abstract LiveData<ApiResponse<T>> createCall();
}