package br.com.lucianoac.recipes.common;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.Objects;
import br.com.lucianoac.recipes.R;
import br.com.lucianoac.recipes.databinding.SimpleListBinding;


public abstract class RecyclerFragment<M, A extends DataListAdapter> extends BaseFragment {
    protected A adapter;
    private SimpleListBinding binding;


    public RecyclerFragment() {
    }

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simple_list, container, false);
        return binding.getRoot();
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();
    }

    @CallSuper
    protected void setUpRecyclerView() {
        adapter = Objects.requireNonNull(createAdapter());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setLoading(true);
        binding.setNoData(false);
        updateList();
    }

    protected void setLoading(final Boolean isLoading) {
        binding.setLoading(isLoading);
    }

    protected RecyclerView getRecyclerView() {
        return binding.recyclerView;
    }

    protected abstract A createAdapter();

    protected abstract void updateList();

    protected void setNoData(@StringRes int message, @DrawableRes int icon) {
        binding.setNoData(true);
        binding.noDataMessage.setText(message);
        binding.noDataIcon.setImageResource(icon);
    }

    @SuppressWarnings("unchecked")
    protected void updateAdapter(@NonNull final List<M> newData) {
        Objects.requireNonNull(newData);
        if (adapter != null) {
            if (newData.size() != 0) {
                setLoading(false);
                this.adapter.replace(newData);
            } else {
                setNoData(R.string.error_msg, R.drawable.ic_cloud_off_black_24dp);
            }
        }
    }
}
