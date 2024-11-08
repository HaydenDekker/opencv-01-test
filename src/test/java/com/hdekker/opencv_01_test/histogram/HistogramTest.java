package com.hdekker.opencv_01_test.histogram;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdekker.opencv_01_test.GrayscaleImage;
import com.hdekker.opencv_01_test.OpenCVTest;
import com.hdekker.opencv_01_test.TestImages;
import com.hdekker.opencv_01_test.TestImages.Images;

public class HistogramTest extends OpenCVTest{
	
	//private static final int Histogram_Size = 256;
	Logger log = LoggerFactory.getLogger(HistogramTest.class);
	
	@Test
	public void canCreateGrayscaleHistogram() {
		
		Mat image = TestImages.openImage(Images.SINGLE_BALL);
		Histogram h = new Histogram(GrayscaleImage.convert(image));
		
		log.info("histogram has " + h.getHist().cols() + " cols and " + h.getHist().rows() + " rows.");
		
		assertThat(h.getHist().rows())
			.isGreaterThan(1);
		
		IntStream.range(0, h.getHist().rows())
			.forEach(i->{	
				log.info("" + h.getHist().get(i, 0)[0]);
				
		});
		
		Mat histImage = new Mat(256, 256, CvType.CV_8UC3, Scalar.all(0));
		
	}

}
