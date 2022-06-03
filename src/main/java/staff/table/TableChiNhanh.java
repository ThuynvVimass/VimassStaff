package staff.table;

import staff.object.ChiNhanh;
import vn.vimass.csdl.utilDB.DbUtil;
import vn.vimass.utils.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TableChiNhanh {

	public static final String TABLE_NAME = "chi_nhanh";
	public static final String ID = "id";
	public static final String TEN = "ten";
	public static final String SDT = "sdt";
	public static final String DIA_CHI = "dia_chi";
	public static final String IMG = "img";
	public static final String MST = "mst";
	public static final String GHI_CHU = "ghi_chu";
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
				chiNhanh.trangThai = rs.getInt(TRANG_THAI);
				ketQua.add(chiNhanh);
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
			String strSqlInsert = "INSERT INTO " + TABLE_NAME + ""
										  + " ("
										  + TEN + ", "
										  + SDT + ", "
										  + DIA_CHI + ", "
										  + IMG + ", "
										  + MST + ", "
										  + GHI_CHU + ", "
										  + TRANG_THAI
										  + " ) VALUES ("
										  + "N'" + chiNhanh.ten + "',"
										  + "N'" + chiNhanh.sdt + "',"
										  + "N'" + chiNhanh.diaChi + "',"
										  + "N'" + chiNhanh.img + "',"
										  + "N'" + chiNhanh.mst + "',"
										  + "N'" + chiNhanh.ghiChu + "',"
										  + chiNhanh.trangThai
										  + ");";

			Data.ghiLogRequest(TAG + "\tinsert:" + strSqlInsert);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlInsert);
			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = "chiNhanh.id";
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
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + TEN + " = N'" + chiNhanh.ten + "', "
										  + SDT + " = N'" + chiNhanh.sdt + "', "
										  + DIA_CHI + " = N'" + chiNhanh.diaChi + "', "
										  + IMG + " = N'" + chiNhanh.img + "', "
										  + MST + " = N'" + chiNhanh.mst + "', "
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
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
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
}
