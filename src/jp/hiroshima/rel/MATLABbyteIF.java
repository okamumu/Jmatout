package jp.hiroshima.rel;
import java.io.DataOutputStream;
import java.io.IOException;

public interface MATLABbyteIF {

	long getByteNum();
	void write(DataOutputStream dos) throws IOException;

}
