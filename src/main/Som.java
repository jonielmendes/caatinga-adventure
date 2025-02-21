package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Som {
    Clip clip;
    URL somURL[] = new URL[30];

    public Som(){
        somURL[0] = getClass().getResource("/som/Adventure.wav");
        somURL[1] = getClass().getResource("/som/coin.wav");
        somURL[2] = getClass().getResource("/som/raposa2.wav");
        somURL[3] = getClass().getResource("/som/somtatu.wav");
        somURL[4] = getClass().getResource("/som/onca2.wav");
        somURL[5] = getClass().getResource("/som/fanfare.wav");

    }
    public void setFile(int i){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(somURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void play(){

        clip.start();

    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop(){
        clip.stop();

    }

}
