package mappers.dto;

import app.domain.model.SNSuser;

public class MapperSNSuser {

    public MapperSNSuser(){}

    public SNSuser toSNSuser(dtoSNSuser dto){
        return new SNSuser(dto.getName(), dto.getSex(),dto.getBirth(),dto.getResidenceAddress(),dto.getEmail(),dto.getPhoneNumber(),dto.getSNSnumber(),dto.getCcNumber());
    }

    public dtoSNSuser toDTO(dtoSNSuser user){
        return new dtoSNSuser(user.getName(), user.getSex(),user.getBirth(),user.getResidenceAddress(),user.getEmail(),user.getPhoneNumber(),user.getSNSnumber(),user.getCcNumber());
    }
}
