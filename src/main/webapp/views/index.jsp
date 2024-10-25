<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Employees</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body>
	<c:if test="${empty param.action || param.action != 'list'}">
		<c:redirect url="/employees?action=list" />
	</c:if>

	<div class="container border border-2">
		<div class="nav bg-success p-2 ">
			<h5 class="text-white mx-auto">15-NguyenThiThuHong-21097741</h5>
		</div>
		<div class="d-flex justify-content-center">
			<a class="link-opacity-50-hover me-3"
				href="${pageContext.request.contextPath}/employees?action=list">Danh
				sách nhân viên</a> <a class="link-opacity-50-hover "
				href="${pageContext.request.contextPath}/employees?action=new">Thêm
				mới nhân viên</a>

		</div>
		<h3 class="text-center mt-3 mb-5">DANH SÁCH NHÂN VIÊN</h3>

		<table class="table table-bordered text-center">
			<thead>
				<tr>
					<th>Tên phòng ban</th>
					<th>MaNV</th>
					<th>TenNV</th>
					<th>Email</th>
					<th>DiaChi</th>
					<th>DienThoai</th>
					<th></th>
				</tr>

			</thead>
			<tbody>
				<c:if test="${empty listNhanVien}">
					<tr>
						<td colspan="7">No Employee found</td>
					</tr>
				</c:if>
				<c:if test="${not empty listNhanVien}">
					<c:forEach var="employee" items="${listNhanVien}">
						<tr>
							<td>${employee.phongBan != null ? employee.phongBan.tenPhongBan : 'Không có phòng ban'}</td>
							<td>${employee.maNV}</td>
							<td>${employee.tenNV}</td>
							<td>${employee.email}</td>
							<td>${employee.diaChi}</td>
							<td>${employee.dienThoai}</td>
							<td>
								<form
									action="${pageContext.request.contextPath}/employees?action=delete"
									method="post" style="display: inline;"
									onsubmit="return confirm('Bạn có chắc chắn muốn xóa nhân viên này?');">
									<input type="hidden" name="maNV" value="${employee.maNV}"> <input
										type="submit" class="btn btn-danger" value="Xóa">
								</form>
						</tr>
					</c:forEach>


				</c:if>


			</tbody>
		</table>

	</div>
</body>
</html>