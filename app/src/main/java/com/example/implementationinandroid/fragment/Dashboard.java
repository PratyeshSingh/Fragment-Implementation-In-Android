package com.example.implementationinandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.implementationinandroid.R;
import com.example.implementationinandroid.fragmentbasecontroller.BaseControllerFragment;

public class Dashboard extends BaseFragment {

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final View reference = getView();

        reference.findViewById(R.id.first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment();
            }
        });

    }

    public void replaceFragment() {
        Listpage listpage = new Listpage();
        Bundle bnd = new Bundle();
        bnd.putInt("page", 1);
        listpage.setArguments(bnd);
        getFragmentLoader(getActivity()).loadFragment(listpage, true);
    }
}
