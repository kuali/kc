DELETE FROM KREW_DOC_TYP_T T 
WHERE T.DOC_TYP_NM IN ('IdentityManagementGroupDocument', 'IdentityManagementPersonDocument', 'IdentityManagementRoleDocument', 'ParameterMaintenanceDocument', 'IdentityManagementGenericPermissionMaintenanceDocument') 
and parnt_id not in (2681, 2994);
commit;

