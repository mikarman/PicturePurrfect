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
 * Created by Vanessa on 5/5/16.
 */
public class ColorPickerFragment extends DialogFragment {

    private GridLayout mGridLayout;
    public static final String EXTRA_COLOR = "com.littlegeektoys.www.picturepurrfect.sticker";
    private String color;
    public static ColorPickerFragment newInstance(){
        Bundle args = new Bundle();
        ColorPickerFragment fragment = new ColorPickerFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_color, null);
        mGridLayout = (GridLayout) v.findViewById(R.id.dialog_color);

        ImageButton color1 = (ImageButton) v.findViewById(R.id.color_1);
       color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "color1";
            }
        });

        ImageButton color2 = (ImageButton) v.findViewById(R.id.color_2);
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "color2";
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.color_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK, color);
                        Log.i("ColorPickerFragment ", color + " selected");
                    }
                })
                .create();

    }

    private void sendResult(int resultCode, String color){
        if(getTargetFragment() == null){
            return;
        }
            Intent intent = new Intent();
            intent.putExtra(EXTRA_COLOR, color);
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);

    }
    }
