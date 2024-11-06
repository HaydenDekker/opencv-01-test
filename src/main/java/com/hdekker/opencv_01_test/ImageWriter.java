package com.hdekker.opencv_01_test;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageWriter {
	
	public static void writeImage(Mat image, String imageName) {
		Imgcodecs.imwrite(imageName, image);
	}

}
