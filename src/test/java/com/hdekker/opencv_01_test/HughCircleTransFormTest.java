package com.hdekker.opencv_01_test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class HughCircleTransFormTest {
	
	String testImage = "speaker-cone.jpg"; //"circle-line.png"; //  // circle-line.png
	String testImageDir = "src/test/resources/";
	Integer minNumberOfciclesFound = 1;

	ImageReader im = new ImageReader();
	
	@BeforeAll
	public static void initOpenCV(){
		OpenCVUtil.loadLocally();
	}
	
	private Mat openGrayscaleImage(String filename) {
		
		String absFilePath = getAbsoluteFilePath(filename);
		Mat image;
		try {
			image = im.read(absFilePath);
			Mat newMat = convertToGrayscale(image);
			return newMat;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void writeImageWithCirclesToDirectory(String fileName, Mat image, Mat circles) {
		
		// Process the detected circles
		for (int i = 0; i < circles.cols(); i++) {
		    double[] circle = circles.get(0, i);
		    int x = (int) Math.round(circle[0]);
		    int y = (int) Math.round(circle[1]);
		    int r = (int) Math.round(circle[2]);

		    Imgproc.circle(image, new Point(x, y), r, new Scalar(0, 0, 255), 2);
		    Imgproc.putText(image, "" + i,  new Point(x, y), 1, 10,  new Scalar(0, 0, 255));
			
		}
		
		ImageWriter.writeImage(image, testImageDir + fileName);

	}
	
	private String getAbsoluteFilePath(String imageName) {
		File testImageFile = new File(testImageDir + imageName);
		String absFilePath = testImageFile.getAbsolutePath();
		return absFilePath;
	}
	
	private Mat convertToGrayscale(Mat image) {
		Mat newMat = new Mat();
		Imgproc.cvtColor(image, newMat, Imgproc.COLOR_RGB2GRAY);
		return newMat;
	}
	
	@Test
	public void givenImageExpect_CirclesFound() throws Exception {
		
		Mat newMat = openGrayscaleImage(testImage);
		HoughCircleFinder finder = new HoughCircleFinder(newMat);
		
		Mat result = finder.getResult();
		Long time = finder.getProcessingDuration();
		
		Long maxProcessingTime = 5000l;
		assertThat(time)
			.isLessThan(maxProcessingTime);
		
		assertThat(result.cols())
			.isGreaterThan(0);
		
		System.out.println("Number of circles detected: " + result.cols());
		
		writeImageWithCirclesToDirectory(testImage + "test.png", newMat, result);
		
	}
	
	String signleRedCircleTestFile = "opencv-test-red-circle.png";
	
	@Test
	public void givenSingleRedCircle_ExpectOnly1CircleFound() throws Exception {
		
		Mat newMat = openGrayscaleImage(signleRedCircleTestFile);
		HoughCircleFinder finder = new HoughCircleFinder(newMat);
		Mat result = finder.getResult();
		assertThat(result.cols())
			.isEqualTo(1);
		double[] coord = result.get(0,0);
		assertThat(coord[0])
			.isCloseTo(860.0, offset(20.0));
		
	}
	
	String circleWithBackground = "opencv-test-red-circle-grass-background.png";
	
	/***
	 * HoughCircles doesn't work well with noise..
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenCircleWithBackground_ExpectManyFalsePositivesFound() throws Exception {
		
		Mat newMat = openGrayscaleImage(circleWithBackground);
		HoughCircleFinder finder = new HoughCircleFinder(newMat);
		Mat result = finder.getResult();

		double[] coord = result.get(0,0);
		assertThat(coord[0])
			.isNotCloseTo(860.0, offset(20.0));
		assertThat(result.cols())
			.isGreaterThan(1);
		
		writeImageWithCirclesToDirectory(circleWithBackground + "test.png", newMat, result);
		
		
	}
	

}
