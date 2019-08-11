package tech.rootsdigital.thryll_sample_integration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tech.rootsdigital.thryll_integration_sdk.Thryll;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    HomeFragment homeFragment;
    EventsFragment eventsFragment;

    int currentScreenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navigation_home)
                    showScreen(0);
                else
                    showScreen(1);

                return true;
            }
        });

        showScreen(0);
    }

    private void showScreen(int screenId) {

        Fragment fragment;

        switch (screenId) {
            case 0:
                if (homeFragment == null)
                    homeFragment = new HomeFragment();
                fragment = homeFragment;
                break;
            case 1:
                if (eventsFragment == null)
                    eventsFragment = new EventsFragment();
                fragment = eventsFragment;
                break;
            default:
                fragment = new Fragment();
        }

        transaction = manager.beginTransaction();

        if(currentScreenId > screenId) {
            manager.popBackStack();
            currentScreenId = screenId;
            return;
        }
        else
            transaction.setCustomAnimations(R.anim.fragment_enter_right, R.anim.fragment_exit_left);

        transaction.add(R.id.fragment_holder, fragment).addToBackStack(fragment.getTag()).commit();
        currentScreenId = screenId;
    }

}
