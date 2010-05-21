delimiter /
TRUNCATE TABLE KREW_DOC_TYP_T
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Workflow Maintenance Document Type Document',2011,'DocumentTypeDocument',0,'1','default.htm?turl=WordDocuments%2Fdocumenttype.htm','Workflow Maintenance Document Type Document','6166CBA1BA5D644DE0404F8189D86C09',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Rule Maintenance Document Type Document',2012,'RoutingRuleDocument',0,'1','Rule Maintenance Document Type Document','6166CBA1BA5E644DE0404F8189D86C09',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${ken.url}/DetailView.form','This is the re-usable notification document type that will be used for delivering all notifications with KEW.',2023,'KualiNotification',0,'2000','Notification','6166CBA1BA69644DE0404F8189D86C09','org.kuali.rice.ken.postprocessor.kew.NotificationPostProcessor','1',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${ken.url}/AdministerNotificationRequest.form','Create a New Notification Request',2024,'SendNotificationRequest',0,'1','Send Notification Request','6166CBA1BA6A644DE0404F8189D86C09','org.kuali.rice.ken.postprocessor.kew.NotificationSenderFormPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create a New Travel Account Maintenance Document',2029,'TravelAccountMaintenanceDocument',0,'1','Travel Account Maintenance Document','6166CBA1BA6F644DE0404F8189D86C09','org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create a New Travel Fiscal Officer',2030,'FiscalOfficerMaintenanceDocument',0,'1','Travel Fiscal Officer','6166CBA1BA70644DE0404F8189D86C09','org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',0,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit parameter namespaces',2031,'NamespaceMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fnamespace.htm','Namespace','6166CBA1BA71644DE0404F8189D86C09',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',0,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter type',2032,'ParameterTypeMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparametertype.htm','Parameter Type Maintenance Document','6166CBA1BA72644DE0404F8189D86C09',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',0,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter detail type',2033,'ParameterDetailTypeMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparametercomponent.htm','Parameter Detail Type Maintenance Document','6166CBA1BA73644DE0404F8189D86C09',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',0,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter',2034,'ParameterMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparameter.htm','Parameter Maintenance Document','6166CBA1BA74644DE0404F8189D86C09',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_PLCY,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'NONE',1,'${workflow.url}/EDocLite','eDoc.Example1 Parent Doctype',2217,'eDoc.Example1.ParentDoctype',0,'2200','eDoc.Example1 Parent Document','6166CBA1BA7B644DE0404F8189D86C09','org.kuali.rice.kew.edl.EDocLitePostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'none','SampleThinClientDocument',2282,'SampleThinClientDocument',0,'1','SampleThinClientDocument','6166CBA1BA7D644DE0404F8189D86C09','org.kuali.rice.kew.postprocessor.DefaultPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_PLCY,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'NONE',1,'${workflow.url}/EDocLite','eDoc.Example1 Request DocumentType',2440,'eDoc.Example1Doctype',0,'2200','eDoc.Example1 Request DocumentType','6166CBA1BA80644DE0404F8189D86C09',2217,'org.kuali.rice.kew.edl.EDocLitePostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${application.url}/travelDocument2.do?methodToCall=docHandler','Create a New Travel Request',2683,'TravelRequest',0,'1','Travel Request','6166CBA1BA84644DE0404F8189D86C09',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','Routing Rule Delegation',2699,'RoutingRuleDelegationMaintenanceDocument',0,'Routing Rule Delegation','A6DC8753-AF90-7A01-0EF7-E6D446529668',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr/maintenance.do?methodToCall=docHandler','Parent Document for Recipe Maintenance Documents',2704,'RecipeParentMaintenanceDocument',0,'1','Recipe Maintenance Document Parent','327B8EEB-BC71-4701-A9E8-B4FC878FFCA6',2681,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2','RECIPE',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr/maintenance.do?methodToCall=docHandler','Create or Update a Recipe Category',2705,'RecipeCategoryMaintenanceDocument',0,'1','Recipe Category Maintenance','E9CB1AAD-0015-16D5-9149-EC2A4AEDE932',2704,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2','RECIPE',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr/maintenance.do?methodToCall=docHandler','Create or Update a Recipe Ingredient',2706,'RecipeIngredientMaintenanceDocument',0,'1','Recipe Ingredient Maintenance','41800805-9154-D43E-785F-3E76255F7F97',2704,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2','RECIPE',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr/maintenance.do?methodToCall=docHandler','Create or Update a Recipe',2707,'RecipeMaintenanceDocument',0,'1','Recipe Maintenance','C1CED233-6389-D07B-8ADD-B8043E50B599',2704,'edu.sampleu.recipe.kew.RecipesPostProcessor','2','RECIPE',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kr.url}/maintenance.do?methodToCall=docHandler',2708,'CampusMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcampus.htm','CampusMaintenanceDocument','616D94CA-D08D-D036-E77D-4B53DB34CD95',2681,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kr.url}/maintenance.do?methodToCall=docHandler',2709,'CampusTypeMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcampustype.htm','CampusTypeMaintenanceDocument','DE0B8588-E459-C07A-87B8-6ACD693AE70C',2681,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kr.url}/maintenance.do?methodToCall=docHandler',2710,'CountryMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcountry.htm','CountryMaintenanceDocument','82EDB593-97BA-428E-C6E7-A7F3031CFAEB',2681,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kr.url}/maintenance.do?methodToCall=docHandler',2711,'CountyMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcounty.htm','CountyMaintenanceDocument','C972E260-5552-BB63-72E6-A514301B0326',2681,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kr.url}/maintenance.do?methodToCall=docHandler',2712,'PostalCodeMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fpostalcode.htm','PostalCodeMaintenanceDocument','B79D1104-BC48-1597-AFBE-773EED31A110',2681,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kr.url}/maintenance.do?methodToCall=docHandler',2713,'StateMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fstate.htm','StateMaintenanceDocument','EF2378F6-E770-D7BF-B7F1-C18881E3AFF0',2681,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,2994,'IdentityManagementDocument',0,'Undefined','917DA614-1BED-D8B4-F3BA-760EB30DF553',2681,'2',7)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kim.url}/identityManagementRoleDocument.do?methodToCall=docHandler',2995,'IdentityManagementRoleDocument',1,'default.htm?turl=WordDocuments%2Frole.htm','Role','6A96BB4F-13F2-72D0-5D12-723E09416097',4043,2703,'2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kim.url}/identityManagementGroupDocument.do?methodToCall=docHandler',2996,'IdentityManagementGroupDocument',1,'default.htm?turl=WordDocuments%2Fgroup.htm','Group','8A4693A2-B198-1DA8-5258-E22D29F9EEC4',4043,2702,'2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kim.url}/identityManagementPersonDocument.do?methodToCall=docHandler',2997,'IdentityManagementPersonDocument',1,'default.htm?turl=WordDocuments%2Fperson.htm','Person','A9B573D5-AFF9-95F4-AF12-1055A26CCB9A',4043,2697,'2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${kr.url}/maintenance.do?methodToCall=docHandler',2998,'IdentityManagementReviewResponsibilityMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fresponsibility.htm','Review Responsibility','05C15C71-A466-36FF-2339-A6D303C268F7',4043,'2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${application.url}/kr/maintenance.do?methodToCall=docHandler',3783,'KualiDocument',0,'Undefined','4B3E237D-05D9-9CB3-D437-AD89BCCD5463','org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,VER_NBR)
  VALUES (1,1,3784,'KC',0,'Undefined','C3BFAD64-896C-E548-C78A-11BB9073CAD6',3783,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2','KC',13)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,3785,'RiceDocument',0,'Undefined','EE5E5CB0-1285-1350-9C2F-6DA0D9ED2C8D',3783,'2',16)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',1,'${kuali.docHandler.url.prefix}/awardBudgetVersions.do?methodToCall=docHandler','Create an Award Budget',3786,'AwardBudgetDocument',0,'1000001','Award Budget Document','56807DC9-52B3-59EB-06DF-6A9D611CF6A8',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',0,'${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','Manage Award',3787,'AwardDocument',0,'1000001','KC Award','9C7B6583-B009-22DC-917F-024887918BD2',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',2)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',0,'${kuali.docHandler.url.prefix}/budgetVersions.do?methodToCall=docHandler','Create a Budget',3788,'BudgetDocument',0,'1000001','Budget Document','D51D3E52-C113-0EDB-6D70-57855E4A3DE5',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',2)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kuali.docHandler.url.prefix}/committeeCommittee.do?methodToCall=docHandler','Create a Committee',3789,'CommitteeDocument',0,'Committee Document','CE23C832-FBB2-853D-46D9-512738635CCC',3784,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',0,'${kuali.docHandler.url.prefix}/institutionalProposalHome.do?methodToCall=docHandler','Manage Institutional Proposal',3793,'InstitutionalProposalDocument',0,'1000001','KC Institutional Proposal','3381D66B-69EE-106D-536F-B6846F98A749',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',2)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',1,'${kuali.docHandler.url.prefix}/kr/maintenance.do?methodToCall=docHandler','Parent of maintenance documents in KC',3794,'KcMaintenanceDocument',0,'1000001','KcMaintenanceDocument','02972160-43E1-C6E0-EAB2-418BF6E8A2D3',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',24)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kuali.docHandler.url.prefix}/proposalDevelopmentProposal.do?methodToCall=docHandler','Create an Awesome Development Proposal',3795,'ProposalDevelopmentDocument',0,'1000002','default.htm?turl=Documents/proposaldevelopmentdocument.htm','Proposal Development Document','92B472E5-CC60-C9C5-B052-5C55292D9369',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000004',1,'${kuali.docHandler.url.prefix}/protocolProtocol.do?methodToCall=docHandler','Create a KC Protocol Document',3796,'ProtocolDocument',0,'1000004','KC Protocol','D579D5DA-6E55-38BD-8CE2-69534B6AD5C3',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',1,'${kuali.docHandler.url.prefix}/timeAndMoney.do?methodToCall=docHandler','Manage TimeAndMoney',3797,'TimeAndMoneyDocument',0,'1000001','KC TimeAndMoney','CC5F824A-E045-4065-066C-ED3A9CC7AF00',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Transaction Type',3798,'Award Transaction Type Maintenance Document',0,'Award Transaction Type','F8FAF8EB-A421-8EEA-E1E3-162E05E0723B',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Carrier Type',3799,'CarrierTypeMaintenanceDocument',0,'Carrier Type','699C443B-90E2-4462-DE25-9A7682C0415D',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Exemption Type Code',3800,'ExemptionTypeMaintenanceDocument',0,'Exemption Type','41C59772-B2AA-C4C3-16BF-C73040174034',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of Awards maintenance documents in KC',3801,'KcAwardsMaintenanceDocument',0,'KcAwardsMaintenanceDocument','BEF7408D-FFC4-81C1-A4A4-5670CAF0399D',3794,'2',24)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of Compliance maintenance documents in KC',3802,'KcComplianceMaintenanceDocument',0,'KcComplianceMaintenanceDocument','6DF53E81-E727-22B8-8E27-844732FEB887',3794,'2',22)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of Miscellaneous maintenance documents in KC',3803,'KcMiscellaneousMaintenanceDocument',0,'KcMiscellaneousMaintenanceDocument','2C981FC4-CF6F-D730-56A3-ADB9049524C1',3794,'2',16)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a KC Person Extended Attributes entry',3804,'KcPersonExtendedAttributesMaintenanceDocument',0,'Person','ED1ADF31-E0D5-1186-0E58-659A2EF603DF',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of Miscellaneous maintenance documents in KC',3805,'KcProposalsMaintenanceDocument',0,'KcProposalsMaintenanceDocument','A112CB6A-06A6-4FB2-FF11-D4054076646A',3794,'2',13)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent of shared maintenance documents in KC',3806,'KcSharedMaintenanceDocument',0,'KcSharedMaintenanceDocument','B00D0434-7309-ABD4-0A20-11ED9F359878',3794,'2',55)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Person Appointment entry',3807,'PersonAppointmentMaintenanceDocument',0,'Person Appointment','2EE72B94-562A-E13B-4161-38FBF101E965',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Person Degree entry',3808,'PersonDegreeMaintenanceDocument',0,'Person Degree','F1779035-DB9A-F107-BB3A-1E8F043AD210',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Person Editable Field',3809,'PersonEditableFieldMaintenanceDocument',0,'Person Editable Field','B1794C42-F81B-D2DB-ACF7-D6F477D4E19B',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Prop Location',3810,'PropLocationMaintenanceDocument',0,'Prop Location','05CC12BB-B276-2FF3-B2A2-BF58E8B26EC8',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Proposal Column To Alter',3811,'ProposalColumnsToAlterMaintenanceDocument',0,'Proposal Column To Alter','102187D4-F0A8-F560-ED87-567A431A2485',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a new Proposal Log',3812,'ProposalLogMaintenanceDocument',0,'Proposal Log','12EC85D6-D1F8-7858-E0E5-388E02B2DF06',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Proposal Response',3813,'ProposalResponseMaintenanceDocument',0,'Proposal Response','53E7AF85-51EC-F915-5ED8-1C9EB38EB383',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Proposal Role Template entry',3814,'ProposalRoleTemplateMaintenanceDocument',0,'Proposal Role Template','47404CAC-6D5B-F3C4-A675-CD69BBD5BC6B',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Proposal Person Document Type',3815,'PropPerDocTypeMaintenanceDocument',0,'Proposal Person Document Type','1CF6DA7B-BBCE-879C-46D3-E8E2535B20F4',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Risk Level',3816,'RiskLevelMaintenanceDocument',0,'Risk Level Maintenance Document','48CE349C-F0D6-B7B9-8376-95046289DA8A',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Sponsor Term',3817,'SponsorTermMaintenanceDocument',0,'Award Sponsor Term','6FDAA6A6-29EA-A95B-CF22-9E1915932E1F',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Term Type',3818,'SponsorTermTypeMaintenanceDocument',0,'Sponsor Term Type','67B371A3-5865-B53E-897F-31C25613E7DD',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New TBN Person',3819,'TbnPersonMaintenanceDocument',0,'To Be Named Person','DED0AFB8-E1F4-1EA3-3531-14788D8E8524',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Unit',3820,'UnitMaintenanceDocument',0,'Unit','AF0542C0-FFA2-024C-DA95-878926C503A9',3794,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Abstract Type',3821,'AbstractTypeMaintenanceDocument',0,'Abstract Type','D8CD46B1-0A53-59E4-ADE1-041800E5D3CD',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Account Type',3822,'AccountTypeMaintenanceDocument',0,'Account Type','6C87D928-AD51-448F-3F36-9326A791E4A9',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Activity Type',3823,'ActivityTypeMaintenanceDocument',0,'Activity Type Maintenance Document','08DF8B36-5A95-716F-A7C5-0607E9E6F5E3',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create an Affiliation Type',3824,'KcAffiliationTypeMaintenanceDocument',0,'Affiliation Type Maintenance Document','81EAFC83-D4C9-2F5B-B273-5E1DC02FDA32',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Argument Value Lookup',3825,'ArgValueLookupMaintenanceDocument',0,'Argument Value','8650D98E-88F9-7564-6D87-A43F40144295',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Award Attachment Type',3826,'AwardAttachmentTypeMaintenanceDocument',0,'Award Attachment Type','B4DDC295-E4EB-10A2-8CE0-B4E4E6890104',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Basis Of Payment',3827,'AwardBasisOfPaymentMaintenanceDocument',0,'Award Basis Of Payment','3BC68EDB-BE06-0CB0-C123-03EAC7DE2998',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Method Of Payment',3828,'AwardMethodOfPaymentMaintenanceDocument',0,'Award Basis Of Payment','339941BE-CA24-BA87-B680-A1A02559AADB',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Status',3829,'AwardStatusMaintenanceDocument',0,'Award Status Type','C6E1FAE7-39E2-323F-372E-EBDC96C568CB',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Comment',3830,'AwardTemplateCommentMaintenanceDocument',0,'Sponsor Template Comment','F80D1FE2-0B55-C161-7F1C-54F07C72ABC9',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Contact',3831,'AwardTemplateContactMaintenanceDocument',0,'Sponsor Template Contact','F6E09D83-7747-A683-C969-AECFE81EBC3B',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template',3832,'AwardTemplateMaintenanceDocument',0,'Sponsor Template','179FE7AF-B949-A6F2-70E5-67E81A5FB00E',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Report Terms',3833,'AwardTemplateReportTermMaintenanceDocument',0,'Edit Sponsor Template Reports','B0610378-DD0D-74CC-D122-706D455C37F0',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Report Term Recipient',3834,'AwardTemplateReportTermRecipientMaintenanceDocument',0,'Sponsor Template Report Term Recipient','123FCC49-FB41-BA59-81A2-E0C89CD3112D',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Template Terms',3835,'AwardTemplateTermMaintenanceDocument',0,'Sponsor Template Report Terms','214D2F71-7D82-4298-477C-3BEDFF19C6B2',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Award Type',3836,'AwardTypeMaintenanceDocument',0,'Award Type','B8169DDE-C097-9FEF-3E8E-4C5EC2B1085B',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit Batch Correspondence Details',3837,'BatchCorrespondenceDetailMaintenanceDocument',0,'Batch Correspondence Detail','5DAD893F-3DDF-6084-571C-07EBC1F2853E',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Batch Correspondence',3838,'BatchCorrespondenceMaintenanceDocument',0,'Batch Correspondence','0B1AE94B-0215-4C12-B88B-947B195B4B66',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Category',3839,'BudgetCategoryMaintenanceDocument',0,'Budget Category Maintenance Document','00A56DBB-6067-1A69-1E2E-9371C31D7065',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Category Mapping',3840,'BudgetCategoryMappingMaintenanceDocument',0,'Budget Category Mapping Maintenance Document','66E3C2C2-9DD5-70FA-BD55-4B705AE93992',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Category Maps',3841,'BudgetCategoryMapMaintenanceDocument',0,'Budget Category Maps Maintenance Document','6CC275CA-3467-FA92-3E0F-B2A5C0971086',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Category Type',3842,'BudgetCategoryTypeMaintenanceDocument',0,'Budget Category Type Maintenance Document','8C37D317-882D-6F59-7CF8-EE4885E9C68C',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Budget Status',3843,'BudgetStatusMaintenanceDocument',0,'Budget Status','6BC093B0-82A5-05DC-BA0C-D644C3477191',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Closeout Report Type',3844,'CloseoutReportTypeMaintenanceDocument',0,'Closeout Report Type','E9E5B84F-360C-B146-63EA-332C39BA143E',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Kuali Coeus Module',3845,'CoeusModuleMaintenanceDocument',0,'Kuali Coeus Module','DD09328C-CB35-78D5-E4E7-D997D84BBCFF',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Kuali Coeus Sub Module',3846,'Coeus Sub Module Maintenance Document',0,'Kuali Coeus Sub Module','3477E644-C0D0-9ADE-A6B3-4D1FC697A5D9',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Comment Type',3847,'CommentTypeMaintenanceDocument',0,'Comment Type','8B107346-D8D9-484C-F039-FE4ACB1361BB',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Committee Membership Type',3848,'CommitteeMembershipTypeMaintenanceDocument',0,'Committee Membership Type','27C52485-10E1-3E7A-ADE2-3754EAF3B951',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Committee Type',3849,'CommitteeTypeMaintenanceDocument',0,'Committee Type','BA26E9FC-9E70-A698-864A-DBFC8CAA2C5E',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Contact Type',3850,'ContactTypeMaintenanceDocument',0,'Contact Type','77D3BAA2-AAD5-75E0-5AED-8F9DEB250AAC',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Correspondent Type',3851,'CorrespondentTypeMaintenanceDocument',0,'Correspondent Type','91DD05C7-AA86-3591-E1D5-8942A0E2865C',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Cost Element',3852,'CostElementMaintenanceDocument',0,'Cost Element Maintenance Document','D5912DE1-A822-9ABB-072A-94616B3FCD58',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Cost Share Type',3853,'CostShareTypeMaintenanceDocument',0,'Cost Share Type','64C32217-BA02-06DD-9700-354DB036A72B',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Country Code',3854,'CountryCodeMaintenanceDocument',0,'Country Code','3C33EE17-757F-F88C-8030-A285CD6CA2D7',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Country',3855,'KcCountryMaintenanceDocument',0,'Country','CC2013BA-F1E5-0258-3EEB-039CBCF3BF1C',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Custom Attribute Document',3856,'CustomAttributeDocumentMaintenanceDocument',0,'CustomAttributeDocument Maintenance Document','0D3D0A4F-BF18-2D9A-6125-59A27E722F9D',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Custom Attribute',3857,'CustomAttributeMaintenanceDocument',0,'CustomAttribute Maintenance Document','9AEB7FAA-D187-F38F-032F-057E741EEB71',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Deadline Type',3858,'DeadlineTypeMaintenanceDocument',0,'Deadline Type','0D013053-DA6B-7A61-4B42-77AF4E7232C8',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Degree Type entry',3859,'DegreeTypeMaintenanceDocument',0,'Degree Type','D191B85F-1338-3C2A-D8F8-2E41442AF153',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Osp Distribution',3860,'DistributionMaintenanceDocument',0,'Osp Distribution','89A338E8-8214-E861-738F-E9BAB866F0BB',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Exempt Studies CheckList Item',3861,'ExemptStudiesCheckListMaintenanceDocument',0,'Exempt Studies CheckList Item','309A98B7-21F0-4AF8-B541-393F7536555A',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Expedited Review CheckList Item',3862,'ExpeditedReviewCheckListMaintenanceDocument',0,'Expedited Review CheckList Item','4552A11F-8E68-B67E-B167-10DDE47308A6',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a F & A Rate Type',3863,'FandaRateTypeMaintenanceDocument',0,'F & A Rate Type','4A9621B3-3AFC-CC36-6ECB-269B3C34447C',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Frequency Base',3864,'FrequencyBaseMaintenanceDocument',0,'Frequency Base','CBF1AC1F-030F-905F-A512-16C4F552CD42',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Frequency',3865,'FrequencyMaintenanceDocument',0,'Frequency','FE3CAA2C-AD2F-B31A-02A9-1777AA037BC9',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Funding Source Type',3866,'FundingSourceTypeMaintenanceDocument',0,'Funding Source Type','999D7864-DECE-AA99-005A-D2D5952C47FA',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Institute La Rate',3867,'InstituteLaRateMaintenanceDocument',0,'Institute La Rates Maintenance Document','47533ABF-BFF7-D64B-9A01-CBE7D1D27689',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Institute Rate',3868,'InstituteRateMaintenanceDocument',0,'Institute Rates Maintenance Document','BBEF8AF3-3DB0-35A2-955D-6937C73E7CAC',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New IP Review Activity Type',3869,'IntellectualPropertyReviewActivityTypeMaintenanceDocument',0,'IP Review Activity Type','6AA701C6-4A94-A883-B919-86EEFDE1E65B',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Maintain Intellectual Property Review',3870,'IntellectualPropertyReviewMaintenanceDocument',0,'Intellectual Property Review','F1E1FBF9-4347-7007-1540-0C5100D8C84E',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New IP Review Requirement Type',3871,'IntellectualPropertyReviewRequirementTypeMaintenanceDocument',0,'IP Review Requirement Type','8B8A58A3-9E24-8FAF-A917-5122F7CED2CD',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New IP Review Result Type',3872,'IntellectualPropertyReviewResultTypeMaintenanceDocument',0,'IP Review Result Type','F85C2524-8F5C-F95E-59BC-55F69C3BF6D6',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Investigator Credit Type',3873,'InvestigatorCreditTypeMaintenanceDocument',0,'Investigator Credit Type','5251086D-8F3A-CA41-E756-A66D545BAD83',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Mail By',3874,'MailByMaintenanceDocument',0,'Mail By','97601571-54B4-4B44-8692-2A4A19D63B25',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Mail Type',3875,'MailTypeMaintenanceDocument',0,'Mail Type','795B4E27-3E0D-985B-D567-1862040D0F2D',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Membership Role',3876,'MembershipRoleMaintenanceDocument',0,'Membership Role','42AFDAFC-5435-3EF0-DBB2-CC3A00B54E02',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update message of the day documents.',3877,'MessageOfTheDayMaintenanceDocument',0,'Message Of The Day Maintenance Document','E2726DF2-6D1E-1318-CCB4-059C5B2D72B3',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Minute Entry Type',3878,'MinuteEntryTypeMaintenanceDocument',0,'Minute Entry Type','7DAAFED6-5DEE-5DFD-6440-DA40C7F5A9F1',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Narrative Status',3879,'NarrativeStatusMaintenanceDocument',0,'Narrative Status','7DA3C696-319C-9711-805D-4FA15BBEFE3B',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Narrative Type',3880,'NarrativeTypeMaintenanceDocument',0,'Narrative Type','1E9F4487-9883-90D0-0F17-63080D15F83B',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Notice of Opportunity',3881,'NoticeOfOpportunityMaintenanceDocument',0,'Notice of Opportunity','85BF5611-137E-2513-12C5-C7A64CDE485F',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New NSF Code',3882,'NsfCodeMaintenanceDocument',0,'NSF Code','F9A6548F-16FE-31CB-E03E-9226057E4F3F',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Organization',3883,'OrganizationMaintenanceDocument',0,'Organization Maintenance Document','92BF232E-F647-CD53-3DAA-DF40DB663FA7',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Organization Type Code',3884,'OrganizationTypeListMaintenanceDocument',0,'Organization','6188881D-878B-C023-302F-2D630644D832',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit an Organization Type',3885,'OrganizationTypeMaintenanceDocument',0,'Organization','8930DD43-4638-36D1-4557-A4A58B6032D0',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Participant Type',3886,'ParticipantTypeMaintenanceDocument',0,'Participant Type','26598916-6117-4B67-AB9C-90B100E2DDC3',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Person entry',3887,'PersonMaintenanceDocument',0,'Person','5CDCB133-3EB7-686A-BBCC-143CBC6CF894',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Person Training',3888,'PersonTrainingMaintenanceDocument',0,'Person Training Maintenance Document','514043D2-65EB-F092-B64C-7999EB2F5E0E',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Post Submission Status Code',3889,'PostSubmissionStatusMaintenanceDocument',0,'Post Submission Status Code','8EAFC782-C63F-C45E-8F6D-A1003733608D',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Proposal Log Status business objects.',3890,'Proposal Log Status Maintenance Document',0,'Proposal Log Status Maintenance Document','ED00550A-9167-03F9-B6E9-BCE892717667',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Proposal Log Type business objects.',3891,'Proposal Log Type Maintenance Document',0,'Proposal Log Type Maintenance Document','ECBD74B2-06E7-8AED-890D-6EA2CB35470F',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Proposal Status business objects.',3892,'Proposal Status Maintenance Document',0,'Proposal Status Maintenance Document','281C492D-3D0C-4FCE-45BD-89F9F55FE545',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Proposal Type Code',3893,'ProposalTypeMaintenanceDocument',0,'Proposal Type','559A3CAE-3085-2AB9-3896-C67F08F8DA9D',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Action Type',3894,'ProtocolActionTypeMaintenanceDocument',0,'Protocol Action Type','7886D4A0-A168-8462-0DB4-D350CA036F50',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Attachment Group',3895,'ProtocolAttachmentGroupMaintenanceDocument',0,'Protocol Attachment Group Maintenance Document','ED7E5DDE-2016-697E-01C2-3BA78F833012',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Attachment Status',3896,'ProtocolAttachmentStatusMaintenanceDocument',0,'Protocol Attachment Status Maintenance Document','C78844AF-F1BA-0097-D18A-6414A4A3AC26',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Attachment Type Group',3897,'ProtocolAttachmentTypeGroupMaintenanceDocument',0,'Protocol Attachment Type Group Maintenance Document','48DCCF3C-A162-37DA-33DE-D1A70AC34C4F',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Attachment Type',3898,'ProtocolAttachmentTypeMaintenanceDocument',0,'Protocol Attachment Type Maintenance Document','CEEC75AB-4C8F-FA4A-7F93-AC6D1E36F7A9',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Protocol Contingency',3899,'ProtocolContingencyMaintenanceDocument',0,'Protocol Contingency','3BCDBF29-4AE8-B164-61AB-81C4834E29C0',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Correspondence Template',3900,'ProtocolCorrespondenceTemplateMaintenanceDocument',0,'Correspondence Template','941D3D71-ABB7-1D7F-DCE3-E271A2129F5B',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Correspondence Type',3901,'ProtocolCorrespondenceTypeMaintenanceDocument',0,'Protocol Correspondence Type','55590B37-A7DC-DABA-4044-EE7F9DBF0E1E',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Reference Type',3902,'ProtocolReferenceTypeMaintenanceDocument',0,'Protocol Reference Type','49D09DB6-F273-2C4F-CD18-613F60EEB81D',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Protocol Reviewer Type',3903,'ProtocolReviewerTypeMaintenanceDocument',0,'Protocol Reviewer Type','45C4F227-D05B-82E9-68C7-240029318D98',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Protocol Review Type',3904,'ProtocolReviewTypeMaintenanceDocument',0,'Protocol Review Type','F97CFA6D-3881-DCEA-5A64-943DBDB5A416',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Status',3905,'ProtocolStatusMaintenanceDocument',0,'Protocol Status Maintenance Document','E5F55E11-A070-063F-B6D8-2708DDE8DBAD',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Protocol Type',3906,'ProtocolTypeMaintenanceDocument',0,'Protocol Type Maintenance Document','12691148-C7D0-6538-1263-406672C41E18',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Question Category',3907,'QuestionCategoryMaintenanceDocument',0,'Question Category','729649BD-A0A0-899C-7F31-25D7EBCAF5D2',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kuali.docHandler.url.prefix}/maintenance.do?methodToCall=docHandler','Create/Edit a Question',3908,'QuestionMaintenanceDocument',0,'Question','BD527C3D-CAE9-A4C8-A581-A4E34C1F035B',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kuali.docHandler.url.prefix}/maintenanceQn.do?methodToCall=docHandler','Create/Edit a Questionnaire',3909,'QuestionnaireMaintenanceDocument',0,'Questionnaire','4DCC6F73-8FA7-2493-684B-7D99DAE69E98',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'Create a Questionnaire Questions',3910,'Questionnaire Questions Maintenance Document',0,'Questionnaire Questions','243E49E3-61E0-3B6F-B845-6987FB2C0EB7',3806,'2',2)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a Questionnaire Usage',3911,'Questionnaire Questions Maintenance Document',1,'Questionnaire Usage','D8966071-BAE9-1CC3-E724-199022EFEF66',3806,3910,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Question Type',3912,'QuestionTypeMaintenanceDocument',0,'Questionnaire Type','30858AD7-EF34-2356-0122-A97568131383',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Rate Class',3913,'RateClassMaintenanceDocument',0,'Rate Class Maintenance Document','1C59DC3C-3BAE-16F1-BB6E-01569E872E93',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Rate Class Type',3914,'RateClassTypeMaintenanceDocument',0,'Rate Class Type Maintenance Document','13731964-B695-2ED8-478B-1C51C2DD5A66',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Rate Type',3915,'RateTypeMaintenanceDocument',0,'Rate Type Maintenance Document','1B86487E-AC14-C89B-7A64-2CAF9B916AC7',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Report Class',3916,'ReportClassMaintenanceDocument',0,'Report Class','4B02D83F-486D-8391-9F2A-ECF8AB58230A',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Report Type',3917,'ReportMaintenanceDocument',0,'Report Type','8746BEA9-DE5D-84F2-D298-3FD745A47663',3801,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Research Area',3918,'ResearchAreasMaintenanceDocument',0,'Report Area','7FA68762-5636-B0FE-AB98-A2A33F340559',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Rolodex entry',3919,'RolodexMaintenanceDocument',0,'Rolodex','099A9EEB-B9ED-6DA3-4376-8C3525EBA7FC',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Revision Type Document',3920,'S2sRevisionTypeMaintenanceDocument',0,'Revision Type Document','DA4E0204-974D-64A3-0725-BA05AB46D654',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New SubmissionType Document Type',3921,'S2sSubmissionTypeMaintenanceDocument',0,'Submission Type Document Type','3ADBF6E6-80EA-3F78-757A-46F4B28BF60F',3805,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Schedule Act Item Type',3922,'ScheduleActItemTypeMaintenanceDocument',0,'Schedule Act Item Type','A125E3CC-CFCD-4519-5C51-7FD5AFE3DF45',3802,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New School Code',3923,'SchoolCodeMaintenanceDocument',0,'School Code','E33ABD6B-A85C-F485-A5E2-743B09188644',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Science Keyword',3924,'ScienceKeywordMaintenanceDocument',0,'Science Keyword','637D2508-1758-24C9-6426-C4B1D57C635E',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Special Review Approval Type Code',3925,'SpecialReviewApprovalTypeMaintenanceDocument',0,'default.htm?turl=Documents/specialreviewapprovaltype.htm','Special Review Approval Type','6C318FC4-C354-A3B8-3F3E-24578282D97C',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Special Review Code',3926,'SpecialReviewMaintenanceDocument',0,'default.htm?turl=Documents/specialreviewapprovaltype.htm','Special Review','4DBC6761-42FA-9B51-3DC4-1D421F61426C',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Form',3927,'SponsorFormsMaintenanceDocument',0,'Sponsor Form','1A9BAE21-8910-0878-1A9E-8FED68AE6BA1',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Form Template',3928,'SponsorFormTemplateMaintenanceDocument',0,'Sponsor Form Template','CAC20BFE-1CB3-A1FC-DCA5-EE06761AF7E3',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Hierarchy',3929,'SponsorHierarchyMaintenanceDocument',0,'Sponsor Hierarchy','3A21338E-C765-84D1-C9B6-E8682E8AD023',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Type',3930,'SponsorMaintenanceDocument',0,'Sponsor','E8B6B123-EAF8-A954-A8E5-E3F764DF170E',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Sponsor Type',3931,'SponsorTypeMaintenanceDocument',0,'Sponsor Type','5854EB57-1871-77C3-CD39-8E178E0D2871',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New State',3932,'KcStateMaintenanceDocument',0,'State','A1D135C9-1858-6C0F-1C4D-926F06C82B42',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Training',3933,'TrainingMaintenanceDocument',0,'Training','F8F7428E-8E1D-C5A5-FCE1-3B53902276AB',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Unit Administrator',3934,'UnitAdministratorMaintenanceDocument',0,'Unit Administrator','8E34663B-C695-5C64-7B1A-B0EBE84EB03A',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Award Basis Payment business objects.',3935,'ValidAwardBasisPaymentMaintenanceDocument',0,'Valid Award Basis Payment Maintenance Document','64219A37-107E-B942-A828-E82D4C909E18',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Basis Method Payment business objects.',3936,'ValidBasisMethodPaymentMaintenanceDocument',0,'Valid Basis Method Payment Maintenance Document','904E6A0D-D992-F580-7E22-A0018CC5E9E5',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Valid Calculation Type',3937,'ValidCalcTypeMaintenanceDocument',0,'Valid Calc Types Maintenance Document','FFBED06E-AB2A-4010-BBAE-3C7F70BA6203',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Valid Cost Element Job Code',3938,'ValidCeJobCodeMaintenanceDocument',0,'Valid Cost Element Job Code','9DEFEB62-88D3-DBBE-5CA1-F48994071155',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit a Valid Cost Element Rate Type',3939,'ValidCeRateTypeMaintenanceDocument',0,'Valid Cost Element Rate Types Maintenance Document','E394E502-C3CF-1D58-4377-BA3ABD1C7227',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Class Report Frequency business objects.',3940,'Valid Class Report Freq Maintenance Document',0,'Valid Class Report Frequency Maintenance Document','4B947E1C-0104-F638-9664-F2BA4663A484',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Frequency Base business objects.',3941,'Valid Frequency Base Maintenance Document',0,'Valid Frequency Base Maintenance Document','71E02B7C-C00B-DDA5-65C8-206D46E87536',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create/Edit Correspondence Generated for Protocol Action',3942,'ValidProtocolActionCorrespondenceMaintenanceDocument',0,'Correspondence Generated','E117C302-F049-7487-957B-3B6429A7B4D1',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create a New Valid Rate',3943,'ValidRatesMaintenanceDocument',0,'Valid Rates','74CBFBC8-5280-9F23-5028-3BC8277D58E9',3803,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Document used to create or update Valid Special Review Approval business objects.',3944,'ValidSpecialReviewApprovalMaintenanceDocument',0,'Valid Special Review Approval Maintenance Document','11055FAF-2016-67F2-CD70-D4D63E33BEB5',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'YnqMaintenanceDocument',3945,'YnqMaintenanceDocument',0,'YnqMaintenanceDocument','993CA25D-0D46-F8A2-C192-4419779EEBF5',3806,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,4043,'IdentityManagementDocument',1,'Undefined','D0C27BCA-7C79-D21F-F0D4-5A9A952BF474',3785,2994,'2',6)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kim.url}/identityManagementRoleDocument.do?methodToCall=docHandler',4044,'IdentityManagementRoleDocument',2,'default.htm?turl=WordDocuments%2Frole.htm','Role','9D9C1CBB-464D-346C-82D2-3C6A3C55CB50',4043,2995,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kim.url}/identityManagementGroupDocument.do?methodToCall=docHandler',4045,'IdentityManagementGroupDocument',2,'default.htm?turl=WordDocuments%2Fgroup.htm','Group','E3387518-4007-7484-B7EE-0AFEA53BAF57',4043,2996,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kim.url}/identityManagementPersonDocument.do?methodToCall=docHandler',4046,'IdentityManagementPersonDocument',2,'default.htm?turl=WordDocuments%2Fperson.htm','Person','1960800F-F174-7D8F-EFF3-8260BBE17786',4043,2997,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',4047,'IdentityManagementReviewResponsibilityMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fresponsibility.htm','Review Responsibility','AB34DE8F-F630-B81F-BCA4-C9337633D53B',4043,2998,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',4048,'IdentityManagementGenericPermissionMaintenanceDocument',0,'Permission','B67A8F6A-AF1A-95B5-7C6D-D1B01129ECED',4043,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',1,'${kuali.docHandler.url.prefix}/institutionalProposalHome.do?methodToCall=docHandler','Manage Institutional Proposal',4049,'InstitutionalProposalDocument',1,'1000001','default.htm?turl=Documents/institutionalproposaldocument.htm','KC Institutional Proposal','8EF73AF1-CE1D-0D89-2990-2E0AFF574D3A',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor',3793,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',1,'${kuali.docHandler.url.prefix}/budgetVersions.do?methodToCall=docHandler','Create a Budget',4050,'BudgetDocument',1,'1000001','default.htm?turl=Documents/budgetdocument.htm','Budget Document','BC4BD2E6-106B-962B-63B9-D54DA10EFEBC',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor',3788,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1000001',1,'${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','Manage Award',4051,'AwardDocument',1,'1000001','default.htm?turl=Documents/awarddocument.htm','KC Award','E863BD5A-1635-0BB3-4EBE-867CB820D6F7',3784,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor',3787,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',4063,'CountyMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fcounty.htm','CountyMaintenanceDocument','49C32140-BE8C-4B2F-5A9D-0EC6955C8FDE',3785,2711,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',4064,'PostalCodeMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fpostalcode.htm','PostalCodeMaintenanceDocument','FC97D152-8488-1019-55D2-0A1FA486EBD6',3785,2712,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',4065,'StateMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fstate.htm','StateMaintenanceDocument','9B5F7A40-B854-FAB2-16C2-5324C7092392',3785,2713,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',4066,'CampusMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fcampus.htm','CampusMaintenanceDocument','751B0BDF-F2BD-8341-C851-9A266C276EFA',3785,2708,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',4067,'CampusTypeMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fcampustype.htm','CampusTypeMaintenanceDocument','8884F652-5EC5-CE02-CDD4-3A257B96B5F5',3785,2709,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler',4068,'CountryMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fcountry.htm','CountryMaintenanceDocument','7E2C4DDB-0D71-B4A6-8D38-8F9615F6F9B0',3785,2710,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter',4069,'ParameterMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fparameter.htm','Parameter Maintenance Document','BF68A035-D596-3A35-D36C-B31AF5C74960',3785,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor',2034,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter type',4070,'ParameterTypeMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fparametertype.htm','Parameter Type Maintenance Document','46A1ED9D-C79E-5C9C-3684-EAE90F9C91B6',3785,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor',2032,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit parameter namespaces',4071,'NamespaceMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fnamespace.htm','Namespace','6BECEBDD-3B10-FA1C-B3C1-30A3427E7996',3785,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor',2031,'2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter detail type',4072,'ParameterDetailTypeMaintenanceDocument',1,'default.htm?turl=WordDocuments%2Fparametercomponent.htm','Parameter Detail Type Maintenance Document','16C60475-8184-70F1-22D8-AF30D46D384F',3785,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor',2033,'2',1)
/
delimiter ;
