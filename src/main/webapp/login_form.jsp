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
            <div align='center'>
                <form action="/login" method="POST">
                    <input type="text" name="username" placeholder="UserName"> <br> <br>
                    <input type="password" name="password" placeholder="Password"> <br> <br>
                    <input type="submit" value="Login">
                </form>
            </div>
        </body>
        <c:if test='${pesan!=null}'>
            <script type="text/javascript">
                alert('pesan: ${pesan}');
            </script>
        </c:if>

    </html>