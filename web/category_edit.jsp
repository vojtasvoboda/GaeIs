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
    String id_category = request.getParameter("id");
    if ( id_category == null ) id_category = "0";
    System.out.println("Category_edit.jsp: ID upravované položky je " + id_category);

    // vytahneme udaje polozky
    CategoryDAO dao = new CategoryDAO();
    Key k = KeyFactory.stringToKey(id_category);
    Category cat = dao.get(k);
%>

<div class="boxerror" style="display:none">Vyplňte název kategorie! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Úprava kategorie</h3>
    <form id="form" action="/category_edit_execute" method="post" accept-charset="utf-8">

        <label for="caption">Název kategorie:</label>
        <input name="caption" id="caption" type="text" class="required" value="<%= cat.getCaption() %>" /> *
        <br>
        <div align="center">
          <input id="button1" type="submit" value="Upravit položku" />
        </div>
        <input name="id_category" type="hidden" id="id_category" value="<%= cat.getKeyWeb() %>" />
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
