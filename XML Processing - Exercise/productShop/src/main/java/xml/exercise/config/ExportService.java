package xml.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ExportService {
    public static <T> void saveToXml(String filePath, T obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File fr = new File(filePath);
        marshaller.marshal(obj, fr);
    }

    public static <T, V> List<V> mappEntitiesToDtos(List<T> entities, Class<V> clazz) {
        ModelMapper modelMapper = new ModelMapper();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, clazz))
                .collect(Collectors.toList());
    }

    public static <T, V> V mappOneEntityToDto(T entity, Class<V> clazz) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, clazz);
    }
}