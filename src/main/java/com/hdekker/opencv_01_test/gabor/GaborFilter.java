package com.hdekker.opencv_01_test.gabor;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class GaborFilter {
	
	final List<Kernel> filters;
	
	public static class Kernel{
		final Mat kernel;
		final Integer kSize;
		final Double sigma;
		final Double theta;
		final Double lambda;
		final Double gamma;
		final Double psi;
		
		public Kernel(Integer kSize, Double sigma, Double theta, Double lambda, Double gamma,
				Double psi) {
			super();
			this.kSize = kSize;
			this.sigma = sigma;
			this.theta = theta;
			this.lambda = lambda;
			this.gamma = gamma;
			this.psi = psi;
			
			kernel = Imgproc.getGaborKernel(new Size(31, 31), 5.0, theta, 10.0, 0.5, 0.0, CvType.CV_32F);
			
		}

		public Mat getKernel() {
			return kernel;
		}

		public Integer getkSize() {
			return kSize;
		}

		public Double getSigma() {
			return sigma;
		}

		public Double getTheta() {
			return theta;
		}

		public Double getLambda() {
			return lambda;
		}

		public Double getGamma() {
			return gamma;
		}

		public Double getPsi() {
			return psi;
		}

		
	
		
	} 

	public List<Kernel> getFilters() {
		return filters;
	}

	public GaborFilter() {
		
		filters = new ArrayList<>();
		for (double theta = 0; theta < Math.PI; theta += Math.PI / 4) {
		    Kernel kernel = new Kernel(31, 5.0, theta, 10.0, 0.5, 0.0);
		    filters.add(kernel);
		}
		
	}
	
	public static record Result(
		String filename,
		Kernel kernel,
		Mat image) {}
	
	public List<Result> apply(Mat img, String filename){
		
		List<Result> filteredImages = new ArrayList<>();
		for (Kernel kernel : filters) {
		    Mat filteredImg = new Mat();
		    Imgproc.filter2D(img, filteredImg, -1, kernel.getKernel());
		    filteredImages.add(new Result(filename, kernel, filteredImg));
		}
		return filteredImages;
	}

}
