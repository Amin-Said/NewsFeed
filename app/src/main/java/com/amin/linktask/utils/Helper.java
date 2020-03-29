package com.amin.linktask.utils;

import com.amin.linktask.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Helper {
    public static String ConvertToFormattedDate(String currentFormat) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(currentFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
        return formattedDate;
    }


}
