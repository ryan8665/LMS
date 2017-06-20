package com.utility;

//This Class is for send text message
public class sendSms {
//This static method is parent method for send text message
//This method have two parameters, phone and message
    public static void _send(String phone, String message) {
        try {

            com.kavenegar.sdk.KavenegarApi api = new com.kavenegar.sdk.KavenegarApi("756E6C616757626C38644136574558586236666F39773D3D");
            api.Send("10006707323323", phone, message);
        } catch (com.kavenegar.sdk.excepctions.ApiException ex) {
            // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
            System.out.print(ex.toString());
        } catch (com.kavenegar.sdk.excepctions.HttpException ex) {
            // در زمانی که مشکلی در برقرای ارتباط با وب سرویس وجود داشته باشد این خطا رخ می دهد
            System.out.print(ex.toString());
        } catch (Exception ex) {
            System.out.print(ex.toString());
        }
    }
    public static void sendWelcome(String phone, String name) {
        try {

            com.kavenegar.sdk.KavenegarApi api = new com.kavenegar.sdk.KavenegarApi("756E6C616757626C38644136574558586236666F39773D3D");
            api.Send("10006707323323", phone, "" + name + "، خوش آمدید\n"
                    + "ورود به سامانه \n"
                    + "در " + com.utility.Utilities.todayDay() + "");
        } catch (com.kavenegar.sdk.excepctions.ApiException ex) {
            // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
            System.out.print("Message 11111111111111: " + ex.toString());
        } catch (com.kavenegar.sdk.excepctions.HttpException ex) {
            // در زمانی که مشکلی در برقرای ارتباط با وب سرویس وجود داشته باشد این خطا رخ می دهد
            System.out.print("Message 1111111111111111: " + ex.toString());
        } catch (Exception ex) {
            System.out.print("Message 1111111111111111: " + ex.toString());
        }
    }
    public static void sendPass(String phone, String name, String user, String pass) {
        try {

            com.kavenegar.sdk.KavenegarApi api = new com.kavenegar.sdk.KavenegarApi("756E6C616757626C38644136574558586236666F39773D3D");
            api.Send("10006707323323", phone, "سامانه \n"
                    + "مشخصات ورود شما عبارت است از\n"
                    + "نام کاربری: " + user + "\n"
                    + "گذرواژه: " + pass + "");
        } catch (com.kavenegar.sdk.excepctions.ApiException ex) {
            // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
            System.out.print("Message 11111111111111: " + ex.toString());
        } catch (com.kavenegar.sdk.excepctions.HttpException ex) {
            // در زمانی که مشکلی در برقرای ارتباط با وب سرویس وجود داشته باشد این خطا رخ می دهد
            System.out.print("Message 1111111111111111: " + ex.toString());
        } catch (Exception ex) {
            System.out.print("Message 1111111111111111: " + ex.toString());
        }
    }

}
