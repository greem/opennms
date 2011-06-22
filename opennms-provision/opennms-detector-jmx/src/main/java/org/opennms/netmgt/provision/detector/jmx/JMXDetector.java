/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2008-2011 The OpenNMS Group, Inc.
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

package org.opennms.netmgt.provision.detector.jmx;

import org.opennms.netmgt.provision.detector.jmx.client.JMXClient;
import org.opennms.netmgt.provision.support.BasicDetector;
import org.opennms.netmgt.provision.support.ClientConversation.ResponseValidator;
import org.opennms.netmgt.provision.support.jmx.connectors.ConnectionWrapper;


/**
 * <p>Abstract JMXDetector class.</p>
 *
 * @author ranger
 * @version $Id: $
 */
public abstract class JMXDetector extends BasicDetector<ConnectionWrapper, Integer>{

    /**
     * <p>Constructor for JMXDetector.</p>
     *
     * @param serviceName a {@link java.lang.String} object.
     * @param port a int.
     */
    protected JMXDetector(String serviceName, int port) {
        super(serviceName, port);
    }
    
    /**
     * <p>Constructor for JMXDetector.</p>
     *
     * @param serviceName a {@link java.lang.String} object.
     * @param port a int.
     * @param timeout a int.
     * @param retries a int.
     */
    protected JMXDetector(String serviceName, int port, int timeout, int retries) {
        super(serviceName, port, timeout, retries);
    }

    
    /** {@inheritDoc} */
    @Override
    protected abstract JMXClient getClient();

    
    /** {@inheritDoc} */
    @Override
    protected abstract void onInit();
    
    /**
     * <p>expectBeanCount</p>
     *
     * @param bannerValidator a {@link org.opennms.netmgt.provision.support.ClientConversation.ResponseValidator} object.
     */
    protected void expectBeanCount(ResponseValidator<Integer> bannerValidator) {
        getConversation().expectBanner(bannerValidator);
    }
    
    /**
     * <p>greatThan</p>
     *
     * @param count a int.
     * @return a {@link org.opennms.netmgt.provision.support.ClientConversation.ResponseValidator} object.
     */
    protected ResponseValidator<Integer> greatThan(final int count){
        return new ResponseValidator<Integer>() {

            public boolean validate(Integer response) throws Exception {
                
                return (response >= count);
            }
            
        };
    }
	
}
