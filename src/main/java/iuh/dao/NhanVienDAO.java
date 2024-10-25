package iuh.dao;

import java.util.List;

import iuh.models.NhanVien;

public interface NhanVienDAO {
	public List<NhanVien> findAll();
	public NhanVien add(NhanVien nhanVien);
	public boolean delete(long id);

}
