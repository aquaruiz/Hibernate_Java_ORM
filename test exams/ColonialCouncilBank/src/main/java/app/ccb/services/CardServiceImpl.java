package app.ccb.services;

import app.ccb.config.Constants;
import app.ccb.domain.dtos.CardDto;
import app.ccb.domain.dtos.CardRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;
    private ClientRepository clientRepository;
    private BankAccountRepository bankAccountRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;

    public CardServiceImpl(CardRepository cardRepository,
                           ClientRepository clientRepository,
                           BankAccountRepository bankAccountRepository,
                           FileUtil fileUtil,
                           ValidationUtil validationUtil,
                           ModelMapper modelMapper) {

        this.cardRepository = cardRepository;
        this.clientRepository = clientRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() throws IOException {
        return fileUtil.readFile(Constants.CARDS_IMPORT_PATH);
    }

    @Override
    public String importCards() throws JAXBException, FileNotFoundException {
        CardRootDto cardRootDto = fileUtil.unmarshallXml(Constants.CARDS_IMPORT_PATH, CardRootDto.class);
        List<CardDto> cardDtos = cardRootDto.getCardDtos();

        StringBuilder stringBuilder = new StringBuilder();
        cardDtos.stream()
                .forEach(c -> {
                    Card newCard = modelMapper.map(c, Card.class);

                    if (!validationUtil.isValid(newCard)) {
                        stringBuilder.append("Error: Incorrect Data!")
                                .append(System.lineSeparator());
                        return;
                    }

                    String bankAccountNumber = c.getAccountnumber();
                    BankAccount savedBankAccount = bankAccountRepository.findByAccountNumber(bankAccountNumber).orElse(null);

                    if(savedBankAccount == null){
                        stringBuilder.append("Error: Incorrect Data!")
                            .append(System.lineSeparator());
                        return;
                    }

                    newCard.setBankAccount(savedBankAccount);
                    cardRepository.saveAndFlush(newCard);
                    stringBuilder.append(String.format("Successfully imported %s – %s.",
                            newCard.getClass().getSimpleName(),
                            newCard.getCardNumber()))
                        .append(System.lineSeparator());
                });

        return null;
    }
}
