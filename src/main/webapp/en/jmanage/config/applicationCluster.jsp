<!--    /config/applicationCluster.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.core.config.ApplicationConfig"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>

<script type="text/javascript" language="Javascript1.1">
    function add(){
        var availableList =
            document.applicationClusterForm.standAloneApplicationIds;
        var selectedList =
            document.applicationClusterForm.childApplicationIds;
        for(var i=0; i<availableList.length; i++){
            if(availableList.options[i].selected == true){
                selectedList.options[selectedList.length] =
                    new Option(availableList.options[i].text,
                               availableList.options[i].value,
                               false, false);
                availableList.options[i] = null;
                i--;
            }
        }
        computeSelectedChildApplications();
    }

    function remove(){
        var availableList =
            document.applicationClusterForm.standAloneApplicationIds;
        var selectedList =
            document.applicationClusterForm.childApplicationIds;
        for(var i=0; i<selectedList.length; i++){
            if(selectedList.options[i].selected == true){
                availableList.options[availableList.length] =
                    new Option(selectedList.options[i].text,
                               selectedList.options[i].value,
                               false, false);
                selectedList.options[i] = null;
                i--;
            }
        }
        computeSelectedChildApplications();
    }

    function computeSelectedChildApplications(){
        var selectedChildApplications = "";
        var selectedList =
            document.applicationClusterForm.childApplicationIds;
        for(var i=0; i<selectedList.length; i++){
            if(selectedChildApplications != "")
                selectedChildApplications += ",";
            selectedChildApplications += selectedList.options[i].value;
        }
        document.applicationClusterForm.selectedChildApplications.value =
                selectedChildApplications;
     }
</script>
</head>
<jmhtml:form action="/config/saveApplicationCluster" method="post">
 <jmhtml:hidden property="applicationId" />
 <jmhtml:hidden property="refreshApps" value="true" />
 <jmhtml:hidden property="selectedChildApplications" value="" />

<table class="table" border="0" cellspacing="0" cellpadding="5" width="500">
    <tr class="tableHeader">
        <td colspan="2">Application Cluster</td>
    </tr>
    <tr>
        <td class="headtext1">Name:</td>
        <td><jmhtml:text property="name" size="50"/></td>
    </tr>
</table>
<br>
<table class="table" border="0" cellspacing="0" cellpadding="5" width="500">
    <tr class="tableHeader">
        <td colspan="3">Applications in this cluster</td>
    </tr>
    <tr>
        <td class="plaintext">Available</td>
        <td>&nbsp;</td>
        <td class="plaintext">Selected</td>
    <tr>
        <td>
        <jmhtml:select property="standAloneApplicationIds" multiple="true">
            <jmhtml:options collection="applications" labelProperty="name" property="applicationId"/>
        </jmhtml:select>
        </td>
        <td width="10">
        <input type="button" class="Inside3d" onClick="add();" value="Add >>"/>
        <br>
        <input type="button" class="Inside3d" onClick="remove()" value="<< Remove"/>
        </td>
        <td>
        <jmhtml:select property="childApplicationIds" multiple="true">
            <jmhtml:options collection="selectedApplications" labelProperty="name" property="applicationId"/>
        </jmhtml:select>
        </td>
    </tr>
    <tr>
        <td colspan="3" align="center">
          <jmhtml:submit value="Save" styleClass="Inside3d"/>
          &nbsp;&nbsp;&nbsp;
          <jmhtml:button property="" value="Cancel" onclick="JavaScript:history.back();" styleClass="Inside3d" />
        </td>
    </tr>
</table>
</jmhtml:form>
<script>computeSelectedChildApplications();</script>