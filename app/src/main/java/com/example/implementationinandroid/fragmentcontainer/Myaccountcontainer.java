package com.example.implementationinandroid.fragmentcontainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.implementationinandroid.R;
import com.example.implementationinandroid.fragment.Dashboard;
import com.example.implementationinandroid.fragmentbasecontroller.BaseControllerFragment;

public class Myaccountcontainer extends BaseControllerFragment {

    private boolean mIsViewInited;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.container_fragment, null);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TextView) getView().findViewById(R.id.textView)).setText("Myaccount Container");
        if (!mIsViewInited) {
            mIsViewInited = true;
            createFragmentLoader(this, R.id.container_framelayout);
            initView();
        }
    }

    private void initView() {
        loadFragment(new Dashboard(), false);
    }
}
