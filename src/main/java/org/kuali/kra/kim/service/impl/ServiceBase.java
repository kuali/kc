/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.kim.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.kim.bo.KimGroup;
import org.kuali.kra.kim.bo.KimGroupAttribute;
import org.kuali.kra.kim.bo.KimGroupGroup;
import org.kuali.kra.kim.bo.KimGroupPerson;
import org.kuali.kra.kim.bo.KimNamespace;
import org.kuali.kra.kim.bo.KimPermission;
import org.kuali.kra.kim.bo.KimPerson;
import org.kuali.kra.kim.bo.KimPersonAttribute;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.kim.bo.KimRoleAttribute;
import org.kuali.kra.kim.bo.KimRoleGroup;
import org.kuali.kra.kim.bo.KimQualifiedRoleGroup;
import org.kuali.kra.kim.bo.KimRolePermission;
import org.kuali.kra.kim.bo.KimRolePerson;
import org.kuali.kra.kim.bo.KimQualifiedRolePerson;
import org.kuali.kra.kim.exception.UnknownGroupNameException;
import org.kuali.kra.kim.exception.UnknownNamespaceNameException;
import org.kuali.kra.kim.exception.UnknownPermissionNameException;
import org.kuali.kra.kim.exception.UnknownRoleNameException;
import org.kuali.kra.kim.exception.UnknownUsernameException;
import org.kuali.kra.kim.pojo.Attribute;
import org.kuali.kra.kim.pojo.Group;
import org.kuali.kra.kim.pojo.Namespace;
import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.kim.pojo.Person;
import org.kuali.kra.kim.pojo.PersonAttribute;
import org.kuali.kra.kim.pojo.Role;

/**
 * Base class for all KIM Service Implementations.
 * Provides a set of common methods for operating on KIM objects.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ServiceBase {

    protected BusinessObjectService businessObjectService = null;

    /**
     * Set the Business Object Service.  This service is used
     * to access the database.
     * @param businessObjectService the Business Object Service
     */
    public final void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    // General Database Methods
    //////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Save the business object to the database.
     * @param bo the business object to save
     */
    protected final void saveToDatabase(PersistableBusinessObject bo) {
        if (bo != null) {
            businessObjectService.save(bo);
        }
    }
    
    /**
     * Delete the business object from the database.
     * @param bo the business object to delete
     */
    protected final void deleteFromDatabase(PersistableBusinessObject bo) {
        if (bo != null) {
            businessObjectService.delete(bo);
        }
    }
    
    /**
     * Find a collection of business objects based upon one field value.
     * @param clazz the business object class
     * @param fieldName the field's name
     * @param fieldValue the field's value
     * @return the list of matching business objects
     */
    protected final Collection findMatching(Class clazz, String fieldName, Object fieldValue) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(fieldName, fieldValue);
        return businessObjectService.findMatching(clazz, fieldValues);
    }
    
    /**
     * Find a collection of business objects based upon two field values.
     * @param clazz the business object class
     * @param fieldName1 the first field's name
     * @param fieldValue1 the first field's value
     * @param fieldName2 the second field's name
     * @param fieldValue2 the second field's value
     * @return the list of matching business objects
     */
    protected final Collection findMatching(Class clazz, String fieldName1, Object fieldValue1,
                                                         String fieldName2, Object fieldValue2) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(fieldName1, fieldValue1);
        fieldValues.put(fieldName2, fieldValue2);
        return businessObjectService.findMatching(clazz, fieldValues);
    }
    
    /**
     * Count the collection of business objects based upon one field value.
     * @param clazz the business object class
     * @param fieldName the field's name
     * @param fieldValue the field's value
     * @return the list of matching business objects
     */
    protected final int countMatching(Class clazz, String fieldName, Object fieldValue) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(fieldName, fieldValue);
        return businessObjectService.countMatching(clazz, fieldValues);
    }
    
    /**
     * Count the collection of business objects based upon two field values.
     * @param clazz the business object class
     * @param fieldName1 the first field's name
     * @param fieldValue1 the first field's value
     * @param fieldName2 the second field's name
     * @param fieldValue2 the seocnd field's value
     * @return the list of matching business objects
     */
    protected final int countMatching(Class clazz, String fieldName1, Object fieldValue1,
                                                   String fieldName2, Object fieldValue2) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(fieldName1, fieldValue1);
        fieldValues.put(fieldName2, fieldValue2);
        return businessObjectService.countMatching(clazz, fieldValues);
    }
    
    /**
     * Get a business object by its primary key.
     * @param clazz the business object class
     * @param keyName the primary key's name
     * @param keyValue the primary key's value
     * @return the business object or null if not found
     */
    protected final PersistableBusinessObject findByPrimaryKey(Class clazz, String keyName, Object keyValue) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put(keyName, keyValue);
        return businessObjectService.findByPrimaryKey(clazz, primaryKey);
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    // Namespace Queries
    //////////////////////////////////////////////////////////////////////////////////

    /**
     * Get all of the KIM Namespaces.
     * @return the KIM Namespaces
     */
    protected final Collection<KimNamespace> getAllNamespaces() {
        return businessObjectService.findAll(KimNamespace.class);
    }
    
    /**
     * Get a KIM Namespace.
     * @param namespaceId the Namespace's ID
     * @return the KIM Namespace
     */
    protected final KimNamespace getNamespace(Long namespaceId) {
        return (KimNamespace) findByPrimaryKey(KimNamespace.class, "id", namespaceId);
    }
    
    /**
     * Get a KIM Namespace by it's name.
     * @param namespaceName the Namespace's Name
     * @return the KIM Namespace
     */
    protected final KimNamespace getNamespaceByName(String namespaceName) {
        Collection<KimNamespace> namespaces = findMatching(KimNamespace.class, "name", namespaceName);
        if (namespaces.size() != 1) {
            throw new UnknownNamespaceNameException(namespaceName);
        }
        return namespaces.iterator().next();
    }
    
    /**
     * Get the Namespace's ID.
     * @param namespaceName the name of the Namespace
     * @return the Namespace's ID
     */
    protected final Long getNamespaceId(String namespaceName) {
        KimNamespace namespace = getNamespaceByName(namespaceName);
        return namespace.getId();
    }
    
    /**
     * Get all of the KIM Permissions for a specific Namespace.
     * @param namespaceId the Namespace ID
     * @return the KIM Permissions
     */
    protected final Collection<KimPermission> getNamespacePermissions(Long namespaceId) {
        return findMatching(KimPermission.class, "namespaceId", namespaceId);
    }
    
    /**
     * Build a Namespace from a KIM Namespace.
     * @param kimNamespace the KIM Namespace
     * @return the Namespace
     */
    protected final Namespace buildNamespace(KimNamespace kimNamespace) {
        Namespace namespace = new Namespace();
        namespace.setName(kimNamespace.getName());
        namespace.setDescription(kimNamespace.getDescription());
        return namespace;
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    // Permission Queries
    //////////////////////////////////////////////////////////////////////////////////

    /**
     * Get a KIM Permission.
     * @param permissionId the Permission's ID
     * @return the KIM Permission
     */
    protected final KimPermission getPermission(Long permissionId) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put("id", permissionId);
        return (KimPermission) businessObjectService.findByPrimaryKey(KimPermission.class, primaryKey);
    }

    /**
     * Get the Permission's ID.
     * @param namespaceId the Namespace's ID
     * @param permissionName the name of the Permission
     * @return the Permission's ID
     */
    protected final Long getPermissionId(Long namespaceId, String permissionName) {
        Collection<KimPermission> permissions = findMatching(KimPermission.class, 
                                                             "namespaceId", namespaceId,
                                                             "name", permissionName);
        if (permissions.size() != 1) {
            throw new UnknownPermissionNameException(permissionName);
        }
        
        return permissions.iterator().next().getId();
    }
    
    /**
     * Build a Permission from a KIM Permission.
     * @param kimPermission the KIM Permission
     * @return the Permission
     */
    protected final Permission buildPermission(KimPermission kimPermission) {
        Permission permission = new Permission();
        permission.setName(kimPermission.getName());
        permission.setDescription(kimPermission.getDescription());
        KimNamespace namespace = getNamespace(kimPermission.getNamespaceId());
        permission.setNamespaceName(namespace.getName());
        return permission;
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    // Role Queries
    //////////////////////////////////////////////////////////////////////////////////

    /**
     * Get all of the KIM Roles.
     * @return the KIM Roles
     */
    protected final Collection<KimRole> getAllRoles() {
        return businessObjectService.findAll(KimRole.class);
    }

    /**
     * Get the Role's ID.
     * @param roleName the name of the Role
     * @return the Role's ID
     */
    protected final Long getRoleId(String roleName) {
        KimRole role = getRoleByName(roleName);
        return role.getId();
    }

    /**
     * Get a KIM Role.
     * @param roleId the Role's ID
     * @return the KIM Role or null if not found
     */
    protected final KimRole getRole(Long roleId) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put("id", roleId);
        return (KimRole) businessObjectService.findByPrimaryKey(KimRole.class, primaryKey);
    }
    
    /**
     * Get a KIM Role.
     * @param roleName the Role's name
     * @return the KIM Role or null if not found
     */
    protected final KimRole getRoleByName(String roleName) {
        Collection<KimRole> roles = findMatching(KimRole.class, "name", roleName);
		if (roles.size() != 1) {
		    throw new UnknownRoleNameException(roleName);
		}
		
        return roles.iterator().next();
    }
    
    /**
     * Build a Role from a KIM Role.
     * @param kimRole the KIM Role
     * @return the Role
     */
    protected final Role buildRole(KimRole kimRole) {
        Role role = new Role();
        role.setName(kimRole.getName());
        role.setDescription(kimRole.getDescription());
        
        List<Attribute> attributes = new ArrayList<Attribute>();
        Collection<KimRoleAttribute> kimAttributes = findMatching(KimRoleAttribute.class, "roleId", kimRole.getId());
        for (KimRoleAttribute kimAttribute : kimAttributes) {
            Attribute attribute = new Attribute(kimAttribute.getAttributeName(), kimAttribute.getAttributeValue());
            attributes.add(attribute);
        }
        role.setAttributes(attributes);
        
        return role;
    }
    
    /**
     * Get the Permissions assigned to a Role.
     * @param roleId the Role's ID
     * @return the Permissions in the Role
     */
    protected final List<KimPermission> getRolePermissions(Long roleId) {
        List<KimPermission> permissions = new ArrayList<KimPermission>();
        Collection<KimRolePermission> rolePermissions = findMatching(KimRolePermission.class, "roleId", roleId);
        for (KimRolePermission rolePermission : rolePermissions) {
            permissions.add(getPermission(rolePermission.getPermissionId()));
        }
        return permissions;
    }
    
    /**
     * Get the Persons who are assigned to a given Role.
     * @param roleId the Role's ID
     * @return the list of Persons
     */
    protected final List<KimPerson> getRolePersons(Long roleId) {
        List<KimPerson> persons = new ArrayList<KimPerson>();
        Collection<KimRolePerson> rolePersons = findMatching(KimRolePerson.class, "roleId", roleId);
        for (KimRolePerson rolePerson : rolePersons) {
            persons.add(getPerson(rolePerson.getPersonId()));
        }
        return persons;
    }

    /**
     * Get the Groups who are assigned to a given Role.
     * @param roleId the Role's ID
     * @return the list of Groups 
     */
    protected final List<KimGroup> getRoleGroups(Long roleId) {
        List<KimGroup> groups = new ArrayList<KimGroup>();
        Collection<KimRoleGroup> roleGroups = findMatching(KimRoleGroup.class, "roleId", roleId);
        for (KimRoleGroup roleGroup : roleGroups) {
            groups.add(getGroup(roleGroup.getGroupId()));
        }
        return groups;
    }
    
    /**
     * Does a Role have a given Permission?
     * @param roleId the Role's ID
     * @param permissionId the Permission's ID
     * @return true if the role does have this permission; otherwise false
     */
    protected final boolean hasPermission(Long roleId, Long permissionId) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put("roleId", roleId);
        primaryKey.put("permissionId", permissionId);
        int count = businessObjectService.countMatching(KimRolePermission.class, primaryKey);
        return count > 0;
    }

    /**
     * Do any of the Roles in the collection have the given Permission?
     * @param roleIds the collection of Role IDs
     * @param permissionId the Permission ID
     * @return true if any of the roles has the permission; otherwise false
     */
    protected final boolean hasPermission(Collection<Long> roleIds, Long permissionId) {
        for (Long roleId : roleIds) {
            if (hasPermission(roleId, permissionId)) {
                return true;
            }
        }
        return false;
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    // Person Queries
    //////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Get all of the KIM Persons.
     * @return the KIM Persons
     */
    protected final Collection<KimPerson> getAllPersons() {
        return businessObjectService.findAll(KimPerson.class);
    }

    /**
     * Get the Person's ID.
     * @param username the username of the Person
     * @return Person's ID
     */
    protected final Long getPersonId(String username) {
        KimPerson person = getPersonByName(username);
        return person.getId();
    }
    
    /**
     * Get a KIM Person.
     * @param personId the Person's ID
     * @return the KIM Person or null if not found
     */
    protected final KimPerson getPerson(Long personId) {
        Map<String, Object> primaryKey = new HashMap<String, Object>();
        primaryKey.put("id", personId);
        return (KimPerson) businessObjectService.findByPrimaryKey(KimPerson.class, primaryKey);
    }
    
    /**
     * Get the Person.
     * @param username the username of the Person
     * @return the Person
     */
    protected final KimPerson getPersonByName(String username) {
        Collection<KimPerson> persons = findMatching(KimPerson.class, "username", username);
        if (persons.size() != 1) {
            throw new UnknownUsernameException(username);
        }
        return persons.iterator().next();
    }
    
    /**
     * Build a Person from a KIM Person.
     * @param kimPerson the KIM Person
     * @return the Person
     */
    protected final Person buildPerson(KimPerson kimPerson) {
        Person person = new Person();
        person.setUsername(kimPerson.getUsername());
        person.setPassword(kimPerson.getPassword());
        
        List<PersonAttribute> personAttributes = new ArrayList<PersonAttribute>();
        Collection<KimPersonAttribute> attrs = findMatching(KimPersonAttribute.class, "personId", kimPerson.getId());
        for (KimPersonAttribute attr : attrs) {
            personAttributes.add(buildPersonAttribute(attr));
        }
        person.setAttributes(personAttributes);
        
        return person;
    }
    
    /**
     * Build a Person Attribute from a KIM Person Attribute.
     * @param kimAttr the KIM Person Attribute
     * @return the Person Attribute
     */
    protected final PersonAttribute buildPersonAttribute(KimPersonAttribute kimAttr) {
        String namespaceName = getNamespace(kimAttr.getNamespaceId()).getName();
        String name = kimAttr.getAttributeName();
        String value = kimAttr.getAttributeValue();
       return new PersonAttribute(namespaceName, name, value);
    }

    /**
     * Get the Group IDs that the Person is in.
     * @param personId the Person's ID
     * @param traverse if true traverse the Group Hierarchy to find all of the Groups;
     *        otherwise only find the immediate Groups the Person is in
     * @return the set of Group IDs the Person is in
     */
    protected final Set<Long> getPersonGroupIds(Long personId, boolean traverse) {
        Set<Long> groupIds = new HashSet<Long>();
        Collection<KimGroupPerson> groupPersons = findMatching(KimGroupPerson.class, "personId", personId);
        for (KimGroupPerson groupPerson : groupPersons) {
            Long groupId = groupPerson.getGroupId();
            if (!groupIds.contains(groupId)) {
                groupIds.add(groupId);
                if (traverse) {
                    groupIds.addAll(getGroupParentGroupIds(groupId, traverse));
                }
            }
        }
        return groupIds;
    }
    
    /**
     * Get the Role IDs that the Person has.
     * @param personId the Person's ID
     * @param traverse if true traverse the Group Hierarchy to find all of the Roles;
     *        otherwise only find the Roles the Person is directly assigned to
     * @return the set of Role IDs the Person has
     */
    protected final Set<Long> getPersonRoleIds(Long personId, boolean traverse) {
        Set<Long> roleIds = new HashSet<Long>();
        Collection<KimRolePerson> rolePersons = findMatching(KimRolePerson.class, "personId", personId);
        for (KimRolePerson rolePerson : rolePersons) {
            roleIds.add(rolePerson.getRoleId());
        }
        
        if (traverse) {
            Set<Long> groupIds = getPersonGroupIds(personId, true);
            for (Long groupId : groupIds) {
                roleIds.addAll(getGroupRoleIds(groupId, false));
            }
        }
        return roleIds;
    }
    
    /**
     * Get the value of a specific Person's Attribute.
     * @param personId the Person's ID
     * @param namespaceId the Namespace ID of the attribute
     * @param attrName the attribute's name
     * @return the value of the attribute or null if not found
     */
    protected String getPersonAttributeValue(Long personId, Long namespaceId, String attrName) {
        String attrValue = null;
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        fieldValues.put("namespaceId", namespaceId);
        fieldValues.put("attributeName", attrName);
        Collection<KimPersonAttribute> attributes = businessObjectService.findMatching(KimPersonAttribute.class, fieldValues);
        if (attributes.size() == 1) {
            KimPersonAttribute attr = attributes.iterator().next();
            attrValue = attr.getAttributeValue();
        }
        return attrValue;
    }
    
    /**
     * Does the Person have an attribute with the given namespace, name, and value.
     * @param personId the Person's ID
     * @param namespaceId the Namespace's ID
     * @param attrName the attribute name
     * @param attrValue the attribute value
     * @return true if the Person has this attribute; otherwise false
     */
    protected boolean hasPersonAttribute(Long personId, Long namespaceId, String attrName, String attrValue) {  
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        fieldValues.put("namespaceId", namespaceId);
        fieldValues.put("attributeName", attrName);
        fieldValues.put("attributeValue", attrValue);
        int cnt = businessObjectService.countMatching(KimPersonAttribute.class, fieldValues);
        return cnt == 1;
    }
    
    /**
     * Get the Persons whose attributes match against a given set.
     * @param namespaceId the ID of the namespace the attributes are in
     * @param personAttributes the attributes to match against
     * @return the Persons who have these attributes
     */
    protected List<KimPerson> getPersons(Long namespaceId, Map<String, String> personAttributes) {
        List<KimPerson> persons = new ArrayList<KimPerson>();
        Collection<KimPerson> allPersons = this.getAllPersons();
        for (KimPerson person : allPersons) {
            List<KimPersonAttribute> attributes = getPersonAttributes(person.getId());
            if (containsAttrs(attributes, namespaceId, personAttributes)) {
                persons.add(person);
            }
        }
        return persons;
    }
    
    /**
     * Get the Person Attributes for a Person.
     * @param personId the Person's ID
     * @return the list of Person Attributes
     */
    protected List<KimPersonAttribute> getPersonAttributes(Long personId) {
        List<KimPersonAttribute> personAttributes = new ArrayList<KimPersonAttribute>();
        personAttributes.addAll(findMatching(KimPersonAttribute.class, "personId", personId));
        return personAttributes;
    }
    
    /**
     * Does the set of Person Attributes contain the given attributes?
     * @param personAttributes the person attributes to search through
     * @param namespaceId the namespace of the attributes to search for
     * @param attributes the attributes to search for in the Person Attributes
     * @return true if the Person Attributes contains these attributes; otherwise false
     */
    private boolean containsAttrs(List<KimPersonAttribute> personAttributes, Long namespaceId, Map<String, String> attributes) {
        Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            if (!containsAttr(personAttributes, namespaceId, entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Does the set of Person Attributes contain the given attribute (name, value) pair?
     * @param personAttributes the Person Attributes to search through
     * @param namespaceId the name of the attribute to search for
     * @param attrName the attribute name to search for
     * @param attrValue the attribute value to search for
     * @return true if the attribute is found in the Person Attributes; otherwise false
     */
    private boolean containsAttr(List<KimPersonAttribute> personAttributes, Long namespaceId, String attrName, String attrValue) {
        for (KimPersonAttribute attribute : personAttributes) {
            if (attribute.getNamespaceId().equals(namespaceId) &&
                attribute.getAttributeName().equals(attrName) &&
                attribute.getAttributeValue().equals(attrValue))
                return true;
        }
        return false;
    }
        
    //////////////////////////////////////////////////////////////////////////////////
    // Group Queries
    //////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Get all of the KIM Groups.
     * @return the KIM Groups
     */
    protected final Collection<KimGroup> getAllGroups() {
        return businessObjectService.findAll(KimGroup.class);
    }

    /**
     * Get a KIM Group.
     * @param groupId the Group's ID
     * @return the KIM Group or null if not found
     */
    protected final KimGroup getGroup(Long groupId) {
        return (KimGroup) findByPrimaryKey(KimGroup.class, "id", groupId);
    }
    
    /**
     * Get a KIM Group.
     * @param groupName the Group's Name
     * @return the KIM Group or null if not found
     */
    protected final KimGroup getGroupByName(String groupName) {
        Collection<KimGroup> groups = findMatching(KimGroup.class, "name", groupName);
        if (groups.size() != 1) {
            throw new UnknownGroupNameException(groupName);
        }
        
        return groups.iterator().next();
    }

    /**
     * Get the Group's ID.
     * @param groupName the name of the Group
     * @return the Group's ID
     */
    protected final Long getGroupId(String groupName) {
        KimGroup group = getGroupByName(groupName);
        return group.getId();
    }
    
    /**
     * Get all of the group's attributes.
     * @param groupId the group's ID
     * @return the group's attributes
     */
    protected final Collection<KimGroupAttribute> getGroupAttributes(Long groupId) {
        return findMatching(KimGroupAttribute.class, "groupId", groupId);
    }
    
    /**
     * Does the set of KIM Group Attribute have the given (name, value) pair?
     * @param kimGroupAttributes the KIM Group Attributes to search through
     * @param attrName the attribute name to search for
     * @param attrValue the attribute value to search for
     * @return true if the (name, value) pair is in the list; otherwise false
     */
    protected final boolean containsAttr(Collection<KimGroupAttribute> kimGroupAttributes, String attrName, String attrValue) {
        for (KimGroupAttribute kimGroupAttribute : kimGroupAttributes) {
            if (kimGroupAttribute.getAttributeName().equals(attrName) &&
                kimGroupAttribute.getAttributeValue().equals(attrValue)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Does the set of KIM Group Attributes contain the given set of (name, value) pairs?
     * @param kimGroupAttributes the KIM Group Attributes to search through
     * @param attributes the set of (name, value) pairs to look for
     * @return true if all of the (name, value) pairs are in the Group Attributes; otherwise false
     */
    protected final boolean containsAttrs(Collection<KimGroupAttribute> kimGroupAttributes, Map<String, String> attributes) {
        Iterator<Entry<String, String>> iterator = attributes.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            if (containsAttr(kimGroupAttributes, entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Build a Group from a KIM Group.
     * @param kimGroup the KIM Group
     * @return the Group
     */
    protected final Group buildGroup(KimGroup kimGroup) {
        Group group = new Group();
        group.setName(kimGroup.getName());
        group.setDescription(kimGroup.getDescription());
        
        List<Attribute> attributes = new ArrayList<Attribute>();
        Collection<KimGroupAttribute> kimAttributes = findMatching(KimGroupAttribute.class, "groupId", kimGroup.getId());
        for (KimGroupAttribute kimAttribute : kimAttributes) {
            Attribute attribute = new Attribute(kimAttribute.getAttributeName(), kimAttribute.getAttributeValue());
            attributes.add(attribute);
        }
        group.setAttributes(attributes);
        
        return group;
    }
    
    /**
     * Get the Group IDs that the Group is in.
     * @param groupId the Group's ID
     * @param traverse if true traverse the Group Hierarchy to find all of the Groups;
     *        otherwise only find the immediate Groups the Group is in
     * @return the set of Group IDs the Person is in
     */
    protected final Set<Long> getGroupParentGroupIds(Long groupId, boolean traverse) {
        Set<Long> groupIds = new HashSet<Long>();
        Collection<KimGroupGroup> groupGroups = findMatching(KimGroupGroup.class, "memberGroupId", groupId);
        for (KimGroupGroup groupGroup : groupGroups) {
            Long parentGroupId = groupGroup.getParentGroupId();
            if (!groupIds.contains(parentGroupId)) {
                groupIds.add(parentGroupId);
                if (traverse) {
                    groupIds.addAll(getGroupParentGroupIds(parentGroupId, traverse));
                }
            }
        }
        return groupIds;
    }
    
    /**
     * Get the Role IDs that the Group has.
     * @param groupId the Group's ID
     * @param traverse if true traverse the Group Hierarchy to find all of the Roles;
     *        otherwise only find the Roles the Group is directly assigned to
     * @return the set of Role IDs the Group has
     */
    protected final Set<Long> getGroupRoleIds(Long groupId, boolean traverse) {
        Set<Long> roleIds = new HashSet<Long>();
        Collection<KimRoleGroup> roleGroups = findMatching(KimRoleGroup.class, "groupId", groupId);
        for (KimRoleGroup roleGroup : roleGroups) {
            roleIds.add(roleGroup.getRoleId());
        }

        if (traverse) {
            Set<Long> parentGroupIds = getGroupParentGroupIds(groupId, false);
            for (Long parentGroupId : parentGroupIds) {
                roleIds.addAll(getGroupRoleIds(parentGroupId, traverse));
            }
        }
        return roleIds;
    }
    
    /**
     * Get all of the Roles for a Group.
     * @param groupId the Group's ID
     * @return the Roles a Group is in
     */
    protected final List<KimRole> getGroupRoles(Long groupId) {
        List<KimRole> groupRoles = new ArrayList<KimRole>();
        Set<Long> roleIds = getGroupRoleIds(groupId, true);
        for (Long roleId : roleIds) {
            groupRoles.add(getRole(roleId));
        }
        return groupRoles;
    }
    
    /**
     * Get all of the Permissions for a Group.
     * @param groupId the Group's ID
     * @return all of the Group's Permissions
     */
    protected final Set<KimPermission> getGroupPermissions(Long groupId) {
        Set<KimPermission> groupPermissions = new HashSet<KimPermission>();
        Set<Long> roleIds = getGroupRoleIds(groupId, true);
        for (Long roleId : roleIds) {
            List<KimPermission> rolePermissions = this.getRolePermissions(roleId);
            groupPermissions.addAll(rolePermissions);
        }
        return groupPermissions;
    }
    
    /**
     * Get the list of Person IDs in a Group.
     * @param groupId the Group's ID
     * @return the list of Person IDs in the Group
     */
    protected final List<Long> getGroupMemberPersonIds(Long groupId) {
        List<Long> personIds = new ArrayList<Long>();
        Collection<KimGroupPerson> groupPersons = findMatching(KimGroupPerson.class, "groupId", groupId);
        for (KimGroupPerson groupPerson : groupPersons) {
            personIds.add(groupPerson.getPersonId());
        }
        return personIds;
    }
    
    /**
     * Get the list of Persons in a Group
     * @param groupId the Group's ID
     * @return the list of Persons
     */
    protected final List<KimPerson> getGroupMemberPersons(Long groupId) {
        List<KimPerson> persons = new ArrayList<KimPerson>();
        List<Long> personIds = getGroupMemberPersonIds(groupId);
        for (Long personId : personIds) {
            persons.add(getPerson(personId));
        }
        return persons;
    }

    /**
     * Get the list of Group IDs in a Group.
     * @param groupId the Group's ID
     * @return the list of Group IDs in the Group
     */
    protected final List<Long> getGroupMemberGroupIds(Long groupId) {
        List<Long> groupIds = new ArrayList<Long>();
        Collection<KimGroupGroup> groupGroups = findMatching(KimGroupGroup.class, "parentGroupId", groupId);
        for (KimGroupGroup groupGroup : groupGroups) {
            groupIds.add(groupGroup.getMemberGroupId());
        }
        return groupIds;
    }
    
    /**
     * Get the list of Groups in a Group
     * @param groupId the Group's ID
     * @return the list of Groups
     */
    protected final List<KimGroup> getGroupMemberGroups(Long groupId) {
        List<KimGroup> groups = new ArrayList<KimGroup>();
        List<Long> groupIds = getGroupMemberPersonIds(groupId);
        for (Long memberGroupId : groupIds) {
            groups.add(getGroup(memberGroupId));
        }
        return groups;
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Role-Person Queries
    //////////////////////////////////////////////////////////////////////////////////

    /**
     * Get a KIM Role-Person association.
     * @param roleId the Role's ID
     * @param personId the Person's ID
     * @return the Role-Person association
     */
    protected final KimRolePerson getRolePerson(Long roleId, Long personId) {
        KimRolePerson rolePerson = null;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("roleId", roleId);
        fieldValues.put("personId", personId);
        Collection<KimRolePerson> rolePersons = businessObjectService.findMatching(KimRolePerson.class, fieldValues);
        if (rolePersons.size() == 1) {
            rolePerson = rolePersons.iterator().next();
        }
        return rolePerson;
    }
    
    /**
     * Get a KIM Person Qualified Role association.
     * @param roleId the Role's ID
     * @param personId the Person's ID
     * @param attrName the attribute's name
     * @param attrValue the attribute's value
     * @return
     */
    protected final KimQualifiedRolePerson getQualifiedRolePerson(Long roleId, Long personId,
                                                         Map<String, String> qualifiedRoleAttributes) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("roleId", roleId);
        fieldValues.put("personId", personId);
       
        Collection<KimQualifiedRolePerson> rolePersons = businessObjectService.findMatching(KimQualifiedRolePerson.class, fieldValues);
    
        for (KimQualifiedRolePerson rolePerson : rolePersons) {
            if (rolePerson.matches(qualifiedRoleAttributes)) {
                return rolePerson;
            }
        }
        return null;
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    // Person Qualified Role Queries
    //////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Determine if a person has a qualified role.  A person is deemed to have
     * the qualified role if the given qualifiedRoleAttributes is a subset of
     * the qualified role.
     * @param personId the Person's ID
     * @param roleId the Role's ID
     * @param qualifiedRoleAttributes the qualified attributes
     * @return true if the person has the qualified role; otherwise false
     */
    protected final boolean hasPersonQualifiedRole(Long personId, Long roleId, Map<String, String> qualifiedRoleAttributes) {
        boolean hasQualifiedRole = false;
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        fieldValues.put("roleId", roleId);
   
        Collection<KimQualifiedRolePerson> rolePersons = businessObjectService.findMatching(KimQualifiedRolePerson.class, fieldValues);
        for (KimQualifiedRolePerson rolePerson : rolePersons) {
            if (rolePerson.partialMatch(qualifiedRoleAttributes)) {
                hasQualifiedRole = true;
                break;
            }
        }
        return hasQualifiedRole;
    }
    
    /**
     * Get the Role IDs for a set of Qualified Roles that a Person is in.  The Qualified Roles
     * must match the given qualifiedRoleAttributes.  A match occurs if the given qualifiedRoleAttributes
     * is a subset of the Qualified Role's attributes.
     * 
     * @param personId the Person's ID
     * @param qualifiedRoleAttributes the qualified attributes to match against
     * @return the set of Role IDs
     */
    protected final Set<Long> getPersonQualifiedRoleIds(Long personId, Map<String, String> qualifiedRoleAttributes) {
        // Get all of the Qualified Roles that the Person is in.
        List<KimQualifiedRolePerson> qualifiedRoles = getPersonQualifiedRoles(personId);
        
        // Only add the Role IDs for those Qualified Roles 
        // that match the given qualifiedRoleAttributes.
        
        Set<Long> roleIds = new HashSet<Long>();
        for (KimQualifiedRolePerson rolePerson : qualifiedRoles) {
            if (rolePerson.partialMatch(qualifiedRoleAttributes)) {
                roleIds.add(rolePerson.getRoleId());
            }
        }
        
        return roleIds;
    }
    
    /**
     * Get all of the Qualified Roles that Person is in.
     * @param personId the Person's ID
     * @return the list of Qualified Roles the Person is in
     */
    protected List<KimQualifiedRolePerson> getPersonQualifiedRoles(Long personId) {
        List<KimQualifiedRolePerson> qualifiedRoles = new ArrayList<KimQualifiedRolePerson>();
        qualifiedRoles.addAll(findMatching(KimQualifiedRolePerson.class, "personId", personId));
        return qualifiedRoles;
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    // Group Qualified Role Queries
    //////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Determine if a group has a qualified role.  A group is deemed to have
     * the qualified role if the given qualifiedRoleAttributes is a subset of
     * the qualified role.
     * @param groupId the Group's ID
     * @param roleId the Role's ID
     * @param qualifiedRoleAttributes the qualified attributes
     * @return true if the group has the qualified role; otherwise false
     */
    protected final boolean hasGroupQualifiedRole(Long groupId, Long roleId, Map<String, String> qualifiedRoleAttributes) {
        boolean hasQualifiedRole = false;
        
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("groupId", groupId);
        fieldValues.put("roleId", roleId);
        Collection<KimQualifiedRoleGroup> roleGroups = businessObjectService.findMatching(KimQualifiedRoleGroup.class, fieldValues);
        for (KimQualifiedRoleGroup roleGroup : roleGroups) {
            if (roleGroup.partialMatch(qualifiedRoleAttributes)) {
                hasQualifiedRole = true;
                break;
            }
        }
        return hasQualifiedRole;
    }
   
    /**
     * Get the Role IDs for a set of Qualified Roles that a Group is in.  The Qualified Roles
     * must match the given qualifiedRoleAttributes.  A match occurs if the given qualifiedRoleAttributes
     * is a subset of the Qualified Role's attributes.
     * 
     * @param groupId the Group's ID
     * @param qualifiedRoleAttributes the qualified attributes to match against
     * @return the set of Role IDs
     */
    protected final Set<Long> getGroupQualifiedRoleIds(Long groupId, Map<String, String> qualifiedRoleAttributes) {  
        
        // Get all of the Qualified Roles that the Person is in.
        List<KimQualifiedRoleGroup> qualifiedRoles = getGroupQualifiedRoles(groupId);
        
        // Only add the Role IDs for those Qualified Roles 
        // that match the given qualifiedRoleAttributes.
        
        Set<Long> roleIds = new HashSet<Long>();
        for (KimQualifiedRoleGroup roleGroup : qualifiedRoles) {
            if (roleGroup.partialMatch(qualifiedRoleAttributes)) {
                roleIds.add(roleGroup.getRoleId());
            }
        }
        
        return roleIds;
    }
    
    /**
     * Get all of the Qualified Roles that the Group is in.
     * @param groupId the Group's ID
     * @return the list of Qualified Roles the Group is in
     */
    protected List<KimQualifiedRoleGroup> getGroupQualifiedRoles(Long groupId) {
        List<KimQualifiedRoleGroup> qualifiedRoles = new ArrayList<KimQualifiedRoleGroup>();
        qualifiedRoles.addAll(findMatching(KimQualifiedRoleGroup.class, "groupId", groupId));
        return qualifiedRoles;
    }
}
