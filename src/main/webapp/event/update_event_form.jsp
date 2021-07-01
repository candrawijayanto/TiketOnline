<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Tambah Event</title>
        </head>

        <body>
            <h1> Update Event </h1>
            <a href="/showAllEvents"> Back </a> <br> <br>

        
                    <form action="/addEvent" method="POST">
                        <input type="hidden" name="idEvent" value=${event.idEvent}>
                        <input type="text" name="name" placeholder="Nama Event" value=${event.name}>
                        <input type="date" name="date" placeholder="Date" value=${event.date}>
                        <input name="location" placeholder="Lokasi" value=${event.location}>
                        <input type="number" name="jml" placeholder="Limit" value=${event.jml}>
                        <input type="submit">
                    </form>
        </body>

    </html>