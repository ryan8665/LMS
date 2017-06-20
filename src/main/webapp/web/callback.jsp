<%@page import="com.dbHelper.Model"%>
<%@ page import="com.bps.sw.pgw.service.IPaymentGateway" %>
<%@ page import="com.bps.sw.pgw.service.PaymentGatewayImplService" %>
<%@ page import="java.util.StringTokenizer" %>

<html>
    <head>
        <title>Behpardakht PGW Test</title>
        <link href="Css/Style.css" rel="stylesheet" type="text/css"/>

        <script language="javascript" type="text/javascript">
            function postRefId(refIdValue) {
                var form = document.createElement("form");
                form.setAttribute("method", "POST");
                form.setAttribute("action", "https://bpm.shaparak.ir/pgwchannel/startpay.mellat");
                form.setAttribute("target", "_self");
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("name", "RefId");
                hiddenField.setAttribute("value", refIdValue);
                form.appendChild(hiddenField);
                document.body.appendChild(form);
                form.submit();
                document.body.removeChild(form);
            }


        </script>
    </head>

    <body>
        <form id="form1" runat="server" on>
            <table width="100%" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td>
                        <table class="MainTable" cellspacing="5" cellpadding="1" align="center">
                            <tr class="HeaderTr">
                                <td colspan="2" align="center" height="25">
                                    <span class="HeaderText">CallBack Params</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="LabelTd">
                                    <span>RefId</span>
                                </td>
                                <td>
                                    <span><%= (request.getParameter("RefId") != null) ? request.getParameter("RefId") : ""%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="LabelTd">
                                    <span>ResCode</span>
                                </td>
                                <td>
                                    <span><%= (request.getParameter("ResCode") != null) ? request.getParameter("ResCode") : ""%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="LabelTd">
                                    <span>SaleOrderId</span>
                                </td>
                                <td>
                                    <span><%= (request.getParameter("SaleOrderId") != null) ? request.getParameter("SaleOrderId") : ""%></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="LabelTd">
                                    <span>SaleReferenceId</span>
                                </td>
                                <td>
                                    <span><%= (request.getParameter("SaleReferenceId") != null) ? request.getParameter("SaleReferenceId") : ""%></span>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>


    <%
    
        IPaymentGateway service = new PaymentGatewayImplService().getPaymentGatewayImplPort();
        String responseMessage = "";
%>


<% ///////////////// VERIFY REQUEST
    if (request.getParameter("VerifyRequestButton") != null) {
        long terminalId = 2174497;
        String userName = "mand25";
        String userPassword = "37414569";
        long orderId = 1;
        long saleOrderId = 1;
        long saleReferenceId = 1;

        try {
            responseMessage = service.bpVerifyRequest(terminalId, userName, userPassword,
                    orderId, saleOrderId, saleReferenceId);
        } catch (Exception e) {
%>
<script language='javascript' type='text/javascript'>alert('<%= e.getStackTrace().toString()  %>');</script>
<%
    }
%>
<script language='javascript'
        type='text/javascript'>alert('Verify Response is : ' + '"<%= responseMessage  %>"');</script>
<%
    }
    ///////////////// VERIFY REQUEST
%>



<%


    
%>






</script>
</html>


