<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="persistence.Invoice"%>
<%@ page import="persistence.InvoiceDAO"%>

<%@include file="header.jsp" %>

<%
    // vytahneme faktury
    InvoiceDAO sDAO = new InvoiceDAO();
    List<Invoice> s = sDAO.getAll();
    // podivame se kolik jsme ziskali zaznamu
    int size = 0;
    if (!s.isEmpty()) { size = s.size(); }
    System.out.println("Invoices.jsp: Pocet zaznamu je " + size);

    // parametry GET
    String vlozeno = request.getParameter("vlozeno");
    if ( vlozeno == null ) vlozeno = "0";
    System.out.println("Invoices.jsp: Parametr 'vlozeno' je " + vlozeno);
    String smazano = request.getParameter("smazano");
    if ( smazano == null ) smazano = "0";
    System.out.println("Invoices.jsp: Parametr 'smazano' je " + smazano);
    String upraveno = request.getParameter("upraveno");
    if ( upraveno == null ) upraveno = "0";
    System.out.println("Invoices.jsp: Parametr 'upraveno' je " + upraveno);

    //zjistime roli uzivatele
        //deklaruji userRole na hodnotu host, aby ji bylo mozne pouzit dale v kodu
       
 %>

<% if ( vlozeno.equals("1") ) { %>
<div class="boxok">Faktura byla úspěšně vložena! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>
<% if ( smazano.equals("1") ) { %>
<div class="boxok">Faktura byla úspěšně smazána! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>
<% if ( upraveno.equals("1") ) { %>
<div class="boxok">Faktura byla úspěšně upravena! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>

<div id="box">
    <h3>Seznam faktur</h3>
    <table width="100%">
        <thead>
            <tr>
            <th>Číslo</th>
            <th>Datum splatnosti</th>
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
        <% for ( Invoice item : s) { %>
        <tr>
            <td><%= item.getId_order() %></td>
            <td><%= item.getDate_to_by_paid() %></td>
            <% if (userRole.compareTo("Host")!=0) { %>
                <td>
                    <a href="/invoice_edit?id=<%= item.getKeyWeb() %>"><img src="img/icons/user_edit.png" title="Editovat fakturu" width="16" height="16" /></a>
                    <a href="/invoice_delete_execute?id=<%= item.getKeyWeb() %>" onclick='return confirm("Opravdu chcete smazat fakturu č.<%= item.getId_order() %>?")'>
                        <img src="img/icons/user_delete.png" title="Smazat fakturu" width="16" height="16" />
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