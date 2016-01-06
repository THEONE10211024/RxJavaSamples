package pers.example.xiayong.rxjavasamples.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.example.xiayong.rxjavasamples.R;
import pers.example.xiayong.rxjavasamples.wiring.LogAdapter;
import rx.Observable;
import rx.functions.Action1;

public class IterateArrayFragment extends BaseFragment {

    @Bind(R.id.btn_start)
    Button btnStart;
    @Bind(R.id.list_threading_log)
    ListView listThreadingLog;

    private LogAdapter _adapter;
    private List<String> _logs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_iterate_array, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _setupLogger();
    }
    @OnClick(R.id.btn_start)
    public void iterateArray() {
        String[] names = {"Tom", "Lily", "Alisa", "Sheldon", "Bill"};
        Observable
                .from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        _log(s);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    // -----------------------------------------------------------------------------------
    // Methods that help wiring up the example (irrelevant to RxJava)

    private void _setupLogger() {
        _logs = new ArrayList<>();
        _adapter = new LogAdapter(getActivity(), new ArrayList<String>());
        listThreadingLog.setAdapter(_adapter);
    }

    private void _log(String logMsg) {

        if (_isCurrentlyOnMainThread()) {
            _logs.add(0, logMsg);
            _adapter.clear();
            _adapter.addAll(_logs);
        } else {
            _logs.add(0, logMsg);

            // You can only do below stuff on main thread.
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    _adapter.clear();
                    _adapter.addAll(_logs);
                }
            });
        }
    }

    private boolean _isCurrentlyOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
