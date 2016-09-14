package io.github.ingluismontesdeoca.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.ingluismontesdeoca.fragment.admin.Contact;
import io.github.ingluismontesdeoca.fragment.admin.Location;
import io.github.ingluismontesdeoca.fragment.admin.Map;
import io.github.ingluismontesdeoca.fragment.ui.Fragment.HypedArtistFragment;
import io.github.ingluismontesdeoca.fragment.ui.Fragment.TopArtistFragment;
import io.github.ingluismontesdeoca.fragment.ui.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar tb;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar*/
        tb = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager();
        tb.setTitle("Album");
        setSupportActionBar(tb);
    }

    private void setupViewPager(){
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), buildArrayAdapters(), MainActivity.this));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_hyped);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_top);
    }

    private ArrayList<Fragment> buildArrayAdapters() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        HypedArtistFragment hypedArtistFragment = new HypedArtistFragment();
        fragments.add(hypedArtistFragment);
        TopArtistFragment topArtistFragment = new TopArtistFragment();
        fragments.add(topArtistFragment);
        return fragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "SETTINGS",Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_contact) {
            //Toast.makeText(MainActivity.this, "CONTACT",Toast.LENGTH_SHORT).show();
            Intent _contact = new Intent(this, Contact.class);
            startActivity(_contact);
            return true;
        }

        if( id== R.id.action_location){
            Intent _location = new Intent(this, Location.class);
            startActivity(_location);
            return true;
        }

        if( id== R.id.action_map){
            Intent _map = new Intent(this, Map.class);
            startActivity(_map);
            return true;
        }

        if( id==R.id.action_album){
            //Crear Fragmento
            //getSupportFragmentManager().beginTransaction().add(R.id.sub_container, new TopArtistFragment()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
