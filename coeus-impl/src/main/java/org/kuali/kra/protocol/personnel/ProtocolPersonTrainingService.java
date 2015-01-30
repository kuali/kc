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
package org.kuali.kra.protocol.personnel;

import java.util.List;




/**
 * This class represents service interface for protocol personnel
 * training. Each institution can customize this interface to deal
 * with person training.
 */
public interface ProtocolPersonTrainingService {

    /**
     * This method is to set trained flag for each person
     * This method is invoked while navigating to personnel page to set the trained flag for 
     * each person in the list.
     * Hook this service method to external / other training session as required. 
     * @param protocolPersons
     */
    public void updatePersonTrained(List<ProtocolPersonBase> protocolPersons);
    
    /**
     * This method is to set trained flag for a person
     * Set true / false
     * This method is invoked from isPersonTrained and while adding a new person to the list.
     * @param protocolPerson
     */
    public void setTrainedFlag(ProtocolPersonBase protocolPerson);
}
