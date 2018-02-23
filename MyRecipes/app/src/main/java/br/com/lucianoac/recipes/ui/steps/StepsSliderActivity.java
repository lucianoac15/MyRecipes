package br.com.lucianoac.recipes.ui.steps;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import br.com.lucianoac.recipes.R;
import br.com.lucianoac.recipes.common.BaseActivity;
import br.com.lucianoac.recipes.ui.recipe.BakingActivity;

public class StepsSliderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_slider);

        if (savedInstanceState == null) {
            StepDetailFragment fragment = StepDetailFragment.newInstance(
                    getIntent().getIntExtra(StepDetailFragment.ARG_RECIPE_ID, - 1),
                    getIntent().getIntExtra(StepDetailFragment.ARG_STEP_ID, - 1)
            );
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigateUpTo(new Intent(this, BakingActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
