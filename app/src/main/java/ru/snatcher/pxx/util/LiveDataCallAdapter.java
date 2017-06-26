package ru.snatcher.pxx.util;

import android.arch.lifecycle.LiveData;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import ru.snatcher.pxx.data.api.ApiResponse;

/**
 * A Retrofit adapterthat converts the Call into a LiveData of ApiResponse.
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {
	private final Type responseType;

	LiveDataCallAdapter(Type responseType) {
		this.responseType = responseType;
	}

	@Override
	public Type responseType() {
		return responseType;
	}

	@Override
	public LiveData<ApiResponse<R>> adapt(Call<R> call) {
		return new LiveData<ApiResponse<R>>() {
			AtomicBoolean started = new AtomicBoolean(false);

			@Override
			protected void onActive() {
				super.onActive();
				if (started.compareAndSet(false, true)) {
					call.enqueue(new Callback<R>() {
						@Override
						public void onResponse(Call<R> call, Response<R> response) {
							postValue(new ApiResponse<>(response));
						}

						@Override
						public void onFailure(Call<R> call, Throwable throwable) {
							postValue(new ApiResponse<>(throwable));
						}
					});
				}
			}
		};
	}
}
