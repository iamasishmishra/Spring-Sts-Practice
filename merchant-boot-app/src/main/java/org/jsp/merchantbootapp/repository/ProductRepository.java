
package org.jsp.merchantbootapp.repository;

import java.util.List;

import org.jsp.merchantbootapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByBrand(String brand);

	List<Product> findByName(String name);

	List<Product> findByCategory(String category);

	@Query("select m.products from Merchant m where m.id=?1")
	List<Product> findByMerchantId(int id);

	@Query("select p from Product p where p.cost between ?1 and ?2")
	List<Product> findBetween(double min, double max);

	@Query("select m.products from Merchant m where m.phone=?1 and m.password=?2")
	List<Product> findByMerchantPhoneAndPassword(long phone, String password);
}
