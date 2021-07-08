<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
    </head>

    <body>
        <div align="center">
            <a href="/showAllUsers"> Manajemen User </a> <br> <br>
            <a href="/showAllEvents"> Manajemen Event </a> <br> <br>
            <a href="/showAllTikets"> Manajemen Tiket </a> <br> <br>
            <a href="/logout"> Logout </a> <br> <br>
        </div>

        <c:if test='${pesan!=null}'>
            <script type="text/javascript">
                alert('pesan: ${pesan}');
            </script>
        </c:if>
    </body>

</html>