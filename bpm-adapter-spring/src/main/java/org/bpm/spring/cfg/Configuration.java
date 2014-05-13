package org.bpm.spring.cfg;

import org.bpm.common.exception.impl.InternalErrorException;
import org.bpm.engine.BpmEngine;
import org.bpm.spring.BpmEngineImpl;
import org.bpm.spring.EngineType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by serv on 14-5-7.
 */
public class Configuration implements Serializable,Environment{

    private Properties properties;

    private ApplicationContext applicationContext;

    private PlatformTransactionManager transactionManager;

    private DataSource dataSource;

    /**
     * 创建流程引擎对象
     * @return
     */
    public BpmEngine buildBpmEngine() {
        return new BpmEngineImpl(this);
    }

    /**
     * 获取spring 上下文
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取事务管理器
     * @return
     */
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * 获取数据源
     * @return
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String propertyName) {
        return properties.getProperty( propertyName );
    }

    /**
     * 获取当前的属性配置器
     * @return
     */
    public Properties getProperties() {
        if (this.properties == null) {
            this.properties = new Properties();
        }
        return this.properties;
    }

    /**
     * 获取流程引擎的类型
     * @return
     */
    public EngineType getEngineType() {

        String engineType = getProperty(ENGINE_TYPE);

        return getType(engineType);
    }

    private EngineType getType(String typeName){

        if(typeName==null){
            throw new InternalErrorException("没有配置默认流程引擎");
        }

        if(typeName.equals(ACTIVITI_ENGINE_TYPE)){
            return EngineType.ACTIVITI;
        }
        if(typeName.equals(JBPM4_ENGINE_TYPE)){
            return EngineType.JBPM4;
        }
        if(typeName.equals(JBPM6_ENGINE_TYPE)){
            return EngineType.JBPM6;
        }
        throw new InternalErrorException("流程引擎配置参数出错");
    }

    public Configuration addProperties(Properties properties) {
        this.getProperties().putAll(properties);
        return this;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
