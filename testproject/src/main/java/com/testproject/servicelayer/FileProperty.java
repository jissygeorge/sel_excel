package testproject;

public class FileProperty {
	String filepath;
	String filename;
	String fileext;
	long filesize;
	String fileMimeType;
	
	public FileProperty (String p, String f, String e, long s, String m) {
		this.filepath = p;
		this.filename = f;
		this.fileext = e;
		this.filesize = s;
		this.fileMimeType = m;
	}
	
	public String toString() {
		return "FilePath : "+filepath+"FileName : " + filename + ", File Extension : " + fileext + ", File Size : " + ((Long)(filesize)).toString() + ", File MIME : " + fileMimeType;
	}
}

