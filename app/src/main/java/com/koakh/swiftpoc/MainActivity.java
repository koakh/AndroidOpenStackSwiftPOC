package com.koakh.swiftpoc;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

import com.koakh.swiftpoc.rest.ServiceGenerator;
import com.koakh.swiftpoc.rest.swiftaccountslistcontainers.IListContainers;
import com.koakh.swiftpoc.rest.swiftaccountslistcontainers.ListContainersResponse;
import com.koakh.swiftpoc.rest.swiftidentityauthenticate.AuthenticateResponse;
import com.koakh.swiftpoc.rest.swiftidentityauthenticate.IAuthenticateService;

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
  private Singleton app;

  //UI
  private EditText mEditText;

  private static final String API_URL_SWIFT = "http://koakh.com:8080/v1/AUTH_b56470aae58e47c6bdf8dd62939db329";
  private static final String API_URL_IDENTITY = "http://koakh.com:5000/v2.0";

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

    //Get Application Singleton
    app = ((Singleton) this.getApplicationContext());
  }

  @Override
  public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
    mEditText = (EditText) findViewById(R.id.editText);
    mEditText.append("Hello Koakh");
    mEditText.scrollTo(0, Integer.MAX_VALUE);

    return super.onCreateView(name, context, attrs);
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
      return PlaceholderFragment.newInstance(position + 1);
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

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }

  public void onClickButtonGetToken(View view) {
    Log.d(app.getTag(), "Tst Log");

    new Thread(new Runnable() {
      @Override
      public void run() {
        try {

          /*
          final RestAdapter restAdapter1 = new RestAdapter.Builder()
            .setEndpoint("https://api.github.com")
            .build();
          IGitHubService service1 = restAdapter1.create(IGitHubService.class);
          List<Object> repos = service1.listRepos("octocat");
          Log.d(app.getTag(), repos.get(0).toString());
          */


          String jsonString = "{\"auth\": {\"tenantName\": \"admin\", \"passwordCredentials\":{\"username\": \"admin\", \"password\": \"kksc28kk\"}}}";
          TypedInput rawJsonBody = new TypedByteArray("application/json", jsonString.getBytes("UTF-8"));

          //TODO: Change with ServiceGenerator
          final RestAdapter authenticateRestAdapter = new RestAdapter.Builder()
            .setEndpoint("http://192.168.1.31:5000/v2.0")
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

          IAuthenticateService authenicateService = authenticateRestAdapter.create(IAuthenticateService.class);
          Log.d(app.getTag(), jsonString);

          Callback<AuthenticateResponse> authenticateCallback = new Callback<AuthenticateResponse>() {
            @Override
            public void success(AuthenticateResponse responseObject, Response responseRaw) {
              app.setAuthenticateResponse(responseObject);
              Log.d(app.getTag(), String.format("AuthenticateToken : [%s]", app.getAuthenticateToken()));
              mEditText.append(app.getAuthenticateToken());
              mEditText.scrollTo(0, Integer.MAX_VALUE);
            }

            @Override
            public void failure(RetrofitError error) {
              Log.e(app.getTag(), String.format("RetrofitError Error : [%s]", error.getCause().getMessage()));
            }
          };
          authenicateService.authenticate(rawJsonBody, authenticateCallback);

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
          IListContainers listContainersService = ServiceGenerator.createService(IListContainers.class, API_URL_SWIFT);

          Callback<List<ListContainersResponse>> listContainersCallback = new Callback<List<ListContainersResponse>>() {
            @Override
            public void success(List<ListContainersResponse> responseObject, Response responseRaw) {
              for (ListContainersResponse container : responseObject) {
                mEditText.append(container.getName());
              }
              mEditText.scrollTo(0, Integer.MAX_VALUE);
            }

            @Override
            public void failure(RetrofitError error) {
            }
          };
          listContainersService.listContainers(app.getAuthenticateToken(), listContainersCallback);

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();
  }

}
