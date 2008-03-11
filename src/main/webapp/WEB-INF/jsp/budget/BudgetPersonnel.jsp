<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="budgetPersonnel"
	documentTypeName="BudgetDocument"
  	headerDispatch="save"
  	headerTabActive="personnel">
  	
  	<kul:uncollapsable tabTitle="Add Budget Personnel" tabErrorKey="newBudgetPerson">
  		<div align="center">
            <table cellpadding="0" cellspacing="0" class="grid" summary="">
              	<tr>
                	<th class="grid"><div align="right">Person:</div></th>
                	<td nowrap class="grid">
                		<label>Employee Search</label>
                  		<label><kul:multipleValueLookup boClassName="org.kuali.kra.bo.Person" 
                        	lookedUpCollectionName="newBudgetPersons" /></label><br>
                        <label>Non-employee Search</label> 
                  		<label><kul:multipleValueLookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
                        	lookedUpCollectionName="newBudgetRolodexes" /></label>
                	</td>
              	</tr>
            </table>
        </div>
	</kul:uncollapsable>
	<br/>
	
	<kra-b:budgetPersonnel/>
	
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>
	
</kul:documentPage>