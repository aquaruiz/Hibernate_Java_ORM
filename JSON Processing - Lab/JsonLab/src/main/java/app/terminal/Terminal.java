package app.terminal;

import app.domain.dto.AddressJsonDto;
import app.domain.dto.StreetJsonDto;
import app.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

//    @Autowired
    private PersonService personService;

    @Override
    public void run(String... strings) throws Exception {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        AddressJsonDto addressJsonDto = new AddressJsonDto();
        addressJsonDto.setCountry("Bulgaria");
        addressJsonDto.setCity("Varna");
        StreetJsonDto streetJsonDto = new StreetJsonDto();
        streetJsonDto.setName("Vladislav Varnenchik str.");
        streetJsonDto.setNumber(6);
        addressJsonDto.addStreet(streetJsonDto);

        String content = gson.toJson(addressJsonDto);
        System.out.println(content);

        AddressJsonDto addressJsonDtoForeign = new AddressJsonDto();
        addressJsonDtoForeign.setCountry("Spain");
        addressJsonDtoForeign.setCity("Barcelona");
        StreetJsonDto streetJsonDtoForeign = new StreetJsonDto();
        streetJsonDtoForeign.setName("Gaudi str.");
        streetJsonDtoForeign.setNumber(10);
        addressJsonDtoForeign.addStreet(streetJsonDto);
        addressJsonDtoForeign.addStreet(streetJsonDtoForeign);

        List<AddressJsonDto> addressJsonDtos = new ArrayList<>();
        addressJsonDtos.add(addressJsonDto);
        addressJsonDtos.add(addressJsonDtoForeign);
        String multipleContent = gson.toJson(addressJsonDtos);
        System.out.println(multipleContent);

        AddressJsonDto g123
                = gson.fromJson(
                        new BufferedReader(
                                new FileReader("src/main/resources/files/input/json/address.json")),
                        AddressJsonDto.class);

        System.out.println(g123);

        AddressJsonDto[] g1234
                = gson.fromJson(
                        new BufferedReader(
                                new FileReader("src/main/resources/files/input/json/addresses.json")),
                        AddressJsonDto[].class);

        System.out.println(g1234);
    }
}