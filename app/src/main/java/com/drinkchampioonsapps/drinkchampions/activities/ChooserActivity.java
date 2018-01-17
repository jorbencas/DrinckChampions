package com.drinkchampioonsapps.drinkchampions.activities;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;


import com.drinkchampioonsapps.drinkchampions.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import butterknife.Unbinder;
import io.fabric.sdk.android.Fabric;

/**
 * Simple list-based Activity to redirect to one of the other Activities. This Activity does not
 * contain any useful code related to Firebase Authentication. You may want to start with
 * one of the following Files:
* {}
 * {@link TwitterActivity}
 */
public class ChooserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "m9AXED7FytTKUoSfbBcIeHnQl";
    private static final String TWITTER_SECRET = "btDpBWLupE2tJ9ZAnWnUZJ1zg3Kn9xYosuPt4tpwY9ic7wcVGk";



    private Unbinder unbinder;

    @BindView(R.id.list_view)
    ListView listView;

    private static final Class[] CLASSES = new Class[] {
           /* GoogleSignInActivity.class*/  TwitterActivity.class
    };

    private static final int[] DESCRIPTION_IDS = new int[] {
            R.string.desc_google_sign_in, R.string.desc_facebook_login, R.string.desc_twitter
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);

        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_chooser);
        unbinder = ButterKnife.bind(this);
        MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES);
        adapter.setDescriptionIds(DESCRIPTION_IDS);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class clicked = CLASSES[position];
        startActivity(new Intent(this, clicked));
    }

    public static class MyArrayAdapter extends ArrayAdapter<Class> {
        private Context mContext;
        private Class[] mClasses;
        private int[] mDescriptionIds;

        public MyArrayAdapter(Context context, int resource, Class[] objects) {
            super(context, resource, objects);
            mContext = context;
            mClasses = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_2, null);
            }
            ((TextView) view.findViewById(android.R.id.text1)).setText(mClasses[position].getSimpleName());
            ((TextView) view.findViewById(android.R.id.text2)).setText(mDescriptionIds[position]);
            return view;
        }

        public void setDescriptionIds(int[] descriptionIds) {
            mDescriptionIds = descriptionIds;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_twitter, menu);
        return true;
    }




}
