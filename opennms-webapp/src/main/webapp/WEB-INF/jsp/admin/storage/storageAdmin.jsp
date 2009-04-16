<%--

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
//      http://www.opennms.com///

--%>

<%@page language="java"
	contentType="text/html"
	session="true"%>
<%@page language="java" contentType="text/html" session="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/includes/header.jsp" flush="false" >
  <jsp:param name="title" value="Rancid" />
  <jsp:param name="headTitle" value="${model.id}" />
  <jsp:param name="headTitle" value="Admin Rancid" />
  <jsp:param name="breadcrumb" value="<a href='admin/index.jsp'>Admin</a>" />
  <jsp:param name="breadcrumb" value="Admin Rancid" />
</jsp:include>

<div class="TwoColLeft">
    <!-- general info box -->
    <h3>General (Status: ${model.status_general})</h3>
    <table class="o-box">
		<tr>
			<th width="50%">Node</th>
	  		<td><a href="element/node.jsp?node=${model.db_id}">${model.id}</a></td>
	  	</tr>
		<tr>
	  		<th>Foreign Source</th>
	  		<td>${model.foreignSource}</td>
	  	</tr>
	</table>

	<h3>Software Images Stored</h3>
	
	<table class="o-box">
	<tr>
		<th>Name</th>
		<th>Size</th>
		<th>Last Modified</th>
	</tr>
	
	<c:forEach items="${model.bucketitems}" var="swimgelem">
		<tr>
			<td>${swimgelem.name}
<a href="${model.url}/storage/buckets/${model.id}?filename=${swimgelem.name}">(download)</a>
<a href="admin/storage/storageDeleteBucketItem.htm?node=${model.db_id}&bucket=${model.id}&filename=${swimgelem.name}">(delete)</a>
			</td>
			<td>${swimgelem.size}</td>
			<td>${swimgelem.lastModified}</td>
		</tr>
	</c:forEach>
	
	<tr>
		<th></th>
		<th>
	<form id="addBucketForm" method="post" name="addBucketForm">	
		<input name="newStatus" id="doAdd" type="submit" value="Upload" onClick="addBucket()">
		<INPUT TYPE="hidden" NAME="bucket" VALUE="${model.id}"> 
	</form>
		</th>
		<th>
	<form id="deleteBucketForm" method="post" name="deleteBucketForm">	
		<input name="newStatus" id="doDelete" type="submit" value="Delete" onClick="deleteBucket()">
		<INPUT TYPE="hidden" NAME="bucket" VALUE="${model.id}">
 	</form>
 		</th>
	</tr>
	</table>
</div>

  <div class="TwoColRight">
      <h3>Descriptions</h3>
      <div class="boxWrapper">
      <p>Detailed Documentation on all options can be found on <a title="The OpenNMS Project wiki" href="http://www.opennms.org" target="new">the OpenNMS wiki</a>.
      </p>
        <p><b>(Delete) Bucket Item</b>: delete the specified image file from <em>bucket</em></p>
      
       <p><b>Upload Bucket</b>:  add a file to <b>bucket</b>. 
        </p>
        
       <p><b>Delete Bucket</b>:  delete <b>bucket</b> with all image files. 
        </p>
                

 
      </div>
  </div>
  <hr />

<jsp:include page="/includes/footer.jsp" flush="false" />

<script language="JavaScript">

function addBucket() {
	alert("not yet implemented");
	  //document.addBucketForm.action="RoccoAction()";
	  //document.addBucketForm.submit();	
}

function deleteBucket() {
	  if(confirm('Do you really want to delete all image files?')==true) {
		  document.deleteBucketForm.action="/admin/storage/storageDeleteBucket.htm?node=${model.db_id}";
		  document.deleteBucketForm.submit();	
  	  }
}

</script>

