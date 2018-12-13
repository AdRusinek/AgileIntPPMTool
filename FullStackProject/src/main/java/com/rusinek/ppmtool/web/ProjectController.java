package com.rusinek.ppmtool.web;

import com.rusinek.ppmtool.domain.Project;
import com.rusinek.ppmtool.services.MapValidationErrorService;
import com.rusinek.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService errorService;

    // ResponseEntity gives more control on JASON responses.
    // BindingRsult gives errors registration capabilities
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = errorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    //this empty String is saying that this will displayed on a default link declared up on our class level
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

       Project project = projectService.findProjectByIdentifier(projectId);

       return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

}
