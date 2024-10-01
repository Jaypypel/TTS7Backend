package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;
import com.TTS.DbWebAPIs.Service.ProjectServiceInterface;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Projects")
public class ProjectController {

    private final ProjectServiceInterface projectService;

    @GetMapping("{projectName}/code")
    ResponseEntity<Integer> getProjectCode(@PathVariable String projectName){
       Integer prjCod = projectService.getProjectCode(projectName);
       return ResponseEntity.ok(prjCod);
    }

    @GetMapping("/list/code")
    ResponseEntity<List<ProjectCode>> getProjectCodeList(){
        List<ProjectCode> projectCodes = projectService.getProjectCodeList();
        return ResponseEntity.ok(projectCodes);
    }

    @GetMapping("/project/count")
    ResponseEntity<Integer> getProjectCount(@PathVariable Long userId, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate){
        Integer prjCnt = projectService.getProjectCount(userId, startDate, endDate);
        return ResponseEntity.ok(prjCnt);
    }

    @GetMapping("/name/list")
    ResponseEntity<List<ProjectName>> getProjectNameList() {
        List<ProjectName>  projectNames = projectService.getProjectNameList();
        return ResponseEntity.ok(projectNames);
    }

    @PostMapping("/project/")
    ResponseEntity<Project> addProject(@PathVariable Long userId, @PathVariable Activity activityId, @PathVariable Long projectCode, @PathVariable String prjNme, @PathVariable LocalTime crtdOn){
      Project project = projectService.addProject(userId,activityId,projectCode,prjNme,crtdOn);
      return ResponseEntity.ok(project);
    }


}