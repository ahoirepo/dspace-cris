<%--
  - _searchGrant.jsp
  - 
  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="it.cilea.hku.authority.model.ResearcherGrant"%>
<div id="content">

<h1><fmt:message key="jsp.layout.hku.search.grant.title"/></h1>

<form:form commandName="dto" method="post" action="${contextPath}/rp/searchGrants.htm">
<table width="98%" align="left" cellpadding="0" cellspacing="0">
	<tr>
		<td><!-- Search box -->
		
		<div id="search">
		<fieldset><legend><fmt:message key="jsp.layout.hku.search.grant.box.label"/></legend>

			<table>
			
		
		</fieldset>
		</div>
		</td>
	</tr>
	<td class="bodyText">

	<!-- Enter code -->

	
		<tr>
			<c:if test="${see_search_grantcode}">
			<td valign="middle">
			<div id="search">
			<div id="searchmiddle">
	
			<fieldset><fmt:message key="jsp.layout.hku.search.byGrantCode"/>&nbsp;<form:input path="codeQuery" /> 
			</fieldset>
			</div>
			</div>	
			</td>
			</c:if>	


	<tr>
		<td></td>
	</tr>
		</c:if>

				<c:choose>
					<c:when test="${!empty result}">
						<display:table name="${result}" cellspacing="0" cellpadding="0"
							requestURI="" id="objectList" htmlId="objectList"  class="displaytaglikemisctable" export="false">
							<display:column titleKey="jsp.layout.table.hku.grants.projecttitle"><a href="../rp/grants/details.htm?id=<%= ((ResearcherGrantDTO)pageContext.getAttribute("objectList")).getId()%>"><%= ((ResearcherGrantDTO)pageContext.getAttribute("objectList")).getTitle()%></a> </display:column>						
							<display:column titleKey="jsp.layout.table.hku.grants.investigator" property="investigators"/>
							<display:column titleKey="jsp.layout.table.hku.grants.year" property="year"/>
							<%
							if(isAdmin) {							    
							%>
							<display:column titleKey="jsp.layout.table.hku.grants.status" property="status"/>							
							<%		    
							}    
							%>							
							
						</display:table>
					</c:when>
					<c:otherwise>
						<p class="submitFormWarn"><fmt:message
							key="jsp.search.general.noresults" /></p>
					</c:otherwise>
				</c:choose>
</c:if>
</table>
