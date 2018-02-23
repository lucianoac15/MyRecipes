package br.com.lucianoac.recipes.di;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import br.com.lucianoac.recipes.R;
import br.com.lucianoac.recipes.api.RecipesService;
import br.com.lucianoac.recipes.api.WidgetService;
import br.com.lucianoac.recipes.common.retrofit.LiveDataCallAdapterFactory;


@SuppressWarnings("WeakerAccess")
@Module
public class NetModule {

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
    }

    @Singleton
    @Provides
    public RecipesService provideVideoService(Application application, OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .baseUrl(application.getString(R.string.api_url))
                .client(client)
                .build()
                .create(RecipesService.class);
    }

    @Singleton
    @Provides
    public WidgetService provideWidgetService(Application application, OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(application.getString(R.string.api_url))
                .client(client)
                .build()
                .create(WidgetService.class);
    }
}
