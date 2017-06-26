package ru.snatcher.pxx.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import ru.snatcher.pxx.R;
import ru.snatcher.pxx.ui.common.NavigationController;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner,
		HasSupportFragmentInjector {

	private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

	@Inject
	DispatchingAndroidInjector<Fragment> mInjector;

	@Inject
	NavigationController mController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mController.navigateToMain();
	}

	@Override
	public void onBackPressed() {
		if (mController.getBackStackCount() > 0) {
			mController.back();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public LifecycleRegistry getLifecycle() {
		return mRegistry;
	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return mInjector;
	}
}