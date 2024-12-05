package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Repository.DelegationMeasurablesRepository;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DelegationMeasurablesService implements DelegationMeasurablesServiceInterface{

    private final MeasurablesRepository measurablesRepository;
    private final DelegationMeasurablesRepository delegationMeasurablesRepository;

    //"it is used in the existing tts app to get a list of approvedTaskMeasurables , ProcessingTaskMeasurable and CompletedMeasurable , ModifiedTaskMeasurable, DelegatedMeasurable
    @Override
    public List<Measurables> getAllocatedMeasurableList(Long taskId) {
        List<Measurables>  measurables = measurablesRepository.findAllocatedMeasurablesByTaskId(taskId);
//        List<DelegationMeasurables> delegationMeasurables = new ArrayList<>();
//        DelegationMeasurables delegationMeasurable;
//        for (Measurables measurable: measurables){
//            delegationMeasurable = new DelegationMeasurables();
//            delegationMeasurable.setId(measurable.getId());
//            delegationMeasurable.setFkMeasurableId(measurable);
//            delegationMeasurables.add(delegationMeasurable);
//        }
        return measurables;
    }

    @Override
    public DelegationMeasurables addDelegationMeasurables(TaskManagement taskManagement, Measurables mesrblId, Long mesrbQuantity, String mesrbUnit) {
        DelegationMeasurables delegationMeasurable = new DelegationMeasurables();
        delegationMeasurable.setFkTaskManagementID(taskManagement);
        delegationMeasurable.setFkMeasurableId(mesrblId);
        delegationMeasurable.setExpectedMeasurableQuantity(mesrbQuantity);
        delegationMeasurable.setActualMeasurableQuantity(mesrbQuantity);
        delegationMeasurable.setMeasurableUnit(mesrbUnit);
        return delegationMeasurablesRepository.save(delegationMeasurable);
    }
}
