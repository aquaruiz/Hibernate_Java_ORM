package json.ex.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.ex.domain.dtos.CarDto;
import json.ex.domain.entities.Car;
import json.ex.domain.entities.Part;
import json.ex.repository.CarRepository;
import json.ex.repository.PartRepository;
import json.ex.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator;
    private final String FILE_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "cars.json";

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
}
