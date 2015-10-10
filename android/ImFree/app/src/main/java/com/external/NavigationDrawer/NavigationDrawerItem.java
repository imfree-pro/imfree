package com.external.NavigationDrawer;

import android.graphics.drawable.Drawable;

/**
 * Created by 종열 on 2015-05-31.
 */
public class NavigationDrawerItem {
    private String _text;
    private Drawable _drawable;

    public NavigationDrawerItem(String text, Drawable drawable) {
        _text = text;
        _drawable = drawable;
    }

    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = text;
    }

    public Drawable getDrawable() {
        return _drawable;
    }

    public void setDrawable(Drawable drawable) {
        _drawable = drawable;
    }
}
