/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.persistence;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** this class is to be used for diagnostic purposes in regards to tracking currently opened and closed connections. */
public class TrackingConnection implements Connection {

    private static final Log LOG = LogFactory.getLog(TrackingConnection.class);
    
    private final Connection connection;

    /** wraps a connection. */
    public TrackingConnection(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("the connection was null");
        }
        
        try {
            ConnectionTracker.addConnection(connection);
        } catch (Throwable t) {
            //do not want to fail just for tracking logic as this could cause connection leaks
            LOG.error("could not add connection for tracking", t);
        } finally {
            this.connection = connection;    
        }
    }
    
//*******************************DELEGATE METHODS************************************************************
    
   @Override
    public void clearWarnings() throws SQLException {
        this.connection.clearWarnings();
    }

   @Override
    public void close() throws SQLException {
        try {
            ConnectionTracker.removeConnection(this.connection);
        } catch (Throwable t) {
            //do not want to fail just for tracking logic as this could cause connection leaks
            LOG.error("could not remove connection from tracking", t);
        } finally {
            this.connection.close();
        }
    }

   @Override
    public void commit() throws SQLException {
        this.connection.commit();
    }

   @Override
    public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

   @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

   @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.connection.createStatement(resultSetType, resultSetConcurrency);
    }

   @Override
    public boolean getAutoCommit() throws SQLException {
        return this.connection.getAutoCommit();
    }

   @Override
    public String getCatalog() throws SQLException {
        return this.connection.getCatalog();
    }

   @Override
    public int getHoldability() throws SQLException {
        return this.connection.getHoldability();
    }

   @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return this.connection.getMetaData();
    }

   @Override
    public int getTransactionIsolation() throws SQLException {
        return this.connection.getTransactionIsolation();
    }

   @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.connection.getTypeMap();
    }

   @Override
    public SQLWarning getWarnings() throws SQLException {
        return this.connection.getWarnings();
    }

   @Override
    public boolean isClosed() throws SQLException {
        return this.connection.isClosed();
    }

   @Override
    public boolean isReadOnly() throws SQLException {
        return this.connection.isReadOnly();
    }

   @Override
    public String nativeSQL(String sql) throws SQLException {
        return this.connection.nativeSQL(sql);
    }

   @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

   @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

   @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return this.connection.prepareCall(sql);
    }

   @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

   @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

   @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return this.connection.prepareStatement(sql, autoGeneratedKeys);
    }

   @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return this.connection.prepareStatement(sql, columnIndexes);
    }

   @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return this.connection.prepareStatement(sql, columnNames);
    }

   @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }

   @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        this.connection.releaseSavepoint(savepoint);
    }

   @Override
    public void rollback() throws SQLException {
        this.connection.rollback();
    }

   @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        this.connection.rollback(savepoint);
    }

   @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        this.connection.setAutoCommit(autoCommit);
    }

   @Override
    public void setCatalog(String catalog) throws SQLException {
        this.connection.setCatalog(catalog);
    }

   @Override
    public void setHoldability(int holdability) throws SQLException {
        this.connection.setHoldability(holdability);
    }

   @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        this.connection.setReadOnly(readOnly);
    }

   @Override
    public Savepoint setSavepoint() throws SQLException {
        return this.connection.setSavepoint();
    }

   @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return this.connection.setSavepoint(name);
    }

   @Override
    public void setTransactionIsolation(int level) throws SQLException {
        this.connection.setTransactionIsolation(level);
    }

   @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        this.connection.setTypeMap(map);
    }

    @Override
    public Clob createClob() throws SQLException {
        return this.connection.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return this.connection.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return this.connection.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return this.connection.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return this.connection.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        this.connection.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        this.connection.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return this.connection.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return this.connection.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return this.connection.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return this.connection.createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        this.connection.setSchema(schema);  
    }

    @Override
    public String getSchema() throws SQLException {
        return this.connection.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        this.connection.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        this.connection.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return this.connection.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.connection.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.connection.isWrapperFor(iface);
    }

    /**
     * This class holds data related to open Connections.  The data is kept on a per thread basis.
     */
    private static final class ConnectionTracker {

        private static final Log LOG = LogFactory.getLog(ConnectionTracker.class);

        /**
         * data structure format:
         *
         * each thread contains a map
         *   map key = a string identifier for a connection (DB user name + connection url)
         *   map value = a Collection connection info object's which describe the connection
         *     CollectionInfo objects = the physical connection object along with the stacktrace that requested that connection
         */
        private static final ThreadLocal<Map<String, Collection<ConnectionInfo>>> OPEN_CONNECTIONS = new ThreadLocal<Map<String, Collection<ConnectionInfo>>>() {
            @Override
            protected synchronized Map<String, Collection<ConnectionInfo>> initialValue() {
                return new HashMap<String, Collection<ConnectionInfo>>();
            }
        };

        private ConnectionTracker() {
            throw new UnsupportedOperationException("do not call");
        }

        /**
         * Adds a connection to the connection count. Null connections are ignored.
         * Call this method when a connection is first established.
         * @param con the connection to add
         */
        public static synchronized void addConnection(Connection con) {
            if (con == null) {
                return;
            }

            final String key = getConnectionKey(con);

            if (key != null) {
                LOG.debug("Opened a connection to " + key + " by thread " + Thread.currentThread().getName());
                logMultipleConnections(key);
                add(key, con);
            } else {
                LOG.warn("DB Connection key is null.  will not track connection usage.");
            }
        }

        /**
         * This method adds a new ConnectionInfo for a given Connection &amp; key.  If a Connection
         * already exists then that connection is removed from tracking and the new ConnectionInfo is created.
         * This handles Connection reuse.
         *
         * @param key the connection key
         * @param con the connection to add
         */
        private static synchronized void add(String key, Connection con) {
            final Collection<ConnectionInfo> conInfos = getConnectionInfos(key);
            for (Iterator<ConnectionInfo> i = conInfos.iterator(); i.hasNext();) {
                final ConnectionInfo conInfo = i.next();
                if (conInfo.getConnection() == con) {
                    i.remove();
                }
            }

            conInfos.add(new ConnectionInfo(con, getStackTrace()));
            OPEN_CONNECTIONS.get().put(key, conInfos);
        }

        /**
         * Removes a connection from the connection count. Null connections are ignored.
         * Call this method when a connection is closed.
         * @param con the connection to remove
         */
        public static synchronized void removeConnection(Connection con) {
            if (con == null) {
                return;
            }

            final String key = getConnectionKey(con);
            if (key != null) {
                LOG.debug("Closed a connection to " + key + " by thread " + Thread.currentThread().getName());
                remove(key, con);
            }
        }

        /**
         * Removes all ConnectionInfos from tracking where for the key &amp; passed in Connection
         * @param key the connection key
         * @param con the connection to remove
         */
        private static synchronized void remove(String key, Connection con) {
            final Collection<ConnectionInfo> conInfos = getConnectionInfos(key);
            for (Iterator<ConnectionInfo> i = conInfos.iterator(); i.hasNext();) {
                final ConnectionInfo conInfo = i.next();
                if (conInfo.getConnection() == con) {
                    i.remove();
                }
            }

            OPEN_CONNECTIONS.get().put(key, conInfos);
        }

        /**
         * returns the a Collection of ConnectionInfos.  Will never return null.
         * If the Collection retrieved is null then a new Collection is returned.
         *
         * @param key the key to lookup the ConnectionInfos from.
         */
        private static synchronized Collection<ConnectionInfo> getConnectionInfos(String key) {
            final Collection<ConnectionInfo> conInfos = OPEN_CONNECTIONS.get().get(key);
            return conInfos != null ? conInfos : new ArrayList<ConnectionInfo>();
        }

        /**
         * gets the connection key.  return null if it cannot be determined.
         * @return the key or null
         */
        private static String getConnectionKey(Connection con) {
            String uName = null;
            String url = null;
            try {
                uName = con.getMetaData().getUserName();
                url = con.getMetaData().getURL();
            } catch (SQLException e) {
                LOG.error("unable to get DB url", e);
            }

            return uName != null && url != null ? uName + " @ " + url : null;
        }

        /**
         * logs a message related to multiple connections being open.
         * @param key the connection key
         */
        private static synchronized void logMultipleConnections(String key) {
            final Collection<ConnectionInfo> conInfos = OPEN_CONNECTIONS.get().get(key);
            if (conInfos != null && !conInfos.isEmpty()) {
                final StringBuilder stacks = new StringBuilder();

                for (ConnectionInfo conInfo : conInfos) {
                    stacks.append(conInfo.getEstablishedStack());
                    stacks.append("\n\n");
                }

                final StringBuilder msg = new StringBuilder();
                msg.append("There are ");
                msg.append(conInfos.size());
                msg.append(" connection(s) on this thread ");
                msg.append(Thread.currentThread().getName());
                msg.append(" to ");
                msg.append(key);
                msg.append(" that have not been closed.");
                msg.append(" The following stacktraces show where these connections were established: \n");
                msg.append(stacks);

                LOG.debug(msg);
            }
        }

        /**
         * Gets the current stacktrace as a String.
         * @return the stacktrace
         */
        private static String getStackTrace() {
            final StringBuilder stack = new StringBuilder();
            for (final StackTraceElement elem : Thread.currentThread().getStackTrace()) {
                stack.append("\tat ");
                stack.append(elem);
                stack.append(SystemUtils.LINE_SEPARATOR);
            }
            return stack.toString();
        }

        /** single class that holds a connection and the stack when the connection was established. */
        private static final class ConnectionInfo {
            private final Connection connection;
            private final String establishedStack;

            /** creates an object with the connection &amp; establisher. */
            private ConnectionInfo(Connection connection, String establishedStack) {
                this.connection = connection;
                this.establishedStack = establishedStack;
            }

            /**
             * Gets the connection.
             * @return the connection.
             */
            public Connection getConnection() {
                return this.connection;
            }

            /**
             * Gets the established stack.
             * @return the established stack.
             */
            public String getEstablishedStack() {
                return this.establishedStack;
            }
        }
    }
}
