<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddAndEdit</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body>
	<div class="container border border-2">
		<div class="nav bg-success p-2 ">
			<h5 class="text-white mx-auto">15-NguyenThiThuHong-21097741</h5>
		</div>
		<a href="${pageContext.request.contextPath}/employees?action=list"
			class="link opacity-50-hover text-left">Danh sách</a>

		<!--     <c:if test="${not empty errors}">
            <div class="alert alert-danger" role="alert">
                <c:out value="${errors}" escapeXml="false" />
            </div>
        </c:if>
         -->
		<c:if test="${not empty errors}">
			<div class="alert alert-danger" role="alert"">
				<c:forEach var="entry" items="${errors}">
					<div>${entry.key}:${entry.value}</div>
				</c:forEach>
			</div>
		</c:if>

		<h3 class="text-center mt-5 mb-3">${param.action == 'edit' ? 'SỬA NHÂN VIÊN' : 'THÊM NHÂN VIÊN'}
		</h3>

		<!-- form -->
		<form action="${pageContext.request.contextPath}/employees"
			method="POST" class="mx-auto col-10 from-label border p-3">
			<input type="hidden" name="action"
				value="${param.action == 'edit' ? 'update' : 'add'}" />

			<!-- Tên nhân viên -->
			<div class="row mb-2">
				<label for="" class="col-sm-2 col-form-label">Tên nhân viên</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="tenNhanVien"
						value="${nhanVien.tenNV}" />
					<!-- <c:if test="${not empty errors['tenNV']}">
                        <small class="text-danger">${errors['tenNV']}</small>
                    </c:if> -->
				</div>
			</div>

			<!-- Email -->
			<div class="row mb-2">
				<label for="" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="email"
						value="${not empty nhanVien ? nhanVien.email : ''}" />
					<!--  <c:if test="${not empty errors['email']}">
                        <small class="text-danger">${errors['email']}</small>
                    </c:if> -->
				</div>
			</div>

			<!-- Địa chỉ -->
			<div class="row mb-2">
				<label for="" class="col-sm-2 col-form-label">Địa chỉ</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="diaChi"
						value="${not empty nhanVien ? nhanVien.diaChi : ''}" />
					<!--  <c:if test="${not empty errors['diaChi']}">
                        <small class="text-danger">${errors['diaChi']}</small>
                    </c:if> -->
				</div>
			</div>

			<!-- Điện thoại -->
			<div class="row mb-2">
				<label for="" class="col-sm-2 col-form-label">Điện thoại</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="dienThoai"
						value="${not empty nhanVien ? nhanVien.dienThoai : ''}" />
					<!-- <c:if test="${not empty errors['dienThoai']}">
                        <small class="text-danger">${errors['dienThoai']}</small>
                    </c:if> -->
				</div>
			</div>

			<!-- Phòng ban -->
			<div class="row mb-2">
				<label for="" class="col-sm-2 col-form-label">Phòng ban</label>
				<div class="col-sm-10">
					<select name="phongBanId" class="form-select">
						<c:forEach var="phong" items="${listPhongBan}">
							<option value="${phong.maPhongban}"
								${not empty nhanVien && nhanVien.phongBan.maPhongBan == phong.maPhongan ? 'selected' : ''}>
								${phong.tenPhongBan}</option>
						</c:forEach>
					</select>
					<!-- <c:if test="${not empty errors['phongBanId']}">
                        <small class="text-danger">${errors['phongBanId']}</small>
                    </c:if> -->
				</div>
			</div>

			<div class="row mt-5 col-2 mx-auto">
				<input type="submit" class="btn btn-success"
					value="${param.action == 'edit' ? 'Cập Nhật' : 'Thêm'}" />
			</div>
		</form>
	</div>
</body>
</html>
