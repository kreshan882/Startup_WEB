/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.util;

import com.shop.db.DBConnection;
import com.shop.init.Status;
import com.shop.inv.member.bean.MemberManagementInputBean;
import com.shop.login.bean.SessionUserBean;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author tharaka
 */
public class Util {

    private static final String ENCRYPTION_KEY = "kreshanKey";

    public static String encryptionCard(String accNumber) throws Exception {
        Key aesKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(accNumber.getBytes());
        return new String(encrypted);
    }

    public static String decryptionCard(String encrypted) throws Exception {
        System.out.println(encrypted);
        Key aesKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted.getBytes()));
        return decrypted;

    }

    private static byte[] fromHexString(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static boolean validateNAME(String text) throws Exception {
        return text.matches("^[A-Za-z]+(?:[ _-][A-Za-z]+)*$") && text.length() <= 50;
    }

    public static boolean validateNUMBER(String numericString) throws Exception {
        return numericString.matches("^[0-9]*$") && numericString.length() <= 15;
    }

    public static boolean validateEMAIL(String email) throws Exception {  //   VF2
        return email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") && email.length() <= 50;
    }

    public static boolean validatePHONENO(String numericString) throws Exception { //   VF1
        return numericString.matches("^[0-9]*$") && numericString.length() <= 15;
        //return numericString.matches("^\\+(?:[0-9])*$") && numericString.length() == 12;
    }


    public static boolean validatePORT(String numericString) throws Exception { //   VF1
        return numericString.matches("([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])");
    }

    public static boolean validateMOBILE(String numericString) throws Exception { //   VF1
        return numericString.matches("^[0-9]*$") && numericString.length() <= 10;
    }

    public static boolean validateAMOUNT(String numericString) throws Exception {
        return numericString.matches("^[0-9]+([.][0-9]{1,2})?");
    }

    public static boolean validateNIC(String nic) {
        return nic.matches("^[0-9]{9}[vVxX]$") && nic.length() >= 10;
    }

    public static boolean validationNICPP(String nicpp) {
        return (nicpp.matches("^[0-9]{9}[vVxX]$") && nicpp.length() >= 10) ? true : (nicpp.matches("^[A-Z]{1}[0-9]{7}$"));
    }

    public static boolean validateSPECIALCHAR(String specialChars) throws Exception {       // VF5
        return specialChars.matches("[~@#$&!~]+");
    }

    public static boolean validateDESCRIPTION(String text) {
        return text.matches("^(.*/)?(?:$|(.+?)(?:(\\.[^.]*$)|$))") && text.length() <= 150;
    }

    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public static boolean validateIP(final String ip) {
        return PATTERN.matcher(ip).matches();
    }

    public static boolean validateURL(String text) {
        return text.matches("\\b(https?|ftp|file|ldap)://"
                + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]"
                + "*[-A-Za-z0-9+&@#/%=~_|]") && text.length() <= 150;
    }

    public static boolean validateSTRING(String text) throws Exception {
        return text.matches("^[a-zA-Z0-9]+$") && text.length() <= 200;
    }

     public static boolean validateImageFileName(String filenamei) throws Exception {

        boolean ok = false;
        String filename = "" + filenamei;
        String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

        try {

            if (filenamei != null && !extension.toUpperCase().equals("JPG") && !extension.toUpperCase().equals("PNG")) {
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ok;
        }

        return ok;
    }
    public static Date convertStringToDate(String dateString) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = format.parse(dateString);
        return date;
    }

    public static java.sql.Date convertStringToDBDate(String date) throws Exception {

        SimpleDateFormat formtter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dtt = formtter.parse(date);
        return new java.sql.Date(dtt.getTime());

    }

    public static Map<Integer, String> getBasicStatus() {
        Map<Integer, String> basicStatus = new HashMap<Integer, String>();
        basicStatus.put(Status.ACTIVE, "ACTIVE");
        basicStatus.put(Status.INACTIVE, "INACTIVE");
        return basicStatus;
    }

    public static Map<String, String> getMemIslifeList() {
        Map<String, String> memIslifeList = new HashMap<String, String>();
        memIslifeList.put("1", "Life Time Member");
        memIslifeList.put("0", "Anual Member");
        return memIslifeList;
    }

    public static Map<String, String> getMerriedList() {
        Map<String, String> merrStatList = new HashMap<String, String>();
        merrStatList.put("1", "Merrid");
        merrStatList.put("0", "Single");
        return merrStatList;
    }
    

    
    public static Map<String, String> getNumberList() {
        Map<String, String> numList = new HashMap<String, String>();
        numList.put("0", "0");
        numList.put("1", "1");
        numList.put("2", "2");
        numList.put("3", "3");
        numList.put("4", "4");
        numList.put("5", "5");
        numList.put("6", "6");
        numList.put("7", "7");
        numList.put("8", "8");
        numList.put("9", "9");
        numList.put("10", "More Then 10");
        return numList;
    }



    

    public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {

            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<K, V> sortedMap = new LinkedHashMap<K, V>();

        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static Date getLocalDate() {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        java.sql.Date date2 = new java.sql.Date(d.getTime());
        return date2;
    }

    public static String getOSPathCert(SessionUserBean sub) throws Exception {
        // returs path if log path as  "/opt/e24ocm/logs/epic"
        String path = null;

        try {
//            String logpath = "/opt/e24ocm/logs/epic";
            String logpath = sub.getLogFilePath();

            String linuxPath = logpath + "downloads";
            String removeFirstSlash = linuxPath.substring(linuxPath.indexOf("/") + 1);
            String removeToSecondSlash = removeFirstSlash.substring(removeFirstSlash.indexOf("/"));
            String conForwordToBack = removeToSecondSlash.replace("/", "\\");

            if (System.getProperty("os.name").startsWith("Windows")) {
                path = "C:" + conForwordToBack;
            } else if (System.getProperty("os.name").startsWith("Linux")) {
                path = linuxPath;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return path;
    }

    public static String getOSLogPath(String logpath) throws Exception {

        String path = null;

        try {
            String linuxPath = logpath + "/";
            //System.out.println("linuxpath="+linuxPath);
            String removeFirstSlash = linuxPath.substring(linuxPath.indexOf("/") + 1);
            String removeToSecondSlash = removeFirstSlash.substring(removeFirstSlash.indexOf("/"));
            String conForwordToBack = removeToSecondSlash.replace("/", "\\\\");

            if (System.getProperty("os.name").startsWith("Windows")) {
                path = "C:" + conForwordToBack;
            } else if (System.getProperty("os.name").startsWith("Linux")) {
                path = linuxPath;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return path;
    }

    public static String generateHash(String password) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        return hashtext;
    }



    public static String changeDateFormat(String date) throws ParseException {

        Date tradeDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(date);
        String krwtrDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(tradeDate);
        return krwtrDate;
    }

    public static String getDateFromTDate(int Kyear, int tDate) {
        GregorianCalendar zeroDate = new GregorianCalendar();
        zeroDate.set(Kyear, 0, 0);

        Calendar birthday = Calendar.getInstance();
        birthday.setTime(zeroDate.getTime());
        birthday.add(Calendar.DAY_OF_YEAR, tDate);

        Date date = birthday.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }



    public static String round(String in) {
        double value = Double.parseDouble(in);
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        Double ret = (double) tmp / factor;
        return ret.toString();
    }

    public static String formatTimestamp(Timestamp in) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(in);
    }

   public static String getDateNow() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
   
   public static String changeDateFormatyyyyMMdd(String date) throws ParseException {

        Date tradeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date);
        String krwtrDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(tradeDate);
        return krwtrDate;
    }


       public static Map<String, String> getCastList() {
        Map<String, String> memCastList = new HashMap<String, String>();
        memCastList.put("01", "Kalar");
        memCastList.put("02", "Maraver");
        memCastList.put("03", "Agamudiyar");
        return memCastList;
    }
}
