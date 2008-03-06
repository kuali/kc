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
	htmlFormAction="budgetDistributionAndIncome"
	documentTypeName="BudgetDocument"
  	headerDispatch="save"
  	headerTabActive="distributionAndIncome"
  	extraTopButtons="${KualiForm.extraTopButtons}"
  	showTabButtons="true">

	<center>Under Construction Still</center>
	
	<div align="center">
		<kul:uncollapsable tabTitle="Select Budget Period:" tabErrorKey="projectIncome*">
	          <div align="center">
	            <table  cellpadding="0" cellspacing="0" class="grid" summary="">
	              <tr>
	                <th class="grid"><div align="right">Budget Period:</div></th>                
	                <td class="grid" >
	                	<html:select property="newBudgetPeriodNumber">
	                    	<html:option value="0">Select</html:option>  		                    	
	                    	<c:set var="budgetPeriods" value="${KualiForm.document.budgetPeriods}"/>
    		            	<html:options collection="budgetPeriods" property="budgetPeriod" labelProperty="label"/>
  			        	</html:select>
	                </td>
	              </tr>
	            </table>
	            <br>
	            <html:image property="methodToCall.updateBudgetPeriodView" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" title="Update View" alt="Update View" styleClass="tinybutton"/>
			</div>
		</kul:uncollapsable>
		
		<div style="padding-top: 3em;">
			<kul:tabTop tabTitle="Cost Sharing (#)" defaultOpen="true" tabErrorKey="budget.projectIncome*">
				<div class="tab-container" align="center">
			    	<div class="h2-container">
			    		<span class="subhead-left"><h2>Cost Sharing Distrbution List</h2></span>
			    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
			        </div>
			    </div>
			</kul:tabTop>
			
			<kul:tab tabTitle="Unrecovered F&A (#)" defaultOpen="true" tabErrorKey="budget.projectIncome*">
				<div class="tab-container" align="center">
			    	<div class="h2-container">
			    		<span class="subhead-left"><h2>Unrecovered F&A Distrbution List</h2></span>
			    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
			        </div>
			    </div>
			</kul:tab>
			
			
			<kra-b:projectIncome />
				
		</div>
	</div>

	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

</kul:documentPage>