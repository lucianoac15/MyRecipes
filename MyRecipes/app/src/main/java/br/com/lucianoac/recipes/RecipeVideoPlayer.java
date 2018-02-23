package br.com.lucianoac.recipes;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.Objects;

public final class RecipeVideoPlayer implements LifecycleObserver {

    private SimpleExoPlayer player;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;

    @NonNull
    private Context context;
    private String videoUrl;
    private SimpleExoPlayerView playerView;

    public RecipeVideoPlayer(@NonNull final Context context,
                             @NonNull final String videoUrl,
                             @NonNull final SimpleExoPlayerView playerView,
                             final long lastPosition) {
        Objects.requireNonNull(context, "Context não pode ser nulo.");
        Objects.requireNonNull(playerView, "playerView não pode ser nulo.");
        this.context = context;
        this.videoUrl = videoUrl;
        this.playerView = playerView;
        this.playbackPosition = lastPosition;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {
        if (player == null && playerView != null) {
            player = ExoPlayerFactory
                    .newSimpleInstance(
                            new DefaultRenderersFactory(context),
                            new DefaultTrackSelector(),
                            new DefaultLoadControl()
                    );
            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
            MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
            player.prepare(mediaSource, true, false);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri, new DefaultHttpDataSourceFactory("exoplayer-codelab"),
                new DefaultExtractorsFactory(), null, null);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }

        if (playerView != null) {
            playerView = null;
        }
    }

    public long getLastPosition() {
        return playbackPosition;
    }


}
