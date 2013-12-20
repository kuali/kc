<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="activeFlag" required="true" description="whether this is for active FE or inactive FE" %>
    <c:choose>
        <c:when test="${activeFlag == 'active'}">
            <c:set var="tableid" value="activeEntities-table" />                                           
            <c:set var="activeheader" value="Active" />                                           
            <c:set var="financialEntities" value="${KualiForm.financialEntityHelper.activeFinancialEntities}" />                                           
        </c:when>
        <c:otherwise>
            <c:set var="tableid" value="inActiveEntities-table" />                                           
            <c:set var="activeheader" value="Inactive" />                                           
            <c:set var="financialEntities" value="${KualiForm.financialEntityHelper.inactiveFinancialEntities}" />                                           
        </c:otherwise>
    </c:choose>

        <table cellpadding=0 cellspacing=0 summary=""> 
            <tr> 
                <td nowrap class="subhead"  width="5%" > &nbsp </td>
                <td nowrap class="subhead" width="25%" >${activeheader} Entities</td> 
                <td nowrap class="subhead" width="35%" >Sponsor</td> 
                <td nowrap class="subhead" width="15%" >Last Update</td> 
                <td nowrap class="subhead" width="20%" ><div align="center">Actions</div></td> 
            </tr> 

            <%-- Header --%>

            <tr>
                <td colspan="5" style="padding:0px;">
                        
                    <div>
                        <table id="${tableid}" cellpadding=0 cellspacing="0"  style="border-collapse:collapse;" class="tablesorter">
                            <thead>
                                <tr>
                                    <th width="5%" />
                                    <th width="25%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                    <th width="35%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                    <th width="15%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                    <th class="{sorter: false}" width="20%"> </th>
                                </tr>
                            </thead>            
                            <tbody>         

                                <kra-coi:financialEntity  financialEntityList="${financialEntities}" activeFlag="${activeFlag}" />
                            </tbody>     
                        </table>
                    </div>
                </td>
            </tr>                           
        </table>
