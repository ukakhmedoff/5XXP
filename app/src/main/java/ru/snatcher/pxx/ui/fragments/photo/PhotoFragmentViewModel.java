package ru.snatcher.pxx.ui.fragments.photo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.snatcher.pxx.data.repositories.PhotosRepository;
import ru.snatcher.pxx.data.vo.PhotoInfo;
import ru.snatcher.pxx.data.vo.Resource;

public class PhotoFragmentViewModel extends ViewModel {
	private PhotosRepository mRepository;

	@Inject
	PhotoFragmentViewModel(PhotosRepository repository) {
		mRepository = repository;
	}

	LiveData<Resource<PhotoInfo>> getPhoto(long photoId) {
		return mRepository.getPhoto(photoId);
	}
}
