package com.example.govin.applockscreen.mangers;

import android.content.Context;

import com.example.govin.applockscreen.PinActivity;
import com.example.govin.applockscreen.PinCompatActivity;
import com.example.govin.applockscreen.PinFragmentActivity;


public class LockManager<T extends AppLockActivity> {

    /**
     * The static singleton instance
     */
    private static LockManager mInstance;
    private static AppLock mAppLocker;

    /**
     * Used to retrieve the static instance
     */
    public static LockManager getInstance() {
        synchronized (LockManager.class) {
            if (mInstance == null) {
                mInstance = new LockManager<>();
            }
        }
        return mInstance;
    }

    public void enableAppLock(Context context, Class<T> activityClass) {
        if (mAppLocker != null) {
            mAppLocker.disable();
        }
        mAppLocker = AppLockImpl.getInstance(context, activityClass);
        mAppLocker.enable();
    }


    public boolean isAppLockEnabled() {
        return (mAppLocker != null && (PinActivity.hasListeners() ||
                PinFragmentActivity.hasListeners() || PinCompatActivity.hasListeners()));
    }

    /**
     * Disables the app lock by calling {@link AppLock#disable()}
     */
    public void disableAppLock() {
        if (mAppLocker != null) {
            mAppLocker.disable();
        }
        mAppLocker = null;
    }

    /**
     * Disables the previous app lock and set a new one
     */
    public void setAppLock(AppLock appLocker) {
        if (mAppLocker != null) {
            mAppLocker.disable();
        }
        mAppLocker = appLocker;
    }

    /**
     * Get the {@link AppLock}. Used for defining custom timeouts etc...
     */
    public AppLock getAppLock() {
        return mAppLocker;
    }
}
