package br.com.lucianoac.recipes.ui.recipe.step;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import br.com.lucianoac.recipes.R;
import br.com.lucianoac.recipes.common.DataListAdapter;
import br.com.lucianoac.recipes.common.OnItemClickedListener;
import br.com.lucianoac.recipes.model.Step;
import br.com.lucianoac.recipes.databinding.CardStepBinding;

public class StepsAdapter extends DataListAdapter<Step, CardStepBinding> {

    StepsAdapter(OnItemClickedListener<Step> listener) {
        super(listener);
    }

    @Override
    protected CardStepBinding createBinding(LayoutInflater inflater, ViewGroup parent) {
        final CardStepBinding binding = DataBindingUtil.inflate(inflater, R.layout.card_step, parent, false);
        binding.getRoot().setOnClickListener(view -> {
            final Step step = binding.getStep();
            if (step != null) listener.onClicked(step);
        });
        return binding;
    }

    @Override
    protected void bind(CardStepBinding binding, Step item) {
        binding.setStep(item);
    }

}
