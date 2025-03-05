package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
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

import java.sql.SQLException;
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
    ResponseEntity<?> getProjectCodeList() throws DatabaseException, InternalServerException {

            List<String> projectCodes = projectService.getProjectCodeList();
            if (projectCodes == null || projectCodes.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No project code found", null));
            }
            return ResponseEntity.ok(new APIResponse("successful",projectCodes));

    }

    //tested at 11:09 am on 9th Oct
    @GetMapping("/project/count/{username}/{startDate}/{endDate}")
    ResponseEntity<?> getProjectCount(@PathVariable String username
            , @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate startDate
            ,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate endDate) throws DatabaseException , InternalServerException  {

            Integer prjCnt = projectService.getProjectCount(username, startDate, endDate);
            if (prjCnt == null || prjCnt==0){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("Not available in the submitted date range", null));
            }
            return ResponseEntity.ok(prjCnt);

    }

    //tested at 4:30pm on 8th Oct
    @GetMapping("/name/list")
    ResponseEntity<?> getProjectNameList() throws DatabaseException  , InternalServerException {

           List<String>  projectNames = projectService.getProjectNameList();
           if (projectNames == null || projectNames.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No count present", null));
           }
           return ResponseEntity.ok(new APIResponse<>("successful",projectNames));

    }

    //tested
    @PostMapping("/project/")
    ResponseEntity<?> addProject(@RequestParam("user_id") String username, @RequestParam("activity_id")
    Long activityId, @RequestParam("proj_code") String projectCode, @RequestParam("proj_name") String prjNme
            , @RequestParam("created_on") String createdOn) throws DatabaseException, UserNotFoundException, NotFoundException , InternalServerException {

            Project project = projectService.addProject(username,activityId,projectCode,prjNme,createdOn);
            return ResponseEntity.ok(new APIResponse("successful","-"));

    }

    //tested at 10:00am on 9th Oct
    @GetMapping("/project/projectCode")
    ResponseEntity<?> getProjectViaProjectCode(@RequestParam("pro_code") String projectCode) throws DatabaseException, InternalServerException  {
           Project project = projectService.getProjectViaProjectCode(projectCode);
           if ( project == null){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No project is present with the submitted project code", null));
           }
           return ResponseEntity.ok(project);

    }

    @GetMapping("/project/projectName")
    ResponseEntity<APIResponse> getProjectCodeViaProjectName(@RequestParam("proj_name") String projectName ) throws DatabaseException , NotFoundException , InternalServerException {
            String projectCode = projectService.getProjectCodeViaProjectName(projectName);
            if ( projectCode == null){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No project code is present with the submitted project name", null));
            }
            return ResponseEntity.ok(new APIResponse<>("project code",projectCode));

    }


}
