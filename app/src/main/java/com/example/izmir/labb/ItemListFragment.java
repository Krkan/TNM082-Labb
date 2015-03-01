package com.example.izmir.labb;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Random;


/**
 * A list fragment representing a list of Items. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link ItemDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemListFragment extends ListFragment {
public ActionMode mActionMode;
    public static final String ORDER_ASCENDING = "ascending_order_preference";
    public static final String SORT_SELECT = "sort";

    public Datasource DS;
    boolean isAscending = true;
private int isPicked = 1;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    //private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */



    /*
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.

        public void onItemSelected(String id);
        //public void onItemSelected(long id);
    }
*/
    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
/*
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };
*/

    private Callbacks mCallbacks = myCallbacks;
    public interface Callbacks {


        public void onItemSelected(long id, int rating, String title, String description);
         //public void onItemSelected(long id);
         }

    private static Callbacks myCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(long id, int rating, String title, String description) {
        }
    };


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemListFragment() {
    }




    public void deleteFromDB(long id)
    {
        openDB();
        DS.deleteItem(Long.toString(id));
        myList.clear();
        myList.addAll(DS.fetchAll(isPicked,isAscending));
        mAdapter.notifyDataSetChanged();

        closeDB();


    }

    public void setSort(int p)
    {
       // Log.v("DDDD", Integer.toString(p));

        isPicked = p;

        openDB();
        //DS.deleteItem(Long.toString(id));
        myList.clear();
        myList.addAll(DS.fetchAll(isPicked,isAscending));
        mAdapter.notifyDataSetChanged();

        closeDB();
        if(isTablet()) {
            fm.popBackStack();
            setActivatedPosition(ListView.INVALID_POSITION);


          try {
              mActionMode.finish();
          }catch(NullPointerException e){


          }

          }
        }


private void openDB()
{
    DS = new Datasource(getActivity());
    DS.open();

}

    private  void closeDB()
    {

        DS.close();
    }

public ArrayAdapter mAdapter;

public ArrayList myList;

FragmentManager fm;
FragmentTransaction ft;
    Fragment frag = new Fragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {





        SharedPreferences prefs = getActivity().getPreferences(Activity.MODE_PRIVATE);


        isAscending = getActivity().getPreferences(Activity.MODE_PRIVATE).getBoolean(ORDER_ASCENDING, true);
        isPicked = getActivity().getPreferences(Activity.MODE_PRIVATE).getInt(SORT_SELECT, 1);


/*
        if (getActivity().findViewById(R.id.item_detail_container) != null) {


        }
*/

        if(isTablet())
        {


        fm=getFragmentManager();
        ft=fm.beginTransaction();
        fm.popBackStack();
        ft.replace(R.id.item_detail_container, frag).addToBackStack(null).commit();

            }
            setHasOptionsMenu(true);
 super.onCreate(savedInstanceState);





        openDB();
            myList = DS.fetchAll(isPicked,isAscending);
/*
        // TODO: replace with a real list adapter.
        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.ITEMS));
*/


//long k = DS.insertItem("Some item",1,"random item");

       mAdapter = new ArrayAdapter<Item>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                myList);



        setListAdapter(mAdapter);


closeDB();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {




        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }




    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }
/*
    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }
*/
   // public int pos;
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {

        if(isTablet()) {
            fm.popBackStack();
        }


        openDB();
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        //mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);

       // mCallbacks.onItemSelected(Long.toString(DS.fetchAll(1,false).get(position).getId()));
       // mCallbacks.onItemSelected(DS.fetchAll(1,false).get(position).getTitle());
        mCallbacks.onItemSelected(DS.fetchAll(isPicked,isAscending).get(position).getId(),DS.fetchAll(isPicked,isAscending).get(position).getRating(), DS.fetchAll(isPicked,isAscending).get(position).getTitle(),
                DS.fetchAll(isPicked,isAscending).get(position).getDescription());
        if (isTablet()) {
            mActionMode = getActivity().startActionMode(mActionModeCallback);

       }
        //pos=position;
        mActivatedPosition = position;

    closeDB();

    }


    public boolean isTablet() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        boolean tablet = false;

        if (screenInches >= 6) {
            tablet = true;
        }

        return tablet;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
           // outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {



        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {

        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
            //Log.v("UAAAA","HEEYY");

        }

        else {
            getListView().setItemChecked(position, true);
        }


        mActivatedPosition = position;

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file, menu);
        return super.onCreateOptionsMenu(menu);
    }

    */


    @Override

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {




        inflater.inflate(R.menu.menu_file, menu);


    }

    @Override
    public void onPrepareOptionsMenu (Menu menu)
    {
        if (isAscending) {

            menu.findItem(R.id.ascending).setChecked(true);
        }
        else{
            menu.findItem(R.id.ascending).setChecked(false);
        }


    }

    Random rand = new Random();
    String title[] = {"A Post", "B Post", "C Post", "D Post", "E Post"};
    int rating[] = {3 , 4, 1, 2, 5};
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ascending:

                if (item.isChecked())
                {
                    isAscending=false;
                    openDB();
                    item.setChecked(false);

                    myList.clear();
                    myList.addAll(DS.fetchAll(isPicked,isAscending));

                    mAdapter.notifyDataSetChanged();
                    closeDB();





                }
                else
                {
                    item.setChecked(true);
                    isAscending=true;
                    openDB();

                    myList.clear();
                    myList.addAll(DS.fetchAll(isPicked,isAscending));

                    mAdapter.notifyDataSetChanged();
                    closeDB();






                }
                if(isTablet()) {
                    fm.popBackStack();
                    setActivatedPosition(ListView.INVALID_POSITION);

                   try {
                       mActionMode.finish();
                   }catch (NullPointerException e){}

                   }
                SharedPreferences prefs = getActivity().getPreferences(Activity.MODE_PRIVATE);

                prefs.edit().putBoolean(ORDER_ASCENDING, isAscending).commit();


                // ft.replace(R.id.item_detail_container, frag).addToBackStack(null).commit();



                return true;
            case R.id.add:



                int  n = rand.nextInt(5);


                openDB();
                long rowID = DS.insertItem(title[n], rating[n], "This is a new post");
/*

                Item temp = new Item();
                temp.setId(rowID);
                temp.setTitle("A Post");
                temp.setRating(3);
                temp.setDescription("This is a new post.");

*/
               // myList.add(temp);

                myList.clear();
                myList.addAll(DS.fetchAll(isPicked,isAscending));

                mAdapter.notifyDataSetChanged();
                closeDB();
//setActivatedPosition(ListView.INVALID_POSITION);

                return true;
            case R.id.sorting:


                MyDialog dialog = new MyDialog();
//dialog.setCancelable(false);
//setTargetFragment(this,1);

                dialog.show(getActivity().getFragmentManager(), "dialog");

return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items

            MenuInflater inflater = mode.getMenuInflater();
            mode.setTitle(getResources().getString(R.string.context));
            inflater.inflate(R.menu.menu_file_b, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {


            if(item.getItemId() == R.id.delete)
            {

                openDB();
                //myList.remove(DS.fetchAll(1,isAscending).get(pos));



                DS.deleteItem(Long.toString(DS.fetchAll(isPicked,isAscending).get(mActivatedPosition).getId()));
               // DS.deleteItem(Integer.toString(pos));
                myList.clear();
                myList.addAll(DS.fetchAll(isPicked,isAscending));
                mAdapter.notifyDataSetChanged();
               closeDB();



                fm.popBackStack();
               // ft.replace(R.id.item_detail_container, frag).addToBackStack(null).commit();
                setActivatedPosition(ListView.INVALID_POSITION);
                //getListView().setItemChecked(mActivatedPosition, false);
                mActionMode.finish();


                return true;
            }
            return false;

            /*
            switch (item.getItemId()) {
                case R.id.delete:

                    //shareCurrentItem();
                    //mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
            */
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            //fm.popBackStack();
            // ft.replace(R.id.item_detail_container, frag).addToBackStack(null).commit();
           // setActivatedPosition(ListView.INVALID_POSITION);
            //mActionMode = null;
            mActionMode.finish();
        }




    };


}
