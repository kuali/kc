update narrative_type set description='PHS_Fellow_IntroductionToApplication' where narrative_type_code = 97;

Insert into NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,SYSTEM_GENERATED,ALLOW_MULTIPLE,UPDATE_TIMESTAMP,UPDATE_USER,NARRATIVE_TYPE_GROUP,OBJ_ID) values (134,'PHS_Fellow_Sponsor_CoSponsor_Info','N','N',now(),'admin','P',uuid())
;

COMMIT;