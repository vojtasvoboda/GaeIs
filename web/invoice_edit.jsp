<%@page import="persistence.InvoiceDAO"%>
<%@page import="persistence.Invoice"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
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

    // zjistime id kategorie pro upravu
    String id_invoice = request.getParameter("id");
    if ( id_invoice == null ) id_invoice = "0";


     // vytahneme udaje polozky
    InvoiceDAO InvDao = new InvoiceDAO();
    Key k = KeyFactory.stringToKey(id_invoice);
    Invoice inv = InvDao.get(k);
%>

<div class="boxerror" style="display:none">Vyberte objednávku pro přiřazení k faktuře! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Úprava faktury č.</h3>
    <form id="form" action="/invoice_edit_execute" method="post" accept-charset="utf-8">

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
        <input type="text" name="date" id="date" value="<%= inv.getDate_to_by_paid() %>" />
        <br>
        <div align="center">
            <input id="button1" type="submit" value="Upravit fakturu"/>
        </div>
        <input id="id_invoice" name="id_invoice" type="hidden" value="<%= inv.getKeyWeb() %>" />
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
