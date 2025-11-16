package com.overlord.ios.assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PreferenceHelper {

    public void SetPrefs(SharedPreferences.Editor editor, EditText name, EditText phone, EditText email, EditText password, String gender, String notification) {
        if (name.getText().length() > 0)
            editor.putString("name", name.getText().toString());
        if (email.getText().length() > 0)
            editor.putString("email", email.getText().toString());
        if (phone.getText().length() > 0)
            editor.putString("phone", phone.getText().toString());
        if (gender.length() > 0)
            editor.putString("gender", gender);
        if (notification.length() > 0)
            editor.putString("notification", notification);
        if (password.getText().length() > 0)
            editor.putString("password", password.getText().toString());
        editor.apply();
    }

    public void DisplayHome(SharedPreferences prefs, TextView name, TextView phone, TextView email, TextView password, TextView gender, TextView notification) {
        String Name = prefs.getString("name", "Not Set");
        String Email = prefs.getString("email", "Not Set");
        String Phone = prefs.getString("phone", "Not Set");
        String Gender = prefs.getString("gender", "Not Set");
        String Notification = prefs.getString("notification", "Not Set");
        String Password = prefs.getString("password", "Not Set");

        name.setText(Name);
        phone.setText(Phone);
        email.setText(Email);
        gender.setText(Gender);
        notification.setText(Notification);
        password.setText(Password);
    }

    public void DisplayEdit(SharedPreferences prefs, EditText name, TextView phone, EditText email, EditText password, RadioButton gender, RadioButton gender2, RadioButton notification, RadioButton notification2) {
        String Name = prefs.getString("name", "Not Set");
        String Email = prefs.getString("email", "Not Set");
        String Phone = prefs.getString("phone", "Not Set");
        String Gender = prefs.getString("gender", "Not Set");
        String Notification = prefs.getString("notification", "Not Set");
        String Password = prefs.getString("password", "Not Set");

        name.setHint(Name);
        phone.setHint(Phone);
        email.setHint(Email);
        if (Gender.equals("Male"))
            gender.setChecked(true);
        else
            gender2.setChecked(true);
        if (Notification.equals("Enabled"))
            notification.setChecked(true);
        else
            notification2.setChecked(true);
        password.setHint(Password);
    }


}
