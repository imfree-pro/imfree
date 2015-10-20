package com.imfree.imfree;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.androidquery.AQuery;

public class AddSuggestConfirmActivity extends ActionBarActivity {

    private Toolbar _toolbar;
    private ActionBar _actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_suggest_confirm);

        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        _toolbar.setTitle(R.string.title_activity_add_suggest_confirm);
        setSupportActionBar(_toolbar);
        _actionbar = getSupportActionBar();

        AQuery aq = new AQuery(this);

        Intent intent = getIntent();

        aq.id(R.id.tv_categoryname).text(intent.getStringExtra("categoryName"));
        aq.id(R.id.tv_categoryitemname).text(intent.getStringExtra("itemName"));

        aq.id(R.id.btn_category).visible().clicked(this, "onBackPressed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_suggest_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_close) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.anim_stable, R.anim.anim_slide_out_right);
        overridePendingTransition(R.anim.anim_stable, R.anim.anim_stable);
    }
}
