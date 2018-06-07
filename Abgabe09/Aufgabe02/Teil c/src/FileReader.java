import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader implements IReader {

	private FileInputStream fileInputStream;
	private File file;

	public FileReader(File file) {
		this.file = file;
	}

	@Override
	public void open() {
		if (file != null && fileInputStream == null) {
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				new RuntimeException(e);
			}
		}
	}

	@Override
	public void close() {
		if (fileInputStream != null) {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				new RuntimeException(e);
			}
			fileInputStream = null;
		}
		file = null;
	}

	@Override
	public byte readNextByte() {
		if (fileInputStream != null) {
			try {
				return  (byte)fileInputStream.read();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else {
			throw new IllegalStateException("Reader never opend, already closed!");
		}
	}

	@Override
	public boolean isNextByteAvailable() {
		try {
			return fileInputStream!=null&&fileInputStream.available()>0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}