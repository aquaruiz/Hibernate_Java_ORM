package xml.service.impl;

import xml.config.*;
import xml.domain.dtos.ProductDto;
import xml.domain.dtos.ProductsDto;
import xml.domain.dtos.queryDtos.query1.ProductInRangeDto;
import xml.domain.dtos.queryDtos.query1.ProductsInRangeDto;
import xml.domain.dtos.queryDtos.query2.RootSellersDto;
import xml.domain.dtos.queryDtos.query2.SoldProductDto;
import xml.domain.dtos.queryDtos.query2.SuccessfullSellerDto;
import xml.domain.entities.Category;
import xml.domain.entities.Product;
import xml.domain.entities.User;
import xml.repository.CategoryRepository;
import xml.repository.ProductRepository;
import xml.repository.UserRepository;
import xml.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public long getRecordsCount() {
        return this.productRepository.count();
    }

    @Transactional
    public void seedProducts() throws IOException, JAXBException {
        ProductDto[] productDtos = ImportService.getFromXml(InputConstants.FILE_PRODUCTS, ProductsDto.class).getProducts();

        List<Product> products = ImportService.mappDtoToEntity(productDtos, Product.class);

        if (ImportService.areValidEntities(products)){
            List<Category> allCategories = this.categoryRepository.findAll();
            int allCategoriesCount = allCategories.size();

            List<User> allUsers = this.userRepository.findAll();
            int all1UserCount = allUsers.size();

            products.stream()
                    .forEach(p -> {
                        Set<Category> productCategories= new HashSet<>();
                        for (int i = 0; i < RandomService.getRandomInt(1, 5); i++) {
                            productCategories.add(allCategories.get(RandomService.getRandomInt(0, allCategoriesCount - 1)));
                        }

                        p.setCategories(productCategories);
                        p.setSeller(allUsers.get(RandomService.getRandomInt(0, all1UserCount- 1 )));

                        if (RandomService.getRandomInt(1, 2) % 2 == 0){
                            p.setBuyer(allUsers.get(RandomService.getRandomInt(0, all1UserCount - 1)));
                        }
                    });

            this.productRepository.saveAll(products);
            this.productRepository.flush();
        }
    }

    @Override
    public void collectProductsInRange(int lowerboundary, int upperboundary) throws IOException, JAXBException {
        List<Product> products = this
                    .productRepository.
                    findAllByPriceGreaterThanAndPriceLessThanAndBuyerOrderByPriceAsc(
                            BigDecimal.valueOf(lowerboundary),
                            BigDecimal.valueOf(upperboundary),
                            null
                    );

        List<ProductInRangeDto> productInRangeDtos = ExportService
                .mappEntitiesToDtos(products, ProductInRangeDto.class);

        ProductsInRangeDto dtoToXml = new ProductsInRangeDto();
        dtoToXml.setProducts(productInRangeDtos);

        ExportService.saveToXml(OutputConstants.FILE_WRITE_PATH1, dtoToXml);
    }

    @Override
    @Transactional
    public void collectUsersWithSuccessfullySoldProducts() throws IOException, JAXBException {
        List<User> sellers = this.userRepository.getAllSellers();

        List<SuccessfullSellerDto> sellersDtoes = ExportService.mappEntitiesToDtos(sellers, SuccessfullSellerDto.class);

        for (SuccessfullSellerDto seller : sellersDtoes) {
            List<SoldProductDto> sellsToRemove = new ArrayList<>();

            List<SoldProductDto> sells = seller.getSells().getSells();

            for (SoldProductDto sell : sells) {
                if (sell.getBuyerLastName() == null && sell.getBuyerFirstName() == null){
                    sellsToRemove.add(sell);
                }
            }

            sells.removeAll(sellsToRemove);
            seller.getSells().setSells(sells);
        }

        RootSellersDto dtoToXml = new RootSellersDto();
        dtoToXml.setSellers(sellersDtoes);

        ExportService.saveToXml(OutputConstants.FILE_WRITE_PATH2, dtoToXml);
    }
}