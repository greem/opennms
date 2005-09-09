//
// This file is part of the OpenNMS(R) Application.
//
// OpenNMS(R) is Copyright (C) 2002-2003 The OpenNMS Group, Inc.  All rights reserved.
// OpenNMS(R) is a derivative work, containing both original code, included code and modified
// code that was published under the GNU General Public License. Copyrights for modified 
// and included code are below.
//
// OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
//
// Copyright (C) 1999-2001 Oculan Corp.  All rights reserved.
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
//      http://www.opennms.com/
//
// Tab Size = 8
//

package org.opennms.netmgt.linkd.snmp;

import org.apache.log4j.Category;
import org.opennms.core.utils.ThreadCategory;
import org.opennms.netmgt.linkd.snmp.NamedSnmpVar;
import org.opennms.protocols.snmp.SnmpObjectId;
import org.opennms.protocols.snmp.SnmpPduBulk;
import org.opennms.protocols.snmp.SnmpPduPacket;
import org.opennms.protocols.snmp.SnmpPduRequest;
import org.opennms.protocols.snmp.SnmpSMI;
import org.opennms.protocols.snmp.SnmpVarBind;

/**
 *<P>The Dot1dTpFdbTableEntry class is designed to hold all the MIB-II
 * information for one entry in the MIB II dot1dBridge.dot1dTp.dot1dTpFdbTable. 
 * The table effectively contains a list of these entries, each entry having information
 * about bridge forwarding table.</P>
 *
 * <P>This object is used by the Dot1dTpFdbTable to hold infomation
 * single entries in the table. See the Dot1dTpFdbTable documentation
 * form more information.</P>
 *
 * @author <A HREF="mailto:rssntn67@yahoo.it">Antonio</A>
 * @author <A HREF="mailto:sowmya@opennms.org">Sowmya</A>
 * @author <A HREF="mailto:weave@oculan.com">Weave</A>
 * @author <A>Jon Whetzel</A>
 * @author <A HREF="http://www.opennms.org/">OpenNMS</A>
 *
 *
 * @see Dot1dTpFdbTable  
 * @see <A HREF="http://www.ietf.org/rfc/rfc1213.txt">RFC1213</A>
 */
public final class Dot1dTpFdbTableEntry extends java.util.TreeMap {
	// Lookup strings for specific table entries
	//

	public final static String FDB_ADDRESS = "dot1dTpFdbAddress";

	public final static String FDB_PORT = "dot1dTpFdbPort";

	public final static String FDB_STATUS = "dot1dTpFdbStatus";


	/**
	 * <P>The keys that will be supported by default from the 
	 * TreeMap base class. Each of the elements in the list
	 * are an instance of the IpRoutetable. Objects
	 * in this list should be used by multiple instances of
	 * this class.</P>
	 */
	private static NamedSnmpVar[] ms_elemList = null;

	/**
	 * <P>Initialize the element list for the class. This
	 * is class wide data, but will be used by each instance.</P>
	 */
	static {
		ms_elemList = new NamedSnmpVar[3];
		int ndx = 0;

		/**
		 * A unicast MAC address for which the bridge has
		 * forwarding and/or filtering information.
		 */
		ms_elemList[ndx++] = new NamedSnmpVar(NamedSnmpVar.SNMPOCTETSTRING,
				FDB_ADDRESS, ".1.3.6.1.2.1.17.4.3.1.1", 1);
		
		/**
		 * Either the value '0', or the port number of the
 		 * port on which a frame having a source address
 		 * equal to the value of the corresponding instance
 		 * of dot1dTpFdbAddress has been seen. A value of
 		 * '0' indicates that the port number has not been
 		 * learned but that the bridge does have some
 		 * forwarding/filtering information about this
 		 * address (e.g. in the dot1dStaticTable).
 		 * Implementors are encouraged to assign the port
 		 * value to this object whenever it is learned even
 		 * for addresses for which the corresponding value of
 		 * dot1dTpFdbStatus is not learned(3).
		 */
		ms_elemList[ndx++] = new NamedSnmpVar(NamedSnmpVar.SNMPINT32,
				FDB_PORT, ".1.3.6.1.2.1.17.4.3.1.2", 2);
		
		/**
		 * The status of this entry. The meanings of the
 		 * values are:
 		 * other(1) : none of the following. This would
 		 * include the case where some other
 		 * MIB object (not the corresponding
 		 * instance of dot1dTpFdbPort, nor an
 		 * entry in the dot1dStaticTable) is
 		 * being used to determine if and how
 		 * frames addressed to the value of
 		 * the corresponding instance of
 		 * dot1dTpFdbAddress are being
 		 * forwarded.
 		 * invalid(2) : this entry is not longer valid
 		 * (e.g., it was learned but has since
 		 * aged-out), but has not yet been
 		 * flushed from the table.
 		 * learned(3) : the value of the corresponding
 		 * instance of dot1dTpFdbPort was
 		 * learned, and is being used.
 		 * self(4) : the value of the corresponding
 		 * instance of dot1dTpFdbAddress
 		 * represents one of the bridge's
 		 * addresses. The corresponding
 		 * instance of dot1dTpFdbPort
 		 * indicates which of the bridge's
 		 * ports has this address.
		 * mgmt(5) : the value of the corresponding
 		 * instance of dot1dTpFdbAddress is
 		 * also the value of an existing
 		 * instance of dot1dStaticAddress.
		 */
		ms_elemList[ndx++] = new NamedSnmpVar(NamedSnmpVar.SNMPINT32,
				FDB_STATUS, ".1.3.6.1.2.1.17.4.3.1.3", 3);
	}

	/**
	 * <P>The TABLE_OID is the object identifier that represents
	 * the root of the Bridge Forward table in the MIB forest.</P>
	 */
	public static final String TABLE_OID = ".1.3.6.1.2.1.17.4.3.1"; // start of table (GETNEXT)

	/**
	 * <P>The SnmpObjectId that represents the root of the 
	 * ipNetToMediaTable tree. It is created when the class is 
	 * initialized and contains the value of TABLE_OID.
	 *
	 * @see #TABLE_OID
	 */
	public static final SnmpObjectId ROOT = new SnmpObjectId(TABLE_OID);

	/**
	 * <P>Creates a default instance of the stp port
	 * table entry map. The map represents a singular
	 * instance of the spannig tree port table. Each column in
	 * the table for the loaded instance may be retreived
	 * either through its name or object identifier.</P>
	 *
	 * <P>The initial table is constructied with zero
	 * elements in the map.</P>
	 */
	public Dot1dTpFdbTableEntry() {
		super();
	}

	/**
	 * <P>The class constructor used to initialize the
	 * object to its initial state. Although the
	 * object's member variables can change after an
	 * instance is created, this constructor will
	 * initialize all the variables as per their named
	 * variable from the passed array of SNMP varbinds.</P>
	 *
	 * <P>If the information in the object should not be
	 * modified then a <EM>final</EM> modifier can be 
	 * applied to the created object.</P>
	 *
	 * @param vars	The array of variable bindings.
	 */
	public Dot1dTpFdbTableEntry(SnmpVarBind[] vars) {
		this();
		update(vars);
	}

	/**
	 * <P>This method is used to update the map
	 * with the current information from the agent.
	 * The array of variables should be all the
	 * elements in the address row.</P>
	 *
	 * </P>This does not clear out any column in the
	 * actual row that does not have a definition.</P>
	 *
	 * @param vars	The variables in the interface row.
	 *
	 */
	public void update(SnmpVarBind[] vars) {
		Category log = ThreadCategory.getInstance(getClass());

		//
		// iterate through the variable bindings
		// and set the members appropiately.
		//
		// Note: the creation of the snmp object id
		// is in the outer loop to limit the times a
		// new object is created.
		//
		for (int x = 0; x < ms_elemList.length; x++) {
			SnmpObjectId id = new SnmpObjectId(ms_elemList[x].getOid());

			for (int y = 0; y < vars.length; y++) {
				if (id.isRootOf(vars[y].getName())) {
					try {
						//
						// Retrieve the class object of the expected SNMP data type for this element
						//
						Class classObj = ms_elemList[x].getTypeClass();

						//
						// If the SnmpSyntax object matches the expected class 
						// then store it in the map. Else, store a null pointer
						// in the map.
						//
						if (classObj == null
								|| classObj.isInstance(vars[y].getValue())) {
							if (log.isDebugEnabled()) {
								log.debug("update: Types match!  SNMP Alias: "
										+ ms_elemList[x].getAlias()
										+ "  Vars[y]: " + vars[y].toString());
							}
							put(ms_elemList[x].getAlias(), vars[y].getValue());
							put(ms_elemList[x].getOid(), vars[y].getValue());
						} else {
							if (log.isDebugEnabled()) {
								log.debug("update: variable '"
										+ vars[y].toString()
										+ "' does NOT match expected type '"
										+ ms_elemList[x].getType() + "'");
							}
							put(ms_elemList[x].getAlias(), null);
							put(ms_elemList[x].getOid(), null);
						}
					} catch (ClassNotFoundException e) {
						log.error(
								"Failed to retreive SNMP type class for element: "
										+ ms_elemList[x].getAlias(), e);
					} catch (NullPointerException e) {
						log.error("Invalid reference", e);
					}
				}
			}
		}
	}

	/**
	 * <P>If the SNMP version is V1, this method is used to get a 
	 * generic SNMP GETNEXT PDU that contains one varbind per member 
	 * element.</P>
	 *
	 * <P>If the SNMP version is V2, this method is used to get an
	 * SNMP GETBULK PDU with a single varbind containing the TABLE_OID
	 * object identifier.</P>
	 *
	 * <P>The PDU can then be used to perform an <EM>SNMP walk</EM> of 
	 * the MIB-II IP Address table of a remote host.</P>
	 * 
	 * @param version	SnmpSMI.SNMPV1 or SnmpSMI.SNMPV2
	 * 
	 * @return An SnmpPduPacket object with a command of GETNEXT (for SNMPv1)
	 * or GETBULK (for SNMPv2).
	 *
	 */
	public static SnmpPduPacket getNextPdu(int version) {
		SnmpPduPacket pdu = null;

		if (version == SnmpSMI.SNMPV2) {
			pdu = new SnmpPduBulk();
			((SnmpPduBulk) pdu).setMaxRepititions(10);
			pdu.setRequestId(SnmpPduPacket.nextSequence());
			SnmpObjectId oid = new SnmpObjectId(TABLE_OID);
			pdu.addVarBind(new SnmpVarBind(oid));
		} else {
			pdu = new SnmpPduRequest(SnmpPduPacket.GETNEXT);
			pdu.setRequestId(SnmpPduPacket.nextSequence());
			for (int x = 0; x < ms_elemList.length; x++) {
				SnmpObjectId oid = new SnmpObjectId(ms_elemList[x].getOid());
				pdu.addVarBind(new SnmpVarBind(oid));
			}
		}

		return pdu;
	}

	/**
	 *<P>This method will determine where the cut off point will be for
	 * valid data from the response to the GETBULK packet.  By using the
	 * size of the element list, listed above, we can determine the 
	 * proper index for this task.<P>
	 */
	public static SnmpObjectId stop_oid() {
		Integer endindex = new Integer(ms_elemList.length + 1);
		String endoid = new String(TABLE_OID + "." + endindex.toString());
		SnmpObjectId oid = new SnmpObjectId(endoid);

		return oid;
	}

	/** 
	 * <P>Returns the number of entries in the MIB-II dot1dBridge.dot1dTp.dot1dTpFdbTable element list.</P>
	 */
	public static int getElementListSize() {
		return ms_elemList.length;
	}

}