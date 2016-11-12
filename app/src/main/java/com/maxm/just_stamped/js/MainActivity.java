package com.maxm.just_stamped.js;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.maxm.just_stamped.js.googleRes.SlidingTabLayout;
import com.maxm.just_stamped.tabs.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private ActionBarDrawerToggle mToggle;                                           //позволяет воздействовать на переключатель на DrawerLayout
    private static final int RC_SIGN_IN = 9001;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    SlidingTabLayout slidingTabLayout;
    static byte numberOfTabs = 3;
    CharSequence titles[] = new CharSequence[numberOfTabs];
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth firebaseAuth;
    //Имя и почта текущего пользователя Google
    static String  userName, userEmail;

    /*
    Этот метод необходим, как точка входа в программу
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVariablesForGoogleAuth();
        setToolbar();
        setToggle();
        setTabs();
    }

   /*
    Этот метод выполняет действия по установке переключателя для вызова боковой панели
     */
    protected void setToggle() {
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);   /* передача указателя mDrawerLayout на объект из activity_main */
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
        Toolbar mToolbar = (Toolbar) findViewById(R.id.action_bar);
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
        Этот метод выполняет определение переменных их инициализацию для входа через Google
         */
    private void setVariablesForGoogleAuth(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("504359195684-rb0iu9qf8v1a5bfkd1hblpreks85tgb6.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        firebaseAuth = FirebaseAuth.getInstance();
        }

    /*
    Этот метод задает параметры слушателя кнопок для входа и выхода из аккаунта
    */
    @Override
    public void onClick(View v) {
        //switch(v.getId()){
         //   case R.id.google_authorization:
                signIn();
           //     break;
            //default:
             //   break;
     //   }
    }

    public void onClickSignOut(View v) {
        signOut();
        refreshUserData(false);
    }

    /*
    Этот метод вызывает диалоговое окно выбора Аккаунтов Google и посылает код RC_SIGN_IN
     */
    private void signIn () {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*
   Этот метод выполняет обработку входа и в случае успеха аутентификации выполняет блок if()
    */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                userEmail = acct.getEmail();
                userName = acct.getDisplayName();
                                String finalMessage = getResources().getString(R.string.welcome_email) + " " +  userEmail + "\n"
                        + getResources().getString(R.string.welcome_name) + " " + userName;
                refreshUserData(true, acct.getPhotoUrl());
                firebaseAuth.createUserWithEmailAndPassword(userEmail, userName);
                collapseExpandConstraintLayoutOnMessage(true);
                Toast.makeText(this, finalMessage , Toast.LENGTH_LONG).show();
            }
            else {

            }
        }
    }

    /*
    Этот метод выполняется при выходе из аккаунта Google
     */
    private void signOut () {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                firebaseAuth.signOut();
                Toast.makeText(MainActivity.this, "You are signed out" , Toast.LENGTH_LONG).show();
            }
        });
    }

    /*
    Этот метод выполняет информирование он неудачном соединении
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(MainActivity.this, connectionResult.toString() , Toast.LENGTH_LONG).show();
    }

    /*
    Этот метод выполняет обновление данных у шапки боковой панели
     */
    private void refreshUserData(boolean logIn) {
        TextView barName = (TextView) findViewById(R.id.bar_name);
        TextView barEmail = (TextView) findViewById(R.id.bar_email);
        if (logIn) {
            barName.setText(userName);
            barEmail.setText(userEmail);
        }
        else
        {
            barName.setText(getResources().getString(R.string.no_user));
            barEmail.setText(getResources().getString(R.string.no_user));
        }
    }

    private void refreshUserData(boolean logIn, Uri uri) {
        TextView barName = (TextView) findViewById(R.id.bar_name);
        TextView barEmail = (TextView) findViewById(R.id.bar_email);
        ImageView avatar = (ImageView) findViewById(R.id.user_icon);
        if (logIn) {
            barName.setText(userName);
            barEmail.setText(userEmail);
            //URL newurl = new URL(photo_url_str);
            //Uri.decode(uri);
        }
        else
        {
            barName.setText(getResources().getString(R.string.no_user));
            barEmail.setText(getResources().getString(R.string.no_user));
        }
    }

    /*
    Этот метод выполняет сворачивание и разворачивание сообщения о регистрации TabToolbar
     */
    public void collapseExpandConstraintLayoutOnMessage(Boolean expanded) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.message_action_bar);
        Animation animation;
        if (expanded) {
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.message_collapse);
            toolbar.startAnimation(animation);
        }
        else
        {
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.message_expand);
            toolbar.startAnimation(animation);
        }
    }
}

