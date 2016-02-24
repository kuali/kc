/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package co.kuali.coeus.sys.impl.monitor;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.config.TargetDatabase;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;

import javax.persistence.EntityManager;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * This is a copy of the {@link org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter} but using
 * the Java Melody PersistenceProvider.
 */
public class MonitoringEclipseLinkJpaVendorAdapter extends AbstractJpaVendorAdapter {
    private final PersistenceProvider persistenceProvider;
    {
        try {
            persistenceProvider = (PersistenceProvider) Class.forName("net.bull.javamelody.JpaPersistence").newInstance();
        } catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private final JpaDialect jpaDialect = new EclipseLinkJpaDialect();


    public PersistenceProvider getPersistenceProvider() {
        return this.persistenceProvider;
    }

    @Override
    public Map<String, Object> getJpaPropertyMap() {
        Map<String, Object> jpaProperties = new HashMap<String, Object>();

        if (getDatabasePlatform() != null) {
            jpaProperties.put(PersistenceUnitProperties.TARGET_DATABASE, getDatabasePlatform());
        }
        else if (getDatabase() != null) {
            String targetDatabase = determineTargetDatabaseName(getDatabase());
            if (targetDatabase != null) {
                jpaProperties.put(PersistenceUnitProperties.TARGET_DATABASE, targetDatabase);
            }
        }

        if (isGenerateDdl()) {
            jpaProperties.put(PersistenceUnitProperties.DDL_GENERATION,
                    PersistenceUnitProperties.CREATE_ONLY);
            jpaProperties.put(PersistenceUnitProperties.DDL_GENERATION_MODE,
                    PersistenceUnitProperties.DDL_DATABASE_GENERATION);
        }
        if (isShowSql()) {
            jpaProperties.put(PersistenceUnitProperties.LOGGING_LEVEL, Level.FINE.toString());
        }

        return jpaProperties;
    }

    /**
     * Determine the EclipseLink target database name for the given database.
     * @param database the specified database
     * @return the EclipseLink target database name, or {@code null} if none found
     */
    protected String determineTargetDatabaseName(Database database) {
        switch (database) {
            case DB2: return TargetDatabase.DB2;
            case DERBY: return TargetDatabase.Derby;
            case HSQL: return TargetDatabase.HSQL;
            case INFORMIX: return TargetDatabase.Informix;
            case MYSQL: return TargetDatabase.MySQL4;
            case ORACLE: return TargetDatabase.Oracle;
            case POSTGRESQL: return TargetDatabase.PostgreSQL;
            case SQL_SERVER: return TargetDatabase.SQLServer;
            case SYBASE: return TargetDatabase.Sybase;
            default: return null;
        }
    }

    @Override
    public JpaDialect getJpaDialect() {
        return this.jpaDialect;
    }

    @Override
    public Class<? extends EntityManager> getEntityManagerInterface() {
        return JpaEntityManager.class;
    }

}