package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersFragment extends Fragment {

    private MediaPlayer myMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager myAudioManager;
    final AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                myMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
                myAudioManager.abandonAudioFocus(afChangeListener);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                myMediaPlayer.pause();
                myMediaPlayer.seekTo(0);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        //Will create array with english numbers from 1 to 10
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one","lutti",R.drawable.number_one, R.raw.number_one));
        words.add(new Word ("two","otiiko",R.drawable.number_two, R.raw.number_two));
        words.add(new Word ("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word ("four", "oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word ("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word ("six","temmokka",R.drawable.number_six, R.raw.number_six));
        words.add(new Word ("seven","kenekaku",R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word ("eight","kawinta",R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word ("nine", "wo'e",R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word ("ten", "na'aacha",R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter (getActivity(), words, R.color.category_numbers);

        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        myAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                int result = myAudioManager.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    Log.i("NumberActivity", "Current word" + word.toString());
                    myMediaPlayer = MediaPlayer.create(getActivity(), word.getDefaultSound());
                    myMediaPlayer.start();
                    myMediaPlayer.setOnCompletionListener(mCompletionListener);
                }}});
        return rootView;
    }



    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer (){
        if (myMediaPlayer != null){
            myMediaPlayer.release();
            myMediaPlayer = null;
            myAudioManager.abandonAudioFocus(afChangeListener);

        }
    }


}
