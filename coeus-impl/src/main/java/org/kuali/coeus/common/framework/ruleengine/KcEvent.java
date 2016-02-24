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
package org.kuali.coeus.common.framework.ruleengine;

/**
 * KcEvents are used to pass data to KcBusinessRule classes declaring
 * KcEventMethod methods. Eventname is required as this is the key
 * to determine what rules to execute.
 */
public interface KcEvent {

	/**
	 * The events name that will be used to register rules and
	 * later determine which rules to be run. Should be namespaced in
	 * some fashion to avoid conflicts. Similar to KC-PD:saveDocument or
	 * KC-B:addPeriod
	 */
	public String getEventName();
}
