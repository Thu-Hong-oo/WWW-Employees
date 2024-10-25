package iuh.dao;

import java.util.List;

import iuh.models.PhongBan;

public interface PhongBanDAO {
	public List<PhongBan> getAllPhongBan();
	public PhongBan findById(long id);

}
