TRUNCATE TABLE KREW_RULE_ATTR_T DROP STORAGE
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
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR)
  VALUES ('edu.sampleu.travel.workflow.DestinationRuleAttribute','Department of Prudence Routing','Department of Prudence Routing','DestinationAttribute','6166CBA1B95A644DE0404F8189D86C09',1011,'RuleAttribute','TRAVEL',1)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR)
  VALUES ('edu.sampleu.travel.workflow.EmployeeAttribute','Employee Routing','Employee Routing','EmployeeAttribute','6166CBA1B95B644DE0404F8189D86C09',1012,'RuleAttribute','TRAVEL',1)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('edu.sampleu.travel.workflow.AccountAttribute','AccountAttribute','AccountAttribute','AccountAttribute','6166CBA1B95C644DE0404F8189D86C09',1013,'RuleAttribute',2)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kns.workflow.attribute.KualiXmlSearchableAttributeImpl','The search attribute used to find documents by account number.','Account Number Attribute','TravelAccountDocumentAccountNumberAttribute','6166CBA1B95D644DE0404F8189D86C09',1014,'SearchableXmlAttribute',1,'<searchingConfig>
                <fieldDef name="accountNumber" title="kuali_dd_label(TravelAccount)">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>wf:xstreamsafe(''//newMaintainableObject/businessObject/number'')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
            </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','EDL School Routing','EDL Campus Routing','EDL.Campus.Example','6166CBA1B95F644DE0404F8189D86C09',1100,'RuleXmlAttribute',2,'<routingConfig>
        <fieldDef name="campus" title="Campus" workflowType="ALL">
          <display>
            <type>select</type>
            <values title="IUB">IUB</values>
            <values title="IUPUI">IUPUI</values>
          </display>
          <validation required="false"/>
          <fieldEvaluation>
            <xpathexpression>//campus = wf:ruledata(''campus'')</xpathexpression>
          </fieldEvaluation>
        </fieldDef>
        <xmlDocumentContent>
          <campus>%campus%</campus>
        </xmlDocumentContent>
      </routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('edu.sampleu.travel.workflow.AccountAttribute','foo','foo','FiscalOfficer','6166CBA1B96D644DE0404F8189D86C09',1133,'RuleAttribute',2)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('components.LoadTestActionListAttibute','LoadTestActionListAttribute','LoadTestActionListAttribute','LoadTestActionListAttribute','6166CBA1B9C4644DE0404F8189D86C09',1232,'ActionListAttribute',1)
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
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','XML Searchable attribute','XML Searchable attribute','XMLSearchableAttribute_CaseInsensitive','6166CBA1B9C6644DE0404F8189D86C09',1234,'SearchableXmlAttribute',1,'<searchingConfig>
        <fieldDef name="givenname_nocase" title="First name">
          <display>
            <type>text</type>
          </display>
          <searchDefinition autoWildcardLocation="prefixonly" caseSensitive="false"/>
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
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','XML Searchable attribute','XML Searchable attribute','XMLSearchableAttributeStdLong','6166CBA1B9C7644DE0404F8189D86C09',1235,'SearchableXmlAttribute',1,'<searchingConfig>
        <fieldDef name="testLongKey" title="Certain Long Value">
          <display>
            <type>text</type>
          </display>
          <searchDefinition allowWildcards="true" autoWildcardLocation="suffixOnly" dataType="long"/>
          <fieldEvaluation>
            <xpathexpression>//testLongKey/value</xpathexpression>
          </fieldEvaluation>
        </fieldDef>
      </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','XML Searchable attribute','XML Searchable attribute','XMLSearchableAttributeStdFloat','6166CBA1B9C8644DE0404F8189D86C09',1236,'SearchableXmlAttribute',1,'<searchingConfig>
        <fieldDef name="testFloatKey" title="Float in the Water">
          <display>
            <type>text</type>
          </display>
          <searchDefinition dataType="float"/>
          <fieldEvaluation>
            <xpathexpression>//testFloatKey/value</xpathexpression>
          </fieldEvaluation>
        </fieldDef>
      </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','XML Searchable attribute','XML Searchable attribute','XMLSearchableAttributeStdCurrency','6166CBA1B9C9644DE0404F8189D86C09',1237,'SearchableXmlAttribute',1,'<searchingConfig>
        <fieldDef name="testCurrencyKey" title="Dollars Here">
          <display>
            <type>text</type>
            <parameters name="displayFormatPattern">#.00</parameters>
          </display>
          <searchDefinition dataType="float"/>
          <fieldEvaluation>
            <xpathexpression>//testCurrencyKey/value</xpathexpression>
          </fieldEvaluation>
        </fieldDef>
      </searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','XML Searchable attribute','XML Searchable attribute','XMLSearchableAttributeStdDateTime','6166CBA1B9CA644DE0404F8189D86C09',1238,'SearchableXmlAttribute',1,'<searchingConfig>
        <fieldDef name="testDateTimeKey" title="Searchable Date Field">
          <display>
            <type>text</type>
          </display>
          <searchDefinition dataType="datetime" datePicker="false"/>
          <fieldEvaluation>
            <xpathexpression>//putWhateverWordsIwantInsideThisTag/testDateTimeKey/value</xpathexpression>
          </fieldEvaluation>
        </fieldDef>
      </searchingConfig>')
/
