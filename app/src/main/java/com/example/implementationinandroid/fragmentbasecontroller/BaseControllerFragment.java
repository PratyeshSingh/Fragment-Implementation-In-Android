package com.example.implementationinandroid.fragmentbasecontroller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.implementationinandroid.R;
import com.example.implementationinandroid.fragment.BaseFragment;

public class BaseControllerFragment extends Fragment {


    public void replaceFragment(BaseFragment fragment, boolean addToBackStack, String ... tagname) {

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                if (addToBackStack) {
                    if (tagname!=null&& tagname.length>0)
                        transaction.addToBackStack(tagname[0]);
                        else
                    transaction.addToBackStack(null);
                }

                //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.container_framelayout, fragment);
                transaction.commit();
                getChildFragmentManager().executePendingTransactions();
    }



    public boolean popFragment() {

        FragmentManager fm = getChildFragmentManager();
        int backStackEntryCount = fm.getBackStackEntryCount();

        boolean isPop = false;
        if (backStackEntryCount > 0) {
            isPop = true;
            //fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            fm.popBackStack();
        }

        return isPop;
    }

    private String getLastFragmentTag() {

        String lastFragmentTag = "";
        FragmentManager fm = getChildFragmentManager();
        int count = fm.getBackStackEntryCount();
        if (count > 0) {
            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(count - 1);
            lastFragmentTag = entry.getName();
        }
        return lastFragmentTag;
    }

    public void clearFragmentStack() {
        FragmentManager fm = getChildFragmentManager();
        int backStackEntryCount = fm.getBackStackEntryCount();

        while (backStackEntryCount > 0) {

           fm.popBackStack();
            backStackEntryCount--;
        }

    }
}

