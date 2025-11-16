package com.overlord.ios.assignment2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu, container, false);
    }

    EditText[] et = new EditText[4];
    Button edit, reset;
    RadioGroup genders, notifications;
    RadioButton[] genderOption = new RadioButton[2];
    RadioButton[] notificationOption = new RadioButton[2];
    ImageButton theme;
    TextView txt;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit = view.findViewById(R.id.edit);
        genders = view.findViewById(R.id.gender);
        notifications = view.findViewById(R.id.notification);
        et[0] = view.findViewById(R.id.name);
        et[1] = view.findViewById(R.id.email);
        et[2] = view.findViewById(R.id.phone);
        et[3] = view.findViewById(R.id.password);
        theme = view.findViewById(R.id.theme);
        reset = view.findViewById(R.id.reset);
        txt = view.findViewById(R.id.t2);

        for (int i = 0, j = 0; i < genders.getChildCount(); i++)
            if (genders.getChildAt(i) instanceof RadioButton)
                genderOption[j++] = (RadioButton) genders.getChildAt(i);

        for (int i = 0, j = 0; i < notifications.getChildCount(); i++)
            if (notifications.getChildAt(i) instanceof RadioButton)
                notificationOption[j++] = (RadioButton) notifications.getChildAt(i);


        et[0].setEnabled(false);
        et[1].setEnabled(false);
        et[2].setEnabled(false);
        et[3].setEnabled(false);

        genderOption[0].setEnabled(false);
        genderOption[1].setEnabled(false);

        notificationOption[0].setEnabled(false);
        notificationOption[1].setEnabled(false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = prefs.edit();
        PreferenceHelper helper = new PreferenceHelper();
        helper.DisplayEdit(prefs, et[0], et[2], et[1], et[3], genderOption[0], genderOption[1], notificationOption[0], notificationOption[1]);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check = et[0].isEnabled();

                if (!check) edit.setText("Save");
                else {
                    String GENDERS = "";
                    String NOTIFICATIONS = "";

                    GENDERS = genderOption[0].isChecked() ? "Male" : "Female";
                    NOTIFICATIONS = notificationOption[0].isChecked() ? "Enabled" : "Disabled";

                    String alert = helper.validate(et[0], et[2], et[1], et[3]);
                    if(!alert.isEmpty())
                    {
                        Toast.makeText(getContext(), alert, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    helper.SetPrefs(editor, et[0], et[2], et[1], et[3], GENDERS, NOTIFICATIONS);

                    Calendar temp = Calendar.getInstance();
                    int day = temp.get(Calendar.DAY_OF_MONTH);
                    int month = temp.get(Calendar.MONTH) + 1;
                    int year = temp.get(Calendar.YEAR);

                    txt.setText(day+"/"+month+"/"+year);

                    edit.setText("Edit Profile");
                }

                et[0].setEnabled(!check);
                et[1].setEnabled(!check);
                et[2].setEnabled(!check);
                et[3].setEnabled(!check);
                genderOption[0].setEnabled(!check);
                genderOption[1].setEnabled(!check);
                notificationOption[0].setEnabled(!check);
                notificationOption[1].setEnabled(!check);
            }
        });

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String GENDERS = "";
                String NOTIFICATIONS = "";


                if(genderOption[0].isChecked())
                    GENDERS = "Male";
                else if(genderOption[1].isChecked())
                    GENDERS = "Female";

                NOTIFICATIONS = notificationOption[0].isChecked() ? "Enabled" : "Disabled";

                String alert = helper.validate(et[0], et[2], et[1], et[3]);
                if(!alert.isEmpty())
                {
                    Toast.makeText(getContext(), alert, Toast.LENGTH_SHORT);
                    return;
                }
                helper.SetPrefs(editor, et[0], et[2], et[1], et[3], GENDERS, NOTIFICATIONS);
                helper.changeTheme(prefs, editor);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                genderOption[0].setChecked(false);
                genderOption[1].setChecked(false);
                helper.DisplayEdit(prefs, et[0], et[2], et[1], et[3], genderOption[0], genderOption[1], notificationOption[0], notificationOption[1]);
            }
        });
    }

}