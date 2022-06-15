package staff.object;

import java.util.ArrayList;

public class ChenhLech {

	public long tuNgay;
	public long denNgay;

	public int soNhanVienNghiViec;
	public int soNhanVienMoiVao;
	public int soLuongNhanVienThayDoi;
	public ArrayList<NhanVien> nhanVienNghiViecs = new ArrayList<>();
	public ArrayList<NhanVien> nhanVienMoiVaos = new ArrayList<>();

	public ChenhLech(long tuNgay, long denNgay, int soNhanVienNghiViec, int soNhanVienMoiVao,
					 int soLuongNhanVienThayDoi, ArrayList<NhanVien> nhanVienNghiViecs, ArrayList<NhanVien> nhanVienMoiVaos) {
		this.tuNgay = tuNgay;
		this.denNgay = denNgay;
		this.soNhanVienNghiViec = soNhanVienNghiViec;
		this.soNhanVienMoiVao = soNhanVienMoiVao;
		this.soLuongNhanVienThayDoi = soLuongNhanVienThayDoi;
		this.nhanVienNghiViecs = nhanVienNghiViecs;
		this.nhanVienMoiVaos = nhanVienMoiVaos;
	}
}
