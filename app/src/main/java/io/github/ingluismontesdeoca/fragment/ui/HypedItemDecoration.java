package io.github.ingluismontesdeoca.fragment.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Aministrador on 08/08/2016.
 */
public class HypedItemDecoration extends RecyclerView.ItemDecoration {

    private int itemOffset;

    //@IntegerRes - Direccion de memoria del recurso entero del archivo de recursos que contiene el valor de dps del dispositivo
    public HypedItemDecoration(Context context, @IntegerRes int hdef) {
        int itemOffsetDp = context.getResources().getInteger(hdef);
        itemOffset = convertDp2Px(itemOffsetDp, context.getResources().getDisplayMetrics());
    }

    public int convertDp2Px(int offset, DisplayMetrics metrics){
        return offset * (metrics.densityDpi / 160);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(itemOffset,itemOffset,itemOffset,itemOffset);
    }
}
