<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Index</title>
</head>
<body>
    <div class="container">
    <%! int betId = 0; %>
    <br><br>
        <form:form action="/play" method="get" modelAttribute="ruleta">
            <form:select class="form-control required" path="betTypes" onchange="setItem(value)">
                <c:forEach var="i" begin="${0}" end="${longType - 1}" step="${1}">
                    <option value="${i}">${betType[i]}</option>
                </c:forEach>
            </form:select>
            <span id="optionSelected"></span>
            <br>
            <c:if test="${parar}">
                <input class="btn btn-dark disabled" type="submit" value="Probar" />
                <a class="btn btn-danger" href="/reset" >Restart</a>
            </c:if>
            <c:if test="${!parar}">
                <input class="btn btn-success" type="submit" value="Probar" />
                <a class="btn btn-danger" href="/reset" >Restart</a>
            </c:if>
            <div>Dinero restante: ${dinero}</div>
        </form:form>

        <c:if test="${isWinner}">
            <div class="alert alert-success" role="alert">
                <h1>Ganaste enhorabuena!!! + ${betMoney} , el numero ganador era ${winnerNumber}</h1>
            </div>
        </c:if>
        <c:if test="${isLoser}">
            <div class="alert alert-danger" role="alert">
                <h1>Perdiste!!! - ${betMoney} , el numero ganador era ${winnerNumber}</h1>
            </div>
        </c:if>
        <c:if test="${message}">
            <div class="alert alert-danger" role="alert">
                <h1>PERDISTE EL JUEGO !!!</h1>
            </div>
        </c:if>
    </div>
    </body>
    <script>
        function setItem(value) {
            if (value == 0) {
                document.getElementById("optionSelected").innerHTML = "<br/><select class='form-control' name='betOption' id='betOption'/><option value='1-18'>1-18</option><option value='19-36'>19-36</option><select/><input class='form-control' name='money' type='number' placeholder='Introduce cantidad a apostar' min='0' max='${dinero}'/>";
            }else if (value == 1) {
                document.getElementById("optionSelected").innerHTML = "<br/><input name='betOption' class='form-control' type='number' placeholder='Introduce numero al que apuestas' min='0' max='36'/><input class='form-control' name='money' type='number' placeholder='Introduce cantidad a apostar' min='0' max='${dinero}'/>";
            }else if (value == 2){
                document.getElementById("optionSelected").innerHTML = "<br/><select class='form-control' name='betOption' id='betOption'/><option value='rojo'>rojo</option><option value='negro'>negro</option><option value='verde'>verde</option><select/><input class='form-control' name='money' type='number' placeholder='Introduce cantidad a apostar' min='0' max='${dinero}'/>";
            }else if (value == 3) {
                document.getElementById("optionSelected").innerHTML = "<br/><select class='form-control' name='betOption' id='betOption'/><option value='par'>par</option><option value='impar'>impar</option><select/><input class='form-control' name='money' type='number' placeholder='Introduce cantidad a apostar' min='0' max='${dinero}'/>";
            }
        }
    </script>
</html>