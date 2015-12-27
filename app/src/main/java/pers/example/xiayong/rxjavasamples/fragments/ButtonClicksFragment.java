package pers.example.xiayong.rxjavasamples.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.example.xiayong.rxjavasamples.R;
import rx.Observer;
import rx.functions.Action1;

public class ButtonClicksFragment extends BaseFragment {

    @Bind(R.id.btn_click)
    Button btnClick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button_clicks, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_click)
    public void btnClick(){
        RxView.clicks(btnClick)
              .throttleFirst(1, TimeUnit.SECONDS)
              .subscribe(new Observer<Object>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable e) {

                  }

                  @Override
                  public void onNext(Object o) {
                      Toast.makeText(getContext(),"Click",Toast.LENGTH_SHORT).show();
                  }
              });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
