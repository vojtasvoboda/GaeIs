<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="persistence.Category"%>
<%@ page import="persistence.CategoryDAO"%>

<%@include file="header.jsp" %>

<%
    // vytahneme polozky
    CategoryDAO sDAO = new CategoryDAO();
    List<Category> s = sDAO.getAll();
    // podivame se kolik jsme ziskali zaznamu
    int size = 0;
    if (!s.isEmpty()) { size = s.size(); }
    System.out.println("Categories.jsp: Pocet zaznamu je " + size);

    // parametry GET
    String vlozeno = request.getParameter("vlozeno");
    if ( vlozeno == null ) vlozeno = "0";
    System.out.println("Categories.jsp: Parametr 'vlozeno' je " + vlozeno);
    String smazano = request.getParameter("smazano");
    if ( smazano == null ) smazano = "0";
    System.out.println("Categories.jsp: Parametr 'smazano' je " + smazano);
    String upraveno = request.getParameter("upraveno");
    if ( upraveno == null ) upraveno = "0";
    System.out.println("Categories.jsp: Parametr 'upraveno' je " + upraveno);
%>

<% if ( vlozeno.equals("1") ) { %>
<div class="boxok">Kategorie byla úspěšně vložena! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>
<% if ( smazano.equals("1") ) { %>
<div class="boxok">Kategorie byla úspěšně smazána! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>
<% if ( upraveno.equals("1") ) { %>
<div class="boxok">Kategorie byla úspěšně upravena! <a href="#" onclick="$('.boxok').fadeOut('500');return false;">Schovat</a></div>
<% } %>

<div id="box">
    <h3>Seznam kategorií</h3>
    <table width="100%">
        <thead>
            <tr>
            <th>Číslo</th>
            <th>Popisek</th>
            <th width="40px">Akce</th>
            </tr>
        </thead>
        <tbody>
        <% if (size == 0) { %>
        <tr>
            <td colspan="7" align="center">Nejsou vložena žádná data.</td>
        </tr>
        <% } else { %>
        <% for ( Category item : s) { %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getCaption() %></td>
            <td>
                <a href="/category_edit?id=<%= item.getKeyWeb() %>"><img src="img/icons/user_edit.png" title="Editovat fakturu" width="16" height="16" /></a>
                <a href="/category_delete_execute?id=<%= item.getKeyWeb() %>" onclick='return confirm("Opravdu chcete smazat kategorii <%= item.getCaption() %>?")'>
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