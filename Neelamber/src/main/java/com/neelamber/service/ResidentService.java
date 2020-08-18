package com.neelamber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neelamber.bean.Resident;
import com.neelamber.bean.ResidentDTO;
import com.neelamber.dao.GenericDao;
import com.neelamber.exception.ResourceNotFoundException;
import com.neelamber.util.GenericUtil;

@Service
public class ResidentService {

	@Autowired
	private GenericDao dao;

	public List<Resident> getAllProducts() {
		return dao.findAll();
	}

	public ResidentDTO getResidentByFlatNo(String flatNo) throws ResourceNotFoundException {
		Resident resident = dao.getResidentByFlatNoIgnoreCase(flatNo)
				.orElseThrow(() -> new ResourceNotFoundException("No Resident found for flat no:" + flatNo));
		GenericUtil util = new GenericUtil();
		return util.convertIntoDTO(resident);
	}

	public Resident addResident(Resident r) {
		return dao.save(r);
	}
	
	public Resident updateResident(Integer id, ResidentDTO res) {
		Resident resident = dao.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Resident found"));
		if(resident != null) {
			resident.setName(res.getName());
			resident.setEmail(res.getEmail());
			resident.setTelephone1(res.getTelephone1());
			resident.setTelephone2(res.getTelephone2());
			resident.setCarNo(res.getCarNo());
			return dao.save(resident);
		}
			return null;
	}

}
