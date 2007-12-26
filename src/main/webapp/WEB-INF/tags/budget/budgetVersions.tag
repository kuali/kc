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

<c:set var="budgetAttributes" value="${DataDictionary.BudgetDocument.attributes}" />

<kul:tabTop tabTitle="Budget Versions (date)" defaultOpen="true" >
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Budget Versions</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        <table cellpadding="0" cellspacing="0" summary="Budget Versions">
			<tr>
				<th scope="row">&nbsp;</th>
				<th><div align="left">*Name:</div></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.versionNumber}" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalDirectCost}" /></th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.totalIndirectCost}" /></th>
				<th>Total:</th>
				<th>Status:</th>
				<th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.finalVersionFlag}" /></th>
				<th><div align="center">Action</div></th>
			</tr>
			<tr>
            	<th width="50" align="right" scope="row"><div align="right">Add:</div></th>
            	<td class="infoline"><label><input name="name" type="text" id="name" size="16"></label></td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">&nbsp;</td>
	            <td class="infoline">
            		<div align=center>
            			<html:image property="methodToCall.addBudgetVersion" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
				</td>
          	</tr>
          	<c:forEach var="budgetVersion" items="${KualiForm.document.budgetVersionOverviews}" varStatus="status">
          		<tr>
           			<td align="right" class="tab-subhead1" scope="row"><div align="center"><img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show/hide this panel" width=45 height=15 border=0 id="F6" vspace=0></a></div></td>
           			<td class="tab-subhead1">${budgetVersion.documentDescription}</td>
	            	<td class="tab-subhead1"><div align="center">${budgetVersion.budgetVersionNumber}</div></td>
		            <td class="tab-subhead1"><div align="right">${budgetVersion.totalDirectCost}</div></td>
		            <td class="tab-subhead1"><div align="right">${budgetVersion.totalIndirectCost}</div></td>
		            <td class="tab-subhead1"><div align="right">${budgetVersion.totalCost}</div></td>
		            <td class="tab-subhead1">
		            	<div align="center">
			              <select onchange="dataChanged()" name="activityType">
			                <option>select</option>
			                <option selected>incomplete</option>
			                <option>complete</option>
			                <option>none</option>
			              </select>
		            	</div>
            		</td>
	            	<td class="tab-subhead1">
	            		<div align="center">
	              			<input name="radio" type="radio" class="radio" id="final2" value="final" checked>
	            		</div>
	            	</td>
           			<td nowrap class="tab-subhead1">
           				<div align=center>
           					<html:image property="methodToCall.openBudgetVersion.line${status.index}.x" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-open.gif' alt="open budget" />
           					<img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-copy2.gif" alt="copy budget" width=40 height=15 hspace=3 vspace=0 border=0>
           				</div>
           			</td>
         		</tr>
         		<tbody id="G6">
           			<tr>
              			<th align="right" scope="row">&nbsp;</th>
              			<td colspan="8" style="padding:0px; border-left:none">
              				<table cellpadding="0" cellspacing="0" summary="" style="width:100%;">
                				<tbody id="G1" style="display: nonee;">
                  					<tr>
		                    			<th width="1%" nowrap><div align="right">Residual Funds:</div></th>
		                    			<td align="left" width="12%">&nbsp;${budgetVersion.residualFunds}</td>
		                    			<th width="40%" nowrap><div align="right">OH Rate Type:</div></th>
		                    			<td align="left" width="99%">${budgetVersion.ohRateTypeCode}</td>
		                  			</tr>
		                  			<tr>
		                    			<th nowrap><div align="right">Cost Sharing:</div></th>
		                    			<td align="left">${budgetVersion.costSharingAmount}</td>
		                    			<th nowrap><div align="right">Last Updated:</div></th>
		                    			<td align="left">${budgetVersion.updateTimestamp}</td>
		                  			</tr>
					                <tr>
					                    <th nowrap><div align="right">Unrecovered F&amp;A:</div></th>
					                    <td align="left">${budgetVersion.underrecoveryAmount}</td>
					                    <th nowrap><div align="right">Last Updated By:</div></th>
					                    <td align="left">${budgetVersion.updateUser}</td>
					                </tr>
	                  				<tr>
	                    				<th nowrap><div align="right">Comments:</div></th>
	                    				<td colspan="3" align="left">${budgetVersion.comments}</td>
	                  				</tr>
               					</tbody>
             				</table>
             			</td>
           			</tr>
         		</tbody>
          	</c:forEach>
        </table>
	</div>
</kul:tabTop>
<kul:panelFooter />

<%--
<table width="100%" cellpadding="0" cellspacing="0">
  <tr>
    <td class="column-left"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20" height="20"></td>
    <td><div id="workarea" >
      <table width="100%" cellpadding="0"  cellspacing="0" class="tab" summary="">
        <tr>
          <td class="tabtable1-left"><img src="${ConfigProperties.kr.externalizable.images.url}tab-topleft.gif" alt="" width="12" height="29" align="absmiddle">Budget Versions (7/23/07 - 7/23/09)</td>
          <td class="tabtable1-mid">&nbsp;</td>
          <td class="tabtable1-right"><img src="${ConfigProperties.kr.externalizable.images.url}tab-topright.gif" alt="" width="12" height="29" align="absmiddle"></td>
        </tr>
      </table>
      <div class="tab-container" align="center" id="G01" style="display: nonee;">
        <div class="tab-container-error">
          <div class="left-errmsg-tab"> </div>
        </div>
        <div class="h2-container"> <span class="subhead-left">
          <h2>Budget Versions</h2>
        </span><span class="subhead-right"> <a href="#"> <img src="${ConfigProperties.kr.externalizable.images.url}my_cp_inf.gif" alt="help" width="15" height="14" border="0" align="absmiddle" onClick="MM_openBrWindow('final/help-pop.html','','scrollbars=yes,resizable=yes,width=500,height=500')"></a></span> </div>
        <table cellpadding=0 cellspacing="0"  summary="">
          <tr>
            <th scope="row">&nbsp;</th>
            <th><div align="left">*Name</div></th>
            <th><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.versionNumber}"/></th>
            <th>Direct Cost</th>
            <th>Indirect Cost</th>
            <th>Total</th>
            <th>Status</th>
            <th>Final</th>
            <th><div align="center">Action</div></th>
          </tr>
          <tr>
            <th width="50" align="right" scope="row"><div align="right">Add:</div></th>
            <td class="infoline"><label>
              <input name="name" type="text" id="name" size="16">
            </label></td>
            <td class="infoline">&nbsp;</td>
            <td class="infoline">&nbsp;</td>
            <td class="infoline">&nbsp;</td>
            <td class="infoline">&nbsp;</td>
            <td class="infoline">&nbsp;</td>
            <td class="infoline">&nbsp;</td>
            <td class="infoline">
            	<div align=center>
            		<html:image property="methodToCall.addBudgetVersion" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
				</div>
			</td>
          </tr>
          <c:forEach var="budgetVersion" items="${KualiForm.document.budgetVersionOverviews}" varStatus="status">
            <kul:innerTab parentTab="Proposal Attachments" defaultOpen="false" tabDescription="${narrType} - ${narrStatus}" tabTitle="${status.index+1}. ${narrType} - ${narrStatus}">
          	<tr>
            	<td align="right" class="tab-subhead1" scope="row"><div align="center"><a href="#" id="A6" onclick="rend(this, false)"><img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" alt="show/hide this panel" width=45 height=15 border=0 id="F6" vspace=0></a></div></td>
            	<td class="tab-subhead1">${budgetVersion.documentDescription}</td>
            	<td class="tab-subhead1"><div align="center">${budgetVersion.budgetVersionNumber}</div></td>
	            <td class="tab-subhead1"><div align="right">${budgetVersion.totalDirectCost}</div></td>
	            <td class="tab-subhead1"><div align="right">${budgetVersion.totalIndirectCost}</div></td>
	            <td class="tab-subhead1"><div align="right">${budgetVersion.totalCost}</div></td>
	            <td class="tab-subhead1">
	            	<div align="center">
		              <select onchange="dataChanged()" name="activityType">
		                <option>select</option>
		                <option selected>incomplete</option>
		                <option>complete</option>
		                <option>none</option>
		              </select>
	            	</div>
	            </td>
            	<td class="tab-subhead1">
            		<div align="center">
              			<input name="radio" type="radio" class="radio" id="final2" value="final" checked>
            		</div>
            	</td>
            	<td nowrap class="tab-subhead1"><div align=center> <a href="bud-summary1.html"><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-open.gif" alt="open budget" width=38 height=15 hspace=3 vspace=0 border=0></a><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-copy2.gif" alt="copy budget" width=40 height=15 hspace=3 vspace=0 border=0></div></td>
          	</tr>
          	<tbody id="G6" style="display: none;">
            	<tr>
	              	<th align="right" scope="row">&nbsp;</th>
	              	<td colspan="8" style="padding:0px; border-left:none">
	              		<table cellpadding=0 cellspacing="0" summary="" style="width:100%;">
	                		<tbody id="G1" style="display: nonee;">
	                  			<tr>
	                    			<th><div align="right">Residual Funds:</div></th>
	                    			<td align="left">&nbsp;${budgetVersion.residualFunds}</td>
	                    			<th><div align="right">OH Rate Type:</div></th>
	                    			<td align="left">${budgetVersion.ohRateTypeCode}</td>
	                  			</tr>
	                  			<tr>
	                    			<th><div align="right">Cost Sharing:</div></th>
	                    			<td align="left">${budgetVersion.costSharingAmount}</td>
	                    			<th><div align="right">Last Updated:</div></th>
	                    			<td align="left">${budgetVersion.updateTimestamp}</td>
	                  			</tr>
				                <tr>
				                    <th><div align="right">Unrecovered F&amp;A:</div></th>
				                    <td align="left">${budgetVersion.underrecoveryAmount}</td>
				                    <th><div align="right">Last Updated By:</div></th>
				                    <td align="left">${budgetVersion.updateUser}</td>
				                </tr>
                  				<tr>
                    				<th><div align="right">Comments:</div></th>
                    				<td colspan="3" align="left">${budgetVersion.comments}</td>
                  				</tr>
                			</tbody>
              			</table>
              		</td>
            	</tr>
          </tbody>
          </kul:innerTab>
          </c:forEach>
        </table>
      </div>
      <!-- TAB -->
      <!-- TAB -->
      <!-- TAB -->
      <!-- TAB -->
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
        <tr>
          <td align="left" class="footer"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
          <td align="right" class="footer-right"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
        </tr>
      </table>
      <div align="right"><br>
        * required</div>
    </div>

    <td class="column-right"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20" height="20"></td>
  </tr>
</table>
 --%>

