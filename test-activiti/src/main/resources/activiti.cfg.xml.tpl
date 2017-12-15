<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="processEngineConfiguration"
		class="org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration">

		<property name="jdbcUrl" value="jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000" />
		<property name="jdbcDriver" value="org.h2.Driver" />
		<property name="jdbcUsername" value="sa" />
		<property name="jdbcPassword" value="" />

		<property name="databaseSchemaUpdate" value="true" />

		<property name="asyncExecutorActivate" value="false" />

		<property name="mailServerHost" value="mail.my-corp.com" />
		<property name="mailServerPort" value="5025" />
	</bean>

</beans>