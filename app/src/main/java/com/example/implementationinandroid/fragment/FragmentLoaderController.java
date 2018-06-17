package com.example.implementationinandroid.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

public class FragmentLoaderController {

    private static final int CURRENT_FRAGMENT_OFFSET = 1;
    private static final int PREVIOUS_FRAGMENT_OFFSET = 2;

    private int mContainerId;

    private FragmentActivity mActivity;
    private FragmentManager fragmentManager;

    private Stack<String> mFragmentNameStack;
    private FragmentManager.OnBackStackChangedListener backStackChangedListener =
            new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    if (fragmentManager != null) {
                        notifyBackStackFragment(fragmentManager, CURRENT_FRAGMENT_OFFSET, true);
//                        notifyBackStackFragment(fragmentManager, PREVIOUS_FRAGMENT_OFFSET, false);
                    }
                }
            };


    public FragmentLoaderController(FragmentActivity activity, int containerId) {
        mActivity = activity;
        mContainerId = containerId;

        mFragmentNameStack = new Stack<>();
        fragmentManager = mActivity.getSupportFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.addOnBackStackChangedListener(backStackChangedListener);
        }
    }

    public FragmentLoaderController(Fragment fragment, int containerId) {
        mActivity = fragment.getActivity();
        mContainerId = containerId;
        mFragmentNameStack = new Stack<>();
        fragmentManager = fragment.getChildFragmentManager();
        fragmentManager.addOnBackStackChangedListener(backStackChangedListener);
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack, String fragmentName) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(fragmentName);
        }

        //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(mContainerId, fragment, fragmentName)
                .commit();
        fragmentManager.executePendingTransactions();
        mFragmentNameStack.push(fragmentName);
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        loadFragment(fragment, addToBackStack, fragment.getClass().getSimpleName());
    }


    public boolean popFragment() {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        boolean isPop = false;
        if (backStackEntryCount > 0) {
            isPop = true;
            //fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            fragmentManager.popBackStack();
            popFragmentName();
        }
        return isPop;
    }

    public String getLastFragmentTag() {
        return mFragmentNameStack.peek();
    }

    public void popAllFragmentStack() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentNameStack.clear();
    }

    public void popUpToThisFragmentFromBackStack(String fragmentName) {
        fragmentManager.popBackStack(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        popFragmentName();
    }

    private void popFragmentName() {
        if (!mFragmentNameStack.isEmpty()) {
            mFragmentNameStack.pop();
        }
    }

    private void notifyBackStackFragment(FragmentManager manager, int relativePosition, boolean visible) {
        final int stackCount = manager.getBackStackEntryCount();
        if (stackCount > relativePosition - 1) {
            final FragmentManager.BackStackEntry stackEntry =
                    manager.getBackStackEntryAt(stackCount - relativePosition);
            if (stackEntry != null) {
                final Fragment frag = manager.findFragmentByTag(stackEntry.getName());
//                if (frag instanceof BaseFragment) {
//                    notifyCurrentFragmentVisibility((BaseFragment) frag, visibility);
//                }
            }
        }
    }

}
