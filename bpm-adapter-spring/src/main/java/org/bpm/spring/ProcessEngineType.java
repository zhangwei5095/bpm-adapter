package org.bpm.spring;

import org.bpm.engine.impl.activiti.ActivitiRuntimeImpl;
import org.bpm.engine.impl.jbpm6.Jbpm6RuntimeImpl;
import org.bpm.spring.cfg.initialize.ActivitiEngineInitialize;
import org.bpm.spring.cfg.initialize.IBeanDefinition;

/**
 * Created by serv on 14-5-17.
 */
public enum ProcessEngineType implements IEngineType{
    activiti("activiti"){
        @Override
        public IBeanDefinition getProcessEngineInitialize(String dataSourceBeanName, String transactionManagerBeanName) {
            return new ActivitiEngineInitialize(dataSourceBeanName,transactionManagerBeanName);
        }

        @Override
        public Class getBpmRuntimeImplClass() {
            return ActivitiRuntimeImpl.class;
        }
    }
    ,jbpm6("jbpm6"){
        @Override
        public IBeanDefinition getProcessEngineInitialize(String dataSourceBeanName, String transactionManagerBeanName) {
            return null;
        }

        @Override
        public Class getBpmRuntimeImplClass() {
            return Jbpm6RuntimeImpl.class;
        }
    };

    private String type;

    ProcessEngineType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ProcessEngineType getProcessEngineType(String processEngineType){
        ProcessEngineType result = activiti;
        if(processEngineType!=null){
            if(processEngineType.equals(activiti.getType())){
                result = activiti;
            }else if(processEngineType.equals(jbpm6.getType())){
                result = jbpm6;
            }
        }
        return result;

    }

}

interface IEngineType{

    IBeanDefinition getProcessEngineInitialize(String dataSourceBeanName, String transactionManagerBeanName);

    Class getBpmRuntimeImplClass();
}