package iuh.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import iuh.dao.NhanVienDAO;
import iuh.dao.PhongBanDAO;
import iuh.daoImpl.PhongBanDAOIml;
import iuh.daoImpl.NhanVienDAOIml;
import iuh.models.NhanVien;
import iuh.models.PhongBan;
import iuh.utils.EntityManagerFactoryUtils;

/**
 * Servlet implementation class NhanVienController
 */
@WebServlet(urlPatterns = "/employees")
public class NhanVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactoryUtils factoryUtils;
	private NhanVienDAO nhanVienDAO;
	private PhongBanDAO phongBanDAO;
	private Validator validator;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NhanVienController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.factoryUtils = new EntityManagerFactoryUtils();
		this.nhanVienDAO = new NhanVienDAOIml(this.factoryUtils.getEntityManager());
		this.phongBanDAO = new PhongBanDAOIml(this.factoryUtils.getEntityManager());
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.factoryUtils.close();
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet is called");
		String action = request.getParameter("action") != null ? request.getParameter("action") : "list";
		switch (action) {
		case "new":
			showAddForm(request, response);
			break;
		case "edit":
			showEditForm(request, response);
			break;
		default:
			listEmployees(request, response);
			break;
		}
	}

	private void listEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<NhanVien> listNhanVien = nhanVienDAO.findAll();
		System.out.println(listNhanVien);
		System.out.println("da chay list");
		request.setAttribute("listNhanVien", listNhanVien);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/index.jsp");
		dispatcher.forward(request, response);

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<PhongBan> listPhongBan = phongBanDAO.getAllPhongBan();
		System.out.println(listPhongBan);
		request.setAttribute("listPhongBan", listPhongBan);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/addAndEdit.jsp");
		dispatcher.forward(request, response);

	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<PhongBan> listPhongBan = phongBanDAO.getAllPhongBan();
		System.out.println(listPhongBan);
		request.setAttribute("listPhongBan", listPhongBan);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/addAndEdit.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		switch (action) {
		case "add":
			addEmployee(request, response);
			break;
		case "delete":
			deleteEmployee(request, response);
			break;
		case "update":
			editEmployee(request, response);
		default:
			doGet(request, response);
		}

	}

	private void editEmployee(HttpServletRequest request, HttpServletResponse response) {
		String tenNhanVien = request.getParameter("tenNhanVien");
		String email = request.getParameter("email");
		String diaChi = request.getParameter("diahChi");
		String dienThoai = request.getParameter("dienThoai");

	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		NhanVien nv = new NhanVien();
		String tenNhanVien = request.getParameter("tenNhanVien");
		String email = request.getParameter("email");
		String diaChi = request.getParameter("diaChi");
		String dienThoai = request.getParameter("dienThoai");
		String phongBanId = request.getParameter("phongBanId");

		// Gán giá trị cho đối tượng NhanVien
		nv.setTenNV(tenNhanVien);
		nv.setEmail(email);
		nv.setDiaChi(diaChi);
		nv.setDienThoai(dienThoai);

		Set<ConstraintViolation<NhanVien>> violations = validator.validate(nv);
		if (violations.isEmpty()) {
			PhongBan phongBan = phongBanDAO.findById(Long.parseLong(phongBanId));

			nv.setPhongBan(phongBan);
			this.nhanVienDAO.add(nv);
			response.sendRedirect(request.getContextPath() + "/employees?action=list");
		} else {
			// Tạo map để lưu các lỗi theo từng trường
			Map<String, String> errors = new HashMap<>();
			for (ConstraintViolation<NhanVien> violation : violations) {
				String field = violation.getPropertyPath().toString();
				String message = violation.getMessage();
				errors.put(field, message); // Thêm lỗi vào map
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/addAndEdit.jsp");
//			StringBuilder errors = new StringBuilder();
//			violations.forEach(violation -> {
//				errors.append(violation.getPropertyPath() + ": " + violation.getMessage());
//				errors.append("<br />");
//			});

			// Gửi các lỗi và giá trị nhập trước đó về form
			request.setAttribute("errors", errors);
			request.setAttribute("nhanVien", nv);
			dispatcher.forward(request, response);

		}

	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long maNhanVien = Long.parseLong(request.getParameter("maNV"));
		 System.out.println(maNhanVien);
	    if (nhanVienDAO.delete(maNhanVien)) {
	        // Xóa thành công, chuyển hướng
	        response.sendRedirect(request.getContextPath() + "/employees?action=list");
	    } else {
	        // Xóa không thành công, có thể hiển thị thông báo lỗi
	        request.setAttribute("errorMessage", "Không thể xóa nhân viên. Vui lòng thử lại.");
	   
	    }

	}

}
