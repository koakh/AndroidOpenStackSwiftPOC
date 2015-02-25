package com.koakh.swiftpoc.ui;

import android.content.Context;
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
  private Context mContext;

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

    //Singleton
    mApp = ((Singleton) this.getApplicationContext());
    mApp.setContext(this);
    mContext = this;
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

      switch (position) {
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
          Log.d(mApp.TAG, jsonString);
          TypedInput rawJsonBody = new TypedByteArray("application/json", jsonString.getBytes("UTF-8"));

          //Get Service
          AuthenticateServiceInterface authenticateService = ServiceGenerator.createService(mContext, AuthenticateServiceInterface.class, mApp.API_URL_IDENTITY);
          Callback<AuthenticateResponse> authenticateCallback = new Callback<AuthenticateResponse>() {
            @Override
            public void success(AuthenticateResponse responseObject, Response responseRaw) {
              mApp.setAuthenticateResponse(responseObject);
              Log.d(mApp.TAG, String.format("AuthenticateToken : [%s]", mApp.getAuthenticateToken()));
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
          String serviceUrl = String.format(mApp.API_URL_SWIFT, mApp.getTenant());
          ListContainersServiceInterface listContainersService = ServiceGenerator.createService(mContext, ListContainersServiceInterface.class, serviceUrl);

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
          String serviceUrl = String.format(mApp.API_URL_SWIFT, mApp.getTenant());
          ContainerDetailsAndObjectsServiceInterface service = ServiceGenerator.createService(mContext, ContainerDetailsAndObjectsServiceInterface.class, serviceUrl);

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
          String serviceUrl = String.format(mApp.API_URL_SWIFT, mApp.getTenant());
          CreateOrReplaceObjectServiceInterface service = ServiceGenerator.createService(mContext, CreateOrReplaceObjectServiceInterface.class, serviceUrl);

          Callback<Response> callback = new Callback<Response>() {
            @Override
            public void success(Response responseObject, Response responseRaw) {
              String eTag = responseObject.getHeaders().get(2).getValue();
              int status = responseObject.getStatus();        //201
              String reason = responseObject.getReason();     //Created
              String message = String.format("status:[%d]\nreason:[%s]\neTag:[%s]\n\n", status, reason, eTag);
              Log.d(mApp.TAG, message);
              mApp.getEditTextLog().append(message);
              mApp.getEditTextLog().scrollTo(0, Integer.MAX_VALUE);
            }

            @Override
            public void failure(RetrofitError error) {
              showRetrofitError(error);
            }
          };

          //File file = new File("/mnt/sdcard/WallpapersHD/hd_wallpaper_11910.jpg");
          File file = new File("/mnt/sdcard/NDrive/maps/PRT_IP.map");
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
          String serviceUrl = String.format(mApp.API_URL_SWIFT, mApp.getTenant());

          GetObjectContentAndMetadataServiceInterface service = ServiceGenerator.createService(mContext, GetObjectContentAndMetadataServiceInterface.class, serviceUrl);

          Callback<Response> callback = new Callback<Response>() {
            @Override
            public void success(Response responseObject, Response responseRaw) {
              String eTag = responseObject.getHeaders().get(3).getValue();
              int status = responseObject.getStatus();        //200
              String reason = responseObject.getReason();     //OK
              String contentLength = responseObject.getHeaders().get(0).getValue();
              String message = String.format("status:[%d]\nreason:[%s]\neTag:[%s]\ncontentLength:[%s]\n\n", status, reason, eTag, contentLength);
              Log.d(mApp.TAG, message);
              mApp.getEditTextLog().append(message);

              //Getting the bytes(filecontent) from the body,
              //This will return byte array that you will write on the file system
              byte[] bytes = ((TypedByteArray) responseObject.getBody()).getBytes();
              File file = new File("/mnt/sdcard/Temp/SwiftPOC01.zip");

              //Save to Disk
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

              if (file.exists()) {
                Log.d(mApp.TAG, file.getAbsolutePath() + " exists");
                mApp.getEditTextLog().append(file.getAbsolutePath() + " exists");
              } else {
                Log.d(mApp.TAG, file.getAbsolutePath() + " dont exists");
                mApp.getEditTextLog().append(file.getAbsolutePath() + " dont exists");
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

  public void onClickButtonUploadFilesAsyncTask(View view) {
    UploadFileTask sendFileTask = new UploadFileTask(this, "container", "/mnt/sdcard/NDrive/maps/PRT_IP.map", "*/*");
    sendFileTask.execute();
  }

  public void onClickButtonDownloadFilesAsyncTask(View view) {
  }

  public void onClickButtonGetMetaData(View view) {
  }

  //============================================================================================================================================================================
  //Helper Methods

  private void showRetrofitError(RetrofitError error) {
    String message = error.getLocalizedMessage()/*error.getCause().getMessage()*/;
    Log.e(mApp.TAG, String.format("RetrofitError Error : [%s]", message));
    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
  }

  //private void toggleButtons(Boolean authenticated ) {
  //  Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.))
  //}

}
