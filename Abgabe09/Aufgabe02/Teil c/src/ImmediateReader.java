import java.util.LinkedList;
import java.util.List;

public class ImmediateReader implements IReader {

	private byte[] allBytes;
	private int nextByteIndex = -1;

	/**
	 * 
	 * @param decoratedReader
	 */
	public ImmediateReader(IReader decoratedReader) {
		decoratedReader.open();
		List<Byte> allByteList = new LinkedList<>();
		while(decoratedReader.isNextByteAvailable()) {
			allByteList.add(decoratedReader.readNextByte());
		}
		allBytes = new byte[allByteList.size()];
		for(int i = 0;i<allByteList.size();i++) {
			allBytes[i] = allByteList.get(i);
		}
	}

	@Override
	public void open() {
		if(nextByteIndex==-1) {
			nextByteIndex = 0;
		}
	}

	@Override
	public void close() {
		allBytes = null;
	}

	@Override
	public byte readNextByte() {
		if(nextByteIndex>0&&allBytes!=null&&nextByteIndex<allBytes.length) {
			return allBytes[nextByteIndex++];
		}else {
			throw new IllegalStateException("Reader never opend, already closed or out of Bytes!");
		}
		
	}

	@Override
	public boolean isNextByteAvailable() {
		return allBytes!=null&&nextByteIndex>0&&nextByteIndex<allBytes.length;
	}

}