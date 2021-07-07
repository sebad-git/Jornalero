package com.sdm.jornalero.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sdm.jornalero.R;

public class SplashActivity extends AppCompatActivity {

    //Permissions Codes.
    public static final int REQUEST_APP_PERMISSIONS = 1;
    private View logo;

    //Permissions.
    private static final String[] permissions = new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.logo = this.findViewById(R.id.logo_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final SplashActivity activity = this;
        Animation animacionSplash = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        animacionSplash.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation) {
                activity.startApplication();
            }
            @Override public void onAnimationRepeat(Animation animation){}});
        this.logo.startAnimation(animacionSplash);

    }

    public boolean hasPermissions(String[] permisionArray){
        boolean validatedPermissions = true;
        for ( String permission : permisionArray) { if(!this.hasPermission(permission)){ validatedPermissions = false; } }
        return validatedPermissions;
    }
    public boolean hasPermission(String permision) {
        if (Build.VERSION.SDK_INT < 23) { return true; }
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, permision);
    }

    public void askPermissions(String[] permisos, int requestCode) {
        if(!this.hasPermissions(permisos)){
            ActivityCompat.requestPermissions(this, permisos, requestCode);
        }
    }

    private void startApplication(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(intent);
    }
}
