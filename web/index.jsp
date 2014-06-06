<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<%-- pokud je prihlasen --%>
<% if (user == null) { %>
<div id="rightnow">
    <h3 class="reallynow">
    <p>Vítejte v systému, prosím přihlašte se!</p>
    </h3>
    <p class="youhave">Pro práci v inventárním systému je nutno se nejdříve přihlásit.<br> To učiníte kliknutím na odkaz nahoře v pravém rohu.</p>
</div>
<% } else { %>

<h3>Kontakty</h3>
<p>Vojtěch Svoboda - svobovo3&#64;fit.cvut.cz<br>
   Michal Včelička - vcelimic&#64;fit.cvut.cz
</p>

<br>

<h3>Přihlášení jako administrátor</h3>
<p>Po přihlášení do aplikace je každý uživatel jako host. Pokud si chcete nastavit práva administrátora, musíte přejít na tento odkaz a přidat se do seznamu: 
<a href="/admins">Přidat uživatele do seznamu administrátorů</a>
</p>

<br>

<h3>Zadání systému</h3>
<p>Proveďte návrh, analýzu a implementaci informačního systému umožňující inventarizaci technického IT vybavení.
    Systém bude umožňovat spravovat jednotlivé položky, přidávat další a editovat je.
    Přes tento systém bude možno vytvářet objednávky, na které se budou moci přidávat položky evidované v tomto systému.
    V systému bude možno označit objednávku jako dodanou. Také bude možno generovat faktury z objednávek,
    které se budou taktéž párovat s objednávkami. Systém bude možno ovládat přes webové grafické rozhraní a
    implementován bude v programovacím jazyku Java nad platformou Google Web App Engine. Systém bude poskytovat webovou službu,
    přes kterou bude možnost vkládat nové objednávky, získávat vygenerované faktury pro konkrétní objednávky
    a dále možnost dotazovat se na stav naskladnění konkrétních položek. Výsledný systém otestujte.
    Při tvorbě se držte zásad softwarového inženýrství. </p>

<br>

<h3>Poskytovaná webová služba</h3>
<p>Právě v přípravě.</p>

<% } %>

<%@include file="footer.jsp" %>
