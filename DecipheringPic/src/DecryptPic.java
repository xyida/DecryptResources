import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

public class DecryptPic {
	private static String PASSWORD = "";
	private static final String[] FileExts = { "png", "jpg","PNG","JPG"};
	private static String filePath;

	public DecryptPic(String filePath) {
		super();
		this.filePath = filePath;
	}

	public DecryptPic() {
		super();
	}

	private boolean doDecryption() {
		byte[] fileByte = null;
		try {
			int pswLen = PASSWORD.length();
			byte[] pswData = PASSWORD.getBytes();
			int j = 0;
			fileByte = FileUtils.readFileToByteArray(new File(filePath));
			int fileLen = fileByte.length;
			for (int i = 0; i < fileLen; i++) {
				fileByte[i] ^= pswData[j];
				j++;
				if (j >= pswLen)
					j = 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (fileByte == null)
			return false;
		try {
			File file = new File(filePath);
			FileUtils.writeByteArrayToFile(file, fileByte);

		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}
		return true;

	}

	public static String getCurrDir() {
		String dir = new File(".").getAbsolutePath();
		return dir.substring(0, dir.length() - 1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] filePath) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		// String currDir=new
		// DecryptPic().getClass().getProtectionDomain().getCodeSource()
		// .getLocation().getFile().substring(1);
		// String currDir=System.getProperty("java.class.path");

		String currDir = getCurrDir();
		System.out
				.println("==========================================================================");
		System.out.println("��ǰ����ִ��·����" + currDir);
		System.out
				.println("==========================================================================");
		// try {
		// File file=new File(currDir.toString()+"key.cfg");
		//
		//
		// if(!file.exists()){
		// System.out.print("��ǰ�Ҳ���key�ļ���������key��");
		// Scanner scanner=new Scanner(System.in);
		// FileUtils.writeStringToFile(new File(currDir.toString()+"key.cfg"),
		// scanner.next());
		// }else{
		// String key=FileUtils.readFileToString(new
		// File(currDir.toString()+"key.cfg"));
		// DecryptPic.PASSWORD=key;
		//
		// }
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		if (filePath.length > 0 && filePath.length == 1) {
			DecryptPic.PASSWORD = filePath[0];
			System.out.println("ִ��Ƿȱ����:�����ļ���·��������ק�ļ��е���ݷ�ʽ��");
			System.out.println("���س����˳�...");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (filePath.length >= 2) {
			DecryptPic.PASSWORD = filePath[0];
			// filePath = "D:\\����֧������\\ResCrypto\\Resources";
			String path = filePath[1];
			String fileFormat = path.substring(path.length() - 4);
			if (fileFormat.equals(".jpg") || fileFormat.equals(".png")||fileFormat.equals(".JPG")||fileFormat.equals(".PNG")) {
				DecryptPic dp = new DecryptPic(path);

				if (dp.doDecryption()) {
					System.out.println("����/���� �ɹ���" + path);

				} else {
					System.out.println("����/���� ʧ�ܣ�" + path);
				}
				System.out.print("����(����)���!�����������س����˳�...");

				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Collection<File> fileCollection = FileUtils.listFiles(new File(
						filePath[1]), FileExts, true);
				for (Iterator<File> it = fileCollection.iterator(); it
						.hasNext();) {
					String currFilePath = ((File) it.next()).toString();
					DecryptPic dp = new DecryptPic(currFilePath);

					if (dp.doDecryption()) {
						System.out.println("����/���� �ɹ���" + currFilePath);
					} else {
						System.out.println("����/���� ʧ�ܣ�" + currFilePath);
					}
				}

				System.out.print("����(����)���!�����������س����˳�...");

				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			System.out.println("ִ��Ƿȱ����:��Կ&�����ļ���·�������ڿ�ݷ�ʽ�������Կ����ק�ļ��е���ݷ�ʽ");
			System.out.println("���س����˳�...");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
