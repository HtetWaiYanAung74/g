/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.allthenews;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class News {

  public String title;
  public String description;
  public String imageUrl;
  public String instructionUrl;
  public String label;

  public static ArrayList<News> getRecipesFromFile(String filename, Context context){
    final ArrayList<News> newsList = new ArrayList<>();

    try {
      // Load data
      String jsonString = loadJsonFromAsset("news.json", context);
      JSONObject json = new JSONObject(jsonString);
      JSONArray news1 = json.getJSONArray("news");

      // Get News objects from data
      for(int i = 0; i < news1.length(); i++){
        News news = new News();

        news.title = news1.getJSONObject(i).getString("title");
        news.description = news1.getJSONObject(i).getString("description");
        news.imageUrl = news1.getJSONObject(i).getString("urlToImage");
        news.instructionUrl = news1.getJSONObject(i).getString("url");
        news.label = news1.getJSONObject(i).getString("author");

        newsList.add(news);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return newsList;
  }

  private static String loadJsonFromAsset(String filename, Context context) {
    String json = null;

    try {
      InputStream is = context.getAssets().open(filename);
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "Unicode");
    }
    catch (java.io.IOException ex) {
      ex.printStackTrace();
      return null;
    }

    return json;
  }

}
