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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends ActionBarActivity {
	ListView lv; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .build();
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
		
		ImageLoader.getInstance().init(config);

		lv = (ListView) findViewById(R.id.listView1);
		
		GetHeroesTask getHeroesTask = new GetHeroesTask();
		getHeroesTask.execute();
		
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
			
			
			return result;

		}

		@Override
		protected void onPostExecute(String result) {

			try {
				ArrayList<Hero> heroes = new ArrayList<Hero>();
				// creating JSONObject 
				JSONObject jsonObj = new JSONObject(result);
				// getting the json array of heroes 
				JSONArray heroesJSONArray = jsonObj.getJSONObject("data")
						.getJSONArray("results");
				for (int i = 0; i < heroesJSONArray.length(); i++) {
					
					JSONObject curHeroJsonObj = heroesJSONArray
							.getJSONObject(i);
					
					String curName = curHeroJsonObj.getString("name");
					String curDesc = curHeroJsonObj.getString("description");

					JSONObject thumbsObj = curHeroJsonObj
							.getJSONObject("thumbnail");
					
					String path = thumbsObj.getString("path");
					String extension = thumbsObj.getString("extension");
					
					Hero curHero = new Hero(curName, curDesc, path + extension);
					heroes.add(curHero);
					
				}
					
					HeroesAdapter heroadapter = new HeroesAdapter(heroes, MainActivity.this);
					lv.setAdapter(heroadapter);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onPostExecute(result); 
		}

	}

}
