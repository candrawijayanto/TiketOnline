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
            <div align="center">
                <h1> Tiket List </h1>
                <a href="/home"> Home </a> <br>
                <a href="/showAllEvents"> Tambah Tiket </a> <br>

                <table border="1">
                    <thead>
                        <tr>
                            <td> ID Tiket </td>
                            <td> Nama User </td>
                            <td> Nama Event </td>
                            <td> Absen </td>
                            <td> Operasi </td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="t" items="${tiket}">
                            <tr>
                                <td> ${t.getIdTiket()} </td>
                                <td> ${t.getUser().getFirstName()} ${t.getUser().getLastName()} </td>
                                <td> ${t.getEvent().getName()} </td>
                                <td> ${t.getAbsen().getAbsen()} </td>
                                <td>
                                    <a href="/deleteTiket?idTiket=${t.getIdTiket()}"
                                        onclick="return confirm('Yakin Hapus?');"> hapus | </a>
                                    <a href="/deleteAbsen?idAbsen=${t.getIdTiket()}"
                                        onclick="return confirm('Yahin Hapus?');"> hapus absen </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test='${pesan!=null}'>
                <script type="text/javascript">
                    alert('pesan: ${pesan}');
                </script>
            </c:if>
        </body>

    </html>