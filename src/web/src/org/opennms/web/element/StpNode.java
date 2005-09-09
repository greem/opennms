//
// Copyright (C) 2002 Sortova Consulting Group, Inc.  All rights reserved.
// Parts Copyright (C) 1999-2001 Oculan Corp.  All rights reserved.
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
//
// For more information contact:
//      OpenNMS Licensing       <license@opennms.org>
//      http://www.opennms.org/
//      http://www.sortova.com/
//

package org.opennms.web.element;


public class StpNode
{
        int     m_nodeId;
        int     m_basenumports;
		int     m_basetype;
		int     m_stpprotocolspecification;
		int     m_stppriority;
		int     m_stprootcost;
		int     m_stprootport;
		int     m_basevlan;
		String  m_basevlanname;
        String  m_basebridgeaddress;
		String  m_stpdesignatedroot;
        String  m_lastPollTime;
        char    m_status;
        int 	m_stprootnodeid;
        /* package-protected so only the NetworkElementFactory can instantiate */
        StpNode()
        {
        }

        /* package-protected so only the NetworkElementFactory can instantiate */
        StpNode(        int     nodeId,
	int     basenumports,
	int     basetype,
	int     stpprotocolspecification,
	int     stppriority,
	int     stprootcost,
	int     stprootport,
	int     basevlan,
	String  basebridgeaddress,
	String  stpdesignatedroot,
	String  lastPollTime,
	char    status,
	int		stprootnodeid
)
        {
                m_nodeId = nodeId;
                m_basenumports = basenumports;
				m_basetype = basetype;
				m_stpprotocolspecification = stpprotocolspecification;
				m_stppriority = stppriority;
				m_stprootcost = stprootcost;
				m_stprootcost = stprootport;
				m_basevlan = basevlan;
                m_basebridgeaddress = basebridgeaddress;
                m_stpdesignatedroot = stpdesignatedroot;
                m_lastPollTime = lastPollTime; 
                m_status = status;
                m_stprootnodeid = stprootnodeid;
        }

        public String toString()
        {
                StringBuffer str = new StringBuffer("Node Id = " + m_nodeId + "\n" );
                str.append("Bridge number of ports = " + m_basenumports + "\n" );
                str.append("At Last Poll Time = " + m_lastPollTime + "\n" );
                str.append("Node At Status= " + m_status + "\n" );
                return str.toString();
        }

		/**
		 * @return
		 */
		public String get_basebridgeaddress() {
			return m_basebridgeaddress;
		}

		/**
		 * @return
		 */
		public int get_basenumports() {
			return m_basenumports;
		}

		/**
		 * @return
		 */
		public int get_basetype() {
			return m_basetype;
		}

		/**
		 * @return
		 */
		public int get_basevlan() {
			return m_basevlan;
		}

		/**
		 * @return
		 */
		public String get_lastPollTime() {
			return m_lastPollTime;
		}

		/**
		 * @return
		 */
		public int get_nodeId() {
			return m_nodeId;
		}

		/**
		 * @return
		 */
		public char get_status() {
			return m_status;
		}

		/**
		 * @return
		 */
		public String get_stpdesignatedroot() {
			return m_stpdesignatedroot;
		}

		/**
		 * @return
		 */
		public int get_stppriority() {
			return m_stppriority;
		}

		/**
		 * @return
		 */
		public int get_stpprotocolspecification() {
			return m_stpprotocolspecification;
		}

		/**
		 * @return
		 */
		public int get_stprootcost() {
			return m_stprootcost;
		}

		/**
		 * @return
		 */
		public int get_stprootport() {
			return m_stprootport;
		}

        /**
         * @return Returns the m_stprootnodeid.
         */
        public int get_stprootnodeid() {
            return m_stprootnodeid;
        }
		/**
		 * @return Returns the m_basevlanname.
		 */
		public String getBaseVlanName() {
			return m_basevlanname;
		}

}
