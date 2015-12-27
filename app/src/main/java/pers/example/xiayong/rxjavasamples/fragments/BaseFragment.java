package pers.example.xiayong.rxjavasamples.fragments;

import android.support.v4.app.Fragment;

import com.squareup.leakcanary.RefWatcher;

import pers.example.xiayong.rxjavasamples.MyApp;

public class BaseFragment
      extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApp.getRefWatcher();
        refWatcher.watch(this);
    }
}
