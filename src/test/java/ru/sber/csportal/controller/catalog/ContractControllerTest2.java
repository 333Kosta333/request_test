package ru.sber.csportal.controller.catalog;

import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sber.csportal.dto.catalog.ContractUiDto;
import ru.sber.csportal.entity.catalog.ContractEntity;
import ru.sber.csportal.mapper.catalog.ContractApiUiMapperImpl;
import ru.sber.csportal.repository.catalog.ContractRepository;
import ru.sber.csportal.service.catalog.ContractServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

@Log4j2
@ExtendWith(MockitoExtension.class)
class ContractControllerTest2 {

    @Mock
    private ContractRepository contractRepository;

    private ContractController contractController;

    @BeforeEach
    void setUp() {
        ContractApiUiMapperImpl contractApiUiMapper = new ContractApiUiMapperImpl();
        ContractServiceImpl contractService = new ContractServiceImpl(this.contractRepository,
                contractApiUiMapper);
        this.contractController = new ContractControllerImpl(contractService);
    }

    @Test
    @DisplayName("Найти все договоры")
    void findAll() {
        List<ContractEntity> contracts = List.of(new ContractEntity("1","2"));
        when(this.contractRepository.findAll()).thenReturn(contracts);
        ResponseEntity<List<ContractUiDto>> all = this.contractController.findAll();
        assertEquals(HttpStatus.OK, all.getStatusCode());
        log.info(all);
    }

    @Test
    @DisplayName("Создать договор")
    public void create() throws Exception {
        //given
        ContractUiDto contract = new ContractUiDto(null, "555444333222111",
                "0555444333222111");
        ContractEntity contractEntity = new ContractEntity("555444333222111",
                "0555444333222111");
        //when
        when(this.contractRepository.save(any(ContractEntity.class))).thenReturn(contractEntity);
        //then
        ResponseEntity<ContractUiDto> create = this.contractController.create(contract);
        assertEquals(HttpStatus.OK, create.getStatusCode());
        assertEquals(contract, create.getBody());
        log.info(create);
    }
}