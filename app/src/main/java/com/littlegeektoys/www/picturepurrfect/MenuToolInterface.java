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

    /**
     * Callback for sticker selection
     * @param sticker
     */
    void onStickerSelect(String sticker);

    /**
     * Callback for text sticker input
     * @param text
     */
    void onTextInput(String text);

    void onGoBack();

    void onSwitchFragment(Fragment fragment);
}
