/**
 * 
 */
package externalAPI;
import java.util.*;
import entity.Item;

/**
 * @author neagum
 *
 */
public interface ExternalAPI {
	public List<Item> search(double lat, double lon, String term);

}
