package src.root.ext;

import org.newdawn.slick.Music;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SoundSystem {
	
	private static String[] fileName;
	private static Audio[] ogg;
	private static Music[] wav;
	private static byte soundFiles;
	
	private static SoundSystem ss = new SoundSystem();

	public static SoundSystem getSoundSystem(){
		return ss;
	}
	
	public void init(String...filenames){
		fileName = filenames;

		int oggFiles = 0, wavFiles = 0;
				
		int j = (filenames).length;
		
		for (int i = 0; i < j; i++) {
			String s = filenames[i];
			if (s.contains("ogg")) {
				oggFiles++;
			} else if (s.contains("wav")) {
				wavFiles++;
			}
		}
		
		ogg = new Audio[oggFiles];
		wav = new Music[wavFiles];
		soundFiles = (byte) (fileName.length - 1);
	}

	public void loadSound() {
		byte oggIteration = 0;
		byte wavIteration = 0;
		try {
			for (int a = 0; a <= soundFiles; a++) {
				if (fileName[a].contains("ogg")) {
					ogg[(oggIteration++)] = AudioLoader.getAudio("OGG",
							ResourceLoader.getResourceAsStream(fileName[a]));
				} else if (fileName[a].contains("wav")) {
					wav[(wavIteration++)] = new Music(fileName[a]);
				} else {
					throw new Exception("Audio file is not of correct format");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void play(String name) {
		if (name.contains("ogg")) {
			ogg[getID(name)].playAsMusic(1.0F, 1.0F, false);
		} else if (name.contains("wav")) {
			wav[getID(name)].play();
		}
	}

	private static int getID(String name) {
		byte oggID = 0;
		byte wavID = 0;
		int j = fileName.length;
		for (int i = 0; i < j; i++) {
			String s = fileName[i];
			if (s.contains("wav")) {
				if (s.contains(name)) {
					return wavID;
				}
				wavID = (byte) (wavID + 1);
			} else if (s.contains("ogg")) {
				if (s.contains(name)) {
					return oggID;
				}
				oggID = (byte) (oggID + 1);
			}
		}
		return -1;
	}
}
