<%@page import="persistence.Orderx"%>
<%@page import="persistence.OrderxDAO"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<%
    // musime zjistit seznam objednavek
    OrderxDAO dao = new OrderxDAO();
    List<Orderx> objednavky = dao.getAll();
    // podivame se kolik jsme ziskali zaznamu
    int size = 0;
    if (!objednavky.isEmpty()) { size = objednavky.size(); }
    System.out.println("Item_new.jsp: Pocet kategorií je " + size);
%>

<div class="boxerror" style="display:none">Vyberte objednávku pro přiřazení k faktuře! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Vložení nové faktury</h3>
    <form id="form" action="/invoice_new_execute" method="post" accept-charset="utf-8">

        <label for="id_order">Objednávka:</label>
        <% if (size == 0) { %>
        <p>Není vložena žádná objednávka!</p>
        <input name="id_order" id="id_order" type="hidden" value="" class="required" />
        <% } else { %>
        <select name="id_order" id="id_order">
        <% for ( Orderx item : objednavky) { %>
            <option value="<%= item.getIdString() %>">č. <%= item.getIdString() %></option>
        <% }} %>
        </select>
        <br>
        <label for="date">Datum splatnosti:</label>
        <input type="text" name="date" id="date" />
        <br>
        <div align="center">
            <input id="button1" type="submit" value="Vytvořit fakturu"/>
        </div>

    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
