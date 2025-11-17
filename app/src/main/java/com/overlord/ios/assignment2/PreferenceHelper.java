package com.overlord.ios.assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import org.w3c.dom.Text;

public class PreferenceHelper {

    public void SetPrefs(SharedPreferences.Editor editor, EditText name, EditText phone, EditText email, EditText password, String gender, String notification, String time) {
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
        if(time.length() > 0)
            editor.putString("time", time);
        editor.apply();
    }

    public void DisplayHome(SharedPreferences prefs, TextView name, TextView phone, TextView email, TextView password, TextView gender, TextView notification) {
        String Name = prefs.getString("name", "Not Set");
        String Email = prefs.getString("email", "Not Set");
        String Phone = prefs.getString("phone", "Not Set");
        String Gender = prefs.getString("gender", "Not Set");
        String Notification = prefs.getString("notification", "Disabled");
        String Password = prefs.getString("password", "Not Set");
        String Theme = prefs.getString("theme", "Light");

        setTheme(Theme);
        name.setText(" "+ Name);
        phone.setText(" " + Phone);
        email.setText(" "+ Email);
        if(Gender.equals("Male"))
            gender.setCompoundDrawablesWithIntrinsicBounds(R.drawable.boy_svgrepo_com,0,0, 0);
        if(Gender.equals("Female"))
            gender.setCompoundDrawablesWithIntrinsicBounds(R.drawable.girl_svgrepo_com,0,0, 0);
        gender.setText(" " + Gender);
        if(Notification.equals("Enabled"))
            notification.setCompoundDrawablesWithIntrinsicBounds(R.drawable.smartphone_svgrepo_com, 0, 0, 0);
        else
            notification.setCompoundDrawablesWithIntrinsicBounds(R.drawable.smartphone_vibration_svgrepo_com, 0, 0, 0);

        notification.setText(" " + Notification);
        password.setText(" " + Password);
    }

    public void DisplayEdit(SharedPreferences prefs, EditText name, TextView phone, EditText email, EditText password, RadioButton gender, RadioButton gender2, RadioButton notification, RadioButton notification2, TextView txt) {
        String Name = prefs.getString("name", "Not Set");
        String Email = prefs.getString("email", "Not Set");
        String Phone = prefs.getString("phone", "Not Set");
        String Gender = prefs.getString("gender", "Not Set");
        String Notification = prefs.getString("notification", "Not Set");
        String Password = prefs.getString("password", "Not Set");
        String Theme = prefs.getString("theme", "Light");
        String Time = prefs.getString("time", "NaN");

        setTheme(Theme);
        name.setHint(" "+ Name);
        name.setText("");
        phone.setHint(" "+Phone);
        phone.setText("");
        email.setHint(" "+Email);
        email.setText("");
        if (Gender.equals("Male"))
            gender.setChecked(true);
        else if(Gender.equals("Female"))
            gender2.setChecked(true);
        if (Notification.equals("Enabled"))
            notification.setChecked(true);
        else
            notification2.setChecked(true);

        String temp = "•".repeat(Password.length());
        password.setHint(" " + temp);
        if(Password.equals("Not Set"))
            password.setHint(" " + Password);
        password.setText("");
        txt.setText(Time);
    }

    public void setTheme(String theme)
    {
        if(theme.equals("Light"))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
    public void changeTheme(SharedPreferences prefs, SharedPreferences.Editor editor)
    {
        String Theme = prefs.getString("theme", "Light");

        if(Theme.equals("Light")) {
            editor.putString("theme", "Dark");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            editor.putString("theme", "Light");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        editor.apply();
    }
    
    public String validate(EditText name, EditText phone, EditText email, EditText password)
    {
        if(name.length() > 0)
            if(!name.getText().toString().matches("[a-zA-z ]+"))
                return "Invalid Name";
        if(email.length() > 0)
            if(!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
                return "Invalid Email Address";
        if(phone.length() > 0)
            if(!phone.getText().toString().matches("\\+?\\d{10,12}"))
                return "Invalid Phone Number";
        if(password.length() > 0)
            if(password.length() < 8)
                return "Min Password Length = 8";
        return "";
    }

}
