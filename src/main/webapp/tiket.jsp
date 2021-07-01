<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <!DOCTYPE html>
        <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
            </head>

            <body>
                <div align="center">
                    <h1> List Tiket: <span style="color: yellowgreen;"> ${tiket.get(0).getEvent().getName()} </span>
                    </h1>
                    Jumlah Tiket Saat ini: <span style="color: red;"> ${jumlahSaatIni} dari ${limitTiketEvent} 
                    </span> <br><br>
                    <a href="/home"> Home </a> <br>
                    <a href="/showAllEvents"> Back </a> <br> <br>
                    <form method="POST" action="/addTiket">
                        <input type="hidden" name="idEvent" value=${tiket.get(0).getEvent().getIdEvent()}>
                        <input type="number" name="jumlah" placeholder="jumlah">
                        <input type="submit" value="Tambah Tiket">
                    </form> <br> <br>
                    <table border="1">
                        <thead>
                            <tr>
                                <td> ID Tiket </td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="tiket" items="${tiket}">
                                <tr>
                                    <td> ${tiket.getIdTiket()} </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>


                    <table>
                        <c:set var="ageTotal" value="${0}" />
                        <c:forEach var="person" items="${personList}">
                            <c:set var="ageTotal" value="${ageTotal + person.age}" />
                            <tr>
                                <td>${person.name}
                                <td>
                                <td>${person.age}</td>
                            </tr>
                        </c:forEach>
                        ${ageTotal}
                    </table>
                </div>
            </body>

        </html>