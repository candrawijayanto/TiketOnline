<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Manajemen Event</title>
        </head>

        <body>
            <div align="center">
                <h1>Event List</h1>
                <a href="/home"> Home </a> <br>
                <a href="/showNewEventForm"> Tambah Event </a> <br>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Id Event</th>
                            <th>Event Name</th>
                            <th>Date</th>
                            <th>Location</th>
                            <th>Limit</th>
                            <th>Operasi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="e" items="${event}">
                            <tr>
                                <td> ${e.idEvent}</td>
                                <td> ${e.name} </td>
                                <td> ${e.date} </td>
                                <td> ${e.location} </td>
                                <td> ${e.jml} </td>
                                <td>
                                    <a href="/deleteEvent?id=${e.idEvent}" onclick="return confirm('Are you sure want to Delete?');"> hapus | </a>
                                    <a href="/showUpdateEventForm?id=${e.idEvent}"> edit | </a>
                                    <a href="/showAllEventTikets?idEvent=${e.idEvent}"> tiket </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

    </html>