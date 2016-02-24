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
package org.kuali.coeus.dc.common.db;

import java.sql.*;

public abstract class AbstractConnectionDaoService implements ConnectionDaoService {

    private String riceConnectionString;
    private String coeusConnectionString;
    private String riceUser;
    private String coeusUser;
    private String ricePassword;
    private String coeusPassword;

    private static final ThreadLocal<Connection> COEUS_CONNECTIONS = new ThreadLocal<>();
    private static final ThreadLocal<Connection> RICE_CONNECTIONS = new ThreadLocal<>();


    {
        try {
            Class.forName(getDriverClassName()).newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String getDriverClassName();

    @Override
    public Connection getCoeusConnection() {
        Connection c = COEUS_CONNECTIONS.get();
        if (c == null) {
            try {
                c = getConnection(getCoeusConnectionString(), getCoeusUser(), getCoeusPassword());
                c.setAutoCommit(false);
            } catch (SQLException e) {
            	e.printStackTrace();
                throw new RuntimeException(e);
            }

            COEUS_CONNECTIONS.set(c);
        }

        return c;
    }

	@Override
    public Connection getRiceConnection() {
        Connection c = RICE_CONNECTIONS.get();
        if (c == null) {
            try {
                c = getConnection(getRiceConnectionString(),getRiceUser(),getRicePassword());
                c.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            RICE_CONNECTIONS.set(c);
        }

        return c;
    }

    private Connection getConnection(String con, String user, String pass) throws SQLException {
        if (user != null && !user.trim().equals("")) {
            return DriverManager.getConnection(con, user, pass);
        } else {
            return DriverManager.getConnection(con);
        }
    }

	public String getRiceConnectionString() {
        return riceConnectionString;
    }

    public void setRiceConnectionString(String riceConnectionString) {
        this.riceConnectionString = riceConnectionString;
    }

    public String getCoeusConnectionString() {
        return coeusConnectionString;
    }

    public void setCoeusConnectionString(String coeusConnectionString) {
        this.coeusConnectionString = coeusConnectionString;
    }

    private String getRiceUser() {
		return riceUser;
	}

	public void setRiceUser(String riceUser) {
		this.riceUser = riceUser;
	}

	private String getCoeusUser() {
		return coeusUser;
	}
	public void setCoeusUser(String coeusUser) {
		this.coeusUser = coeusUser;
	}
	private String getRicePassword() {
		return ricePassword;
	}
	public void setRicePassword(String ricePassword) {
		this.ricePassword = ricePassword;
	}
    private String getCoeusPassword() {
		return coeusPassword;
	}
	public void setCoeusPassword(String coeusPassword) {
		this.coeusPassword = coeusPassword;
	}
}
