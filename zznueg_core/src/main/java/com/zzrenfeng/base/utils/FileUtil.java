package com.zzrenfeng.base.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 * author zhoujincheng
 * create 2016/4/2 18:47
 */
public class FileUtil extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static final int BUFFER = 1024;
    
    /**
     * @功能描述：判断给定的文件全路径（包含文件名）代表的文件是否存在且是标准文件（不是目录）
     * 			说明：这里判断的比较粗略，没有具体分给定的文件名具体是何对象，并返回不同的内容，可以扩展优化，这里需求不定，不再展开；
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月11日 下午4:50:05
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param strSourceFileName 指定的文件全路径名
     * @return 返回值：true-文件存在；false-文件不存在；
     */
    public static boolean isExists(String strSourceFileName) throws Exception {
    	File fileSource = new File(strSourceFileName);
    	// 如果源文件不存或源文件是文件夹
        if (!fileSource.exists() || !fileSource.isFile()) {
            logger.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
            return false;
        }
        return true;
    }

    /**
     * 拷贝文件
     * strSourceFileName 指定的文件全路径名
     * strDestDir 拷贝到指定的文件夹
     * return 如果成功true;否则false
     */
    public static boolean copyTo(String strSourceFileName, String strDestDir) {
        File fileSource = new File(strSourceFileName);
        File fileDest = new File(strDestDir);

        // 如果源文件不存或源文件是文件夹
        if (!fileSource.exists() || !fileSource.isFile()) {
            logger.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
            return false;
        }

        // 如果目标文件夹不存在
        if (!fileDest.isDirectory() || !fileDest.exists()) {
            if (!fileDest.mkdirs()) {
                logger.debug("目录文件夹不存，在创建目标文件夹时失败!");
                return false;
            }
        }

        try {
            String strAbsFilename = strDestDir + File.separator + fileSource.getName();

            FileInputStream fileInput = new FileInputStream(strSourceFileName);
            FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

            logger.debug("开始拷贝文件:");

            int count;

            long nWriteSize = 0;
            long nFileSize = fileSource.length();

            byte[] data = new byte[BUFFER];

            while (-1 != (count = fileInput.read(data, 0, BUFFER))) {

                fileOutput.write(data, 0, count);

                nWriteSize += count;

                long size = (nWriteSize * 100) / nFileSize;
                long t = nWriteSize;

                String msg;

                if (size <= 100 && size >= 0) {
                    msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
                    //logger.debug(msg);
                } else if (size > 100) {
                    msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
                    //logger.debug(msg);
                }

            }

            fileInput.close();
            fileOutput.close();

            logger.debug("拷贝文件成功!");
            return true;

        } catch (Exception e) {
            logger.debug("异常信息：[");
            logger.error("拷贝文件出错！", e);
            logger.debug("]");
            return false;
        }
    }

    /**
     * 删除指定的文件
     * strFileName 指定绝对路径的文件名
     * 如果删除成功true否则false
     */
    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);

        if (!fileDelete.exists() || !fileDelete.isFile()) {
            logger.debug("错误: " + strFileName + "不存在!");
            return false;
        }

        return fileDelete.delete();
    }

    /**
     * 移动文件
     * <p>
     * strSourceFileName 是指定的文件全路径名
     * strDestDir        移动到指定的文件夹中
     * 如果成功true; 否则false
     */
    public static boolean moveFile(String strSourceFileName, String strDestDir) {
        if (copyTo(strSourceFileName, strDestDir))
            return FileUtil.delete(strSourceFileName);
        else
            return false;
    }

    /**
     * 创建文件夹
     * <p>
     * strDir 要创建的文件夹名称
     * 如果成功true;否则false
     */
    public static boolean makedir(String strDir) {
        File fileNew = new File(strDir);

        if (!fileNew.exists()) {
            logger.debug("文件夹不存在--创建文件夹");
            return fileNew.mkdirs();
        } else {
            logger.debug("文件夹存在");
            return true;
        }
    }

    /**
     * 删除文件夹
     * <p>
     * strDir 要删除的文件夹名称
     * 如果成功true;否则false
     */
    public static boolean rmdir(String strDir) {
        File rmDir = new File(strDir);
        if (rmDir.isDirectory() && rmDir.exists()) {
            String[] fileList = rmDir.list();

            for (int i = 0; i < fileList.length; i++) {
                String subFile = strDir + File.separator + fileList[i];
                File tmp = new File(subFile);
                if (tmp.isFile())
                    tmp.delete();
                else if (tmp.isDirectory())
                    rmdir(subFile);
                else {
                    logger.debug("error!");
                }
            }
            rmDir.delete();
        } else
            return false;
        return true;
    }


    /**
     * @功能描述：下载文件
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月30日 上午10:55:16
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param filename  下载文件的文件名
     * @param response
     * @param allPath   下载文件在服务器上的全路径（包括文件名）
     * @throws IOException
     */
    public static void downFile(String filename, HttpServletResponse response, String allPath)
            throws IOException {
        BufferedInputStream bis;
        BufferedOutputStream bos;
        OutputStream fos;
        InputStream fis;
        File uploadFile = new File(allPath);
        fis = new FileInputStream(uploadFile);
        bis = new BufferedInputStream(fis);
        fos = response.getOutputStream();
        bos = new BufferedOutputStream(fos);
        
        response.reset();
        //设置文件MIME类型  
        //response.setContentType(request.getServletContext().getMimeType(filename)); 
        //设置Content-Disposition  
        response.addHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode(filename, "utf-8"));
        int bytesRead;
        byte[] buffer = new byte[8192];
        while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush();
        fis.close();
        bis.close();
        fos.close();
        bos.close();
    }
    
    /**
     * @功能描述：下载文件，改造上述方法
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月30日 下午2:43:15
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param filePath  文件全路径，包含文件名
     * @param response
     * @throws IOException FileNotFoundException
     */
    public static void downFile(String filePath, HttpServletResponse response) throws IOException,FileNotFoundException {
        BufferedInputStream bis;
        BufferedOutputStream bos;
        OutputStream fos;
        InputStream fis;
        File uploadFile = new File(filePath);
        fis = new FileInputStream(uploadFile);
        bis = new BufferedInputStream(fis);
        fos = response.getOutputStream();
        bos = new BufferedOutputStream(fos);
        
        String filename = uploadFile.getName();
        
        response.reset();
        //设置文件MIME类型  
        //response.setContentType(request.getServletContext().getMimeType(filename));
        //设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");
        //设置Content-Disposition  
        response.addHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode(filename, "utf-8"));
        int bytesRead;
        byte[] buffer = new byte[8192];
        while ((bytesRead = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush();
        fis.close();
        bis.close();
        fos.close();
        bos.close();
    }
    
    /**
     * @功能描述：文件下载，改造上述方法
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月9日 上午9:50:22
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param request
     * @param response
     * @param filePath
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException,FileNotFoundException {
        BufferedInputStream bis;
        BufferedOutputStream bos;
        OutputStream fos;
        InputStream fis;
        File uploadFile = new File(filePath);
        String filename = "";
        
        if(!uploadFile.exists()) {
        	throw new FileNotFoundException("您要下载的文件不存在！");
        } else {
        	filename = uploadFile.getName();
        	//指定IE及其他浏览器时下载文件名的编码格式
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0
                    || request.getHeader("User-Agent").toUpperCase().indexOf("TRIDENT") > 0) {
            	filename = URLEncoder.encode(filename, "UTF-8");
            } else {
            	filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            
            fis = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            
            response.reset();
			// 设置文件MIME类型
			response.setContentType(request.getServletContext().getMimeType(filename));
			// 设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");
			
			try {
				int bytesRead = -1;
				byte[] buffer = new byte[8192];
				while ((bytesRead = bis.read(buffer)) != -1) {
				    bos.write(buffer, 0, bytesRead);
				}
				bos.flush();
			} catch (Exception e) {
				e.printStackTrace();	
				throw new IOException("文件下载失败 \n erorr in  com.zzrenfeng.base.utils.FileUtil.downFile()!");
			} finally {
				if(null != fis) fis.close();
		        if(null != fis) bis.close();
		        if(null != fos) fos.close();
		        if(null != bos) bos.close();
			}     
	        
		}
        
    }

    /**
     * 读取二进制文件并且写入数组里
     *
     * @param filePath
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static byte[] getBytes4File(String filePath) {

        InputStream in = null;
        BufferedInputStream buffer = null;
        DataInputStream dataIn = null;
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        byte[] bArray = null;
        try {
            in = new FileInputStream(filePath);
            buffer = new BufferedInputStream(in);
            dataIn = new DataInputStream(buffer);
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            byte[] buf = new byte[1024];
            while (true) {
                int len = dataIn.read(buf);
                if (len < 0)
                    break;
                dos.write(buf, 0, len);
            }
            bArray = bos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            try {
                if (in != null)
                    in.close();
                if (dataIn != null)
                    dataIn.close();
                if (buffer != null)
                    buffer.close();
                if (bos != null)
                    bos.close();
                if (dos != null)
                    dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bArray;
    }
    
    
    /**
     * @功能描述：下载文件
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月30日 下午2:15:14
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param path  文件全路径（带文件名）
     * @param response
     * @return
     */
    public static HttpServletResponse downloadFile(String path, HttpServletResponse response) throws IOException,FileNotFoundException {
        
		// path是指欲下载的文件的路径。
		File file = new File(path);
		// 取得文件名。
		String filename = file.getName();
		// 取得文件的后缀名。
		String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

		// 以流的形式下载文件。
		InputStream fis = new BufferedInputStream(new FileInputStream(path));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
        return response;
    }
    
    /**
     * @功能描述：下载本地文件
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月30日 下午2:15:59
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param response
     * @throws FileNotFoundException
     */
    public static void downloadLocalFile(HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "Operator.doc".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("c:/Operator.doc");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[8192];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @功能描述：下载网络文件到本地
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月30日 下午2:28:20
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param netFileUrl 网络资源url
     * @param localFilePath 本地存放文件路径，以“/”结尾
     * @param response
     * @throws MalformedURLException,FileNotFoundException,IOException
     */
    public static void downloadNetFile(String netFileUrl, String localFilePath,HttpServletResponse response) throws MalformedURLException,FileNotFoundException,IOException {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;
        
        //netFileUrl = "windine.blogdriver.com/logo.gif";
        //netFileUrl = java.net.URLEncoder.encode(netFileUrl, "UTF-8");
        URL url = new URL(netFileUrl);
        String fileName = url.getFile();
        localFilePath = StringUtil.isEmpty(localFilePath) ? ("c:/" + fileName) : (localFilePath + fileName);
        
        /*try {*/
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(localFilePath);

            byte[] buffer = new byte[8192];
            //int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
            fs.flush();
        /*} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {*/
            if(null != inStream) inStream.close();
            if(null != fs) fs.close();
    	/*}*/
    }
    
    /**
     * @功能描述：支持在线打开文件的一种下载方式
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月30日 下午2:34:18
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param filePath
     * @param response
     * @param isOnLine 是否在线打开标识
     * @throws Exception
     */
    public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        out.flush();
        br.close();
        out.close();
    }
    
    
}
