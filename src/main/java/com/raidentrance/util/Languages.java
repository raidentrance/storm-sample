/**
 * 
 */
package com.raidentrance.util;

/**
 * @author alex @raidentrance
 *
 */
public enum Languages {
	JAVA("java", "java.txt"), JAVASCRIPT("javascript", "javascript.txt"), PHP("php", "php.txt"), PYTHON("python",
			"python.txt");

	private String name;
	private String fileName;

	private Languages(String name, String fileName) {
		this.name = name;
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public String getFileName() {
		return fileName;
	}

}
