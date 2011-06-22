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

package org.opennms.netmgt.dao.castor;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opennms.netmgt.config.ackd.AckdConfiguration;
import org.opennms.netmgt.config.ackd.Parameter;
import org.opennms.netmgt.config.ackd.Reader;
import org.opennms.netmgt.config.ackd.ReaderSchedule;
import org.opennms.netmgt.dao.AckdConfigurationDao;
import org.springframework.dao.DataAccessResourceFailureException;

/**
 * Default implementation of <code>AckdConfiguration</code> containing utility methods for manipulating
 * the <code>Ackd</code> and <code>AckdReader</code>s.
 *
 * @author <a href="mailto:david@opennms.org">David Hustace</a>
 * @version $Id: $
 */
public class DefaultAckdConfigurationDao extends AbstractCastorConfigDao<AckdConfiguration, AckdConfiguration> implements AckdConfigurationDao {

    /**
     * <p>Constructor for DefaultAckdConfigurationDao.</p>
     */
    public DefaultAckdConfigurationDao() {
        super(AckdConfiguration.class, "Ackd Configuration");
    }
    
    /**
     * <p>getConfig</p>
     *
     * @return a {@link org.opennms.netmgt.config.ackd.AckdConfiguration} object.
     */
    public AckdConfiguration getConfig() {
        return getContainer().getObject();
    }

    /** {@inheritDoc} */
    @Override
    public AckdConfiguration translateConfig(AckdConfiguration castorConfig) {
        return castorConfig;
    }

    /** {@inheritDoc} */
    public Boolean acknowledgmentMatch(List<String> messageText) {
        String expression = getConfig().getAckExpression();
        return matcher(messageText, expression);
    }

    /** {@inheritDoc} */
    public Boolean clearMatch(List<String> messageText) {
        String expression = getConfig().getClearExpression();
        return matcher(messageText, expression);
    }

    /** {@inheritDoc} */
    public Boolean escalationMatch(List<String> messageText) {
        String expression = getConfig().getEscalateExpression();
        return matcher(messageText, expression);
    }

    /** {@inheritDoc} */
    public Boolean unAcknowledgmentMatch(List<String> messageText) {
        String expression = getConfig().getUnackExpression();
        return matcher(messageText, expression);
    }

    private Boolean matcher(List<String> messageText, String expression) {
        Boolean matches = Boolean.FALSE;
        Pattern p;
        
        if (expression.startsWith("~")) {
            expression = (expression.startsWith("~") ? expression.substring(1) : expression); 
            p = Pattern.compile(expression);

            for (String text : messageText) {
                Matcher m = p.matcher(text);
                matches = m.matches();
                if (matches) {
                    break;
                }
            }
        } else {
            for (String text : messageText) {
                matches = expression.equalsIgnoreCase(text);
            }
        }
        return matches;
    }

    /** {@inheritDoc} */
    public Reader getReader(String readerName) {
        Reader readerByName = null;
        List<Reader> readers = getConfig().getReaders().getReaderCollection();
        for (Reader reader : readers) {
            if (readerName.equals(reader.getReaderName())) {
                readerByName = reader;
            }
        }
        return readerByName;
    }
    
    /** {@inheritDoc} */
    public ReaderSchedule getReaderSchedule(String readerName) {
        ReaderSchedule schedule = null;
        Reader reader = getReader(readerName);
        if (reader != null) {
            schedule = reader.getReaderSchedule();
        }
        return schedule;
    }
    
    /** {@inheritDoc} */
    public boolean isReaderEnabled(String readerName) {
        boolean enabled = false;
        Reader reader = getReader(readerName);
        if (reader != null) {
            enabled = reader.isEnabled();
        }
        return enabled;
    }

    /**
     * The exception boils up from the container class  The container class should
     * indicate this.
     *
     * @throws org.springframework.dao.DataAccessResourceFailureException if any.
     */
    public void reloadConfiguration() throws DataAccessResourceFailureException {
        getContainer().reload();
    }

    /**
     * <p>getEnabledReaderCount</p>
     *
     * @return a int.
     */
    public int getEnabledReaderCount() {
        int cnt = 0;
        Iterator<Reader> it = getConfig().getReaders().getReaderCollection().iterator();

        while (it.hasNext()) {
            Reader reader = (Reader) it.next();
            if (reader.isEnabled()) {
                cnt++;
            }
        }
        return cnt;
    }

    /** {@inheritDoc} */
    public List<Parameter> getParametersForReader(String name) {
        return getReader(name).getParameterCollection();
    }
    
}
