--INSERT INTO FS_PARM_SEC_T values ('SYSTEM', '1', 1, 'WorkflowAdmin', 'Desc')
--/
--INSERT INTO FS_PARM_SEC_T values ('CoreMaintenanceEDoc', '2', 1, 'WorkflowAdmin', 'Desc')
--/
--INSERT INTO FS_PARM_T VALUES('SYSTEM','HELP_URL','07D71A3FF0D604D8E043814FD88104D8','1','http://www.fms.indiana.edu/fis/home.asp','','N', 'MC')
--/
--INSERT INTO FS_PARM_T VALUES('SYSTEM','lookup.results.limit','1AFCED30C07B2070E043814FD8812070','0','200','Limit of results returned in a lookup query','N', 'MC')
--/
--INSERT INTO FS_PARM_T VALUES('SYSTEM','demonstrationEncryptionCheck_FLAG','1C3D291AAD51A08CE043814FD881A08C','1','Y','Flag for enabling/disabling the demonstration encryption check.','N', 'MC')
--/
--INSERT INTO FS_PARM_T VALUES('SYSTEM','loadDataFileStep_USER','1F75EFB795DFB050E043814FD881B050','1','KULUSER','determines who the loadDataFileStep of pcdo_batch.sh will run as','N', 'MC')
--/
--insert into FS_PARM_T values ('CoreMaintenanceEDoc','Kuali.Document.RoutingReport.Workgroup','263A097060A3F152E043814FD881F152','1','WorkflowAdmin','Workgroup which can perform the route report on documents.','N', 'MC')
--/
--insert into FS_PARM_T values ('CoreMaintenanceEDoc','CASPasswordEnabled','26C8E6D6E77F40B4E043814FD88140B4','1','N','Whether the built in CAS implementation should ask for a password. The password will be verified against the Universal User Table.','N', 'MC')
--/
--insert into FS_PARM_T values ('CoreMaintenanceEDoc','UniversalUser.EditWorkgroup','2409BD6AB4CA800EE043814FD881800E','1','WorkflowAdmin','Workgroup which can edit the universal user table.','N', 'MC')
--/
--insert into FS_PARM_T values ('CoreMaintenanceEDoc','Workflow.Exception.Workgroup','2409BD6AB4CB800EE043814FD881800E','1','WorkflowAdmin','Workgroup which can perform functions on documents in exception routing status.','N', 'MC')
--/
--insert into FS_PARM_T values ('CoreMaintenanceEDoc','Kuali.Supervisor.Workgroup','2409BD6AB4CC800EE043814FD881800E','1','WorkflowAdmin','Workgroup which can perform almost any function within Kuali.','N', 'MC')
--/

insert into EN_APPL_CNST_T values ('Feature.CheckRouteLogAuthentication.CheckFuture', 'true', 1)
/
insert into EN_APPL_CNST_T values ('RouteQueue.maxRetryAttempts', '0', 1)
/
insert into EN_APPL_CNST_T values ('BAM', 'true', 1)
/
insert into EN_APPL_CNST_T values ('Security.HttpInvoker.SignMessages', 'true', 1)
/
insert into EN_APPL_CNST_T values ('Workflow.AdminWorkgroup', 'WorkflowAdmin', 1)
/
insert into EN_APPL_CNST_T values ('Routing.ImmediateExceptionRouting', 'true', 1)
/
insert into EN_APPL_CNST_T values ('Workgroup.IsRouteLogPopup', 'false', 0)
/
insert into EN_APPL_CNST_T values ('DocumentType.IsRouteLogPopup', 'false', 0)
/
insert into EN_APPL_CNST_T values ('DocumentSearch.IsRouteLogPopup', 'true', 0)
/
insert into EN_APPL_CNST_T values ('DocumentSearch.IsDocumentPopup', 'true', 0)
/
insert into EN_APPL_CNST_T values ('Config.Backdoor.TargetFrameName', 'iframe_51148', 0)
/
insert into FP_DOC_STATUS_T values ('A',	'2E0671732A684002E043814FD8814002',	1,	'Approved')
/
insert into FP_DOC_STATUS_T values ('C',	'2E0671732A694002E043814FD8814002',	1,	'Cancelled')
/
insert into FP_DOC_STATUS_T values ('E',	'2E0671732A6A4002E043814FD8814002',	1,	'Extracted')
/
insert into FP_DOC_STATUS_T values ('I',	'2E0671732A6B4002E043814FD8814002',	1,	'In Process')
/
insert into FP_DOC_STATUS_T values ('II',	'2E0671732A6C4002E043814FD8814002',	1,	'In Process')
/
insert into FP_DOC_STATUS_T values ('O',	'2E0671732A6D4002E043814FD8814002',	1,	'Pend Org')
/
insert into FP_DOC_STATUS_T values ('OO',	'2E0671732A6E4002E043814FD8814002',	1,	'Pend Org')
/
insert into FP_DOC_STATUS_T values ('P',	'2E0671732A6F4002E043814FD8814002',	1,	'Pend Acct')
/
insert into FP_DOC_STATUS_T values ('PP',	'2E0671732A704002E043814FD8814002',	1,	'Pend Acct')
/
insert into FP_DOC_STATUS_T values ('R',	'2E0671732A714002E043814FD8814002',	1,	'Pend Specl')
/
insert into FP_DOC_STATUS_T values ('RR',	'2E0671732A724002E043814FD8814002',	1,	'Pend Specl')
/
insert into FP_DOC_STATUS_T values ('S',	'2E0671732A734002E043814FD8814002',	1,	'Pend CG')
/
insert into FP_DOC_STATUS_T values ('V',	'2E0671732A744002E043814FD8814002',	1,	'Validation')
/
insert into FP_DOC_STATUS_T values ('Q',	'2E0671732A754002E043814FD8814002',	1,	'Doc Specif')
/
INSERT INTO kr_qrtz_locks values('TRIGGER_ACCESS')
/
INSERT INTO kr_qrtz_locks values('JOB_ACCESS')
/
INSERT INTO kr_qrtz_locks values('CALENDAR_ACCESS')
/
INSERT INTO kr_qrtz_locks values('STATE_ACCESS')
/
INSERT INTO kr_qrtz_locks values('MISFIRE_ACCESS')
/
INSERT INTO KIM_NAMESPACES_T (ID, NAME, DESCRIPTION) VALUES (1, 'KIM', 'This record represents the actual KIM system and must always be loaded by default in order for the system to work properly.') 
/
INSERT INTO KIM_PERSONS_T (ID, USERNAME, PASSWORD) VALUES (1, 'admin', 'admin')
/

-- KEN sample app data below...

-- NOTIFICATION_PRIORITIES --
INSERT INTO NOTIFICATION_PRIORITIES
(ID, NAME, DESCRIPTION, PRIORITY_ORDER)
VALUES
(1, 'Normal', 'Normal priority', 2)/

INSERT INTO NOTIFICATION_PRIORITIES
(ID, NAME, DESCRIPTION, PRIORITY_ORDER)
VALUES
(2, 'Low', 'A low priority', 3)/

INSERT INTO NOTIFICATION_PRIORITIES
(ID, NAME, DESCRIPTION, PRIORITY_ORDER)
VALUES
(3, 'High', 'A high priority', 1)/

-- NOTIFICATION_CONTENT_TYPES --
INSERT INTO NOTIFICATION_CONTENT_TYPES
(ID, NAME, DESCRIPTION, NAMESPACE, XSD, XSL)
VALUES
(1, 'Simple', 'Simple content type', 'notification/ContentTypeSimple',
'<?xml version="1.0" encoding="UTF-8"?>
<!-- This schema describes a simple notification.  It only contains a content
element which is a String...about as simple as one can get -->
<schema xmlns="http://www.w3.org/2001/XMLSchema"
  xmlns:c="ns:notification/common"
  xmlns:cs="ns:notification/ContentTypeSimple"
  targetNamespace="ns:notification/ContentTypeSimple"
  attributeFormDefault="unqualified" 
    elementFormDefault="qualified">
  <annotation>
    <documentation xml:lang="en">
      Simple Content Schema
    </documentation>
  </annotation>
  <import namespace="ns:notification/common" schemaLocation="resource:notification/notification-common" />
  <!--  The content element is just a String -->
  <element name="content">
    <complexType>
      <sequence>
        <element name="message" type="c:LongStringType"/>
      </sequence>
    </complexType>
  </element>
</schema>',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
   version="1.0" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
   xmlns:n="ns:notification/ContentTypeSimple" 
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
   xsi:schemaLocation="ns:notification/ContentTypeSimple resource:notification/ContentTypeSimple" 
   exclude-result-prefixes="n xsi">
   <xsl:output method="html" omit-xml-declaration="yes" />
   <xsl:template match="/n:content/n:message">
      <strong>
          <xsl:value-of select="." disable-output-escaping="yes"/>
      </strong>
   </xsl:template>
</xsl:stylesheet>')/

INSERT INTO NOTIFICATION_CONTENT_TYPES
(ID, NAME, DESCRIPTION, NAMESPACE, XSD, XSL)
VALUES
(2, 'Event', 'Event content type', 'notification/ContentTypeEvent',
'<?xml version="1.0" encoding="UTF-8"?>
<!-- This schema defines an generic event notification type in order for it
to be accepted into the system. -->
<schema xmlns="http://www.w3.org/2001/XMLSchema" xmlns:c="ns:notification/common" xmlns:ce="ns:notification/ContentTypeEvent" targetNamespace="ns:notification/ContentTypeEvent" attributeFormDefault="unqualified" elementFormDefault="qualified">
  <annotation>
    <documentation xml:lang="en">Content Event Schema</documentation>
  </annotation>
  <import namespace="ns:notification/common" schemaLocation="resource:notification/notification-common" />
  <!-- The content element describes the content of the notification.  It
  contains a message (a simple String) and a message element -->
  <element name="content">
    <complexType>
      <sequence>
        <element name="message" type="c:LongStringType"/>
        <element ref="ce:event"/>
      </sequence>
    </complexType>
  </element>
  <!-- This is the event element.  It describes a simple event type containing a
  summary, description, location, and start/stop times -->
  <element name="event">
    <complexType>
      <sequence>
        <element name="summary" type="c:NonEmptyShortStringType" />
        <element name="description" type="c:NonEmptyShortStringType" />
        <element name="location" type="c:NonEmptyShortStringType" />
        <element name="startDateTime" type="dateTime" />
        <element name="stopDateTime" type="dateTime" />
      </sequence>
    </complexType>
  </element>
</schema>', 
'<?xml version="1.0" encoding="UTF-8"?>
<!-- style sheet declaration: be very careful editing the following, the
     default namespace must be used otherwise elements will not match -->
<xsl:stylesheet
    version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:n="ns:notification/ContentTypeEvent" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xsi:schemaLocation="ns:notification/ContentTypeEvent resource:notification/ContentTypeEvent" 
    exclude-result-prefixes="n xsi">
    <!-- output an html fragment -->
    <xsl:output method="html" indent="yes" />
    <!-- match everything -->
    <xsl:template match="/n:content" >
        <table class="bord-all">
            <xsl:apply-templates />
        </table>
    </xsl:template>
    <!--  match message element in the default namespace and render as strong -->
    <xsl:template match="n:message" >
        <caption>
            <strong><xsl:value-of select="." disable-output-escaping="yes"/></strong>
        </caption>
    </xsl:template>
    <!-- match on event in the default namespace and display all children -->
    <xsl:template match="n:event">
        <tr>
            <td class="thnormal"><strong>Summary: </strong></td>
            <td class="thnormal"><xsl:value-of select="n:summary" /></td>
        </tr>
        <tr>
            <td class="thnormal"><strong>Description: </strong></td>
            <td class="thnormal"><xsl:value-of select="n:description" /></td>
        </tr>
        <tr>
            <td class="thnormal"><strong>Location: </strong></td>
            <td class="thnormal"><xsl:value-of select="n:location" /></td>
        </tr>
        <tr>
            <td class="thnormal"><strong>Start Time: </strong></td>
            <td class="thnormal"><xsl:value-of select="n:startDateTime" /></td>
        </tr>
        <tr>
            <td class="thnormal"><strong>End Time: </strong></td>
            <td class="thnormal"><xsl:value-of select="n:stopDateTime" /></td>
        </tr>
    </xsl:template> 
</xsl:stylesheet>')/

-- NOTIFICATION_PRODUCERS --
INSERT INTO NOTIFICATION_PRODUCERS 
(ID, NAME, DESCRIPTION, CONTACT_INFO) 
VALUES 
(1, 'Notification System', 'This producer represents messages sent from the general message sending forms.', 'kuali-ken-testing@cornell.edu')/

INSERT INTO NOTIFICATION_PRODUCERS 
(ID, NAME, DESCRIPTION, CONTACT_INFO) 
VALUES 
(2, 'University Library System', 'This producer represents messages sent from the University Library system.', 'kuali-ken-testing@cornell.edu')/

INSERT INTO NOTIFICATION_PRODUCERS 
(ID, NAME, DESCRIPTION, CONTACT_INFO) 
VALUES 
(3, 'University Events Office', 'This producer represents messages sent from the University Events system.', 'kuali-ken-testing@cornell.edu')/

-- NOTIFICATION_CHANNELS --
DELETE FROM NOTIFICATION_CHANNELS/

INSERT INTO NOTIFICATION_CHANNELS
(ID, NAME, DESCRIPTION, SUBSCRIBABLE)
VALUES
(1, 'Kuali Rice Channel', 'This channel is used for sending out information about the Kuali Rice effort.', 'Y')/

INSERT INTO NOTIFICATION_CHANNELS
(ID, NAME, DESCRIPTION, SUBSCRIBABLE)
VALUES
(2, 'Library Events Channel', 'This channel is used for sending out information about Library Events.', 'Y')/

INSERT INTO NOTIFICATION_CHANNELS
(ID, NAME, DESCRIPTION, SUBSCRIBABLE)
VALUES
(3, 'Overdue Library Books', 'This channel is used for sending out information about your overdue books.', 'N')/

INSERT INTO NOTIFICATION_CHANNELS
(ID, NAME, DESCRIPTION, SUBSCRIBABLE)
VALUES
(4, 'Concerts Coming to Campus', 'This channel broadcasts any concerts coming to campus.', 'Y')/

INSERT INTO NOTIFICATION_CHANNELS
(ID, NAME, DESCRIPTION, SUBSCRIBABLE)
VALUES
(5, 'University Alerts', 'This channel broadcasts general announcements for the university.', 'N')/

-- NOTIFICATION_CHANNEL_SUBSCRIPTIONS

INSERT INTO USER_CHANNEL_SUBSCRIPTIONS
(ID, CHANNEL_ID, USER_ID)
VALUES
(1, 1, 'TestUser4')/

-- NOTIFICATION_RECIPIENTS_LISTS

INSERT INTO NOTIFICATION_RECIPIENTS_LISTS
(ID, CHANNEL_ID, RECIPIENT_TYPE, RECIPIENT_ID)
values
(1, 4, 'USER', 'TestUser1')/

INSERT INTO NOTIFICATION_RECIPIENTS_LISTS
(ID, CHANNEL_ID, RECIPIENT_TYPE, RECIPIENT_ID)
values
(2, 4, 'USER', 'TestUser3')/

-- NOTIFICATION_CHANNEL_REVIEWERS

INSERT INTO NOTIFICATION_REVIEWERS
(ID, CHANNEL_ID, REVIEWER_TYPE, REVIEWER_ID) 
VALUES 
(1, 1, 'GROUP', 'RiceTeam')/

INSERT INTO NOTIFICATION_REVIEWERS
(ID, CHANNEL_ID, REVIEWER_TYPE, REVIEWER_ID) 
VALUES 
(2, 5, 'USER', 'TestUser3')/

INSERT INTO NOTIFICATION_REVIEWERS
(ID, CHANNEL_ID, REVIEWER_TYPE, REVIEWER_ID) 
VALUES 
(3, 5, 'GROUP', 'TestGroup1')/

-- NOTIFICATION_CHANNEL_PRODUCERS --
DELETE FROM NOTIFICATION_CHANNEL_PRODUCERS/

INSERT INTO NOTIFICATION_CHANNEL_PRODUCERS 
(CHANNEL_ID, PRODUCER_ID) 
VALUES 
(1, 1)/

INSERT INTO NOTIFICATION_CHANNEL_PRODUCERS 
(CHANNEL_ID, PRODUCER_ID) 
VALUES 
(2, 1)/

INSERT INTO NOTIFICATION_CHANNEL_PRODUCERS 
(CHANNEL_ID, PRODUCER_ID) 
VALUES 
(3, 1)/

INSERT INTO NOTIFICATION_CHANNEL_PRODUCERS 
(CHANNEL_ID, PRODUCER_ID) 
VALUES 
(4, 1)/

INSERT INTO NOTIFICATION_CHANNEL_PRODUCERS 
(CHANNEL_ID, PRODUCER_ID) 
VALUES 
(5, 1)/

INSERT INTO NOTIFICATION_CHANNEL_PRODUCERS 
(CHANNEL_ID, PRODUCER_ID) 
VALUES 
(2, 2)/

INSERT INTO NOTIFICATION_CHANNEL_PRODUCERS 
(CHANNEL_ID, PRODUCER_ID) 
VALUES 
(3, 2)/

INSERT INTO NOTIFICATION_CHANNEL_PRODUCERS 
(CHANNEL_ID, PRODUCER_ID) 
VALUES 
(4, 3)/

