package app.ccb.services;

import app.ccb.config.Constants;
import app.ccb.domain.dtos.BankAccountDto;
import app.ccb.domain.dtos.BankAccountRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private BankAccountRepository bankAccountRepository;
    private ClientRepository clientRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  ClientRepository clientRepository,
                                  FileUtil fileUtil,
                                  ValidationUtil validationUtil,
                                  ModelMapper modelMapper) {

        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        return fileUtil.readFile(Constants.BANK_ACCOUNTS_IMPORT_PATH);
    }

    @Override
    public String importBankAccounts() throws JAXBException, FileNotFoundException {
        BankAccountRootDto bankAccountRootDto = fileUtil.unmarshallXml(Constants.BANK_ACCOUNTS_IMPORT_PATH, BankAccountRootDto.class);
        List<BankAccountDto> bankAccountDtos = bankAccountRootDto.getBankAccountDtos();

        StringBuilder stringBuilder = new StringBuilder();
        bankAccountDtos.stream()
                .forEach(b -> {
//                    BankAccount newBankAccount = modelMapper.map(b, BankAccount.class);
                    BankAccount newBankAccount = new BankAccount();
                    newBankAccount.setAccountNumber(b.getAccountNumber());
                    newBankAccount.setBalance(b.getBalance());

                    if (!validationUtil.isValid(newBankAccount)){
                        stringBuilder.append("Error: Incorrect Data!")
                                .append(System.lineSeparator());
                        return;
                    }

                    String clientFullName = b.getClientFullName();
                    Client savedClient = clientRepository.findByFullName(clientFullName).orElse(null);

                    if (savedClient == null){
                        stringBuilder.append("Error: Incorrect Data!")
                                .append(System.lineSeparator());
                        return;
                    }

                    newBankAccount.setClient(savedClient);

                    bankAccountRepository.saveAndFlush(newBankAccount);
                    stringBuilder.append(String.format("Successfully imported %s – %s.",
                            newBankAccount.getClass().getSimpleName(),
                            newBankAccount.getAccountNumber()));
                });

        return stringBuilder.toString();
    }
}