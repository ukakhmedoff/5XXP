package ru.snatcher.pxx.ui.fragments.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.snatcher.pxx.data.repositories.PhotosRepository;
import ru.snatcher.pxx.data.vo.Main;
import ru.snatcher.pxx.data.vo.Resource;

public class MainFragmentViewModel extends ViewModel {

	private PhotosRepository mRepository;

	@Inject
	MainFragmentViewModel(PhotosRepository repository) {
		mRepository = repository;
	}

	LiveData<Resource<Main>> getPhotos(long offset) {
		return mRepository.getPhotos(offset, "popular");
	}
}