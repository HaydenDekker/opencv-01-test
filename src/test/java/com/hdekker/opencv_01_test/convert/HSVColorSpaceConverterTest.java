package com.hdekker.opencv_01_test.convert;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import com.hdekker.opencv_01_test.ImageWriter;
import com.hdekker.opencv_01_test.OpenCVTest;
import com.hdekker.opencv_01_test.TestImages;
import com.hdekker.opencv_01_test.TestImages.Images;
import com.hdekker.opencv_01_test.gabor.GaborFilterTest;

public class HSVColorSpaceConverterTest extends OpenCVTest{
	
	static Mat hsv;
	
	@BeforeAll
	public static void setup() {
		Mat image = TestImages.openImage(Images.SPEAKER_CONE);
		hsv = HSVConverter.convert(image);
	}
	
	@Test
	public void canConvertToHSVColor() {
		
		assertThat(hsv.channels())
			.isEqualTo(3);
		
		ImageWriter.writeImage(hsv, GaborFilterTest.outputDirectory + "hsv-test.jpg");
		
		Mat hue = new Mat();
		Mat sat = new Mat();
		Mat val = new Mat();
		
		Core.extractChannel(hsv, hue, 0);
		Core.extractChannel(hsv, sat, 1);
		Core.extractChannel(hsv, val, 2);
		
		ImageWriter.writeImage(hue, GaborFilterTest.outputDirectory + "hsv-test-h.jpg");
		ImageWriter.writeImage(sat, GaborFilterTest.outputDirectory + "hsv-test-s.jpg");
		ImageWriter.writeImage(val, GaborFilterTest.outputDirectory + "hsv-test-v.jpg");
		
		
	}

}
