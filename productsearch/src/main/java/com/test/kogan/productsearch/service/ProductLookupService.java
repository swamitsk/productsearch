package com.test.kogan.productsearch.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductLookupService {

	public String getTotalProductWeight(String category) {

		JSONArray allObject = new JSONArray();
		try {

			String baseUrl = "http://wp8m3he1wt.s3-website-ap-southeast-2.amazonaws.com";
			String resource = "/api/products/1";
			while (!resource.equals("null")) {
				URL url = new URL(baseUrl + resource);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				StringBuffer output = new StringBuffer();
				String tempOutput = null;

				while ((tempOutput = br.readLine()) != null) {
					output.append(tempOutput);
				}

				JSONObject jsonObject = new JSONObject(output.toString());
				JSONArray jsonarray = (JSONArray) jsonObject.get("objects");

				
				
				int current = 0;
				while (current != jsonarray.length()) {
					JSONObject saleableObject = (JSONObject) jsonarray.get(current);
					current++;

					if (saleableObject.get("category").equals(category)) {
						// get the dimension array
						JSONObject sizeObj = (JSONObject) saleableObject.get("size");
						double width = (sizeObj.getDouble("width")) / 100;
						double height = (sizeObj.getDouble("height")) / 100;
						double length = (sizeObj.getDouble("length")) / 100;

						DecimalFormat roundFormatter = new DecimalFormat("########0.00000");
						double val = Double.parseDouble(roundFormatter.format(width * height * length * 250));
						
						saleableObject.append("totalWeight", val);
						allObject.put(saleableObject);
					}
				}

				resource = jsonObject.getString("next");
				conn.disconnect();
			}
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return allObject.toString();
	}

	public String getAllProductCategories() {

		Set<String> setD = new TreeSet<String>();

		JSONObject allObject = new JSONObject();

		try {

			String baseUrl = "http://wp8m3he1wt.s3-website-ap-southeast-2.amazonaws.com";
			String resource = "/api/products/1";
			while (!resource.equals("null")) {
				URL url = new URL(baseUrl + resource);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				StringBuffer output = new StringBuffer();
				String tempOutput = null;

				while ((tempOutput = br.readLine()) != null) {
					output.append(tempOutput);
				}

				JSONObject jsonObject = new JSONObject(output.toString());
		
				JSONArray jsonarray = (JSONArray) jsonObject.getJSONArray("objects");
				int current = 0;
				while (current != jsonarray.length()) {
					JSONObject saleableObject = (JSONObject) jsonarray.get(current);
					setD.add(saleableObject.getString("category"));
					current++;
				}

				resource = jsonObject.getString("next");
				conn.disconnect();
				
			}
			allObject.put("name", new JSONArray(setD));

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (JSONException e) {
			e.printStackTrace();
		}
			return allObject.toString();
	}
}
