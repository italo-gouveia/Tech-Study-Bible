package structural.adapter;

public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new VLCPlayerAdapter();
        player.play("vlc", "sample.vlc");
    }
}
