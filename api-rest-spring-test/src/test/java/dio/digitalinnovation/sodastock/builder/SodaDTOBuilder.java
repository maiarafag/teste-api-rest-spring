package dio.digitalinnovation.sodastock.builder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Builder;
import dio.digitalinnovation.sodastock.dto.SodaDTO;
import dio.digitalinnovation.sodastock.enums.SodaType;

@Builder
public class SodaDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Guarana Antarctica";

    @Builder.Default
    private String brand = "Ambev";

    @Builder.Default
    private int max = 40;

    @Builder.Default
    private int quantity = 15;

    @Builder.Default
    private SodaType type = SodaType.LAGER;

    public SodaDTO toSodaDTO() {
        return new SodaDTO(id,
                name,
                brand,
                max,
                quantity,
                type);
    }
}
