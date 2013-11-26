package lv.jug.javaday.androidapp.common;

import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;
import org.apache.http.HttpEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey Nikolaenko
 * Date: 11/26/13
 * Time: 10:41 PM
 */
public class FeedbackService {
	public static final String BASE_URL = "http://dmi3.net/plusminus/";
	private static final String JSON_TYPE = "application/json";
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void post(Context context, HttpEntity entity, ResponseHandlerInterface responseHandler) {
		client.post(context, BASE_URL, entity, JSON_TYPE, responseHandler);
	}
}
