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
package org.kuali.coeus.common.framework.sponsor.hierarchy;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.PermissionConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SponsorHierarchy extends KcPersistableBusinessObjectBase implements Permissionable {

    private static final long serialVersionUID = 2255685234044720175L;

    private String hierarchyName;

    private String sponsorCode;

    private String level1;

    private String level10;

    private Integer level10Sortid;

    private Integer level1Sortid;

    private String level2;

    private Integer level2Sortid;

    private String level3;

    private Integer level3Sortid;

    private String level4;

    private Integer level4Sortid;

    private String level5;

    private Integer level5Sortid;

    private String level6;

    private Integer level6Sortid;

    private String level7;

    private Integer level7Sortid;

    private String level8;

    private Integer level8Sortid;

    private String level9;

    private Integer level9Sortid;

    private Sponsor sponsor;

    public SponsorHierarchy() {
        super();
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel10() {
        return level10;
    }

    public void setLevel10(String level10) {
        this.level10 = level10;
    }

    public Integer getLevel10Sortid() {
        return level10Sortid;
    }

    public void setLevel10Sortid(Integer level10Sortid) {
        this.level10Sortid = level10Sortid;
    }

    public Integer getLevel1Sortid() {
        return level1Sortid;
    }

    public void setLevel1Sortid(Integer level1Sortid) {
        this.level1Sortid = level1Sortid;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public Integer getLevel2Sortid() {
        return level2Sortid;
    }

    public void setLevel2Sortid(Integer level2Sortid) {
        this.level2Sortid = level2Sortid;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public Integer getLevel3Sortid() {
        return level3Sortid;
    }

    public void setLevel3Sortid(Integer level3Sortid) {
        this.level3Sortid = level3Sortid;
    }

    public String getLevel4() {
        return level4;
    }

    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public Integer getLevel4Sortid() {
        return level4Sortid;
    }

    public void setLevel4Sortid(Integer level4Sortid) {
        this.level4Sortid = level4Sortid;
    }

    public String getLevel5() {
        return level5;
    }

    public void setLevel5(String level5) {
        this.level5 = level5;
    }

    public Integer getLevel5Sortid() {
        return level5Sortid;
    }

    public void setLevel5Sortid(Integer level5Sortid) {
        this.level5Sortid = level5Sortid;
    }

    public String getLevel6() {
        return level6;
    }

    public void setLevel6(String level6) {
        this.level6 = level6;
    }

    public Integer getLevel6Sortid() {
        return level6Sortid;
    }

    public void setLevel6Sortid(Integer level6Sortid) {
        this.level6Sortid = level6Sortid;
    }

    public String getLevel7() {
        return level7;
    }

    public void setLevel7(String level7) {
        this.level7 = level7;
    }

    public Integer getLevel7Sortid() {
        return level7Sortid;
    }

    public void setLevel7Sortid(Integer level7Sortid) {
        this.level7Sortid = level7Sortid;
    }

    public String getLevel8() {
        return level8;
    }

    public void setLevel8(String level8) {
        this.level8 = level8;
    }

    public Integer getLevel8Sortid() {
        return level8Sortid;
    }

    public void setLevel8Sortid(Integer level8Sortid) {
        this.level8Sortid = level8Sortid;
    }

    public String getLevel9() {
        return level9;
    }

    public void setLevel9(String level9) {
        this.level9 = level9;
    }

    public Integer getLevel9Sortid() {
        return level9Sortid;
    }

    public void setLevel9Sortid(Integer level9Sortid) {
        this.level9Sortid = level9Sortid;
    }

    public boolean isNihSponsorInAnylevel(String nihIndicator) {
        boolean isNih = false;
        for (String levelValue : getAllLevelValues()) {
            isNih = StringUtils.isNotEmpty(levelValue) && (levelValue.equals(nihIndicator));
            if (isNih) {
                break;
            }
        }
        return isNih;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    private String[] getAllLevelValues() {
        return new String[] { getLevel1(), getLevel2(), getLevel3(), getLevel4(), getLevel5(), getLevel5(), getLevel6(), getLevel7(), getLevel8(), getLevel9(), getLevel10() };
    }

    @Override
    public String getDocumentNumberForPermission() {
        return this.getSponsorCode() != null ? this.getSponsorCode() : ""; 
    }

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.SPONSOR_HIREARCHY_KEY;
    }

    @Override
    public List<String> getRoleNames() {
        List<String> roles = new ArrayList<String>();
        roles.add(PermissionConstants.SPONSOR_HIERARCHY_ADD);
        roles.add(PermissionConstants.SPONSOR_HIERARCHY_DELETE);
        roles.add(PermissionConstants.SPONSOR_HIERARCHY_MODIFY);
        return roles;
    }

    @Override
    public String getNamespace() {
        return KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE;
    }

    @Override
    public String getLeadUnitNumber() {
        return (this.getSponsor() != null && this.getSponsor().getUnit() != null) ? this.getSponsor().getUnit().getUnitNumber() : "";
    }

    @Override
    public String getDocumentRoleTypeCode() {
        return getNamespace();
    }

    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {

        
    }
}
