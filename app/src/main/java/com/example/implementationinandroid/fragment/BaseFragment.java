package com.example.implementationinandroid.fragment;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    private boolean maintainBackStack = false;

    public boolean isMaintainBackStack() {
        return maintainBackStack;
    }

    public void setMaintainBackStack(boolean maintainBackStack) {
        this.maintainBackStack = maintainBackStack;
    }
}
