package com.example.miku_project.helper;

import android.media.MediaPlayer;
import android.os.AsyncTask;

class Player extends AsyncTask<String, Void, Boolean> {
    MediaPlayer mediaPlayer;

    @Override
    protected Boolean doInBackground(String... strings) {
        Boolean prepared = false;

        try {
            mediaPlayer.setDataSource(strings[0]);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

            mediaPlayer.prepare();
            prepared = true;
        } catch (Exception e) {
            prepared = false;
        }

        return prepared;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        mediaPlayer.start();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}
