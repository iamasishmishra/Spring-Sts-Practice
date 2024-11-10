package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository merchantRepository;

	public Merchant saveMerchant(Merchant merchant) {
		return merchantRepository.save(merchant);
	}

	public Optional<Merchant> findById(int id) {
		return merchantRepository.findById(id);
	}

	public void delete(int id) {
		merchantRepository.deleteById(id);
	}

	public List<Merchant> findAll() {
		return merchantRepository.findAll();
	}

	public List<Merchant> findByName(String name) {
		return merchantRepository.findByName(name);
	}

	public Optional<Merchant> findByPhone(long phone) {
		return merchantRepository.findByPhone(phone);
	}

	public Optional<Merchant> verifyMerchant(long phone, String password) {
		return merchantRepository.findByPhoneAndPassword(phone, password);
	}

	public Optional<Merchant> verifyMerchant(String email, String password) {
		return merchantRepository.verifyByEmailAndPassword(email, password);
	}
}
