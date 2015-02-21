package com.koakh.swiftpoc.ui.fragments;

/**
 * Created by mario on 21/02/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koakh.swiftpoc.R;
import com.koakh.swiftpoc.app.Singleton;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentViewPager3 extends Fragment {
  /**
   * The fragment argument representing the section number for this fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";

  /**
   * Application Singleton
   */
  private Singleton mApp;

  /**
   * Returns a new instance of this fragment for the given section number.
   */
  public static PlaceholderFragmentViewPager3 newInstance(int sectionNumber) {
    PlaceholderFragmentViewPager3 fragment = new PlaceholderFragmentViewPager3();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    return fragment;
  }

  public PlaceholderFragmentViewPager3() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_section3, container, false);

    //Get Application Singleton
    mApp = ((Singleton) getActivity().getApplication().getApplicationContext());

    return rootView;
  }
}
