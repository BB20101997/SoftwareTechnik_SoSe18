import java.util.LinkedList;
import java.util.List;

public class WordReader implements IReader {

	private IReader decoratedReader;

	public String readNextWord() {
		List<Byte> byteList = new LinkedList<>();
		while(decoratedReader.isNextByteAvailable()) {
			byteList.add(decoratedReader.readNextByte());
		}
		byte[] wordBytes = new byte[byteList.size()];
		for(int i = 0;i<wordBytes.length;i++) {
			wordBytes[i] = byteList.get(i);
		}
		return new String(wordBytes);
	}

	public WordReader(IReader decorated) {
		this.decoratedReader = decorated;
	}

	@Override
	public void open() {
		decoratedReader.open();
	}

	@Override
	public void close() {
		decoratedReader.close();
	}

	@Override
	public byte readNextByte() {
		return decoratedReader.readNextByte();
	}

	@Override
	public boolean isNextByteAvailable() {
		return decoratedReader.isNextByteAvailable();
	}

}