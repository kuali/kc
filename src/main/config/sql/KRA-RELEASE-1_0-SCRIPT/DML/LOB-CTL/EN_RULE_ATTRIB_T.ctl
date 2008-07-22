load data
infile *
into table EN_RULE_ATTRIB_T
APPEND
fields terminated by ',' optionally enclosed by "'"
TRAILING NULLCOLS 
(RULE_ATTRIB_ID, 
RULE_ATTRIB_NM, 
RULE_ATTRIB_LBL_TXT, 
RULE_ATTRIB_TYP, 
RULE_ATTRIB_DESC, 
RULE_ATTRIB_CLS_NM, 
RULE_ATTRIB_XML_RTE_TXT LOBFILE(constant "DML/LOB-CTL/LOB-DATA/EN_RULE_ATTRIB_T-RULE_ATTRIB_XML_RTE_TXT.dat") TERMINATED BY '<!--EOR-->', 
DB_LOCK_VER_NBR, 
MESSAGE_ENTITY_NM
)
BEGINDATA
'1000', 'AggregatorSearchAttribute', 'AggregatorSearchAttribute', 'SearchableXmlAttribute', 'AggregatorSearchAttribute', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '16'
'1001', 'BudgetCreatorSearchAttribute', 'BudgetCreatorSearchAttribute', 'SearchableXmlAttribute', 'BudgetCreatorSearchAttribute', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '16'
'1002', 'NarrativeWriterSearchAttribute', 'NarrativeWriterSearchAttribute', 'SearchableXmlAttribute', 'NarrativeWriterSearchAttribute', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '16'
'1003', 'ViewerSearchAttribute', 'ViewerSearchAttribute', 'SearchableXmlAttribute', 'ViewerSearchAttribute', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '16'
'1004', 'ApproverSearchAttribute', 'ApproverSearchAttribute', 'SearchableXmlAttribute', 'ApproverSearchAttribute', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '8'
'1062', 'NotificationChannelSearchableAttribute', 'Notification Channel', 'SearchableXmlAttribute', 'The associated channel that this message was sent on.', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '1'
'1063', 'NotificationPrioritySearchableAttribute', 'Notification Priority', 'SearchableXmlAttribute', 'The priority of this notification.', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '1'
'1064', 'NotificationContentTypeSearchableAttribute', 'Notification Content Type', 'SearchableXmlAttribute', 'The content type of this notification.', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '1'
'1065', 'NotificationProducerSearchableAttribute', 'Notification Producer', 'SearchableXmlAttribute', 'The producer of this notification.', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '1'
'1066', 'NotificationRecipientsSearchableAttribute', 'Notification Recipient', 'SearchableXmlAttribute', 'Those who are receiving this notification.', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '1'
'1067', 'NotificationSendersSearchableAttribute', 'Notification Senders', 'SearchableXmlAttribute', 'Those who this notification is being sent on behalf of.', 'edu.iu.uis.eden.docsearch.xml.StandardGenericXMLSearchableAttribute', '1'
'1005', 'CustomDataAttribute', 'CustomDataAttribute', 'RuleXmlAttribute', 'CustomDataAttribute', 'edu.iu.uis.eden.routetemplate.xmlrouting.StandardGenericXMLRuleAttribute', '14'
'1140', 'DepartmentalApprovalAttribute', 'DepartmentalApprovalAttribute', 'RuleXmlAttribute', 'DepartmentalApprovalAttribute', 'edu.iu.uis.eden.routetemplate.xmlrouting.StandardGenericXMLRuleAttribute', '9'
'1180', 'DepartmentalBudgetApprovalAttribute', 'DepartmentalBudgetApprovalAttribute', 'RuleXmlAttribute', 'DepartmentalBudgetApprovalAttribute', 'edu.iu.uis.eden.routetemplate.xmlrouting.StandardGenericXMLRuleAttribute', '2'
