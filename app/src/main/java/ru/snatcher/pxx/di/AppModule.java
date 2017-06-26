package ru.snatcher.pxx.di;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.snatcher.pxx.data.api.PixelService;
import ru.snatcher.pxx.util.LiveDataCallAdapterFactory;
import ru.snatcher.pxx.viewmodel.MarketViewModelFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
	@Singleton
	@Provides
	PixelService provideMarketService() {
		return new Retrofit.Builder()
				.baseUrl("https://api.500px.com/v1/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(new LiveDataCallAdapterFactory())
				.build()
				.create(PixelService.class);
	}

	@Singleton
	@Provides
	ViewModelProvider.Factory provideViewModelFactory(
			ViewModelSubComponent.Builder viewModelSubComponent) {
		return new MarketViewModelFactory(viewModelSubComponent.build());
	}
}
