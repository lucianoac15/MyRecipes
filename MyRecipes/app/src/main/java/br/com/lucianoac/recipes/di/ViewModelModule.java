package br.com.lucianoac.recipes.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import br.com.lucianoac.recipes.ui.main.RecipesViewModel;
import br.com.lucianoac.recipes.ui.recipe.SingleRecipeViewModel;
import br.com.lucianoac.recipes.ui.steps.StepViewModel;
import br.com.lucianoac.recipes.common.ViewModelFactory;

@SuppressWarnings("WeakerAccess")
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StepViewModel.class)
    abstract ViewModel bindStepsViewModel(StepViewModel stepViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RecipesViewModel.class)
    abstract ViewModel bindRecipesViewModel(RecipesViewModel recipesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SingleRecipeViewModel.class)
    abstract ViewModel bindSingleRecipeViewModel(SingleRecipeViewModel singleRecipeViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
