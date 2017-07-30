package com.example.prudnikoff.valley;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

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

        setUpListView();
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
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort) {
            showSortPopUpMenu(findViewById(id));
            return true;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_info: {
                goInfoActivity();
            } break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpListView() {
        EventElement[] elements = {
                new EventElement("Java MeetUp", "#java #spring #javaFX", "15.07.2017", "New York", "25$"),
                new EventElement("Ruby Hackathon", "#ruby #rubyonrails", "20.07.2017", "Minsk", "free"),
                new EventElement("C++ Conference", "#c++ #c #arduino", "02.08.2017", "Moscow", "50$"),
                new EventElement("Java Day", "#java #android", "20.08.2017", "San Francisco", "free"),
                new EventElement("Apple WWDC", "#apple #iphone #ipad", "07.09.2017", "San Diego", "150$"),
                new EventElement("Android for developers", "#java #android #google", "15.09.2017", "Minsk", "free"),
                new EventElement("Java MeetUp", "#java #spring #javaFX", "15.07.2017", "New York", "25$"),
                new EventElement("Swift MeetUp", "#swift #apple", "20.09.2017", "Minsk", "free"),
                new EventElement("Ruby Hackathon", "#ruby #rubyonrails", "27.09.2017", "Minsk", "free"),
                new EventElement("C++ Conference", "#c++ #c #arduino", "02.10.2017", "Moscow", "70$"),
        };
        EventAdapter adapter = new EventAdapter(this, R.layout.element_row, elements);
        ListView eventsListView = (ListView)findViewById(R.id.events_listView);
        eventsListView.setAdapter(adapter);
    }

    private void goInfoActivity() {
        Intent intent = new Intent(this, FullEventActivity.class);
        startActivity(intent);
    }

    private void showSortPopUpMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.sort_popup_menu, popup.getMenu());
        popup.show();
    }
}
