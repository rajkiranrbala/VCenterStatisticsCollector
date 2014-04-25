<%--
  User: vplociennik
  Date: 11/27/13
  Time: 3:26 AM
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>CMPE283</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">CMPE283</a>
        </div>
    </div><!-- /.container -->
</div><!-- /.navbar -->

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <div class="well sidebar-nav">
                <ul class="nav" id="inventory">

                    <li>Hosts</li>
                    <c:forEach items="${list}" var="h">
                        <li><a href="#" class="host" id="${h}">${h}</a> </li>
                    </c:forEach>

                    <li>Virtual Machines</li>
                    <c:forEach items="${list2}" var="vm">
                        <li><a href="#" class="vm" id="${vm}">${vm}</a> </li>
                    </c:forEach>

                    <li>Comparison</li>
                    <li><a href="#" class="host" id="allHost">Hosts</a></li>
                    <li><a href="#" class="vm" id="allVm">Virtual machines</a> </li>


                </ul>


            </div><!--/.well -->
        </div><!--/span-->


        <div class="col-xs-12 col-sm-9">

            <div class="btn-group" id="myBtn">
                <button type="button" class="btn btn-default" id="5min">5 minutes</button>
                <button type="button" class="btn btn-default" id="1h">1 hour</button>
                <button type="button" class="btn btn-default" id="24h">24 hours</button>
            </div>

            <div class="containerC" id="container1">
                <div class="jumbotron">
                    <h3>Choose an element in inventory</h3>
                </div>
            </div>

            <div class="containerC" id="container2"></div>

            <div class="containerC" id="container3"></div>

            <div class="containerC" id="container4"></div>

            <div class="containerC" id="container5"></div>

            <div class="containerC" id="container6"></div>

            <div class="containerC" id="container7"></div>

        </div>
        <!--/span-->


    </div><!--/row-->

    <hr>

    <footer>
        <p>&copy; SJSU - CMPE283 - 2013</p>
    </footer>

</div><!--/.container-->


<script src="${pageContext.request.contextPath}/js/jquery-2.0.3.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/gray.js"></script>
<script>

    $(function () {

        var focus;
        var type;

        $("#myBtn").hide();

        function getChartsVm(id, i){

            $("#myBtn").show();

            $.getJSON("/vm/cpu/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container1').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $.getJSON("/vm/memory/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container2').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $.getJSON("/vm/tx/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container3').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $.getJSON("/vm/rx/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container4').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $.getJSON("/vm/thread/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container5').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $.getJSON("/vm/ioread/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container6').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });


            $.getJSON("/vm/iowrite/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container7').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });
        }

        function getChartsHost(id, i){

            $("#myBtn").show();

            $.getJSON("/host/cpu/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container1').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $.getJSON("/host/memory/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container2').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $.getJSON("/host/tx/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container3').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $.getJSON("/host/rx/"+id+"/"+i)
                    .done(function( json ) {
                        $('#container4').highcharts(json);
                    })
                    .fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ", " + error;
                        console.log( "Request Failed: " + err );
                    });

            $("#container5").empty();

            $("#container6").empty();

            $("#container7").empty();

        }


        $("#inventory").on('click', '.vm', function() {

            focus = this.id;
            type = "vm";
            getChartsVm(focus, 1);

        });

        $("#inventory").on('click', '.host', function() {

            focus = this.id;
            type = "host";
            getChartsHost(focus, 1);

        });


        $("#5min").click(function() {
            if(type == "vm")
                getChartsVm(focus, 1);
            else if(type == "host")
                getChartsHost(focus, 1);
        });

        $("#1h").click(function() {
            if(type == "vm")
                getChartsVm(focus, 2);
            else if(type == "host")
                getChartsHost(focus, 2);
        });

        $("#24h").click(function() {
            if(type == "vm")
                getChartsVm(focus, 3);
            else if(type == "host")
                getChartsHost(focus, 3);
        });

    });

</script>


</body>
</html>