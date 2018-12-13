package com.rusinek.ppmtool.services;

import com.rusinek.ppmtool.domain.Project;
import com.rusinek.ppmtool.exceptions.ProjectIdException;
import com.rusinek.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase()
                    + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String identifier) {

        Project project = projectRepository.findByProjectIdentifier(identifier.toUpperCase());

        if(project == null) {
            throw new ProjectIdException("Project ID '" + identifier
                    + "' doesn't exists.");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}
