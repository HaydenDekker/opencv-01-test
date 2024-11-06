package com.hdekker.opencv_01_test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import nu.pattern.OpenCV;

public class ImageReaderTest {
	
	String imageURL = "/test/images/test.png";
	ImageReader r = new ImageReader();
	Integer waitTimeMillis = 500;
	
	@BeforeAll
	public static void initOpenCV(){
		OpenCVUtil.loadLocally();
	}
	
	@Test
	public void givenImage_ExpectCanOpen() throws Exception {
		
		Mat image = r.read(imageURL);
		assertThat(image)
				.isNotNull();
		
	}
	
	@Test
	public void givenNonExistingImage_ExpectDetectFailure() {
		
		assertThrows(Exception.class, ()->{
			r.read("fakeurl");
		});
		
	}
	
	@Test
	public void givenImage_ExpectCanDisplay() throws Exception {
		
		Mat image = r.read(imageURL);
		HighGui.imshow("sw", image);
		HighGui.waitKey(waitTimeMillis);
		/// mmmmm how to assert.
	
	}

}

