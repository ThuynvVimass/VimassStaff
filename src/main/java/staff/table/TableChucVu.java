package staff.table;

import staff.object.ChucVu;
import vn.vimass.csdl.utilDB.DbUtil;
import vn.vimass.utils.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TableChucVu {

	public static final String TABLE_NAME = "chuc_vu";
	public static final String ID = "id";
	public static final String TEN = "ten";
	public static final String GHI_CHU = "ghi_chu";
	public static final String TRANG_THAI = "trang_thai";

	public static ArrayList<ChucVu> get(int idInput) {
		return get(idInput, 0, 0);
	}
	public static ArrayList<ChucVu> get(int idInput, int limit, int offset) {
		String TAG = "TableChucVu-lay";
		ArrayList<ChucVu> ketQua = new ArrayList<>();
		try {
			String strSqlSelect = "SELECT * FROM " + TABLE_NAME;
			if (idInput != 0) strSqlSelect += " WHERE " + "(" + TableChiNhanh.ID + " =  " + idInput + ")";
			if (limit != 0) strSqlSelect += " LIMIT " + offset + ", " + limit + ";";
			else strSqlSelect += ";";
			Data.ghiLogRequest(TAG + "\tselect:" + strSqlSelect);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			Statement statement = connect.createStatement();

			ResultSet rs = statement.executeQuery(strSqlSelect);
			while (rs.next()) {
				ChucVu chucVu = new ChucVu();
				chucVu.id = rs.getInt(ID);
				chucVu.ten = rs.getString(TEN);
				chucVu.ghiChu = rs.getString(GHI_CHU);
				chucVu.trangThai = rs.getInt(TRANG_THAI);
				ketQua.add(chucVu);
			}
			Data.ghiLogRequest(TAG + "\tkq:" + ketQua);

		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return ketQua;
	}

	public static String add(ChucVu chucVu) {
		String TAG = "TableChucVu-add";
		String idKQ = "";
		try {
			String strSqlInsert = "INSERT INTO " + TABLE_NAME + ""
										  + " ("
										  + TEN + ", "
										  + GHI_CHU +  ", "
										  + TRANG_THAI
										  + " ) VALUES ("
										  + "N'" + chucVu.ten + "',"
										  + "N'" + chucVu.ghiChu + "',"
										  + chucVu.trangThai
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

	public static String update(ChucVu chucVu) {
		String TAG = "TableChucVu-update";
		String idKQ = "";
		String checkSum1 = "";
		try {
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + GHI_CHU + " = N'" + chucVu.ghiChu + "', "
										  + TRANG_THAI + " = " + chucVu.trangThai + ", "
										  + TEN + " = N'" + chucVu.ten + "'";
			strSqlUpdate += " WHERE "
									+ ID + " = '" + chucVu.id + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlUpdate);

			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = String.valueOf(chucVu.id);
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}

	public static String delete(ChucVu chucVu) {
		String TAG = "TableChucVu-delete";
		String idKQ = "";
		String checkSum1 = "";
		try {
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + GHI_CHU + " = N'" + chucVu.ghiChu + "', "
										  + TRANG_THAI + " = -2 ";
			strSqlUpdate += " WHERE "
									+ ID + " = '" + chucVu.id + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlUpdate);

			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = String.valueOf(chucVu.id);
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}
}

