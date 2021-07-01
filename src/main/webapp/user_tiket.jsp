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
                <h1> List tiket untuk user: <span style="color: yellowgreen;"> ${userTiket.get(0).getUser().get(0).getFirstName()} ${userTiket.get(0).getUser().get(0).getLastName()} </span> </h1>
                <a href="/home"> Home </a> <br>
                <a href="/showAllUsers"> Back </a>
                <table border="1">
                    <thead>
                        <tr>
                            <td> ID Tiket </td>
                            <td> Event Name </td>
                            <td> Event Date </td>
                            <td> Event Location </td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="userTiket" items="${userTiket}">
                            <tr>
                                <td> ${userTiket.getIdTiket()} </td>
                                <td> ${userTiket.getEvent().getName()} </td>
                                <td> ${userTiket.getEvent().getDate()} </td>
                                <td> ${userTiket.getEvent().getLocation()} </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </body>

    </html>