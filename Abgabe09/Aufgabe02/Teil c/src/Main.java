import java.io.File;

public class Main {

	public static void main(String[] args) {
		WordReader reader = new WordReader(new FileReader(new File("src/testfile_")));
		reader.open();
		if(reader.isNextByteAvailable()) {
			System.out.print(reader.readNextWord());
		}
		while(reader.isNextByteAvailable()) {
			System.out.print(" "+reader.readNextWord());
		}
		reader.close();
	}

}
