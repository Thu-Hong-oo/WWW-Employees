package iuh.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PhongBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maPhongban;

    @Column(name = "TENPHONGBAN")
    private String tenPhongBan;

    @OneToMany(mappedBy = "phongBan")
    private List<NhanVien> nhanVien; 
    // Getter và Setter
    public long getMaPhongban() {
        return maPhongban;
    }

    public void setMaPhongban(long maPhongban) {
        this.maPhongban = maPhongban;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public List<NhanVien> getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(List<NhanVien> nhanVien) {
        this.nhanVien = nhanVien;
    }

	@Override
	public String toString() {
		return "PhongBan [maPhongban=" + maPhongban + ", tenPhongBan=" + tenPhongBan + "]";
	}
    
    
}
