package com.hdekker.opencv_01_test;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
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
	
	public static void addCirclesToMat(Mat image, Mat circles, Integer numberToDraw) {
		// Process the detected circles
		for (int i = 0; i < numberToDraw; i++) {
		    double[] circle = circles.get(0, i);
		    int x = (int) Math.round(circle[0]);
		    int y = (int) Math.round(circle[1]);
		    int r = (int) Math.round(circle[2]);

		    Imgproc.circle(image, new Point(x, y), r, new Scalar(255, 255, 255), 2);
		    Imgproc.putText(image, "" + i,  new Point(x, y), 1, 10,  new Scalar(0, 0, 0));
			
		}
		
	}

}
