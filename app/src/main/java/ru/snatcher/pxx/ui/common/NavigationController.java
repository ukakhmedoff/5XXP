package ru.snatcher.pxx.ui.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import javax.inject.Inject;

import ru.snatcher.pxx.R;
import ru.snatcher.pxx.ui.MainActivity;
import ru.snatcher.pxx.ui.fragments.main.MainFragment;
import ru.snatcher.pxx.ui.fragments.photo.PhotoFragment;

public class NavigationController {
	private final int mContainerId;
	private final FragmentManager mManager;
	private FragmentTransaction mTransaction;

	@Inject
	public NavigationController(MainActivity mainActivity) {
		mContainerId = R.id.navigation_view_container;
		mManager = mainActivity.getSupportFragmentManager();
	}

	private void beginTransaction() {
		mTransaction = mManager.beginTransaction();
	}

	private void replaceFragment(Fragment fragment) {
		beginTransaction();
		mTransaction.replace(mContainerId, fragment, fragment.getTag());
	}

	private void addToBackStack() {
		mTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
		mTransaction.addToBackStack(null);
		commitTransaction();
	}

	private void commitTransaction() {
		mTransaction.commitAllowingStateLoss();
	}

	public void navigateToMain() {
		MainFragment fragment = new MainFragment();
		replaceFragment(fragment);
		commitTransaction();
	}

	public void navigateToPhoto(long photoId) {
		PhotoFragment fragment = PhotoFragment.create(photoId);
		replaceFragment(fragment);
		addToBackStack();
	}

	public int getBackStackCount() {
		return mManager.getBackStackEntryCount();
	}

	public void back() {
		mManager.popBackStack();
	}
}