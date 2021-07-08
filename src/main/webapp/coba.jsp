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
            <c:if test='${a!=null}'>
                <script type="text/javascript">
                    alert('hallo bos ${a}');
                </script>
            </c:if>
        </body>

    </html>