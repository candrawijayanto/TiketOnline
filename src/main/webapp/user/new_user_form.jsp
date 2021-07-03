<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>

    <body>
        <h1> Tambah User </h1>
        <a href="/showAllUsers"> Back </a> <br> <br>

        <form action="/addUser" method="POST">
            <input type="text" placeholder="First Name" name="firstName">
            <input type="text" placeholder="Last Name" name="lastName">
            <input type="email" placeholder="Email" name="email">
            <input type="submit" value="Tambah user">
        </form>
    </body>

</html>