package com.example.implementationinandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.implementationinandroid.R;
import com.example.implementationinandroid.fragmentbasecontroller.BaseControllerFragment;

public class Listpage extends BaseFragment {

    int count = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        count = bundle.getInt("page");
    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listpage, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final View reference = getView();

        TextView first = (TextView) reference.findViewById(R.id.first);
        first.setText("Page number : " + count + "\n Open New Page");
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(reference.getContext());
//                alertDialog.setTitle("Set your action");
//                alertDialog.setMessage("Do you want to add it in stack");
//                alertDialog.setCancelable(false);
//
//                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
                replaceFragment(true);
//                        dialog.dismiss();
//                    }
//                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        replaceFragment(false);
//                        dialog.dismiss();
//                    }
//                });
//                alertDialog.show();

            }
        });

    }

    public void replaceFragment(boolean addToBackStack) {
        Listpage listpage = new Listpage();
        Bundle bnd = new Bundle();
        bnd.putInt("page", 1 + count);
        listpage.setArguments(bnd);
        getFragmentLoader(getActivity()).loadFragment(listpage, addToBackStack);
    }

    public void replaceFragment() {
        Dashboard dashboard = new Dashboard();
        Bundle bnd = new Bundle();
        bnd.putInt("page", ++count);
        dashboard.setArguments(bnd);
        getFragmentLoader(getActivity()).loadFragment(dashboard, true);
    }
}
