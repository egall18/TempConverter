package edu.csumb.gall3079.tempconverter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AboutActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int passedInt = bundle.getInt("myIntValue");
            String passString = bundle.getString("myStringValue");
            Toast.makeText(this,
                    "Int Value : " + passedInt + "\nStringValue : " + passString,
                    Toast.LENGTH_LONG).show();
        }
        Button button = findViewById(R.id.ok_button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.d("AboutActivity", "onClick called");
        // return data back to MainActivity
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("myReturnCode", 7);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}