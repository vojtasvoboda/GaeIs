<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="persistence.Supplier"%>
<%@page import="persistence.SupplierDAO"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<%
    // zjistime id kategorie pro upravu
    String id_supplier = request.getParameter("id");
    if ( id_supplier == null ) id_supplier = "0";
    System.out.println("Supplier_edit.jsp: ID upravované položky je " + id_supplier);

    // vytahneme udaje polozky
    SupplierDAO dao = new SupplierDAO();
    Key k = KeyFactory.stringToKey(id_supplier);
    Supplier supp = dao.get(k);
%>

<div class="boxerror" style="display:none">Vyplňte všechny povinné položky! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Úprava dodavatele</h3>
    <form id="form" action="/supplier_edit_execute" method="post" accept-charset="utf-8">
        <label for="Name">Název firmy:</label>
        <input type="text" name="Name" id="Name" class="required" value="<%= supp.getName() %>" /> *
        <br>
        <label for="ICO">IČO:</label>
        <input type="text" name="ICO" id="ICO" class="required" value="<%= supp.getIco() %>" /> *
        <br>
        <label for="DIC">DIČ:</label>
        <input type="text" name="DIC" id="DIC" class="required" value="<%= supp.getDic() %>" /> *
        <br>
        <label for="Address">Adresa:</label>
        <input type="text" name="Address" id="Address" class="required" value="<%= supp.getAddress() %>" /> *
        <br>
        <label for="Contact_person">Kontatní osoba:</label>
        <input type="text" name="Contact_person" id="Contact_person" value="<%= supp.getContact() %>" />
        <br>
        <div align="center">
            <input id="button1" type="submit" value="Upravit dodavatele"/>
        </div>
        <input id="id_supplier" name="id_supplier" type="hidden" value="<%= supp.getKeyWeb() %>" />
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
