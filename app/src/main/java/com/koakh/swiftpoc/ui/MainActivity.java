package com.koakh.swiftpoc.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.koakh.swiftpoc.R;
import com.koakh.swiftpoc.app.Singleton;
import com.koakh.swiftpoc.rest.ServiceGenerator;
import com.koakh.swiftpoc.rest.identity.authenticate.AuthenticateResponse;
import com.koakh.swiftpoc.rest.identity.authenticate.AuthenticateServiceInterface;
import com.koakh.swiftpoc.rest.swift.accounts.showaccountdetailsandlistcontainers.ListContainersResponse;
import com.koakh.swiftpoc.rest.swift.accounts.showaccountdetailsandlistcontainers.ListContainersServiceInterface;
import com.koakh.swiftpoc.rest.swift.containers.showcontainerdetailsandlistobjects.ContainerDetailsAndObjectsResponse;
import com.koakh.swiftpoc.rest.swift.containers.showcontainerdetailsandlistobjects.ContainerDetailsAndObjectsServiceInterface;
import com.koakh.swiftpoc.rest.swift.objects.createorreplaceobject.CreateOrReplaceObjectServiceInterface;
import com.koakh.swiftpoc.rest.swift.objects.getobjectcontentandmetadata.GetObjectContentAndMetadataServiceInterface;
import com.koakh.swiftpoc.ui.fragments.PlaceholderFragmentViewPager1;
import com.koakh.swiftpoc.ui.fragments.PlaceholderFragmentViewPager2;
import com.koakh.swiftpoc.ui.fragments.PlaceholderFragmentViewPager3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedInput;

public class MainActivity extends ActionBarActivity {

  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide
   * fragments for each of the sections. We use a
   * {@link FragmentPagerAdapter} derivative, which will keep every
   * loaded fragment in memory. If this becomes too memory intensive, it
   * may be best to switch to a
   * {@link android.support.v4.app.FragmentStatePagerAdapter}.
   */
  SectionsPagerAdapter mSectionsPagerAdapter;

  /**
   * The {@link ViewPager} that will host the section contents.
   */
  ViewPager mViewPager;

  //Application Singleton
  private Singleton mApp;

  /**
   * Constants
   */
  private static final String API_URL_IDENTITY = "http://192.168.1.31:5000/v2.0";
  private static final String API_URL_SWIFT = "http://192.168.1.31:8080/v1/AUTH_%s";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(mSectionsPagerAdapter);

    //Button ButtonGetToken = (Button) findViewById(R.id.button_get_token);

    //Get Application Singleton
    mApp = ((Singleton) this.getApplicationContext());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  //============================================================================================================================================================================
  //FragmentPagerAdapter

  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
      //return PlaceholderFragment.newInstance(position + 1);

      switch(position) {
        case 0:
          return PlaceholderFragmentViewPager1.newInstance(1);
        case 1:
          return PlaceholderFragmentViewPager2.newInstance(2);
        case 2:
          return PlaceholderFragmentViewPager3.newInstance(3);
        default:
          return null;
      }
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      Locale l = Locale.getDefault();
      switch (position) {
        case 0:
          return getString(R.string.title_section1).toUpperCase(l);
        case 1:
          return getString(R.string.title_section2).toUpperCase(l);
        case 2:
          return getString(R.string.title_section3).toUpperCase(l);
      }
      return null;
    }
  }

  //============================================================================================================================================================================
  //Click Events

  public void onClickButtonGetToken(View view) {
    final View thisButton = view;

    new Thread(new Runnable() {
      @Override
      public void run() {
        try {

          //TODO: Create a Auth Object, do not send BODY has JSON, Or leave it has Example
          String jsonString = "{\"auth\": {\"tenantName\": \"admin\", \"passwordCredentials\":{\"username\": \"admin\", \"password\": \"kksc28kk\"}}}";
          Log.d(mApp.getTag(), jsonString);
          TypedInput rawJsonBody = new TypedByteArray("application/json", jsonString.getBytes("UTF-8"));

          //Get Service
          AuthenticateServiceInterface authenticateService = ServiceGenerator.createService(AuthenticateServiceInterface.class, API_URL_IDENTITY);
          Callback<AuthenticateResponse> authenticateCallback = new Callback<AuthenticateResponse>() {
            @Override
            public void success(AuthenticateResponse responseObject, Response responseRaw) {
              mApp.setAuthenticateResponse(responseObject);
              Log.d(mApp.getTag(), String.format("AuthenticateToken : [%s]", mApp.getAuthenticateToken()));
              mApp.getEditTextLog().append(String.format("valid Token for Authenticated User: %s", mApp.getAuthenticateResponse().getAccess().getUser().getName()) + "\n\n");
              mApp.getEditTextLog().append(mApp.getAuthenticateToken().substring(0, 125) + "...\n\n");
              mApp.getEditTextLog().scrollTo(0, Integer.MAX_VALUE);
              //Toggle Action Buttons
              thisButton.setEnabled(false);
            }

            @Override
            public void failure(RetrofitError error) {
              showRetrofitError(error);
            }
          };
          authenticateService.authenticateUser(rawJsonBody, authenticateCallback);

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();
  }

  public void onClickButtonListContainers(View view) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          String url = String.format(API_URL_SWIFT, mApp.getTenant());
          ListContainersServiceInterface listContainersService = ServiceGenerator.createService(ListContainersServiceInterface.class, url);

          Callback<List<ListContainersResponse>> listContainersCallback = new Callback<List<ListContainersResponse>>() {
            @Override
            public void success(List<ListContainersResponse> responseObject, Response responseRaw) {
              for (ListContainersResponse container : responseObject) {
                mApp.getEditTextLog().append(container.getName() + "\n");
              }
              mApp.getEditTextLog().append("\n");
              mApp.getEditTextLog().scrollTo(0, Integer.MAX_VALUE);
            }

            @Override
            public void failure(RetrofitError error) {
              showRetrofitError(error);
            }
          };
          listContainersService.getContainers(mApp.getAuthenticateToken(), listContainersCallback);

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();
  }

  public void onClickButtonListContainerObjects(View view) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          String url = String.format(API_URL_SWIFT, mApp.getTenant());
          ContainerDetailsAndObjectsServiceInterface service = ServiceGenerator.createService(ContainerDetailsAndObjectsServiceInterface.class, url);

          Callback<List<ContainerDetailsAndObjectsResponse>> callback = new Callback<List<ContainerDetailsAndObjectsResponse>>() {
            @Override
            public void success(List<ContainerDetailsAndObjectsResponse> responseObject, Response responseRaw) {
              for (ContainerDetailsAndObjectsResponse object : responseObject) {
                mApp.getEditTextLog().append(object.getName() + "\n");
              }
              mApp.getEditTextLog().append("\n");
              mApp.getEditTextLog().scrollTo(0, Integer.MAX_VALUE);
            }

            @Override
            public void failure(RetrofitError error) {
              showRetrofitError(error);
            }
          };
          service.getContainerDetails(mApp.getAuthenticateToken(), "container", callback);
          service.getContainerDetails(mApp.getAuthenticateToken(), "containerfromapi", callback);
          service.getContainerDetails(mApp.getAuthenticateToken(), "containerfromapinew", callback);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();
  }

  public void onClickButtonUploadFile(View view) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          String url = String.format(API_URL_SWIFT, mApp.getTenant());
          CreateOrReplaceObjectServiceInterface service = ServiceGenerator.createService(CreateOrReplaceObjectServiceInterface.class, url);

          Callback<Response> callback = new Callback<Response>() {
            @Override
            public void success(Response responseObject, Response responseRaw) {
              String eTag = responseObject.getHeaders().get(2).getValue();
              int status = responseObject.getStatus();        //201
              String reason = responseObject.getReason();     //Created
              String message = String.format("status:[%d]\nreason:[%s]\neTag:[%s]\n\n", status, reason, eTag);
              Log.d(mApp.getTag(), message);
              mApp.getEditTextLog().append(message);
              mApp.getEditTextLog().scrollTo(0, Integer.MAX_VALUE);
            }

            @Override
            public void failure(RetrofitError error) {
              showRetrofitError(error);
            }
          };

          File file = new File("/mnt/sdcard/WallpapersHD/hd_wallpaper_11910.jpg");
          if (file.exists()) {
            TypedFile typedFile = new TypedFile("image/*", file);
            service.uploadFile(mApp.getAuthenticateToken(), file.length(), "container", file.getName(), typedFile, callback);
          }

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();
  }

  public void onClickButtonDownloadFile(View view) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          String url = String.format(API_URL_SWIFT, mApp.getTenant());
          GetObjectContentAndMetadataServiceInterface service = ServiceGenerator.createService(GetObjectContentAndMetadataServiceInterface.class, url);

          Callback<Response> callback = new Callback<Response>() {
            @Override
            public void success(Response responseObject, Response responseRaw) {
              String eTag = responseObject.getHeaders().get(3).getValue();
              int status = responseObject.getStatus();        //200
              String reason = responseObject.getReason();     //OK
              String contentLength = responseObject.getHeaders().get(0).getValue();
              String message = String.format("status:[%d]\nreason:[%s]\neTag:[%s]\ncontentLength:[%s]\n\n", status, reason, eTag, contentLength);
              Log.d(mApp.getTag(), message);
              mApp.getEditTextLog().append(message);

              //Getting the bytes from the body, This will return byte array that you will write on the file system
              byte[] bytes = ((TypedByteArray) responseObject.getBody()).getBytes();

              File file = new File("/mnt/sdcard/Temp/downloadedFile.jpg");
              try {
                FileOutputStream fos = new FileOutputStream(file);
                try {
                  fos.write(bytes);
                  fos.flush();
                  fos.close();
                } catch (IOException e) {
                  e.printStackTrace();
                }
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
              mApp.getEditTextLog().scrollTo(0, Integer.MAX_VALUE);
            }

            @Override
            public void failure(RetrofitError error) {
              showRetrofitError(error);
            }
          };

          service.downloadFile(mApp.getAuthenticateToken(), "container", "SwiftPOC01.zip", callback);

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();
  }

  //============================================================================================================================================================================
  //Helper Methods

  private void showRetrofitError(RetrofitError error) {
    Log.e(mApp.getTag(), String.format("RetrofitError Error : [%s]", error.getCause().getMessage()));
    Toast.makeText(getApplicationContext(), error.getCause().getMessage(), Toast.LENGTH_LONG);
  }

  //private void toggleButtons(Boolean authenticated ) {
  //  Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.))
  //}

}
