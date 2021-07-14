<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Dashboard</title>

            <!-- Latest compiled and minified CSS -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
                integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
                crossorigin="anonymous">

            <!-- Optional theme -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
                integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
                crossorigin="anonymous">
            <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

            <link rel="stylesheet" href="style.css">
        </head>


        <body>
            <div class="wrapper">
                <nav class="navbar navbar-default">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/home">Dashboard</a>
                    </div>
                </nav>

                <aside class="sidebar">
                    <menu>
                        <ul class="menu-content">
                            <li><a href="/home"><i class="fa fa-home"></i> Home</a></li>
                            <li>
                            <li><a href="/showAllUsers"> Manajemen User </a></li>
                            <li><a href="/showAllUsers"> Manajemen Event </a></li>
                            <li><a href="/showAllTikets"> Manajemen Tiket </a></li>
                            <li><a href="/logout"> Logout </a> </li>
                            </li>
                    </menu>
                </aside>

                <section class="content">
                    <div class="inner">
                        <p>
                            PREVIEW
                        </p>
                    </div>
                </section>
            </div>
            <c:if test='${pesan!=null}'>
                <script type="text/javascript">
                    alert('pesan: ${pesan}');
                </script>
            </c:if>
        </body>

    </html>