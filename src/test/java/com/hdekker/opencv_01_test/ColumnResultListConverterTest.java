package com.hdekker.opencv_01_test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ColumnResultListConverterTest {
	
	@Test
	public void given2dResult_ExpectCanCreateJSONArray() throws JsonProcessingException {
		
		OpenCVUtil.loadLocally();
		
		String expect = "[[20.0,30.0,40.0],[56.0,76.0,2.0]]";
		Mat mat = new Mat(1, 2, CvType.CV_32FC3);
		int[] col = {0,0};
		int[] col2 = {0,1};
		float[] f1 = {20.0f, 30.0f, 40.0f};
		float[] f2 = {56.0f, 76.0f, 2.0f};
		mat.put(col	, f1);
		mat.put(col2, f2);

		List<List<Double>> array = ColumnResultListConverter.convertResultToDoubleArray(mat);
		assertThat(array.size())
			.isEqualTo(2);
		
		assertThat(ColumnResultListConverter.convertToJsonArray(array))
			.isEqualTo(expect);
		
	}

}
