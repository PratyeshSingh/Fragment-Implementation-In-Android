package com.example.pratyesh.implementationinandroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pratyesh.implementationinandroid.R;
import com.example.pratyesh.implementationinandroid.fragmentbasecontroller.BaseControllerFragment;
import com.example.pratyesh.implementationinandroid.fragmentcontainer.Cartcontainer;
import com.example.pratyesh.implementationinandroid.fragmentcontainer.Homecontainer;
import com.example.pratyesh.implementationinandroid.fragmentcontainer.Myaccountcontainer;

public class HomeActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // transaction.addToBackStack(null);
        transaction.replace(R.id.container_fragment, new Homecontainer());
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cart) {

            FragmentManager fm = getSupportFragmentManager();
            BaseControllerFragment fragment = (BaseControllerFragment) fm.findFragmentById(R.id.container_fragment);

            if (fragment instanceof Myaccountcontainer) {
                clearMyaccountContainer((Myaccountcontainer) fragment);
            }

            if (!(fragment instanceof Cartcontainer)) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container_fragment, new Cartcontainer());
                transaction.commit();
            }
            return true;
        } else if (id == R.id.action_account) {
            FragmentManager fm = getSupportFragmentManager();
            BaseControllerFragment fragment = (BaseControllerFragment) fm.findFragmentById(R.id.container_fragment);

            if (fragment instanceof Cartcontainer) {
                clearCartcontainer((Cartcontainer) fragment);
            }

            if (!(fragment instanceof Myaccountcontainer)) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container_fragment, new Myaccountcontainer());
                transaction.commit();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private final void goToLandingPage(Homecontainer fragment) {
        int childCount = fragment.getChildFragmentManager().getBackStackEntryCount();
        while (childCount > 0) {
            fragment.popFragment();
            childCount--;
        }
    }

    private final void clearCartcontainer(Cartcontainer fragment) {
        int childCount = fragment.getChildFragmentManager().getBackStackEntryCount();
        while (childCount > 0) {
            fragment.popFragment();
            childCount--;
        }
        super.onBackPressed();
    }


    private final void clearMyaccountContainer(Myaccountcontainer fragment) {
        int childCount = fragment.getChildFragmentManager().getBackStackEntryCount();
        while (childCount > 0) {
            fragment.popFragment();
            childCount--;
        }
        super.onBackPressed();
    }


    @Override
    public void onBackPressed() {
        int childCount = 0;
        BaseControllerFragment fragment = (BaseControllerFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
        if (fragment instanceof Homecontainer) {
            childCount = ((Homecontainer) fragment).getChildFragmentManager().getBackStackEntryCount();
        } else if (fragment instanceof Cartcontainer) {
            childCount = ((Cartcontainer) fragment).getChildFragmentManager().getBackStackEntryCount();
        } else if (fragment instanceof Myaccountcontainer) {
            childCount = ((Myaccountcontainer) fragment).getChildFragmentManager().getBackStackEntryCount();
        }

        /**
         *
         * Here put your logic on behalf of child Count
         *
         *
         * */

        if (!fragment.popFragment()) {
//            if (fragment instanceof Homecontainer) {
//                Utils.showDialogExit(this, "Pepperfry", "Do you want to exit");
//            } else {
            super.onBackPressed();
//            }
        }
    }
}
