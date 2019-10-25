package com.cibertec.syscharla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.cibertec.syscharla.Fragments.CharlasFragment;
import com.cibertec.syscharla.Fragments.MiPerfilFragment;
import com.cibertec.syscharla.Fragments.MisCharlasFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // BottomNavigationViewHelper.disableShiftMode(navigation);

        Fragment selectedfragment =  new CharlasFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedfragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_Charlas:
                    selectedfragment = new CharlasFragment();
                    break;
                case R.id.navigation_MisCharlas:
                    selectedfragment = new MisCharlasFragment();
                    break;
                case R.id.navigation_MiPerfil:
                    selectedfragment = new MiPerfilFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
            return true;
        }
    };
}