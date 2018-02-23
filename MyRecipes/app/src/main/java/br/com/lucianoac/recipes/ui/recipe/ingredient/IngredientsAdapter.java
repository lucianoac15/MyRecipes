package br.com.lucianoac.recipes.ui.recipe.ingredient;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import br.com.lucianoac.recipes.R;
import br.com.lucianoac.recipes.common.DataListAdapter;
import br.com.lucianoac.recipes.common.OnItemClickedListener;
import br.com.lucianoac.recipes.model.Ingredient;
import br.com.lucianoac.recipes.databinding.CardIngredientBinding;

public final class IngredientsAdapter extends DataListAdapter<Ingredient, CardIngredientBinding> {

    IngredientsAdapter(OnItemClickedListener<Ingredient> listener) {
        super(listener);
    }

    @Override
    protected CardIngredientBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        return DataBindingUtil.inflate(inflater, R.layout.card_ingredient, parent, false);
    }

    @Override
    protected void bind(CardIngredientBinding binding, Ingredient item) {
        binding.setIngredient(item);
    }
}
