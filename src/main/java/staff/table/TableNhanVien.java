package staff.table;

import staff.object.NhanVien;
import vn.vimass.csdl.utilDB.DbUtil;
import vn.vimass.utils.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TableNhanVien {

	public static final String TABLE_NAME = "nhan_vien";
	public static final String ID = "id";
	public static final String TEN = "ten";
	public static final String SDT = "sdt";
	public static final String EMAIL = "email";
	public static final String SKYPE = "skype";
	public static final String IMG = "img";
	public static final String HO_SO_NHAN_VIEN = "ho_so_nhan_vien";
	public static final String THOI_GIAN_BAT_DAU_LAM_VIEC = "time_in";
	public static final String THOI_GIAN_KET_THUC_LAM_VIEC = "time_out";
	public static final String ID_CHUC_VU = "id_chuc_vu";
	public static final String ID_PHONG_BAN = "id_phong_ban";
	public static final String CV = "cv";
	public static final String LUONG = "luong";
	public static final String LOAI_HOP_DONG = "loai_hop_dong";
	public static final String GHI_CHU = "ghi_chu";
	public static final String TRANG_THAI = "trang_thai";

	public static ArrayList<NhanVien> getAll() {
		String TAG = "TableNhanVien-getAll";
		ArrayList<NhanVien> ketQua = new ArrayList<>();
		try {
			String strSqlSelect = "SELECT * FROM " + TABLE_NAME;
			// Thêm đoạn code sắp xếp theo khu vực -> phòng ban -> độ ưu tiên
			Data.ghiLogRequest(TAG + "\tselect:" + strSqlSelect);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			Statement statement = connect.createStatement();

			ResultSet rs = statement.executeQuery(strSqlSelect);
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.id = rs.getInt(ID);
				nhanVien.ten = rs.getString(TEN);
				nhanVien.sdt = rs.getString(SDT);
				nhanVien.email = rs.getString(EMAIL);
				nhanVien.skype = rs.getString(SKYPE);
				nhanVien.img = rs.getString(IMG);
				nhanVien.hoSoNhanVien = rs.getString(HO_SO_NHAN_VIEN);
				nhanVien.thoiGianBatDauLamViec = rs.getLong(THOI_GIAN_BAT_DAU_LAM_VIEC);
				nhanVien.thoiGianKetThucLamViec = rs.getLong(THOI_GIAN_KET_THUC_LAM_VIEC);
				nhanVien.idChucVu = rs.getInt(ID_CHUC_VU);
				nhanVien.idPhongBan = rs.getInt(ID_PHONG_BAN);
				nhanVien.cv = rs.getString(CV);
				nhanVien.luong = rs.getString(LUONG);
				nhanVien.loaiHopDong = rs.getInt(LOAI_HOP_DONG);
				nhanVien.ghiChu = rs.getString(GHI_CHU);
				nhanVien.trangThai = rs.getInt(TRANG_THAI);
				ketQua.add(nhanVien);
			}
			Data.ghiLogRequest(TAG + "\tkq:" + ketQua);

		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return ketQua;
	}

	public static ArrayList<NhanVien> get(int idInput, int idChiNhanh, int idPhongBan) {
		return get(idInput, idChiNhanh, idPhongBan, 0, 0);
	}

	public static ArrayList<NhanVien> get(int idNhanVien, int idChiNhanh, int idPhongBan, int limit, int offset) {
		String TAG = "TableNhanVien-lay";
		ArrayList<NhanVien> ketQua = new ArrayList<>();
		try {
			String strSqlSelect = "SELECT * FROM " + TABLE_NAME;
			strSqlSelect += "  nv join " + TablePhongBan.TABLE_NAME + " pb on nv." + ID_PHONG_BAN + " = pb." + TablePhongBan.ID +
									" where ((nv.id = " + idNhanVien + ") or (" + idNhanVien + " = 0)) and ((id_chi_nhanh = " +
									idChiNhanh + ") or (" + idChiNhanh + " = 0)) and ((id_phong_ban = " + idPhongBan + ") or (" +
									idPhongBan + " = 0)) ";
			if (limit != 0) strSqlSelect += " LIMIT " + offset + ", " + limit + ";";
			else strSqlSelect += ";";
			Data.ghiLogRequest(TAG + "\tselect:" + strSqlSelect);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			Statement statement = connect.createStatement();

			ResultSet rs = statement.executeQuery(strSqlSelect);
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.id = rs.getInt(ID);
				nhanVien.ten = rs.getString(TEN);
				nhanVien.sdt = rs.getString(SDT);
				nhanVien.email = rs.getString(EMAIL);
				nhanVien.skype = rs.getString(SKYPE);
				nhanVien.img = rs.getString(IMG);
				nhanVien.hoSoNhanVien = rs.getString(HO_SO_NHAN_VIEN);
				nhanVien.thoiGianBatDauLamViec = rs.getLong(THOI_GIAN_BAT_DAU_LAM_VIEC);
				nhanVien.thoiGianKetThucLamViec = rs.getLong(THOI_GIAN_KET_THUC_LAM_VIEC);
				nhanVien.idChucVu = rs.getInt(ID_CHUC_VU);
				nhanVien.idPhongBan = rs.getInt(ID_PHONG_BAN);
				nhanVien.cv = rs.getString(CV);
				nhanVien.luong = rs.getString(LUONG);
				nhanVien.loaiHopDong = rs.getInt(LOAI_HOP_DONG);
				nhanVien.ghiChu = rs.getString(GHI_CHU);
				nhanVien.trangThai = rs.getInt(TRANG_THAI);
				ketQua.add(nhanVien);
			}
			Data.ghiLogRequest(TAG + "\tkq:" + ketQua);

		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return ketQua;
	}

	public static String add(NhanVien nhanVien) {
		String TAG = "TableChiNhanh-addDuLieu-getAll";
		String idKQ = "";
		try {
			String strSqlInsert = "INSERT INTO " + TABLE_NAME + ""
										  + " ("
										  + TEN + ", "
										  + SDT + ", "
										  + EMAIL + ", "
										  + SKYPE + ", "
										  + IMG + ", "
										  + HO_SO_NHAN_VIEN + ", "
										  + THOI_GIAN_BAT_DAU_LAM_VIEC + ", "
										  + THOI_GIAN_KET_THUC_LAM_VIEC + ", "
										  + ID_CHUC_VU + ", "
										  + ID_PHONG_BAN + ", "
										  + CV + ", "
										  + LUONG + ", "
										  + LOAI_HOP_DONG + ", "
										  + GHI_CHU + ", "
										  + TRANG_THAI
										  + " ) VALUES ("
										  + "N'" + nhanVien.ten + "',"
										  + "N'" + nhanVien.sdt + "',"
										  + "N'" + nhanVien.email + "',"
										  + "N'" + nhanVien.skype + "',"
										  + "N'" + nhanVien.img + "',"
										  + "N'" + nhanVien.hoSoNhanVien + "',"
										  + nhanVien.thoiGianBatDauLamViec + ","
										  + nhanVien.thoiGianKetThucLamViec + ","
										  + nhanVien.idChucVu + ","
										  + nhanVien.idPhongBan + ","
										  + "N'" + nhanVien.cv + "',"
										  + "N'" + nhanVien.luong + "',"
										  + nhanVien.loaiHopDong + ","
										  + "N'" + nhanVien.ghiChu + "',"
										  + nhanVien.trangThai
										  + ");";

			Data.ghiLogRequest(TAG + "\tinsert:" + strSqlInsert);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlInsert);
			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				String strSqlSelect = "SELECT MAX(ID) as id FROM " + TABLE_NAME;
				Statement statement2 = connect.createStatement();
				ResultSet rs = statement2.executeQuery(strSqlSelect);
				while (rs.next()) {
					idKQ = String.valueOf(rs.getInt(ID));
				}
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi 1=======");
		}
		return idKQ;
	}

	public static String update(NhanVien nhanVien) {
		String TAG = "TableChiNhanh-update";
		String idKQ = "";
		try {
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + TEN + " = N'" + nhanVien.ten + "', "
										  + SDT + " = N'" + nhanVien.sdt + "', "
										  + EMAIL + " = N'" + nhanVien.email + "', "
										  + SKYPE + " = N'" + nhanVien.skype + "', "
										  + IMG + " = N'" + nhanVien.img + "', "
										  + HO_SO_NHAN_VIEN + " = N'" + nhanVien.hoSoNhanVien + "', "
										  + THOI_GIAN_BAT_DAU_LAM_VIEC + " = " + nhanVien.thoiGianBatDauLamViec + ", "
										  + THOI_GIAN_KET_THUC_LAM_VIEC + " = " + nhanVien.thoiGianKetThucLamViec + ", "
										  + ID_CHUC_VU + " = " + nhanVien.idChucVu + ", "
										  + ID_PHONG_BAN + " = " + nhanVien.idPhongBan + ", "
										  + CV + " = N'" + nhanVien.cv + "', "
										  + LUONG + " = N'" + nhanVien.luong + "', "
										  + LOAI_HOP_DONG + " = " + nhanVien.loaiHopDong + ", "
										  + GHI_CHU + " = N'" + nhanVien.ghiChu + "', "
										  + TRANG_THAI + " = " + nhanVien.trangThai;
			strSqlUpdate += " WHERE "
									+ ID + " = '" + nhanVien.id + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlUpdate);

			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = String.valueOf(nhanVien.id);
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}

	public static String delete(NhanVien nhanVien) {
		String TAG = "Table" + TABLE_NAME + "-delete";
		String idKQ = "";
		try {
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + GHI_CHU + " = N'" + nhanVien.ghiChu + "', "
										  + TRANG_THAI + " = -2 ";
			strSqlUpdate += " WHERE "
									+ ID + " = '" + nhanVien.id + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlUpdate);

			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = String.valueOf(nhanVien.id);
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}

	public static int count() {
		String TAG = "Table" + TABLE_NAME + "-count";
		int ketQua =0;
		try {
			String strSqlSelect = "SELECT COUNT('ID') as COUNT FROM " + TABLE_NAME;
			strSqlSelect += " WHERE " + "(" + TRANG_THAI + " =  " + 1 + ");";
			Data.ghiLogRequest(TAG + "\tselect:" + strSqlSelect);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			Statement statement = connect.createStatement();

			ResultSet rs = statement.executeQuery(strSqlSelect);
			while (rs.next()) {
				ketQua = rs.getInt("COUNT");
			}
			Data.ghiLogRequest(TAG + "\tkq:" + ketQua);

		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return ketQua;
	}
}
