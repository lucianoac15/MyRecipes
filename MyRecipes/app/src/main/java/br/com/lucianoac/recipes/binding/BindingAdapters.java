package br.com.lucianoac.recipes.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import br.com.lucianoac.recipes.model.Ingredient;

public class BindingAdapters {

    @BindingAdapter("showView")
    public static void showView(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("quantity")
    public static void quantity(TextView tv, Ingredient ingredient) {
        tv.setText(String.format("%s %s", ingredient.quantity, ingredient.measure));
    }

    @BindingAdapter("hideView")
    public static void hideView(View view, boolean hide) {
        showView(view, ! hide);
    }

}
