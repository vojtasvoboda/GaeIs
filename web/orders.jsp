<%@page import="java.util.Locale"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="persistence.Orderx"%>
<%@page import="persistence.OrderxDAO"%>
<%@page import="persistence.SupplierDAO"%>
<%@include file="header.jsp" %>

<%
    // vytahneme objednavky
    OrderxDAO sDAO = new OrderxDAO();
    List<Orderx> s = sDAO.getAll();
    // podivame se kolik jsme ziskali zaznamu
    int size = 0;
    if (!s.isEmpty()) { size = s.size(); }
    System.out.println("Orders.jsp: Pocet zaznamu je " + size);

    // definujeme DAO pro dodavatele
    SupplierDAO supdao = new SupplierDAO();
    Key k;

    // parametry GET
    String vlozeno = request.getParameter("vlozeno");
    if ( vlozeno == null ) vlozeno = "0";
    System.out.println("Orders.jsp: Parametr 'vlozeno' je " + vlozeno);
    String smazano = request.getParameter("smazano");
    if ( smazano == null ) smazano = "0";
    System.out.println("Orders.jsp: Parametr 'smazano' je " + smazano);
    String upraveno = request.getParameter("upraveno");
    if ( upraveno == null ) upraveno = "0";
    System.out.println("Orders.jsp: Parametr 'upraveno' je " + upraveno);

    // uprava formatu ceny
    response.setLocale(new Locale("cs-CZ"));
    DecimalFormat df = new DecimalFormat("#,###");
%>

<% if ( vlozeno.equals("1") ) { %>
<div class="boxok">Objednávka byla úspěšně vložena! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>
<% if ( smazano.equals("1") ) { %>
<div class="boxok">Objednávka byla úspěšně smazána! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>
<% if ( upraveno.equals("1") ) { %>
<div class="boxok">Objednávka byla úspěšně upravena! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>

<div id="box">
    <h3>Seznam obednávek</h3>
    <table width="100%">
        <thead>
            <tr>
            <th>Číslo</th>
            <th>Dodavatel</th>
            <th>Částka</th>
            <th>Datum objednáni</th>
            <th>Poznámka</th>
             <% if (userRole.compareTo("Host")!=0) { %>
                <th width="40px">Akce</th>
            <% } %>
            </tr>
        </thead>
        <tbody>
        <% if (size == 0) { %>
        <tr>
            <td colspan="6" align="center">Nejsou vložena žádná data.</td>
        </tr>
        <% } else { %>
        <% for ( Orderx item : s) { %>
        <tr>
            <td><%= item.getId() %></td>
            <td>
                <%= item.getId_supplier() %>
            </td>
            <td><%= df.format(item.getAmount()) %>,-&nbsp;Kč</td>
            <td><%= item.getDate_ordered() %></td>
            <td><%= item.getNote() %></td>
            <% if (userRole.compareTo("Host")!=0) { %>
            <td>
                <a href="/order_edit?id=<%= item.getKeyWeb() %>"><img src="img/icons/page_white_edit.png" title="Editovat" width="16" height="16" /></a>
                <a href="/order_delete_execute?id=<%= item.getKeyWeb() %>" onclick='return confirm("Opravdu chcete smazat objednávku č.<%= item.getId() %>?")'>
                    <img src="img/icons/user_delete.png" title="Smazat" width="16" height="16" />
                </a>
            </td>
            <% } %>
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
