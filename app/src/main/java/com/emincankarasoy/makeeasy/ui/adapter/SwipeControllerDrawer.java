package com.emincankarasoy.makeeasy.ui.adapter;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeControllerDrawer {
    private final RecyclerView recyclerView;
    private final SwipeController swipeController;
    public SwipeControllerDrawer(RecyclerView recyclerView,SwipeController swipeController){
        this.recyclerView = recyclerView;
        this.swipeController = swipeController;
    }
    public void addItemDecoration(){
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }
}
