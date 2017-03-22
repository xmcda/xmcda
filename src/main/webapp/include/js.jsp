<script type="text/javascript" src="./template/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="./template/bootstrap/js/bootstrap.min.js"></script>

<script src="./template/codemirror/lib/codemirror.js"></script>
<script src="./template/codemirror/xml/xml.js"></script>   

<script type="text/javascript">
var reponse = CodeMirror.fromTextArea(document.getElementById("reponse"), {
        mode: "text/xml",
        tabMode: "indent",
        styleActiveLine: false,
        lineNumbers: true,
        lineWrapping: false
});

</script>