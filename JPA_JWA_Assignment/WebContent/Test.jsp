<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" import="JPA.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> titles here:please enter</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<%
SiteDao dao= new SiteDao();
List<Site> sites= dao.findAllSites();
%>
<h1>Sites</h1>

<table class="table table-striped">
<%
 for(Site site: sites)
 {
%> <tr>	 
<td> <%= site.getName()%> </td>
</tr>
 <%
  }
%>
</table>
</div>

</body>
</html>