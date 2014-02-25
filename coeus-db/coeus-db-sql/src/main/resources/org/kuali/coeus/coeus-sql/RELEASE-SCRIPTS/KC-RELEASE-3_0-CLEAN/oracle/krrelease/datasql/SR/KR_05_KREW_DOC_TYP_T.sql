DECLARE doc_typ_id_0 NUMBER;
        doc_typ_id_1 NUMBER;
        doc_typ_id_2 NUMBER;
        doc_typ_id_3 NUMBER;
        doc_typ_id_4 NUMBER;
        doc_typ_id_5 NUMBER;

BEGIN
--    SELECT DOC_TYP_ID INTO doc_typ_id_0 FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KualiDocument' AND DOC_TYP_VER_NBR = 0;
--    UPDATE KREW_DOC_TYP_T
--       SET CUR_IND='0'
--     WHERE DOC_TYP_NM = 'KualiDocument' AND DOC_TYP_VER_NBR = 0;
--    SELECT KREW_DOC_HDR_S.NEXTVAL INTO doc_typ_id_1 FROM DUAL;
--    INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) 
--        VALUES (doc_typ_id_1,'KualiDocument','KualiDocument','KualiDocument',1,null,doc_typ_id_0,'${application.url}/kr/maintenance.do?methodToCall=docHandler',null,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',null,null,null,1,1,SYS_GUID(),3);
        
    SELECT KREW_DOC_HDR_S.NEXTVAL INTO doc_typ_id_2 FROM DUAL;
    INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) 
        VALUES (doc_typ_id_2,'KC',null,'Undefined',0,doc_typ_id_1,null,null,null,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2','KC',null,null,1,1,SYS_GUID(),11);
    
    SELECT KREW_DOC_HDR_S.NEXTVAL INTO doc_typ_id_3 FROM DUAL;
    INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) 
        VALUES (doc_typ_id_3,'KcMaintenanceDocument','Parent of maintenance documents in KC','KcMaintenanceDocument',0,doc_typ_id_2,null,'${kuali.docHandler.url.prefix}/kr/maintenance.do?methodToCall=docHandler',null,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',null,(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'KcAdmin'),(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'KcAdmin'),1,1,SYS_GUID(),24);
    
    SELECT KREW_DOC_HDR_S.NEXTVAL INTO doc_typ_id_4 FROM DUAL;
    INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) 
        VALUES (doc_typ_id_4,'KcSharedMaintenanceDocument','Parent of shared maintenance documents in KC','KcSharedMaintenanceDocument',0,doc_typ_id_3,null,null,null,null,'2',null,null,null,1,1,SYS_GUID(),61);

    SELECT KREW_DOC_HDR_S.NEXTVAL INTO doc_typ_id_5 FROM DUAL;
    INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR)
        VALUES (doc_typ_id_5,'QuestionnaireMaintenanceDocument','Create/Edit a Questionnaire','Questionnaire',0,doc_typ_id_4,null,'${kuali.docHandler.url.prefix}/maintenanceQn.do?methodToCall=docHandler','default.htm?turl=Documents/questionnaire1.htm',null,'2',null,null,null,1,1,SYS_GUID(),1);
END;
/
