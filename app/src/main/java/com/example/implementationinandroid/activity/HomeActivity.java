package com.example.implementationinandroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.implementationinandroid.R;
import com.example.implementationinandroid.fragmentbasecontroller.BaseControllerFragment;
import com.example.implementationinandroid.fragmentcontainer.Cartcontainer;
import com.example.implementationinandroid.fragmentcontainer.Homecontainer;
import com.example.implementationinandroid.fragmentcontainer.Myaccountcontainer;

public class HomeActivity extends BaseActivity {
    static final private String HOME_CONTAINER = "home_screen";
    static final private String CART_CONTAINER = "cart_screen";
    static final private String ACCOUNT_CONTAINER = "account_screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createFragmentLoader(this, R.id.container_fragment);
        loadFragment(new Homecontainer(), true, HOME_CONTAINER);
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
            BaseControllerFragment fragment = (BaseControllerFragment)
                    fm.findFragmentByTag(ACCOUNT_CONTAINER);

            if (fragment instanceof Myaccountcontainer) {
                fragment.popAllFragmentStack();
            }

            if (!(fragment instanceof Cartcontainer)) {
                loadFragment(new Cartcontainer(), true, CART_CONTAINER);
            }
            return true;
        } else if (id == R.id.action_account) {

            FragmentManager fm = getSupportFragmentManager();
            BaseControllerFragment fragment = (BaseControllerFragment)
                    fm.findFragmentByTag(CART_CONTAINER);

            if (fragment instanceof Cartcontainer) {
                fragment.popAllFragmentStack();
            }

            if (!(fragment instanceof Myaccountcontainer)) {
                loadFragment(new Myaccountcontainer(), true, ACCOUNT_CONTAINER);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private final void goToLandingPage(Homecontainer fragment) {
        int childCount = fragment.getChildFragmentManager().getBackStackEntryCount();
        while (childCount > 0) {
            fragment.popLastFragment();
            childCount--;
        }
    }
}
