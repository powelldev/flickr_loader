package michaelpowell.razilabtakehome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ImagesLoadedListener {

  private static final String LOG_TAG = "RaizLabsTakeHome";

  private static final String SOURCE_URL = "http://api.flickr.com/services/feeds/photos_public.gne?tags=boston";
  private static final String GRID_FRAGMENT_TAG = "grid_fragment_tag";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.container, new GridFragment(), GRID_FRAGMENT_TAG)
        .commit();
  }

  @Override
  protected void onResume() {
    super.onResume();
    new LoadFlickrCityTask(SOURCE_URL, this).execute();
  }

  @Override
  public void onImagesLoaded(final List<String> images) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        ((GridFragment) getSupportFragmentManager().findFragmentByTag(GRID_FRAGMENT_TAG)).setData(images);
      }
    });
  }

  @Override
  public void onImagesFailed(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

}
