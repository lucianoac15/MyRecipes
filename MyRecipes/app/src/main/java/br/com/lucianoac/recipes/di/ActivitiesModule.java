package br.com.lucianoac.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import br.com.lucianoac.recipes.ui.main.HomeActivity;
import br.com.lucianoac.recipes.ui.recipe.BakingActivity;
import br.com.lucianoac.recipes.ui.steps.StepsSliderActivity;

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract HomeActivity contributesRecipesListActivity();

    @ContributesAndroidInjector
    abstract StepsSliderActivity contributesStepsActivity();

    @ContributesAndroidInjector
    abstract BakingActivity contributesBakingActivity();
}
