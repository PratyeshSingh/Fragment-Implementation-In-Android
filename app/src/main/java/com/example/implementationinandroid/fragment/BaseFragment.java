package com.example.implementationinandroid.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private boolean maintainBackStack = false;

    public boolean isMaintainBackStack() {
        return maintainBackStack;
    }

    public void setMaintainBackStack(boolean maintainBackStack) {
        this.maintainBackStack = maintainBackStack;
    }

    public interface FragmentLoader {

        void loadFragment(BaseFragment fragment, boolean addToBackStack);

        boolean popLastFragment();

        String getLastFragmentTag();

        void popAllFragmentStack();

        void popUpToThisFragmentFromBackStack(String fragmentName);
    }

    public static FragmentLoader getFragmentLoader(FragmentActivity activity) {
        return activity != null && activity instanceof FragmentLoader ? (FragmentLoader) activity : null;
    }
}
