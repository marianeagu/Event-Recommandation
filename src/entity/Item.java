/**
 * 
 */
package entity;

import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author neagum
 *
 */
public class Item {
	private String itemId;
	private String name;
	private double rating;
	private String address;
	private String city;
	private String country;
	private String state;
	private String zipcode;
	private double latitude;
	private double longitude;
	private String description;
	private Set<String> categories;
	private String imageUrl;
	private String url;
	private String snippet;
	private String snippetUrl;

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the categories
	 */
	public Set<String> getCategories() {
		return categories;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the snippet
	 */
	public String getSnippet() {
		return snippet;
	}

	/**
	 * @return the snippetUrl
	 */
	public String getSnippetUrl() {
		return snippetUrl;
	}

	// method to convert an Item object a JSONObject instance
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("item_id", itemId);
			obj.put("name", name);
			obj.put("rating", rating);
			obj.put("address", address);
			obj.put("city", city);
			obj.put("country", country);
			obj.put("state", state);
			obj.put("zipcode", zipcode);
			obj.put("latitude", latitude);
			obj.put("longitude", longitude);
			obj.put("description", description);
			obj.put("categories", new JSONArray(categories));
			obj.put("image_url", imageUrl);
			obj.put("url", url);
			obj.put("snippet_url", snippetUrl);
			obj.put("snippet", snippet);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * This is a builder pattern in Java.
	 */
	public Item(ItemBuilder builder) {
		this.itemId = builder.itemId;
		this.name = builder.name;
		this.rating = builder.rating;
		this.address = builder.address;
		this.city = builder.city;
		this.country = builder.country;
		this.state = builder.state;
		this.zipcode = builder.zipcode;
		this.latitude = builder.latitude;
		this.longitude = builder.longitude;
		this.description = builder.description;
		this.categories = builder.categories;
		this.imageUrl = builder.imageUrl;
		this.url = builder.url;
		this.snippet = builder.snippet;
		this.snippetUrl = builder.snippetUrl;
	}

	/**
	 * @author neagum
	 *
	 */
	public static class ItemBuilder {
		private String itemId;
		private String name;
		private double rating;
		private String address;
		private String city;
		private String country;
		private String state;
		private String zipcode;
		private double latitude;
		private double longitude;
		private String description;
		private Set<String> categories;
		private String imageUrl;
		private String url;
		private String snippet;
		private String snippetUrl;

		/**
		 * @param itemId the itemId to set
		 */
		public ItemBuilder setItemId(String itemId) {
			this.itemId = itemId;
			return this; // By returning the builder each time, we can create a fluent interface.
		}

		/**
		 * @param name the name to set
		 */
		public ItemBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * @param rating the rating to set
		 */
		public ItemBuilder setRating(double rating) {
			this.rating = rating;
			return this;
		}

		/**
		 * @param address the address to set
		 */
		public ItemBuilder setAddress(String address) {
			this.address = address;
			return this;
		}

		/**
		 * @param city the city to set
		 */
		public ItemBuilder setCity(String city) {
			this.city = city;
			return this;
		}

		/**
		 * @param country the country to set
		 */
		public ItemBuilder setCountry(String country) {
			this.country = country;
			return this;
		}

		/**
		 * @param state the state to set
		 */
		public ItemBuilder setState(String state) {
			this.state = state;
			return this;
		}

		/**
		 * @param zipcode the zipcode to set
		 */
		public ItemBuilder setZipcode(String zipcode) {
			this.zipcode = zipcode;
			return this;
		}

		/**
		 * @param latitude the latitude to set
		 */
		public ItemBuilder setLatitude(double latitude) {
			this.latitude = latitude;
			return this;
		}

		/**
		 * @param longitude the longitude to set
		 */
		public ItemBuilder setLongitude(double longitude) {
			this.longitude = longitude;
			return this;
		}

		/**
		 * @param description the description to set
		 */
		public ItemBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		/**
		 * @param categories the categories to set
		 */
		public ItemBuilder setCategories(Set<String> categories) {
			this.categories = categories;
			return this;
		}

		/**
		 * @param imageUrl the imageUrl to set
		 */
		public ItemBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		/**
		 * @param url the url to set
		 */
		public ItemBuilder setUrl(String url) {
			this.url = url;
			return this;
		}

		/**
		 * @param snippet the snippet to set
		 */
		public ItemBuilder setSnippet(String snippet) {
			this.snippet = snippet;
			return this;
		}

		/**
		 * @param snippetUrl the snippetUrl to set
		 */
		public ItemBuilder setSnippetUrl(String snippetUrl) {
			this.snippetUrl = snippetUrl;
			return this;
		}

		/**
		 * Here we create the actual Item object, which is always in a fully initialised
		 * state when it's returned.
		 * 
		 * @return
		 */
		public Item build() {
			return new Item(this);
		}
	}

	
	// hashCode() and equals() methods for itemId used for de-duplicate items in
	// recommendation results.
	// Since we’re using set to save results from our recommendation function, we
	// need do the de-duplication based on item_id.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

}
