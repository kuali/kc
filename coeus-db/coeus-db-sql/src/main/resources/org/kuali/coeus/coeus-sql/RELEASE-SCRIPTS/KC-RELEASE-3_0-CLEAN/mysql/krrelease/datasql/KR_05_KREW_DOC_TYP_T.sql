drop procedure if exists p;

delimiter //
create procedure p ()
begin
    declare doc_typ_id_0 INT;
    declare doc_typ_id_1 INT;
    declare doc_typ_id_2 INT;
    declare doc_typ_id_3 INT;
    declare doc_typ_id_4 INT;
    declare doc_typ_id_5 INT;

    SELECT DOC_TYP_ID INTO doc_typ_id_0 FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KualiDocument' AND DOC_TYP_VER_NBR = 0;
    INSERT INTO KREW_DOC_HDR_S VALUES (null);
    SELECT MAX(ID) INTO doc_typ_id_1 FROM KREW_DOC_HDR_S;
    set @insert_seq := concat('INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (',doc_typ_id_1,',"KualiDocument","KualiDocument","KualiDocument",1,null,',doc_typ_id_0,',"${application.url}/kr/maintenance.do?methodToCall=docHandler",null,"org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor","2",null,null,null,1,1,UUID(),3)');
    prepare insert_seq_stmt from @insert_seq;
    execute insert_seq_stmt; 
    deallocate prepare insert_seq_stmt;
    
    INSERT INTO KREW_DOC_HDR_S VALUES (null);
    SELECT MAX(ID) INTO doc_typ_id_2 FROM KREW_DOC_HDR_S;
    set @insert_seq := concat('INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (',doc_typ_id_2,',"KC",null,"Undefined",0,',doc_typ_id_1,',null,null,null,"org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor","2","KC",null,null,1,1,UUID(),11)');
    prepare insert_seq_stmt from @insert_seq;
    execute insert_seq_stmt; 
    deallocate prepare insert_seq_stmt;
    
    INSERT INTO KREW_DOC_HDR_S VALUES (null);
    SELECT MAX(ID) INTO doc_typ_id_3 FROM KREW_DOC_HDR_S;
    set @insert_seq := concat('INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (',doc_typ_id_3,',"KcMaintenanceDocument","Parent of maintenance documents in KC","KcMaintenanceDocument",0,',doc_typ_id_2,',null,"${kuali.docHandler.url.prefix}/kr/maintenance.do?methodToCall=docHandler",null,"org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor","2",null,(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = "KC-WKFLW" AND GRP_NM = "KcAdmin"),(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = "KC-WKFLW" AND GRP_NM = "KcAdmin"),1,1,UUID(),24)');
    prepare insert_seq_stmt from @insert_seq;
    execute insert_seq_stmt; 
    deallocate prepare insert_seq_stmt;
    
    INSERT INTO KREW_DOC_HDR_S VALUES (null);
    SELECT MAX(ID) INTO doc_typ_id_4 FROM KREW_DOC_HDR_S;
    set @insert_seq := concat('INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (',doc_typ_id_4,',"KcSharedMaintenanceDocument","Parent of shared maintenance documents in KC","KcSharedMaintenanceDocument",0,',doc_typ_id_3,',null,null,null,null,"2",null,null,null,1,1,UUID(),61)');
    prepare insert_seq_stmt from @insert_seq;
    execute insert_seq_stmt; 
    deallocate prepare insert_seq_stmt;
    
    INSERT INTO KREW_DOC_HDR_S VALUES (null);
    SELECT MAX(ID) INTO doc_typ_id_5 FROM KREW_DOC_HDR_S;
    set @insert_seq := concat('INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,SVC_NMSPC,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (',doc_typ_id_5,',"QuestionnaireMaintenanceDocument","Create/Edit a Questionnaire","Questionnaire",0,',doc_typ_id_4,',null,"${kuali.docHandler.url.prefix}/maintenanceQn.do?methodToCall=docHandler","default.htm?turl=Documents/questionnaire1.htm",null,"2",null,null,null,1,1,UUID(),1)');
    prepare insert_seq_stmt from @insert_seq;
    execute insert_seq_stmt; 
    deallocate prepare insert_seq_stmt;
end //
delimiter ;

call p ();

drop procedure if exists p;