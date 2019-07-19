package softuni.workshop.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.CompanyDto;
import softuni.workshop.domain.dtos.CompanyRootDto;
import softuni.workshop.domain.entities.Company;
import softuni.workshop.error.Constants;
import softuni.workshop.repository.CompanyRepository;
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
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;
    private FileUtil fileUtil;
    private XmlParser xmlParser;
    private ValidatorUtil validatorUtil;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              ModelMapper modelMapper,
                              FileUtil fileUtil,
                              XmlParser xmlParser,
                              ValidatorUtil validatorUtil) {

        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importCompanies() throws FileNotFoundException, JAXBException {
        BufferedReader reader = this.fileUtil.readFile(Constants.COMPANIES_PATH_IMPORT);
        CompanyRootDto rootCompany = this.xmlParser.getFromXml(reader, CompanyRootDto.class);

        List<CompanyDto> companyDtos = rootCompany.getCompanies();

        List<Company> companies = companyDtos.stream()
                .map(c -> {
                    Set<ConstraintViolation<CompanyDto>> violations = this.validatorUtil.makeValidation(c);
                    if (violations.size() > 0){
                        violations.forEach(
                                v -> System.out.println(v.getMessage())
                        );

                        return null;
                    } else {
                        return this.modelMapper.map(c, Company.class);
                    }
                })
                .collect(Collectors.toList());

        this.companyRepository.saveAll(companies);
    }

    @Override
    public boolean areImported() {
        return companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {
        return fileUtil.getXmltoString(Constants.COMPANIES_PATH_IMPORT);
    }
}