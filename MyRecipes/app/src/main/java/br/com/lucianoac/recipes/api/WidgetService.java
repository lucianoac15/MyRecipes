package br.com.lucianoac.recipes.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import br.com.lucianoac.recipes.model.Recipe;

public interface WidgetService {
    @GET("baking.json")
    Call<List<Recipe>> recipes();
}
