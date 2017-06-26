package ru.snatcher.pxx.ui.common;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.snatcher.pxx.R;
import ru.snatcher.pxx.data.vo.Photo;
import ru.snatcher.pxx.databinding.MainListItemBinding;

public class MainListAdapter extends DataBoundListAdapter<Photo, MainListItemBinding> {

	private final DataBindingComponent mComponent;

	private final PhotoClickCallback mCallback;

	public MainListAdapter(DataBindingComponent component, PhotoClickCallback callback) {
		this.mComponent = component;
		this.mCallback = callback;
	}

	@Override
	protected MainListItemBinding createBinding(final ViewGroup parent) {
		MainListItemBinding binding = DataBindingUtil
				.inflate(LayoutInflater.from(parent.getContext()),
						R.layout.main_list_item, parent,
						false, mComponent);

		binding.getRoot().setOnClickListener(v -> {
			Photo photo = binding.getPhoto();
			if (photo != null && mCallback != null) {
				mCallback.onClick(photo.id);
			}
		});
		return binding;
	}

	@Override
	protected void bind(final MainListItemBinding binding, final Photo item) {
		binding.setPhoto(item);
	}

	public interface PhotoClickCallback {
		void onClick(long photoId);
	}
}