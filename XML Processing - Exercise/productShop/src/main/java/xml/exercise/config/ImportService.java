package xml.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ImportService {
    public static <T> T getFromJson(String filePath, Class<T> clazz) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        return gson.fromJson(br, clazz);
    }

    public static <T> T getFromXml(String filePath, Class<T> clazz) throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (T) unmarshaller.unmarshal(reader);
    }

    public static <T, V> List<V> mappDtoToEntity(T[] dtoes, Class<V> clazz) {
        ModelMapper modelMapper = new ModelMapper();
        return Arrays.stream(dtoes)
                .map(dto -> modelMapper.map(dto, clazz))
                .collect(Collectors.toList());
    }

    public static <T> boolean areValidEntities(List<T> entities) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        for (T entity : entities) {
            Set<ConstraintViolation<T>> violations = validator.validate(entity);

            if (violations.size() > 0){
                return false;
            }
        }

        return true;
    }
}