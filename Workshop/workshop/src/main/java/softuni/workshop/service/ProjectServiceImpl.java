package softuni.workshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.ProjectDto;
import softuni.workshop.domain.dtos.ProjectRootDto;
import softuni.workshop.domain.entities.Project;
import softuni.workshop.error.Constants;
import softuni.workshop.repository.ProjectRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private ModelMapper modelMapper;
    private FileUtil fileUtil;
    private XmlParser xmlParser;
    private ValidatorUtil validatorUtil;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper, FileUtil fileUtil, XmlParser xmlParser, ValidatorUtil validatorUtil) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importProjects() throws FileNotFoundException, JAXBException {
        BufferedReader reader = this.fileUtil.readFile(Constants.PROJECTS_PATH_IMPORT);
        ProjectRootDto rootProject = this.xmlParser.getFromXml(reader, ProjectRootDto.class);

        List<ProjectDto> projectDtos = rootProject.getProjects();

        List<Project> projects = projectDtos.stream()
                .map(p -> {
                    Set<ConstraintViolation<ProjectDto>> violations = this.validatorUtil.makeValidation(p);
                    if (violations.size() > 0){
                        violations.forEach(
                                v -> System.out.println(v.getMessage())
                        );

                        return null;
                    } else {
                        return this.modelMapper.map(p, Project.class);
                    }
                })
                .collect(Collectors.toList());

        this.projectRepository.saveAll(projects);
    }

    @Override
    public boolean areImported() {
       return this.projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() throws IOException {
        return fileUtil.getXmltoString(Constants.PROJECTS_PATH_IMPORT);
    }

    @Override
    public String exportFinishedProjects() throws JAXBException, IOException {
        List<Project> finishedProjects = this.projectRepository.findAllByIsFinished(true);

        ProjectRootDto projectRootDto = new ProjectRootDto();
        List<ProjectDto> projectsToexport = finishedProjects.stream()
                .map(p -> this.modelMapper.map(p, ProjectDto.class))
                .collect(Collectors.toList());

        projectRootDto.setProjects(projectsToexport);
        this.xmlParser.saveRootDtoToXml(projectRootDto, Constants.EXPORT_FINISHED_PROJECTS_PATH);

        return this.fileUtil.getXmltoString(Constants.EXPORT_FINISHED_PROJECTS_PATH);
    }
}