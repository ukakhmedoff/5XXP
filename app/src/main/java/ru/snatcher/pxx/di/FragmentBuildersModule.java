package ru.snatcher.pxx.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.snatcher.pxx.ui.fragments.main.MainFragment;
import ru.snatcher.pxx.ui.fragments.photo.PhotoFragment;

@Module
public abstract class FragmentBuildersModule {
	@ContributesAndroidInjector
	abstract MainFragment contributeMainFragment();

	@ContributesAndroidInjector
	abstract PhotoFragment contributePhotoFragment();

}
