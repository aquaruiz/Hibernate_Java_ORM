package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{
    private final static String CARS_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/cars.json";

    private CarRepository carRepository;
    private RacerRepository racerRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          RacerRepository racerRepository,
                          FileUtil fileUtil,
                          Gson gson,
                          ModelMapper modelMapper,
                          ValidationUtil validationUtil) {

        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        return this.fileUtil.readFile(CARS_JSON_FILE_PATH);
    }

    @Override
    public String importCars(String carsFileContent) throws FileNotFoundException {
        FileReader fileReader = new FileReader(CARS_JSON_FILE_PATH);
        CarImportDto[] carImportDtos = gson.fromJson(fileReader, CarImportDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        List<Car> cars = Arrays.stream(carImportDtos)
                .map(c -> {
                    Car newCar = this.modelMapper.map(c, Car.class);

                    String racerName = c.getRacerName();
                    Racer newRacer = this.racerRepository.findByName(racerName).orElse(null);
                    newCar.setRacer(newRacer);

                    boolean isValid = this.validationUtil.isValid(newCar);
                    if (isValid){
                        stringBuilder
                                .append(String.format("Successfully imported Car – %s %s @ %s.",
                                        newCar.getBrand(),
                                        newCar.getModel(),
                                        newCar.getYearOfProduction()
                                        ))
                                .append(System.lineSeparator());
                        return newCar;
                    }

                    stringBuilder
                            .append("Error: Incorrect Data!")
                            .append(System.lineSeparator());
                    return null;
                })
                .collect(Collectors.toList());

        this.carRepository.saveAll(cars);
        this.carRepository.flush();

        return stringBuilder.toString();
    }
}