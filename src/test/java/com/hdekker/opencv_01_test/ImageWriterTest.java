package com.hdekker.opencv_01_test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ImageWriterTest {

	@Test
	public void canWriteImage() throws IOException {
		
		OpenCVUtil.loadLocally();
		String testImageFilePath = "/test/images/blankimg.png";
		int[] sizes = {10,10};
		Mat img = Mat.ones(sizes, CvType.CV_8UC1);
		ImageWriter.writeImage(img, testImageFilePath);
		File image = new File(testImageFilePath);
		assertThat(image.exists())
			.isTrue();
		Files.delete(Paths.get(image.getPath()));
		
	}
	
}
