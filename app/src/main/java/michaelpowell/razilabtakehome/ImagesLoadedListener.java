package michaelpowell.razilabtakehome;

import java.util.List;

interface ImagesLoadedListener  {
  public void onImagesLoaded(List<String> images);
  public void onImagesFailed(String message);
}

