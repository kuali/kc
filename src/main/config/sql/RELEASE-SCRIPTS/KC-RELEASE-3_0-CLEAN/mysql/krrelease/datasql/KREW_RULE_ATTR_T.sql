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
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','AwardCustomDataAttribute','AwardCustomDataAttribute','AwardCustomDataAttribute','221DF188-E76B-5E05-E649-E06307DBD0BD',1645,'RuleXmlAttribute','KC',3,'<routingConfig>
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
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.kra.irb.ProtocolQualifierResolver','To limit the Approval Request to be routed to ProtocolApprovers for this protocol only','ProtocolApprover-XPathQualifierResolver','ProtocolApprover-XPathQualifierResolver','E206BE62-006E-BECD-1D84-458D4E8DD463',1646,'QualifierResolver','KC',3,'<resolverConfig>
				<qualifier name="protocol">
					<xPathExpression>//document/protocolList[1]/org.kuali.kra.irb.Protocol[1]/protocolNumber[1]</xPathExpression>
				</qualifier>
				<qualifier name="unitNumber">
					<xPathExpression>//document/protocolList[1]/org.kuali.kra.irb.Protocol[1]/leadUnitNumber[1]</xPathExpression>
				</qualifier>
			</resolverConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','ProtocolWorkflowTypeAttribute','ProtocolWorkflowTypeAttribute','ProtocolWorkflowTypeAttribute','7C49D57E-E219-284C-5F6F-7F79A14191A6',1647,'RuleXmlAttribute','KC',3,'<routingConfig>
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
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','UnitApprovalAttribute','ProtocolUnitApprovalAttribute','ProtocolUnitApprovalAttribute','E1C55B25-E23C-7BFC-64C4-1CBD94EC8946',1648,'RuleXmlAttribute','KC',3,'<routingConfig>
				<fieldDef name="leadUnitNumber" title="Lead Unit" workflowType="RULE">
					<display>
						<type>text</type>
					</display>
					<validation required="false"/>
					<fieldEvaluation>
						<xpathexpression>
						  	wf:xstreamsafe(\'//document/protocolList[1]/org.kuali.kra.irb.Protocol[1]/leadUnitNumber[1]\')= wf:ruledata(\'leadUnitNumber\') and
						  	wf:xstreamsafe(\'//document/protocolWorkflowType\') = wf:ruledata(\'protocolWorkflowType\')
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
			</routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,VER_NBR)
  VALUES ('org.kuali.kra.workflow.UnitAdministratorRoleAttribute','Protocol Unit Routing','Protocol Unit Routing','UnitAdministratorRoleAttribute','DD727608-CB26-39DF-8F8B-A17987E94EEB',1649,'RuleAttribute',3)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','ProtocolReRouteAttribute - Indicates if the document is being rerouted','ProtocolReRouteAttribute','ProtocolReRouteAttribute','9547F7C9-B0C7-AE91-3680-559906715869',1650,'RuleXmlAttribute','KC',3,'<routingConfig>
				<fieldDef name="reRouted" title="Re-Routed" workflowType="RULE">
					<display>
						<type>text</type>
					</display>
					<validation required="false"/>
					<fieldEvaluation>
						<xpathexpression>
						  	wf:xstreamsafe(\'//document/reRouted\')= wf:ruledata(\'reRouted\')
						</xpathexpression>
					</fieldEvaluation>
				</fieldDef>
			</routingConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR)
  VALUES ('org.kuali.kra.workflow.CopyCustomActionListAttribute','Copy Action List Attribute','Copy Action List Attribute','CopyCustomActionListAttribute','5DBCF1B1-59FF-8C05-0EC6-6DF8BB60051E',1651,'ActionListAttribute','KC',3)
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','AggregatorSearchAttribute','AggregatorSearchAttribute','AggregatorSearchAttribute','3C91AFDF-AD0A-6442-D700-4E845202215D',1652,'SearchableXmlAttribute','KC',3,'<searchingConfig>
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
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','BudgetCreatorSearchAttribute','BudgetCreatorSearchAttribute','BudgetCreatorSearchAttribute','DC2C5735-FE46-B920-CBA5-4B233FD654BE',1653,'SearchableXmlAttribute','KC',3,'<searchingConfig>
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
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','NarrativeWriterSearchAttribute','NarrativeWriterSearchAttribute','NarrativeWriterSearchAttribute','0BAB73E6-E211-CA30-0AC3-5CAD478309ED',1654,'SearchableXmlAttribute','KC',3,'<searchingConfig>
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
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','ViewerSearchAttribute','ViewerSearchAttribute','ViewerSearchAttribute','0D753240-7D55-F92B-1721-41ABFC7AA8B3',1655,'SearchableXmlAttribute','KC',3,'<searchingConfig>
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
  VALUES ('org.kuali.rice.kew.docsearch.xml.StandardGenericXMLSearchableAttribute','ProposalNumberSearchAttribute','ProposalNumberSearchAttribute','ProposalNumberAttribute','ACA77652-99C2-8AE2-FAB9-F76ED701A86D',1656,'SearchableXmlAttribute','KC',3,'<searchingConfig>
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
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','DepartmentalApprovalAttribute','DepartmentalApprovalAttribute','DepartmentalApprovalAttribute','AE339C79-ACC9-9E30-DEDF-38310EF4E8AC',1657,'RuleXmlAttribute','KC',3,'<routingConfig>
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
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','DepartmentalBudgetApprovalAttribute','DepartmentalBudgetApprovalAttribute','DepartmentalBudgetApprovalAttribute','A99D537A-ED7B-026F-9E99-F8229D4172DD',1658,'RuleXmlAttribute','KC',3,'<routingConfig>
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
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.rule.xmlrouting.StandardGenericXMLRuleAttribute','CustomDataAttribute','CustomDataAttribute','CustomDataAttribute','5D69FB7B-AAE6-082B-2615-C04BA0F0FD15',1659,'RuleXmlAttribute','KC',3,'<routingConfig>
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
  VALUES ('org.kuali.rice.kew.role.XPathQualifierResolver','ProposalPersons-XPathQualifierResolver','ProposalPersons-XPathQualifierResolver','ProposalPersons-XPathQualifierResolver','5BA2E59E-366C-042A-B491-EA824FF6F53D',1660,'QualifierResolver','KC',3,'<resolverConfig>
				<qualifier name="proposal">
					<xPathExpression>//document/developmentProposalList[1]/org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal[1]/proposalNumber[1]</xPathExpression>
				</qualifier>
			</resolverConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.role.XPathQualifierResolver','DepartmentRouting-XPathQualifierResolver','DepartmentRouting-XPathQualifierResolver','DepartmentRouting-XPathQualifierResolver','BF564EA2-7D4F-C0B2-A439-DE0059DFEF25',1661,'QualifierResolver','KC',3,'<resolverConfig>
				<qualifier name="unitNumber">
					<xPathExpression>//document/developmentProposalList[1]/org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal[1]/ownedByUnitNumber[1]</xPathExpression>
				</qualifier>
			</resolverConfig>')
/
INSERT INTO KREW_RULE_ATTR_T (CLS_NM,DESC_TXT,LBL,NM,OBJ_ID,RULE_ATTR_ID,RULE_ATTR_TYP_CD,SVC_NMSPC,VER_NBR,XML)
  VALUES ('org.kuali.rice.kew.role.XPathQualifierResolver','ProtocolOnlineReviewReviewer-XPathQualifierResolver','ProtocolOnlineReviewReviewer-XPathQualifierResolver','ProtocolOnlineReviewReviewer-XPathQualifierResolver','0ADFF93A-6F1F-B185-A207-F95585873B73',1662,'QualifierResolver','KC',3,'<resolverConfig>
				<qualifier name="protocolReviewerId">
					<xPathExpression>//document/protocolOnlineReviewList[1]/org.kuali.kra.irb.onlinereview.ProtocolOnlineReview[1]/protocolReviewerId</xPathExpression>
				</qualifier>

				<qualifier name="protocolOnlineReviewId">
					<xPathExpression>//document/protocolOnlineReviewList[1]/org.kuali.kra.irb.onlinereview.ProtocolOnlineReview[1]/protocolOnlineReviewId</xPathExpression>
				</qualifier>

				<qualifier name="submissionId">
					<xPathExpression>//document/protocolOnlineReviewList[1]/org.kuali.kra.irb.onlinereview.ProtocolOnlineReview[1]/submissionIdFk</xPathExpression>
				</qualifier>

				<qualifier name="protocol">
					<xPathExpression>//document/protocolOnlineReviewList[1]/org.kuali.kra.irb.onlinereview.ProtocolOnlineReview[1]/protocol/protocolNumber</xPathExpression>
				</qualifier>

			</resolverConfig>')
/
delimiter ;
