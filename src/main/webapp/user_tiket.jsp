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

        </body>

    </html>