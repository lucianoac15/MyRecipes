package br.com.lucianoac.recipes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import br.com.lucianoac.recipes.model.Ingredient;

@Database(entities = {Ingredient.class}, version = 1)
public abstract class IngredientsDatabase extends RoomDatabase {
    public abstract IngredientsDao ingredients();
}
