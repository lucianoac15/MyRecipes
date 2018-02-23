package br.com.lucianoac.recipes.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import br.com.lucianoac.recipes.api.ApiResponse;
import br.com.lucianoac.recipes.api.RecipesService;
import br.com.lucianoac.recipes.model.Ingredient;
import br.com.lucianoac.recipes.model.Recipe;
import br.com.lucianoac.recipes.model.Step;


public final class RecipeRepo {

    @NonNull
    private RecipesService service;


    @Inject
    public RecipeRepo(@NonNull RecipesService service) {
        Objects.requireNonNull(service);
        this.service = service;
    }

    public LiveData<ApiResponse<List<Recipe>>> recipes() {
        return service.recipes();
    }

    @NonNull
    public LiveData<Recipe> recipe(@NonNull final Integer id) {
        return Transformations.map(this.recipes(), response -> {
            if (response != null && response.isSuccessful()) {
                for (Recipe recipe : response.getData()) {
                    if (recipe.id.equals(id)) return recipe;
                }
            }
            return null;
        });
    }


    @NonNull
    public LiveData<List<Ingredient>> ingredient(@NonNull final Integer id) {
        return Transformations.map(this.recipes(), response -> {
            if (response != null && response.isSuccessful()) {
                for (Recipe recipe : response.getData()) {
                    if (recipe.id.equals(id))
                        return recipe.ingredients;
                }
            }
            return new ArrayList<>();
        });
    }

    @NonNull
    public LiveData<List<Step>> steps(@NonNull final Integer id) {
        return Transformations.map(this.recipes(), response -> {
            if (response != null && response.isSuccessful()) {
                for (Recipe recipe : response.getData()) {
                    if (recipe.id.equals(id)) return recipe.steps;
                }
            }
            return new ArrayList<>();
        });
    }
}
