-- Staging data
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-AWARD','D','AWARD_ACTIVE_STATUS_CODES',1,'CONFG','1','Comma delimited list of award status codes considered active.','A','6e3547e6-7fae-4fa6-bcad-33737e87931f');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-AWARD','D','AWARD_COST_SHARING',1,'CONFG','009906','Numeric code from the Sponsor table that defines an award as being for Cost Sharing for sync descendants.','A','3e580bcc-1ab4-4c1f-a53a-822a5cb7ca4f');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-AWARD','D','AWARD_FABRICATED_EQUIPMENT',1,'CONFG','2','Numeric code from Account Type table that defines an award as being for Fabricated Equipment for sync descendants.','A','4796932c-7fb7-4ea6-aa2a-a64b8627c8c8');

commit;