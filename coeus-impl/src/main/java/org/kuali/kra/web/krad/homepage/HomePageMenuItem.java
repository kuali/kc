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
package org.kuali.kra.web.krad.homepage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "DASH_BOARD_MENU_ITEMS")
public class HomePageMenuItem extends KcPersistableBusinessObjectBase {
    @PortableSequenceGenerator(name = "SEQ_DASH_BOARD_MENU_ITEM_ID")
    @GeneratedValue(generator = "SEQ_DASH_BOARD_MENU_ITEM_ID")
	@Id
	@Column(name = "DASH_BOARD_MENU_ITEM_ID")
    private String id;

    @Column(name = "MENU_ITEM")
    private String menuItem;

    @Column(name = "MENU_ACTION")
    private String menuAction;

    @Column(name = "MENU_TYPE_FLAG")
    private String menuTypeFlag;

    @Column(name = "ACTIVE")
    private String active;

    @Transient
    private String menuItemFormatted;

    private enum MenuTypeIndicator {
        UNKNOWN ("UNKNOWN", "(Not Defined)"),
        OSP_ONLY ("O", "(OSP Only)"),
        ADMIN_ONLY("A", "(Admin Only)");

        private String code;
        private String description;

        private MenuTypeIndicator(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() { return code; }
        public String getDescription() { return description; }
    };
 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuItemFormatted() {
        if (StringUtils.isNotBlank(menuItem)) {
            if (menuTypeFlag != null) {
            	String menuIndicator = MenuTypeIndicator.UNKNOWN.getDescription();
            	for (MenuTypeIndicator menuTypeIndicator : MenuTypeIndicator.values()) {
            		if(menuTypeFlag.equalsIgnoreCase(menuTypeIndicator.getCode())) {
            			menuIndicator = menuTypeIndicator.getDescription();
            		}
            	}
                menuItemFormatted = menuItem + "<span class='osp-ind'>" + menuIndicator + "</span>";
            } else {
                menuItemFormatted = menuItem;
            }
        }

        return menuItemFormatted;
    }

    public void setMenuItemFormatted(String menuItemFormatted) {
        this.menuItemFormatted = menuItemFormatted;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public String getMenuTypeFlag() {
        return menuTypeFlag;
    }

    public void setMenuTypeFlag(String menuTypeFlag) {
        this.menuTypeFlag = menuTypeFlag;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
