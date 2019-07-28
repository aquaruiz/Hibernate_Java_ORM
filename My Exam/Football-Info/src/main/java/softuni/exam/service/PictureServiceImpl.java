package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.config.Constants;
import softuni.exam.domain.dtos.PictureDto;
import softuni.exam.domain.dtos.PicturesRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private PictureRepository pictureRepository;
    private FileUtil fileUtil;
    private XmlParser xmlParser;
    private ValidatorUtil validatorUtil;
    private ModelMapper modelMapper;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository,
                              FileUtil fileUtil,
                              XmlParser xmlParser,
                              ValidatorUtil validatorUtil,
                              ModelMapper modelMapper) {

        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importPictures() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        List<PictureDto> pictureDtos = xmlParser.parseXml(Constants.PICTURES_IMPORT_PATH, PicturesRootDto.class)
                                                .getPictures();

        pictureDtos.stream().forEach(p -> {
            Picture newPicture = modelMapper.map(p, Picture.class);

            if (!validatorUtil.isValid(newPicture)){
                sb.append("Invalid picture"). append(System.lineSeparator());
                return;
            }

            pictureRepository.saveAndFlush(newPicture);
            sb.append(String.format("Successfully imported %s - %s",
                        newPicture.getClass().getSimpleName().toLowerCase(),
                        newPicture.getUrl()))
                    .append(System.lineSeparator());
        });

        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return fileUtil.readFile(Constants.PICTURES_IMPORT_PATH);
    }
}