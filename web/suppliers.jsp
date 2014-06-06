<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="persistence.Supplier"%>
<%@ page import="persistence.SupplierDAO"%>

<%@include file="header.jsp" %>

<%
    // vytahneme dodavatele
    SupplierDAO sDAO = new SupplierDAO();
    List<Supplier> s = sDAO.getAll();
    // podivame se kolik jsme ziskali zaznamu
    int size = 0;
    if (!s.isEmpty()) { size = s.size(); }
    System.out.println("Suppliers.jsp: Pocet zaznamu je " + size);

    // parametry GET
    String vlozeno = request.getParameter("vlozeno");
    if ( vlozeno == null ) vlozeno = "0";
    String smazano = request.getParameter("smazano");
    if ( smazano == null ) smazano = "0";
    String upraveno = request.getParameter("upraveno");
    if ( upraveno == null ) upraveno = "0";
%>

<% if ( vlozeno.equals("1") ) { %>
<div class="boxok">Dodavatel byl úspěšně vložen! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>
<% if ( smazano.equals("1") ) { %>
<div class="boxok">Dodavatel byl úspěšně smazán! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>
<% if ( upraveno.equals("1") ) { %>
<div class="boxok">Dodavatel byl úspěšně upraven! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>

<div id="box">
    <h3>Seznam dodavatelů</h3>
    <table width="100%">
        <thead>
            <tr>
            <th>Jméno</th>
            <th>IČO</th>
            <th>DIČ</th>
            <th>Adresa</th>
            <th>Kontaktni osoba</th>
            <th width="40px">Akce</th>
            </tr>
        </thead>
        <tbody>
        <% if (size == 0) { %>
        <tr>
            <td colspan="6" align="center">Nejsou vložena žádná data.</td>
        </tr>
        <% } else { %>
        <% for ( Supplier item : s) { %>
        <tr>
            <td><%= item.getName() %></td>
            <td><%= item.getIco() %></td>
            <td><%= item.getDic() %></td>
            <td><%= item.getAddress() %></td>
            <td><%= item.getContact() %></td>
            <td>
                <a href="/supplier_edit?id=<%= item.getKeyWeb() %>"><img src="img/icons/user_edit.png" alt="edit" title="Edit user" width="16" height="16" /></a>
                <a href="/supplier_delete_execute?id=<%= item.getKeyWeb() %>" onclick='return confirm("Opravdu chcete smazat dodavatele <%= item.getName() %>?")'>
                    <img src="img/icons/user_delete.png" title="Smazat" width="16" height="16" />
                </a>
            </td>
         </tr>
        <% }} %>
        </tbody>
    </table>
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
    položek na stránku | Celkem nalezeno <strong><%= size %></strong> záznamů.
    </div>

</div><!-- ./box -->

<%@include file="footer.jsp" %>