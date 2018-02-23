package br.com.lucianoac.recipes.ui.main;

import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import br.com.lucianoac.recipes.common.RecyclerActivity;
import br.com.lucianoac.recipes.model.Recipe;

public class HomeActivity extends RecyclerActivity<Recipe, RecipesAdapter> {
    RecipesViewModel recipes;


    @VisibleForTesting
    @Inject OkHttpClient client;

    @Override
    protected RecipesAdapter createAdapter() {
        return new RecipesAdapter(recipe -> navigator.toRecipeDetails(recipe));
    }

    @VisibleForTesting
    public OkHttpClient getClient() {
        return client;
    }

    @Override
    protected void createList() {
        recipes = getViewModel(RecipesViewModel.class);
        recipes.getRecipes().observe(this, response -> {
            if (response != null && response.isSuccessful())
                updateAdapter(response.getData());
        });
    }
}
