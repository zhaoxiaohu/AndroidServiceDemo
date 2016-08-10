package com.example.guohl.myservice.service.normal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.guohl.myservice.R;
import com.example.guohl.myservice.service.bind.bind.BindingActivity;
import com.example.guohl.myservice.service.bind.messenger.ActivityMessenger;

public class MainActivity extends AppCompatActivity {

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "start service", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                /*stopSelf(msg.arg1); msg.arg1与最后一次生成的startId相同时 才停止服务*/
                /*Intent intent = new Intent(MainActivity.this, HelloIntentService.class);
                intent.putExtra("from", "click " + count);
                startService(intent);
                Utils.printThreadInfo();

                count++;*/

                /*start service*/
                /*Intent intent = new Intent(MainActivity.this, HelloService.class);
                intent.putExtra("from", "click " + count);
                startService(intent);
                Utils.printThreadInfo();

                count++;*/

                /* goto-> bind service demo*/
                /*startActivity(new Intent(MainActivity.this, BindingActivity.class));*/

                /* goto-> messenger service demo*/
                startActivity(new Intent(MainActivity.this, ActivityMessenger.class));
            }
        });
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
