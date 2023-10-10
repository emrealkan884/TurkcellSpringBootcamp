package com.turkcell.spring.business.concretes;

import com.turkcell.spring.business.abstracts.OrderDetailService;
import com.turkcell.spring.business.abstracts.OrderService;
import com.turkcell.spring.core.exceptions.types.BusinessException;
import com.turkcell.spring.entities.concretes.Order;
import com.turkcell.spring.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.entities.dtos.order.OrderForGetByIdDto;
import com.turkcell.spring.entities.dtos.order.OrderForListingDto;
import com.turkcell.spring.entities.dtos.order.OrderForUpdateDto;
import com.turkcell.spring.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {
    //Service'ten başka bir service çağırabiliriz.
    //Service'ten başka bir entity'nin repository'sini kullanmamalıyız..
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public void add(OrderForAddDto orderForAddDto) {
        //Order'ı db'ye kaydet ki order'ın id'si oluşsun.
        //Oluşan id'yi ve itemları orderDetailService'e gönder. O da id'ye detay eklmelerini yapsın.
        /*Order order = Order.builder()
                .customer(Customer.builder().customerId(orderForAddDto.getCustomerId()).build())
                .employee(Employee.builder().employeeId(orderForAddDto.getEmployeeId()).build())
                .shippedDate(orderForAddDto.getShippedDate())
                .orderDate(LocalDate.now())
                .shipCity(orderForAddDto.getShipCity())
                .shipAddress(orderForAddDto.getShipAddress())
                .shipName(orderForAddDto.getShipName())
                .shipRegion(orderForAddDto.getShipRegion())
                .requiredDate(orderForAddDto.getRequiredDate())
                .shipPostalCode(orderForAddDto.getShipPostalCode())
                .build();*/

        Order orderFromAutoMapping = modelMapper.map(orderForAddDto, Order.class);

        orderFromAutoMapping = orderRepository.save(orderFromAutoMapping);
        //Bu satırdan sonra order'ın id alanı oluşmuş olacak.Yani artık order_details tablosuna order_id verebilecek haldeyiz.
        orderDetailService.addItemsToOrder(orderFromAutoMapping, orderForAddDto.getItems());
    }

    @Override
    public List<OrderForListingDto> getAll() {
        return null;
    }

    @Override
    public OrderForGetByIdDto getById(short id) {
        return null;
    }

    @Override
    public void update(short id, OrderForUpdateDto orderForUpdateDto) {

    }

    @Override
    public void delete(short id) {

    }
    @Override
    public List<Order> findByFreightGreaterThan(float freight) {
        return null;
    }

    public  void orderWithShippedDateGreaterThanOrderDate(LocalDate orderDate, LocalDate shippedDate){
        int result = orderDate.compareTo(shippedDate);

        if(result > 0){
            throw new BusinessException(
                    messageSource.getMessage("orderWithShippedDateGreaterThanOrderDate",null,LocaleContextHolder.getLocale()));
        }
    }
    //Bir ülkeye giden şipariş sayısı 122'den büyük olamaz.
    public  void orderWithFreightGreaterThan750Limit7(float freight){
        if (freight > 750){
            List<Order> orders = orderRepository.findOrdersByFreightGreaterThan750();
            if (orders.size() >= 7){
                throw new BusinessException(
                        messageSource.getMessage("orderWithFreightGreaterThan750Limit7",null,LocaleContextHolder.getLocale())
                );
            }
        }
    }
    //Bölge ve Posta Kodu aynı anda null olamaz.
    public void orderWithShipRegionAndShipPostalCodeCannotBeNullAtSameTime(String shipRegion, String shipPostalCode){
        if(shipRegion == null && shipPostalCode == null){
            throw new BusinessException(
                    messageSource.getMessage("orderWithShipRegionAndShipPostalCodeCannotBeNullAtSameTime",null, LocaleContextHolder.getLocale())
            );
        }
    }
}

//OrderId yi kullanıcıdan istemeyeceğiz çünkü zaten yeni bir sipariş oluşturuyoruz
//ProductId yi isteyeceğiz çünkü kullanıcı hangi ürünü seçeceğine kendisi karar verir.
//UnitPrice kullanıcıdan istemyeceğiz. Çünkü unitPrice hali hazırda tabloda var.
//(Kullanıcıya bir ürünü al ve fiyatını sen belirle demek saçma olur)
//Bir üründen kaç adet alacağına kullanıcı karar verecek. Yani isteyeceğiz.