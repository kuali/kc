delete from en_rule_rsp_t t where t.rule_base_val_id in (select t1.rule_base_val_id from en_rule_base_val_t t1 where t1.doc_typ_nm = 'ProposalDevelopmentDocument');
delete from en_rule_base_val_t t where t.doc_typ_nm = 'ProposalDevelopmentDocument';
delete from en_rule_tmpl_t t where t.rule_tmpl_nm in ('FirstApprovalRouting', 'SecondApprovalRouting', 'FinalApprovalRouting');
delete from en_doc_typ_t t where t.doc_typ_nm = 'ProposalDevelopmentDocument';  