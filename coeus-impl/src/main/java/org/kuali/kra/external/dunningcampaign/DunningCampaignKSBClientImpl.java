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
package org.kuali.kra.external.dunningcampaign;

import org.kuali.kfs.module.external.kc.service.DunningCampaignService;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;

public final class DunningCampaignKSBClientImpl extends DunningCampaignClientBase {
    
    private DunningCampaignKSBClientImpl() {  }
    
    public static DunningCampaignClient getInstance() {
      if (ksbClient == null)
          ksbClient = new DunningCampaignKSBClientImpl();
      return ksbClient;
    }

    private static DunningCampaignKSBClientImpl ksbClient;

    @Override
    protected DunningCampaignService getServiceHandle() {
        DunningCampaignService dunningCampaignService = (DunningCampaignService) GlobalResourceLoader.getService(SERVICE_NAME);
        return dunningCampaignService;
    }
}
