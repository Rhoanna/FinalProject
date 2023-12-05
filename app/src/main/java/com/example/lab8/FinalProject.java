package com.example.lab8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
public class FinalProject extends AppCompatActivity {
    /**
     * creating variable for the class
     */
    ListView listViewRSS;
    ArrayList<String> titles;
    ArrayList<String> descriptions;
    ArrayList<String> dates;
    ArrayList<String> links;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    databaseHelper myDb;


    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * creating the initial screen and taking the feed from the RSS url
         */
        setContentView(R.layout.activity_final_project);

        myDb = new databaseHelper(this);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ImageView right_icon1 = findViewById(R.id.right_icon1);
        ImageView right_icon2 = findViewById(R.id.right_icon2);
        /**
         * drawer layout instance to toggle the menu icon to open drawer and back button to close drawer
         */

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        /**
         *   pass the Open and Close toggle for the drawer layout listener  to toggle the button
         *
         */

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        /**
         *  to make the Navigation drawer icon always appear on the action bar
         *
         */

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         drawerLayout.closeDrawer(GravityCompat.START);
        //    final callActivity ca = new callActivity();

        /**
        *  dont need to inflate the next one or we have duplicates
         *
         */
          // navigationView.inflateMenu(R.menu.navigation_drawer);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem options) {
                 /**
                  * menu items
                  */
                 Log.d("fyi ", "inside  nav listener");

             //    MenuInflater inflater = getMenuInflater();
            //    inflater.inflate(R.menu.navigation_drawer, null);
                 if(options.getItemId()==R.id.nav_favourites){

                     Toast.makeText(FinalProject.this, "clicked on favourites ", Toast.LENGTH_SHORT).show();
                     Log.d("fyi ", "inside  nav listener after intent code , before start activity");
                     //  replaceFragment(new nav_favourites());
                     /**
                      *  finally got it working. originally, setting the content view did not help with system crash, does not want to create new activity
                      */
                     setContentView(R.layout.fragment_nav_favourites);
                     TextView tv = findViewById(R.id.fav_titles);

                     //  tv.setText((CharSequence) myDb.getData());
                     Cursor cursor = myDb.getData();
                     String displayData = "";
                     if (cursor.getCount()==0) {
                         // no data to display in the database
                         // so show the default text box
                         Log.d("fyi ", "inside  nav listener after intent code , no database data found");
                         Intent activity = new Intent(FinalProject.this, nav_other.class);

                     } else {
                        // show the database content as there is stuff stored there
                         Log.d("fyi ", "inside  nav listener after intent code , yes, database data found");

                         while (cursor.moveToNext()) { // use standard move to next method to move through database items
                         // title is column = 1 below
                            displayData=displayData + cursor.getString(1) +"\n\n";
                             // could show other columns/fields but it gets very busy on the screen
                            }
                         tv.setText(displayData);
                         Intent activity = new Intent(FinalProject.this, nav_other.class);


                     }


             } else if(options.getItemId()==R.id.nav_other){
                    Toast.makeText(FinalProject.this, "clicked on help ", Toast.LENGTH_SHORT).show();
                   //  replaceFragment(new nav_other());

                     /**
                      *  setting the content view did not help with system crash, does not want to create new activity
                      */
                     setContentView(R.layout.fragment_nav_other);
                     Intent activity = new Intent(FinalProject.this, nav_other.class);
                     Log.d("fyi ", "inside  nav listener after intent code , before start activity");

                } else if(options.getItemId()==R.id.nav_quit){
                   Toast.makeText(FinalProject.this, "clicked on quit ", Toast.LENGTH_SHORT).show();
                //   replaceFragment(new nav_quit());
                     /**
                      *  setting the content view did not help with system crash, does not want to create new activity
                      */
                     setContentView(R.layout.fragment_nav_quit);
                     Intent activity = new Intent(FinalProject.this, nav_other.class);
                     Log.d("fyi ", "inside  nav listener after intent code , before start activity");


                     finish();
                     System.exit(0);


                 }

                return true;
             }
          });

        // TextView title = findViewById(R.id.toolbar_title);
        right_icon1.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v The view that was clicked.
             *          click the animal
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(FinalProject.this, "toast bark bark", Toast.LENGTH_SHORT).show();
                /**
                 * do edit text here
                 */
                EditText editText = findViewById(R.id.name_box);
                TextView tv = findViewById(R.id.textName);
                setContentView(R.layout.activity_add_your_name);
                Intent activity = new Intent(FinalProject.this, nav_other.class);

            }
        });
        right_icon2.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v The view that was clicked.
             *          click the animal
             */
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.my_drawer_layout), "snackbar puff puff", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
//        title.setText("CST2335 Final Project");
        listViewRSS = findViewById(R.id.listViewRSS);
        titles = new ArrayList<>();
        descriptions = new ArrayList<>();
        dates = new ArrayList<>();
        links = new ArrayList<>();
        listViewRSS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 *  when url is clicked, show information about it
                 *   You should show the title, description, and date of the article, as well as a link
                 *                  to the website for the article.
                 *                  Clicking on the link should load the Android web browser to that page
                 *                  next three lines are the simple original version here that launches the url directly
                 *
                 */
                   // Uri uri = Uri.parse(links.get(position)); // connect to web page
                   // Intent intent = new Intent(Intent.ACTION_VIEW, uri); // create implicit intent
                   // startActivity(intent);// show the web page
                String title = titles.get(position);
                String description = descriptions.get(position);
                String date = dates.get(position);
                String link = links.get(position);
               // setContentView(R.layout.activity_final_project);
                setContentView(R.layout.activity_topic);
                Toolbar myToolbar = findViewById(R.id.my_toolbar);
                setSupportActionBar(myToolbar);
                /** progress bar not visible as internet is too fast
                 *
                 */
                final ProgressBar simpleProgressBar = findViewById(R.id.progressBar);
                simpleProgressBar.setVisibility(View.VISIBLE);
                simpleProgressBar.setProgress(0);
                TextView tv1 = findViewById(R.id.uriTitles);
                simpleProgressBar.setProgress(25);
                TextView tv2 = findViewById(R.id.uriDescriptions);
                simpleProgressBar.setProgress(50);
                TextView tv3 = findViewById(R.id.uriDates);
                simpleProgressBar.setProgress(75);
                TextView tv4 = findViewById(R.id.uriLinks);
                simpleProgressBar.setProgress(100);
                tv1.setText(title);
                tv2.setText(description);
                tv3.setText(date);
                tv4.setText(link);
                Button button = findViewById(R.id.webpageButton);
                button.setVisibility(View.VISIBLE);
                Button button2 = findViewById(R.id.saveButton);
                button2.setVisibility(View.VISIBLE);

                /**
                 * drawer layout instance to toggle the menu icon to open drawer and back button to close drawer
                 */

                drawerLayout = findViewById(R.id.my_drawer_layout2);
                actionBarDrawerToggle = new ActionBarDrawerToggle(FinalProject.this, drawerLayout, R.string.nav_open, R.string.nav_close);
                /**
                 *   pass the Open and Close toggle for the drawer layout listener  to toggle the button
                 *
                 */

                drawerLayout.addDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.syncState();

                NavigationView navigationView = findViewById(R.id.navigationView);
                /**
                 *  to make the Navigation drawer icon always appear on the action bar
                 *
                 */

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                Log.i("fyi" , "before you click button ");
                /**
                 *  when button works, dont need three lines below
                 */


                //  this should be an external class so i dont have a duplicate
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem options) {
                        /**
                         * menu items
                         */
                        Log.d("fyi ", "inside  nav listener");

                        //    MenuInflater inflater = getMenuInflater();
                        //    inflater.inflate(R.menu.navigation_drawer, null);
                        if(options.getItemId()==R.id.nav_favourites){

                            Toast.makeText(FinalProject.this, "clicked on favourites ", Toast.LENGTH_SHORT).show();
                            Log.d("fyi ", "inside  nav listener after intent code , before start activity");
                            //  replaceFragment(new nav_favourites());
                            /**
                             *  finally got it working. originally, setting the content view did not help with system crash, does not want to create new activity
                             */
                            setContentView(R.layout.fragment_nav_favourites);
                            TextView tv = findViewById(R.id.fav_titles);

                            //  tv.setText((CharSequence) myDb.getData());
                            Cursor cursor = myDb.getData();
                            String displayData = "";
                            if (cursor.getCount()==0) {
                                // no data to display in the database
                                // so show the default text box
                                Log.d("fyi ", "inside  nav listener after intent code , no database data found");
                                Intent activity = new Intent(FinalProject.this, nav_other.class);

                            } else {
                                // show the database content as there is stuff stored there
                                Log.d("fyi ", "inside  nav listener after intent code , yes, database data found");

                                while (cursor.moveToNext()) { // use standard move to next method to move through database items
                                    // title is column = 1 below
                                    displayData=displayData + cursor.getString(1) +"\n\n";
                                    // could show other columns/fields but it gets very busy on the screen
                                }
                                tv.setText(displayData);
                                Intent activity = new Intent(FinalProject.this, nav_other.class);


                            }


                        } else if(options.getItemId()==R.id.nav_other){
                            Toast.makeText(FinalProject.this, "clicked on help ", Toast.LENGTH_SHORT).show();
                            //  replaceFragment(new nav_other());
                            // final nav_other ca = new nav_other();
                            // ca.showActivity(FinalProject.class, false);
                            /**
                             *  setting the content view did not help with system crash, does not want to create new activity
                             */
                            setContentView(R.layout.fragment_nav_other);
                            Intent activity = new Intent(FinalProject.this, nav_other.class);
                            Log.d("fyi ", "inside  nav listener after intent code , before start activity");
                            //   startActivity(activity);

                        } else if(options.getItemId()==R.id.nav_quit){
                            Toast.makeText(FinalProject.this, "clicked on quit ", Toast.LENGTH_SHORT).show();
                            //   replaceFragment(new nav_quit());
                            /**
                             *  setting the content view did not help with system crash, does not want to create new activity
                             */
                            setContentView(R.layout.fragment_nav_quit);
                            Intent activity = new Intent(FinalProject.this, nav_other.class);
                            Log.d("fyi ", "inside  nav listener after intent code , before start activity");
                            //   startActivity(activity);

                            finish();
                            System.exit(0);


                        }

                        return true;
                    }
                });



           //     Uri uri = Uri.parse(links.get(position)); // connect to web page
           //    Intent intent = new Intent(Intent.ACTION_VIEW, uri); // create implicit intent
           //    startActivity(intent);// show the web page
                button.setOnClickListener(new View.OnClickListener() {
                    /**
                     *
                     * @param view The view that was clicked.
                     *             button to launch url
                     */
                    public void onClick(View view) {
                        /**
                         *  launch web site
                         */
                         Uri uri = Uri.parse(links.get(position)); // connect to web page
                         Intent intent = new Intent(Intent.ACTION_VIEW, uri); // create implicit intent
                         intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); // *** so app does not close
                         startActivity(intent);// show the web page
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    /**
                     *
                     * @param view The view that was clicked.
                     *
                     */
                    public void onClick(View view) {

                        Toast.makeText(getApplicationContext(),"button2 was pressed",Toast.LENGTH_SHORT).show();
                        Log.i("fyi", "button 2 was pressed");
                        /**
                         *  save this to database
                         *
                         */
                        // Gets the data repository in write mode

                        ContentValues values = new ContentValues();

                        boolean didItWork = myDb.insertData(title, description, date, link);
                     //   long newRowId = myDb.insertData(myDb.TABLE_NAME, null, values);
                        if(didItWork == true)
                            Toast.makeText(FinalProject.this, "data was inserted successfully", Toast.LENGTH_SHORT).show();



                    }
                });
            }
        });

        /**
         * run the async task
         */
        new ProcessReadInBackground().execute();

    }
        public InputStream getInputStream(URL url)
        {   /**
             * process stream of RSS
          */

            //  "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml"
           // Log.i("fyi url value as a string ", url.toString());
            try {
             //   Log.i("fyi", "*****in try of getinput stream");
                if (url == null) {Log.i("oops", "*****number 99 URL is null"); }
                else { Log.i("oops", "*****number 100 URL is not null");}
                /**
                 *  open a connection on the url
                 *
                 */
                return url.openConnection().getInputStream();
            } catch
            (IOException e) {
              //  Log.i("fyi", "*****in catch of getinput stream, failed");
                return null;
            }
        }
// need async class to read from web site
            public class ProcessReadInBackground extends AsyncTask<Integer, Void, Exception> {
            ProgressDialog progressDialog = new ProgressDialog(FinalProject.this);
            Exception exception = null;
            @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressDialog.setMessage("Busy, talk to the hand, loading RSS...");
                }
                @Override
                protected Exception doInBackground(Integer... params) {
                    /**
                     *  bulk of processing including parsing
                     */
                    Log.i("fyi", "*****begining of doinbackground 1");
                    try {
                    // Log.i("fyi", "*****begining of doinbackground 1b");
                        /**
                         *  open a connection on the url
                         *  because it was failing on the next line...
                         */

                    URL url = new URL("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
                    //    Log.i("fyi", "*****begining of doinbackground 1c");
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                     //   Log.i("fyi", "*****begining of doinbackground 1d");
                    factory.setNamespaceAware(false);  // specify for xml name spaces , dont need
                     //   Log.i("fyi", "*****begining of doinbackground 1e");
                    XmlPullParser pull = factory.newPullParser();
                        /**
                         *     next go to the actual URL we want set by the url variable
                         *                      you can see by all of the log messages i had trouble getting the url to work
                         *                      i was missing a slash in the url name, so it was failing
                         */

                    // Log.i("fyi", "*****begining of doinbackground 2");
                 //   if (url == null) {Log.i("oops", "*****number 2b URL is null"); }
                  //      else { Log.i("oops", "*****number 2c URL is not null");
                  //  }
                   //  if (getInputStream(url) == null) {Log.i("oops", "*****url is null"); }
                   //     else {Log.i("oops", "*****url is not null");}
                        pull.setInput(getInputStream(url), "UTF-8");
                    //    Log.i("oops", "*****number 3");
                  //  Toast.makeText(FinalProject.this, "****** after pull statement", Toast.LENGTH_SHORT).show();
                    boolean inItem = false; // so we want to know when we are in an item tag
                    int eventType = 0;
                        try {
                            eventType = pull.getEventType(); // tells us which tag we are at
                        } catch (Exception e) { // do nothing
                            }
                        while (eventType != XmlPullParser.END_DOCUMENT) {
                          //  Log.i("fyi", "*****parsing");
                            if (eventType == XmlPullParser.START_TAG) {
                            if (pull.getName().equalsIgnoreCase("item")) {
                               // Log.i("fyi", "*****found an item tag 1000");
                                inItem = true;
                            } else if (pull.getName().equalsIgnoreCase("title")) {
                                if (inItem) {
                                    // get data
                                    titles.add(pull.nextText());
                                }
                            } else if (pull.getName().equalsIgnoreCase("description")) {
                                if (inItem) {
                                    // get data
                                    descriptions.add(pull.nextText());
                                }
                            } else if (pull.getName().equalsIgnoreCase("pubdate")) {
                                if (inItem) {
                                    // get data
                                    dates.add(pull.nextText());
                                }
                            } else if (pull.getName().equalsIgnoreCase("link")) {
                                if (inItem) {
                                    // get data
                                    links.add(pull.nextText());
                                }
                            }
                        } else if (eventType == XmlPullParser.END_TAG && pull.getName().equalsIgnoreCase("item")) {
                            inItem = false;
                        }
//                            Log.i("fyi", "*****pulling next event type");
                            eventType = pull.next();
                    }
                }
                catch (IOException | XmlPullParserException e)
                    {
                        exception = e;
                    }
                // could add other exceptions
                return exception;
                }
                @Override
                protected void onPostExecute(Exception s) {
                    super.onPostExecute(s);
/**
 *  before we dismiss, get the data
 */

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(FinalProject.this, android.R.layout.simple_list_item_1, titles);
                    listViewRSS.setAdapter(adapter);
                    progressDialog.dismiss();
                }
       }

    /**
     *
     * @param item The menu item that was selected.
     *
     * @return
     *    override the onOptionsItemSelected()
     *      function to implement
     *      the item click listener callback
     *      to open and close the navigation
     *      drawer when the icon is clicked
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /**
         * nav drawer clicking
         */
        Toast.makeText(FinalProject.this, "clicked on the nav drawer ", Toast.LENGTH_SHORT).show();
        Log.d("val = ", String.valueOf(item.getItemId()));
       // int val =item.getItemId() ;
     //   item.setChecked(true);
      //  drawerLayout.openDrawer(GravityCompat.START);
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;}
            return super.onOptionsItemSelected(item);
        }
private void replaceFragment(Fragment fragment){
    /**
     * tried to get fragments to work
     */
    Log.d("fyi ", "in replacement for fragment");
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frame_layout, fragment);
    fragmentTransaction.commit();
}
}