package staff;

import com.google.gson.Gson;
import staff.object.*;
import staff.table.*;
import vn.vimass.csdl.object.ErrorCode;
import vn.vimass.csdl.object.ObjectMessageResult;
import vn.vimass.utils.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class StaffFunc {

	public static String getAllNhanSu() {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		Data.ghiLogRequest("ApiLayThongTinNhanSu");
		try {
			ChiNhanhs nhanSu = new ChiNhanhs();
			// Lấy all thông tin
			ArrayList<NhanVien> nhanViens = TableNhanVien.getAll();

			// Tạo mapChucVus
			ArrayList<ChucVu> chucVus = TableChucVu.get(0);
			HashMap<Integer, ChucVu> chucVusMap = new HashMap<>();
			for (ChucVu chucVu : chucVus) chucVusMap.put(chucVu.id, chucVu);

			// Tạo mapPhongBans
			ArrayList<PhongBan> phongBans = TablePhongBan.get(0);
			HashMap<Integer, PhongBan> phongBansMap = new HashMap<>();
			for (PhongBan phongBan : phongBans) phongBansMap.put(phongBan.id, phongBan);

			HashMap<Integer, ArrayList<NhanVien>> mapIdPhongBan_NhanViens = new HashMap<>();
			for (NhanVien nhanVien : nhanViens) {
				ArrayList<NhanVien> nhanViens1 = new ArrayList<>();
				nhanViens1 = mapIdPhongBan_NhanViens.containsKey(nhanVien.idPhongBan)
									 ? mapIdPhongBan_NhanViens.get(nhanVien.idPhongBan) : nhanViens1;
				nhanViens1.add(nhanVien);
				mapIdPhongBan_NhanViens.put(nhanVien.idPhongBan, nhanViens1);
			}

			// Tạo mapChiNhanhs
			ArrayList<ChiNhanh> chiNhanhs = TableChiNhanh.get(0);
			HashMap<Integer, ChiNhanh> chiNhanhsMap = new HashMap<>();
			for (ChiNhanh chiNhanh : chiNhanhs) chiNhanhsMap.put(chiNhanh.id, chiNhanh);

			HashMap<Integer, ArrayList<PhongBan>> mapIdChiNhanh_PhongBans = new HashMap<>();
			for (PhongBan phongBan : phongBans) {
				ArrayList<PhongBan> phongBans1 = new ArrayList<>();
				phongBans1 = mapIdChiNhanh_PhongBans.containsKey(phongBan.idChiNhanh)
									 ? mapIdChiNhanh_PhongBans.get(phongBan.idChiNhanh) : phongBans1;
				phongBans1.add(phongBan);
				mapIdChiNhanh_PhongBans.put(phongBan.idChiNhanh, phongBans1);
			}

			// Xử lý
			for (ChiNhanh chiNhanh : chiNhanhs) {
				if (chiNhanh.trangThai == 1) {
					ArrayList<PhongBan> phongBans1 = new ArrayList<>();
					phongBans1 = mapIdChiNhanh_PhongBans.containsKey(chiNhanh.id)
										 ? mapIdChiNhanh_PhongBans.get(chiNhanh.id) : phongBans1;
					for (PhongBan phongBan1 : phongBans1) {
						if (phongBan1.trangThai == 1) {
							ArrayList<NhanVien> nhanViens1 = new ArrayList<>();
							nhanViens1 = mapIdPhongBan_NhanViens.containsKey(phongBan1.id)
												 ? mapIdPhongBan_NhanViens.get(phongBan1.id) : nhanViens1;
							for (NhanVien nhanVien : nhanViens1) {
								if (nhanVien.trangThai == 1) {
									nhanVien.chucVu = chucVusMap.get(nhanVien.idChucVu);
									phongBan1.nhanViens.add(nhanVien);
								}
							}
							chiNhanh.phongBans.add(phongBan1);
						}
					}
					nhanSu.chiNhanhs.add(chiNhanh);
				}
			}

			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, nhanSu);
			Data.ghiLogRequest("ApiLayThongTinNhanSu: " + new Gson().toJson(nhanSu));

			return new Gson().toJson(ketQua);
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiLayThongTinNhanSu: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String getNhanVien(int id, int idChiNhanh, int idPhongBan, int limit, int offset) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		Data.ghiLogRequest("ApiLayThongTinNhanVien");
		try {
			ArrayList<NhanVien> nhanViens = TableNhanVien.get(id, idChiNhanh, idPhongBan, limit, offset);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS,
					new NhanViens(nhanViens));
			Data.ghiLogRequest("ApiLayThongTinNhanVien: " + new Gson().toJson(nhanViens));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiLayThongTinNhanVien: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String addNhanVien(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		NhanVien objInput = new Gson().fromJson(input, NhanVien.class);

		Data.ghiLogRequest("ApiAddNhanVien");
		try {
			String kq = TableNhanVien.add(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiAddNhanVien: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiAddNhanVien: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String updateNhanVien(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		NhanVien objInput = new Gson().fromJson(input, NhanVien.class);

		Data.ghiLogRequest("ApiUpdateNhanVien");
		try {
			String kq = TableNhanVien.update(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiUpdateNhanVien: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiUpdateNhanVien: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String deleteNhanVien(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		NhanVien objInput = new Gson().fromJson(input, NhanVien.class);

		Data.ghiLogRequest("ApiDeleteNhanVien");
		try {
			String kq = TableNhanVien.delete(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiDeleteNhanVien: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiDeleteNhanVien: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String getChiNhanh(int id, int limit, int offset) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		Data.ghiLogRequest("ApiGetChiNhanh");
		try {
			ArrayList<ChiNhanh> chiNhanhs = TableChiNhanh.get(id, limit, offset);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS,
					new ChiNhanhs(chiNhanhs));
			Data.ghiLogRequest("ApiGetChiNhanh: " + new Gson().toJson(chiNhanhs));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiGetChiNhanh: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String addChiNhanh(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		ChiNhanh objInput = new Gson().fromJson(input, ChiNhanh.class);

		Data.ghiLogRequest("ApiAddChiNhanh");
		try {
			String kq = TableChiNhanh.add(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiAddChiNhanh: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiAddChiNhanh: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String updateChiNhanh(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		ChiNhanh objInput = new Gson().fromJson(input, ChiNhanh.class);

		Data.ghiLogRequest("ApiUpdateChiNhanh");
		try {
			String kq = TableChiNhanh.update(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiUpdateChiNhanh: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiUpdateChiNhanh: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String deleteChiNhanh(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		ChiNhanh objInput = new Gson().fromJson(input, ChiNhanh.class);

		Data.ghiLogRequest("ApiDeleteChiNhanh");
		try {
			String kq = TableChiNhanh.delete(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiDeleteChiNhanh: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiDeleteChiNhanh: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}
	public static String getPhongBan(int id, int idChiNhanh, int limit, int offset) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		Data.ghiLogRequest("ApiGetPhongBan");
		try {
			ArrayList<PhongBan> phongBans = TablePhongBan.get(id, idChiNhanh, limit, offset);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS,
					new PhongBans(phongBans));
			Data.ghiLogRequest("ApiGetPhongBan: " + new Gson().toJson(phongBans));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiGetPhongBan: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String addPhongBan(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		PhongBan objInput = new Gson().fromJson(input, PhongBan.class);

		Data.ghiLogRequest("ApiAddPhongBan");
		try {
			String kq = TablePhongBan.add(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiAddPhongBan: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiAddPhongBan: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String updatePhongBan(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		PhongBan objInput = new Gson().fromJson(input, PhongBan.class);

		Data.ghiLogRequest("ApiUpdatePhongBan");
		try {
			String kq = TablePhongBan.update(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiUpdatePhongBan: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiUpdatePhongBan: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String deletePhongBan(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		PhongBan objInput = new Gson().fromJson(input, PhongBan.class);

		Data.ghiLogRequest("ApiDeletePhongBan");
		try {
			String kq = TablePhongBan.delete(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiDeletePhongBan: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiDeletePhongBan: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String getChucVu(int id, int limit, int offset) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		Data.ghiLogRequest("ApiGetChucVu");
		try {
			ArrayList<ChucVu> chucVus = TableChucVu.get(id, limit, offset);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS,
					new ChucVus(chucVus));
			Data.ghiLogRequest("ApiGetChucVu: " + new Gson().toJson(chucVus));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiGetChucVu: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String addChucVu(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		ChucVu objInput = new Gson().fromJson(input, ChucVu.class);

		Data.ghiLogRequest("ApiAddChucVu");
		try {
			String kq = TableChucVu.add(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiAddChucVu: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiAddChucVu: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String updateChucVu(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		ChucVu objInput = new Gson().fromJson(input, ChucVu.class);

		Data.ghiLogRequest("ApiUpdateChucVu");
		try {
			String kq = TableChucVu.update(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiUpdateChucVu: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiUpdateChucVu: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	public static String deleteChucVu(String input) {
		ObjectMessageResult ketQua = new ObjectMessageResult(ErrorCode.FALSE, ErrorCode.MES_FALSE);

		ChucVu objInput = new Gson().fromJson(input, ChucVu.class);

		Data.ghiLogRequest("ApiDeleteChucVu");
		try {
			String kq = TableChucVu.delete(objInput);
			ketQua = new ObjectMessageResult(ErrorCode.SUCCESS, ErrorCode.MES_SUCCESS, kq);
			Data.ghiLogRequest("ApiDeleteChucVu: " + new Gson().toJson(kq));
		} catch (Exception e) {
			ketQua.setResult(e.getMessage());
			Data.ghiLogRequest("ApiDeleteChucVu: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

}