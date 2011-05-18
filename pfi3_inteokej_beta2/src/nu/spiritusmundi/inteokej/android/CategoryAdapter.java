package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<String> {

	private ArrayList<String> categories;
	private BrowseQuestions parentActivity;
	
	public CategoryAdapter(Context context, int textViewResourceId, ArrayList<String> categories) {
		super(context, textViewResourceId, categories);
		
		this.parentActivity = (BrowseQuestions) context;
		this.categories = categories;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = parentActivity.getLayoutInflater();
		View row = inflater.inflate(R.layout.browse_categories_item, parent, false);
		
		TextView lable = (TextView) row.findViewById(R.id.category_title);
		lable.setTypeface(Typeface.createFromAsset(parentActivity.getAssets(), "fonts/MyriadWebPro.ttf"), Typeface.BOLD);
		lable.setText(categories.get(position));
		
		return row;
	}


}
