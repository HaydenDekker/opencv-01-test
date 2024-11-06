package com.hdekker.opencv_01_test;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class HoughCircleFinder {
	
	final Mat image;
	
	Mat circles = new Mat();
	
	private final Integer maxRadius = 300;
	private final Integer minRadius = 10;
	private final Integer param1 = 300;
	private final Integer param2 = 100;
	private final Integer minDist = 110;
	private final Double dp = 5.0;
	
	Long time; 
	
	public HoughCircleFinder(Mat image) {
		
		this.image = image;
		start();
		Imgproc.HoughCircles(image, circles, Imgproc.HOUGH_GRADIENT, dp, minDist, param1, param2, minRadius, maxRadius);
		time = stop();
		
	}
	
	public Long getProcessingDuration() {
		return time;
	}
	public Mat getResult() {
		return circles;
	}
	
	private void start(){
		time = System.currentTimeMillis();
	}
	private Long stop() {
		long stopTime = System.currentTimeMillis();
		long dur = (stopTime - time);
		System.out.println("Timer Finished: " + dur);
		return dur;
	}

}
