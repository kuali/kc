package org.kuali.rice.krad.data.platform;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import javax.sql.DataSource;

public class MaxValueIncrementerFactoryBean implements FactoryBean<DataFieldMaxValueIncrementer>, InitializingBean {

    private DataSource dataSource;
    private String incrementerName;

    @Override
    public DataFieldMaxValueIncrementer getObject() throws Exception {
        return MaxValueIncrementerFactory.getIncrementer(dataSource, incrementerName);
    }

    @Override
    public Class<DataFieldMaxValueIncrementer> getObjectType() {
        return DataFieldMaxValueIncrementer.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new IllegalStateException("Property 'dataSource' is required");
        }

        if (StringUtils.isBlank(incrementerName)) {
            throw new IllegalStateException("Property 'incrementerName' is required");
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getIncrementerName() {
        return incrementerName;
    }

    public void setIncrementerName(String incrementerName) {
        this.incrementerName = incrementerName;
    }
}
