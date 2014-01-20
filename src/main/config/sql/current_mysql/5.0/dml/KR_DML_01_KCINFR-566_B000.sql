DELIMITER /
insert into KRMS_TYP_T values ('KC1001','Function Term Resolver Type Service','KC-PD','functionTermResolverTypeService','Y',1)
/
insert into KRMS_TERM_SPEC_T values('KC1009','IS_SPONSOR_FEDERAL','java.lang.String','Y',1,'Is Sponsor Federal','KC-PD')
/
insert into KRMS_TERM_T values ('KC1009','KC1009',1,'Is Sponsor Federal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T values ('KC1009','KC-PD-CONTEXT','KC1009','Y')
/
insert into KRMS_TERM_RSLVR_T values ('KC1000','KC-PD','storedFunctionResolver','KC1001','KC1009','Y',1)
/
DELIMITER ;
