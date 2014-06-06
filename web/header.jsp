<%@page import="persistence.AdminsDAO"%>
<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Inventární systém IT</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/theme.css">
    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />
    <!--[if IE]>
    <link rel="stylesheet" type="text/css" href="/css/ie-sucks.css" />
    <![endif]-->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/js/script.js"></script>
</head>
<body>
    <%
        // aktualni uzivatel
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        // aktualni stranka
        String p = request.getRequestURI();
        p = p.substring(1);
        //zjistime roli uzivatele
        //deklaruji userRole na hodnotu host, aby ji bylo mozne pouzit dale v kodu
        String userRole = "Host";
        if (user != null){
            AdminsDAO userCheck = new AdminsDAO();
            userRole = userCheck.getRole(user.toString());
            //System.out.println("UserRole:" + userRole);
        }
    %>
    <div id="container">
    	<div id="header">
            <h1>Inventární systém IT</h1>
            
            <% if (user != null) { %>
            <div id="topmenu">
            <ul>
                <li<% if (p.startsWith("index")) { out.print(" class=\"current\""); } %>><a href="/">Úvod</a></li>
                <li<% if (p.startsWith("order")) { out.print(" class=\"current\""); } %>><a href="/orders">Objednávky</a></li>
                <li<% if (p.startsWith("invoice")) { out.print(" class=\"current\""); } %>><a href="/invoices">Faktury</a></li>
                <li<% if (p.startsWith("item")) { out.print(" class=\"current\""); } %>><a href="/items">Katalog vybavení</a></li>
                <!-- kategorie muze prohlizet skladnik nebo admin -->
                <% 
                if (userRole.compareTo("Skladnik")==0 || userRole.compareTo("Admin")==0) { %>
                    <li<% if (p.startsWith("categor")) { out.print(" class=\"current\""); } %>><a href="/categories">Kategorie vybavení</a></li>
                <!-- krome hosta mohou dodavatele prohlizet vsichni... -->
                <% }
                if (userRole.compareTo("Host")!=0) { %>
                    <li<% if (p.startsWith("supplier")) { out.print(" class=\"current\""); } %>><a href="/suppliers">Dodavatelé</a></li>
                <% } %>
                <% 
                if (userRole.compareTo("Admin")==0) { %>
                    <li<% if (p.startsWith("admins")) { out.print(" class=\"current\""); } %>><a href="/admins">Uživatelé</a></li>
                <% } %>
                <li<% if (p.startsWith("black")) { out.print(" class=\"current\""); } %>><a href="/blacklist">Black list</a></li>
            </ul>
            </div><!-- ./topmenu -->
            <% } %>

            <div id="login">
            <%
                if (user != null) {
                    out.println("<p>" + user.getNickname()
                            + " | <a href=\""
                            + userService.createLogoutURL("/")
                            + "\">Odhlásit se</a></p>");
                } else {
                    out.println("<p><a href=\""
                            + userService.createLoginURL("/")
                            + "\">Přihlásit se</a></p>");
                }
            %>
            </div>
        </div><!-- ./header -->

        <% if (user != null) { %>
        <div id="top-panel">
            <div id="panel">
                <ul>
                    <% if (p.startsWith("order")) { %>
                    <li><a href="/orders" class="cart">Seznam objednávek</a></li>
                        <% if (userRole.compareTo("Host")!=0) { %>
                            <li><a href="/order_new" class="cart_add">Vložit novou objednávku</a></li>
                        <% } %>
                    <% } else if (p.startsWith("invoice")) { %>
                    <li><a href="/invoices" class="promotions">Seznam faktur</a></li>
                        <% if (userRole.compareTo("Admin")==0 || userRole.compareTo("Fakturant")==0) { %>
                            <li><a href="/invoice_new" class="add">Přidat fakturu</a></li>
                        <% } %>
                    <% } else if (p.startsWith("item")) { %>
                    <li><a href="/items" class="modules">Seznam položek</a></li>
                        <% if (userRole.compareTo("Host")!=0) { %>
                            <li><a href="/item_new" class="add">Přidat novou položku</a></li>
                        <% } %>
                    <% } else if (p.startsWith("categor")) { %>
                    <li><a href="/categories" class="folder_table">Seznam kategorií</a></li>
                    <li><a href="/category_new" class="add">Přidat novou kategorii</a></li>
                    <% } else if (p.startsWith("supplier")) { %>
                    <li><a href="/suppliers" class="group">Seznam dodavatelů</a></li>
                    <li><a href="/supplier_new" class="useradd">Přidat nového dodavatele</a></li>
                    <% } else if (p.startsWith("admins")) { %>
                    <li><a href="/admins" class="group">Seznam uživatelů</a></li>
                    <li><a href="/admins_new" class="useradd">Přidat nového uživatele</a></li>
                    <% } else { %>
                    <li><a href="/" class="report">Zadání projektu</a></li>
                    <% } %>
                </ul>
            </div>
        </div><!-- ./top-panel -->
        <% } %>
        <div id="wrapper">
        <div id="content">
