package com.movies.lab.utils;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noor aka Thor on 2020-04-11.
 */
public class JsonUtil {

    /**
     * @param object model class
     * @param classOfT Class to convert to json
     * @return jsonString converted from given object
     */
    public static String parseObject(@NonNull Object object, @NonNull Class classOfT) {
        return new Gson().toJson(object, classOfT);
    }

    /**
     * @param jsonString should be a JsonObject
     * @param classOfT Class to filled from jsonString
     * @param <T> Generics
     * @return classOfT filled from jsonString
     */
    public static <T> T parseObject(@NonNull String jsonString, @NonNull Class<T> classOfT) {
        return new Gson().fromJson(jsonString, classOfT);
    }

    /**
     * @param jsonElement directly from ResponseBody. It should have a jsonArray but i'm also check might be it have jsonObject
     * @param classOfT Class to filled from jsonElement
     * @param <T> Generics
     * @return classOfT filled from jsonElement
     */
    public static <T> T parseObject(@NonNull JsonElement jsonElement, @NonNull Class<T> classOfT) {
        Gson gson = new Gson();
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray != null && jsonArray.size() > 0) {
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                return gson.fromJson(jsonObject.toString(), classOfT);
            }
        } else if (jsonElement.isJsonObject()) {
            return gson.fromJson(jsonElement.getAsJsonObject().toString(), classOfT);
        }
        return null;
    }

    /**
     * @param jsonElement directly from ResponseBody. It should have a jsonArray but i'm also check might be it have jsonObject
     * @param classOfT Class to filled from jsonElement
     * @param <T> Generics
     * @return List<classOfT></classOfT> filled from jsonElement
     *
     * use TypeToken.getParameterized this method to achieve parsing using Generics
     */
    public static <T> List<T> parseArray(@NonNull JsonElement jsonElement, @NonNull Class<T> classOfT) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray != null && jsonArray.size() > 0) {
//                Type type = new TypeToken<List<T>>() {}.getType();
                Type type = TypeToken.getParameterized(List.class, classOfT).getType();
                list = gson.fromJson(jsonArray.toString(), type);
            }
        } else if (jsonElement.isJsonObject()) {
            list.add(gson.fromJson(jsonElement.getAsJsonObject().toString(), classOfT));
        }
        return list;
    }

    /**
     * @param jsonElement directly from ResponseBody. It should have a jsonArray but i'm also check might be it have jsonObject
     * @param classOfT Class to filled from jsonElement
     * @param key key from to get array from parent JsonElement
     * @param <T> Generics
     * @return List<classOfT></classOfT> filled from jsonElement
     *
     * use TypeToken.getParameterized this method to achieve parsing using Generics
     */
    public static <T> List<T> parseArrayFromKey(@NonNull JsonElement jsonElement, @NonNull Class<T> classOfT, String key) {
        jsonElement = getValueAsJsonElement(jsonElement, key);
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray != null && jsonArray.size() > 0) {
//                Type type = new TypeToken<List<T>>() {}.getType();
                Type type = TypeToken.getParameterized(List.class, classOfT).getType();
                list = gson.fromJson(jsonArray.toString(), type);
            }
        } else if (jsonElement.isJsonObject()) {
            list.add(gson.fromJson(jsonElement.getAsJsonObject().toString(), classOfT));
        }
        return list;
    }

    /**
     * @param jsonElement directly from ResponseBody. It should have a jsonObject but i'm also check might be it have jsonArray
     * @param key key of value to extract
     * @return String value
     */
    public static String getStringValue(@NonNull JsonElement jsonElement, @NonNull String key) {
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray != null && jsonArray.size() > 0) {
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                return jsonObject.get(key).toString();
            }
        } else if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject().get(key).getAsString();
        }
        return null;
    }

    /**
     * @param jsonElement directly from ResponseBody. It should have a jsonObject but i'm also check might be it have jsonArray
     * @param key key of value to extract
     * @return JsonElement value
     */
    public static JsonElement getValueAsJsonElement(@NonNull JsonElement jsonElement, @NonNull String key) {
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray != null && jsonArray.size() > 0) {
                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                return jsonObject.get(key);
            }
        } else if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject().get(key);
        }
        return null;
    }

    /**
     * @param jsonElement directly from ResponseBody.
     * @param key key of value to extract
     * @return boolean value
     */
    public static boolean getBooleanValue(@NonNull JsonElement jsonElement, @NonNull String key) {
        if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject().get(key).getAsBoolean();
        }
        return false;
    }
}

















