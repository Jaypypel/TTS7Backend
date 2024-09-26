package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DelegationMeasurablesService implements DelegationMeasurablesServiceInterface{

    private final MeasurablesRepository measurablesRepository;

    //"it is used in the existing tts app to get a list of approvedTaskMeasurables , ProcessingTaskMeasurable and CompletedMeasurable , ModifiedTaskMeasurable, DelegatedMeasurable
    @Override
    public List<DelegationMeasurables> getAllocatedMeasurableList(Long taskId) {
        List<Measurables>  measurables = measurablesRepository.findAllocatedMeasurablesByTaskId(taskId);
        List<DelegationMeasurables> delegationMeasurables = new ArrayList<>();
        DelegationMeasurables delegationMeasurable;
        for (Measurables measurable: measurables){
            delegationMeasurable = new DelegationMeasurables();
            delegationMeasurable.setId(measurable.getId());
            delegationMeasurable.setFkMeasurableId(measurable);
            delegationMeasurables.add(delegationMeasurable);
        }
        return delegationMeasurables;
    }
}
