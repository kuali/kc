INSERT into KRNS_PARM_DTL_TYP_T  ( nmspc_cd, parm_dtl_typ_cd, VER_NBR, NM, ACTV_IND,OBJ_ID) Values ('KC-GEN','DocumentType',1,'Cusatom Attribute Document Type','Y','fcd15789-4a4a-4ac0-86dc-1761363b1f55');


INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD,OBJ_ID) VALUES('KC-GEN', 'DocumentType', 'customAttributeDocumentType', 'CONFG', 'AWRD=Award;INPR=Institutional Proposal;PRDV=Proposal Development;PROT=Protocol', 'List of Custom Attribute Document type name.', 'A','640a716a-bc46-4c88-842c-c66749b96cfa') ;

commit;

