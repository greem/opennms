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

package org.opennms.netmgt.provision.detector.snmp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
/**
 * <p>Win32ServiceDetector class.</p>
 *
 * @author ranger
 * @version $Id: $
 */
@Scope("prototype")
public class Win32ServiceDetector extends SnmpDetector {
    
    private static final String SV_SVC_OPERATING_STATE_OID = ".1.3.6.1.4.1.77.1.2.3.1.3";
    private static final String DEFAULT_SERVICE_NAME = "Service";
    
    /**
     * <p>Constructor for Win32ServiceDetector.</p>
     */
    public Win32ServiceDetector(){
        setServiceName(DEFAULT_SERVICE_NAME);
        setOid(generateOid());
        setVbvalue("1");
    }

    private String generateOid() {
        int snLength = getServiceName().length();
        
        StringBuffer serviceOidBuf = new StringBuffer(SV_SVC_OPERATING_STATE_OID);
        serviceOidBuf.append(".").append(Integer.toString(snLength));
        for (byte thisByte : getServiceName().getBytes()) {
            serviceOidBuf.append(".").append(Byte.toString(thisByte));
        }
        
        return serviceOidBuf.toString();
    }
}
