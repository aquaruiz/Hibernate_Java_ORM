package example.xml;

import com.sun.xml.bind.v2.runtime.reflect.opt.FieldAccessor_Long;
import example.xml.dtoes.AddressDto;
import example.xml.dtoes.UserDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;

@Controller
public class Runner implements CommandLineRunner {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator;
    private JAXBContext jaxbContext;

    public Runner() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(UserDto.class);
    }

    @Override
    public void run(String... args) throws Exception {

//        UserDto user = new UserDto("bee", 17);
//        Marshaller marshaller = this.jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(user, new File("usersss.xml"));

        AddressDto addressDto = new AddressDto("BG", "pld");
        UserDto user = new UserDto("bee", 17);
        user.setAddress(addressDto);

        this.jaxbContext = JAXBContext.newInstance(UserDto.class);

        Marshaller marshaller = this.jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(user, new File(FOLDER_PATH + "output" +
                File.separator + "addresses.xml"));


        Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
        UserDto gottenUser = (UserDto) unmarshaller.unmarshal(new File(FOLDER_PATH + "input" +
                File.separator + "addresses.xml"));
        System.out.println("hello");
    }
}