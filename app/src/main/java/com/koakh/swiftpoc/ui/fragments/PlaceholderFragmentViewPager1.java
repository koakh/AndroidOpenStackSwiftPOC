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
public class PlaceholderFragmentViewPager1 extends Fragment {
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
  public static PlaceholderFragmentViewPager1 newInstance(int sectionNumber) {
    PlaceholderFragmentViewPager1 fragment = new PlaceholderFragmentViewPager1();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    return fragment;
  }

  public PlaceholderFragmentViewPager1() {
  }

  /**
   * To provide a layout for a fragment, you must implement the onCreateView() callback method,
   * which the Android system calls when it's time for the fragment to draw its layout.
   * Your implementation of this method must return a View that is the root of your fragment's layout.
   */

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_section1, container, false);
    //if (savedInstanceState != null) int sectionNumber = savedInstanceState.getInt(ARG_SECTION_NUMBER);

    //Get Application Singleton
    mApp = ((Singleton) getActivity().getApplication().getApplicationContext());

    return rootView;
  }
}
