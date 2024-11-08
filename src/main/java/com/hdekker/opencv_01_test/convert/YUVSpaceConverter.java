package com.hdekker.opencv_01_test.convert;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class YUVSpaceConverter {

	public static Mat convert(Mat image) {
		Mat newMat = new Mat();
		Imgproc.cvtColor(image, newMat, Imgproc.COLOR_RGB2YUV);
		return newMat;
	}

}
