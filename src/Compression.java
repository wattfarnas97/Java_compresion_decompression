
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compression {

	public static void main(String[] args) {
		
		
		File directoryToZip = new File("pathname");
        ArrayList<File> fileList = new ArrayList<File>();
		try {
			System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getAllFiles(fileList, directoryToZip);
		 System.out.println(fileList.size());
		System.out.println("---Creating zip file");
		writeZipFile(directoryToZip, fileList);
		System.out.println("---Done");
	
	
	}
			
	

		

	
	
	public  static void  getAllFiles(ArrayList<File> sourceContains,File source)
	{
		
	
		
		File [] files=source.listFiles();
		for (File file : files) {
			sourceContains.add(file);
			if(file.isDirectory())
			{
				getAllFiles(sourceContains, file);
			}
		}
	}


public static void writeZipFile(File diresctoryToZip,ArrayList<File> fileList) 
{
	try {
		FileOutputStream fos= new FileOutputStream(diresctoryToZip.getAbsolutePath()+".zip");
		ZipOutputStream zos= new ZipOutputStream(fos);
		for (File file : fileList) {
			if(file.isFile())
				addzip(diresctoryToZip,file,zos);
			
		}
	zos.close();
	fos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}







private static void addzip(File diresctoryToZip, File file, ZipOutputStream zos) {
	// TODO Auto-generated method stub
	try {
		FileInputStream fis = new FileInputStream(file);
		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(diresctoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}