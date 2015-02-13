--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

delimiter /
TRUNCATE TABLE KREW_RULE_ATTR_T
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('org.kuali.rice.kew.rule.RuleRoutingAttribute','Rule Routing Attribute','Rule Routing Attribute','RuleRoutingAttribute','6166CBA1B94F644DE0404F8189D86C09',1000,'RuleAttribute',3)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('org.kuali.rice.ken.kew.NotificationCustomActionListAttribute','Notification Action List Attribute','Notification  Action List Attribute','NotificationCustomActionListAttribute','6166CBA1B952644DE0404F8189D86C09',1003,'ActionListAttribute',2)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','The associated channel that this message was sent on.','Notification Channel','NotificationChannelSearchableAttribute','6166CBA1B953644DE0404F8189D86C09',1004,'SearchableXmlAttribute',1,'<searchingConfig>
              <fieldDef name="notificationChannel" title="Notification Channel">
                  <display>
                      <type>text</type>
                  </display>
                  <validation required="false"/>
                  <fieldEvaluation>
                      <xpathexpression>string(/documentContent/applicationContent/notification/channel/name)</xpathexpression>
                  </fieldEvaluation>
              </fieldDef>
           </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','The priority of this notification.','Notification Priority','NotificationPrioritySearchableAttribute','6166CBA1B954644DE0404F8189D86C09',1005,'SearchableXmlAttribute',1,'<searchingConfig>
              <fieldDef name="notificationPriority" title="Notification Priority">
                  <display>
                      <type>text</type>
                  </display>
                  <validation required="false"/>
                  <fieldEvaluation>
                      <xpathexpression>string(/documentContent/applicationContent/notification/priority/name)</xpathexpression>
                  </fieldEvaluation>
              </fieldDef>
           </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','The content type of this notification.','Notification Content Type','NotificationContentTypeSearchableAttribute','6166CBA1B955644DE0404F8189D86C09',1006,'SearchableXmlAttribute',1,'<searchingConfig>
              <fieldDef name="notificationContentType" title="Notification Content Type">
                  <display>
                      <type>text</type>
                  </display>
                  <validation required="false"/>
                  <fieldEvaluation>
                      <xpathexpression>string(/documentContent/applicationContent/notification/contentType/name)</xpathexpression>
                  </fieldEvaluation>
              </fieldDef>
           </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','The producer of this notification.','Notification Producer','NotificationProducerSearchableAttribute','6166CBA1B956644DE0404F8189D86C09',1007,'SearchableXmlAttribute',1,'<searchingConfig>
              <fieldDef name="notificationProducer" title="Notification Producer">
                  <display>
                      <type>text</type>
                  </display>
                  <validation required="false"/>
                  <fieldEvaluation>
                      <xpathexpression>string(/documentContent/applicationContent/notification/producer/name)</xpathexpression>
                  </fieldEvaluation>
              </fieldDef>
           </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','Those who are receiving this notification.','Notification Recipient','NotificationRecipientsSearchableAttribute','6166CBA1B957644DE0404F8189D86C09',1008,'SearchableXmlAttribute',1,'<searchingConfig>
              <fieldDef name="notificationRecipients" title="Notification Recipients">
                  <display>
                      <type>text</type>
                  </display>
                  <validation required="false"/>
                  <fieldEvaluation>
                      <xpathexpression>string(/documentContent/applicationContent/notification//recipients)</xpathexpression>
                  </fieldEvaluation>
              </fieldDef>
           </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','Those who this notification is being sent on behalf of.','Notification Senders','NotificationSendersSearchableAttribute','6166CBA1B958644DE0404F8189D86C09',1009,'SearchableXmlAttribute',1,'<searchingConfig>
              <fieldDef name="notificationSenders" title="Notification Senders">
                  <display>
                      <type>text</type>
                  </display>
                  <validation required="false"/>
                  <fieldEvaluation>
                      <xpathexpression>string(/documentContent/applicationContent/notification//senders)</xpathexpression>
                  </fieldEvaluation>
              </fieldDef>
           </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('org.kuali.rice.ken.kew.ChannelReviewerRoleAttribute','Channel Reviewer Role Attribute','Channel Reviewer Role Attribute','ChannelReviewerRoleAttribute','6166CBA1B959644DE0404F8189D86C09',1010,'RuleAttribute',1)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','XML Searchable attribute','XML Searchable attribute','XMLSearchableAttribute','6166CBA1B9C5644DE0404F8189D86C09',1233,'SearchableXmlAttribute',1,'<searchingConfig>
        <fieldDef name="givenname" title="First name">
          <display>
            <type>text</type>
          </display>
          <searchDefinition autoWildcardLocation="prefixonly"/>
          <validation required="true">
            <regex>^[a-zA-Z ]+$</regex>
            <message>Invalid first name</message>
          </validation>
          <fieldEvaluation>
            <xpathexpression>//givenname/value</xpathexpression>
          </fieldEvaluation>
        </fieldDef>
      </searchingConfig>')
/
delimiter ;
