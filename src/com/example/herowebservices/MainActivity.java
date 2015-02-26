package com.example.herowebservices;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends ActionBarActivity {
	ListView lv; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//not this jar :D
		
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();

		
		 
		lv = (ListView) findViewById(R.id.listView1);
		
		
		GetHeroesTask getHeroesTask = new GetHeroesTask();
		getHeroesTask.execute();
		//thats it
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

	private class GetHeroesTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... arg0) {

			String result = HTTPUtils
					.HTTP_GET("http://vishnu.pw/ng/Marvel/js/data/chars.json");
			//http://vishnu.pw/ng/Marvel/js/data/characters.json
			
			return result;

		}

		@Override
		protected void onPostExecute(String result) {

			try {
				ArrayList<Hero> heroes = new ArrayList<Hero>();
				// creating JSONObject with the json string we got from API.
				JSONObject jsonObj = new JSONObject(result);
				// getting the json array of heroes from data.results element in
				// JSON.
				JSONArray heroesJSONArray = jsonObj.getJSONObject("data")
						.getJSONArray("results");
				for (int i = 0; i < heroesJSONArray.length(); i++) {
					// results is an array. heroesJsonArray is results array.
					// we are picking each of the jsonobjects in that array.
					// through loop
					JSONObject curHeroJsonObj = heroesJSONArray
							.getJSONObject(i);
					// this jsonObject has title and description and a thumbnail
					// jsonobject.
					// so what is next we have to get these three things ? yes,
					// and form a hero object.
					// lets get these first. whats the data type of name? all 3
					// are strings. cool.
					// curHeroJSonObj.getString("name") will get the name
					String curName = curHeroJsonObj.getString("name");
					String curDesc = curHeroJsonObj.getString("description");

					// for thumbnail we have to create another object ? good. go
					// ahead

					// the following is wrong
					// JSONObject thumbJsonObj =
					// heroesJSONArray.getJSONObject(i);
					// heroesjsonarray is not the parent of thumbs. neither is
					// it ith element
					// from, heroesjsonarray, we got a current object. he
					// thumbnail is a part of that object.
					// and its parent is not an array, so its not get(i) u can
					// use the field name, like we got the name and desc

					JSONObject thumbsObj = curHeroJsonObj
							.getJSONObject("thumbnail");
					// now we have jsonobject of thumbnail in it, there is path
					// and extension.
					// get those into two string variables

					String path = thumbsObj.getString("path");
					String extension = thumbsObj.getString("extension");

					// tell me this is right ?good going. :)great !!
					// i think we are done with json. yes. now... we gotta
					// display it.
					// we have everything we got for a hero object here, create
					// a hero object.

					Hero curHero = new Hero(curName, curDesc, path + extension);
					// . we have constructor with 3 parameters I cant create
					// object like this ???????
					// cool
					// since we have constructor with 3 parameters in Hero class
					// whenever I create a object of that class I alwats have to
					// pass
					// 3 parameters ??
					// can you suggest me a situation wer you need a hero object
					// yet you dont have these data?
					// no i dont think a situation will come but just for my
					// understanding .
					// usually, datat models never face such a situation. thats
					// y we created such a constructor.
					// if we need, we can overload the construtor. ryt?
					// Y. we can overload keep it. k
					heroes.add(curHero);
					// now... after looping thru all the elements of json array, heroes arraylist
					// will be full of all the heroes we have. :) clear? yes
					// done with the loop.
				}
				
				// we have heroes arraylist with all the heroes. we need to desplay it in the listview.
				//setAdapter 
				// i need to create a list view for that in main lesayout . yes
				// now we have listview, and data to be shown. yes
				// create a myadapter object and set it to the listvie. thats all. :)
				// bad naming
				/// heroes is the arraylist of data to be displayed. and contect is the context of the activity.
				// point me the data to be shown.no. thats a temp variable in the loop. the data to display is an arraylist.
				HeroesAdapter heroadapter = new HeroesAdapter(heroes, MainActivity.this);// context? MainActivity.this
						//we have adapter, set it to the listview and run the app :D
				
				lv.setAdapter(heroadapter);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onPostExecute(result); 
		}

	}

}
