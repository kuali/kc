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
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionQualifierTypeBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;
import org.kuali.kra.protocol.actions.submit.ValidProtoSubTypeQualMaintenanceDocumentRuleBase;


 /**
  * 
  * This class is the maintenance document rule for valid submission/type qualifier table.
  */
 public class ValidProtoSubTypeQualMaintenanceDocumentRule extends ValidProtoSubTypeQualMaintenanceDocumentRuleBase {

         @Override
         protected Class<? extends ProtocolSubmissionTypeBase> getProtocolSubmissionTypeBOClassHook() {
             return ProtocolSubmissionType.class;
         }

         @Override
         protected Class<? extends ProtocolSubmissionQualifierTypeBase> getProtocolSubmissionQualifierTypeBOClassHook() {
             return ProtocolSubmissionQualifierType.class;
         }
         
         @Override
         protected  Class<? extends ValidProtoSubTypeQual> getValidProtoSubTypeQualBOClassHook() {
             return org.kuali.kra.irb.actions.submit.ValidProtoSubTypeQual.class;
         }

} 
 
