public interface IReader {

	void open();

	void close();

	byte readNextByte();

	boolean isNextByteAvailable();

}