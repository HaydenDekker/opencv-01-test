package com.hdekker.opencv_01_test;

import java.io.File;
import org.opencv.core.Mat;

public class TestImages {
	
	private static final String TEST_IMAGE_DIR = "src/test/resources/";
	private static final ImageReader im = new ImageReader();
	
	public enum Images{
		
		SPEAKER_CONE("speaker-cone.jpg"),
		RED_CIRCLE_GRASS_BACKGROUND("opencv-test-red-circle-grass-background.png"),
		SINGLE_BALL("single-ball.png"),
		LACROSSE_BALL_GRASS_BACKGROUND("opencv-test-lacrosse-ball-grass.png"),
		ACTION_SOCCER_KICK("action.png");
		
		private final String fileName;
		
		Images(String fileName){
			this.fileName = fileName;
		}

		public String getFileName() {
			return fileName;
		}

	}
	
	public static Mat openImage(Images image) {
		String absFilePath = getAbsoluteFilePath(image.getFileName());
		Mat imageMat;
		try {
			imageMat = im.read(absFilePath);
			return imageMat;
		}catch (Exception e) {
			
		}
		return null;
	}

	
	private static String getAbsoluteFilePath(String imageName) {
		File testImageFile = new File(TEST_IMAGE_DIR + imageName);
		String absFilePath = testImageFile.getAbsolutePath();
		return absFilePath;
	}
	

}
