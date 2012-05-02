insert into KC_KRMS_TERM_FUNCTION values (SEQ_KC_KRMS_TERM_FUNCTION_ID.nextval,'KC1009','IS_FEDERAL_SPONSOR','java.lang.String',null,sysdate,'admin',1,sys_guid())
/
insert into KC_KRMS_TERM_FUN_PARAM_SPEC values (SEQ_KC_KRMS_TERM_FUN_PARM_ID.nextval,SEQ_KC_KRMS_TERM_FUNCTION_ID.currval,'sponsorCode','java.lang.Object',1,sysdate,'admin',1,sys_guid())
/
