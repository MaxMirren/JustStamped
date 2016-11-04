package com.maxm.just_stamped.js;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.maxm.just_stamped.authorization.SignInUpWithGoogle;
import com.maxm.just_stamped.js.googleRes.SlidingTabLayout;
import com.maxm.just_stamped.tabs.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;                                              //позволяет воздействовать на DrawerLayout
    private ActionBarDrawerToggle mToggle;                                           //позволяет воздействовать на переключатель на DrawerLayout
    private Toolbar mToolbar;                                                        //позволяет воздействовать на верхнюю панель
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    SlidingTabLayout slidingTabLayout;
    byte numberOfTabs = 3;
    CharSequence titles[] = new CharSequence[numberOfTabs];

    //CharSequence titles[] = {"ОБЩИЕ", "МОИ", "ЧАСТО\nПОСЕЩАЕМЫЕ"};


    /*
    Этот метод необходим, как точка входа в программу
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setToggle();
        setTabs();
    }

   /*
    Этот метод выполняет действия по установке переключателя для вызова боковой панели
     */
    protected void setToggle() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);                /* передача указателя mDrawerLayout на объект из activity_main */
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.to_open,
                                            R.string.to_close);                                  /* вызов конструктора для переключателя mToggle */
        mDrawerLayout.addDrawerListener(mToggle);                                      /* добавление прослушивателя mToggle к mDrawerLayout */
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                         /* добавление поддержки клавиши для вызова боковой панели */
    }

    /*
    Этот метод выполняет обработку нажатий на элементы бокового меню
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Этот метод выполняет обработку нажатий на элементы бокового меню
     */
    protected void setToolbar () {
        mToolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(mToolbar);
    }

    /*
    Этот метод добавляет вкладки, count - количество вкладок, tabNames - заголовки вкладок прописными символами
     */
    protected void setTabs(){
        titles[0] = getResources().getString(R.string.common);
        titles[1] = getResources().getString(R.string.mine);
        titles[2] = getResources().getString(R.string.most_visited);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, numberOfTabs);
        viewPager.setAdapter(viewPagerAdapter);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setDistributeEvenly(true);
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });
        // Setting the ViewPager For the SlidingTabsLayout
        slidingTabLayout.setViewPager(viewPager);
    }

    /*
    Этот метод задает параметры слушателя кнопки для google_authorization c message_first_message.xml
    */
    public void signInUpWithGoogle (View vew) {
        Intent intent = new Intent(this, SignInUpWithGoogle.class);
        startActivity(intent);
    }
}

