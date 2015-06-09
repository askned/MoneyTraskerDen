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

import com.example.rf1.myapplication2.auth.SessionManager;
import com.example.rf1.myapplication2.rest.RestClient;
import com.example.rf1.myapplication2.rest.TransactionRes;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    @ViewById
    Toolbar toolbar;

    @ViewById
    DrawerLayout drawerLayout;

    @ViewById
    ListView left_drawer;

    @RestService
    RestClient api;

    @Bean
    SessionManager sessionManager;

    ActionBarDrawerToggle drawerToggle;


    @AfterViews
    void ready() {
        String[] navigationData = new String[]{getString(R.string.transactions), getString(R.string.categorii), getString(R.string.statistics)};
        ArrayAdapter<String> navigationDrawerAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, navigationData);
        left_drawer.setAdapter(navigationDrawerAdapter);
        left_drawer.setOnItemClickListener(new DrawerItemClickListener());


        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new TransactionFragment_()).commit();

        testNet();
    }

    @Receiver(actions = {SessionManager.SESSION_OPENED_BROADCAST}, registerAt = Receiver.RegisterAt.OnResumeOnPause, local = true)
    void onSessionOpen() {
        testNet();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sessionManager.login(this);
    }

    @Background
    void testNet() {

        //    sessionManager.createAccount("den", login.authToken);
        //   AuthenticatorInterceptor.authToken = login.authToken;
        //   api.addCategory("second");
        //      api.addBalance(100000);
        //    final Result result = api.addTransactions(2200, "airplane", "2015-05-25");
        final TransactionRes transactions = api.getTransactions();
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
            switch (position) {
                case 0:
                left_drawer.setItemChecked(position, true);
                drawerLayout.closeDrawer(left_drawer);
                setTitle(getString(R.string.transactions));
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, TransactionFragment_.builder().build()).commit();
                    break;

                case 1:
                left_drawer.setItemChecked(position, true);
                drawerLayout.closeDrawer(left_drawer);
                setTitle(getString(R.string.categorii));
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, CategoryFragment_.builder().build()).commit();
                    break;
                case 2:

                left_drawer.setItemChecked(position, true);
                drawerLayout.closeDrawer(left_drawer);
                setTitle(getString(R.string.statistics));
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, StatmentFragment_.builder().build()).commit();
                    break;
            }
        }
    }

}
