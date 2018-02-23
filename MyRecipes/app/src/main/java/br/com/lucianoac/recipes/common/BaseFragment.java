package br.com.lucianoac.recipes.common;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class BaseFragment extends Fragment {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    protected Navigator navigator;

    public BaseFragment() {
    }

    protected void toast(@NonNull final String message) {
        Objects.requireNonNull(message, "message cannot be null");
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    protected <T extends ViewModel> T getViewModel(final Class<T> cls) {
        return ViewModelProviders.of(this, viewModelFactory).get(cls);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {AndroidSupportInjection.inject(this);
        super.onAttach(activity);
        navigator = new Navigator(activity);
    }
}
