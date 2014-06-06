<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="persistence.Item"%>
<%@page import="persistence.ItemDAO"%>
<%@page import="persistence.Orderx"%>
<%@page import="persistence.OrderxDAO"%>
<%@page import="persistence.Category"%>
<%@page import="java.util.ListIterator"%>
<%@page import="persistence.CategoryDAO"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<%
    // musime zjistit seznam kategorii z modelu aplikace
    CategoryDAO dao = new CategoryDAO();
    List<Category> kategorie = dao.getAll();
    // podivame se kolik jsme ziskali zaznamu
    int size = 0;
    if (!kategorie.isEmpty()) { size = kategorie.size(); }
    System.out.println("Item_edit.jsp: Pocet kategorií je " + size);

    // podivame se kolik jsme ziskali zaznamu
    int vel = 0;
    if (!kategorie.isEmpty()) { vel = kategorie.size(); }
    System.out.println("Item_edit.jsp: Pocet kategorií je " + vel);

    // a taky seznam faktur
    OrderxDAO dao2 = new OrderxDAO();
    List<Orderx> objednavky = dao2.getAll();

    // podivame se kolik jsme ziskali zaznamu
    int size2 = 0;
    if (!objednavky.isEmpty()) { size2 = objednavky.size(); }
    System.out.println("Item_edit.jsp: Pocet objednávek je " + size2);

     // zjistime id kategorie pro upravu
    String id_item = request.getParameter("id");
    if ( id_item == null ) id_item = "0";
    System.out.println("Item_edit.jsp: ID upravované položky je " + id_item);

    // vytahneme udaje polozky
    ItemDAO itemDao = new ItemDAO();
    Key k = KeyFactory.stringToKey(id_item);
    Item itm = itemDao.get(k);

%>

<div class="boxerror" style="display:none">Vyplňte všechny povinné položky a vyberte kategorii! <a href="#" onclick="$('.boxerror').fadeOut('500');return false;">Schovat</a></div>

<div id="box">

    <h3 id="adduser">Úprava položky</h3>
    <form id="form" action="/item_edit_execute" method="post" accept-charset="utf-8">

        <label for="category">Kategorie:</label>
        <% if (vel == 0) { %>
        <p>Není vložena žádná kategorie! <a href="/category_new">Vložit kategorii</a></p>
        <input type="hidden" value="" name="id_order" class="required" />
        <% } else { %>
        <select name="category" id="category">
        <% for ( Category item : kategorie) { %>
            <option value="<%= item.getIdString() %>"><%= item.getCaption() %></option>
        <% }} %>
        </select>
        <br>

        <label for="order">Objednávka:</label>
        <% if (size2 == 0) { %>
        <p>Není vložena žádná objednávka! <a href="/order_new">Vložit objednávku</a></p>
        <input type="hidden" name="id_order" value="" class="required" />
        <% } else { %>
        <select name="order" id="order">
        <% for ( Orderx item : objednavky) { %>
            <option value="<%= item.getIdString() %>">č. <%= item.getIdString() %></option>
        <% }} %>
        </select>
        <br>

        <label for="manufacturer">Výrobce:</label>
        <input name="manufacturer" id="manufacturer" type="text" class="required" value="<%= itm.getManufacturer() %>" /> *
        <br>
        <label for="caption">Název položky:</label>
        <input name="caption" id="caption" type="text" class="required" value="<%= itm.getCaption() %>" /> *
        <br>
        <label for="pn">Produktové číslo (PN):</label>
        <input name="pn" id="pn" type="text" class="required" value="<%= itm.getPn() %>" /> *
        <br>
        <label for="sn">Sériové číslo (SN):</label>
        <input name="sn" id="sn" type="text" class="required" value="<%= itm.getSn()%>" /> *
        <br>
        <label for="price">Cena:</label>
        <input name="price" id="price" type="text" class="required" value="<%= itm.getPrice() %>" />,- Kč *
        <br>
        <label for="pozn">Poznámka:</label>
        <textarea rows="2" cols="50" name="pozn" id="pozn"><%= itm.getNote() %></textarea>
        <br><br>
        <div align="center">
          <input id="button1" type="submit" value="Upravit položku" />
        </div>
        <input id="id_item" name="id_item" type="hidden" value="<%= itm.getKeyWeb() %>" />
    </form>

</div><!-- ./box -->

<%@include file="footer.jsp" %>
