package iuh.daoImpl;

import java.util.List;

import iuh.dao.NhanVienDAO;
import iuh.models.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class NhanVienDAOIml implements NhanVienDAO {
	private EntityManager entityManager;

	public NhanVienDAOIml(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<NhanVien> findAll() {
		try {
			return entityManager.createQuery("SELECT nv FROM NhanVien nv JOIN FETCH nv.phongBan").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public NhanVien add(NhanVien nhanVien) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(nhanVien);
			transaction.commit();
			return nhanVien;
			
		} catch (Exception e) {
			e.printStackTrace();
			if(transaction!=null &&transaction.isActive()) {
				transaction.rollback();
			}
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
	    EntityTransaction transaction = null;
	    try {
	        transaction = entityManager.getTransaction();
	        transaction.begin(); // Bắt đầu giao dịch

	        NhanVien nhanVien = entityManager.find(NhanVien.class, id);
	        if (nhanVien != null) {
	            entityManager.remove(entityManager.contains(nhanVien) ? nhanVien : entityManager.merge(nhanVien));
	            transaction.commit(); // Cam kết giao dịch
	            return true;
	        } else {
	            System.out.println("Không tìm thấy nhân viên với ID: " + id);
	            return false; // Không tìm thấy nhân viên
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // In ra lỗi để chẩn đoán
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback(); // Hoàn tác giao dịch nếu có lỗi
	        }
	    }
	    return false; // Trả về false nếu không thành công
	}

}
