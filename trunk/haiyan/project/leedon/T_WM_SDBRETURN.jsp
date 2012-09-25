<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.haiyan.genmis.core.*"%>
<%@ page import="com.haiyan.genmis.core.exception.*"%>
<%@ page import="com.haiyan.genmis.core.db.*"%>
<%@ page import="com.haiyan.genmis.core.right.*"%>
<%@ page import="com.haiyan.genmis.core.struts.*"%>
<%@ page import="com.haiyan.genmis.core.paging.*"%>
<%@ page import="com.haiyan.genmis.castorgen.Table"%>
<%@ page import="com.haiyan.genmis.castorgen.types.*"%>
<%@ page import="com.haiyan.genmis.util.*"%>
<%@ page import="com.haiyan.genmis.view.render.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%
SrvContext srvContext = new SrvContext(request, response);
try {
	User user = srvContext.getUser();
	if (user==null) {
	    throw new Warning(srvContext.trans(100032, "session_overtime"));
	}
	String productID = srvContext.getParameter("__pid");
	DBManager dbm = srvContext.getDBM();
	String sql = "select max(BATCH) from T_WM_SDBINPRE where PRODUCTID="+PRODUCTID;
	String[][] rs = dbm.getResultStrArray(sql, 1, null);
	String maxBatch = rs[0][0];

	net.sf.json.JSONObject json = new net.sf.json.JSONObject();
	json.put("success",true);
	json.put("data",maxBatch);
} catch(Throwable ex) {
	DebugUtil.error(ex);
	out.clear();
    out.print(Warning.getClientErr(ex.getMessage()));
} finally {
	srvContext.close();
}
%>