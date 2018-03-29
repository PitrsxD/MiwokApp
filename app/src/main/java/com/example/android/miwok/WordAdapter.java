package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pitrs on 11.03.2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColor;

    public WordAdapter (Activity context, ArrayList<Word> word,int backgroundColor) {
        super (context, 0, word);
        mBackgroundColor = backgroundColor;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

    }

    Word currentWordAdapter = getItem(position);

        final View textContainer = listItemView.findViewById(R.id.translationCategory);
        textContainer.setBackgroundResource(mBackgroundColor);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWordAdapter.getDefaultTranslation());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWordAdapter.getMiwokTranslation());

            ImageView defaultImageView = listItemView.findViewById(R.id.default_image);
            if (currentWordAdapter.hasImage()){
                defaultImageView.setImageResource(currentWordAdapter.getDefaultImage());
                defaultImageView.setVisibility(View.VISIBLE);
            } else {
                defaultImageView.setVisibility(View.GONE);
            }


        return listItemView;
    }
}

