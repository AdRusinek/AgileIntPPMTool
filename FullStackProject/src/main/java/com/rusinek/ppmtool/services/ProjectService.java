package com.rusinek.ppmtool.services;

import com.rusinek.ppmtool.domain.Project;
import com.rusinek.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        // todo there will be a lot of logic there...

        return projectRepository.save(project);
    }

}
