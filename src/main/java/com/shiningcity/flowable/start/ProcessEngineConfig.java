package com.shiningcity.flowable.start;

import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import lombok.Data;

/**
 * 流程引擎配置文件
 **/
@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class ProcessEngineConfig {

    private Logger logger = LoggerFactory.getLogger(ProcessEngineConfig.class);

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    //@Value("${spring.datasource.publicKey}")
    //private String publicKey;

    /**
     * 初始化流程引擎
     * @return
     */
    @Primary
    @Bean(name = "processEngine")
    public ProcessEngine initProcessEngine() {
        logger.info("=============================ProcessEngineBegin=============================");

        // 流程引擎配置
        ProcessEngineConfiguration cfg = null;

        try {
            cfg = new StandaloneProcessEngineConfiguration()
                    .setJdbcUrl(url)
                    .setJdbcUsername(username)
                    //.setJdbcPassword(ConfigTools.decrypt(publicKey, password))
                    .setJdbcPassword(password)
                    .setJdbcDriver(driverClassName)
                    // 初始化基础表，不需要的可以改为 DB_SCHEMA_UPDATE_FALSE
                    .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//                    // 默认邮箱配置
//                    // 发邮件的主机地址，先用 QQ 邮箱
//                    .setMailServerHost("smtp.qq.com")
//                    // POP3/SMTP服务的授权码
//                    .setMailServerPassword("xxxxxxx")
//                    // 默认发件人
//                    .setMailServerDefaultFrom("836369078@qq.com")
//                    // 设置发件人用户名
//                    .setMailServerUsername("管理员")
//                    // 解决流程图乱码
//                    .setActivityFontName("宋体")
//                    .setLabelFontName("宋体")
//                    .setAnnotationFontName("宋体");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 初始化流程引擎对象
        ProcessEngine processEngine = cfg.buildProcessEngine();
        logger.info("=============================ProcessEngineEnd=============================");
        return processEngine;
    }
    
    /* 
     * 如果引入的flowable-spring-boot-starter依赖，不需要注册八大核心服务接口，springboot会自动注册
     * 如果引入的flowable-engine依赖，需要注册如下八大核心服务接口
     */
//  //八大接口
// // 业务流程的定义相关服务
// @Bean
// public RepositoryService repositoryService(ProcessEngine processEngine){
//     return processEngine.getRepositoryService();
// }
//
// // 流程对象实例相关服务
// @Bean
// public RuntimeService runtimeService(ProcessEngine processEngine){
//     return processEngine.getRuntimeService();
// }
//
// // 流程任务节点相关服务
// @Bean
// public TaskService taskService(ProcessEngine processEngine){
//     return processEngine.getTaskService();
// }
//
// // 流程历史信息相关服务
// @Bean
// public HistoryService historyService(ProcessEngine processEngine){
//     return processEngine.getHistoryService();
// }
//
// // 表单引擎相关服务
// @Bean
// public FormService formService(ProcessEngine processEngine){
//     return processEngine.getFormService();
// }
//
// // 用户以及组管理相关服务
// @Bean
// public IdentityService identityService(ProcessEngine processEngine){
//     return processEngine.getIdentityService();
// }
//
// // 管理和维护相关服务
// @Bean
// public ManagementService managementService(ProcessEngine processEngine){
//     return processEngine.getManagementService();
// }
//
// // 动态流程服务
// @Bean
// public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine){
//     return processEngine.getDynamicBpmnService();
// }
// //八大接口 end

}
