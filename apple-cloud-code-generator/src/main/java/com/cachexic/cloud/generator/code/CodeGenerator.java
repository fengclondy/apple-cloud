package com.cachexic.cloud.generator.code;


import com.cachexic.cloud.generator.bean.EntityInfo;
import com.cachexic.cloud.generator.bean.GenConfig;
import com.cachexic.cloud.generator.tmplate.*;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangmin
 * @version V1.0
 * @Title: CodeGenerator.java
 * @Package com.gasq.cloud.generator.generator
 * @Description: 代码生成器
 * @date 2017-04-26 16:26:23
 */
public class CodeGenerator {

    List<Class<?>> clazzList = new ArrayList<Class<?>>();

    /**
     * 是否继承BaseEntity
     */
    private String extendBaseEntity;

    /**
     * 主键类型
     */
    private int idType;

    /**
     * 微服务名称
     */
    private String serverName;

    /**
     * 实体对应的表名
     */
    private String tableName;


    /**
     * requestMapping路径
     */
    private String requestMapPath;

    /**
     * 模块名
     */
    private String modelName;

    public String getExtendBaseEntity() {
        return extendBaseEntity;
    }

    public void setExtendBaseEntity(String extendBaseEntity) {
        this.extendBaseEntity = extendBaseEntity;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public String getRequestMapPath() {
        return requestMapPath;
    }

    public void setRequestMapPath(String requestMapPath) {
        this.requestMapPath = requestMapPath;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public CodeGenerator addClass(Class<?> clazz) {
        this.clazzList.add(clazz);
        return this;
    }

    public void writeFile(File dir, String fileName, String content)
            throws Exception {
        File file = new File(dir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        Writer writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
        } finally {
            writer.close();
        }
    }

    /**
     * 配置生成文件存放位置，生成的表名
     *
     * @param path
     * @throws Exception
     */
    public void outPut(String path) throws Exception {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        GenConfig genConfig = new GenConfig();
        //加载配置
        genConfig.setServerName(serverName);
        genConfig.setTableName(tableName);
        genConfig.setRequestMapPath(requestMapPath);
        genConfig.setModelName(modelName);
        genConfig.setIdType(idType);
        genConfig.setExtendBaseEntity(extendBaseEntity);

        //根据模板生成代码
        MysqlDDLGenerator mysql = new MysqlDDLGenerator();
        MybatisXmlGenerator xmlGenerator = new MybatisXmlGenerator();
        MybatisXmlJoinGenerator xmlJoinGenerator = new MybatisXmlJoinGenerator();
        QueryGenerator query = new QueryGenerator();
        DaoGenerator dao = new DaoGenerator();
        ServiceGenerator service = new ServiceGenerator();
        ServiceImplGenerator serviceImpl = new ServiceImplGenerator();
        ControllerGenerator controller = new ControllerGenerator();
        ConsumerControllerGenerator consumerController = new ConsumerControllerGenerator();
        FeignClientWithFallBackFactoryGenerator withFallbackFactory = new FeignClientWithFallBackFactoryGenerator();
        FeignClientFallbackFactoryGenerator fallbackFactory = new FeignClientFallbackFactoryGenerator();
        FeignClientGenerator feignClien = new FeignClientGenerator();
        for (Class<?> clazz : this.clazzList) {
            EntityInfo entity = new EntityInfo(clazz, tableName);
            writeFile(dir, tableName + ".sql",
                    mysql.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "Query.java",
                    query.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "Dao.xml",
                    xmlGenerator.generateCode(entity, genConfig));
            writeFile(dir, "JOIN_" + entity.getClassName() + "Dao.xml",
                    xmlJoinGenerator.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "Dao.java",
                    dao.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "Service.java",
                    service.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "ServiceImpl.java",
                    serviceImpl.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "Controller.java",
                    controller.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "ConsumerController.java",
                    consumerController.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "FeignClientWithFallbackFactory.java",
                    withFallbackFactory.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "FeignClientFallbackFactory.java",
                    fallbackFactory.generateCode(entity, genConfig));
            writeFile(dir, entity.getClassName() + "FeignClient.java",
                    feignClien.generateCode(entity, genConfig));

        }
        Runtime.getRuntime().exec("cmd /c start " + path);
    }


}