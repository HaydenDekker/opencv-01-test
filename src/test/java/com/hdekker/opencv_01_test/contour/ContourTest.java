package com.hdekker.opencv_01_test.contour;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdekker.opencv_01_test.ImageWriter;
import com.hdekker.opencv_01_test.OpenCVTest;
import com.hdekker.opencv_01_test.TestImages;
import com.hdekker.opencv_01_test.TestImages.Images;
import com.hdekker.opencv_01_test.convert.HSVConverter;
import com.hdekker.opencv_01_test.gabor.GaborFilter;
import com.hdekker.opencv_01_test.gabor.GaborFilter.Result;
import com.hdekker.opencv_01_test.gabor.GaborFilterTest;

public class ContourTest extends OpenCVTest{
	
	Logger log = LoggerFactory.getLogger(ContourTest.class);

	@Test
	public void canCreateContourImage() {
		
		Mat img = TestImages.openImage(Images.RED_CIRCLE_GRASS_BACKGROUND);
		Mat conv = HSVConverter.convert(img);
		Mat oneChannel = new Mat();
		Core.extractChannel(conv, oneChannel, 0);
		GaborFilter gf = new GaborFilter();
		List<Result> res = gf.apply(oneChannel, "red-bk");

		HighGui.imshow("input", res.get(0).image());
		HighGui.waitKey();
		
		res.forEach(filter->{
			
			List<MatOfPoint> points = new ArrayList<MatOfPoint>();
			Imgproc.findContours(filter.image(), points, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_TC89_KCOS);
			
			assertThat(points.size())
				.isGreaterThan(0);
			
			log.info("" + points.size() + " contours found.");
			
			Imgproc.drawContours(img, points, -1, new Scalar(0,0,255));
			ImageWriter.writeImage(img, GaborFilterTest.outputDirectory + filter.kernel().getTheta() + "contour.png");
			
		});
		
		
	}
	
}
