package pers.example.xiayong.rxjavasamples.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxCompoundButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import pers.example.xiayong.rxjavasamples.R;
import rx.functions.Action1;

/**
 * 使用Rxjava做响应式界面.比如勾选了某个checkbox,自动更新对应的preference.
 */
public class RxUIFragment extends BaseFragment {

    @Bind(R.id.checkBox)
    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rx_ui, container, false);
        ButterKnife.bind(this, view);
        initCheckBox();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void  initCheckBox(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
        RxCompoundButton.checkedChanges(checkBox).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                Toast.makeText(getContext(),"checked:"+aBoolean,Toast.LENGTH_SHORT).show();
            }
        });


    }
}
