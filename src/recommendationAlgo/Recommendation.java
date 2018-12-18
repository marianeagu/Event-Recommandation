/**
 * 
 */
package recommendationAlgo;

import java.util.List;
import entity.Item;

/**
 * @author neagum
 *
 */

public interface Recommendation {
	public List<Item> recommendItems(String userId, double latitude, double longitude);
}
