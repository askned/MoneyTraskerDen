package com.example.rf1.myapplication2;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ListView left_drawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        left_drawer = (ListView) findViewById(R.id.left_drawer);

//        String[] navigationData = getResources().getStringArray(R.array.screen_aray);
        String[] navigationData = new String[]{"Траты", "Категории", "Статистика"};
        ArrayAdapter<String> navigationDrawerAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, navigationData);
        left_drawer.setAdapter(navigationDrawerAdapter);
        left_drawer.setOnItemClickListener(new DrawerItemClickListener());


        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new TransactionFragment()).commit();

    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                left_drawer.setItemChecked(position, true);
                drawerLayout.closeDrawer(left_drawer);
                setTitle(getString(R.string.transactions));
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new TransactionFragment()).commit();
            }
            if (position == 1) {
                left_drawer.setItemChecked(position, true);
                drawerLayout.closeDrawer(left_drawer);
                setTitle(getString(R.string.categorii));
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new CategoryFragment()).commit();
            }
            if (position == 2) {
                left_drawer.setItemChecked(position, true);
                drawerLayout.closeDrawer(left_drawer);
                setTitle(getString(R.string.statistics));
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new StatmentFragment()).commit();
            }
        }
    }
}