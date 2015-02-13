package co.kuali.coeus.data.migration.custom;

import javax.sql.DataSource;

public interface RiceDataSourceAware {
    DataSource getRiceDataSource();
    void setRiceDataSource(DataSource riceDataSource);
}
