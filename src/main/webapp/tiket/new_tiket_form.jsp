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
                <h1> Tambah Tiket </h1>
                <a href="/home"> Home </a> <br>
                <a href="/showAllEventTikets?idEvent=${idEvent}"> Back </a> <br><br>
                <table border="1">
                    <thead>
                        <tr>
                            <th> ID User </th>
                            <th> Nama User </th>
                            <th> Checkbox </th>
                        </tr>
                    </thead>
                    <tbody>
                        <form method="POST" action="/addTiket2">
                            <input type="hidden" name="jumlahSaatIni" value=${jumlahSaatIni}>
                            <input type="hidden" name="idEvent" value=${idEvent}> 
                            <c:forEach var="u" items="${user}">
                                <tr>
                                    <td> ${u.getIdUser()} </td>
                                    <td> ${u.getFirstName()} ${u.getLastName()} </td>
                                    <td> <input type="checkbox" name="userSelect" value=${u.getIdUser()}> </td>
                                </tr>
                            </c:forEach>
                            <td colspan="3"> <input type="submit" value="Tambah Tiket"> </td>
                        </form>
                    </tbody>
                </table>

            </div>
        </body>

    </html>