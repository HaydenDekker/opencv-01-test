package com.hdekker.opencv_01_test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

public class ImageReaderTest extends OpenCVTest{
	
	String imageURL = "/test/images/test.png";
	ImageReader r = new ImageReader();
	Integer waitTimeMillis = 500;
	
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

