package object;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Native {@link INews} implementation.
 */
public class News implements INews {

  @Override
  public Long getId() {
    return Long.MIN_VALUE;
  }

  @Override
  public String getTitle() {
    return "";
  }

  @Override
  public String getDescription() {
    return "";
  }

  @Override
  public String getAuthor() {
    return "";
  }
  
  @Override
  public Date getRequestedDate() {
    return new Date();
  }

  @Override
  public Date getDate() {
    return null;
  }

  @Override
  public Category getCategory() {
    return null;
  }

  public Collection<INews> getConnectedNews() {
    return Collections.EMPTY_SET;
  }

  @Override
  public String getUrl() {
    return "";
  }

}
