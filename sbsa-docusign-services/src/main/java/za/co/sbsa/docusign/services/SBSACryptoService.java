package za.co.sbsa.docusign.services;

import za.co.sbsa.dto.SBSACryptoDto;

public interface SBSACryptoService {

	public SBSACryptoDto retrieveSbsaCrypto(String bpId) throws Exception;
}
