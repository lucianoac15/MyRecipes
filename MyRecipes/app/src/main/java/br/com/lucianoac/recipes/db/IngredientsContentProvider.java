package br.com.lucianoac.recipes.db;

import android.arch.persistence.room.Room;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import br.com.lucianoac.recipes.model.Ingredient;

public final class IngredientsContentProvider extends ContentProvider {

    public static final String AUTHORITY = "br.com.lucianoac.recipes.provider";
    public static final Uri URI_INGREDIENT = Uri.parse("content://" + AUTHORITY + "/" + Ingredient.TABLE_NAME);
    private static final int CODE_INGREDIENT_DIR = 11;
    private static final int CODE_INGREDIENT_ITEM = 22;
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Ingredient.TABLE_NAME, CODE_INGREDIENT_DIR);
        MATCHER.addURI(AUTHORITY, Ingredient.TABLE_NAME + "/*", CODE_INGREDIENT_ITEM);
    }

    private IngredientsDatabase db;

    @Override
    public boolean onCreate() {
        db = Room
                .databaseBuilder(getContext(), IngredientsDatabase.class, "ingredients")
                .allowMainThreadQueries()
                .build();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);
        if (code == CODE_INGREDIENT_DIR || code == CODE_INGREDIENT_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }

            if (code == CODE_INGREDIENT_DIR) {
                final Cursor cursor;
                cursor = db.ingredients().selectAll();
                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            }

            return null;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (MATCHER.match(uri)) {
            case CODE_INGREDIENT_DIR:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }
                final long id = db.ingredients().insert(Ingredient.fromContentValues(values));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_INGREDIENT_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        switch (MATCHER.match(uri)) {
            case CODE_INGREDIENT_DIR:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final Ingredient[] ingredients = new Ingredient[values.length];
                for (int i = 0; i < values.length; i++) {
                    ingredients[i] = Ingredient.fromContentValues(values[i]);
                }
                return db.ingredients().insertAll(ingredients).length;
            case CODE_INGREDIENT_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @NonNull
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_INGREDIENT_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + Ingredient.TABLE_NAME;
            case CODE_INGREDIENT_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + Ingredient.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_INGREDIENT_DIR:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final int count = db.ingredients().deleteAll();
                context.getContentResolver().notifyChange(uri, null);
                return count;
            case CODE_INGREDIENT_ITEM:
                throw new IllegalArgumentException("Invalid URI, there is not; only delete all" + uri);

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new IllegalStateException("update is not allowed, DON'T use it.");
    }
}
