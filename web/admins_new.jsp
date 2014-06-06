<%@page import="java.util.ListIterator"%>
<%@page import="persistence.ItemDAO"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<div class="boxerror" style="display:none">Vyplňte údaje o uživateli! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Vložení nového uživatele</h3>
    <form id="form" action="/admin_new_execute" method="post" accept-charset="utf-8">

        <label for="user_name">Přihlašovací email:</label>
        <input name="user_name" id="user_name" type="text" class="required" /> *
        <br>
        <label for="real_name">Jméno:</label>
        <input name="real_name" id="real_name" type="text" class="required" /> *
        <br>
        <label for="role">Role:</label>
        <select name="role" id="role">
            <option value="Admin">Admin</option>
            <option value="Fakturant">Fakturant</option>
            <option value="Skladnik">Skladnik</option>
        </select>
        <br>
        <div align="center">
          <input id="button1" type="submit" value="Vložit uživatele" />
        </div>
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
