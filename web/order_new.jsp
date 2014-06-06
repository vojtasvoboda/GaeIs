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
%>

<div class="boxerror" style="display:none">Vyplňte všechny povinné položky a vyberte dodavatele! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Vložení nové objednávky</h3>
    <form id="form" action="/order_new_execute" method="post" accept-charset="utf-8">

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
        <input name="date" id="date" type="text" class="required" /> *
        <br>
        <label for="price">Částka:</label>
        <input name="price" id="price" type="text" class="required" />,- Kč
        <br>
        <label for="pozn">Poznámka:</label>
        <textarea rows="2" cols="50" name="pozn" id="pozn"></textarea>
        <br><br>
        <div align="center">
          <input id="button1" type="submit" value="Vložit objednávku" />
        </div>
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
