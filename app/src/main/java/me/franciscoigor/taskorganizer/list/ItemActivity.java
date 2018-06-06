package me.franciscoigor.taskorganizer.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

import me.franciscoigor.taskorganizer.R;

public class ItemActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_ID = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, ItemActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, crimeId);
        return intent;
    }
}
