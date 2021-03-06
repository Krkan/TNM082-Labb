package com.example.izmir.labb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;


import com.example.izmir.labb.dummy.DummyContent;


import java.util.ArrayList;
/*import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;*/
/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details
 * (if present) is a {@link ItemDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ItemListFragment.Callbacks} interface
 * to listen for item selections.
 */


public class ItemListActivity extends ActionBarActivity
        implements ItemListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    //public ActionMode mActionMode;


public int picked = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // fr = new ItemListFragment();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.


    }

    /**
     * Callback method from {@link ItemListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    /*
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }

    }

*/

    @Override
    public void onItemSelected(long id, int rating, String title, String description) {


        if (mTwoPane) {

            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.

           /* Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,id);

            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
*/


            /* MIN KOD

            TextView myTitle= (TextView)findViewById(R.id.theTitle);
            myTitle.setText(title);


            TextView myDescription= (TextView)findViewById(R.id.theDescription);
            myDescription.setText(description);

            RatingBar bar = (RatingBar)findViewById(R.id.theRatingBar);
            bar.setRating(rating);
MIN KOD
*/





            ItemDetailFragment fragment = new ItemDetailFragment();

            Bundle arguments = new Bundle();
            arguments.putLong(ItemDetailFragment.ARG_ITEM_ID, id);
            arguments.putString(ItemDetailFragment.ARG_ITEM_TITLE, title);
            arguments.putInt(ItemDetailFragment.ARG_ITEM_RATING, rating);
            arguments.putString(ItemDetailFragment.ARG_ITEM_DESCRIPTION, description);

            fragment.setArguments(arguments);

            getFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container,fragment).addToBackStack(null).commit();


        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra("title",title);
            detailIntent.putExtra("description",description);
            detailIntent.putExtra("rating",rating);
            detailIntent.putExtra("id",id);
            startActivityForResult(detailIntent,1);
/*
           Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_TITLE, title);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_RATING, rating);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_DESCRIPTION, description);

            startActivityForResult(detailIntent, 1);
*/

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                //String result=Long.toString(data.getExtras().getLong("id"));
               // Log.v("LALALALA",result);
                ((ItemListFragment) getSupportFragmentManager().findFragmentById(R.id.item_list)).deleteFromDB(data.getExtras().getLong("id"));



//fr.deleteFromDB(data.getExtras().getLong("id"));


            }

        }
    }


public void pickedSort(int picked)
{


    ((ItemListFragment) getSupportFragmentManager().findFragmentById(R.id.item_list)).setSort(picked);


}

}








