package com.overlord.ios.assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    FrameLayout frame;
    Fragment frag = null;
    FragmentManager Manager;
    FragmentTransaction Transaction;
    String LoadedFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        frame = findViewById(R.id.homePage);

        if (savedInstanceState != null && savedInstanceState.getString("Frag").equals("Menu")) {
            frag = new Menu();
            LoadedFrag = "Menu";
            tabs.getTabAt(1).select();
        }
        else {
            frag = new ProfileView();
            LoadedFrag = "Profile";
        }

        Manager = getSupportFragmentManager();
        Transaction = Manager.beginTransaction();
        Transaction.replace(R.id.homePage, frag);
        Transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Transaction.commit();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                frag = null;

                switch (tab.getPosition()) {
                    case 0:
                        frag = new ProfileView();
                        LoadedFrag = "Profile";
                        break;
                    case 1:
                        frag = new Menu();
                        LoadedFrag = "Menu";
                        break;
                }
                Transaction = Manager.beginTransaction();
                Transaction.replace(R.id.homePage, frag);
                Transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                Transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Frag", LoadedFrag);
    }
}