package com.example.lab8;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
// import android.widget.Toolbar; this is the wrong version of the toolbar widget
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
//    public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // This gets the toolbar from the layout :)
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);

        // This loads the toolbar, which calls the onCreateOptionsMenu
       // this also creates the lab8 artifact
        setSupportActionBar(myToolbar);

        // navigation
 //      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
 //          ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, "open", "close");
 //        R.string.open, R.string.close);
  //     drawer.addDrawerListener(toggle);
  //     toggle.syncState();

   //   NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
   //     navigationView.setNavigationItemSelectedListener(this);


        ImageView right_icon1 = findViewById(R.id.right_icon1);
        ImageView right_icon2 = findViewById(R.id.right_icon2);
        TextView title = findViewById(R.id.toolbar_title);
        right_icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked on item 1", Toast.LENGTH_SHORT).show();
            }
        });
        right_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked on item 2", Toast.LENGTH_SHORT).show();
            }
        });
        title.setText("CST2335 Lab 8");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    //  @Override
   //   public boolean onOptionsItemSelected(MenuItem item) {

// Handle presses on the action bar items
   //   switch (item.getItemId()) {
   //       case R.id.HomeID:
    //show a Toast
    //        Toast.makeText(this, "pressed home", Toast.LENGTH_SHORT).show();
    //         break;
    //      case R.id.Dad_JokesID: //launch another Activity
    //          Toast.makeText(this, "pressed dad jokes", Toast.LENGTH_SHORT).show();
    //          break;

    //      case R.id.ExitID: //launch another Activity
    //          Toast.makeText(this, "pressed exit", Toast.LENGTH_SHORT).show();
    //         break;
    // }
    // Needed for the OnNavigationItemSelected interface:
   // @Override
   // public boolean onNavigationItemSelected( MenuItem item) {

     //   String message = null;

//        switch(item.getItemId())
  //      {
    //        case R.id.HomeID:
     //           message = "You clicked home";
      //          break;
      //      case R.id.Dad_JokesID:
      //          message = "You clicked dad jokes";
      //          break;
      //      case R.id.ExitID:
       //         message = "You clicked exit";
        //        break;

      //  }

     //   DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
     //   drawerLayout.closeDrawer(GravityCompat.START);

//        Toast.makeText(this, "NavigationDrawer: " + message, Toast.LENGTH_LONG).show();
  //      return false;
   // }
}



