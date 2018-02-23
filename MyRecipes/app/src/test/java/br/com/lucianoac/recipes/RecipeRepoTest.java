package br.com.lucianoac.recipes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.lucianoac.recipes.db.RecipeRepo;
import br.com.lucianoac.recipes.api.RecipesService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@RunWith(JUnit4.class)
public class RecipeRepoTest {

    private RecipesService service;
    private RecipeRepo repo;


    @Before
    public void setUp() {
        service = mock(RecipesService.class);
        repo = new RecipeRepo(service);
    }


    @Test
    public void loadRecipes() {
        repo.recipes();
        verify(service, times(1)).recipes();
    }

}