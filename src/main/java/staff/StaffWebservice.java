package staff;

import vn.vimass.utils.Data;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;

@Path("")
@Produces("application/json;charset=utf-8")
public class StaffWebservice {

	@Inject
	private HttpServletRequest request;

	@GET
	@Path("/test-staff")
	public String test2020() {

		if (request != null) {
			Data.ghiLogRequest("IP: " + request.getRemoteAddr());
			return request.getRemoteAddr();
		}
		return "Loi";
	}

	@GET
	@Path("/thong-tin-nhan-su")
	public String getNhanSu() {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.getAllNhanSu();

	}

	@GET
	@Path("/tong-quan")
	public String getTongQuan() {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.getOverview();

	}

	@GET
	@Path("/chenh-lech")
	public String getChenhLech(@QueryParam("tuNgay") long tuNgay,
							   @QueryParam("denNgay") long denNgay) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.getDifference(tuNgay, denNgay);

	}
	@GET
	@Path("/nhan-vien")
	public String getNhanVien(@QueryParam("id") int id,
							  @QueryParam("idChiNhanh") int idChiNhanh,
							  @QueryParam("idPhongBan") int idPhongBan,
							  @QueryParam("limit") int limit,
							  @QueryParam("offset") int offset) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.getNhanVien(id, idChiNhanh, idPhongBan, limit, offset);

	}

	@POST
	@Path("/nhan-vien")
	public String addNhanVien(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

			return StaffFunc.addNhanVien(input);
	}

	@POST
	@Path("/nhan-vien/cap-nhat")
	public String updateNhanVien(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.updateNhanVien(input);
	}

	@POST
	@Path("/nhan-vien/xoa")
	public String deleteNhanVien(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.deleteNhanVien(input);
	}

	@GET
	@Path("/chi-nhanh")
	public String getChiNhanh(@QueryParam("id") int id,
							  @QueryParam("limit") int limit,
							  @QueryParam("offset") int offset) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.getChiNhanh(id, limit, offset);
	}

	@POST
	@Path("/chi-nhanh")
	public String addChiNhanh(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.addChiNhanh(input);
	}

	@POST
	@Path("/chi-nhanh/cap-nhat")
	public String updateChiNhanh(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.updateChiNhanh(input);
	}

	@POST
	@Path("/chi-nhanh/xoa")
	public String deleteChiNhanh(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.deleteChiNhanh(input);
	}

	@GET
	@Path("/phong-ban")
	public String getPhongBan(@QueryParam("id") int id,
							  @QueryParam("idChiNhanh") int idChiNhanh,
							  @QueryParam("limit") int limit,
							  @QueryParam("offset") int offset) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.getPhongBan(id, idChiNhanh,  limit, offset);

	}

	@POST
	@Path("/phong-ban")
	public String addPhongBan(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.addPhongBan(input);
	}

	@POST
	@Path("/phong-ban/cap-nhat")
	public String updatePhongBan(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.updatePhongBan(input);
	}

	@POST
	@Path("/phong-ban/xoa")
	public String deletePhongBan(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.deletePhongBan(input);
	}

	@GET
	@Path("/chuc-vu")
	public String geChucVu(@QueryParam("id") int id,
						   @QueryParam("limit") int limit,
						   @QueryParam("offset") int offset) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.getChucVu(id, limit, offset);

	}

	@POST
	@Path("/chuc-vu")
	public String addChucVu(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.addChucVu(input);
	}

	@POST
	@Path("/chuc-vu/cap-nhat")
	public String updateChucVu(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.updateChucVu(input);
	}

	@POST
	@Path("/chuc-vu/xoa")
	public String deleteChucVu(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		return StaffFunc.deleteChucVu(input);
	}

}