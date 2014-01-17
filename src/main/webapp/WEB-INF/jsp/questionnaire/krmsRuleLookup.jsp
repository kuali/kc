<%--
 Copyright 2005-2014 The Kuali Foundation

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
        
        <label>

       <input type="button" id = "lookupBtn" value="Rule lookup" onclick="window.location.href='${ConfigProperties.rice.server.url}/kr-krad/lookup?methodToCall=start&amp;dataObjectClassName=org.kuali.rice.krms.impl.repository.RuleBo&amp;returnLocation=${ConfigProperties.application.url}/krmsRuleLookup.do&returnFormKey=1&conversionFields=id:ruleId'" />
        
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
