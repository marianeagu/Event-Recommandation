package jaxrpc;

import java.util.*;
import org.json.*;
import database.*;
import entity.Item;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ItemHistory
 */
@WebServlet("/ItemHistory")
public class ItemHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection conn = DatabaseConnectionFactory.getDBConnection();

	/**
	 * Default constructor.
	 */
	public ItemHistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// String user_id = request.getParameter("user_id");
		if (session.getAttribute("user") == null) {
			response.setStatus(403);
			return;
		}

		String userId = session.getAttribute("user").toString();
		// String userId = request.getParameter("user_id");
		Set<Item> items = conn.getFavoriteItems(userId);
		JSONArray array = new JSONArray();
		for (Item item : items) {
			JSONObject obj = item.toJSONObject();
			try {
				obj.append("favorite", true);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			array.put(obj);
		}
		JaxrpcHelper.writeJsonArray(response, array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("user") == null) {
				response.setStatus(403);
				return;
			}

			String userId = session.getAttribute("user").toString();
			JSONObject input = JaxrpcHelper.readJsonObject(request);
			// String userId = input.getString("user_id");
			JSONArray array = (JSONArray) input.get("favorite");

			List<String> histories = new ArrayList<>();
			for (int i = 0; i < array.length(); i++) {
				String itemId = (String) array.get(i);
				histories.add(itemId);
			}
			conn.setFavoriteItems(userId, histories);
			JaxrpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Is this safe enough? NO!!! We only checked whether the user has logged in, but did not check who logged in.(Hint: we should use the user id in the session attribute instead of in the url.)

			HttpSession session = request.getSession();
			if (session.getAttribute("user") == null) {
				response.setStatus(403);
				return;
			}

			String userId = session.getAttribute("user").toString();
			JSONObject input = JaxrpcHelper.readJsonObject(request);
			// String userId = input.getString("user_id");
			JSONArray array = (JSONArray) input.get("favorite");

			List<String> histories = new ArrayList<>();
			for (int i = 0; i < array.length(); i++) {
				String itemId = (String) array.get(i);
				histories.add(itemId);
			}
			conn.unsetFavoriteItems(userId, histories); // the only difference between doPost and doDelete is here
			JaxrpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
