--this scripts corrects some problems related to rice principal column length

ALTER TABLE KRNS_LOOKUP_RSLT_T MODIFY (PRNCPL_ID varchar(40));
ALTER TABLE KRNS_LOOKUP_SEL_T MODIFY (PRNCPL_ID varchar(40));