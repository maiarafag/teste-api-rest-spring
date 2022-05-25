package dio.digitalinnovation.sodastock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import dio.digitalinnovation.sodastock.dto.SodaDTO;
import dio.digitalinnovation.sodastock.dto.QuantityDTO;
import dio.digitalinnovation.sodastock.exception.SodaAlreadyRegisteredException;
import dio.digitalinnovation.sodastock.exception.SodaNotFoundException;
import dio.digitalinnovation.sodastock.exception.SodaStockExceededException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api("Manages soda stock")
public interface SodaControllerDocs {

    @ApiOperation(value = "Soda creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success soda creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    SodaDTO createSoda(SodaDTO sodaDTO) throws SodaAlreadyRegisteredException;

    @ApiOperation(value = "Returns soda found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success soda found in the system"),
            @ApiResponse(code = 404, message = "Soda with given name not found.")
    })
    SodaDTO findByName(@PathVariable String name) throws SodaNotFoundException;

    @ApiOperation(value = "Returns a list of all soda registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all sodas registered in the system"),
    })
    List<SodaDTO> listSodas();

    @ApiOperation(value = "Delete a soda found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success soda deleted in the system"),
            @ApiResponse(code = 404, message = "Soda with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws SodaNotFoundException;
}
