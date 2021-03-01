package com.example.studentapp.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.R;

import org.w3c.dom.Text;

public class CustomToast {

    public static void createToast(Context context, String message, boolean error) {
        Toast toast = new Toast(context);
        //View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        View view = View.inflate(context, R.layout.custom_toast, null);
        TextView toastTextView = view.findViewById(R.id.textViewToast);

        SpannableString spannableString = new SpannableString(message);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, spannableString.length(), 0);

        toastTextView.setText(spannableString);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);

        if(error)
        {
            toastTextView.setTextColor(Color.parseColor("#7d1128"));
        }
        else
        {
            toastTextView.setTextColor(Color.parseColor("#2c6e49"));
        }

        toast.setGravity(Gravity.BOTTOM, 32,32);
        toast.show();
    }
}
