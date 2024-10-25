package iuh.daoImpl;

import java.util.List;

import iuh.dao.PhongBanDAO;
import iuh.models.PhongBan;
import jakarta.persistence.EntityManager;

public class PhongBanDAOIml implements PhongBanDAO {
	private EntityManager entityManager;

	public PhongBanDAOIml(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<PhongBan> getAllPhongBan() {
		try {
			return entityManager.createQuery("FROM PhongBan", PhongBan.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}



	@Override
	public PhongBan findById(long id) {
		 try {
	            return entityManager.find(PhongBan.class, id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	}

}
