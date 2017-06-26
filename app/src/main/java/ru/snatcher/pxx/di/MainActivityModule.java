package ru.snatcher.pxx.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.snatcher.pxx.ui.MainActivity;

@Module
public abstract class MainActivityModule {
	@ContributesAndroidInjector(modules = FragmentBuildersModule.class)
	abstract MainActivity contributeMainActivity();
}
