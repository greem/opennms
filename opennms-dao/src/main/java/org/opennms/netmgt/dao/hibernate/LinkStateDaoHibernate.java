/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2009-2011 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2011 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.opennms.netmgt.dao.LinkStateDao;
import org.opennms.netmgt.model.OnmsLinkState;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * <p>LinkStateDaoHibernate class.</p>
 *
 * @author ranger
 * @version $Id: $
 */
public class LinkStateDaoHibernate extends AbstractDaoHibernate<OnmsLinkState, Integer> implements LinkStateDao {
    /**
     * <p>Constructor for LinkStateDaoHibernate.</p>
     */
    public LinkStateDaoHibernate() {
        super(OnmsLinkState.class);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public Collection<OnmsLinkState> findAll(final Integer offset, final Integer limit) {
        return getHibernateTemplate().execute(new HibernateCallback<Collection<OnmsLinkState>>() {

            public Collection<OnmsLinkState> doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createCriteria(OnmsLinkState.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
            }
        });
    }

    /** {@inheritDoc} */
    public OnmsLinkState findById(Integer id) {
        return findUnique("from OnmsLinkState as ls where ls.id = ?", id);
    }

    /** {@inheritDoc} */
    public OnmsLinkState findByDataLinkInterfaceId(final Integer interfaceId) {
        return findUnique("from OnmsLinkState as ls where ls.dataLinkInterface.id = ?", interfaceId);
    }

    /** {@inheritDoc} */
    public Collection<OnmsLinkState> findByNodeId(Integer nodeId) {
        return find("from OnmsLinkState as ls where ls.dataLinkInterface.nodeId = ?", nodeId);
    }

    /** {@inheritDoc} */
    public Collection<OnmsLinkState> findByNodeParentId(Integer nodeParentId) {
        return find("from OnmsLinkState as ls where ls.dataLinkInterface.nodeParentId = ?", nodeParentId);
    }

}
