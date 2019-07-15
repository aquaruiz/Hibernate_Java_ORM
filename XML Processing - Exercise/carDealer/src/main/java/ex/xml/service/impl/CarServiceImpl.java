package ex.xml.service.impl;

import ex.xml.config.*;
import ex.xml.domain.dtos.CarRootDto;
import ex.xml.domain.dtos.query2.CarByMakeDto;
import ex.xml.domain.dtos.query2.CarByMakeRootDto;
import ex.xml.domain.dtos.query4.CarDto;
import ex.xml.domain.dtos.query4.CarWithPartsRootDto;
import ex.xml.domain.entities.Car;
import ex.xml.domain.entities.Part;
import ex.xml.repository.CarRepository;
import ex.xml.repository.PartRepository;
import ex.xml.service.CarService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    @Override
    public long getDbRecordsCount() {
        return this.carRepository.count();
    }

    @Override
    public void addCarsData() throws FileNotFoundException, JAXBException {
        CarRootDto carRootDto = ImportService.getFromXml(InputConstants.FILE_CARS_PATH, CarRootDto.class);
        List<Car> carsList = ImportService.mappDtoToEntity(carRootDto.getCars(), Car.class);

        List<Part> allParts = this.partRepository.findAll();

        carsList.forEach(car -> {
            for (int i = 0; i < RandomService.getRandomInt(10, 21); i++){
                car.addPart(allParts.get(RandomService.getRandomInt(0, allParts.size() - 1)));
                car.setPrice(car.getPrice());
            }
        });

        this.carRepository.saveAll(carsList);
        this.carRepository.flush();
    }

    @Override
    @Transactional
    public void getCarsFromMake(String carMake) throws IOException, JAXBException {
        List<Car> carsByMake = this.carRepository.getAllByMakeOrderByModelAscTravelledDistanceDesc(carMake);

        List<CarByMakeDto> carsDtoList = ExportService.mappEntitiesToDtos(carsByMake, CarByMakeDto.class);

        CarByMakeRootDto dtoToXml = new CarByMakeRootDto();
        dtoToXml.setCars(carsDtoList);

        ExportService.saveToXml(OutputConstants.FILE_QUERY2, dtoToXml);
    }

    @Override
    public void getCarsWithPartsList() throws IOException, JAXBException {
        List<Car> allCars = this.carRepository.findAll();

        List<CarDto> carDtos = ExportService.mappEntitiesToDtos(allCars, CarDto.class);

        CarWithPartsRootDto dtoToxml = new CarWithPartsRootDto();
        dtoToxml.setCars(carDtos);

        ExportService.saveToXml(OutputConstants.FILE_QUERY4, dtoToxml);
        //        this.saveToJson(carsWithParts, FILE_WRITE4);
        System.out.println();
    }
}