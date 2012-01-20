UPDATE KRIM_ROLE_T SET ROLE_NM='Award Attachments Maintainer',DESC_TXT='Role grants permission to add, view or delete any Attachment in an Award' WHERE ROLE_NM='Award Documents Maintainer' AND NMSPC_CD='KC-AWARD'
/
UPDATE KRIM_PERM_T SET NM='Maintain Award Attachments',DESC_TXT='Permission to add, view or delete any Attachment in an Award' WHERE NM='Maintain Award Documents' AND NMSPC_CD='KC-AWARD'
/
UPDATE KRIM_ROLE_T SET ROLE_NM='Award Attachments Viewer',DESC_TXT='Role grants permission to view any Attachment in an Award' WHERE ROLE_NM='Award Documents Viewer' AND NMSPC_CD='KC-AWARD'
/
UPDATE KRIM_PERM_T SET NM='View Award Attachments',DESC_TXT='Permission to view any Attachment in an Award' WHERE NM='View Award Documents' AND NMSPC_CD='KC-AWARD'
/