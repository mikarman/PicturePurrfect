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
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextClock;

/**
 * Created by Jesse DeMott on 5/5/16.
 */


public class TextInputFragment extends DialogFragment {

    //Listing 12.9 Calling back to your target
    public static final String EXTRA_TEXT = "com.littlegeektoys.www.picturepurrfect.text";

    //private static final String ARG_STICKER = "sticker";
    private String text;

    private LinearLayout mLinearLayout;

    public static TextInputFragment newInstance(){
        TextInputFragment fragment = new TextInputFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_text_input, null);

        //Listing 12.7 Extracting the date and initializing DatePicker
        mLinearLayout = (LinearLayout) v.findViewById(R.id.dialog_text_input);
        final EditText mEditText = (EditText) v.findViewById(R.id.text_input);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.text_input_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        text = mEditText.getText().toString();
                        sendResult(Activity.RESULT_OK, text);
                        Log.i("StickerPickerFragment", text + " input");
                    }
                })
                .create();

    }

    private void sendResult(int resultCode, String text){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TEXT, text);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
