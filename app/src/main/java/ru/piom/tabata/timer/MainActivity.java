package ru.piom.tabata.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private TextView counter;
    private TextView loops;
    private int loopsCount = 0;


    boolean isRun = false;
    FloatingActionButton fab;
    Runnable updateCurrentTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler = new Handler();
        counter = (TextView) findViewById(R.id.counter);
        loops = (TextView) findViewById(R.id.loops);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doIncrease();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void doIncrease() {
        if (updateCurrentTime == null) {
            updateCurrentTime = new Runnable() {
                @Override
                public void run() {
                    if (!isRun) {
                        handler.removeCallbacks(this);
                    } else {
                        int value = Integer.valueOf(counter.getText().toString());
                        if (value == 0) {
                            value = 15;
                            loopsCount++;
                        } else {
                            value--;
                        }
                        counter.setText(String.valueOf(value));  // Update the current Time
                        handler.postDelayed(this, 1000);
                    }
                    loops.setText(String.valueOf(loopsCount));
                }
            };
        }
        if (isRun) {
            fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play, getTheme()));
            isRun = false;
        } else {
            handler.postDelayed(updateCurrentTime, 0);
            fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause, getTheme()));
            isRun = true;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
