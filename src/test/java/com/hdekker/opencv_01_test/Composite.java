package com.hdekker.opencv_01_test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdekker.opencv_01_test.TestImages.Images;
import com.hdekker.opencv_01_test.convert.HSVConverter;
import com.hdekker.opencv_01_test.gabor.GaborFilter;
import com.hdekker.opencv_01_test.gabor.GaborFilterTest;
import com.hdekker.opencv_01_test.gabor.GaborFilter.Result;

public class Composite extends OpenCVTest{
	
	Logger log = LoggerFactory.getLogger(Composite.class);
	
	@Test
	public void usingGaborContourThenHough_ExpectRedCircleAsHighConfidence() {
		
		Arrays.stream(Images.values())
			.forEach(i->{
				
				Mat img = TestImages.openImage(i);
				Mat conv = HSVConverter.convert(img);
				Mat oneChannel = new Mat();
				Core.extractChannel(conv, oneChannel, 0);
				Imgproc.GaussianBlur(oneChannel, oneChannel, new Size(13, 13), 0);
				GaborFilter gf = new GaborFilter();
				List<Result> res = gf.apply(oneChannel, "red-bk");
				
				List<MatOfPoint> pointsl = res.stream()
					.flatMap(filter->{
					
					List<MatOfPoint> points = new ArrayList<MatOfPoint>();
					Imgproc.findContours(filter.image(), points, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_TC89_KCOS);
					
					assertThat(points.size())
						.isGreaterThan(0);
					
					log.info("" + points.size() + " contours found.");
					
					return points.stream();
					
				}).toList();
				
				Mat contours = new Mat(img.size(), img.type());
				Imgproc.drawContours(contours, pointsl, -1, new Scalar(255,255,255), -1);
				ImageWriter.writeImage(contours, GaborFilterTest.outputDirectory + i.getFileName() + "combined-contour.png");
				
				Mat gs = GrayscaleImage.convert(contours);

				HoughCircleFinder hcf = new HoughCircleFinder(gs);
				Mat circles = hcf.getResult();
				log.info("" + circles.cols()+ " circles found.");
				
				HoughCircleFinder.addCirclesToMat(img, circles, 10);
				ImageWriter.writeImage(img, GaborFilterTest.outputDirectory + i.getFileName() + "gabor-contour-hough-circle.png");
				
			});
		
		
	}

}
