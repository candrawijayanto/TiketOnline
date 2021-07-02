<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1> List Tiket: <span style="color: yellowgreen;"> ${tiket.get(0).getEvent().getName()} </span></h1>
                Jumlah Tiket Saat ini: <span style="color: red;"> ${jumlahSaatIni} dari  ${limitTiketEvent} </span>
                <br><br>
                <a href="/showNewTiketForm?idEvent=${idEvent}"> Tambah Tiket </a> <br>
                <a href="/home"> Home </a> <br>
                <a href="/showAllEvents"> Back </a> <br> <br>
                <table border="1">
                    <thead>
                        <tr>
                            <td> ID Tiket </td>
                            <td> Nama User </td>
                            <td> Operasi </td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${tiket == null}">
                                <h4> Kosong </h4>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="t" items="${tiket}">
                                    <tr>
                                        <td> ${t.getIdTiket()}</td>
                                        <td> ${t.getUser().getFirstName()} ${t.getUser().getLastName()} </td>
                                        <td> <a href="/deleteTiket?idTiket=${t.getIdTiket()}&idEvent=${idEvent}" onclick="return confirm('Are you sure want to Delete?');"> hapus </a>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </body>

    </html>