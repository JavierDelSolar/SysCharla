package com.cibertec.syscharla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class PrivacidadActivity extends AppCompatActivity {

    GridLayout glMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacidad);

        glMenu = (GridLayout)findViewById(R.id.glMenu);
        setSingleEvents(glMenu);
    }
    private  void setSingleEvents(GridLayout glMenu) {

        for (int i = 0; i < glMenu.getChildCount(); i++) {

            CardView cardView = (CardView) glMenu.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(),"Click: " +String.valueOf(finalI),Toast.LENGTH_SHORT ).show();

                    if (finalI == 0) {
                        //Intent intent = new Intent(getApplicationContext(), CambioPasswordActivity.class);
                        //startActivity(intent);
                    } else if (finalI == 1) {
                        Intent intent = new Intent(getApplicationContext(), CambioEmailActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
