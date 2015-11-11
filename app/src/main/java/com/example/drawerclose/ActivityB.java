package com.example.drawerclose;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by jware9 on 11/11/15.
 * Copyright 2015
 */
public class ActivityB extends AppCompatActivity {

    //ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Activity B");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //you can use an ActionBarDrawerToggle instead, and the bug is the same.  I'm omitting it here for simplicity
        //drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        //drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_TITLE);

        findViewById(android.R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityB.this, ActivityA.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

                //Make sure the activity starts with the drawer closed next time.  This doesn't trigger the bug, you can comment it out if you like
                drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
