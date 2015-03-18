package michaelpowell.razilabtakehome;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Loads image urls from a flickr xml url.
 */
public class FlickrCity {

  private Document document;

  FlickrCity(URL url) {
    try {
      URLConnection urlConn = url.openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      String line;
      String xmlData = "";
      while ((line = reader.readLine()) != null) {
        xmlData += line + "\n";
      }
      reader.close();
      document = DocumentHelper.parseText(xmlData);
    } catch (IOException | DocumentException e) {
      e.printStackTrace();
    }
  }

  public List<String> parseImages() {
    List<String> images = new ArrayList<>();
    if (document != null) {
      for (Element entryElement : document.getRootElement().elements("entry")) {
        // There are two link tags per entry, only one has the enclosure containing the jpg.
        for (Element linkElement : entryElement.elements("link")) {
          if (linkElement.attribute("rel").getValue().equals("enclosure")) {
            images.add(linkElement.attribute("href").getValue());
          }
        }
      }
    }
    return images;
  }

}
