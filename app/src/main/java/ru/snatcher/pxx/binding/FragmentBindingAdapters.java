package ru.snatcher.pxx.binding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import ru.snatcher.pxx.data.vo.Status;
import ru.snatcher.pxx.ui.common.RetryCallback;

/**
 * Binding adapters that work with a fragment instance.
 */
public class FragmentBindingAdapters {
	private final Fragment mFragment;

	@Inject
	FragmentBindingAdapters(Fragment fragment) {
		mFragment = fragment;
	}

	@BindingAdapter("imageUrl")
	public void bindImage(ImageView imageView, String url) {
		Glide.with(mFragment)
				.asBitmap()
				.load(url)
				.apply(new RequestOptions()
						.encodeFormat(Bitmap.CompressFormat.JPEG)
						.encodeQuality(100)
						.fitCenter()
						.format(DecodeFormat.PREFER_ARGB_8888))
				.into(imageView);
	}

	@BindingAdapter("refresh_listener")
	public void bindRefreshListener(SwipeRefreshLayout view, RetryCallback retryCallback) {
		view.setOnRefreshListener(retryCallback::retry);
	}

	@BindingAdapter("refreshing")
	public void bindRefreshing(SwipeRefreshLayout view, Status status) {
		view.setRefreshing(status == Status.LOADING);
	}
}