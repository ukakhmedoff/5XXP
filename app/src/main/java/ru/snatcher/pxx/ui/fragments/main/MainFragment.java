package ru.snatcher.pxx.ui.fragments.main;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.snatcher.pxx.R;
import ru.snatcher.pxx.binding.FragmentDataBindingComponent;
import ru.snatcher.pxx.data.vo.Main;
import ru.snatcher.pxx.data.vo.Resource;
import ru.snatcher.pxx.databinding.FragmentMainBinding;
import ru.snatcher.pxx.di.Injectable;
import ru.snatcher.pxx.ui.common.MainListAdapter;
import ru.snatcher.pxx.ui.common.NavigationController;
import ru.snatcher.pxx.util.AutoClearedValue;

public class MainFragment extends LifecycleFragment implements Injectable {

	private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
	@Inject
	NavigationController mController;
	@Inject
	ViewModelProvider.Factory mFactory;
	DataBindingComponent mComponent = new FragmentDataBindingComponent(this);
	long page = 1;
	private MainFragmentViewModel mViewModel;
	private AutoClearedValue<MainListAdapter> mAdapter;
	private AutoClearedValue<FragmentMainBinding> mBinding;

	@Override
	public LifecycleRegistry getLifecycle() {
		return mRegistry;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
		FragmentMainBinding binding = DataBindingUtil
				.inflate(inflater, R.layout.fragment_main, container, false, mComponent);
		mBinding = new AutoClearedValue<>(this, binding);
		return binding.getRoot();
	}

	@Override
	public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(this, mFactory).get(MainFragmentViewModel.class);
		initUi();
	}

	private void initUi() {
		mBinding.get().setRetryCallback(this::updateProductList);
		initRecycler();
	}

	private void initRecycler() {
		MainListAdapter adapter = new MainListAdapter(mComponent, mController::navigateToPhoto);
		RecyclerView recycler = mBinding.get().recyclerMain.recycler;
		recycler.setAdapter(adapter);
		mAdapter = new AutoClearedValue<>(this, adapter);
		initPhotos(page, false);
		addScrollListener();
	}

	private void initPhotos(long offset, boolean isPagination) {
		mViewModel.getPhotos(offset)
				.observe(this, observer(isPagination));
	}

	private void addScrollListener() {
		RecyclerView recycler = mBinding.get().recyclerMain.recycler;
		LinearLayoutManager layoutManager = (LinearLayoutManager) recycler.getLayoutManager();
		recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				long lastPosition = layoutManager.findLastVisibleItemPosition();
				if (lastPosition == mAdapter.get().getItemCount() - 2) {
					page++;
					initPhotos(page, true);
				}
			}
		});
	}

	private void updateProductList() {
		mViewModel.getPhotos(1)
				.observe(this, observer(false));
	}

	@NonNull
	private Observer<Resource<Main>> observer(boolean isPagination) {
		return observer -> {
			if (observer != null) {
				mBinding.get().setResource(observer);
				mAdapter.get().add(observer.data != null ? observer.data.photos : null,
						isPagination);

				mBinding.get().executePendingBindings();
				if (observer.data != null && observer.data.photos.size() < 10) {
					mBinding.get().recyclerMain.recycler.clearOnChildAttachStateChangeListeners();
				}
			}
		};
	}
}