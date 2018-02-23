package br.com.lucianoac.recipes.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import java.util.Objects;
import br.com.lucianoac.recipes.model.Recipe;
import br.com.lucianoac.recipes.model.Step;
import br.com.lucianoac.recipes.ui.recipe.BakingActivity;
import br.com.lucianoac.recipes.ui.steps.StepDetailFragment;
import br.com.lucianoac.recipes.ui.steps.StepsSliderActivity;

public class Navigator {

    static final String KEY_ITEM_TITLE = "KEY_ITEM_TITLE";
    public static final String KEY_ITEM_ID = "KEY_RECIPE_ID";
    private final Context context;

    Navigator(final Context context) {
        this.context = Objects.requireNonNull(context, "context cannot be null.");
    }


    public void toSteps(Integer recipeId, Step step) {
        final Intent intent = new Intent(context, StepsSliderActivity.class);
        intent.putExtra(StepDetailFragment.ARG_RECIPE_ID, recipeId)
                .putExtra(StepDetailFragment.ARG_STEP_ID, step.id);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
    public void toRecipeDetails(@NonNull final Recipe recipe) {
        final Intent intent = new Intent(context, BakingActivity.class);
        intent.putExtra(KEY_ITEM_ID, recipe.id)
                .putExtra(KEY_ITEM_TITLE, recipe.name);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
