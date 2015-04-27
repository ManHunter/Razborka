<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/jquery-ui/pepper-grinder/jquery-ui-1.8.16.custom.css"/>'/>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/style.css"/>'/>
    <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type='text/javascript' src='<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/resources/js/util.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/resources/js/jquery-fileupload/vendor/jquery.ui.widget.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/resources/js/jquery-fileupload/jquery.iframe-transport.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/resources/js/jquery-fileupload/jquery.fileupload.js"/>'></script>
    <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>

    <title>Upload</title>

</head>

<body>
<h1>Spring MVC - jQuery File Upload</h1>
<div style="width:500px;padding:20px">

    <input id="fileupload" type="file" name="files[]" data-url="controller/upload" multiple>

    <div id="dropzone">Drop files here</div>

    <div id="progress">
        <div style="width: 0%;"></div>
    </div>

    <table id="uploaded-files">
        <tr>
            <th>File Name</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Download</th>
        </tr>
    </table>

</div>
</body>
</html>