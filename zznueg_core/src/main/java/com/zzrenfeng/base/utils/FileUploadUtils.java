package com.zzrenfeng.base.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.zzrenfeng.base.utils.exception.FileNameLengthLimitExceededException;
import com.zzrenfeng.base.utils.exception.InvalidExtensionException;
import com.zzrenfeng.base.utils.security.Coder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;

/**
 * topic
 * author: zhoujincheng
 * create: 2016/3/16 14:26
 */
public class FileUploadUtils {
    // 默认大小 100M
    public static final long DEFAULT_MAX_SIZE = 104857600;
    // 默认的文件名最大长度
    public static final int DEFAULT_FILE_NAME_LENGTH = 200;
    public static final String[] IMAGE_EXTENSION = {"bmp", "gif", "jpg", "jpeg", "png"};
    public static final String[] FLASH_EXTENSION = {"swf", "flv"};
    public static final String[] MEDIA_EXTENSION = {"swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
            "asf", "rm", "rmvb"};
    //上传文件类型包含：图片，word，压缩文件，pdf文件
    public static final String[] DEFAULT_ALLOWED_EXTENSION = {"bmp", "gif", "jpg", "jpeg", "png", "doc", "docx",
            "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt", "rar", "zip", "gz", "bz2", "pdf", "flv", "mp4"};
    // 默认上传的地址，相对web容器中应用所在根目录的路径
    private static String defaultWebBaseDir = "upload";
    private static String defaultBaseDir = PropertiesUtils.getUploadPath();
    private static int counter = 0;
    /**
	 * 私有构造方法，不需要创建对象
	 */
    private FileUploadUtils() {    	
    }
    
    public static String getDefaultWebBaseDir() {
        return defaultWebBaseDir;
    }
    
    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }
    
    /**
     * @功能描述：将上传到WebRoot下的文件拷贝到文件系统指定的目录
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月11日 上午9:37:16
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param request
     * @param uploadFilePath 相对于WebRoot的文件路径+文件名
     * @return 返回文件系统指定的文件目录和文件名
     */
    public static String copyUploadFileToFSDir(HttpServletRequest request, String uploadFilePath) {
    	String webRootPath = extractWebUploadDir(request);
    	//上传文件的绝对路径；(Web容器中应用的根目录=webRootPath)+(文件相对目录+文件名=uploadFilePath)
    	String absUploadFilePath = webRootPath + uploadFilePath;	
    	//带日期的目录的文件名，形如：2017\10\10\FDAFAFAFDAFADFADFAF_test.pdf
    	String filenameWithDatePath = uploadFilePath.substring(uploadFilePath.indexOf(getDefaultWebBaseDir()) + getDefaultWebBaseDir().length() + 1);
    	//截取上传文件中带的日期目录，形如：2017\10\10
    	String uploadFileDatePath = filenameWithDatePath.substring(0, filenameWithDatePath.lastIndexOf(File.separatorChar));
    	//截取源目录中文件名
    	String filename = uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1);
    	//文件系统目录——使用源上传路径中的日期目录
//    	String fsDir = FilenameUtils.normalizeNoEndSeparator(getDefaultBaseDir()) + filenameWithDatePath;
    	//文件系统目录——使用当前日期的目录
    	String fsDir = FilenameUtils.normalizeNoEndSeparator(getDefaultBaseDir()) + File.separator + datePath();
    	//目标文件名绝对路径，要返回的结果；absFsFilename=文件系统目录+源文件名
    	String absFsFilename = fsDir + File.separator + filename;
    	
System.out.println("##################====源文件绝对路径+文件名====>>>>" + absUploadFilePath);    
System.out.println("##################====目标文件绝对路径====>>>>" + fsDir);   
		
		if(FileUtil.copyTo(absUploadFilePath, fsDir)) {
System.out.println("##################====>>>>文件拷贝成功<<<<====##################" + absFsFilename);  			
			return absFsFilename;
		}
		return null;
    }

    /**
     * 以默认配置进行文件上传
     * <p>
     * param request 当前请求
     * param file    上传的文件
     * param result  添加出错信息
     * param isUploadWebRoot  是否上传到web容器中应用的上下文根目录下，true-是，false-否
     * return
     */
    public static final String upload(HttpServletRequest request, MultipartFile file, BindingResult result, boolean isUploadWebRoot) {
        return upload(request, file, result, DEFAULT_ALLOWED_EXTENSION, isUploadWebRoot);
    }

    /**
     * 以默认配置进行文件上传；将文件上传到defaultBaseDir（config.properties中配置为：file.upload.dir = F:\\ade\\upload）中
     * <p>
     * param request          当前请求
     * param file             上传的文件
     * param result           添加出错信息
     * param allowedExtension 允许上传的文件类型
     * param isUploadWebRoot  是否上传到web容器中应用的上下文根目录下，true-是，false-否
     * return 上传成功后文件的路径（文件所在目录+文件名）
     */
    public static final String upload(HttpServletRequest request, MultipartFile file, BindingResult result,
                                      String[] allowedExtension, boolean isUploadWebRoot) {
        try {
        	if(isUploadWebRoot) {
        		return upload(request, getDefaultWebBaseDir(), file, allowedExtension, DEFAULT_MAX_SIZE, true, isUploadWebRoot);
        	} else {
        		return upload(request, getDefaultBaseDir(), file, allowedExtension, DEFAULT_MAX_SIZE, true, isUploadWebRoot);
        	}            
        } catch (IOException e) {
            LogUtils.logError("file upload error", e);
            result.reject("upload.server.error");
        } catch (InvalidExtensionException.InvalidImageExtensionException e) {
            result.reject("upload.not.allow.image.extension");
        } catch (InvalidExtensionException.InvalidFlashExtensionException e) {
            result.reject("upload.not.allow.flash.extension");
        } catch (InvalidExtensionException.InvalidMediaExtensionException e) {
            result.reject("upload.not.allow.media.extension");
        } catch (InvalidExtensionException e) {
            result.reject("upload.not.allow.extension");
        } catch (FileSizeLimitExceededException e) {
            result.reject("upload.exceed.maxSize");
        } catch (FileNameLengthLimitExceededException e) {
            result.reject("upload.filename.exceed.length");
        }
        return null;
    }

    /**
     * 文件上传——将文件上传到（filename=baseDir+filename）的路径中
     * <p>
     * param request                   当前请求 从请求中提取 应用上下文根
     * param baseDir                   相对应用的基目录
     * param file                      上传的文件
     * param allowedExtension          允许的文件类型 null 表示允许所有
     * param maxSize                   最大上传的大小 -1 表示不限制
     * param needDatePathAndRandomName 是否需要日期目录和随机文件名前缀
     * param isUploadWebRoot		       是否上传到web容器中应用的上下文根目录下，true-是，false-否
     * return 返回上传成功的文件名
     * throws InvalidExtensionException            如果MIME类型不允许
     * throws FileSizeLimitExceededException       如果超出最大大小
     * throws FileNameLengthLimitExceededException 文件名太长
     * throws IOException                          比如读写文件出错时
     * 
     * 修改人：zjc
     * 版本：V1.1.0
     * 修改日期：20171010 17:44
     * 修改内容：增加参数isUploadWebRoot（是否上传到web容器中应用的上下文根目录下），true-是，为应用所在的根目录；false-否，为配置的文件系统具体目录；
     */
    public static final String upload(HttpServletRequest request, String baseDir, MultipartFile file,
                                      String[] allowedExtension, long maxSize, boolean needDatePathAndRandomName, boolean isUploadWebRoot)
            throws InvalidExtensionException, FileSizeLimitExceededException, IOException,
            FileNameLengthLimitExceededException {

        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength,
                    FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension, maxSize);
        String filename = extractFilename(file, baseDir, needDatePathAndRandomName);
        
        File desc = null;
        if(isUploadWebRoot) {
        	desc = getAbsoluteFile(extractWebUploadDir(request), filename);
        } else {
        	desc = getAbsoluteFile(null, filename);  //此时的filename已经为文件路径+文件名；
        }

        file.transferTo(desc);
        return filename;
    }

    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {
    	uploadDir = StringUtil.isEmpty(uploadDir) ? "" : uploadDir;
        uploadDir = FilenameUtils.normalizeNoEndSeparator(uploadDir);

        File desc = new File(uploadDir + File.separator + filename);
//System.out.println("----------------------->>>>filename=(" + (filename) + ")");
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * @功能描述：提取取文件名，filename=baseDir+extendDir+filename(基目录+扩展目录；其中基目录为配置的相对或绝对路径；扩展目录为是否带日期目录；)
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月10日 下午5:02:14
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param file
     * @param baseDir
     * @param needDatePathAndRandomName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static final String extractFilename(MultipartFile file, String baseDir, boolean needDatePathAndRandomName)
            throws UnsupportedEncodingException {
    	String filename = baseDir + File.separator + extendFilename(file, needDatePathAndRandomName);
        return filename;
    }
    
    /**
     * @功能描述：扩展文件名，是否增加日期目录
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月10日 下午5:01:31
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param file
     * @param needDatePathAndRandomName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static final String extendFilename(MultipartFile file, boolean needDatePathAndRandomName) throws UnsupportedEncodingException {
    	 String filename = file.getOriginalFilename();
    	 int slashIndex = filename.indexOf("/");
    	 if (slashIndex >= 0) {
             filename = filename.substring(slashIndex + 1);
         }
    	 if (needDatePathAndRandomName) {
             filename = datePath() + File.separator + encodingFilename(filename);
         } else {
             filename = filename;
         }
         return filename;
    }

    /**
     * 编码文件名,默认格式为： 1、'_'替换为 ' ' 
     * 					 2、hex(md5(filename + now nano time + counter++)) 
     * 					 3、[2]_原始文件名
     * <p>
     * 
     * zjc moddify:
     * date:20170816 11:43
     * desc:源文件的文件名原样输出，不做任何修改，将原来的第1条（'_'替换为 ' '）去掉
     * 
     * param filename
     * return
     */
    private static final String encodingFilename(String filename) {
        //filename = filename.replace("_", " ");		//源文件的文件名原样输出，不做任何修改
        filename = Coder.encryptMD5(filename + System.nanoTime() + counter++) + "_" + filename;
        return filename;
    }

    /**
     * 日期路径 即年/月/日 如2013/01/03
     * <p>
     * return
     * zjc modify:为了避免windows和linux系统目录分隔符的不同，这里的日期目录分隔符要根据系统来确定；这里同意使用File.separator定义
     */
    private static final String datePath() {
        Date now = new Date();
        String formatStr = "yyyy" + File.separator + "MM" + File.separator + "dd";
        return DateFormatUtils.format(now, formatStr);
    }

    /**
     * 是否允许文件上传
     * <p>
     * param file             上传的文件
     * param allowedExtension 文件类型 null 表示允许所有
     * param maxSize          最大大小 字节为单位 -1表示不限制
     * return
     * throws InvalidExtensionException      如果MIME类型不允许
     * throws FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension, long maxSize)
            throws InvalidExtensionException, FileSizeLimitExceededException {

        String filename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        filename);
            } else if (allowedExtension == FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        filename);
            } else if (allowedExtension == MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        filename);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, filename);
            }
        }

        long size = file.getSize();
        if (maxSize != -1 && size > maxSize) {
            throw new FileSizeLimitExceededException("not allowed upload upload", size, maxSize);
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     * <p>
     * param extension
     * param allowedExtension
     * return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 提取上传的根目录 默认是应用的根
     * <p>
     * param request
     * return
     * 
     * 修改版本：V1.0.1-该修改版本废弃
     * 修改人：zjc
     * 修改日期：20171009 14:23
     * 修改内容：（该修改版本废弃）增加方法参数：isContextRoot是否为上下文根；true-是上下文中应用的根，false-不是上下文应用根，是用户指定的根目录
     * 
     * 修改版本：V1.1.0
     * 修改人：zjc
     * 修改日期：20171010 17:37
     * 修改内容：将方法名从extractUploadDir修改为extractWebUploadDir，意为：提取上下文跟的所在操作系统的实际目录；
     * 		注意：若以war包部署该方法失效，无法获取web容器中应用的上下文根所在的实际目录；
     */
    public static final String extractWebUploadDir(HttpServletRequest request) {
    	return request.getServletContext().getRealPath("/");
    }

    public static final void delete(HttpServletRequest request, String filename) throws IOException {
        if (StringUtil.isEmpty(filename)) {
            return;
        }
        File desc = getAbsoluteFile(extractWebUploadDir(request), filename);
        if (desc.exists()) {
            desc.delete();
        }
    }
}
