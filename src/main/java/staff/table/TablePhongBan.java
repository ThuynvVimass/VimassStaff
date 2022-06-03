package staff.table;

import staff.object.PhongBan;
import vn.vimass.csdl.utilDB.DbUtil;
import vn.vimass.utils.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TablePhongBan {

	public static final String TABLE_NAME = "phong_ban";
	public static final String ID = "id";
	public static final String TEN = "ten";
	public static final String ID_CHI_NHANH = "id_chi_nhanh";
	public static final String GHI_CHU = "ghi_chu";
	public static final String TRANG_THAI = "trang_thai";

	public static ArrayList<PhongBan> get(int idInput) {
		return get(idInput, 0, 0, 0);
	}

	public static ArrayList<PhongBan> get(int idInput, int idChiNhanh, int limit, int offset) {
		String TAG = "TablePhongBan-get";
		ArrayList<PhongBan> ketQua = new ArrayList<>();
		try {
			String strSqlSelect = "SELECT * FROM " + TABLE_NAME;
			strSqlSelect += "  pb join " + TableChiNhanh.TABLE_NAME + " cn on pb." + ID_CHI_NHANH + " = cn." + TableChiNhanh.ID +
									" where ((pb.id = " + idInput + ") or (" + idInput + " = 0)) and ((id_chi_nhanh = " +
									idChiNhanh + ") or (" + idChiNhanh + " = 0)) ";

			if (limit != 0) strSqlSelect += " LIMIT " + offset + ", " + limit + ";";
			else strSqlSelect += ";";
			Data.ghiLogRequest(TAG + "\tselect:" + strSqlSelect);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			Statement statement = connect.createStatement();

			ResultSet rs = statement.executeQuery(strSqlSelect);
			while (rs.next()) {
				PhongBan phongBan = new PhongBan();
				phongBan.id = rs.getInt(ID);
				phongBan.ten = rs.getString(TEN);
				phongBan.idChiNhanh = rs.getInt(ID_CHI_NHANH);
				phongBan.ghiChu = rs.getString(GHI_CHU);
				phongBan.trangThai = rs.getInt(TRANG_THAI);
				ketQua.add(phongBan);
			}
			Data.ghiLogRequest(TAG + "\tkq:" + ketQua);

		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return ketQua;
	}

	public static String add(PhongBan phongBan) {
		String TAG = "TablePhongBan-addl";
		String idKQ = "";
		try {
			String strSqlInsert = "INSERT INTO " + TABLE_NAME + ""
										  + " ("
										  + TEN + ", "
										  + GHI_CHU + ", "
										  + ID_CHI_NHANH + ", "
										  + TRANG_THAI
										  + " ) VALUES ("
										  + "N'" + phongBan.ten + "',"
										  + "N'" + phongBan.ghiChu + "',"
										  + phongBan.idChiNhanh + ","
										  + phongBan.trangThai
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

	public static String update(PhongBan phongBan) {
		String TAG = "TablePhongBan-update";
		String idKQ = "";
		String checkSum1 = "";
		try {
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + TEN + " = N'" + phongBan.ten + "', "
										  + GHI_CHU + " = N'" + phongBan.ghiChu + "', "
										  + ID_CHI_NHANH + " = " + phongBan.idChiNhanh + ", " +
										  TRANG_THAI + " = " + phongBan.trangThai;
			strSqlUpdate += " WHERE "
									+ ID + " = '" + phongBan.id + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlUpdate);

			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = String.valueOf(phongBan.id);
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}

	public static String delete(PhongBan phongBan) {
		String TAG = "TablePhongBan-delete";
		String idKQ = "";
		String checkSum1 = "";
		try {
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
										  + GHI_CHU + " = N'" + phongBan.ghiChu + "', "
										  + TRANG_THAI + " = -2 ";
			strSqlUpdate += " WHERE "
									+ ID + " = '" + phongBan.id + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);

			Connection connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			PreparedStatement statement = connect.prepareStatement(strSqlUpdate);

			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = String.valueOf(phongBan.id);
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}
}
