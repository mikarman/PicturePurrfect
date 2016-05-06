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
import android.widget.ImageButton;

/**
 * Created by Jesse DeMott on 5/5/16.
 */

public class StickerPickerFragment extends DialogFragment {

    /**
     * Identifier for sticker extra to be passed from dialog box
     */
    public static final String EXTRA_STICKER = "com.littlegeektoys.www.picturepurrfect.sticker";

    /**
     * String to be passed back to caller to specify which sticker to use
     */
    private String sticker;


    public static StickerPickerFragment newInstance(){
        Bundle args = new Bundle();
        StickerPickerFragment fragment = new StickerPickerFragment();
        return fragment;
    }

    /**
     * Creates the sticker picker Dialog box
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_stickers, null);

        //OnClickListeners for dialog box sticker buttons
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

        ImageButton sticker7 = (ImageButton) v.findViewById(R.id.sticker_7);
        sticker7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker7";
            }
        });

        ImageButton sticker8 = (ImageButton) v.findViewById(R.id.sticker_8);
        sticker8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker8";
            }
        });

        ImageButton sticker9 = (ImageButton) v.findViewById(R.id.sticker_9);
        sticker9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker9";
            }
        });

        ImageButton sticker10 = (ImageButton) v.findViewById(R.id.sticker_10);
        sticker10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker10";
            }
        });

        ImageButton sticker11 = (ImageButton) v.findViewById(R.id.sticker_11);
        sticker11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker11";
            }
        });

        ImageButton sticker12 = (ImageButton) v.findViewById(R.id.sticker_12);
        sticker12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker12";
            }
        });

        ImageButton sticker13 = (ImageButton) v.findViewById(R.id.sticker_13);
        sticker13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker13";
            }
        });


        ImageButton sticker14 = (ImageButton) v.findViewById(R.id.sticker_14);
        sticker14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker14";
            }
        });

        ImageButton sticker15 = (ImageButton) v.findViewById(R.id.sticker_15);
        sticker15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sticker = "sticker15";
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

    /**
     * Sends result of dialog selection back to the caller
     * @param resultCode
     * @param sticker
     */
    private void sendResult(int resultCode, String sticker){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_STICKER, sticker);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
