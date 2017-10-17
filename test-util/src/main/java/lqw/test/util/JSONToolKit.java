package lqw.test.util;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JSONToolKit {

	private static final Logger LOG = LoggerFactory.getLogger(JSONToolKit.class);

	public static <T> T fromJson(String json, Class<T> classOfT) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.fromJson(json, classOfT);
	}

	public static String toJson(Object sp) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		Type type = new TypeToken<Object>() {
		}.getType();

		// ServicePattern target = gson.fromJson(sp, type);
		return gson.toJson(sp, type);
	}

	// /**
	// * 判断是否是json结构
	// */
	// public static boolean isJson(String value) {
	// try {
	// new JSONObject(value);
	// } catch (JSONException e) {
	// return false;
	// }
	// return true;
	// }

	public static void main(String[] args) {
		String src = "{\"uid\":\"BS.001.songdl\",\"userName\":\"songdonglin\",\"password\":\"123456\",\"age\":23,\"email\":\"sohosdl@gmail.com\",\"createTime\":\"2012-07-24 13:15:00\",\"birthTime\":\"2012-07-24 13:15:00\"}";
		// fromJson(src,ClassToolKit.getType("com.bosssoft.ds.repository.services.model.User"));
		Map json = fromJson(src, Map.class);
		TypeVariable<?>[] typeVariables = json.getClass().getTypeParameters();
		// System.out.println(typeVariables[0].getClass());
		LOG.info(">>>>>>>>>>>>>>" + typeVariables[0].getClass());

	}
}
