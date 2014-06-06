<%@page import="com.google.apphosting.api.DatastorePb.AddActionsRequest"%>
<%@page import="persistence.Admins"%>
<%@page import="persistence.AdminsDAO"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="persistence.Category"%>
<%@page import="persistence.CategoryDAO"%>
<%@page import="java.util.ListIterator"%>
<%@page import="persistence.ItemDAO"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<%
    // zjistime id kategorie pro upravu
    String id_admins = request.getParameter("id");
    if ( id_admins == null ) id_admins = "0";

       // vytahneme udaje polozky
    AdminsDAO dao = new AdminsDAO();
    Key k = KeyFactory.stringToKey(id_admins);
    Admins adm = dao.get(k);
%>

<div class="boxerror" style="display:none">Vyplňte uživatelské detaily! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Úprava uživatele</h3>
    <form id="form" action="/admin_edit_execute" method="post" accept-charset="utf-8">

        <label for="user_name">Přihlašovací email:</label>
        <input name="user_name" id="user_name" type="text" class="required" value="<%= adm.getUser_name() %>" /> *
        <br>
        <label for="real_name">Jméno:</label>
        <input name="real_name" id="real_name" type="text" class="required" value="<%= adm.getReal_name() %>" /> *
        <br>
        <label for="role">Role:</label>
        <select name="role" id="role">
            <option value="Admin">Admin</option>
            <option value="Fakturant">Fakturant</option>
            <option value="Skladnik">Skladnik</option>
        </select>
        <br>
        <div align="center">
          <input id="button1" type="submit" value="Upravit položku" />
        </div>
        <input name="id_admins" type="hidden" id="id_admins" value="<%= adm.getKeyWeb() %>" />
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
