package br.com.lucianoac.recipes.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import br.com.lucianoac.recipes.db.IngredientsDatabase;


@SuppressWarnings("WeakerAccess")
@Module(includes = ViewModelModule.class)
public class AppModule {

    @NonNull
    private Application application;

    public AppModule(@NonNull Application app) {
        this.application = app;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    public SharedPreferences providesSharedPreferences(@NonNull Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    public IngredientsDatabase providesIngredientsDb(Application app) {
        return Room
                .databaseBuilder(app, IngredientsDatabase.class, "ingredients")
                .allowMainThreadQueries()
                .build();
    }

}
