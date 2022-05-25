package dio.digitalinnovation.sodastock.service;

import lombok.AllArgsConstructor;
import dio.digitalinnovation.sodastock.dto.SodaDTO;
import dio.digitalinnovation.sodastock.entity.Soda;
import dio.digitalinnovation.sodastock.exception.SodaAlreadyRegisteredException;
import dio.digitalinnovation.sodastock.exception.SodaNotFoundException;
import dio.digitalinnovation.sodastock.exception.SodaStockExceededException;
import dio.digitalinnovation.sodastock.mapper.SodaMapper;
import dio.digitalinnovation.sodastock.service.repository.SodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SodaService {

    private final SodaRepository sodaRepository;
    private final SodaMapper sodaMapper = SodaMapper.INSTANCE;

    public SodaDTO createSoda(SodaDTO sodaDTO) throws SodaAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(sodaDTO.getName());
        Soda soda = sodaMapper.toModel(sodaDTO);
        Soda savedSoda = sodaRepository.save(soda);
        return sodaMapper.toDTO(savedSoda);
    }

    public SodaDTO findByName(String name) throws SodaNotFoundException {
        Soda foundSoda = sodaRepository.findByName(name)
                .orElseThrow(() -> new SodaNotFoundException(name));
        return sodaMapper.toDTO(foundSoda);
    }

    public List<SodaDTO> listAll() {
        return sodaRepository.findAll()
                .stream()
                .map(sodaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws SodaNotFoundException {
        verifyIfExists(id);
        sodaRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws SodaAlreadyRegisteredException {
        Optional<Soda> optSavedSoda = sodaRepository.findByName(name);
        if (optSavedSoda.isPresent()) {
            throw new SodaAlreadyRegisteredException(name);
        }
    }

    private Soda verifyIfExists(Long id) throws SodaNotFoundException {
        return sodaRepository.findById(id)
                .orElseThrow(() -> new SodaNotFoundException(id));
    }

    public SodaDTO increment(Long id, int quantityToIncrement) throws SodaNotFoundException, SodaStockExceededException {
        Soda sodaToIncrementStock = verifyIfExists(id);
        int quantityAfterIncrement = quantityToIncrement + sodaToIncrementStock.getQuantity();
        if (quantityAfterIncrement <= sodaToIncrementStock.getMax()) {
            sodaToIncrementStock.setQuantity(sodaToIncrementStock.getQuantity() + quantityToIncrement);
            Soda incrementedSodaStock = sodaRepository.save(sodaToIncrementStock);
            return sodaMapper.toDTO(incrementedSodaStock);
        }
        throw new SodaStockExceededException(id, quantityToIncrement);
    }
}
