package iuh.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MANV")
	private long maNV;
	
	@Column(name = "TENNV")
	@NotBlank(message = "Tên nhân viên không được để trống")
	@Size(max = 50, message = "Tên không quá 50 ký tự")
	private String tenNV;
	
	@Column(name = "EMAIL", unique = true)
	@NotBlank(message = "Email không được để trống")
//	@Pattern(regexp = "^[a-zA-Z0-9]+@gmail.com$", message = "Email phải có định dạng như abcsd@gmail.com")
	@Email
	private String email;
	
	@Column(name = "DIACHI")
	private String diaChi;
	
	@Column(name = "DIENTHOAI")
	@NotBlank(message = "Điện thoại không được để trống")
	private String dienThoai;
	
	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maPhongBan")
	private PhongBan phongBan;

	public NhanVien() {
		
	}

	public NhanVien(String tenNV, String email, String diaChi, String dienThoai) {
		super();
		this.tenNV = tenNV;
		this.email = email;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
	}

	public long getMaNV() {
		return maNV;
	}

	public void setMaNV(long maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", email=" + email + ", diaChi=" + diaChi
				+ ", dienThoai=" + dienThoai + ", phongBan=" + phongBan + "]";
	}



	
}
