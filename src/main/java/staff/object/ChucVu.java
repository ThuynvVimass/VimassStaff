package staff.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ChucVu {

	public int id;
	public String ten = "";
	public long createdAt;
	public long updateAt;
	public String modifiedBy = "";
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
