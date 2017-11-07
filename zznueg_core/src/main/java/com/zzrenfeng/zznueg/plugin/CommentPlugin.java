package com.zzrenfeng.zznueg.plugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.db.ConnectionFactory;
/**
 * @功能描述：生成Java注释插件
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月20日 上午10:21:33
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
public class CommentPlugin extends PluginAdapter {
	
	private static final String AUTHOR = "modelClassAuthor";

	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		String remark = introspectedColumn.getRemarks();
		field.addJavaDocLine("/** " + remark + " */");

		return true;
	}

	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		addModelClassComment(topLevelClass, introspectedTable);
		return true;
	}

	/**
	 * @功能描述：为生成的类添加注释模块
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 上午10:32:45
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		String remarks = "";
		String author = getProperties().getProperty(AUTHOR);
		if (null == author || "".equals(author)) {
			author = System.getProperty("user.name");
		}

		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		try {
			Connection connection = ConnectionFactory.getInstance()
					.getConnection(context.getJdbcConnectionConfiguration());
			ResultSet rs = connection.getMetaData().getTables(table.getIntrospectedCatalog(),
					table.getIntrospectedSchema(), table.getIntrospectedTableName(), null);

			if (null != rs && rs.next()) {
				remarks = rs.getString("REMARKS");
			}
			closeConnection(connection, rs);
		} catch (SQLException e) {
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * @功能描述：" + remarks);		
		topLevelClass.addJavaDocLine(" * @创  建  者：" + author);
		topLevelClass.addJavaDocLine(" * @版        本：V1.0.0");
		topLevelClass.addJavaDocLine(" * @创建日期：" + format.format(new Date()));
		topLevelClass.addJavaDocLine(" *");
		topLevelClass.addJavaDocLine(" */");
	}

	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		addModelClassComment(topLevelClass, introspectedTable);
		return true;
	}

	private void closeConnection(Connection connection, ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}
	
	/**
     * This plugin is always valid - no properties are required
     */
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
	
	
}
