
<div style="background:#626262;margin:0;padding:0;height:100%;">
<script type="text/javascript" language="JavaScript">
    window.onload = function () {
        var frmBody = document.getElementById('menu');
        var imgArrow = document.getElementById('img');
        var ia = document.getElementById('imga');
        var temp=0;
        function toggleMenuA() {
            if (temp == 0) {
                frmBody.style="display:none";
                temp = 1;
                ia.onclick="toggleMenuB()";
                imgArrow.src = "${pageContext.request.contextPath}/images/arrow_right.gif";
            }
        }
        function toggleMenuB(){
            if(temp == 1) {
                frmBody.style="display:inline";
                temp = 0;
                ia.onclick="toggleMenuA()";
                imgArrow.src = "${pageContext.request.contextPath}/images/arrow_left.gif";
            }
        }
    }
</script>
<table height="100%" cellspacing="0" cellpadding="0" id="tbl">
    <tbody>
        <tr>
            <td>
                <a href="javascript:;" onclick="toggleMenuA()">
                    <img style="margin-top:50%;" src="${pageContext.request.contextPath}/images/admin/arrow_left.gif" width="10" height="30" id="img" border="0"></a></td></tr>
    </tbody>
</table>
</div>