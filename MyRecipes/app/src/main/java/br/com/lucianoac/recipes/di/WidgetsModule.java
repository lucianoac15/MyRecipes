package br.com.lucianoac.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import br.com.lucianoac.recipes.db.IngredientsContentProvider;
import br.com.lucianoac.recipes.ui.widget.IngredientsWidget;

@Module
abstract class WidgetsModule {

    @ContributesAndroidInjector
    abstract IngredientsWidget contributesIngredientsWidget();

    @ContributesAndroidInjector
    abstract IngredientsContentProvider contributesIngredientsContentProvider();

}
