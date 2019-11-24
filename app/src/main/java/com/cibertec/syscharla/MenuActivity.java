package com.cibertec.syscharla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // BottomNavigationViewHelper.disableShiftMode(navigation);

        Fragment selectedfragment =  new CharlasFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this,LogueoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
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
