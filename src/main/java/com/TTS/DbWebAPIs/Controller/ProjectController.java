package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;
import com.TTS.DbWebAPIs.Service.ProjectServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Projects")
public class ProjectController {

    private final ProjectServiceInterface projectService;


    @GetMapping("/list/code")
    ResponseEntity<List<String>> getProjectCodeList(){
        List<String> projectCodes = projectService.getProjectCodeList();
        return ResponseEntity.ok(projectCodes);
    }

    @GetMapping("/project/count")
    ResponseEntity<Integer> getProjectCount(@PathVariable Long userId, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate){
        Integer prjCnt = projectService.getProjectCount(userId, startDate, endDate);
        return ResponseEntity.ok(prjCnt);
    }

    //tested at 4:30pm on 8th Oct
    @GetMapping("/name/list")
    ResponseEntity<List<String>> getProjectNameList() {
        List<String>  projectNames = projectService.getProjectNameList();
        return ResponseEntity.ok(projectNames);
    }

    //
    @PostMapping("/project/")
    ResponseEntity<Project> addProject(@RequestParam("user_id") Long userId, @RequestParam("activity_id")
    Long activityId, @RequestParam("proj_code") String projectCode, @RequestParam("proj_name") String prjNme, @RequestParam("created_on") LocalTime crtdOn){
        Project project = projectService.addProject(userId,activityId,projectCode,prjNme,crtdOn);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/projectCode/")
    ResponseEntity<Project> getProjectViaProjectCode(@RequestParam("pro_code") String projectCode){
        Project project = projectService.getProjectCode(projectCode);
        System.out.println(project);
        return ResponseEntity.ok(project);
    }


}
