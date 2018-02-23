package br.com.lucianoac.recipes.api;


import android.arch.lifecycle.LiveData;
import java.util.List;
import retrofit2.http.GET;
import br.com.lucianoac.recipes.model.Recipe;

public interface RecipesService {

    @GET("baking.json")
    LiveData<ApiResponse<List<Recipe>>> recipes();

}
