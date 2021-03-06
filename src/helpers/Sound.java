package helpers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Nilas Karlsson
 * play Sound-methods 
 *
 */
public class Sound {
	
	public final static String SET_IN_SCORE_SOUND = "row//setInScore.wav";
	public final static String SHAKE_AND_ROLL_DICE = "row//ShakeAndRollDice.wav";
	public final static String TAKE_DICE_LONG = "row//takeDiceLong.wav";
	public final static String TAKE_DICE = "row//takeDice1.wav";
	public final static String LEAVE_DICE = "row//Reverse.wav"; 
	public final static String WINNER = "row//winner.wav";
	public final static String GETBONUS = "row//getBonus.wav";
	public final static String NO = "row//no.mp3";
	public final static String GAME_START = "row//loading.wav";
	
	final String BORDER_IMG = "row//Green.png";
	
	private static final int BUFFER_SIZE = 4096;
	
	public static void playSound_advance(String audioFilePath) {
        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
 
            audioLine.open(format);
 
            audioLine.start();
             
            System.out.println("Playback started.");
             
            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
 
            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }
             
            audioLine.drain();
            audioLine.close();
            audioStream.close();
             
            System.out.println("Playback completed.");
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }      
    }
	
	public static void playSound(String audioFilePath)
    {
        try 
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception e)
        {
            System.out.println("Error with playing sound.");
        }
    }

	/**
	 * this start a thread with the backgroundMusic
	 */
	public static void BGM() {
		
				new Thread(new Runnable() {
					public void run() {
						try 
						{	
							do
					            {
								
							FileInputStream fis = new FileInputStream(new File("row//Tyrian.mp3"));
							BufferedInputStream bis = new BufferedInputStream(fis);
							javazoom.jl.player.Player intro = new javazoom.jl.player.Player(bis);	
//							do
//				            {
								intro.play();					
				            }while(true);
						}
						catch(Exception e)
						{
							System.out.println("Error with playing sound.");
						}
					}
				}).start();
		
	}
	
	/**
	 * @param path
	 * this play a mp3 file (string path)
	 */
	public static void playMp3SoundInThread(String path) {
		// ___________
		new Thread(new Runnable() {
			public void run() {

				try {
					FileInputStream fis = new FileInputStream(new File(path));
					BufferedInputStream bis = new BufferedInputStream(fis);
					javazoom.jl.player.Player intro = new javazoom.jl.player.Player(bis);	
					intro.play();					
				} catch (Exception e) {
					System.out
							.println("Error with playing sound.");
				}
			}
		}).start();
		// ___________
	}
	
	

//	public static void startGameSound() {
//		new Thread(new Runnable() {
//			public void run() {
//				try 
//				{				
//					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("row//loading.wav"));
//					Clip clip = AudioSystem.getClip( );
//					clip.open(audioInputStream);
//					clip.start( );				
//				}
//				catch(Exception e)
//				{
//					System.out.println("Error with playing sound.");
//				}
//			}
//		}).start();
//	}

	
	public static void playSoundInThread(String path) {
		// ___________
		new Thread(new Runnable() {
			public void run() {

				try {
					Sound.playSound(path);
				} catch (Exception e) {
					System.out
							.println("Error with playing sound.");
				}
			}
		}).start();
		// ___________
	}

}
