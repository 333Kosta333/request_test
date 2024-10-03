package ru.sber.csportal.controller.catalog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sber.csportal.dto.catalog.ContractUiDto;
import ru.sber.csportal.service.catalog.ContractServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for the {@link ContractControllerImpl}
 */
@ExtendWith(MockitoExtension.class)
public class ContractControllerTest {

    @Mock
    private ContractServiceImpl contractService;

    @InjectMocks
    private ContractControllerImpl contractController;

    @Test
    @DisplayName("Найти все договоры")
    public void findAll_ReturnsContracts() throws Exception {
        //given
        List<ContractUiDto> contracts = List
                .of(new ContractUiDto(UUID.randomUUID(),
                                "55544433322211",
                                "055544433322211"),
                        new ContractUiDto(UUID.randomUUID(),
                                "55544433322211",
                                "055544433322211"));
        //when
        when(this.contractService.findAll()).thenReturn(Optional.of(contracts));
        //then
        ResponseEntity<List<ContractUiDto>> all = this.contractController.findAll();
        assertEquals(HttpStatus.OK, all.getStatusCode());
        assertEquals(contracts, all.getBody());
    }

    @Test
    @DisplayName("Найти договор по номеру УВХД")
    public void findByUvhdNumber() throws Exception {
        //given
        List<ContractUiDto> contracts = List.of(new ContractUiDto(UUID.randomUUID(),
                "55544433322211",
                "055544433322211"));
        //when
        when(this.contractService.findByUvhdNumber("555444333222111"))
                .thenReturn(Optional.of(contracts));
        //then
        ResponseEntity<List<ContractUiDto>> byUvhdNumber = this.contractController.findByUvhdNumber("555444333222111");
        assertEquals(HttpStatus.OK, byUvhdNumber.getStatusCode());
        assertEquals(contracts, byUvhdNumber.getBody());
    }

    @Test
    @DisplayName("Создать договор")
    public void create() throws Exception {
        //given
        ContractUiDto contract = new ContractUiDto(null, "555444333222111",
                "0555444333222111");
        //when
        when(this.contractService.createUi(contract)).thenReturn(contract);
        //then
        ResponseEntity<ContractUiDto> create = this.contractController.create(contract);
        assertEquals(HttpStatus.OK, create.getStatusCode());
        assertEquals(contract, create.getBody());
    }
}
