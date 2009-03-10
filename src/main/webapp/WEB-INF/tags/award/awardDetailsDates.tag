<%--
 Copyright 2006-2008 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />

<kul:tab tabTitle="Details & Dates" defaultOpen="false" tabErrorKey="document.award*">

<!-- Institution -->
<div class="tab-container" align="right">
	<h3>
		<span class="subhead-left">Institution</span>
		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.Award" altText="help"/></span>
	</h3>
</div>
<table cellpAdding="0" cellspacing="0" summary="">
  	<tr>
    	<th><div align="right">*Award ID:</div></th>
    	<td>${KualiForm.awardDocument.award.awardId}</td>
    	<th>
    		<div align="right">Account Type:</div>
		</th>
    	<td>
    		<select name="x1">
        		<option value="0">select</option>
        		<option value="1" selected>Regular</option>
        		<option value="2">Fabricated Equipment</option>
        		<option value="3">Draper Fellowship</option>
        		<option value="4">Core Grant Administration</option>
        		<option value="5">Gift</option>
        		<option value="6">Conversion Account</option>
        		<option value="7">Off-campus account</option>
        		<option value="8">SBIR</option>
        		<option value="9">STTR</option>
        		<option value="10">No Account</option>
        		<option value="11">Service Facilities</option>
     		</select>
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">*Version:</div>
      	</th>
    	<td>${KualiForm.awardDocument.award.sequenceNumber}</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.activityTypeCode}" /></div>
    	</th>
    	<td>
    		<kul:htmlControlAttribute property="awardDocument.award.activityTypeCode" attributeEntry="${awardAttributes.activityTypeCode}" />
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" /></div>
      	</th>
    	<td align="left" valign="middle">
    		<kul:htmlControlAttribute property="awardDocument.award.accountNumber" attributeEntry="${awardAttributes.accountNumber}" />
    	</td>
    	<th>
    		<div align="right">
        		<label for="label7"></label>
        		<label for="label">*Award Type:</label>
        		<label for="basis of payment"></label>
      		</div>
      	</th>
    	<td>
    		<select name="z1">
        		<option value="0">select</option>
        		<option value="1">Budget Office WBS </option>
        		<option value="2">Consortium Expenditures </option>
        		<option value="3">Consortium Membership</option>
        		<option value="4">Contract </option>
        		<option value="5">Cooperative Agreement </option>
        		<option value="6">Facilities Agreement</option>
        		<option value="7">Fellowship </option>
        		<option value="8">Gift </option>
        		<option value="9" selected>Grant </option>
        		<option value="10">Indefinite Delivery Contract </option>
        		<option value="11">NIH Training Grant </option>
        		<option value="12">Other Transaction Agreement </option>
        		<option value="13">Student Financial Aid</option>
      		</select>
      	</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">
        		<kul:htmlAttributeLabel attributeEntry="${awardAttributes.statusCode}" />
      		</div>
      	</th>
    	<td>
    		<kul:htmlControlAttribute property="awardDocument.award.statusCode" attributeEntry="${awardAttributes.statusCode}" />
      	</td>
    	<th>&nbsp;</th>
    	<td align="left" valign="middle">&nbsp;</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">
        		<kul:htmlAttributeLabel attributeEntry="${awardAttributes.title}" />
      		</div>
      	</th>
    	<td colspan="3">
        	<table style="border:none; width:100%;">
        		<tr>
            		<td style="border:none; width:100%;">
            			<kul:htmlControlAttribute property="awardDocument.award.title" attributeEntry="${awardAttributes.title}" />
                    	<kra:expandedTextArea textAreaFieldName="awardDocument.award.title" action="awardHome" textAreaLabel="${awardAttributes.title.label}" />
        			</td>
            	</tr>
        	</table>
    	</td>
  	</tr>
</table>

<!-- Sponsor -->
<div class="tab-container" align="right">
	<h3>
		<span class="subhead-left">Sponsor</span>
		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Sponsor" altText="help"/></span>
	</h3>
</div>
<table cellpadding="0" cellspacing="0" summary="">
    <tr>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorCode}" /></div>
        </th>
        <td>
        	<kul:htmlControlAttribute property="document.award.sponsorCode" attributeEntry="${awardAttributes.sponsorCode}" onblur="loadSponsorName('document.award.sponsorCode', 'sponsorName');" />
        	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.award.sponsorCode,sponsorName:document.award.sponsor.sponsorName" anchor="${tabKey}" />
            <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.sponsorCode:sponsorCode" anchor="${tabKey}" />
        	<div id="sponsorName.div" >
            	<c:if test="${!empty KualiForm.document.award.sponsorCode}">
            		<c:choose>
						<c:when test="${empty KualiForm.document.award.sponsor}">
	                    	<span style='color: red;'>not found</span>
	               		</c:when>
	                  	<c:otherwise>
							<c:out value="${KualiForm.document.award.sponsor.sponsorName}" />
						</c:otherwise>
					</c:choose>
            	</c:if>
			</div>
        </td>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.primeSponsorCode}" /></div>
        </th>
        <td>
        	<kul:htmlControlAttribute property="document.award.primeSponsorCode" attributeEntry="${awardAttributes.primeSponsorCode}" onblur="loadSponsorName('document.award.primeSponsorCode', 'primeSponsorName');" />
        	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.award.primeSponsorCode,sponsorName:document.award.primeSponsor.sponsorName" anchor="${tabKey}" />
            <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.primeSponsorCode:sponsorCode" anchor="${tabKey}" />
            <div id="primeSponsorName.div">
            	<c:if test="${!empty KualiForm.document.award.primeSponsorCode}">
            		<c:choose>
						<c:when test="${empty KualiForm.document.award.primeSponsor}">
	                    	<span style='color: red;'>not found</span>
	               		</c:when>
	                  	<c:otherwise>
							<c:out value="${KualiForm.document.award.primeSponsor.sponsorName}" />
						</c:otherwise>
					</c:choose>
            	</c:if>
			</div>
        </td>
    </tr>
    <tr>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorAwardNumber}" /></div>
        </th>
        <td>
        	<kul:htmlControlAttribute property="document.award.sponsorAwardNumber" attributeEntry="${awardAttributes.sponsorAwardNumber}" />
        </td>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.cfdaNumber}" /></div>
        </th>
        <td>
            <kul:htmlControlAttribute property="document.award.cfdaNumber" attributeEntry="${awardAttributes.cfdaNumber}" />
		</td>
    </tr>
    <tr>
        <th align="right">
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.modificationNumber}" /></div>
        </th>
        <td align="left" valign="middle">
            <kul:htmlControlAttribute property="document.award.modificationNumber" attributeEntry="${awardAttributes.modificationNumber}" />
        </td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.nsfCode}" />
        </th>
        <td align="left" valign="middle">
        	<kul:htmlControlAttribute property="document.award.nsfCode" attributeEntry="${awardAttributes.nsfCode}" />
        </td>
    </tr>
</table>

<!-- Sponsor Funding Transferred -->
<table cellpAdding="0" cellspacing="0" summary="">
	<tr>
        <td colspan="3" class="tab-subhead1">
        	Sponsor Funding Transferred
        </td>
    </tr>
    <tr>
        <th style="text-align:center;">&nbsp; </th>
        <th style="text-align:center;">
            <b>ID/Description</b>
        </th>
        <th style="text-align:center; width:60px;">
            <b>Action</b>
        </th>
    </tr>
    <tr>
        <th>
            <div align=right>
	            <b>Add:</b>
            </div>
        </th>
        <td class="infoline">
            <input name="sftid" type=text onchange="dataChanged()" value="" size="80" id="sftid"/>
            <input name="image23" type="image" class="nobord" src="../images/searchicon.gif" alt="lookup">
            <input name="image23" type="image" class="nobord" src="../images/book_open.png" alt="inquiry">
        </td>
        <td class="infoline" style="text-align:center;">
            <input name="image231" type="image" class="nobord" src="../images/tinybutton-add1.gif" alt="add">
        </td>
    </tr>
    <tr>
        <th>
            1
        </th>
        <td>
            000627 : Department of Health and Human Services
        </td>
        <td style="text-align:center;">
            <input name="image2311" type="image" class="nobord" src="../images/tinybutton-delete1.gif" alt="delete">
        </td>
    </tr>
    <tr>
        <th>
            2
        </th>
        <td>
            003839 : National Eye Institute
        </td>
        <td style="text-align:center;">
            <input name="image2312" type="image" class="nobord" src="../images/tinybutton-delete1.gif" alt="delete">
        </td>
    </tr>
</table>

<!-- Dates -->
<div class="tab-container">
	<h3>
		<span class="subhead-left">Dates</span>
	</h3>
</div>
<table cellpAdding="0" cellspacing="0" summary="">
	<tr>
		<th>
			<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.beginDate}" /></div>
        </th>
        <td align="left" valign="middle">
        	<kul:htmlControlAttribute property="document.award.beginDate" attributeEntry="${awardAttributes.beginDate}" datePicker="true" />
		</td>
        <th>
			<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardEffectiveDate}" /></div>
        </th>
        <td align="left" valign="middle">
        	<kul:htmlControlAttribute property="document.award.awardEffectiveDate" attributeEntry="${awardAttributes.awardEffectiveDate}" datePicker="true" />
        </td>
    </tr>
    <tr>
        <th>
        	<div align="right">
            	<label for="account type">*Project End Date:</label>
            </div>
        </th>
        <td align=left valign=middle>
        	<span>
            	<input name="ed" type="text" value="12/31/2013" size=12>
            </span>
            <span>
            	<a href="#" onClick="cal.select(document.forms['example'].date1,'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor4"> <img src="../images/cal.gif" alt="select date" width=16 height=16 align=absmiddle></a>
            </span>
        </td>
        <th>
        	<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardExecutionDate}" /></div>
        </th>
        <td align="left" valign="middle">
            <kul:htmlControlAttribute property="document.award.awardExecutionDate" attributeEntry="${awardAttributes.awardExecutionDate}" datePicker="true" />
        </td>
    </tr>
</table>

</kul:tab>
