package staff.ultis;

import org.apache.commons.lang3.math.NumberUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Ultis {
	public static String HOADON_GTGT = "01GTKT";
	public static String FPT_ROOT = "https://api-uat.einvoice.fpt.com.vn";
	public static String URL_LAP_HOADON = FPT_ROOT + "/create-invoice";
	
	public static int SUCCESS = 200;
	public static int AUTHOR = 400;
	public static int DATA_ERROR = 401;
	
	 public static boolean isValidJSON(final String json) {
	        boolean valid = false;
	        try {
	            final JsonParser parser = new ObjectMapper().getJsonFactory()
	                    .createJsonParser(json);
	            while (parser.nextToken() != null) {
	            }
	            valid = true;
	        } catch (JsonParseException jpe) {
	            jpe.printStackTrace();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }

	        return valid;
	    }
	
	public static String convertMoneyToWord(double money)
	{		
		String word = ChuyenSangChu(String.valueOf(money));
		return word;
	}

	public static HashMap<String, String> hm_tien = new HashMap<String, String>() {
        {
            put("0", "không");
            put("1", "một");
            put("2", "hai");
            put("3", "ba");
            put("4", "bốn");
            put("5", "năm");
            put("6", "sáu");
            put("7", "bảy");
            put("8", "tám");
            put("9", "chín");
        }
    };

    public static HashMap<String, String> hm_hanh = new HashMap<String, String>() {
        {
            put("1", "đồng");
            put("2", "mươi");
            put("3", "trăm");
            put("4", "nghìn");
            put("5", "mươi");
            put("6", "trăm");
            put("7", "triệu");
            put("8", "mươi");
            put("9", "trăm");
            put("10", "tỷ");
            put("11", "mươi");
            put("12", "trăm");
            put("13", "nghìn");
            put("14", "mươi");
            put("15", "trăm");

        }
    };
    
    public static String ChuyenSangChu(String x) {
        String kq = "";
        x = x.replace(".", "");
        String arr_temp[] = x.split(",");
        if (!NumberUtils.isNumber(arr_temp[0])) {
            return "";
        }
        String m = arr_temp[0];
        int dem = m.length();
        String dau = "";
        int flag10 = 1;
        while (!m.equals("")) {
            if (m.length() <= 3 && m.length() > 1 && Long.parseLong(m) == 0) {

            } else {
                dau = m.substring(0, 1);
                if (dem % 3 == 1 && m.startsWith("1") && flag10 == 0) {
                    kq += "mốt ";
                    flag10 = 0;
                } else if (dem % 3 == 2 && m.startsWith("1")) {
                    kq += "mười ";
                    flag10 = 1;
                } else if (dem % 3 == 2 && m.startsWith("0") && m.length() >= 2 && !m.substring(1, 2).equals("0")) {
                    //System.out.println("a  "+m.substring(1, 2));
                    kq += "lẻ ";
                    flag10 = 1;
                } else {
                    if (!m.startsWith("0")) {
                        kq += hm_tien.get(dau) + " ";
                        flag10 = 0;
                    }
                }
                if (dem%3!=1 &&m.startsWith("0") && m.length()>1) {
                } else {
                    if (dem % 3 == 2 && (m.startsWith("1") || m.startsWith("0"))) {//mười
                    } else {
                        kq += hm_hanh.get(dem + "") + " ";
                    }
                }
            }
            m = m.substring(1);
            dem = m.length();
        }
        kq=kq.substring(0, kq.length() - 1);
        return kq;
    }

    public static String currencyFormat(String curr) {
        try {
            double vaelue = Double.parseDouble(curr);
            String pattern = "###,###";
            DecimalFormat myFormatter = new DecimalFormat(pattern);
            String output = myFormatter.format(vaelue);
            return output;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

	public static long TimeConverterFrom(String time)
	{
		long result = 0;
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			Date d = f.parse(time);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			result = d.getTime();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static long TimeConverterTo(String time)
	{
		long result = 0;
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			Date d = f.parse(time);
			d.setHours(23);
			d.setMinutes(59);
			d.setSeconds(0);
			result = d.getTime();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

}
