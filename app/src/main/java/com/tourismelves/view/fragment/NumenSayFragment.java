package com.tourismelves.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.bean.ElfsaidBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumenSayFragment extends Fragment {



    ElfsaidBean elfsaidBean;
    List<ElfsaidBean.DataListBean> listBeen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_numen_say, container, false);
        return view;
    }

}
