package lqw.test.test_jmeter.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @ClassName 类名：IOUtils
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2015-5-7
 * @author 创建人： songdl
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2015-5-7 songdl 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

public class IOUtils {

	// 根据byte数组，生成文件
	public static void getFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static void writeToFile(byte[] data, File file) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(data);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	public static byte[] readFile(String file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] buff = new byte[fis.available()];
			fis.read(buff);
			fis.close();
			return buff;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static byte[] readClasspathFile(String file) {
		InputStream fis = null;
		try {
			fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
			if (fis == null) {
				return null;
			}
			byte[] buff = new byte[fis.available()];
			fis.read(buff);
			fis.close();
			return buff;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeSteamQuietly(fis);
		}
	}

	public static void closeSteamQuietly(InputStream inputStream, OutputStream outputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * <p> 函数名称： closeSteamQuietly </p> <p> 功能说明：关闭流 </p> @date
	 * 创建时间：2016年7月28日 @author 作者：huangzq <p>参数说明：</p> @param closeables
	 * TODO @throws
	 */
	public static void closeSteamQuietly(Closeable... closeables) {
		try {
			for (Closeable c : closeables) {
				if (c != null) {
					c.close();
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static byte[] readToByte(InputStream is) {
		try {
			return readToByteByStream(is);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static byte[] readToByteByStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int len;
		while ((len = is.read(buff)) > 0) {
			baos.write(buff, 0, len);
		}
		baos.close();
		return baos.toByteArray();
	}

}
