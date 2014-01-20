Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
Values
('KC-PROTOCOL','D','irb.protocol.award.linking.enabled', sys_guid(), 1,'CONFG','Y','Linking from Award to Protocol Funding source is configurable at impl time','A','WorkflowAdmin','Y');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
Values
('KC-PROTOCOL','D','irb.protocol.development.proposal.linking.enabled', sys_guid(), 1,'CONFG','Y','Linking from Award to Protocol Funding source is configurable at impl time','A','WorkflowAdmin','Y');

Insert into KRNS_PARM_T 
( NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND)
Values
('KC-PROTOCOL','D','irb.protocol.institute.proposal.linking.enabled', sys_guid(), 1,'CONFG','N','Linking from Award to Protocol Funding source is configurable at impl time','A','WorkflowAdmin','Y');

commit;
