<%@page import="persistence.Orderx"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="persistence.OrderxDAO"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="persistence.Supplier"%>
<%@page import="persistence.SupplierDAO"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>

<%
    // ziskame vsechny vlozeny dodavatele
    SupplierDAO sDAO = new SupplierDAO();
    List<Supplier> s = sDAO.getAll();
    // podivame se kolik jsme ziskali zaznamu
    int size = 0;
    if (!s.isEmpty()) { size = s.size(); }
    System.out.println("Order_new.jsp: Pocet dodavatelu je " + size);

    // zjistime id kategorie pro upravu
    String id_order = request.getParameter("id");
    if ( id_order == null ) id_order = "0";
    System.out.println("Category_edit.jsp: ID upravované položky je " + id_order);

    // vytahneme udaje polozky
    OrderxDAO dao = new OrderxDAO();
    Key k = KeyFactory.stringToKey(id_order);
    Orderx ord = dao.get(k);
%>

<div class="boxerror" style="display:none">Vyplňte všechny povinné položky a vyberte dodavatele! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Úprava objednávky č.</h3>
    <form id="form" action="/order_edit_execute" method="post" accept-charset="utf-8">

        <label for="id_supplier">Dodavatel:</label>
        <% if (size == 0) { %>
            <p>V systému není vložen žadný dodavatel! <a href="/supplier_new" title="Vložit nového dodavatele">Vložit dodavatele</a></p>
            <input name="id_supplier" id="id_supplier" type="hidden" value="" class="required" />
        <% } else { %>
            <select name="id_supplier">
            <% for ( Supplier item : s) { %>
                <option value="<%= (item.getKey()).getId() %>"><%= item.getName() %></option>
            <% } %>
            </select>
        <% } %>

        <br>
        <label for="date">Datum objednávky:</label>
        <input name="date" id="date" type="text" class="required" value="<%= ord.getDate_ordered()%>" /> *
        <br>
        <label for="price">Částka:</label>
        <input name="price" id="price" type="text" class="required" value="<%= ord.getAmount()%>" />,- Kč
        <br>
        <label for="pozn">Poznámka:</label>
        <textarea rows="2" cols="50" name="pozn" id="pozn"><%= ord.getNote()%></textarea>
        <br><br>
        <div align="center">
          <input id="button1" type="submit" value="Upravit objednávku" />
        </div>
        <input id="id_order" name="id_order" type="hidden" value="<%= ord.getKeyWeb() %>" />
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
