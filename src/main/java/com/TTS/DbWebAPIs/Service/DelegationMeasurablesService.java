package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.DTO.AssociatedMeasurableDto;
import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Repository.DelegationMeasurablesRepository;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DelegationMeasurablesService implements DelegationMeasurablesServiceInterface{

    private final MeasurablesRepository measurablesRepository;
    private final DelegationMeasurablesRepository delegationMeasurablesRepository;

    //"it is used in the existing tts app to get a list of approvedTaskMeasurables , ProcessingTaskMeasurable and CompletedMeasurable , ModifiedTaskMeasurable, DelegatedMeasurable
    @Override
    public List<Measurables> getAllocatedMeasurableList(Long taskId) throws DatabaseException {
        List<Measurables>  measurables = measurablesRepository.findAllocatedMeasurablesByTaskId(taskId);
        return measurables;
    }

    @Transactional
    @Override
    public DelegationMeasurables addDelegationMeasurables(TaskManagement taskManagement,
                                                          Measurables mesrblId,
                                                          Long mesrbQuantity,
                                                          String mesrbUnit) throws DatabaseException{
        DelegationMeasurables delegationMeasurable = new DelegationMeasurables();
        delegationMeasurable.setFkTaskManagementID(taskManagement);
        delegationMeasurable.setFkMeasurableId(mesrblId);
        delegationMeasurable.setExpectedMeasurableQuantity(mesrbQuantity);
        delegationMeasurable.setActualMeasurableQuantity(mesrbQuantity);
        delegationMeasurable.setMeasurableUnit(mesrbUnit);
        return delegationMeasurablesRepository.save(delegationMeasurable);
    }

    @Override
    public void addAllDelegationMeasurables(TaskManagement taskManagement, List<AssociatedMeasurableDto> associatedMeasurableDtos) {
     List<DelegationMeasurables>  delegationMeasurables =  associatedMeasurableDtos.stream().map(associatedMeasurableDto -> {
            DelegationMeasurables delegationMeasurable = new DelegationMeasurables();
            delegationMeasurable.setFkTaskManagementID(taskManagement);
            Measurables measurables = new Measurables();
            measurables.setId(associatedMeasurableDto.getId());
            delegationMeasurable.setFkMeasurableId(measurables);
            delegationMeasurable.setExpectedMeasurableQuantity(associatedMeasurableDto.measurableQty);
            delegationMeasurable.setActualMeasurableQuantity(associatedMeasurableDto.measurableQty);
            delegationMeasurable.setMeasurableUnit(associatedMeasurableDto.measurableUnit);
            return delegationMeasurable;
        }).toList();
      delegationMeasurablesRepository.saveAll(delegationMeasurables);
    }
}
