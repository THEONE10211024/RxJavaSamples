package pers.example.xiayong.rxjavasamples.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.example.xiayong.rxjavasamples.R;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button)
    Button btnBasicUse;
    @Bind(R.id.button2)
    Button btnOperators;
    @Bind(R.id.button3)
    Button btnOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.button)
    void basicUse(){
        startActivity(new Intent(this,BasicOperationActivity.class));
    }
    @OnClick(R.id.button2)
    void operators(){
        startActivity(new Intent(this,OperatorsActivity.class));
    }
    @OnClick(R.id.button3)
    void others(){
        startActivity(new Intent(this,OthersActivity.class));
    }


}
