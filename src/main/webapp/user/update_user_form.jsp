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
            <h1> Update User </h1>
            <a href="/showAllUsers"> Back </a> <br> <br>

            <form action="/updateUser" method="POST">
                <input type="hidden" name="idUser" value=${user.getIdUser()}>
                <input type="text" placeholder="First Name" name="firstName" value="${user.firstName}">
                <input type="text" placeholder="Last Name" name="lastName" value="${user.lastName}">
                <input type="email" placeholder="Email"  name="email" value="${user.email}">
                <input type="submit" value="Update User">
            </form>

        </body>

    </html>