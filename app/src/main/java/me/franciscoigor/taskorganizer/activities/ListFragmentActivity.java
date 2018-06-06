package me.franciscoigor.taskorganizer.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import me.franciscoigor.taskorganizer.list.ListContainerFragment;
import me.franciscoigor.taskorganizer.R;

public class ListFragmentActivity extends FragmentActivity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadObjects();
        insertFragment();
    }

    private void loadObjects(){
        title = findViewById(R.id.list_title);
    }

    public void insertFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = getNewFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    protected Fragment getNewFragment(){
        return new ListContainerFragment();
    }
}
