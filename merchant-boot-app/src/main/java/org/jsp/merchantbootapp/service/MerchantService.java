
package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setMessage("Merchant Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(merchantDao.saveMerchant(merchant));
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recMerchant.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("Invalid Merchant Id");
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setPhone(merchant.getPhone());
			structure.setData(merchantDao.saveMerchant(dbMerchant));
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new MerchantNotFoundException("cannot Update as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findAllMerchants() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		structure.setData(merchantDao.findAll());
		structure.setMessage("List of All Merchants");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<String>> deleteMerchant(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		if (recMerchant.isPresent()) {
			structure.setData("Merchant Found");
			structure.setMessage("Merchant deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			merchantDao.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData("Merchant Not Found");
		structure.setMessage("Cannot delete Merchant as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(String name) {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		List<Merchant> merchants = merchantDao.findByName(name);
		structure.setData(merchants);
		if (merchants.isEmpty()) {
			structure.setMessage("No Merchant Found with entered name");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
		structure.setMessage("List of Merchants with entered name");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<Merchant>> findByPhone(long phone) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findByPhone(phone);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recMerchant.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("Invalid Phone Number");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(phone, password);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found and Verification Succesfull");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recMerchant.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("Invalid Phone Number or password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(String email, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(email, password);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recMerchant.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("Invalid Email Id or password");
	}
}
