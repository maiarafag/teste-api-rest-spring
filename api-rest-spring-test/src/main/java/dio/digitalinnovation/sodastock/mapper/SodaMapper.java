package dio.digitalinnovation.sodastock.mapper;

import dio.digitalinnovation.sodastock.dto.SodaDTO;
import dio.digitalinnovation.sodastock.entity.Soda;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SodaMapper {

    SodaMapper INSTANCE = Mappers.getMapper(SodaMapper.class);

    Soda toModel(SodaDTO sodaDTO);

    SodaDTO toDTO(Soda soda);
}
