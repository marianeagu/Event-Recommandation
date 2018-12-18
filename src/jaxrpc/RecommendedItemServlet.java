package jaxrpc;

import entity.Item;
import java.util.List;
import java.io.IOException;
import org.json.JSONArray;
import javax.servlet.http.*;
import recommendationAlgo.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class RecommendedItemServlet
 */
@WebServlet("/RecommendedItemServlet")
public class RecommendedItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendedItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.setStatus(403);
			return;
		}

		String userId = session.getAttribute("user").toString();
		// String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		Recommendation recommendation = new GeographicalRecommendation();
		List<Item> items = recommendation.recommendItems(userId, lat, lon);

		// Convert the list of items into json array.
		try {
			JSONArray array = JaxrpcHelper.getJSONArray(items);
			JaxrpcHelper.writeJsonArray(response, array);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
