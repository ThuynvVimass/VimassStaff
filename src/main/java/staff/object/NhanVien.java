package staff.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NhanVien {

	public int id;
	public String ten = "";
	public String sdt = "";
	public String email = "";
	public String skype = "";
	public String img = "";
	public String hoSoNhanVien = "";
	public long thoiGianBatDauLamViec;
	public long thoiGianKetThucLamViec;

	public ChucVu chucVu;
	public int idChucVu;
	public int idPhongBan = 0;
	public String ghiChu = "";
	public int trangThai;

	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}

}
