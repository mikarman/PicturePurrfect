package com.littlegeektoys.www.picturepurrfect;

import android.support.v4.app.Fragment;

/**
 * Created by Michael Karman on 4/30/2016.
 */
public interface MenuToolInterface {
    enum ToolName {
        COLOR, DRAW, STICKER, TEXT
    }

    void onToolSelect(ToolName tool);

    void onGoBack();

    void onSwitchFragment(Fragment fragment);
}
