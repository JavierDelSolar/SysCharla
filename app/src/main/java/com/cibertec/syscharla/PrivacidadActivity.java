package com.cibertec.syscharla;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;

public class PrivacidadActivity extends AppCompatActivity {

    GridLayout glMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacidad);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        glMenu = (GridLayout)findViewById(R.id.glMenu);
        setSingleEvents(glMenu);
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

    private  void setSingleEvents(GridLayout glMenu)
    {
        for (int i = 0; i < glMenu.getChildCount(); i++){

            CardView cardView = (CardView)glMenu.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(),"Click: " +String.valueOf(finalI),Toast.LENGTH_SHORT ).show();

                    if(finalI == 0){
                        Intent intent = new Intent(getApplicationContext(), CambioPassActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 1){
                        Intent intent = new Intent(getApplicationContext(), CambioEmailActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
