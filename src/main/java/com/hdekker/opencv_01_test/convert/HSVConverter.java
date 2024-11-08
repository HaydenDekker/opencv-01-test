package com.hdekker.opencv_01_test.convert;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class HSVConverter {
	
	public static Mat convert(Mat image) {
		Mat mat = new Mat();
		Imgproc.cvtColor(image, mat, Imgproc.COLOR_RGB2HSV);
		return mat;
	}

}
