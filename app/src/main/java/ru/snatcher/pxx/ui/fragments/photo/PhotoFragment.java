package ru.snatcher.pxx.ui.fragments.photo;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.snatcher.pxx.R;
import ru.snatcher.pxx.binding.FragmentDataBindingComponent;
import ru.snatcher.pxx.databinding.FragmentPhotoBinding;
import ru.snatcher.pxx.di.Injectable;
import ru.snatcher.pxx.ui.common.NavigationController;
import ru.snatcher.pxx.util.AutoClearedValue;

public class PhotoFragment extends LifecycleFragment implements Injectable {

	static final String PHOTO_ID_KEY = "photo_id_key";
	private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
	@Inject
	NavigationController mController;
	@Inject
	ViewModelProvider.Factory mFactory;
	android.databinding.DataBindingComponent mComponent = new FragmentDataBindingComponent(this);
	private PhotoFragmentViewModel mViewModel;

	private AutoClearedValue<FragmentPhotoBinding> mBinding;

	public static PhotoFragment create(long photoId) {
		Bundle args = new Bundle();
		args.putLong(PHOTO_ID_KEY, photoId);
		PhotoFragment photoFragment = new PhotoFragment();
		photoFragment.setArguments(args);
		return photoFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		FragmentPhotoBinding binding = DataBindingUtil
				.inflate(inflater, R.layout.fragment_photo, container, false, mComponent);
		mBinding = new AutoClearedValue<>(this, binding);
		return binding.getRoot();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(this, mFactory).get(PhotoFragmentViewModel.class);
		initUi();
	}

	private void initUi() {
		mBinding.get().setRetryCallback(this::getPhoto);
		getPhoto();
	}

	private void getPhoto() {
		mViewModel.getPhoto(getArguments().getLong(PHOTO_ID_KEY))
				.observe(this, resource -> {
					mBinding.get().setResource(resource);

					if (resource != null && resource.data != null) {
						mBinding.get().setPhoto(resource.data.photo);
					}
				});
	}

	@Override
	public LifecycleRegistry getLifecycle() {
		return mRegistry;
	}
}
