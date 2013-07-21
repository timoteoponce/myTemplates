package org.application.config

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.context.annotation.ComponentScan
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource
import org.apache.commons.dbcp.BasicDataSource
import org.springframework.context.annotation.Bean
import org.hibernate.SessionFactory
import java.util.Properties
import org.springframework.orm.hibernate4.LocalSessionFactoryBean
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = Array("org.timoponce","org.timoponce.rest"))
class AppConfig extends WebMvcConfigurerAdapter {

  @Bean(destroyMethod = "close")
  def datasource(): DataSource = {
    val ds = new BasicDataSource
    ds.setDriverClassName("org.h2.Driver")
    ds.setUrl("jdbc:h2:~/.tmp/screenfood;AUTO_SERVER=TRUE;MVCC=TRUE")
    ds.setUsername("sa")
    ds.setPassword("")
    return ds
  }

  @Bean
  def sessionFactory(): SessionFactory = {
    val factory = new LocalSessionFactoryBean
    factory.setDataSource(datasource())
    factory
      .setPackagesToScan("org.timoponce")
    val properties = new Properties()
    properties.setProperty("hibernate.dialect",
      "org.hibernate.dialect.H2Dialect")
    properties.setProperty("hibernate.show_sql", "false")
    properties.setProperty("hibernate.format_sql", "false")
    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop")
    factory.setHibernateProperties(properties)
    factory.afterPropertiesSet()
    return factory.getObject()
  }

  @Bean(name = Array("transactionManager"))
  def transactionManager(): PlatformTransactionManager = {
    return new HibernateTransactionManager(sessionFactory());
  }

}