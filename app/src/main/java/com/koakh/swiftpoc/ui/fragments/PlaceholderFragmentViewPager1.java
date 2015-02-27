package com.koakh.swiftpoc.ui.fragments;

/**
 * Created by mario on 21/02/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.koakh.swiftpoc.R;
import com.koakh.swiftpoc.app.Singleton;
import com.koakh.swiftpoc.background.UploadFileTask;
import com.koakh.swiftpoc.rest.ServiceGenerator;
import com.koakh.swiftpoc.rest.identity.authenticate.AuthenticateResponse;
import com.koakh.swiftpoc.rest.identity.authenticate.AuthenticateServiceInterface;
import com.koakh.swiftpoc.rest.swift.accounts.showaccountdetailsandlistcontainers.ListContainersResponse;
import com.koakh.swiftpoc.rest.swift.accounts.showaccountdetailsandlistcontainers.ListContainersServiceInterface;
import com.koakh.swiftpoc.rest.swift.containers.showcontainerdetailsandlistobjects.ContainerDetailsAndObjectsResponse;
import com.koakh.swiftpoc.rest.swift.containers.showcontainerdetailsandlistobjects.ContainerDetailsAndObjectsServiceInterface;
import com.koakh.swiftpoc.rest.swift.objects.createorreplaceobject.CreateOrReplaceObjectServiceInterface;
import com.koakh.swiftpoc.rest.swift.objects.getobjectcontentandmetadata.GetObjectContentAndMetadataServiceInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedInput;

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

    //Get Application Singleton
    mApp = ((Singleton) getActivity().getApplication().getApplicationContext());

    Button buttonGetToken = (Button) rootView.findViewById(R.id.button_get_token);
    Button buttonListContainers = (Button) rootView.findViewById(R.id.button_list_containers);

    return rootView;
  }
}
