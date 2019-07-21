package alararestaurant.service;

import alararestaurant.config.Constants;
import alararestaurant.domain.dtos.ItemImportDto2;
import alararestaurant.domain.dtos.OrderImportDto;
import alararestaurant.domain.dtos.OrderRootDto;
import alararestaurant.domain.entities.*;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private EmployeeRepository employeeRepository;
    private ItemRepository itemRepository;
    private OrderItemRepository orderItemRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private Gson gson;

    public OrderServiceImpl(OrderRepository orderRepository,
                            EmployeeRepository employeeRepository,
                            ItemRepository itemRepository,
                            OrderItemRepository orderItemRepository,
                            FileUtil fileUtil,
                            ValidationUtil validationUtil,
                            ModelMapper modelMapper,
                            Gson gson) {

        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return fileUtil.readFile(Constants.ORDERS_IMPORT_PATH);
    }

    @Override
    @Transactional
    public String importOrders() throws JAXBException {
        StringBuilder sBuilder = new StringBuilder();
        OrderRootDto orderRootDto = fileUtil
                .parseXml(Constants.ORDERS_IMPORT_PATH, OrderRootDto.class);

        List<OrderImportDto> orderDtos = orderRootDto.getOrders();
        orderDtos
            .forEach( o -> {
                Order newOrder = modelMapper.map(o, Order.class);

                if (!validationUtil.isValid(newOrder)){
                    return;
                }

                String employeeName = o.getEmployee();
                Employee newEmployee = employeeRepository.findByName(employeeName).orElse(null);

                if (newEmployee == null){
                    return;
                }

                newOrder.setEmployee(newEmployee);
                List<ItemImportDto2> itemDtos = o.getItemRootDto().getItemsDtos();
                List<OrderItem> orderItems = new ArrayList<>();

                itemDtos.forEach(i -> {
                    String itemName = i.getName();
                    Item newItem = itemRepository.findByName(itemName).orElse(null);

                    if (newItem == null){
                        return;
                    } else {
                        OrderItem newOrderItem = new OrderItem();
                        newOrderItem.setItem(newItem);
                        newOrderItem.setOrder(newOrder);
                        newOrderItem.setQuantity(i.getQuantity());

                        orderItemRepository.saveAndFlush(newOrderItem);

                        orderItems.add(newOrderItem);
                    }
                });

                if (orderItems.size() != itemDtos.size()){
                    return;
                }

                newOrder.setOrderItems(orderItems);

                orderRepository.saveAndFlush(newOrder);

                sBuilder.append(String.format("Order for %s on %s added",
                        newOrder.getCustomer(),
                        newOrder.getDateTime().toString()))
                        .append(System.lineSeparator());
                return;
            });

        return sBuilder.toString();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        List<Employee> exported = this.employeeRepository.findAllBurgerFlipers();

        StringBuilder sBuilder = new StringBuilder();
        exported.forEach(
                e -> {
                    sBuilder.append(String.format("Name: %s",
                                e.getName()))
                            .append(System.lineSeparator())
                            .append(String.format("Orders: "))
                            .append(System.lineSeparator());

                    List<Order> employeeOrders = e.getOrders();
                    employeeOrders.sort(Comparator.comparingInt(BaseEntity::getId));

                    employeeOrders.forEach(
                            o -> {
                                sBuilder.append(String.format("   Customer: %s",
                                                        o.getCustomer()))
                                        .append(System.lineSeparator())
                                        .append("   Items: ")
                                        .append(System.lineSeparator());

                                List<OrderItem> orderItems = o.getOrderItems();
                                orderItems.forEach(oi -> {
                                    Item orderItem = oi.getItem();
                                    sBuilder.append(String.format("      Name: %s",
                                            orderItem.getName()))
                                        .append(System.lineSeparator())
                                        .append(String.format("      Price: %.2f",
                                                orderItem.getPrice()))
                                        .append(System.lineSeparator())
                                        .append(String.format("      Quantity: %d",
                                                oi.getQuantity()))
                                        .append(System.lineSeparator())
                                        .append(System.lineSeparator());
                                });
                            }
                    );
                }
        );

        return sBuilder.toString();
    }
}