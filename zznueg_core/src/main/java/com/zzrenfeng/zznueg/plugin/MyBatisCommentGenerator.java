package com.zzrenfeng.zznueg.plugin;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * <pre>
 * @功能描述：MyBatis自定义注释生成器
 * 			使用方法：类配置到generatorConfig.xml文件中，eg：
 * 			<commentGenerator type="com.zzrenfeng.zznueg.plugin.MyBatisCommentGenerator">
 *	        	<property name="javaFileEncoding" value="UTF-8"/>
 *	            <property name="suppressAllComments" value="false"/> <!-- 是否取消自动生成的注释 true-是 ,false-否 -->
 *	            <property name="suppressDate" value="true"/> <!-- 是否生成注释代时间戳-->
 *	        </commentGenerator>
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月23日 上午9:56:54
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 * </pre>
 */
//public class MyBatisCommentGenerator implements CommentGenerator {
public class MyBatisCommentGenerator extends DefaultCommentGenerator {
	private Properties properties;
	private Properties systemPro;
	private boolean suppressDate;
	private boolean suppressAllComments;
	private String currentDateStr;

	/**
	 * 构造方法
	 */
	public MyBatisCommentGenerator() {
	    super();
	    properties = new Properties();
	    systemPro = System.getProperties();
	    suppressDate = true;
	    suppressAllComments = false;
	    currentDateStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}
	
	/**
	 * 增加类注释
	 */
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
	        return;
	    }
		
	    StringBuilder sb = new StringBuilder();
	    
//	    innerClass.addJavaDocLine("/**");
//	    sb.append(" * ");
//	    sb.append(introspectedTable.getFullyQualifiedTable());
//	    sb.append(" ");
//	    sb.append(getDateString());
//	    innerClass.addJavaDocLine(sb.toString());
//	    innerClass.addJavaDocLine(" */");
	    
	    innerClass.addJavaDocLine("/**");
	    sb.append(" * @功能描述：");
	    sb.append(introspectedTable.getFullyQualifiedTable());
	    sb.append("实体类");
	    innerClass.addJavaDocLine(sb.toString());

	    sb.setLength(0);
	    sb.append(" * @创  建  者： ");
	    sb.append(systemPro.getProperty("user.name"));
	    innerClass.addJavaDocLine(sb.toString());
	    sb.setLength(0);
	    sb.append(" * @版        本：V1.0.0");
	    innerClass.addJavaDocLine(sb.toString());
	    sb.setLength(0);
	    sb.append(" * @创建日期：");
	    sb.append(currentDateStr);
	    innerClass.addJavaDocLine(sb.toString());
	    
//	    addJavadocTag(innerClass, markAsDoNotDelete);
	    
	    innerClass.addJavaDocLine(" */");	    
	    
	}
	
	/**
	 * 增加类注释
	 */
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
	        return;
	    }

	    StringBuilder sb = new StringBuilder();

	    innerClass.addJavaDocLine("/**");
	    sb.append(" * @功能描述：");
	    sb.append(introspectedTable.getFullyQualifiedTable());
	    sb.append("实体类");
	    innerClass.addJavaDocLine(sb.toString());

	    sb.setLength(0);
	    sb.append(" * @创  建  者： ");
	    sb.append(systemPro.getProperty("user.name"));
	    innerClass.addJavaDocLine(sb.toString());
	    sb.setLength(0);
	    sb.append(" * @版        本：V1.0.0");
	    innerClass.addJavaDocLine(sb.toString());
	    sb.setLength(0);
	    sb.append(" * @创建日期：");
	    sb.append(currentDateStr);
	    innerClass.addJavaDocLine(sb.toString());
	    
//	    addJavadocTag(innerClass, markAsDoNotDelete);
	    
	    innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addComment(XmlElement xmlElement) {
		// TODO Auto-generated method stub
		return;
	}

	/**
	 * 增加配置属性
	 */
	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
	    suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
	    suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
	}

	/**
	 * 增加枚举类注释
	 */
	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
	        return;
	    }

	    StringBuilder sb = new StringBuilder();

	    innerEnum.addJavaDocLine("/**");
//	    addJavadocTag(innerEnum, false);
	    sb.append(" * ");
	    sb.append(introspectedTable.getFullyQualifiedTable());
	    innerEnum.addJavaDocLine(sb.toString());
	    innerEnum.addJavaDocLine(" */");
	}

	/**
	 * 增加域(成员方法或属性)注释
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
	        return;
	    }

	    StringBuilder sb = new StringBuilder();

	    field.addJavaDocLine("/**");
	    
	    sb.append(" * ");
	    sb.append(introspectedTable.getFullyQualifiedTable());
	   
	    field.addJavaDocLine(sb.toString());  //field.addJavaDocLine(sb.toString().replace("\n", " "));
	    field.addJavaDocLine(" */");
	}
	
	/**
	 * 增加域(成员方法或属性)注释
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
	        return;
	    }

	    StringBuilder sb = new StringBuilder();

	    field.addJavaDocLine("/**");
	    sb.append(" * ");
	    sb.append(introspectedColumn.getRemarks());
	    field.addJavaDocLine(sb.toString());  //field.addJavaDocLine(sb.toString().replace("\n", " "));
	    
//	    addJavadocTag(field, false);

	    field.addJavaDocLine(" */");
	}
	
	/**
	 * 增加一般方法注释
	 */
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
	        return;
	    }
//		method.addJavaDocLine("/**");
//		addJavadocTag(method, false);
//		method.addJavaDocLine(" */");
	}
	
	/**
	 * 增加getter方法注释
	 */
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
	        return;
	    }

	    method.addJavaDocLine("/**");

	    StringBuilder sb = new StringBuilder();
	    sb.append(" * ");
	    sb.append(introspectedColumn.getRemarks());
	    method.addJavaDocLine(sb.toString());

	    sb.setLength(0);
	    sb.append(" * @return ");
	    sb.append(introspectedColumn.getActualColumnName());
	    sb.append(" ");
	    sb.append(introspectedColumn.getRemarks());
	    method.addJavaDocLine(sb.toString());

//	    addJavadocTag(method, false);

	    method.addJavaDocLine(" */");
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void addRootComment(XmlElement xmlElement) {
		// TODO Auto-generated method stub
		return;
	}
	
	/**
	 * 增加setter方法注释
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
	        return;
	    }
		
	    method.addJavaDocLine("/**");
	    StringBuilder sb = new StringBuilder();
	    sb.append(" * ");
	    sb.append(introspectedColumn.getRemarks());
	    method.addJavaDocLine(sb.toString());

	    Parameter parm = method.getParameters().get(0);
	    sb.setLength(0);
	    sb.append(" * @param ");
	    sb.append(parm.getName());
	    sb.append(" ");
	    sb.append(introspectedColumn.getRemarks());
	    method.addJavaDocLine(sb.toString());

//	    addJavadocTag(method, false);

	    method.addJavaDocLine(" */");
	}
	
	/**
	 * @功能描述：This method adds the custom javadoc tag for. You may do nothing if you do
	 * 			not wish to include the Javadoc tag - however, if you do not include the
	 * 			Javadoc tag then the Java merge capability of the eclipse plugin will break.
	 * 			(该方法将自定义Javadoc标签。你如果不希望包括Javadoc标签，什么都不做即可，然而，如果你包括Javadoc标签，则Eclipse合并java插件的能力将终止。 )
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月23日 上午10:07:40
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param javaElement
	 * @param markAsDoNotDelete
	 */
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
	    javaElement.addJavaDocLine(" *");
	    StringBuilder sb = new StringBuilder();
	    sb.append(" * ");
	    sb.append(MergeConstants.NEW_ELEMENT_TAG);
	    if (markAsDoNotDelete) {
	        sb.append(" do_not_delete_during_merge");
	    }
	    String s = getDateString();
	    if (s != null) {
	        sb.append(' ');
	        sb.append(s);
	    }
	    javaElement.addJavaDocLine(sb.toString());
	}
	/**
	 * @功能描述：获取当前日期字符串
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月23日 上午10:16:34
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	protected String getDateString() {
	    String result = null;
	    if (!suppressDate) {
	        result = currentDateStr;
	    }
	    return result;
	}
	

}
