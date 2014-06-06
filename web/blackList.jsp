<%@page import="servlets.ReadXML"%>
<%@page import="java.beans.XMLEncoder"%>
<%@page import="java.io.FileOutputStream"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="persistence.BlackList"%>
<%@ page import="restws.BlackListRemote"%>

<%@include file="header.jsp" %>

<%
    // vytahneme blacklist
    BlackListRemote blr = new BlackListRemote();
    String bl = blr.getList();
    // List<BlackList> list = blr.getObjectList();
%>

<div id="box">
    <h3>Black List</h3>
    <table width="100%">
        <thead>
            <tr>
            <th>Id</th>
            <th>Jméno</th>
            <th>Příjmení</th>
            <th>Email</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <%= bl %>
    <div id="pager">
        Strana <a href="#"><img src="img/icons/arrow_left.gif" width="16" height="16" /></a>
        <input size="1" value="1" type="text" name="page" id="page" />
        <a href="#"><img src="img/icons/arrow_right.gif" width="16" height="16" /></a>z 1
    stran | Zobrazit <select name="view">
                                <option>10</option>
                    <option>20</option>
                    <option>50</option>
                    <option>100</option>
                        </select>
    položek na stránku | Celkem nalezeno <strong>0</strong> záznamů.
    </div>

</div><!-- ./box -->

<%@include file="footer.jsp" %>