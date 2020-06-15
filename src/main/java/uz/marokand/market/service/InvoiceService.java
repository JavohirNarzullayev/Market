package uz.marokand.market.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.marokand.market.config.SecurityUtils;
import uz.marokand.market.entity.*;
import uz.marokand.market.repository.InvoiceRepository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
   private final InvoiceRepository invoiceRepository;

   private final UserService userService;
   private final OrderDetailService orderDetailService;
   private final OrderService orderService;
      @Autowired
   public InvoiceService(InvoiceRepository invoiceRepository, UserService userService, OrderService orderService, OrderDetailService orderDetailService) {
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
        this.orderService = orderService;
          this.orderDetailService = orderDetailService;
      }
    public void confirmOrders() throws NotFoundException {
        Date date=new Date(System.currentTimeMillis());
        Optional<String> user= SecurityUtils.getCurrentUser();
        if (user.isPresent()){
        Users byUserName = userService.getByUserName(user.get());
        Order currentOrder=new Order(date,byUserName);
        List<OrderDetails> all = orderDetailService.getAllOrders();
        for (OrderDetails order:all) {
            order.setOrder(currentOrder);
        }
        orderService.saveOrder(currentOrder);

    }}

    public List<Invoice> getAllInvoice() throws NotFoundException {
        Users byUserName = userService.getByUserName(SecurityUtils.getCurrentUser().get());
        List<Order> orderByCustomer = orderService.getOrderByCustomer(byUserName);
        Order order = orderByCustomer.get(orderByCustomer.size() - 1);
        List<OrderDetails> orderDetailByOrder = orderDetailService.getOrderDetailByOrder(order);
        double amount=0;
        for (OrderDetails orderDetail:orderDetailByOrder) {
            Integer quantity = orderDetail.getQuantity();

            Product product = orderDetail.getProduct();
            Double price = product.getPrice();
            if (price!=null&&quantity!=null)
            amount+=(price*quantity);
        }
        Date invoiceDate=new Date(System.currentTimeMillis());
        Calendar dueCalendar=Calendar.getInstance();
        dueCalendar.setTime(invoiceDate);
        dueCalendar.add(Calendar.DATE,1);

        Date dueDate= new Date(dueCalendar.getTimeInMillis());
        Invoice invoice=new Invoice(amount,invoiceDate,dueDate,order);
        invoiceRepository.save(invoice);
        return invoiceRepository.findByOrder_Customer(byUserName);
    }
    public List<OrderDetails> getProductByOrder() throws NotFoundException {
        Users byUserName = userService.getByUserName(SecurityUtils.getCurrentUser().get());
        List<Order> orderByCustomer = orderService.getOrderByCustomer(byUserName);
        Order order = orderByCustomer.get(orderByCustomer.size() - 1);
        List<OrderDetails> orderDetailByOrder = orderDetailService.getOrderDetailByOrder(order);
        return orderDetailByOrder;
    }
     @Transactional
      public List<Invoice> findAll(){
               return invoiceRepository.findAll();
      }







}

