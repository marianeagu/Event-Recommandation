package jaxrpc;

import database.*;
import org.json.*;
import java.util.*;
import entity.Item;
import externalAPI.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class SearchItemServlet
 */
@WebServlet("/SearchItemServlet")
public class SearchItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection conn = DatabaseConnectionFactory.getDBConnection();
	private static final Logger LOGGER = Logger.getLogger(SearchItemServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.setStatus(403);
			return;
		}

		String userId = session.getAttribute("user").toString();
		// String userId = "1111";
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		// String userId = request.getParameter("user_id");
		// Term can be empty or null.
		String term = request.getParameter("term");
		// following two lines moved to MySQLConnection.java's searchItems()
		// ExternalAPI externalAPI = ExternalAPIFactory.getExternalAPI();
		// List<Item> items = externalAPI.search(lat, lon, term);
		// LOGGER.log(Level.INFO, "lat:" + lat + ",lon:" + lon);
		List<Item> items = conn.searchItems(userId, lat, lon, term);
		List<JSONObject> list = new ArrayList<>();

		Set<String> favorite = conn.getFavoriteItemIds(userId);
		try {
			for (Item item : items) {
				// Add a thin version of item object
				JSONObject obj = item.toJSONObject();
				// check if this is a favorite one.
				// this field is required by frontend to correctly display favorite items.
				if (favorite != null) {
					obj.put("favorite", favorite.contains(item.getItemId()));
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray array = new JSONArray(list);
		JaxrpcHelper.writeJsonArray(response, array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
