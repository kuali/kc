<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

        
<kul:page showDocumentInfo="false"
          headerTitle="Negotiation Changes Lost Page"
          docTitle="Negotiation Changes Lost Page"
          transactionalDocument="false"
          htmlFormAction="maintenanceCoiDisclosureEvent"
          defaultMethodToCall="returnToPortal">
          
    <div class="topblurb">
        <div align="center">
	        <table cellpadding="10" cellspacing="0" border="0">
	            <tr>
	                <td>
	                    <div align="center">
	                        Changes to your Negotiation have been lost.  This is often due to use of the browser's back button.
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td>
	                    <div align="center">
	                        <input type="image" name="methodToCall.returnToPortal" title="Return to Portal" alt="Return to Portal" 
	                               src="${ConfigProperties.kra.externalizable.images.url}buttonsmall_returnToPortal.gif" class="tinybutton" />
	                    </div>
	                </td>
	            </tr>
	        </table>
	    </div>
    </div>
    
</kul:page>
