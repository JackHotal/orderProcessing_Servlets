<%--
  Created by IntelliJ IDEA.
  User: aytugh
  Date: 3/24/2019
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="oplib.Order, java.util.* " %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" type ="text/css" href ="servlet.css" />

</head>
<body>
<%
    Order db = new Order("ism6236", "ism6236bo");
    List<String> pids = db.getProductIds();
%>
<form ACTION="Order" METHOD="POST">
    <fieldset id="info">
        <legend > Order: </legend>
        <label for="no"> Customer No: </label> <INPUT id= "no" type=text size=20 name="cid" value=""> <br>
        <label for="pq"> Quantity: </label> <INPUT id= "pq" type=text size=20 name="quantity" value=""> <br>

        <select id = "orders" name="orderlist" size = "6">
            <% for (String pid : pids)  {
                String pd = db.getProductDetail(pid);
                String line = String.format("<option>%s,%s </option>", pid,pd);
                out.println(line);
                }
            %>
        </select>
        <br>
        <INPUT TYPE="submit" VALUE="Purchase">
        <Input TYPE ="submit" formaction="index.jsp" value="Main Menu">
    </fieldset>
</form>
</body>
</html>
