package com.hdekker.opencv_01_test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.Test;

public class AbsoluteFilePathProvider {
	
	public static String relativePath = "src/test/resources/speaker-cone.jpg";
	
	@Test
	public void canGetAbsoluteFilePathForRelativePath() {
		
		File f = new File(relativePath);
		String absPath = f.getAbsolutePath();
		assertThat(relativePath)
			.isNotEqualTo(absPath);
		System.out.println(absPath);
		String[] slashes = splitWindowsOrLinuxPath(absPath);
		assertThat(slashes.length)
			.isGreaterThan(4);
		assertThat(f.exists())
			.isTrue();
		
	}
	
	String[] splitWindowsOrLinuxPath(String path) {
		return path.split("[/\\\\]");
	}

}
