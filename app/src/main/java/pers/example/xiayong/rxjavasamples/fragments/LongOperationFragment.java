package pers.example.xiayong.rxjavasamples.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.example.xiayong.rxjavasamples.R;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 展示了RxJava如何处理耗时操作的~
 */
public class LongOperationFragment extends Fragment {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.btn_long_operation)
    Button btnLongOperation;
    private Subscription _subscription;

    public static LongOperationFragment newInstance(String param1, String param2) {
        LongOperationFragment fragment = new LongOperationFragment();

        return fragment;
    }

    @OnClick(R.id.btn_long_operation)
    public void startLongOperation() {

        progressBar.setVisibility(View.VISIBLE);
//        _log("Button Clicked");

        _subscription = _getObservable()//
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_getObserver());                             // Observer
    }

    private Observable<Boolean> _getObservable() {
        return Observable.just(true).map(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean aBoolean) {
//                _log("Within Observable");
                _doSomeLongOperation_thatBlocksCurrentThread();
                return aBoolean;
            }
        });
    }
    /**
     * Observer that handles the result through the 3 important actions:
     *
     * 1. onCompleted
     * 2. onError
     * 3. onNext
     */
    private Observer<Boolean> _getObserver() {
        return new Observer<Boolean>() {

            @Override
            public void onCompleted() {
//                _log("On complete");
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error in RxJava Demo concurrency");
//                _log(String.format("Boo! Error %s", e.getMessage()));
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNext(Boolean bool) {
//                _log(String.format("onNext with return value \"%b\"", bool));
            }
        };
    }
    // Method that help wiring up the example (irrelevant to RxJava)

    private void _doSomeLongOperation_thatBlocksCurrentThread() {
//        _log("performing long operation");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Timber.d("Operation was interrupted");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_long_operation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (_subscription != null) {
            _subscription.unsubscribe();
        }
    }
}
