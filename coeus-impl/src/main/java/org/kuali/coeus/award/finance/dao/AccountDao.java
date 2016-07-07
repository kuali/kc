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
package org.kuali.coeus.award.finance.dao;

import org.kuali.coeus.award.finance.AwardAccount;
import org.kuali.coeus.award.finance.AwardPosts;
import org.kuali.kra.award.home.Award;

import java.util.List;

public interface AccountDao {

    public List<AwardAccount> getAccounts(Integer startIndex, Integer size);

    public AwardAccount getAccount(String accountNumber);

    public AwardAccount saveAccount(AwardAccount account);

    public Award getAward(Long awardId);

    List<AwardPosts> getActiveAwardPosts(String accountNumber);

    AwardPosts getAwardPostsById(Long postId);

    List<AwardPosts> getAllAwardPostsInHierarchy(String accountNumber, String awardNumber);

    }
