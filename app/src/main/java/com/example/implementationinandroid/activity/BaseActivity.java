package com.example.implementationinandroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.implementationinandroid.fragment.BaseFragment;
import com.example.implementationinandroid.fragment.FragmentLoaderController;
import com.example.implementationinandroid.fragmentbasecontroller.BaseControllerFragment;

public abstract class BaseActivity extends FragmentActivity implements BaseFragment.FragmentLoader {

    protected FragmentLoaderController mFragmentLoaderController;
    private FragmentManager.OnBackStackChangedListener backStackChangedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBackStackChangedListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportFragmentManager().addOnBackStackChangedListener(backStackChangedListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSupportFragmentManager().removeOnBackStackChangedListener(backStackChangedListener);
    }


    private void initBackStackChangedListener() {
        backStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                final FragmentManager manager = getSupportFragmentManager();
                if (manager != null) {
                    if (manager.getBackStackEntryCount() > 0) {
                        final FragmentManager.BackStackEntry stackEntry = manager.getBackStackEntryAt(
                                manager.getBackStackEntryCount() - 1);
                        if (stackEntry != null) {
                            final Fragment currFragment = manager.findFragmentByTag(stackEntry.getName());
//                            if (currFragment != null && currFragment instanceof BaseFragment) {
//                                ((BaseFragment) currFragment).updateTitle();
//                            }
                        }
                    } else {
                        // Finish the activity if there are no more fragments
                        finish();
                    }
                }
            }
        };
    }


    public void createFragmentLoader(BaseActivity activity, int fragmentContainerId) {
        mFragmentLoaderController = new FragmentLoaderController(activity, fragmentContainerId);
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack, String fragmentName) {
        if (mFragmentLoaderController != null) {
            mFragmentLoaderController.loadFragment(fragment, addToBackStack, fragmentName);
        }
    }

    public void loadFragment(BaseControllerFragment fragment, boolean addToBackStack) {
        if (mFragmentLoaderController != null) {
            loadFragment(fragment, addToBackStack, fragment.getClass().getSimpleName());
        }
    }

    @Override
    public void loadFragment(BaseFragment fragment, boolean addToBackStack) {
        if (mFragmentLoaderController != null) {
            loadFragment(fragment, addToBackStack, fragment.getClass().getSimpleName());
        }
    }

    @Override
    public boolean popLastFragment() {
        if (mFragmentLoaderController != null) {
            return mFragmentLoaderController.popFragment();
        } else {
            return false;
        }
    }

    @Override
    public String getLastFragmentTag() {
        if (mFragmentLoaderController != null) {
            return mFragmentLoaderController.getLastFragmentTag();
        } else {
            return "";
        }
    }

    @Override
    public void popAllFragmentStack() {
        if (mFragmentLoaderController != null) {
            mFragmentLoaderController.popAllFragmentStack();
        }
    }

    @Override
    public void popUpToThisFragmentFromBackStack(String fragmentName) {
        if (mFragmentLoaderController != null) {
            mFragmentLoaderController.popUpToThisFragmentFromBackStack(fragmentName);
        }
    }
}
