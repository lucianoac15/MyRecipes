package br.com.lucianoac.recipes.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import br.com.lucianoac.recipes.ui.recipe.ingredient.IngredientsFragment;
import br.com.lucianoac.recipes.ui.recipe.step.StepsFragment;
import br.com.lucianoac.recipes.ui.steps.StepDetailFragment;

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract StepDetailFragment contributesStepDetailFragment();

    @ContributesAndroidInjector
    abstract StepsFragment contributesStepsFragment();

    @ContributesAndroidInjector
    abstract IngredientsFragment contributesIngredientsFragment();
}
