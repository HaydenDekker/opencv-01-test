package com.hdekker.opencv_01_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ColumnResultListConverter {
	
	Logger log = LoggerFactory.getLogger(ColumnResultListConverter.class);
	
	public static List<List<Double>> convertResultToDoubleArray(Mat mat){
		
		List<List<Double>> array = new ArrayList<>();
		
		for (int i = 0; i < mat.cols(); i++) {
		    double[] circle = mat.get(0, i);
		    array.add(Arrays.stream(circle)
		    	.boxed()
		    	.toList());
		}
		return array;
	}
	
	public static String convertToJsonArray(List<List<Double>> array) {
		ObjectMapper om = new ObjectMapper();
		String result = "";
		try {
			result = om.writeValueAsString(array);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
