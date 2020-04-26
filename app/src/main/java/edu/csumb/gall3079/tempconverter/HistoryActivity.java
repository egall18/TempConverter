package edu.csumb.gall3079.tempconverter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HistoryActivity extends AppCompatActivity {

    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ListView rv = findViewById(R.id.list_view);
        adapter = new HistoryAdapter();
        rv.setAdapter( adapter );
    }

    /*
     * convert Temp to a  history_item containing TextViews for centigrade, fahrenheit
     */
    private class HistoryAdapter extends ArrayAdapter<Temp> {

        private HistoryAdapter() {
            super(HistoryActivity.this, R.layout.history_item, MainActivity.temps);
        }

        // display history.get(position) into history_item.xml
        @Override
        public View getView(final int position, View view, ViewGroup parent) {

            LayoutInflater inflater=HistoryActivity.this.getLayoutInflater();
            View itemView=inflater.inflate(R.layout.history_item, null,true);
            TextView tempFview = itemView.findViewById(R.id.fahrenheit);
            TextView tempCview = itemView.findViewById(R.id.centigrade);
            final Temp temp = MainActivity.temps.get(position);
            tempFview.setText( Double.toString(temp.f) + "\u00B0F");
            tempCview.setText( Double.toString(temp.c) + "\u00B0C");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // confirm dialog to delete this history item
                    AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                    String msg = Double.toString(temp.c)+ "\u00B0C "
                            + Double.toString(temp.f) + "\u00B0F "
                            + " Really delete?";
                    builder.setTitle(msg);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user selected yes
                            MainActivity.temps.remove(position);
                            // refresh UI
                            adapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user select no, do nothing
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            return itemView;
        }
    }
}
