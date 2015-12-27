package pers.example.xiayong.rxjavasamples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import pers.example.xiayong.rxjavasamples.fragments.MainFragment;
import pers.example.xiayong.rxjavasamples.fragments.RotationPersist1WorkerFragment;
import pers.example.xiayong.rxjavasamples.fragments.RotationPersist2WorkerFragment;
import pers.example.xiayong.rxjavasamples.rxbus.RxBus;

public class MainActivity
      extends FragmentActivity {

    private RxBus _rxBus = null;

    // This is better done with a DI Library like Dagger
    public RxBus getRxBusSingleton() {
        if (_rxBus == null) {
            _rxBus = new RxBus();
        }

        return _rxBus;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        _removeWorkerFragments();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                  .replace(android.R.id.content, new MainFragment(), this.toString())
                  .commit();
        }
    }

    private void _removeWorkerFragments() {
        Fragment frag = getSupportFragmentManager()//
              .findFragmentByTag(RotationPersist1WorkerFragment.class.getName());

        if (frag != null) {
            getSupportFragmentManager().beginTransaction().remove(frag).commit();
        }

        frag = getSupportFragmentManager()//
              .findFragmentByTag(RotationPersist2WorkerFragment.class.getName());

        if (frag != null) {
            getSupportFragmentManager().beginTransaction().remove(frag).commit();
        }
    }
}