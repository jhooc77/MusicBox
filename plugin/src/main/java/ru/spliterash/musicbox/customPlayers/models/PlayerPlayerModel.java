package ru.spliterash.musicbox.customPlayers.models;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import lombok.Getter;
import ru.spliterash.musicbox.Lang;
import ru.spliterash.musicbox.players.PlayerWrapper;

/**
 * Небольшая модель
 * Которая применима ко всем проигрывателям игроков
 */
@Getter
public class PlayerPlayerModel {

    private final PlayerWrapper wrapper;
    private final MusicBoxSongPlayerModel model;

    public PlayerPlayerModel(PlayerWrapper wrapper, MusicBoxSongPlayerModel model) {
        this.wrapper = wrapper;
        this.model = model;
        wrapper.setBarProgress(0);
        wrapper.setBarVisible(true);
        wrapper.setBarTitle(Lang.CURRENT_PLAYNING.toString("{song}", model.getCurrentSong().getName()));
        SongPlayer songPlay = model.getMusicBoxSongPlayer().getApiPlayer();
        songPlay.addPlayer(wrapper.getPlayer());
    }

    /**
     * Вызывается до воспроизведения следующего тика
     *
     * @param all     Кол-во тиков в мелодии
     * @param current Текущий тик
     */
    public void nextTick(int all, int current) {
        if (current == -1) {
            return;
        }
        if (current == 0) {
            if (model.getPlayList().hasNext() && model.getPlayList().getNextSongs(1).get(0).getSong() == model.getMusicBoxSongPlayer().getMusicBoxSong().getSong()) {
                model.getPlayList().next();
            }
            if (model.getControlGUI() != null) {
                model.getControlGUI().refresh();
            }
            wrapper.setBarTitle(Lang.CURRENT_PLAYNING.toString("{song}", model.getCurrentSong().getName()));
        }
        double progress = (double) current / (double) all;
        if (progress >= 0 && progress <= 1) {
            wrapper.setBarProgress(progress);
        }
    }

    public void destroy() {
        wrapper.nullActivePlayer(getModel().getMusicBoxSongPlayer());
    }
}
