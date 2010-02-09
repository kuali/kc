delete from KRNS_PARM_T where PARM_NM = 'scope.sync.PAYMENTS_AND_INVOICES_TAB.AwardReportTerm.reportClassCode';
delete from KRNS_PARM_T where PARM_NM = 'scope.sync.REPORTS_TAB.AwardReportTerm.reportClassCode';
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-AWARD','D','scope.sync.PAYMENTS_AND_INVOICES_TAB.AwardReportTerm.reportClassCode','CONFG','6','Comma delimited list of reportClassCodes for reports to sync on the Reports tab.','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) values ('KC-AWARD','D','scope.sync.REPORTS_TAB.AwardReportTerm.reportClassCode','CONFG','1,2,3,4,5,7','Comma delimited list of reportClassCodes for reports to sync on the Reports Tab.','A');

commit;