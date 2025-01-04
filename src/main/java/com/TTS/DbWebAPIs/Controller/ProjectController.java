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
    ResponseEntity<?> getProjectCodeList(){
        try {
            List<String> projectCodes = projectService.getProjectCodeList();
            if (projectCodes == null || projectCodes.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No project code found", null));
            }
            return ResponseEntity.ok(new APIResponse("successful",projectCodes));
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting project codes . Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 11:09 am on 9th Oct
    @GetMapping("/project/count/{username}/{startDate}/{endDate}")
    ResponseEntity<?> getProjectCount(@PathVariable String username, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate startDate ,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate endDate){
        try {
            Integer prjCnt = projectService.getProjectCount(username, startDate, endDate);
            if (prjCnt == null || prjCnt==0){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("Not available in the submitted date range", null));
            }
            return ResponseEntity.ok(prjCnt);
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while fetching no of project. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 4:30pm on 8th Oct
    @GetMapping("/name/list")
    ResponseEntity<?> getProjectNameList() {
       try{
           List<String>  projectNames = projectService.getProjectNameList();
           if (projectNames == null || projectNames.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No count present", null));
           }
           return ResponseEntity.ok(new APIResponse<>("successful",projectNames));
       }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while fetching project names . Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested
    @PostMapping("/project/")
    ResponseEntity<?> addProject(@RequestParam("user_id") String username, @RequestParam("activity_id")
    Long activityId, @RequestParam("proj_code") String projectCode, @RequestParam("proj_name") String prjNme
            , @RequestParam("created_on") String createdOn){
        try {
            Project project = projectService.addProject(username,activityId,projectCode,prjNme,createdOn);
            return ResponseEntity.ok(new APIResponse("successful","-"));
        } catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while adding a project. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 10:00am on 9th Oct
    @GetMapping("/project/projectCode")
    ResponseEntity<?> getProjectViaProjectCode(@RequestParam("pro_code") String projectCode){
       try {
           Project project = projectService.getProjectViaProjectCode(projectCode);
           if ( project == null){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No project is present with the submitted project code", null));
           }
           return ResponseEntity.ok(project);
       }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting a Project . Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    @GetMapping("/project/projectName")
    ResponseEntity<APIResponse> getProjectCodeViaProjectName(@RequestParam("proj_name") String projectName ){

        try {
            String projectCode = projectService.getProjectCodeViaProjectName(projectName);
            if ( projectCode == null){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No project code is present with the submitted project name", null));
            }
            return ResponseEntity.ok(new APIResponse("project code",projectCode));
        //    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/json")).body("{ name: "+Name+"}");
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting a project code. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }


}
