package com.hdekker.opencv_01_test.gabor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hdekker.opencv_01_test.ImageWriter;
import com.hdekker.opencv_01_test.OpenCVTest;
import com.hdekker.opencv_01_test.TestImages;
import com.hdekker.opencv_01_test.TestImages.Images;
import com.hdekker.opencv_01_test.gabor.GaborFilter.Result;

public class GaborFilterTest extends OpenCVTest{
	
	static GaborFilter gf;
	static List<Result> results;
	
	@BeforeAll
	public static void setup() {
		
		Logger log = LoggerFactory.getLogger(GaborFilterTest.class);
		
		gf = new GaborFilter();
		log.info("" + gf.getFilters().size() + " filters created.");
		
		results = Arrays.stream(Images.values())
			.flatMap(img->{
				Mat image = TestImages.openImage(img);
				return gf.apply(image, img.getFileName()).stream();
			})
			.toList();

		log.info("" + results.size() + " results returned.");

	}
	
	@Test
	public void canCreateGaborFilterBank() {

		assertThat(gf.getFilters())
			.hasSizeGreaterThan(1);
		
	}
	
	@Test
	public void givenImageExpectGaborFiltersApplied() {
		
		assertThat(results.size())
			.isGreaterThan(1);
		
	}
	
	public static final String outputDirectory = "/test/output/";
	
	@Test
	public void givenImagesExpectWrittenToFileSystem() {
		
		results.forEach(mat->{
			
			ImageWriter.writeImage(mat.image(), outputDirectory + "GaborFilter_" + mat.kernel().getTheta()+ mat.filename());
			
		});
		
	}
	
	

}
