package ru.snatcher.pxx.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.snatcher.pxx.di.ViewModelSubComponent;
import ru.snatcher.pxx.ui.fragments.main.MainFragmentViewModel;
import ru.snatcher.pxx.ui.fragments.photo.PhotoFragmentViewModel;

@Singleton
public class MarketViewModelFactory implements ViewModelProvider.Factory {
	private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

	@Inject
	public MarketViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
		creators = new ArrayMap<>();
		// we cannot inject view models directly because they won't be bound to the owner's
		// view model scope.
		creators.put(PhotoFragmentViewModel.class,
				viewModelSubComponent::productFragmentViewModel);
		creators.put(MainFragmentViewModel.class, viewModelSubComponent::mainFragmentViewModel);
	}

	@Override
	public <T extends ViewModel> T create(Class<T> modelClass) {
		Callable<? extends ViewModel> creator = creators.get(modelClass);
		if (creator == null) {
			for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
				if (modelClass.isAssignableFrom(entry.getKey())) {
					creator = entry.getValue();
					break;
				}
			}
		}
		if (creator == null) {
			throw new IllegalArgumentException("unknown model class " + modelClass);
		}
		try {
			return (T) creator.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
