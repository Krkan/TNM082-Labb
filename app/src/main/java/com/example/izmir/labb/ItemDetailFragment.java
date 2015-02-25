package com.example.izmir.labb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.izmir.labb.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {


    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_TITLE = "item_title";
    public static final String ARG_ITEM_RATING = "item_rating";
    public static final String ARG_ITEM_DESCRIPTION = "item_description";
    private long itemId;
    private String itemTitle;
    private int itemRating;
    private String itemDescription;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
   // public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    //private DummyContent.DummyItem mItem;
    //private Item it;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*

GAMMAL KOD
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

        }
GAMMAL KOD
*/

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            itemId = getArguments().getLong(ARG_ITEM_ID);
        }
        if (getArguments().containsKey(ARG_ITEM_TITLE)) {
            itemTitle = getArguments().getString(ARG_ITEM_TITLE);
        }
        if (getArguments().containsKey(ARG_ITEM_RATING)) {
            itemRating = getArguments().getInt(ARG_ITEM_RATING);
        }
        if (getArguments().containsKey(ARG_ITEM_DESCRIPTION)) {
            itemDescription = getArguments().getString(ARG_ITEM_DESCRIPTION);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate your layout
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the content as text in a TextView.
        /*
        if (itemId > -1) {
            ((TextView) rootView.findViewById(R.id.item_id)).setText(String.valueOf(itemId));
        }
        */
        if (itemTitle != null) {
            ((TextView) rootView.findViewById(R.id.item_title)).setText(itemTitle);
        }
        if (itemRating > -1) {
            ((RatingBar) rootView.findViewById(R.id.item_rating_bar)).setRating(itemRating);
        }

        if (itemDescription != null) {
            ((TextView) rootView.findViewById(R.id.item_description)).setText(itemDescription);
        }

        //return the created ViewGroup
        return rootView;
    }
}
