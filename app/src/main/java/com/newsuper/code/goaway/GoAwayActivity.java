package com.newsuper.code.goaway;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.baidu.mobstat.StatService;
import com.newsuper.code.R;

//这是一款有意思的防沉迷应用.时刻提醒消耗在手机上的时间，点亮关闭间帮你记录时间，底部tips告知你当前状态
// ，五彩壁纸随着使用时间逐渐变化，长按显示各个app的使用排名，时刻警示你放下手机！
public class GoAwayActivity extends AppCompatActivity {

    private MenuItem mMenuItem;
    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // statistics
        StatService.start(this);
        startFragment(new MainFragment(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        mMenuItem = menu.findItem(R.id.menu_setting);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // Go back MainFragment
                onBackPressed();
                break;
            case R.id.menu_setting: // Go SettingFragment
                startFragment(new SettingFragment(), true);

                mMenuItem.setVisible(false);
                setTitle(R.string.menu_setting);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // for miui，miui dev version will leak activity，miui internal is processing fix，Emmmmmm...
        ((ViewGroup) getWindow().getDecorView()).removeAllViews();
    }

    /**
     * When back press in SettingFragment, back to MainFragment
     */
    @Override
    public void onBackPressed() {
        Fragment fragment = fm.findFragmentByTag(SettingFragment.class.getName());
        if (fragment != null && fragment.isVisible()) {
            mMenuItem.setVisible(true);
            setTitle(R.string.app_name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        super.onBackPressed();
    }

    protected void startFragment(Fragment fragment, boolean addBackStack) {
       FragmentTransaction transaction = fm.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(android.R.id.content, fragment, fragment.getClass().getName());
        if (addBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }
}
