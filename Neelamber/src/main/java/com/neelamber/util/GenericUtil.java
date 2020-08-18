package com.neelamber.util;

import com.neelamber.bean.Resident;
import com.neelamber.bean.ResidentDTO;

public class GenericUtil {
	
	public ResidentDTO convertIntoDTO(Resident resident) {
		ResidentDTO dto = new ResidentDTO();
		dto.setName(resident.getName());
		dto.setCarNo(resident.getCarNo());
		dto.setEmail(resident.getEmail());
		dto.setFlatNo(resident.getFlatNo());
		dto.setId(resident.getId());
		dto.setTelephone1(resident.getTelephone1());
		return dto;
	}

}
