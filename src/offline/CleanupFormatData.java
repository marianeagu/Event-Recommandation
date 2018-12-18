/**
 * 
 */
package offline;

import java.io.FileReader;
import database.mongodb.*;
import org.bson.Document;
import java.io.BufferedReader;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author neagum
 *
 */
public class CleanupFormatData {
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase(MongoDB.DB_NAME);
		// The name of the file to open.
		// Windows is different : C:\\Documents\\ratings_Musical_Instruments.csv
		String fileName = "/Users/daniel/Downloads-keep/ratings_Musical_Instruments.csv";

		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] values = line.split(","); // why split using "," here? because the raw data is separated by ","
				// transfer table to collection(collection is table, and document is row)
				// in original table, the first column(attribute) is use_id, the second is item_id, the third is ratings, the last is ratings time(we don't care)
				db.getCollection("ratings")
						.insertOne(
								new Document()
										.append("user", values[0])
										.append("item", values[1])
										.append("rating",
												Double.parseDouble(values[2])));

			}
			System.out.println("Import Done!");
			bufferedReader.close();
			mongoClient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
