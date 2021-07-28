package za.co.sbsa.docusign.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.sbsa.docusign.domain.SbsaCrypto;
import za.co.sbsa.docusign.repositories.SBSACryptoRepository;
import za.co.sbsa.dto.SBSACryptoDto;

@Service
public class SBSACryptoServiceImpl implements SBSACryptoService {

	@Autowired
	private SBSACryptoRepository sBSACryptoRepository;

	@Override
	public SBSACryptoDto retrieveSbsaCrypto(String bpId) throws Exception {
		try {
			SBSACryptoDto sbsaCryptoDto = null;
			List<SbsaCrypto> sbsaCryptos = sBSACryptoRepository.findBySigningBPId(bpId);
			for(SbsaCrypto sbsaCrypto : sbsaCryptos){
				if(sbsaCrypto.getState()!=null && sbsaCrypto.getState().equalsIgnoreCase("Available")){
					sbsaCryptoDto = new SBSACryptoDto();
					sbsaCryptoDto.setKeyId(sbsaCrypto.getKeyId());
					sbsaCryptoDto.setBpId(sbsaCrypto.getSigningBPId());
					sbsaCryptoDto.setHalfKey1(sbsaCrypto.getHalfKeyBlob());
					sbsaCryptoDto.setPublicKey(sbsaCrypto.getPublicKeyBlob());
				}
			}
			// TODO move sbsaCryptoDto to state "Available"
			return sbsaCryptoDto;
		} catch (Exception ex) {
			//TODO - Add the debug log
			throw ex;
		}
	}

}
