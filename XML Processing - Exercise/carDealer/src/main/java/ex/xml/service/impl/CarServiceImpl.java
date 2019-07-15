package ex.xml.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ex.xml.domain.dtos.CarDto;
import ex.xml.domain.dtos.query2.CarByMakeDto;
import ex.xml.domain.dtos.query4.CarPartDto;
import ex.xml.domain.entities.Car;
import ex.xml.domain.entities.Part;
import ex.xml.repository.CarRepository;
import ex.xml.repository.PartRepository;
import ex.xml.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";
    private final String FILE_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "cars.xml";
    private final String FILE_WRITE2 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "toyota-cars.xml";
    private final String FILE_WRITE4 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "cars-and-parts.xml";

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public long getDbRecordsCount() {
        return this.carRepository.count();
    }

    @Override
    public void addCarsData() throws FileNotFoundException {
        FileReader fr = new FileReader(FILE_PATH);
        CarDto[] carDtosList = gson.fromJson(fr, CarDto[].class);
        List<Car> carsList = Arrays.stream(carDtosList)
                .map(dto -> this.modelMapper.map(dto, Car.class))
                .collect(Collectors.toList());

        List<Part> allParts = this.partRepository.findAll();
        carsList.forEach(car -> {
            for (int i = 0; i < randomInRange(10, 21); i++){
                car.addPart(allParts.get(randomInRange(0, allParts.size() - 1)));
                car.setPrice(car.getPrice());
            }
        });

        this.carRepository.saveAll(carsList);
        this.carRepository.flush();
    }

    private int randomInRange(int start, int end) {
        Random random = new Random();

        return random.nextInt((end - start) + 1) + start;
    }

    @Override
    @Transactional
    public void getCarsFromMake(String carMake) throws IOException {
        List<Car> carsByMake = this.carRepository.getAllByMakeOrderByModelAscTravelledDistanceDesc(carMake);

        List<CarByMakeDto> carsDtoList = carsByMake.stream()
                .map(c -> this.modelMapper.map(c, CarByMakeDto.class))
                .collect(Collectors.toList());

        saveToJson(carsDtoList, FILE_WRITE2);
    }

    @Override
    public void getCarsWithPartsList() throws IOException {
        List<Car> allCars = this.carRepository.findAll();

        List<CarPartDto> carsWithParts = allCars.stream()
                .map(c -> this.modelMapper.map(c, CarPartDto.class))
                .collect(Collectors.toList());

        this.saveToJson(carsWithParts, FILE_WRITE4);
    }

    private void saveToJson(List<?> dtos, String filePath) throws IOException {
        FileWriter fr = new FileWriter(filePath);
        this.gson.toJson(dtos, fr);
        fr.flush();
    }
}