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

<html:form styleId="kualiForm" method="post"
    action="/krmsRuleLookup.do" enctype=""
    onsubmit="return hasFormAlreadyBeenSubmitted();"> 
    
    <input type="hidden" id="methodToCall"
        name="methodToCall" value="${KrmsRuleLookupForm.methodToCall}"/>
    <input type="hidden" id="fieldId"
        name="fieldId" value="${KrmsRuleLookupForm.fieldId}"/>
    <input type="hidden" id="ruleId"
        name="ruleId" value="${KrmsRuleLookupForm.ruleId}"/>
    <input type="hidden" id="anchor"
        name="anchor" value="${KrmsRuleLookupForm.anchor}"/>
	<kul:csrf />        
        <label>

       <input type="button" id = "lookupBtn" value="Rule lookup" onclick="window.location.href='${ConfigProperties.rice.server.url}/kr-krad/lookup?methodToCall=start&amp;dataObjectClassName=org.kuali.rice.krms.impl.repository.RuleBo&amp;returnLocation=${ConfigProperties.application.url}/krmsRuleLookup.do&returnFormKey=1&conversionFields=id:ruleId&returnByScript=false&returnTarget=_top'" />
        
            </label><br>
            
            
            <p><a href="javascript:returnRule();window.close();"><b>return data</b></a> <a href="javascript:window.close()">Close</a></p> 
            
            <script type="text/javascript">
       function hasFormAlreadyBeenSubmitted() {
       //    return false;
       }
            
              
                 function returnRule() {
                        var newRuleId = document.getElementById("ruleId").value;
                        if (newRuleId != '') {
	                        var fieldId = document.getElementById("fieldId").value
	                        window.opener.returnRule(newRuleId,fieldId);
                        }
                 
                 }

                 var lookupBtn=document.getElementById("lookupBtn");
                 if (document.getElementById("methodToCall").value != "refresh") {
                	 lookupBtn.click();
                 } else {
                    returnRule();
                    window.close();
                 }
                 
           //      });
            </script>
</html:form>
