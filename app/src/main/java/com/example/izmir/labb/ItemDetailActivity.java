package com.example.izmir.labb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



/**
 * An activity representing a single Item detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ItemDetailFragment}.
 */
public class ItemDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ItemDetailFragment fragment = new ItemDetailFragment();

        Bundle arguments = new Bundle();
        arguments.putLong(ItemDetailFragment.ARG_ITEM_ID, getIntent().getExtras().getLong("id"));
        arguments.putString(ItemDetailFragment.ARG_ITEM_TITLE, getIntent().getExtras().getString("title"));
        arguments.putInt(ItemDetailFragment.ARG_ITEM_RATING, getIntent().getExtras().getInt("rating"));
        arguments.putString(ItemDetailFragment.ARG_ITEM_DESCRIPTION, getIntent().getExtras().getString("description"));

        fragment.setArguments(arguments);
        setContentView(R.layout.fragment_item_detail);
        //getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container,fragment).addToBackStack(null).commit();

        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail, fragment)
                .commit();

//setContentView(fragment.);
       // getFragmentManager().popBackStack();
        //getSupportFragmentManager().beginTransaction().replace(R.id.item_list,fragment).addToBackStack(null).commit();
//        setContentView(R.layout.fragment_item_detail);
        //getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container,fragment).addToBackStack(null).commit();

        /*


        setContentView(R.layout.activity_item_detail);

        TextView title = (TextView)findViewById(R.id.theTitle_m);
        title.setText(getIntent().getExtras().getString("title"));
        TextView description = (TextView)findViewById(R.id.theDescription_m);
        description.setText(getIntent().getExtras().getString("description"));
        RatingBar rb = (RatingBar)findViewById(R.id.ratingBar_m);
        rb.setRating(getIntent().getExtras().getInt("rating"));
        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        /*
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
            return true;
        }
        else if(id == R.id.delete){
           long backid = getIntent().getExtras().getLong("id");
          Intent myIntent = new Intent();
           myIntent.putExtra("id", backid);
          setResult(RESULT_OK,myIntent);
            finish();

           //  Log.v("AYY",Long.toString(backid));
return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file_b, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
