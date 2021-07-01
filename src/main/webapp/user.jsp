<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Manajemen User</title>
        </head>

        <body>
            <div align="center">
                <h1> User List</h1>
                <a href="/home"> Home </a>
                    <table border="1">
                        <thead>
                            <tr>
                                <td> ID User </td>
                                <td> First Name </td>
                                <td> Last Name </td>
                                <td> Email </td>
                                <td> coba </td>
                                <td> operasi </td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${user}">
                                <tr>
                                    <td> ${user.getIdUser()} </td>
                                    <td> ${user.getFirstName()} </td>
                                    <td> ${user.getLastName()} </td>
                                    <td> ${user.getEmail()} </td>
                                    <td> ${user.getTiket().get(1).getIdTiket()} </td>
                                    <td>
                                        <a href="/showAllUserTiket?idUser=${user.getIdUser()}"> tiket </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </div>
        </body>

    </html>