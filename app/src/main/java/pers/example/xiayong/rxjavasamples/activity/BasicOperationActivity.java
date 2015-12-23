package pers.example.xiayong.rxjavasamples.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pers.example.xiayong.rxjavasamples.R;
import pers.example.xiayong.rxjavasamples.fragments.BasicOperationFragment;

public class BasicOperationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new BasicOperationFragment(), this.toString())
                    .commit();
        }
    }
}
