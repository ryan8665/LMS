<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="com.dbHelper.Model"%>
<%@ page import="com.bps.sw.pgw.service.IPaymentGateway" %>
<%@ page import="com.bps.sw.pgw.service.PaymentGatewayImplService" %>
<%@ page import="java.util.StringTokenizer" %>
<%
    Long am;
     String _userid, _amount ,amR;
    _userid = request.getParameter("u");
    _amount = request.getParameter("p");
    amR = _amount;
    am = Long.parseLong(_amount+"0");

    Model om = new Model();
    int tempid = 0;
    String last_id = om.select("SELECT max(`b_OrderId`) FROM `bank`");
    try {
        tempid = Integer.parseInt(last_id)+2;
    } catch (Exception e) {
        tempid = 88000000;

    }
%>
<html>
    <head>
      
       <title>redirect to bank</title>
        <link href="Css/Style.css" rel="stylesheet" type="text/css"/>
        <link type="text/css" href="/resources/sentinel-layout/css/font-icon-layout.css" rel="stylesheet" />
        <link type="text/css" href="/resources/sentinel-layout/css/sentinel-layout.css" rel="stylesheet" />
        <link type="text/css" href="/resources/sentinel-layout/css/core-layout.css" rel="stylesheet" />
        <link type="text/css" rel="stylesheet" href="/resources/website/css/custom.css"/>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

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

    <body style="background-color: #3CB371" >

       <div class="Container100 Top20Percent PosAbsolute MarAuto OvHidden">
          
        <div class="Wid50 MarAuto OvHidden TexAlCenter">

            <embed type="image/svg+xml" src="/resources/sentinel-layout/images/bills-of-dollars.svg" class="red" style="width: 100px;"/>
            <div class="EmptyBox20"></div>
             <span class="white Wid100 DispBlock  Fs24 fontWEB">در حال اتصال</span><br/>
            <span class="white Wid100 DispBlock Fs12  fontWEB"> لطفا شکیبا باشید در حال اتصال به درگاه بانک ملت<br/></span>
            <span class="white Wid100 DispBlock Fs12  fontWEB"> در صورتی که به صورت اتوماتیک به درکاه بانک منتقل نشدید بر روی "پرداخت" کلیک نمایید<br/></span>
            <div class="EmptyBox20"></div>
             <form name="form1" method="post" preservedata="true">
             <input type="submit" CssClass="PublicButton" name="PayRequestButton" value="پرداخت"/>
             </form>               
                        

            <div class="EmptyBox50"></div>
        </div>
                
    </div>
                        </body>


                        <%
                            if (request.getMethod().equalsIgnoreCase("post")) {
                                IPaymentGateway service = new PaymentGatewayImplService().getPaymentGatewayImplPort();
                                String responseMessage = "";
                        %>
                        <% ///////////////// PAY REQUEST
                            Date d = new Date();
                            
                            if (request.getParameter("PayRequestButton") != null) {
                                long terminalId = 2174497;
                                String userName = "mand25";
                                String userPassword = "37414569";
                                long orderId = tempid;
                                long amount = am;
                                String localDate = "20100517";
                                String localTime = "140000";
                                String additionalData = "";
                                String callBackUrl = "http://amoozeshmand.com/callback";
                                long payerId = 0;

                                try {
                                    responseMessage = service.bpPayRequest(terminalId, userName, userPassword,
                                            orderId, amount, localDate, localTime, additionalData, callBackUrl, payerId);
                                } catch (Exception e) {
                        %>
                        <%--<script language='javascript' type='text/javascript'>alert('<%= e.getStackTrace().toString()%>');</script>--%>
                        <%
                            }
                        %>
                        <%--<script language='javascript' type='text/javascript'>alert('Pay Response is : ' + '"<%= responseMessage%>"');</script>--%>
                        <%
                            StringTokenizer stringTokenizer = new StringTokenizer(responseMessage, ",");
                            String[] results = new String[2];

                            // push all the words to the stack one by one
                            int i = 0;
                            while (stringTokenizer.hasMoreTokens()) {
                                results[i] = (String) stringTokenizer.nextElement();
                                i++;
                            }
                            if (results[0].equals("0")) {
                                om.insert("INSERT INTO `lms`.`bank` (`b_id`, `b_LocalDate`, `b_LocalTime`, `b_Amount`, `b_OrderId`, `b_AdditionalData`, `b_PayerId`, `b_SaleOrderId` ,b_us) VALUES "
                                        + "(NULL, '20180219', '140000', '" + amR + "', '" + tempid + "', 'no data', '"+_userid+"', '" + tempid + "', 'u');");
                        %>
                        <script language='javascript' type='text/javascript'>postRefId('<%= results[1] %>');</script>
                        <%
                        } else {
response.sendRedirect("../Error/bank_error.xhtml");
                        %>
                        <%--<script language='javascript' type='text/javascript'>postRefId('<%= responseMessage  %>');</script>--%>
                        <%
                                }
                            }
                            ///////////////// PAY REQUEST
                        %>
                        
                        
                        <%                            }
                        %>
                        
                        


                      
                        </script>
                      
                        </html>