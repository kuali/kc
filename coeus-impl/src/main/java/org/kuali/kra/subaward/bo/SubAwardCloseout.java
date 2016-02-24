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
package org.kuali.kra.subaward.bo;

import java.sql.Date;


public class SubAwardCloseout extends SubAwardAssociate { 

    private static final long serialVersionUID = 1L;

    private Integer subAwardCloseoutId;
    private Long subAwardId; 
    private String subAwardCode;
    private Integer closeoutNumber;

    private Integer closeoutTypeCode;

    private Date dateRequested;

    private Date dateFollowup;

    private Date dateReceived;

    private String comments;


    public SubAwardCloseout() {
    }



    /**.
  * This is the Getter Method for subAwardCloseoutId
   * @return Returns the subAwardCloseoutId.
   */
	public Integer getSubAwardCloseoutId() {
		return subAwardCloseoutId;
	}



	/**.
	 * This is the Setter Method for subAwardCloseoutId
	 * @param subAwardCloseoutId The subAwardCloseoutId to set.
	 */
	public void setSubAwardCloseoutId(Integer subAwardCloseoutId) {
		this.subAwardCloseoutId = subAwardCloseoutId;
	}



	/**.
	 * This is the Getter Method for subAwardId
	 * @return Returns the subAwardId.
	 */
	public Long getSubAwardId() {
		return subAwardId;
	}


	/**.
	 * This is the Setter Method for subAwardId
	 * @param subAwardId The subAwardId to set.
	 */
	public void setSubAwardId(Long subAwardId) {
		this.subAwardId = subAwardId;
	}



	/**.
	 * This is the Getter Method for subAwardCode
	 * @return Returns the subAwardCode.
	 */
	public String getSubAwardCode() {
		return subAwardCode;
	}



	/**.
	 * This is the Setter Method for subAwardCode
	 * @param subAwardCode The subAwardCode to set.
	 */
	public void setSubAwardCode(String subAwardCode) {
		this.subAwardCode = subAwardCode;
	}



	/**.
	 * This is the Getter Method for closeoutNumber
	 * @return Returns the closeoutNumber.
	 */
	public Integer getCloseoutNumber() {
		return closeoutNumber;
	}



	/**.
	 * This is the Setter Method for closeoutNumber
	 * @param closeoutNumber The closeoutNumber to set.
	 */
	public void setCloseoutNumber(Integer closeoutNumber) {
		this.closeoutNumber = closeoutNumber;
	}



	/**.
	 * This is the Getter Method for closeoutTypeCode
	 * @return Returns the closeoutTypeCode.
	 */
	public Integer getCloseoutTypeCode() {
		return closeoutTypeCode;
	}



	/**.
	 * This is the Setter Method for closeoutTypeCode
	 * @param closeoutTypeCode The closeoutTypeCode to set.
	 */
	public void setCloseoutTypeCode(Integer closeoutTypeCode) {
		this.closeoutTypeCode = closeoutTypeCode;
	}



	/**.
	 * This is the Getter Method for dateRequested
	 * @return Returns the dateRequested.
	 */
	public Date getDateRequested() {
		return dateRequested;
	}



	/**.
	 * This is the Setter Method for dateRequested
	 * @param dateRequested The dateRequested to set.
	 */
	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}



	/**.
	 * This is the Getter Method for dateFollowup
	 * @return Returns the dateFollowup.
	 */
	public Date getDateFollowup() {
		return dateFollowup;
	}



	/**.
	 * This is the Setter Method for dateFollowup
	 * @param dateFollowup The dateFollowup to set.
	 */
	public void setDateFollowup(Date dateFollowup) {
		this.dateFollowup = dateFollowup;
	}



	/**.
	 * This is the Getter Method for dateReceived
	 * @return Returns the dateReceived.
	 */
	public Date getDateReceived() {
		return dateReceived;
	}



	/**.
	 * This is the Setter Method for dateReceived
	 * @param dateReceived The dateReceived to set.
	 */
	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}



	/**.
	 * This is the Getter Method for comments
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}



	/**.
	 * This is the Setter Method for comments
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}



	@Override
    public void resetPersistenceState() {

        this.subAwardCloseoutId=null;
    }
}
