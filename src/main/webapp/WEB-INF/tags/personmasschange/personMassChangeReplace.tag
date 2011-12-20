<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="attributes" value="${DataDictionary.PersonMassChange.attributes}" />

<kul:tab tabTitle="Replace Person" defaultOpen="true" tabErrorKey="document.personMassChange*">
    
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Replace the Person...</span>
        </h3>
        
        <table id="person-mass-change-replacee-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <td align="left" valign="middle"><div align="center">
                    <table cellpadding="0" cellspacing="0" summary="" border="0" style="border: medium none;">
                        <tbody>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
                                                fieldConversions="personId:document.personMassChange.replaceePersonId" />
                                </td>
                            </tr>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Non-Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
                                                fieldConversions="rolodexId:document.personMassChange.replaceeRolodexId" />
                                </td>
                            </tr>
                         </tbody>
                    </table>
                    <div id="notificationRecipientFullName" align="left">
                        <kul:htmlControlAttribute property="document.personMassChange.replaceeFullName" 
                                                  attributeEntry="${attributes.replaceeFullName}" 
                                                  readOnly="true" />
                    </div>
                </div></td>
            </tr>
        </table>
    </div>
    
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">With...</span>
        </h3>
        
        <table id="person-mass-change-replacee-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <td align="left" valign="middle"><div align="center">
                    <table cellpadding="0" cellspacing="0" summary="" border="0" style="border: medium none;">
                        <tbody>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
                                                fieldConversions="personId:document.personMassChange.replacerPersonId" />
                                </td>
                            </tr>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Non-Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
                                                fieldConversions="rolodexId:document.personMassChange.replacerRolodexId" />
                                </td>
                            </tr>
                         </tbody>
                    </table>
                    <div id="notificationRecipientFullName" align="left">
                        <kul:htmlControlAttribute property="document.personMassChange.replacerFullName" 
                                                  attributeEntry="${attributes.replacerFullName}" 
                                                  readOnly="true" />
                    </div>
                </div></td>
            </tr>
        </table>
    </div>
    
</kul:tab>