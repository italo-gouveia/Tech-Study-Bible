package structural.adapter;

// Adapter class
public class VLCPlayerAdapter implements MediaPlayer {
    private VLCPlayer vlcPlayer = new VLCPlayer();

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVLC(fileName);
        }
    }
}
