package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
	private static HttpURLConnection connection;

	public static void main(String[] args) throws IOException {
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		URL url = new URL("https://jsonplaceholder.typicode.com/comments");
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(10000);
		connection.setReadTimeout(10000);

		int status = connection.getResponseCode();
		System.out.println(status);

		if (status > 200) {
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			while ((line = reader.readLine()) != null) {
				responseContent.append(line);
			}
			reader.close();
		} else {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) {
				responseContent.append(line);
			}
			reader.close();
		}
		//System.out.println(responseContent.toString());
		parse(responseContent.toString());
	}
	public static String parse(String responseBody) {
		JSONArray comments=new JSONArray(responseBody);
		for(int i=0;i<comments.length();i++) {
			JSONObject comment= comments.getJSONObject(i);
			int postId= comment.getInt("postId");
			int id= comment.getInt("id");
			String name= comment.getString("name");
			String email= comment.getString("email");
			String body= comment.getString("body");
			System.out.println(name+" ----  "+ email);
		} return null;
	}

}
