package network;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class JsonConfig {
	static Gson gson = null;
	
	public JsonConfig () {};
	
	public static JsonObject creaJson() {
		gson = new GsonBuilder().create();
		JsonObject objecte = null;
		try {
			objecte = (JsonObject) gson.fromJson (new FileReader("config.json"), JsonObject.class);
			objecte = objecte.get("configuration").getAsJsonObject();

		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objecte;

	}
	
	
}
