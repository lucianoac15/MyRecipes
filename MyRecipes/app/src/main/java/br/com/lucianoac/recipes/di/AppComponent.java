package br.com.lucianoac.recipes.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import br.com.lucianoac.recipes.RecipesApp;


@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        NetModule.class,
        ActivitiesModule.class,
        FragmentsModule.class,
        WidgetsModule.class
})
public interface AppComponent {
    void inject(RecipesApp recipesApp);
}
