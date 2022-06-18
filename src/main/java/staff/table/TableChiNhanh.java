package staff.table;

import staff.object.ChiNhanh;
import vn.vimass.csdl.utilDB.DbUtil;
import vn.vimass.utils.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class TableChiNhanh {

	public static final String TABLE_NAME = "chi_nhanh";
	public static final String ID = "id";
	public static final String TEN = "ten";
	public static final String SDT = "sdt";
	public static final String DIA_CHI = "dia_chi";
	public static final String IMG = "img";
	public static final String MST = "mst";
	public static final String GHI_CHU = "ghi_chu";
	public static final String LAT = "lat";
	public static final String LNG = "lng";
	public static final String RADIUS = "radius";
	public static final String CREATED_AT = "created_at";
	public static final String UPDATE_AT = "update_at";
	public static final String MODIFIED_BY = "modified_by";

	public static final String TRANG_THAI = "trang_thai";

	public static ArrayList<ChiNhanh> get(int idInput) {
		return get(idInput, 0, 0);
	}

	public static ArrayList<ChiNhanh> get(int idInput, int limit, int offset) {
		String TAG = "TableChiNhanh-lay";
		ArrayList<ChiNhanh> ketQua = new ArrayList<>();
		try {
			String strSqlSelect = "SELECT * FROM " + TABLE_NAME;
			if (idInput != 0) strSqlSelect += " WHERE " + "(" + TableChiNhanh.ID + " =  " + idInput + ") ";
			if (limit != 0) strSqlSelect += " LIMIT " + offset + ", " + limit + ";";
			else strSqlSelect += ";";
			Data.ghiLogRequest(TAG + "\tselect:" + strSqlSelect);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			Statement statement = connect.createStatement();

			ResultSet rs = statement.executeQuery(strSqlSelect);
			while (rs.next()) {
				ChiNhanh chiNhanh = new ChiNhanh();
				chiNhanh.id = rs.getInt(ID);
				chiNhanh.ten = rs.getString(TEN);
				chiNhanh.sdt = rs.getString(SDT);
				chiNhanh.diaChi = rs.getString(DIA_CHI);
				chiNhanh.img = rs.getString(IMG);
				chiNhanh.mst = rs.getString(MST);
				chiNhanh.ghiChu = rs.getString(GHI_CHU);
				chiNhanh.lat = rs.getDouble(LAT);
				chiNhanh.lng = rs.getDouble(LNG);
				chiNhanh.radius = rs.getInt(RADIUS);
				chiNhanh.createdAt = rs.getLong(CREATED_AT);
				chiNhanh.updateAt = rs.getLong(UPDATE_AT);
				chiNhanh.modifiedBy = rs.getString(MODIFIED_BY);
				chiNhanh.trangThai = rs.getInt(TRANG_THAI);
				ketQua.add(chiNhanh);
			}
			Data.ghiLogRequest(TAG + "\tkq:" + ketQua);

		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return ketQua;
	}

	public static ChiNhanh get(String sdtNhanVien) {
		String TAG = "TableChiNhanh-lay";
		ChiNhanh ketQua = null;
		try {
			if (sdtNhanVien == null || sdtNhanVien.equals("") ) return null;
			String strSqlSelect = "SELECT * FROM " + TABLE_NAME + " cn join";
			strSqlSelect += " (SELECT pb.id as id_phong_ban, id_chi_nhanh FROM phong_ban pb join nhan_vien nv";
			strSqlSelect += " on pb.id = nv.id_phong_ban";
			strSqlSelect += " WHERE nv.sdt like '%" + sdtNhanVien + "%'";
			strSqlSelect += " LIMIT  1) temp";
			strSqlSelect += " on cn.id = temp.id_chi_nhanh;";

			Data.ghiLogRequest(TAG + "\tselect:" + strSqlSelect);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			Statement statement = connect.createStatement();

			ResultSet rs = statement.executeQuery(strSqlSelect);
			while (rs.next()) {
				ChiNhanh chiNhanh = new ChiNhanh();
				chiNhanh.id = rs.getInt(ID);
				chiNhanh.ten = rs.getString(TEN);
				chiNhanh.sdt = rs.getString(SDT);
				chiNhanh.diaChi = rs.getString(DIA_CHI);
				chiNhanh.img = rs.getString(IMG);
				chiNhanh.mst = rs.getString(MST);
				chiNhanh.ghiChu = rs.getString(GHI_CHU);
				chiNhanh.lat = rs.getDouble(LAT);
				chiNhanh.lng = rs.getDouble(LNG);
				chiNhanh.radius = rs.getInt(RADIUS);
				chiNhanh.createdAt = rs.getLong(CREATED_AT);
				chiNhanh.updateAt = rs.getLong(UPDATE_AT);
				chiNhanh.modifiedBy = rs.getString(MODIFIED_BY);
				chiNhanh.trangThai = rs.getInt(TRANG_THAI);
				ketQua = chiNhanh;
			}
			Data.ghiLogRequest(TAG + "\tkq:" + ketQua);

		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return ketQua;
	}

	public static String add(ChiNhanh chiNhanh) {
		String TAG = "TableChiNhanh-add";
		String idKQ = "";
		try {
			Date dayNow = new Date();
			String strSqlInsert = "INSERT INTO " + TABLE_NAME + ""
										  + " ("
										  + TEN + ", "
										  + SDT + ", "
										  + DIA_CHI + ", "
										  + IMG + ", "
										  + MST + ", "
										  + GHI_CHU + ", "
										  + LAT + ", "
										  + LNG + ", "
										  + RADIUS + ", "
										  + CREATED_AT + ", "
										  + MODIFIED_BY + ", "
										  + TRANG_THAI
										  + " ) VALUES ("
										  + "N'" + chiNhanh.ten + "',"
										  + "N'" + chiNhanh.sdt + "',"
										  + "N'" + chiNhanh.diaChi + "',"
										  + "N'" + chiNhanh.img + "',"
										  + "N'" + chiNhanh.mst + "',"
										  + chiNhanh.lat + ","
										  + chiNhanh.lng + ","
										  + chiNhanh.radius + ","
										  + dayNow.getTime() + ","
										  + "N'" + chiNhanh.modifiedBy + "',"
										  + "N'" + chiNhanh.ghiChu + "',"
										  + chiNhanh.trangThai
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

	public static String update(ChiNhanh chiNhanh) {
		String TAG = "TableChiNhanh-update";
		String idKQ = "";
		String checkSum1 = "";
		try {
			Date dayNow = new Date();
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + TEN + " = N'" + chiNhanh.ten + "', "
										  + SDT + " = N'" + chiNhanh.sdt + "', "
										  + DIA_CHI + " = N'" + chiNhanh.diaChi + "', "
										  + IMG + " = N'" + chiNhanh.img + "', "
										  + MST + " = N'" + chiNhanh.mst + "', "
										  + LAT + " = " + chiNhanh.lat + ", "
										  + LNG + " = " + chiNhanh.lng + ", "
										  + RADIUS + " = " + chiNhanh.radius + ", "
										  + UPDATE_AT + " = " + dayNow.getTime() + ", "
										  + MODIFIED_BY + " = N'" + chiNhanh.modifiedBy + "', "
										  + GHI_CHU + " = N'" + chiNhanh.ghiChu + "', "
										  + TRANG_THAI + " = " + chiNhanh.trangThai;
			strSqlUpdate += " WHERE "
									+ ID + " = '" + chiNhanh.id + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlUpdate);

			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = String.valueOf(chiNhanh.id);
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}

	public static String delete(ChiNhanh chiNhanh) {
		String TAG = "TableChiNhanh-delete";
		String idKQ = "";
		String checkSum1 = "";
		try {
			Date dayNow = new Date();
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + UPDATE_AT + " = " + dayNow.getTime() + ", "
										  + MODIFIED_BY + " = N'" + chiNhanh.modifiedBy + "', "
										  + GHI_CHU + " = N'" + chiNhanh.ghiChu + "', "
										  + TRANG_THAI + " = -2 ";
			strSqlUpdate += " WHERE "
									+ ID + " = '" + chiNhanh.id + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlUpdate);

			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = String.valueOf(chiNhanh.id);
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}

	public static int count() {
		String TAG = "Table" + TABLE_NAME + "-count";
		int ketQua = 0;
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
