package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.ProjectServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Projects")
public class ProjectController {

    private final ProjectServiceInterface projectService;

    //tested at 10:14am on 9th Oct
    @GetMapping("/list/code")
    ResponseEntity<List<String>> getProjectCodeList(){
        List<String> projectCodes = projectService.getProjectCodeList();
        return ResponseEntity.ok(projectCodes);
    }

    //tested at 11:09 am on 9th Oct
    @GetMapping("/project/count/{username}/{startDate}/{endDate}")
    ResponseEntity<Integer> getProjectCount(@PathVariable String username, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate startDate ,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate endDate){
        System.out.println("Username: " + username + ", StartDate: " + startDate + ", EndDate: " + endDate);
        Integer prjCnt = projectService.getProjectCount(username, startDate, endDate);
        return ResponseEntity.ok(prjCnt);
    }

    //tested at 4:30pm on 8th Oct
    @GetMapping("/name/list")
    ResponseEntity<List<String>> getProjectNameList() {
        List<String>  projectNames = projectService.getProjectNameList();
        return ResponseEntity.ok(projectNames);
    }

    //tested
    @PostMapping("/project/")
    ResponseEntity<Project> addProject(@RequestParam("user_id") Long userId, @RequestParam("activity_id")
    Long activityId, @RequestParam("proj_code") String projectCode, @RequestParam("proj_name") String prjNme, @RequestParam("created_on") LocalTime crtdOn){
        Project project = projectService.addProject(userId,activityId,projectCode,prjNme,crtdOn);
        return ResponseEntity.ok(project);
    }

    //tested at 10:00am on 9th Oct
    @GetMapping("/project/projectCode")
    ResponseEntity<Project> getProjectViaProjectCode(@RequestParam("pro_code") String projectCode){
        Project project = projectService.getProjectViaProjectCode(projectCode);
        System.out.println(project);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/project/projectName")
    ResponseEntity<APIResponse> getProjectCodeViaProjectName(@RequestParam("proj_name") String projectName ){
        System.out.println(projectName);


        try {
            String Name = projectService.getProjectCodeViaProjectName(projectName);
            System.out.println(Name);
            return ResponseEntity.ok(new APIResponse("project code",Name));
        //    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/json")).body("{ name: "+Name+"}");

        } catch (Exception e) {
            //System.out.println("Error retrieving project code: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse("error ", e.fillInStackTrace()));
        }
    }


}
