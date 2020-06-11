<%-- 
    Document   : kalkulator
    Created on : Jun 10, 2020, 11:15:31 AM
    Author     : rafli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Servlet Calculator</title>"
<link rel="stylesheet" href="style.css">            
</head>
<body>
    <div class="container">
        <center><h1 style="color: white;">Calculator</h3></center>
        <br>
        <form class="calculator" name="kalkulator">
                        <!--<span style="color:white;">${operation}</span>-->
            <input class="hasil" value=" ${history}" name="value" type="text" readonly=""/>
            <input name="reset" class="num clear " value="C" type="submit">
            <input name="div" class="num " value="/" type="submit">
            <input name="mul" class="num " value="*" type="submit">
            <input name="tujuh" class="num" value="7" type="submit">
            <input name="delapan" class="num " value="8" type="submit">
            <input name="sembilan" class="num " value="9" type="submit">
            <input name="sub" class="num " value="-" type="submit">
            <input name="empat" class="num " value="4" type="submit">
            <input name="lima" class="num " value="5" type="submit">
            <input name="enam" class="num " value="6" type="submit">
            <input name="add" class="num plus " value="+" type="submit">
            <input name="tiga" class="num " value="3" type="submit">
            <input name="dua" class="num " value="2" type="submit">
            <input name="satu" class="num " value="1" type="submit">
            <input name="nol" class="num " value="0" type="submit">
            <input name="ratus" class="num " value="00" type="submit">
            <input name="koma" class="num " value="." type="submit">
            <input name="equ" class="num equal " value="=" type="submit">
        </form>
            <center>
            <br>
            <h3 style="color: white">History</h3>
            <div class="box">
                
                <p >${history}</p>
                <h2>${results}</h2>
            </div>
            </center>
    </div>
</body>
</html>
