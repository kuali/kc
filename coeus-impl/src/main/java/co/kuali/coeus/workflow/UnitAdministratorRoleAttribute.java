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
package co.kuali.coeus.workflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.api.identity.Id;
import org.kuali.rice.kew.api.identity.PrincipalId;
import org.kuali.rice.kew.api.rule.RoleName;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.routeheader.DocumentContent;
import org.kuali.rice.kew.rule.GenericRoleAttribute;
import org.kuali.rice.kew.rule.QualifiedRoleName;

@SuppressWarnings("unchecked")
public class UnitAdministratorRoleAttribute extends GenericRoleAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2187135270043141363L;

	private static final Log LOG = LogFactory.getLog(UnitAdministratorRoleAttribute.class);
	private static final String HOMEUNIT = "homeUnit";
	//private static final String UNITS = "units";
	
	// Default Values 
	private boolean getUnits = true;
	private String roleName = "Department Head";
	private String unitAdministratorCode = "3";
	
	UnitService unitService;
	
	public List<String> getQualifiedRoleNames(String roleName,
			DocumentContent documentContent) {

		// RoleName!Unit Administrator Code!TRUE OF PARENT
		if (roleName != null) {
			String[] info = roleName.split("!");
			
			if (info.length != 3) {
				LOG.warn("Not enough parameters in the roleName.");
			} else {
				this.roleName = info[0];
				this.unitAdministratorCode = info[1];
				if (info[2].toUpperCase().equals("FALSE")) {
					getUnits = false;
				}
			}
		}
		
		List<String> qualifiedRoleNames = new ArrayList<String>();
		qualifiedRoleNames.add(this.roleName);
	
		return qualifiedRoleNames;
	}
	
	public List<RoleName> getRoleNames() {
		RoleName role = RoleName.Builder.create(UnitAdministratorRoleAttribute.class.getName(), roleName, roleName).build();
		return Collections.singletonList(role);
	}
	
	@Override
	public Map<String, String> getProperties() {
		// intentionally unimplemented...not intending on using this attribute
		// client-side
		return null;
	}
	
    protected UnitService getUnitService() {
    	if (this.unitService == null) {
    		this.unitService = KcServiceLocator.getService(UnitService.class);
    	}
    	
        return unitService;
    }
    
    @Override
    protected List<Id> resolveRecipients(RouteContext routeContext, QualifiedRoleName qualifiedRoleName) {
        List<Id> members = new ArrayList<Id>();
        Collection<Element> personnels = retrieveKeyPersonnel(routeContext);
        
        for(Element keyPerson : personnels) {
            if (getUnits) {
               Collection<Element> units = XmlHelper.findElements(keyPerson, ProposalPersonUnit.class.getName());
               for(Element unitElement : units) {
                   Unit unit  = getUnitService().getUnit(unitElement.getChildText("unitNumber"));
                   List<UnitAdministrator> unitAdministrators = getUnitService().retrieveUnitAdministratorsByUnitNumber(unit.getUnitNumber());
                   for ( UnitAdministrator unitAdministrator : unitAdministrators ) {
                       if (StringUtils.isNotBlank(unitAdministrator.getPersonId()) && unitAdministrator.getUnitAdministratorType().getCode().equals(unitAdministratorCode)) {
                           Id personId = new PrincipalId(unitAdministrator.getPersonId());
                           
                           if (!members.contains(personId)) {
                               members.add(personId);
                           }
                       }
                   }
               }
            } else {
                Collection<Element> units = XmlHelper.findElements(keyPerson, ProposalPersonUnit.class.getName());
                for(Element unitElement : units) {
                    Unit unit  = getUnitService().getUnit(unitElement.getChildText("unitNumber"));
                    Unit parentUnit = unit.getParentUnit();
                    if(parentUnit != null){
                        List<UnitAdministrator> unitAdministrators = getUnitService().retrieveUnitAdministratorsByUnitNumber(parentUnit.getUnitNumber());
                        for ( UnitAdministrator unitAdministrator : unitAdministrators ) {
                            if (StringUtils.isNotBlank(unitAdministrator.getPersonId()) && unitAdministrator.getUnitAdministratorType().getCode().equals(unitAdministratorCode)) {
                                Id personId = new PrincipalId(unitAdministrator.getPersonId());
                                
                                if (!members.contains(personId)) {
                                    members.add(personId);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return members;
    }
	
	protected Collection<Element> retrieveKeyPersonnel(RouteContext context) {
	    Document document = XmlHelper.buildJDocument(context.getDocumentContent().getDocument());
	   
	    Collection<Element> personnels = XmlHelper.findElements(document.getRootElement(), ProposalPerson.class.getName());
	    return personnels;
	}
	
}
