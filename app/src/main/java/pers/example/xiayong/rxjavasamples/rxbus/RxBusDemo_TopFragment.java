package pers.example.xiayong.rxjavasamples.rxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.example.xiayong.rxjavasamples.MainActivity;
import pers.example.xiayong.rxjavasamples.R;
import pers.example.xiayong.rxjavasamples.fragments.BaseFragment;

public class RxBusDemo_TopFragment
      extends BaseFragment {

    private RxBus _rxBus;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_rxbus_top, container, false);
        ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _rxBus = ((MainActivity) getActivity()).getRxBusSingleton();
    }

    @OnClick(R.id.btn_demo_rxbus_tap)
    public void onTapButtonClicked() {
        if (_rxBus.hasObservers()) {
            _rxBus.send(new RxBusDemoFragment.TapEvent());
        }
    }
}
