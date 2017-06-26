package ru.snatcher.pxx.ui.common;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic RecyclerView adapter that uses Data Binding.
 *
 * @param <T> Type of the items in the list
 * @param <V> The of the ViewDataBinding
 */
public abstract class DataBoundListAdapter<T, V extends ViewDataBinding>
		extends RecyclerView.Adapter<DataBoundViewHolder<V>> {

	@Nullable
	private List<T> items = new ArrayList<>();

	private int dataVersion = 0;
	private int startVersion = 0;

	@Override
	public final DataBoundViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
		V binding = createBinding(parent);
		return new DataBoundViewHolder<>(binding);
	}

	protected abstract V createBinding(ViewGroup parent);

	@Override
	public final void onBindViewHolder(DataBoundViewHolder<V> holder, int position) {
		//noinspection ConstantConditions
		bind(holder.binding, items.get(position));
		holder.binding.executePendingBindings();
	}

	public void add(List<T> add, boolean isPagination) {
		if (!isPagination) {
			dataVersion++;
			startVersion = dataVersion;
			items = add;
			notifyDataSetChanged();
		} else if (checkItems(items) & checkItems(add) & startVersion == dataVersion
				&& items.size() >= add.size()) {
			dataVersion++;
			int itemsSize = items.size();
			items.addAll(add);
			notifyItemInserted(itemsSize);
		} else if (startVersion != dataVersion & !checkItems(add)) {
			startVersion = dataVersion;
		}
	}

	public void replace(List<T> update) {
		if (!checkItems(items)) {
			if (!checkItems(update)) {
				return;
			}
			items = update;
			notifyDataSetChanged();
		} else if (!checkItems(update)) {
			int oldSize = items.size();
			items = null;
			notifyItemRangeRemoved(0, oldSize);
		} else {
			items.clear();
			items = update;
			notifyDataSetChanged();
		}
	}

	private boolean checkItems(List<T> list) {
		return list != null && list.size() > 0;
	}

	protected abstract void bind(V binding, T item);

	@Override
	public int getItemCount() {
		return items == null ? 0 : items.size();
	}
}