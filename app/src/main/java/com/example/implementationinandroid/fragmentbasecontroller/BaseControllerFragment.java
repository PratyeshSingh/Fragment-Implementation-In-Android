package com.example.implementationinandroid.fragmentbasecontroller;

import android.support.v4.app.Fragment;

import com.example.implementationinandroid.fragment.BaseFragment;
import com.example.implementationinandroid.fragment.FragmentLoaderController;

public class BaseControllerFragment extends Fragment {
    protected FragmentLoaderController mFragmentLoaderController;

    public void createFragmentLoader(Fragment fragment, int fragmentContainerId) {
        mFragmentLoaderController = new FragmentLoaderController(fragment, fragmentContainerId);
    }

    public void loadFragment(BaseFragment fragment, boolean addToBackStack, String fragmentName) {
        if (mFragmentLoaderController != null) {
            mFragmentLoaderController.loadFragment(fragment, addToBackStack, fragmentName);
        }
    }

    public void loadFragment(BaseFragment fragment, boolean addToBackStack) {
        if (mFragmentLoaderController != null) {
            mFragmentLoaderController.loadFragment(fragment, addToBackStack, fragment.getClass().getSimpleName());
        }
    }

    public boolean popLastFragment() {
        if (mFragmentLoaderController != null) {
            return mFragmentLoaderController.popFragment();
        } else {
            return false;
        }
    }

    public String getLastFragmentTag() {
        if (mFragmentLoaderController != null) {
            return mFragmentLoaderController.getLastFragmentTag();
        } else {
            return "";
        }
    }

    public void popAllFragmentStack() {
        if (mFragmentLoaderController != null) {
            mFragmentLoaderController.popAllFragmentStack();
        }
    }

    public void popUpToThisFragmentFromBackStack(String fragmentName) {
        if (mFragmentLoaderController != null) {
            mFragmentLoaderController.popUpToThisFragmentFromBackStack(fragmentName);
        }
    }
}

