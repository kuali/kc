update krim_perm_t set actv_ind = 'N' where nmspc_cd = 'KRA-PD' and nm = 'Blanket Approve ProposalDevelopmentDocument';
update krim_perm_t set actv_ind = 'N' where nmspc_cd = 'KC-PROTOCOL' and nm = 'Blanket Approve ProtocolDocument';
update krim_perm_t set actv_ind = 'N' where nmspc_cd = 'KC-PROTOCOL' and nm = 'Blanket Approve CommitteeDocument';
update krim_perm_t set actv_ind = 'N' where nmspc_cd = 'KC-AWARD' and nm = 'Blanket Approve AwardDocument';
update krim_perm_t set actv_ind = 'N' where nmspc_cd = 'KC-AWARD' and nm = 'Blanket Approve AwardBudgetDocument';
update krim_perm_t set actv_ind = 'N' where nmspc_cd = 'KC-AWARD' and nm = 'Blanket Approve TimeAndMoneyDocument';
commit;