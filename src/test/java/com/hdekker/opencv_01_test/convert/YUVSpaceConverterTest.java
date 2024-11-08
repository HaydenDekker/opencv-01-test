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

public class YUVSpaceConverterTest extends OpenCVTest{
	
	static Mat image;
	static Mat converted;
	
	@BeforeAll
	public static void setup() {
		image = TestImages.openImage(Images.SPEAKER_CONE);
		converted = YUVSpaceConverter.convert(image);
	}
	
	@Test
	public void canConvertToYUVSpace() {
		
		assertThat(image.channels())
			.isEqualTo(3);
		
		
		assertThat(converted.channels())
			.isEqualTo(3);
		
		assertThat(image.get(0,0)[0])
		.isNotEqualTo(converted.get(0,0)[0]);
		
		assertThat(image.rows())
			.isEqualTo(converted.rows());
		
	}
	
	@Test
	public void canGetLuminance() {
		
		Mat luminance = new Mat();
		Core.extractChannel(converted, luminance, 0);
		assertThat(luminance.channels())
			.isEqualTo(1);
		ImageWriter.writeImage(converted, GaborFilterTest.outputDirectory + "luminance-test-base.jpg");
		ImageWriter.writeImage(luminance, GaborFilterTest.outputDirectory + "luminance-test.jpg");
	
	}

}
