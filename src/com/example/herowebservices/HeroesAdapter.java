package com.example.herowebservices;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroesAdapter extends BaseAdapter {

	ArrayList<Hero> heroes;
	Activity context;

	public HeroesAdapter(ArrayList<Hero> heroes, Activity context) {
		this.heroes = heroes;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return heroes.size();
	}

	@Override
	public Hero getItem(int arg0) {
		// TODO Auto-generated method stub
		return heroes.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.row_hero, null, false);

		Hero hero = getItem(arg0);

		ImageView img = (ImageView) v.findViewById(R.id.img1);
		TextView tvLarge = (TextView) v.findViewById(R.id.tv1);
		TextView tvMedium = (TextView) v.findViewById(R.id.tv2);

		img.setImageResource(R.drawable.ic_launcher);
		tvLarge.setText(hero.getName());
		tvMedium.setText(hero.getDescription());


		return v;
	}

}
