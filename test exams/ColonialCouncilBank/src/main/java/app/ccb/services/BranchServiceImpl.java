package app.ccb.services;

import app.ccb.config.Constants;
import app.ccb.domain.dtos.BranchDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepository branchRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository,
                             FileUtil fileUtil,
                             ValidationUtil validationUtil,
                             Gson gson,
                             ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return fileUtil.readFile(Constants.BRANCHES_IMPORT_PATH);
    }

    @Override
    public String importBranches(String branchesJson) throws FileNotFoundException {
        BranchDto[] branchDtos = fileUtil.parseFromJson(gson,
                Constants.BRANCHES_IMPORT_PATH,
                BranchDto[].class);

        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(branchDtos)
            .forEach(b -> {
                Branch newBranch = modelMapper.map(b, Branch.class);

                if (!validationUtil.isValid(newBranch)){
                    stringBuilder.append("Error: Incorrect Data!")
                            .append(System.lineSeparator());
                    return;
                }

                stringBuilder.append(String.format("Successfully imported %s – %s.",
                            newBranch.getClass().getSimpleName(),
                            newBranch.getName()))
                        .append(System.lineSeparator());

                branchRepository.saveAndFlush(newBranch);
        });

        return stringBuilder.toString();
    }
}