package pers.example.xiayong.rxjavasamples.wiring;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import pers.example.xiayong.rxjavasamples.R;

public class LogAdapter
      extends ArrayAdapter<String> {

    public LogAdapter(Context context, List<String> logs) {
        super(context, R.layout.item_log, R.id.item_log, logs);
    }
}
