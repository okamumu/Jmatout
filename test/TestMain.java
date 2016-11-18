import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.hiroshima.rel.MATLABDoubleMatrix;
import jp.hiroshima.rel.MATLABDoubleSparseMatrix;
import jp.hiroshima.rel.MATLABHeader;

public class TestMain {

	public static void main(String[] args) {
		File file = new File("test.mat");
		try {
			MATLABDoubleMatrix da = new MATLABDoubleMatrix("A", new int[] {2,5}, new double[] {1,2,3,4,5,6,7,8,9,10});
			MATLABDoubleSparseMatrix sa = new MATLABDoubleSparseMatrix("B", new int[] {3,3}, 3, new int[] {0,1,2}, new int[] {0,1,2,3}, new double[] {1,2,3});

			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			MATLABHeader header = new MATLABHeader();
			header.write(dos);
			da.write(dos);
			sa.write(dos);
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
