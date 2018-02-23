package br.com.lucianoac.recipes.common;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.util.Objects;
import javax.inject.Inject;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    protected Navigator navigator;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        navigator = new Navigator(this);
        setHomeEnabled(false);
    }

    protected void toast(@NonNull final String message) {
        Objects.requireNonNull(message, "message cannot be null");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected <T extends ViewModel> T getViewModel(final Class<T> cls) {
        return ViewModelProviders.of(this, viewModelFactory).get(cls);
    }

    protected void setActionBarTitle(@NonNull final String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Objects.requireNonNull(title));
        }
    }

    protected void setHomeEnabled(@NonNull final Boolean showUp) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showUp);
        }
    }

    public Integer itemId() {
        return getIntent().getIntExtra(Navigator.KEY_ITEM_ID, - 1);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
