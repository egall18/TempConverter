package edu.csumb.gall3079.tempconverter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Temp> temps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate called");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onCreate called");
                EditText edittext = findViewById(R.id.edittext);
                String ins = edittext.getText().toString();
                Log.d("MainActivity", "editText view="  + ins);
                TextView result = findViewById(R.id.result);
                try {
                    double ct = Double.parseDouble(ins);
                    double ft = 9.0 / 5.0 * ct + 32;
                    Temp t = new Temp(ft, ct);
                    temps.add(t);
                    Log.d("MainActivity", "new temp = "+ t.toString());
                    result.setText(Double.toString(ft));
                } catch (Exception e) {
                    result.setText("Invalid input.");
                }
            }
        });
        Button button2 = findViewById(R.id.about_button);
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Log.d("MainActivity", "about button clicked");
                Intent intent = new Intent(MainActivity.this, edu.csumb.gall3079.tempconverter.AboutActivity.class);
                // passing values to another activity
                Bundle bundle = new Bundle();
                bundle.putInt("myIntValue", 5);
                bundle.putString("myStringValue", "CSUMB");
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            int rc = bundle.getInt("myReturnCode");
            Log.d("MainActivity", "return code from About " + rc);
        } else {
            Log.d("MainActivity", "resultCode " + resultCode);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.history) {
            Log.d("MainActivity", "history selected");
            // start a new Activity with no return result
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
