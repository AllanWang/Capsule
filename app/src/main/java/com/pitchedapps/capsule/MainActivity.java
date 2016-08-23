package com.pitchedapps.capsule;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pitchedapps.capsule.library.activities.CapsuleActivity;
import com.pitchedapps.capsule.library.changelog.ChangelogDialog;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class MainActivity extends CapsuleActivity {
    @Override
    protected int getFragmentId() {
        return R.id.main;
    }

    @Override
    protected int getFabId() {
        return R.id.fab;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableCLog();
        super.onCreate(savedInstanceState);
        capsulate().toolbar(R.id.toolbar);
        switchFragment(new FragmentSample());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_changelog) {
            ChangelogDialog.show(this, R.xml.changelog);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
