package com.freeman.printfleet.utilities;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil implements Serializable {

	public static String obj2json(Object bean, Type type) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(java.util.Date.class,
						new UtilDateSerializer())
				.setDateFormat(DateFormat.LONG).create();
		return gson.toJson(bean);
	}

	public static Object json2Obj(String json, Type type) {
		Gson gson = new GsonBuilder().registerTypeAdapter(
				java.util.Date.class,
				new UtilDateDeserializer()).setDateFormat(DateFormat.LONG).create();
		return gson.fromJson(json, type);
	}

	public static String obj2json(Object bean) {
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss")
				.create();
		return gson.toJson(bean);
	}

	public static <T> List<T> getListFromJson(Object obj, Class<T> clazz) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		List<T> list = new ArrayList<T>();
		for (Iterator<String> iter = jsonObject.keys(); iter.hasNext();) {

			String key = iter.next();
			JSONArray array = jsonObject.getJSONArray(key);
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = (JSONObject) array.get(i);
				T t = (T) JSONObject.toBean(object, clazz);
				if (t != null)
					list.add(t);
			}
		}
		return list;
	}

	public static <T> List<T> getListFromJsonArray(String jsonString, Class<T[]> arrayClazz) {
		
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(java.util.Date.class, new UtilDateSerializer())
				.setDateFormat(DateFormat.LONG).create();
		
		List<T> list = new ArrayList<T>();
		T[] array = (T[]) gson.fromJson(jsonString, arrayClazz);
		for (T entity : array) {
			list.add(entity);
		}
		return list;
	}
	
	public static <T> T[] getArrayFromJson(String jsonString, Class<T[]> clazz) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(java.util.Date.class,
				new UtilDateSerializer())
				.setDateFormat(DateFormat.LONG).create();

	    T[] array = (T[]) gson.fromJson(jsonString, clazz);
		return array;
	}
	
	public static JSONObject parseJSON(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject;
	}
}
