package ru.snatcher.pxx.di;

import javax.inject.Inject;

import dagger.Subcomponent;
import ru.snatcher.pxx.ui.fragments.main.MainFragmentViewModel;
import ru.snatcher.pxx.ui.fragments.photo.PhotoFragmentViewModel;
import ru.snatcher.pxx.viewmodel.MarketViewModelFactory;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link MarketViewModelFactory}. Using this component allows
 * ViewModels to define {@link Inject} constructors.
 */
@Subcomponent
public interface ViewModelSubComponent {
	MainFragmentViewModel mainFragmentViewModel();

	PhotoFragmentViewModel productFragmentViewModel();

	@Subcomponent.Builder
	interface Builder {
		ViewModelSubComponent build();
	}
}
