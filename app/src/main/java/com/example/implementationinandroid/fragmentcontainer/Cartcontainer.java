package com.example.implementationinandroid.fragmentcontainer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.implementationinandroid.R;
import com.example.implementationinandroid.fragment.Dashboard;
import com.example.implementationinandroid.fragmentbasecontroller.BaseControllerFragment;

public class Cartcontainer extends BaseControllerFragment {

    private boolean mIsViewInited;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.container_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TextView) getView().findViewById(R.id.textView)).setText("Cart Container");
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
