<%@page import="java.util.ListIterator"%>
<%@page import="persistence.ItemDAO"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<div class="boxerror" style="display:none">Vyplňte název kategorie! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Vložení nové kategorie</h3>
    <form id="form" action="/category_new_execute" method="post" accept-charset="utf-8">

        <label for="caption">Název kategorie:</label>
        <input name="caption" id="caption" type="text" class="required" /> *
        <br>
        <div align="center">
          <input id="button1" type="submit" value="Vložit položku" />
        </div>
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
