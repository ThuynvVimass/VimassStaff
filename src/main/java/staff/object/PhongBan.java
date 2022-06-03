package staff.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class PhongBan {

	public int id;
	public String ten = "";
	public int idChiNhanh;
	public String ghiChu = "";
	public int trangThai;

	public ArrayList<NhanVien> nhanViens = new ArrayList<>();

	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}

}
