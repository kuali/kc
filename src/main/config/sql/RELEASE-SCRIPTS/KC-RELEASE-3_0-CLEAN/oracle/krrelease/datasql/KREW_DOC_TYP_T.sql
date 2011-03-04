TRUNCATE TABLE KREW_DOC_TYP_T DROP STORAGE
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Workflow Maintenance Document Type Document',2011,'DocumentTypeDocument',0,'1','default.htm?turl=WordDocuments%2Fdocumenttype.htm','Workflow Maintenance Document Type Document','6166CBA1BA5D644DE0404F8189D86C09',3001,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Rule Maintenance Document Type Document',2012,'RoutingRuleDocument',0,'1','Rule Maintenance Document Type Document','6166CBA1BA5E644DE0404F8189D86C09',3001,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${ken.url}/DetailView.form','This is the re-usable notification document type that will be used for delivering all notifications with KEW.',2023,'KualiNotification',0,'2000','Notification','6166CBA1BA69644DE0404F8189D86C09','org.kuali.rice.ken.postprocessor.kew.NotificationPostProcessor','1',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${ken.url}/AdministerNotificationRequest.form','Create a New Notification Request',2024,'SendNotificationRequest',0,'1','Send Notification Request','6166CBA1BA6A644DE0404F8189D86C09','org.kuali.rice.ken.postprocessor.kew.NotificationSenderFormPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit parameter namespaces',2031,'NamespaceMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fnamespace.htm','Namespace','6166CBA1BA71644DE0404F8189D86C09',3001,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter type',2032,'ParameterTypeMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparametertype.htm','Parameter Type Maintenance Document','6166CBA1BA72644DE0404F8189D86C09',3001,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter detail type',2033,'ParameterDetailTypeMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparametercomponent.htm','Parameter Detail Type Maintenance Document','6166CBA1BA73644DE0404F8189D86C09',3001,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter',2034,'ParameterMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparameter.htm','Parameter Maintenance Document','6166CBA1BA74644DE0404F8189D86C09',3001,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'KualiDocument',2680,'KualiDocument',0,'KualiDocument','6166CBA1BA81644DE0404F8189D86C09','org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'Parent Document Type for all Rice Documents',2681,'RiceDocument',0,'Rice Document','6166CBA1BA82644DE0404F8189D86C09',3000,'2',2)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','Routing Rule Delegation',2699,'RoutingRuleDelegationMaintenanceDocument',0,'Routing Rule Delegation','A6DC8753-AF90-7A01-0EF7-E6D446529668',3001,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',2708,'CampusMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcampus.htm','CampusMaintenanceDocument','616D94CA-D08D-D036-E77D-4B53DB34CD95',3001,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',2709,'CampusTypeMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcampustype.htm','CampusTypeMaintenanceDocument','DE0B8588-E459-C07A-87B8-6ACD693AE70C',3001,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',2710,'CountryMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcountry.htm','CountryMaintenanceDocument','82EDB593-97BA-428E-C6E7-A7F3031CFAEB',3001,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',2711,'CountyMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcounty.htm','CountyMaintenanceDocument','C972E260-5552-BB63-72E6-A514301B0326',3001,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',2712,'PostalCodeMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fpostalcode.htm','PostalCodeMaintenanceDocument','B79D1104-BC48-1597-AFBE-773EED31A110',3001,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',2713,'StateMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fstate.htm','StateMaintenanceDocument','EF2378F6-E770-D7BF-B7F1-C18881E3AFF0',3001,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,2994,'IdentityManagementDocument',0,'Undefined','917DA614-1BED-D8B4-F3BA-760EB30DF553',3001,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kim.url}/identityManagementRoleDocument.do?methodToCall=docHandler',2995,'IdentityManagementRoleDocument',0,'default.htm?turl=WordDocuments%2Frole.htm','Role','6A96BB4F-13F2-72D0-5D12-723E09416097',2994,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kim.url}/identityManagementGroupDocument.do?methodToCall=docHandler',2996,'IdentityManagementGroupDocument',0,'default.htm?turl=WordDocuments%2Fgroup.htm','Group','8A4693A2-B198-1DA8-5258-E22D29F9EEC4',2994,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kim.url}/identityManagementPersonDocument.do?methodToCall=docHandler',2997,'IdentityManagementPersonDocument',0,'default.htm?turl=WordDocuments%2Fperson.htm','Person','A9B573D5-AFF9-95F4-AF12-1055A26CCB9A',2994,'2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',2998,'IdentityManagementReviewResponsibilityMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fresponsibility.htm','Review Responsibility','05C15C71-A466-36FF-2339-A6D303C268F7',2994,'2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kr.url}/maintenance.do?methodToCall=docHandler',2999,'IdentityManagementGenericPermissionMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fpermission.htm','Permission','93B3B080-E14E-1836-67D0-0AC58A57E61B',2994,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${application.url}/kr/maintenance.do?methodToCall=docHandler','KualiDocument',3000,'KualiDocument',1,'KualiDocument','EA406932-75F2-0B3A-0095-83F2E5AF448E','org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor',2680,'2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent Document Type for all Rice Documents',3001,'RiceDocument',1,'Rice Document','F9749768-A384-2439-8C87-5B520EB07AF8',3000,2681,'2',4)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,VER_NBR)
  VALUES (1,1,3002,'KC',0,'Undefined','95CC2BC2-54C5-2FFA-D4E2-587269234A4C',3000,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2','KC',11)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'10000',1,'${kuali.docHandler.url.prefix}/awardBudgetVersions.do?methodToCall=docHandler','Create an Award Budget',3003,'AwardBudgetDocument',0,'10000','Award Budget Document','D9DB7D96-A0DA-6943-6606-10CE95E2FF25',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'10000',1,'${kuali.docHandler.url.prefix}/budgetVersions.do?methodToCall=docHandler','Create a Budget',3004,'BudgetDocument',0,'10000','default.htm?turl=Documents/budget.htm','Budget Document','EADBE590-2A57-FAD7-E7DF-254537B2931E',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'10000',1,'${kuali.docHandler.url.prefix}/kr/maintenance.do?methodToCall=docHandler','Parent of maintenance documents in KC',3005,'KcMaintenanceDocument',0,'10000','KcMaintenanceDocument','8922BF75-F35F-C0A6-A993-162EB2384375',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',24)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'10000',1,'${kuali.docHandler.url.prefix}/institutionalProposalHome.do?methodToCall=docHandler','Manage Institutional Proposal',3006,'InstitutionalProposalDocument',0,'10000','default.htm?turl=Documents/institutionalproposal.htm','KC Institutional Proposal','5BD91169-5A88-893D-F814-C6F52F63A2FA',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'10000',1,'${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','Manage Award',3007,'AwardDocument',0,'10000','default.htm?turl=Documents/award1.htm','KC Award','17F426FF-2FFD-486E-7967-5C010CFC3554',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kuali.docHandler.url.prefix}/committeeCommittee.do?methodToCall=docHandler','Create a Committee',3008,'CommitteeDocument',0,'default.htm?turl=Documents/committee.htm','Committee Document','3FE93CEC-47EF-1D8D-1023-CD7A54683FCD',3002,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'10003',1,'${kuali.docHandler.url.prefix}/protocolProtocol.do?methodToCall=docHandler','Create a KC Protocol Document',3009,'ProtocolDocument',0,'10003','default.htm?turl=Documents/protocol.htm','KC Protocol','E91F9C34-7F02-327A-BF62-4A5D148EE419',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${application.url}/kim/identityManagementGroupDocument.do?methodToCall=docHandler',3010,'IdentityManagementGroupDocument',1,'default.htm?turl=WordDocuments%2Fgroup.htm','Group','496DF571-5E84-5B5B-C4E7-DCBE49ABA2F9',3001,2996,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${application.url}/kim/identityManagementRoleDocument.do?methodToCall=docHandler',3011,'IdentityManagementRoleDocument',1,'default.htm?turl=WordDocuments%2Frole.htm','Role','49C810DB-4B17-A17A-B73D-E02955AF2C22',3001,2995,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',3012,'IdentityManagementGenericPermissionMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fpermission.htm','Permission','86B068E6-06B6-6889-6572-28C35B95FC55',3001,2999,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'10000',1,'${kuali.docHandler.url.prefix}/timeAndMoney.do?methodToCall=docHandler','Manage TimeAndMoney',3013,'TimeAndMoneyDocument',0,'10000','KC TimeAndMoney','5496F340-393A-6F59-7D94-502EC4EC89B2',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kuali.docHandler.url.prefix}/proposalDevelopmentProposal.do?methodToCall=docHandler','Create an Awesome Development Proposal',3014,'ProposalDevelopmentDocument',0,'10001','default.htm?turl=Documents/proposaldevelopment.htm','Proposal Development Document','9C053423-F7E2-F628-1924-B76EF3AA3C01',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'10003',1,'${kuali.docHandler.url.prefix}/protocolOnlineReviewRedirect.do?methodToCall=redirectToProtocolFromReview','Create a KC Protocol Review Document',3015,'ProtocolOnlineReviewDocument',0,'10003','KC Protocol Review','8177A59F-29E8-70EA-F87D-FE04ECFE9087',3002,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Person Appointment entry',3016,'PersonAppointmentMaintenanceDocument',0,'Person Appointment','803FDD9A-6F0E-4128-AB46-873A2F1012BD',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New TBN Person',3017,'TbnPersonMaintenanceDocument',0,'To Be Named Person','29002833-974A-8C5E-76B1-08C21B616AB0',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Term Type',3018,'SponsorTermTypeMaintenanceDocument',0,'Sponsor Term Type','0D5D4E2B-2AF6-816E-1007-890F061CEAB2',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Exemption Type Code',3019,'ExemptionTypeMaintenanceDocument',0,'Exemption Type','0D8E8A51-6AEA-F4BC-4BF3-0266EEBA58CE',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Proposal Response',3020,'ProposalResponseMaintenanceDocument',0,'Proposal Response','1B564B08-F0D8-E8C1-4441-096E62BA121D',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Transaction Type',3021,'Award Transaction Type Maintenance Document',0,'Award Transaction Type','6714195D-B4E7-84D6-C9B6-240E36053AEB',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a new Proposal Log',3022,'ProposalLogMaintenanceDocument',0,'Proposal Log','2111A60A-E6D1-D1AC-8C17-A85D9318F44D',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of Miscellaneous maintenance documents in KC',3023,'KcProposalsMaintenanceDocument',0,'KcProposalsMaintenanceDocument','C17AB2FC-AF9A-3B27-1B30-269AF245BFE6',3005,'2',15)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a KC Person Extended Attributes entry',3024,'KcPersonExtendedAttributesMaintenanceDocument',0,'Person','520F3DC5-ADD9-4217-06F5-BDAFC0C2B131',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Person Editable Field',3025,'PersonEditableFieldMaintenanceDocument',0,'default.htm?turl=Documents/persontableeditablecolumns.htm','Person Editable Field','930554C7-FEF6-F92B-8A5D-C9C53EB305D5',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of Awards maintenance documents in KC',3026,'KcAwardsMaintenanceDocument',0,'KcAwardsMaintenanceDocument','C4BAC7E1-E293-9A60-E7DC-6B76A61E59CE',3005,'2',26)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Carrier Type',3027,'CarrierTypeMaintenanceDocument',0,'Carrier Type','D9897C9A-2833-87AB-762E-98A2D800DCC2',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Prop Location',3028,'PropLocationMaintenanceDocument',0,'Prop Location','572623F8-B8AA-A749-6AB7-D9B2877C1D06',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Person Degree entry',3029,'PersonDegreeMaintenanceDocument',0,'Person Degree','C31B09AA-D396-0266-478B-BD2DF410D915',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Proposal Column To Alter',3030,'ProposalColumnsToAlterMaintenanceDocument',0,'Proposal Column To Alter','C5626650-378B-3737-E189-41DA878DE49E',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Proposal Person Document Type',3031,'PropPerDocTypeMaintenanceDocument',0,'Proposal Person Document Type','DB4FC1C8-459E-0004-18A2-1A9E9DAFF823',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Unit',3032,'UnitMaintenanceDocument',0,'Unit','AEBA4D7B-479A-C90E-5AD7-EC8E29ECC76B',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of Compliance maintenance documents in KC',3033,'KcComplianceMaintenanceDocument',0,'KcComplianceMaintenanceDocument','A9D20426-271C-BDC4-6F5D-3EF629E7892F',3005,'2',24)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Risk Level',3034,'RiskLevelMaintenanceDocument',0,'Risk Level Maintenance Document','03AEA176-83A0-0D3E-BCDF-487C03839F10',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of shared maintenance documents in KC',3035,'KcSharedMaintenanceDocument',0,'KcSharedMaintenanceDocument','043EFF74-96F5-6D77-B5A6-57D5647D44E9',3005,'2',61)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Proposal Role Template entry',3036,'ProposalRoleTemplateMaintenanceDocument',0,'Proposal Role Template','B32338BC-AB76-3BC2-D140-028B4CE834D9',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Sponsor Term',3037,'SponsorTermMaintenanceDocument',0,'Award Sponsor Term','5E229B6B-FEEE-509F-B981-0DBAF562F003',3005,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of Miscellaneous maintenance documents in KC',3038,'KcMiscellaneousMaintenanceDocument',0,'KcMiscellaneousMaintenanceDocument','0EDDB547-230A-4746-F41E-A42BF225BCB6',3005,'2',14)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Hierarchy',3039,'SponsorHierarchyMaintenanceDocument',0,'Sponsor Hierarchy','BBCA8085-520C-A5B5-1CD1-7145500746D2',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kuali.docHandler.url.prefix}/maintenanceQn.do?methodToCall=docHandler','Create/Edit a Questionnaire',3040,'QuestionnaireMaintenanceDocument',0,'default.htm?turl=Documents/questionnaire1.htm','Questionnaire','7C98CCCE-28AF-E895-ED5D-62615836D8E9',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Custom Attribute Document',3041,'CustomAttributeDocumentMaintenanceDocument',0,'default.htm?turl=Documents/customattribute.htm','CustomAttributeDocument Maintenance Document','0BEFE138-BE37-03A6-CE76-7C706B104F3F',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Category',3042,'BudgetCategoryMaintenanceDocument',0,'default.htm?turl=Documents/budgetcategory.htm','Budget Category Maintenance Document','8F58C1D2-B898-8B15-4C62-6734C9BC3401',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Organization Type',3043,'OrganizationTypeMaintenanceDocument',0,'Organization','304A81EC-3591-3F99-182B-04E05BECA784',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Report Class',3044,'ReportClassMaintenanceDocument',0,'Report Class','C3C6AD2F-EE99-C6FE-27A7-CEBBA5A53979',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Deadline Type',3045,'DeadlineTypeMaintenanceDocument',0,'default.htm?turl=Documents/deadlinetype.htm','Deadline Type','CBA714E4-2621-82FA-C5D8-FA722F45A7CB',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Mail By',3046,'MailByMaintenanceDocument',0,'default.htm?turl=Documents/mailby.htm','Mail By','3FAB4E1E-3AC1-27C4-9D29-90B906AD7CB0',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Science Keyword',3047,'ScienceKeywordMaintenanceDocument',0,'default.htm?turl=Documents/keywords.htm','Science Keyword','FCFAA871-38BB-86C7-A14E-16F3B6B9768C',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Status',3048,'AwardStatusMaintenanceDocument',0,'Award Status Type','406A6584-8DD2-4223-B2F8-1E13CA2149D0',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a F & A Rate Type',3049,'FandaRateTypeMaintenanceDocument',0,'F & A Rate Type','A658F446-0DF7-B934-A7AC-46D8BEEB82FC',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Degree Type entry',3050,'DegreeTypeMaintenanceDocument',0,'default.htm?turl=Documents/degreetype.htm','Degree Type','02909F47-7FF2-897F-E03B-5F870F6780B4',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Funding Source Type',3051,'FundingSourceTypeMaintenanceDocument',0,'Funding Source Type','4F428CA0-BCB1-9A29-2D26-42DAC845DAAE',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Expedited Review CheckList Item',3052,'ExpeditedReviewCheckListMaintenanceDocument',0,'Expedited Review CheckList Item','1E8484AF-C78B-A67F-FF4C-AC1392EFC539',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Cost Element',3053,'CostElementMaintenanceDocument',0,'default.htm?turl=Documents/costelement.htm','Cost Element Maintenance Document','281E246D-5E68-52D8-2A0D-E1854C1EC30A',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Rate Class Type',3054,'RateClassTypeMaintenanceDocument',0,'default.htm?turl=Documents/rateclasstype.htm','Rate Class Type Maintenance Document','751C9CB8-6E33-9168-7576-748107F3C4E0',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Notice of Opportunity',3055,'NoticeOfOpportunityMaintenanceDocument',0,'default.htm?turl=Documents/noticeofopportunity.htm','Notice of Opportunity','12DBEBD7-4A82-B597-3C0A-92D067802DAC',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Proposal Type Code',3056,'ProposalTypeMaintenanceDocument',0,'default.htm?turl=Documents/proposaltype.htm','Proposal Type','1E7CB78D-93D5-7135-F86C-D4CCFF572A6F',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Rate Type',3057,'RateTypeMaintenanceDocument',0,'default.htm?turl=Documents/ratetype.htm','Rate Type Maintenance Document','CB3A0CA1-0B24-9E99-44D2-83970DE0023B',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Questionnaire Questions',3058,'Questionnaire Questions Maintenance Document',0,'Questionnaire Questions','471CAE93-87F8-7C3D-70C4-7DE9802DD1CF',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Protocol Reviewer Type',3059,'ProtocolReviewerTypeMaintenanceDocument',0,'Protocol Reviewer Type','44337036-4CB9-D975-362E-9A7C2F4F5FEC',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Terms',3060,'AwardTemplateTermMaintenanceDocument',0,'Sponsor Template Report Terms','3B2B03CE-51E3-EF97-64FC-C20976B5B9C2',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Attachment Group',3061,'ProtocolAttachmentGroupMaintenanceDocument',0,'Protocol Attachment Group Maintenance Document','95E23AEC-700B-5A8C-F3AD-079645300CFC',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Valid Rate',3062,'ValidRatesMaintenanceDocument',0,'Valid Rates','C3615539-524D-A323-0153-74C688A16373',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Form',3063,'SponsorFormsMaintenanceDocument',0,'Sponsor Form','797B6315-CD78-AC32-CFFD-8858A6D49656',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Category Mapping',3064,'BudgetCategoryMappingMaintenanceDocument',0,'default.htm?turl=Documents/budgetcategorymapping.htm','Budget Category Mapping Maintenance Document','D2870E18-E6F3-D7D3-C18E-F0458DBA839A',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Attachment Type',3065,'ProtocolAttachmentTypeMaintenanceDocument',0,'Protocol Attachment Type Maintenance Document','08A923AF-EE52-9D18-3321-6A34277BA967',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Frequency Base',3066,'FrequencyBaseMaintenanceDocument',0,'Frequency Base','0BD8CFC0-9A54-9C91-557B-6C06C7CB546E',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template',3067,'AwardTemplateMaintenanceDocument',0,'Sponsor Template','A69EA0D5-5719-ADD2-67CE-7253DA568134',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Correspondence Template',3068,'ProtocolCorrespondenceTemplateMaintenanceDocument',0,'Correspondence Template','ABAEC8AF-3BC0-E6E4-90FC-D2871F837798',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New State',3069,'KcStateMaintenanceDocument',0,'default.htm?turl=Documents/state.htm','State','68AF47CB-F438-84D1-21C7-1F9384ACA444',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Basis Of Payment',3070,'AwardBasisOfPaymentMaintenanceDocument',0,'Award Basis Of Payment','BB0406ED-B0F8-63C5-EED8-3D2A88CB552F',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Category Type',3071,'BudgetCategoryTypeMaintenanceDocument',0,'default.htm?turl=Documents/budgetcategorytype.htm','Budget Category Type Maintenance Document','1AC11D97-2030-DA81-24D7-662206054564',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Abstract Type',3072,'AbstractTypeMaintenanceDocument',0,'default.htm?turl=Documents/abstracttype.htm','Abstract Type','C58A8E47-61FF-8958-B149-5A59BD7C804C',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Question Type',3073,'QuestionTypeMaintenanceDocument',0,'Questionnaire Type','FBCA55A6-1F76-A57F-CBA2-5BEB62910866',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Organization',3074,'OrganizationMaintenanceDocument',0,'default.htm?turl=Documents/organization.htm','Organization Maintenance Document','1AF9190C-374A-44FB-8DC4-7A2204D36846',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Person entry',3075,'PersonMaintenanceDocument',0,'default.htm?turl=Documents/personmaintenancedocument.htm','Person','B7EE97B7-F73B-DBB9-07F8-3EFEC7A12963',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Committee Type',3076,'CommitteeTypeMaintenanceDocument',0,'Committee Type','181D19EC-4695-A0BE-2480-353462A4B0AA',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Osp Distribution',3077,'DistributionMaintenanceDocument',0,'Osp Distribution','10E8A8BB-920C-8C1E-889D-7246D66F819F',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Frequency',3078,'FrequencyMaintenanceDocument',0,'Frequency','230E512F-A872-7CC0-DD48-B6AD04590645',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Institute Rate',3079,'InstituteRateMaintenanceDocument',0,'default.htm?turl=Documents/instituterate.htm','Institute Rates Maintenance Document','5853DC26-7AAD-391F-4256-B2AEF6BF6A3A',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kuali.docHandler.url.prefix}/maintenance.do?methodToCall=docHandler','Create/Edit a Question',3080,'QuestionMaintenanceDocument',0,'default.htm?turl=Documents/question.htm','Question','10D3B448-F16F-A150-F2E6-696F237C9B63',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Batch Correspondence',3081,'BatchCorrespondenceMaintenanceDocument',0,'Batch Correspondence','22E2C34B-D424-12A0-A647-01F0ED9C3975',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Research Area',3082,'ResearchAreasMaintenanceDocument',0,'Report Area','9E2405F8-3072-D728-B5C2-D047B8558F8A',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit Valid Protocol Submission Review Type',3083,'ValidProtoSubRevTypeMaintenanceDocument',0,'Valid Protocol Submission Review Type','5A694524-948E-134B-AA82-74126A186A7E',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Review Type',3084,'ProtocolReviewTypeMaintenanceDocument',0,'Protocol Review Type','D7E90172-BAAF-0196-D776-FCB08787092E',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Post Submission Status Code',3085,'PostSubmissionStatusMaintenanceDocument',0,'Post Submission Status Code','7AEA4FA2-1ABC-AF05-0228-772417DCB68F',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New ProtocolOnlineReviewDeterminationRecommendation',3086,'ProtocolOnlineReviewDeterminRecommendMaintenanceDocument',0,'Protocol Review Determination Recommendation Code','97897B98-1861-1B6B-A8D3-EB982F08FFAC',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Type',3087,'ProtocolTypeMaintenanceDocument',0,'Protocol Type Maintenance Document','76B1B061-A9BF-9BEF-B31A-2E165AD684B5',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create an Affiliation Type',3088,'KcAffiliationTypeMaintenanceDocument',0,'Affiliation Type Maintenance Document','B8C972CC-AC9D-9632-FA54-42A1654012A4',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Participant Type',3089,'ParticipantTypeMaintenanceDocument',0,'Participant Type','310199D9-6C08-C805-BB68-4CA8BE10B7D9',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Questionnaire Usage',3090,'Questionnaire Usage Maintenance Document',0,'Questionnaire Usage','586A6719-8256-7A0F-F991-9608780CC047',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Organization Type Code',3091,'OrganizationTypeListMaintenanceDocument',0,'Organization','A7A849F1-9D52-A6AB-25D9-A1E7498AAC09',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit Valid Protocol Submission Type Qualifier',3092,'ValidProtoSubTypeQualMaintenanceDocument',0,'Valid Protocol Submission Type Qualifier','576AD04D-124E-D280-68E2-D2C71D975606',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Job Code',3093,'JobCodeMaintenanceDocument',0,'Job Code','118600C2-4B90-08E3-279F-ADD39C827EF4',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Award Basis Payment business objects.',3094,'ValidAwardBasisPaymentMaintenanceDocument',0,'Valid Award Basis Payment Maintenance Document','57E6618E-33E4-DF3E-401B-D2048DB4FCC2',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Award Attachment Type',3095,'AwardAttachmentTypeMaintenanceDocument',0,'Award Attachment Type','D278A98D-4A61-6A39-2E83-57AA4E7C192A',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Appointment Type',3096,'AppointmentTypeMaintenanceDocument',0,'default.htm?turl=Documents/appointmenttype.htm','Appointment Type','3EC24884-79FE-3590-DC22-AA637B68A02A',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Exempt Studies CheckList Item',3097,'ExemptStudiesCheckListMaintenanceDocument',0,'Exempt Studies CheckList Item','68D0A694-4A79-4C8F-6889-A207F0A8F23B',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update message of the day documents.',3098,'MessageOfTheDayMaintenanceDocument',0,'Message Of The Day Maintenance Document','EA4E6E13-3FFF-8620-A637-90548B067523',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New IP Review Activity Type',3099,'IntellectualPropertyReviewActivityTypeMaintenanceDocument',0,'IP Review Activity Type','9104B3AB-57D4-7AF2-EFF3-4E4A3C7E20AB',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Proposal Status business objects.',3100,'Proposal Status Maintenance Document',0,'Proposal Status Maintenance Document','D7C23C59-97E5-AD1F-D87A-5B29B03C7F6C',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Action Type',3101,'ProtocolActionTypeMaintenanceDocument',0,'Protocol Action Type','2DD0A02E-80B0-E7F8-98B5-23C63C689F72',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Valid Cost Element Job Code',3102,'ValidCeJobCodeMaintenanceDocument',0,'Valid Cost Element Job Code','9D59656C-1F7B-0DD4-EC70-410714C5D453',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Protocol Contingency',3103,'ProtocolContingencyMaintenanceDocument',0,'Protocol Contingency','1F5D9F04-0021-361E-9CEC-D272BFCE66B7',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Organization Type',3104,'Protocol Organization Type Maintenance Document',0,'Protocol Protocol Organization Type','0432841D-7D66-C75F-2C1D-FCCB520C3222',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Status',3105,'ProtocolStatusMaintenanceDocument',0,'Protocol Status Maintenance Document','9880AB62-4BF8-AF83-3885-A3DC303D6B06',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Investigator Credit Type',3106,'InvestigatorCreditTypeMaintenanceDocument',0,'default.htm?turl=Documents/investigatorcredittype.htm','Investigator Credit Type','92D8057A-5015-5EEF-C1F7-D84390085C2D',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Type',3107,'SponsorMaintenanceDocument',0,'default.htm?turl=Documents/sponsor.htm','Sponsor','C7872064-CE33-A060-2D21-24225F6B4DDC',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Comment',3108,'AwardTemplateCommentMaintenanceDocument',0,'Sponsor Template Comment','F0EA44F1-B937-ACE7-B5F6-7F1FE1EF2EC6',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Reference Type',3109,'ProtocolReferenceTypeMaintenanceDocument',0,'Protocol Reference Type','3651DF47-29AA-3755-57CF-4963FA53FE3B',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New IP Review Result Type',3110,'IntellectualPropertyReviewResultTypeMaintenanceDocument',0,'IP Review Result Type','A46903F5-4945-0138-C80A-50F572FE196D',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Contact Type',3111,'ContactTypeMaintenanceDocument',0,'Contact Type','CF2811E4-777F-0706-D670-23F99EB2B280',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Person Training',3112,'PersonTrainingMaintenanceDocument',0,'Person Training Maintenance Document','2805772E-1A46-A0FA-686E-3C86F8E3E7F4',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Revision Type Document',3113,'S2sRevisionTypeMaintenanceDocument',0,'default.htm?turl=Documents/s2srevisiontype.htm','Revision Type Document','0633CFA1-A792-D1DC-50AE-4E24F1A5A082',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Narrative Type',3114,'NarrativeTypeMaintenanceDocument',0,'default.htm?turl=Documents/narrativetypes.htm','Narrative Type','604AC1EE-0C91-E279-4954-A75BD89F44A9',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Attachment Type Group',3115,'ProtocolAttachmentTypeGroupMaintenanceDocument',0,'Protocol Attachment Type Group Maintenance Document','1E565B7D-6161-1A21-4D0B-F8EA7D314B21',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Valid Calculation Type',3116,'ValidCalcTypeMaintenanceDocument',0,'default.htm?turl=Documents/validcalculationtype.htm','Valid Calc Types Maintenance Document','D45CC79C-BE34-E2C8-AAF2-93D58D58D8C7',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Maintain Intellectual Property Review',3117,'IntellectualPropertyReviewMaintenanceDocument',0,'Intellectual Property Review','6696926A-8762-9746-0D42-95A6DF508C16',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Class Report Frequency business objects.',3118,'Valid Class Report Freq Maintenance Document',0,'Valid Class Report Frequency Maintenance Document','5E1AB6F5-6F32-EF5E-22EC-AE7685AA3766',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Closeout Report Type',3119,'CloseoutReportTypeMaintenanceDocument',0,'Closeout Report Type','28EBE7E9-C85F-6D52-710E-3B7170421C06',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Special Review Code',3120,'SpecialReviewMaintenanceDocument',0,'default.htm?turl=Documents/specialreviewapprovaltype.htm','Special Review','3B365C44-B6F3-CF2E-4F9B-541E439765E2',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Valid Cost Element Rate Type',3121,'ValidCeRateTypeMaintenanceDocument',0,'default.htm?turl=Documents/validcostelementratetype.htm','Valid Cost Element Rate Types Maintenance Document','9082102A-F670-238F-5A51-7910E5E4C1C2',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Institute La Rate',3122,'InstituteLaRateMaintenanceDocument',0,'default.htm?turl=Documents/institutelarate.htm','Institute La Rates Maintenance Document','A41240A1-FDB2-3E43-A7E5-5A6574AA4453',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Contact',3123,'AwardTemplateContactMaintenanceDocument',0,'Sponsor Template Contact','E590BA7E-257F-45E3-A18C-F0462D1B556A',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Argument Value Lookup',3124,'ArgValueLookupMaintenanceDocument',0,'Argument Value','424F3A04-3EE3-24D4-1BAE-84A3D94DD380',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Status',3125,'BudgetStatusMaintenanceDocument',0,'default.htm?turl=Documents/budgetstatus.htm','Budget Status','2FC4B048-C986-D06C-146B-E5C39122B325',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Proposal Log Type business objects.',3126,'Proposal Log Type Maintenance Document',0,'Proposal Log Type Maintenance Document','41FC3CA4-94A6-6B2E-93BA-62CAD1A417C4',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit Batch Correspondence Details',3127,'BatchCorrespondenceDetailMaintenanceDocument',0,'Batch Correspondence Detail','6BBFB1E0-5298-C15D-60E3-E7C0F58C8907',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Report Term Recipient',3128,'AwardTemplateReportTermRecipientMaintenanceDocument',0,'Sponsor Template Report Term Recipient','72419FB9-8E14-F9FA-8947-FA08729F11E2',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create an Protocol Submission Qualifier Type',3129,'ProtocolSubmissionQualifierTypeMaintenanceDocument',0,'Protocol Submission Qualifier Type Maintenance Document','DFD5BEBD-4C69-1F86-4FBA-6AACAF64ADEF',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Training',3130,'TrainingMaintenanceDocument',0,'Training','04C929C1-8791-5976-A6A6-E7A830C3E996',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Form Template',3131,'SponsorFormTemplateMaintenanceDocument',0,'Sponsor Form Template','A96676E0-4F49-0FE0-3B6E-72EC3C7C0C40',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New SubmissionType Document Type',3132,'S2sSubmissionTypeMaintenanceDocument',0,'default.htm?turl=Documents/s2ssubmissiontype.htm','Submission Type Document Type','4C79F8ED-4663-5DA4-9B15-882645FF2839',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Comment Type',3133,'CommentTypeMaintenanceDocument',0,'Comment Type','C0F42895-29A6-59DC-EEFE-1A3761F2CAE0',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New NSF Code',3134,'NsfCodeMaintenanceDocument',0,'NSF Code','F591C239-B7FB-FCB9-AA4A-FCD10D798D3D',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit Correspondence Generated for Protocol Action',3135,'ValidProtocolActionCorrespondenceMaintenanceDocument',0,'Correspondence Generated','05359094-82A0-F107-B4F2-16346F1F3D92',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Schedule Act Item Type',3136,'ScheduleActItemTypeMaintenanceDocument',0,'Schedule Act Item Type','6302960E-B0BB-037F-F895-D09687D6AFF8',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Map a Valid Narrative type to an S2S form',3137,'ValidNarrFormsMaintenanceDocument',0,'Valid Narrative Type - S2S Form','A227CDF4-0408-619C-085F-5C5ACCE8941A',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Account Type',3138,'AccountTypeMaintenanceDocument',0,'Account Type','88CCD951-AF78-D74A-DAB4-B6D1740D413A',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Kuali Coeus Sub Module',3139,'Coeus Sub Module Maintenance Document',0,'Kuali Coeus Sub Module','055B315B-07FA-3372-A2B3-E76A19C16817',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Attachment Status',3140,'ProtocolAttachmentStatusMaintenanceDocument',0,'Protocol Attachment Status Maintenance Document','CC9A8116-9659-D2A8-CFE0-CA9FB3B05A2F',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Notification Template',3141,'ProtocolNotificationTemplateMaintenanceDocument',0,'Notification Template','84FCE08D-3D93-C546-25B3-A1A373629536',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Category Maps',3142,'BudgetCategoryMapMaintenanceDocument',0,'default.htm?turl=Documents/budgetcategorymaps.htm','Budget Category Maps Maintenance Document','E7722416-7058-EED2-3038-8C7FE8D86705',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Special Review Approval business objects.',3143,'ValidSpecialReviewApprovalMaintenanceDocument',0,'default.htm?turl=Documents/validspecialreviewapproval.htm','Valid Special Review Approval Maintenance Document','480834BB-7C1D-AB53-3C2C-44C6C4D075D1',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New IP Review Requirement Type',3144,'IntellectualPropertyReviewRequirementTypeMaintenanceDocument',0,'IP Review Requirement Type','0B3228A3-0264-7D4E-3F52-83A43FAE98BA',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Minute Entry Type',3145,'MinuteEntryTypeMaintenanceDocument',0,'Minute Entry Type','88D46F60-D1F2-0DB8-1D9A-26C318D54E39',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New ProtocolOnlineReview Status',3146,'ProtocolOnlineReviewStatusMaintenanceDocument',0,'Protocol Review Status Code','E49D0E32-2708-D04F-B4BB-DC248FBBF518',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Type',3147,'SponsorTypeMaintenanceDocument',0,'Sponsor Type','D1CF47D5-AD74-D01B-ABDD-E6D259CF41F3',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Proposal Log Status business objects.',3148,'Proposal Log Status Maintenance Document',0,'Proposal Log Status Maintenance Document','1CB7B3D2-C11F-B02D-3784-314F2DFB9F31',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'YnqMaintenanceDocument',3149,'YnqMaintenanceDocument',0,'default.htm?turl=Documents/yesnoquestions.htm','YnqMaintenanceDocument','3E12FE93-73B3-2F7A-3F3A-EAAB74DBAA36',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Special Review Approval Type Code',3150,'SpecialReviewApprovalTypeMaintenanceDocument',0,'default.htm?turl=Documents/specialreviewapprovaltype.htm','Special Review Approval Type','CF21F6C5-5FDC-7C15-68CE-D267B8A775F6',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Person Role',3151,'ProtocolPersonRoleMaintenanceDocument',0,'Protocol Person Role','6B4EFD10-AA59-9CA3-672C-B7726E2CAD61',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Membership Role',3152,'MembershipRoleMaintenanceDocument',0,'Membership Role','19250AF6-BE6E-1A85-3A2A-E407F3E8109E',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Mail Type',3153,'MailTypeMaintenanceDocument',0,'Mail Type','A8080DEC-19FC-F4BE-7C03-AC81B0710CAA',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Activity Type',3154,'ActivityTypeMaintenanceDocument',0,'default.htm?turl=Documents/activitytype.htm','Activity Type Maintenance Document','ECD4135F-8762-8255-6659-2FD240ACB7E2',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Frequency Base business objects.',3155,'Valid Frequency Base Maintenance Document',0,'Valid Frequency Base Maintenance Document','243C90D2-2B37-001B-4026-5148562AFF0A',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Basis Method Payment business objects.',3156,'ValidBasisMethodPaymentMaintenanceDocument',0,'Valid Basis Method Payment Maintenance Document','D974A27A-28C4-9276-5D2A-F816E7494440',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Question Category',3157,'QuestionCategoryMaintenanceDocument',0,'Question Category','1F3E1636-22C6-6144-172A-043AF351E447',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Committee Membership Type',3158,'CommitteeMembershipTypeMaintenanceDocument',0,'Committee Membership Type','27D61E2E-E563-B392-AC00-53E6A324EF5D',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Unit Administrator',3159,'UnitAdministratorMaintenanceDocument',0,'Unit Administrator','64429841-0751-44B7-8F6C-360F560EC4D5',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Committee Decision Motion Type',3160,'CommitteeDecisionMotionTypeMaintenanceDocument',0,'Committee Decision Motion Type','E6C51A9B-9882-863F-6265-F1B0BD9097CA',3033,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Custom Attribute',3161,'CustomAttributeMaintenanceDocument',0,'default.htm?turl=Documents/customattributedocument.htm','CustomAttribute Maintenance Document','53609287-D351-D13A-3B32-2D6D7C1211F0',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Correspondence Type',3162,'ProtocolCorrespondenceTypeMaintenanceDocument',0,'Protocol Correspondence Type','FAF10266-2E87-0DBC-7785-EDD1DEDADE5D',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Method Of Payment',3163,'AwardMethodOfPaymentMaintenanceDocument',0,'Award Basis Of Payment','AE3E755A-95DE-11BF-182D-51A1DDCDEBCE',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Rate Class',3164,'RateClassMaintenanceDocument',0,'default.htm?turl=Documents/rateclass.htm','Rate Class Maintenance Document','C6DEE385-43A1-A93C-D478-3970FC016052',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Report Type',3165,'ReportMaintenanceDocument',0,'Report Type','2DDE0BA7-0307-17DA-B344-9D0F66159084',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Narrative Status',3166,'NarrativeStatusMaintenanceDocument',0,'default.htm?turl=Documents/narrativestatus.htm','Narrative Status','2D162AC4-6507-03D6-6613-618686E012C8',3023,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Type',3167,'AwardTypeMaintenanceDocument',0,'Award Type','C8F27E62-F097-7BFA-90F1-3B32CE5E5EC5',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New School Code',3168,'SchoolCodeMaintenanceDocument',0,'School Code','E31DBC8C-EC38-3A78-9FDC-8A27F47D3C4E',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Correspondent Type',3169,'CorrespondentTypeMaintenanceDocument',0,'Correspondent Type','C1D9ACBB-54B9-651F-EC25-9087E0C99FB6',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Rolodex entry',3170,'RolodexMaintenanceDocument',0,'Rolodex','39BF6A9F-A884-D7F4-9CCC-23746CDC2A8A',3035,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Kuali Coeus Module',3171,'CoeusModuleMaintenanceDocument',0,'Kuali Coeus Module','8F8EB70A-88DA-D078-53F5-16C4A4EA7C45',3038,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Report Terms',3172,'AwardTemplateReportTermMaintenanceDocument',0,'Edit Sponsor Template Reports','704F6DB9-016A-8675-BBE5-FAB0D74FFFFB',3026,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Cost Share Type',3173,'CostShareTypeMaintenanceDocument',0,'Cost Share Type','8057ACE3-6D1E-FC68-A7C9-D6F611DC98B3',3035,'2',1)
/
