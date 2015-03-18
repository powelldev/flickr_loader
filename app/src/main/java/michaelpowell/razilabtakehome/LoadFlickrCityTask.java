package michaelpowell.razilabtakehome;


import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LoadFlickrCityTask extends AsyncTask<Void, Void, Void> {

  URL url;
  final ImagesLoadedListener listener;

  public LoadFlickrCityTask(String urlStr, ImagesLoadedListener listener) {
    this.listener = listener;
    try {
      url = new URL(urlStr);
    } catch (MalformedURLException e) {
      listener.onImagesFailed(e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  protected Void doInBackground(Void... params) {
    FlickrCity boston = new FlickrCity(url);
    List<String> images = boston.parseImages();
    if (images.size() > 0) {
      listener.onImagesLoaded(images);
    } else {
      listener.onImagesFailed("");
    }
    return null;
  }
}


