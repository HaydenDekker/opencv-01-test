package com.hdekker.opencv_01_test.histogram;

import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

import com.hdekker.opencv_01_test.GrayscaleImage;

public class Histogram {
	
	final Mat hist;
	
	public Histogram(Mat grayImage) {
		
		// Calculate the histogram
        hist = new Mat();
        MatOfInt histSize = new MatOfInt(256);
        float[] range = {0, 256};
        MatOfFloat ranges = new MatOfFloat(range);
        Imgproc.calcHist(List.of(grayImage), new MatOfInt(0), new Mat(), hist, histSize, ranges);

        // Normalize the histogram
        // Imgproc.normalize(hist, hist, 0, histSize, Core.NORM_MINMAX, -1, new Mat());
	
	}

	public Mat getHist() {
		return hist;
	}
	
	

}
