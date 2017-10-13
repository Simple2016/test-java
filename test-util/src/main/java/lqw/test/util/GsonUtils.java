package lqw.test.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public final class GsonUtils {

    public static <T> T fromJson(String jsonStr, Class<? extends T> clazz){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonStr, clazz);
    }

    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }

    public static String toJson(Object obj){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Type type = new TypeToken<Object>() {
        }.getType();

        return gson.toJson(obj, type);
    }

}
