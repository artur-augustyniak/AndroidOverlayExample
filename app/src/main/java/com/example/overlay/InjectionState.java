package com.example.overlay;

public final class InjectionState {
    private static InjectionState instance;
    private boolean injectionInProgress;

    private InjectionState() {
        this.injectionInProgress = false;
    }

    synchronized public boolean isInjectionInProgress() {
        return injectionInProgress;
    }

    synchronized public void setInjectionInProgress(boolean injectionInProgress) {
        this.injectionInProgress = injectionInProgress;
    }

    public static InjectionState getInstance() {
        if (instance == null) {
            instance = new InjectionState();
        }
        return instance;
    }
}