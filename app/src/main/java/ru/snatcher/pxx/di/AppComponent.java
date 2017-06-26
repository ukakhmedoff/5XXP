package ru.snatcher.pxx.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import ru.snatcher.pxx.App;

@Singleton
@Component(modules = {
		AndroidInjectionModule.class,
		AppModule.class,
		MainActivityModule.class
})
public interface AppComponent {
	void inject(App pApp);

	@Component.Builder
	interface Builder {
		@BindsInstance
		Builder application(Application application);

		AppComponent build();
	}
}
