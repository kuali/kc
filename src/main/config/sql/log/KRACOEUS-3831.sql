insert into KRNS_PARM_T 
  (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) 
values 
  ('KUALI', 'KC-PD', 'D', 'DEFAULT_BIOGRAPHY_DOCUMENT_TYPE_CODE', '81237ef9-9d29-4a07-9fb8-ebd8aaddc834', 1, 'CONFG', '1', 'Value of the default biography document type code. This is the document type code that will be used when adding new users to a Proposal Development Document and they have an attached Biosketch file.', 'A');
commit;