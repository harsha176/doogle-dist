package edu.ncsu.csc573.project.doogle.test.commlayer;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import edu.ncsu.csc573.project.commlayer.IPublishHandler;

public class DefaultPublishHandler implements IPublishHandler {

	public File getFileToUpload(String fileName) {
		//File publishDirectory = ConfigurationManager.getInstance().getPublishDirectory();
		URL url = ClassLoader.getSystemResource("samplePublishFolder");
		Assert.assertNotNull(url);
		System.out.println(url.getFile());
		File publishDirectory = new File(url.getFile());
		Assert.assertEquals(publishDirectory.isDirectory(), true);
		if(publishDirectory.isDirectory()) {
			FilenameFilter textFilter = new FilenameFilter() {
				
				public boolean accept(File dir, String name) {
					if(name.endsWith(".txt"))
						return true;
					else 
						return false;
				}
			};
			List<File> files = Arrays.asList(publishDirectory.listFiles(textFilter));
			for(File aFile: files) {
				if(aFile.getAbsolutePath().contains(fileName)) {
					return aFile;
				}
			}
		}
		return null;
	}

}
