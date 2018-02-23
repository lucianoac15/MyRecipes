package br.com.lucianoac.recipes.ui.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public final class IngredientsListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new IngredientsRemoteViewsFactory(this.getApplicationContext()));
    }
}
