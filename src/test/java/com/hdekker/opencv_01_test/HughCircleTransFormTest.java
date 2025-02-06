package com.hdekker.opencv_01_test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import java.io.File;

import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;

public class HughCircleTransFormTest extends OpenCVTest{
	
	String testImage = "speaker-cone.jpg"; //"circle-line.png"; //  // circle-line.png
	String testImageDir = "src/test/resources/";
	Integer minNumberOfciclesFound = 1;

	ImageReader im = new ImageReader();
	
	private Mat openGrayscaleImage(String filename) {
		
		String absFilePath = getAbsoluteFilePath(filename);
		Mat image;
		try {
			image = im.read(absFilePath);
			Mat newMat = GrayscaleImage.convert(image);
			return newMat;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void writeImageWithCirclesToDirectory(String fileName, Mat image, Mat circles) {
		
		HoughCircleFinder.addCirclesToMat(image, circles, circles.cols());
		ImageWriter.writeImage(image, testImageDir + fileName);

	}
	
	private String getAbsoluteFilePath(String imageName) {
		File testImageFile = new File(testImageDir + imageName);
		String absFilePath = testImageFile.getAbsolutePath();
		return absFilePath;
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
	
	String circleWithBackground = TestImages.Images.RED_CIRCLE_GRASS_BACKGROUND.getFileName();
	
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
