package ru.snatcher.pxx;

import android.app.Activity;
import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import ru.snatcher.pxx.di.AppInjector;

public class App extends Application implements HasActivityInjector {
	@Inject
	DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

	@Override
	public void onCreate() {
		super.onCreate();
		AppInjector.init(this);
		ViewTarget.setTagId(R.id.glide_tag);
	}

	@Override
	public DispatchingAndroidInjector<Activity> activityInjector() {
		return dispatchingAndroidInjector;
	}

}
