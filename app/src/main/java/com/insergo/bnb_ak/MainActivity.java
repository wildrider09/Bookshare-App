package com.insergo.bnb_ak;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
        Home sc=new Home();
        ft.replace(R.id.container,sc).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Home sc=new Home();
            ft.replace(R.id.container,sc).commit();
        } else if (id == R.id.nav_gallery) {
            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Share_Books sc=new Share_Books();
            ft.replace(R.id.container,sc).commit();


        } else if (id == R.id.nav_slideshow) {
            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Search_Books sc=new Search_Books();
            ft.replace(R.id.container,sc).commit();


        } else if (id == R.id.nav_about) {
            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            About sc=new About();
            ft.replace(R.id.container,sc).commit();


        }
        else if (id == R.id.nav_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "BOOK_STORE");
                String sAux = "Let me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps\n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "Choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        }


        else if (id == R.id.nav_send) {
            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Help sc=new Help();
            ft.replace(R.id.container,sc).commit();


        }
        else if (id == R.id.nav_manage) {
            android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
            Contact sc=new Contact();
            ft.replace(R.id.container,sc).commit();


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
