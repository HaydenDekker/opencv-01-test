package com.hdekker.opencv_01_test;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageReader {
	
	public Mat read(String path) throws Exception {
		Mat mat = Imgcodecs.imread(path);
		if(mat.empty()) {
			throw new Exception("failed.");
		}
		return mat;
	}

}
