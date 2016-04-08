package com.makemoji.sbaar.mojilist;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class ActivateActivity extends AppCompatActivity {

    TextView activateText;
    Button activateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        activateButton = (Button) findViewById(R.id.kb_activate);
        activateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0);
            }
        });
        activateText = (TextView)findViewById(R.id.activate_tv);
    }
    @Override
    protected void onResume(){
        super.onResume();
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        for (InputMethodInfo imi: imm.getEnabledInputMethodList()){
            if (BuildConfig.APPLICATION_ID.equals(imi.getPackageName())){
                activateButton.setEnabled(false);
                activateButton.setText("Enabled!");
                activateText.setText("You're good to go! You can now select the MakeMoji Keyboard when writing messages");
                return;
            }
        }
        activateButton.setText("Enable Keyboard");
        activateText.setText("");

    }

}