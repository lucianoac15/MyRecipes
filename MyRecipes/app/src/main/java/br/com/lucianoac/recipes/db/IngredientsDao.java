package br.com.lucianoac.recipes.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;
import br.com.lucianoac.recipes.model.Ingredient;


@Dao
public interface IngredientsDao {

    @Query("SELECT * FROM " + Ingredient.TABLE_NAME)
    Cursor selectAll();

    @Query("DELETE FROM " + Ingredient.TABLE_NAME)
    int deleteAll();

    @Insert
    long insert(Ingredient ingredient);


    @Insert
    long[] insertAll(Ingredient... ingredients);

}
