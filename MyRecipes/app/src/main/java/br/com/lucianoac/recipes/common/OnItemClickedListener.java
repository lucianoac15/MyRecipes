package br.com.lucianoac.recipes.common;

import android.support.annotation.NonNull;


public interface OnItemClickedListener<T> {
    void onClicked(@NonNull final T item);
}
