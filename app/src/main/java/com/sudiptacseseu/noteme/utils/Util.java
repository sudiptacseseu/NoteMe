package com.sudiptacseseu.noteme.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;

import com.sudiptacseseu.noteme.R;
import com.sudiptacseseu.noteme.activity.EditNoteActivity;
import com.sudiptacseseu.noteme.activity.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class Util {

    public static String getCurrentDate(String myFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date currentTime = Calendar.getInstance().getTime();

        return sdf.format(currentTime);
    }

    public static String getDeadlineDate(String myFormat, Calendar myCalendar) {
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date currentTime = myCalendar.getTime();

        return sdf.format(currentTime);
    }

    public static void customizeActionBar(ActionBar actionBar, Activity context, String titleText){
        View viewActionBar = context.getLayoutInflater().inflate(R.layout.actionbar_layout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textviewTitle = viewActionBar.findViewById(R.id.actionbarTitle);
        textviewTitle.setText(titleText);
        assert actionBar != null;
        actionBar.setCustomView(viewActionBar, params);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    public static void customDialog(Activity context, String hintText, Drawable logoId, OnEditTextClickListener onEditTextClickListener) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context).setCancelable(false);
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);
        dialogBuilder.setView(dialogView);

        EditText editText = dialogView.findViewById(R.id.dialogEditTextId);
        editText.setHint("Enter "+hintText);
        ImageView logoImageView = dialogView.findViewById(R.id.logoId);
        logoImageView.setImageDrawable(logoId);
        Button buttonSave = dialogView.findViewById(R.id.okButtonId);
        buttonSave.setText(context.getString(R.string.save) +" "+ hintText);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map.Entry<String,String> input = new AbstractMap.SimpleEntry<>(hintText, editText.getText().toString());
                onEditTextClickListener.onEditTextClick(input);
                alertDialog.dismiss();
            }
        });
    }

    public static void noteSaveDialog(Activity context) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context).setCancelable(false);
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_confirmation_layout, null);
        dialogBuilder.setView(dialogView);

        Button buttonDelete = dialogView.findViewById(R.id.okButtonId);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
                context.finish();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
