package com.littlegeektoys.www.picturepurrfect;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

/**
 * Created by Jesse DeMott on 5/5/16.
 */

public class StickerPickerFragment extends DialogFragment {

    //Listing 12.9 Calling back to your target
    public static final String EXTRA_STICKER = "com.littlegeektoys.www.picturepurrfect.sticker";

    //private static final String ARG_STICKER = "sticker";
    private String sticker;

    private GridLayout mGridLayout;

    public static StickerPickerFragment newInstance(){
        Bundle args = new Bundle();
        StickerPickerFragment fragment = new StickerPickerFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_stickers, null);

        //Listing 12.7 Extracting the date and initializing DatePicker
        mGridLayout = (GridLayout) v.findViewById(R.id.dialog_sticker_picker);

        ImageButton sticker1 = (ImageButton) v.findViewById(R.id.sticker_1);
        sticker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker1";
            }
        });

        ImageButton sticker2 = (ImageButton) v.findViewById(R.id.sticker_2);
        sticker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker2";
            }
        });

        ImageButton sticker3 = (ImageButton) v.findViewById(R.id.sticker_3);
        sticker3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker3";
            }
        });

        ImageButton sticker4 = (ImageButton) v.findViewById(R.id.sticker_4);
        sticker4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker4";
            }
        });

        ImageButton sticker5 = (ImageButton) v.findViewById(R.id.sticker_5);
        sticker5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker5";
            }
        });

        ImageButton sticker6 = (ImageButton) v.findViewById(R.id.sticker_6);
        sticker6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker6";
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.sticker_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        sendResult(Activity.RESULT_OK, sticker);
                        Log.i("StickerPickerFragment", sticker + " selected");
                    }
                })
                .create();

    }

    private void sendResult(int resultCode, String sticker){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_STICKER, sticker);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
