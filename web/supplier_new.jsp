<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<div class="boxerror" style="display:none">Vyplňte všechny povinné položky! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Vložení nového dodavatele</h3>
    <form id="form" action="/supplier_new_execute" method="post" accept-charset="utf-8">
        <label for="Name">Název firmy:</label>
        <input type="text" name="Name" id="Name" class="required" /> *
        <br>
        <label for="ICO">IČO:</label>
        <input type="text" name="ICO" id="ICO" class="required" /> *
        <br>
        <label for="DIC">DIČ:</label>
        <input type="text" name="DIC" id="DIC" class="required" /> *
        <br>
        <label for="Address">Adresa:</label>
        <input type="text" name="Address" id="Address" class="required" /> *
        <br>
        <label for="Contact_person">Kontatní osoba:</label>
        <input type="text" name="Contact_person" id="Contact_person" />
        <br>
        <div align="center">
            <input id="button1" type="submit" value="Vytvořit dodavatele"/>
        </div>
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
