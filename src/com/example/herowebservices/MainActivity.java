package com.example.herowebservices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);




	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class GetHeroesTask extends AsyncTask<String, String, String>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... arg0) {

			String result = HTTPUtils.HTTP_GET("http://vishnu.pw/ng/Marvel/js/data/characters.json");
			return result;

		}

		@Override
		protected void onPostExecute(String result) {

			try {
				// creating JSONObject with the json string we got from API.
				JSONObject  jsonObj = new JSONObject(result);
				// getting the json array of heroes from data.results element in JSON.
				JSONArray heroesJSONArray = jsonObj.getJSONObject("data").getJSONArray("results");
				for (int i =0 ; i< heroesJSONArray.length(); i++){
					// results is an array. heroesJsonArray is results array.
					//  we are picking each of the jsonobjects in that array. through loop
					JSONObject curHeroJsonObj =  heroesJSONArray.getJSONObject(i);
					// this jsonObject has title and description and a thumbnail jsonobject.
					// so what is next we have to get these three things ? yes, and form a hero object.
					// lets get these first. whats the data type of name? all 3 are strings. cool.
					// curHeroJSonObj.getString("name") will get the name
					String curName = curHeroJsonObj.getString("name");
					String curDesc = curHeroJsonObj.getString("description");
					
					// for thumbnail we have to create another object ? good. go ahead
					
					//the following is wrong 
					JSONObject thumbJsonObj =  heroesJSONArray.getJSONObject(i);
					//heroesjsonarray is not the parent of thumbs. neither is it ith element
					// from, heroesjsonarray, we got a current object. he thumbnail is a part of that object.
					//and its parent is not an array, so its not get(i) u can use the field name, like we got the name and desc
					
					JSONObject thumbsObj = curHeroJsonObj.getJSONObject("thumbnail");
					//can we display like this itself ?
					
				
				
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onPostExecute(result);
		}


	}


}
