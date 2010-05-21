delimiter /
TRUNCATE TABLE KREW_RULE_ATTR_T
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('org.kuali.rice.kew.rule.RuleRoutingAttribute','Rule Routing Attribute','Rule Routing Attribute','RuleRoutingAttribute','25DFE894-AFA3-DBC7-9D72-4688AABCE09C',1000,'RuleAttribute',4)
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
                        <xpathexpression>wf:xstreamsafe(\'//newMaintainableObject/businessObject/number\')</xpathexpression>
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
            <xpathexpression>//campus = wf:ruledata(\'campus\')</xpathexpression>
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
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','AwardCustomDataAttribute','AwardCustomDataAttribute','AwardCustomDataAttribute','B591A340-BC9F-BD18-BBB7-4ED06E6560F0',1640,'RuleXmlAttribute','KC',7,'<routingConfig>
                <fieldDef name="billingElement" title="Billing Element" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'billingElement\']/value) = wf:ruledata(\'billingElement\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="costSharingBudget" title="Cost Sharing Budget" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'costSharingBudget\']/value) = wf:ruledata(\'costSharingBudget\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="numberOfTrainees" title="Number of Trainees" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'numberOfTrainees\']/value) = wf:ruledata(\'numberOfTrainees\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="graduateStudentCount" title="Graduate Student Count" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'graduateStudentCount\']/value) = wf:ruledata(\'graduateStudentCount\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="tenured" title="Tenured" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'tenured\']/value) = wf:ruledata(\'tenured\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="exportControls" title="Export Controls" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'exportControls\']/value) = wf:ruledata(\'exportControls\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="inventions" title="Inventions" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'inventions\']/value) = wf:ruledata(\'inventions\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
            </routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','IRBCustomDataAttribute','IRBCustomDataAttribute','IRBCustomDataAttribute','0860F044-4883-87E1-C4A2-8BE804E4B45D',1641,'RuleXmlAttribute',2,'<routingConfig>
                <fieldDef name="instructorName" title="Instructor Name" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'instructorName\']/value) = wf:ruledata(\'instructorName\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="courseName" title="Course Name" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <xpathexpression>string(//xmlRouting/field[@name=\'courseName\']/value) = wf:ruledata(\'courseName\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
            </routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR)
  VALUES ('org.kuali.kra.workflow.CopyCustomActionListAttribute','Copy Action List Attribute','Copy Action List Attribute','CopyCustomActionListAttribute','985DFEFB-41D8-A0B2-E1AE-6CE57A839B32',1642,'ActionListAttribute','KC',11)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','AggregatorSearchAttribute','AggregatorSearchAttribute','AggregatorSearchAttribute','012287AD-B018-25E3-C33E-A5EFA74A1F19',1643,'SearchableXmlAttribute','KC',11,'<searchingConfig>
				<fieldDef name="aggregator" title="Aggregator">
					<display>
						<type>text</type>
					</display>
					<lookup businessObjectClass="org.kuali.rice.kim.bo.Person">
				      <fieldConversions>
				        <fieldConversion localFieldName="aggregator" lookupFieldName="principalName"/>
				      </fieldConversions>
				    </lookup>
					
					<fieldEvaluation>
						<!-- not sure yet -->
						<!--  example //organization/organizationId -->
						<xpathexpression>
							//aggregator/string
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
				<xmlSearchContent>
					<users>
						<aggregator>
							<value>%aggregator%</value>
						</aggregator>
					</users>
				</xmlSearchContent>
			</searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','BudgetCreatorSearchAttribute','BudgetCreatorSearchAttribute','BudgetCreatorSearchAttribute','6A76718F-18FF-41DC-92AF-72C78B37F23A',1644,'SearchableXmlAttribute','KC',11,'<searchingConfig>
				<fieldDef name="budgetCreator" title="Budget Creator">
					<display>
						<type>text</type>
					</display>
					<lookup businessObjectClass="org.kuali.rice.kim.bo.Person">
				      <fieldConversions>
				        <fieldConversion localFieldName="budgetCreator" lookupFieldName="principalName"/>
				      </fieldConversions>
				    </lookup>
					
					<fieldEvaluation>
						<xpathexpression>
							//budgetcreator/string
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
				<xmlSearchContent>
					<users>
						<budgetCreator>
							<value>%budgetCreator%</value>
						</budgetCreator>
					</users>
				</xmlSearchContent>
			</searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','NarrativeWriterSearchAttribute','NarrativeWriterSearchAttribute','NarrativeWriterSearchAttribute','44B7E762-0747-A92D-A41B-5195CAFA24F8',1645,'SearchableXmlAttribute','KC',11,'<searchingConfig>
				<fieldDef name="narrativeWriter" title="Narrative Writer">
					<display>
						<type>text</type>
					</display>
					<lookup businessObjectClass="org.kuali.rice.kim.bo.Person">
				      <fieldConversions>
				        <fieldConversion localFieldName="narrativeWriter" lookupFieldName="principalName"/>
				      </fieldConversions>
				    </lookup>
					
					<fieldEvaluation>
						<xpathexpression>
							//narrativewriter/string
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
				<xmlSearchContent>
					<users>
						<narrativeWriter>
							<value>%narrativeWriter%</value>
						</narrativeWriter>
					</users>
				</xmlSearchContent>
			</searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','ViewerSearchAttribute','ViewerSearchAttribute','ViewerSearchAttribute','A35478A7-7160-2641-5DCC-8D979565F1E0',1646,'SearchableXmlAttribute','KC',11,'<searchingConfig>
				<fieldDef name="viewer" title="Viewer">
					<display>
						<type>text</type>
					</display>
					<lookup businessObjectClass="org.kuali.rice.kim.bo.Person">
				      <fieldConversions>
				        <fieldConversion localFieldName="viewer" lookupFieldName="principalName"/>
				      </fieldConversions>
				    </lookup>
					
					<fieldEvaluation>
						<xpathexpression>
							//viewer/string
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
				<xmlSearchContent>
					<users>
						<viewer>
							<value>%viewer%</value>
						</viewer>
					</users>
				</xmlSearchContent>
			</searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','ProposalNumberSearchAttribute','ProposalNumberSearchAttribute','ProposalNumberAttribute','9F678FF9-18B8-BF84-6042-583409448124',1647,'SearchableXmlAttribute','KC',11,'<searchingConfig>
				<fieldDef name="proposalNumber" title="Proposal ID">
					<display>
						<type>text</type>
					</display>
					<fieldEvaluation>
						<xpathexpression>
							//document/developmentProposalList[1]/proposalNumber
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
				<xmlSearchContent>
					<users>
						<viewer>
							<value>%proposalNumber%</value>
						</viewer>
					</users>
				</xmlSearchContent>
			</searchingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','DepartmentalApprovalAttribute','DepartmentalApprovalAttribute','DepartmentalApprovalAttribute','2BED2E87-1513-3B99-A4D9-9BB15FB389AF',1648,'RuleXmlAttribute','KC',11,'<routingConfig>
				<fieldDef name="ownedByUnitNumber" title="Lead Unit" workflowType="RULE">
					<display>
						<type>text</type>
					</display>
					<validation required="false"/>
					<fieldEvaluation>
						<xpathexpression>
						  	wf:xstreamsafe(\'//document/developmentProposalList[1]/org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal/ownedByUnitNumber\')= wf:ruledata(\'ownedByUnitNumber\')
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
			</routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','
				DepartmentalBudgetApprovalAttribute
			','DepartmentalBudgetApprovalAttribute','DepartmentalBudgetApprovalAttribute','8E0FFA12-A3C2-7683-9831-D7522F9ECB91',1649,'RuleXmlAttribute','KC',11,'<routingConfig>
				<fieldDef name="totalCost" title="Total" workflowType="RULE">
					<display>
						<type>text</type>
					</display>
					<validation required="false"/>
					<fieldEvaluation>
						<xpathexpression>
							wf:xstreamsafe(\'//document/developmentProposalList[1]/ownedByUnitNumber\')= \'IN-CARD\' and wf:xstreamsafe("//budgetDocumentVersions/budgetVersionOverview/org.kuali.kra.budget.versions.BudgetVersionOverview/totalCost/value") &gt;= wf:ruledata(\'totalCost\')
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
			</routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('org.kuali.kra.workflow.ProposalPersonRoleAttribute','Proposal Routing','Proposal Routing','ProposalPersonRoleAttribute','FB9681D7-BE2A-1C0B-26B0-BFC543246A26',1650,'RuleAttribute',3)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','CustomDataAttribute','CustomDataAttribute','CustomDataAttribute','8C348B9A-0EB0-41A0-FD7B-EA833D0BB433',1651,'RuleXmlAttribute','KC',11,'<routingConfig>
                <!-- work in progress -->
                <fieldDef name="billingElement" title="Billing Element" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true -->
                        <xpathexpression>string(//xmlRouting/field[@name=\'billingElement\']/value) = wf:ruledata(\'billingElement\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="graduateStudentCount" title="Graduate Student Count" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true -->
                        <xpathexpression>string(//xmlRouting/field[@name=\'graduateStudentCount\']/value) = wf:ruledata(\'graduateStudentCount\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="costSharingBudget" title="Cost Sharing Budget" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true -->
                        <xpathexpression>string(//xmlRouting/field[@name=\'costSharingBudget\']/value) = wf:ruledata(\'costSharingBudget\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
     			<fieldDef name="numberOfTrainees" title="Number Of Trainees" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true -->
                        <xpathexpression>string(//xmlRouting/field[@name=\'numberOfTrainees\']/value) = wf:ruledata(\'numberOfTrainees\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="tenured" title="Tenured" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true -->
                        <xpathexpression>string(//xmlRouting/field[@name=\'tenured\']/value) = wf:ruledata(\'tenured\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="exportControls" title="Export Controls" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true -->
                        <xpathexpression>string(//xmlRouting/field[@name=\'exportControls\']/value) = wf:ruledata(\'exportControls\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="inventions" title="Inventions" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/> 
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true -->
                        <xpathexpression>string(//xmlRouting/field[@name=\'inventions\']/value) = wf:ruledata(\'inventions\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
                <fieldDef name="effDate" title="Effective Date" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true -->
                        <xpathexpression>string(//xmlRouting/field[@name=\'effDate\']/value) = wf:ruledata(\'effDate\')</xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
            </routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','ProtocolWorkflowTypeAttribute','ProtocolWorkflowTypeAttribute','ProtocolWorkflowTypeAttribute','DD5FE913-8C08-D714-EBC6-49E8D98055C3',1688,'RuleXmlAttribute','KC',7,'<routingConfig>
                <fieldDef name="protocolWorkflowType" title="protocolWorkflowType" workflowType="RULE">
                    <display>
                        <type>text</type>
                    </display>
                    <validation required="false"/>
                    <fieldEvaluation>
                        <!-- expectation: this is run and evaluates to true  -->
                        <xpathexpression>
                        wf:xstreamsafe(\'//document/protocolWorkflowType\') = wf:ruledata(\'protocolWorkflowType\')
                        </xpathexpression>
                    </fieldEvaluation>
                </fieldDef>
            </routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.role.XPathQualifierResolver','ProposalPersons-XPathQualifierResolver','ProposalPersons-XPathQualifierResolver','ProposalPersons-XPathQualifierResolver','B468D2BF-61BB-8E35-465B-301539F13130',1709,'QualifierResolver','KC',8,'<resolverConfig>
				<qualifier name="proposal">
					<xPathExpression>//document/developmentProposalList[1]/org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal[1]/proposalNumber[1]</xPathExpression>
				</qualifier>
			</resolverConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.role.XPathQualifierResolver','DepartmentRouting-XPathQualifierResolver','DepartmentRouting-XPathQualifierResolver','DepartmentRouting-XPathQualifierResolver','EA894159-2489-B37B-31DE-E263D4411C6D',1710,'QualifierResolver','KC',3,'<resolverConfig>
				<qualifier name="unitNumber">
					<xPathExpression>//document/developmentProposalList[1]/org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal[1]/ownedByUnitNumber[1]</xPathExpression>
				</qualifier>
			</resolverConfig>')
/
delimiter ;
