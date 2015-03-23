package com.deitel.twittersearches;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import com.deitel.twittersearches.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FirstFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;
    private SharedPreferences saveSearches;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        MainActivity main = (MainActivity)this.getActivity();
        ListView listView = (ListView)v.findViewById(android.R.id.list);
        listView.setAdapter(main.getAdapter());
        listView.setOnItemLongClickListener(main.getOnItemLongClickListener());
        saveSearches=main.getSavedSearches();

        return v;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String tag = ((TextView) v).getText().toString();
        String urlString = getString(R.string.searchURL) +
                Uri.encode(saveSearches.getString(tag, ""), "UTF-8");

        MainActivity main = (MainActivity)this.getActivity();
        main.sendUrlToFragment2(urlString);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_holder,new SecondFragment())
                .addToBackStack(null)
                .commit();


    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String url) {
        if (mListener != null) {
            mListener.sendUrlToFragment2(url);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void sendUrlToFragment2(String url);
    }

}
