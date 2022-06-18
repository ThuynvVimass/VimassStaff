package staff.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ChiNhanh {

	public int id;
	public String ten = "";
	public String sdt = "";
	public String diaChi = "";
	public String img = "";
	public String mst = "";
	public String ghiChu = "";
	public double lat;
	public double lng;
	public int radius;
	public long createdAt;
	public long updateAt;
	public String modifiedBy = "";
	public int trangThai;


	public ArrayList<PhongBan> phongBans = new ArrayList<>();

	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
							.disableHtmlEscaping()
							.create();
		return gson.toJson(this);
	}

}
