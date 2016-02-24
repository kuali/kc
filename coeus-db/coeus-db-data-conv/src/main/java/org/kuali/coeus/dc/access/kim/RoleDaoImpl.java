/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.dc.access.kim;


import org.kuali.coeus.dc.common.db.ConnectionDaoService;
import org.kuali.coeus.dc.common.db.SequenceDaoService;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.*;

public class RoleDaoImpl implements RoleDao {

    private static final Logger LOG = Logger.getLogger(RoleDaoImpl.class.getName());

    private ConnectionDaoService connectionDaoService;
    private KimTypeDao kimTypeDao;
    private SequenceDaoService sequenceDaoService;

    @Override
    public void copyRoleMembersToDocAccessType(Collection<String> roleIds, KimAttributeDocumentValueHandler handler) {

        final String kimTypeId = kimTypeDao.getDocAccessKimTypeId();

        if (kimTypeId == null || kimTypeId.trim().equals("")) {
            throw new IllegalStateException("Doc Access Kim Type is not found");
        }

        for (String roleId : roleIds) {
            Role existingRole = getRole(roleId);

            if (!existingRole.getKimTypeId().equals(kimTypeId) && copyExists(createNewRoleName(existingRole.getName()), existingRole.getNamespaceCode())) {
                copyRoleMembers(existingRole, handler);
            }
        }

        handler.cleanup();
    }

    protected boolean copyExists(String name, String namespace) {
        Connection connection = connectionDaoService.getRiceConnection();

        try (PreparedStatement stmt = setString(2, namespace, setString(1, name, connection.prepareStatement("SELECT count(*) from KRIM_ROLE_T WHERE ROLE_NM = ? AND NMSPC_CD = ?")));
            ResultSet result = stmt.executeQuery()) {
            result.next();
            final boolean exists =  result.getInt(1) > 0;
            if (exists) {
                return true;
            } else {
                LOG.warning("Copy Role does not exist for name: " + name + " and namespace: " + namespace);
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String createNewRoleName(String name) {
        return name + " Document Level";
    }

    protected void copyRoleMembers(Role existingRole, KimAttributeDocumentValueHandler handler) {

        Collection<DocumentAccess> accessesToSave = new ArrayList<>();
        Collection<String> roleMbrDeleteIds = new HashSet<>();
        for (RoleMember member : getRoleMembers(existingRole.getId())) {
            if ("P".equals(member.getTypeCode())) {
                Collection<RoleMemberAttributeData> attrs = getRoleMemberAttributeData(member.getId());
                for (RoleMemberAttributeData attr : attrs) {
                    if (handler.isDocumentValueType(attr.getKimAttributeId())) {
                    	if (attr.getAttributeValue() != null) {
                            String documentNumber = handler.transform(attr.getAttributeValue());
                            if (documentNumber != null) {
                                DocumentAccess access = new DocumentAccess();
                                access.setId(sequenceDaoService.getNextCoeusSequence("SEQ_DOCUMENT_ACCESS_ID", ""));
                                access.setDocumentNumber(documentNumber);
                                access.setPrincipalId(member.getMemberId());
                                access.setRoleName(createNewRoleName(existingRole.getName()));
                                access.setNamespaceCode(existingRole.getNamespaceCode());
                                access.setUpdateUser("kc-doc-access-conv");
                                access.setUpdateTimestamp(new Timestamp(new java.util.Date().getTime()));
                                access.setVersionNumber(1L);
                                access.setObjectId(UUID.randomUUID().toString());

                                accessesToSave.add(access);
                                roleMbrDeleteIds.add(member.getId());
                            }
                    	}
                    }
                }
            }
        }

        final Set<DocumentAccess> filtered = new TreeSet<>(new Comparator<DocumentAccess>(){
            @Override
            public int compare(DocumentAccess o1, DocumentAccess o2) {
                if (o1.getPrincipalId().equals(o2.getPrincipalId())
                        && o1.getDocumentNumber().equals(o2.getDocumentNumber())
                        && o1.getRoleName().equals(o2.getRoleName())
                        && o1.getNamespaceCode().equals(o2.getNamespaceCode())) {
                    return 0;
                } else {
                    return o1.getDocumentNumber().compareTo(o2.getDocumentNumber());
                }
            }
        });
        filtered.addAll(accessesToSave);

        if (filtered.size() != accessesToSave.size()) {
            LOG.warning("Duplicate role member document qualifiers detected");
        }

        saveDocumentAccess(filtered);
        deleteRoleMembers(roleMbrDeleteIds);
    }
    
    private void deleteRoleMembers(Collection<String> roleMemberIdsToDelete) {
        for (String roleMbrId: roleMemberIdsToDelete) {
        	LOG.info("Removing KRIM_ROLE_MBR_T for ROLE_MBR_ID=" + roleMbrId);
            Connection connection = connectionDaoService.getRiceConnection();
            // First remove attribute data for the role member (data integrity)
            try (PreparedStatement stmt = setString(1, roleMbrId, connection.prepareStatement("DELETE FROM KRIM_ROLE_MBR_ATTR_DATA_T WHERE ROLE_MBR_ID = ?"))) {
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // Then remove the memeber data itself
            try (PreparedStatement stmt = setString(1, roleMbrId, connection.prepareStatement("DELETE FROM KRIM_ROLE_MBR_T WHERE ROLE_MBR_ID = ?"))) {
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void saveDocumentAccess(Collection<DocumentAccess> accesses) {
        for (DocumentAccess access : accesses) {
            Connection connection = connectionDaoService.getCoeusConnection();
            try (PreparedStatement stmt = setString(9, access.getObjectId(),
                                        setLong(8, access.getVersionNumber(),
                                        setString(7, access.getUpdateUser(),
                                        setTimestamp(6, access.getUpdateTimestamp(),
                                        setString(5, access.getNamespaceCode(),
                                        setString(4, access.getRoleName(),
                                        setString(3, access.getPrincipalId(),
                                        setString(2, access.getDocumentNumber(),
                                        setString(1, access.getId(), connection.prepareStatement("INSERT INTO DOCUMENT_ACCESS (DOC_ACCESS_ID, DOC_HDR_ID, PRNCPL_ID, ROLE_NM, NMSPC_CD, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"))))))))))) {
            	stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected Collection<RoleMember> getRoleMembers(String roleId) {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(1, roleId,
                    connection.prepareStatement("SELECT ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT FROM KRIM_ROLE_MBR_T WHERE ROLE_ID = ?"));
            ResultSet result = stmt.executeQuery()) {

            final Collection<RoleMember> members = new ArrayList<RoleMember>();
            while(result.next()) {
                RoleMember member = new RoleMember();
                member.setId(result.getString(1));
                member.setVersionNumber(result.getLong(2));
                member.setObjectId(result.getString(3));
                member.setRoleId(result.getString(4));
                member.setMemberId(result.getString(5));
                member.setTypeCode(result.getString(6));
                member.setActiveFromDateValue(result.getTimestamp(7));
                member.setActiveToDateValue(result.getTimestamp(8));
                member.setLastUpdatedDate(result.getTimestamp(9));

                members.add(member);
            }
            return members;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    protected Collection<RoleMemberAttributeData> getRoleMemberAttributeData(String roleMemberId) {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(1, roleMemberId, connection.prepareStatement("SELECT ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL FROM KRIM_ROLE_MBR_ATTR_DATA_T WHERE ROLE_MBR_ID = ?"));    		
        	ResultSet result = stmt.executeQuery()) {

            final Collection<RoleMemberAttributeData> attrs = new ArrayList<RoleMemberAttributeData>();
            while(result.next()) {
                RoleMemberAttributeData attr = new RoleMemberAttributeData();
                attr.setId(result.getString(1));
                attr.setObjectId(result.getString(2));
                attr.setVersionNumber(result.getLong(3));
                attr.setRoleMemberId(result.getString(4));
                attr.setKimTypeId(result.getString(5));
                attr.setKimAttributeId(result.getString(6));
                attr.setAttributeValue(result.getString(7));

                attrs.add(attr);
            }
            return attrs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Role getRole(String roleId) {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(1, roleId, connection.prepareStatement("SELECT ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, OBJ_ID, VER_NBR, LAST_UPDT_DT FROM KRIM_ROLE_T WHERE ROLE_ID = ?"));
            ResultSet result = stmt.executeQuery()) {
            if (result.next())  {
                final Role role = new Role();
                role.setId(result.getString(1));
                role.setName(result.getString(2));
                role.setNamespaceCode(result.getString(3));
                role.setDescription(result.getString(4));
                role.setKimTypeId(result.getString(5));
                role.setActive(result.getString(6));
                role.setObjectId(result.getString(7));
                role.setVersionNumber(result.getLong(8));
                role.setLastUpdatedDate(result.getTimestamp(9));
                return role;
            } else {
                throw new IllegalStateException("role not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }

    public KimTypeDao getKimTypeDao() {
        return kimTypeDao;
    }

    public void setKimTypeDao(KimTypeDao kimTypeDao) {
        this.kimTypeDao = kimTypeDao;
    }

    public SequenceDaoService getSequenceDaoService() {
        return sequenceDaoService;
    }

    public void setSequenceDaoService(SequenceDaoService sequenceDaoService) {
        this.sequenceDaoService = sequenceDaoService;
    }


}
