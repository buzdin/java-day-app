package lv.jug.javaday.androidapp.domain;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey Nikolaenko
 * Date: 11/26/13
 * Time: 9:58 PM
 */
public class Vote {
	public static final Integer EXCELLENT = 1;
	public static final Integer GOOD = 0;
	public static final Integer BAD = -1;

	public static HttpEntity create(Integer rate, Integer sessionId, String comment) {
		HttpEntity entity = null;
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("action", "jdayriga");
			jsonObject.put("target", sessionId);
			jsonObject.put("amount", rate);
			jsonObject.put("comment", comment);
			entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
