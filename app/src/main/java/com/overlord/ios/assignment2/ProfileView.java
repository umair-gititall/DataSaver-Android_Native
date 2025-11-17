package com.overlord.ios.assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileView.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileView newInstance(String param1, String param2) {
        ProfileView fragment = new ProfileView();
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
        return inflater.inflate(R.layout.fragment_profile_view, container, false);
    }


    TextView name, phone, email, gender, notification, password;
    ImageButton theme, exit;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.name);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        gender = view.findViewById(R.id.gender);
        notification = view.findViewById(R.id.notification);
        password = view.findViewById(R.id.password);
        theme = view.findViewById(R.id.theme);
        exit = view.findViewById(R.id.exit);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        PreferenceHelper helper = new PreferenceHelper();

        helper.DisplayHome(prefs, name, phone, email, password, gender, notification);

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.changeTheme(prefs, prefs.edit());
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(80);
                requireActivity().finishAffinity();
            }
        });
    }
}