package com.is4tech.salesapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.is4tech.salesapi.domain.*;
import com.is4tech.salesapi.services.CustomerService;
import com.is4tech.salesapi.services.OrderDetailService;
import com.is4tech.salesapi.services.OrderService;
import com.is4tech.salesapi.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
class SalesapiApplicationTests {

    @MockBean
    CustomerService customerService;
    @MockBean
    ProductService productService;
    @MockBean
    OrderService orderService;
    @MockBean
    OrderDetailService orderDetailService;

    private MockMvc mvc;

    @Autowired
    private final WebApplicationContext context;

    SalesapiApplicationTests(WebApplicationContext context) {
        this.context = context;
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testGetAllProducts() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.get("/api/products")
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetAllProductsWithInvalidRoute() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.get("/api/productss")
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerWithInvalidRoute() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.get("/api/customers1")
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testAddProduct() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setImage("http://via.placeholder.com/640x360");
        mockProduct.setCost(BigDecimal.valueOf(45));
        mockProduct.setSalePrice(BigDecimal.ZERO);
        mockProduct.setPrice(BigDecimal.valueOf(80));
        mockProduct.setName("Test product");
        mockProduct.setStockQuantity(50);
        mockProduct.setDescription("test description");

        String body = this.convertToJSON(mockProduct);

        when(productService.save(any(Product.class))).thenReturn(new Product());

        this.mvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testAddProductInvalidBody () throws Exception {
        Product mockProduct = new Product();
        mockProduct.setSalePrice(BigDecimal.ZERO);
        mockProduct.setPrice(BigDecimal.valueOf(80));
        mockProduct.setName("Test product");
        mockProduct.setStockQuantity(50);
        mockProduct.setDescription("test description");
        String body = this.convertToJSON(mockProduct);
        when(productService.save(any(Product.class))).thenReturn(new Product());
        this.mvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetProductById() throws Exception {
        when(productService.getById(1)).thenReturn(new Product());
        this.mvc.perform(MockMvcRequestBuilders.get("/api/products/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetProductByIdInvalidRoute() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/productss/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testEditProduct() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setImage("http://via.placeholder.com/640x360");
        mockProduct.setCost(BigDecimal.valueOf(45));
        mockProduct.setSalePrice(BigDecimal.ZERO);
        mockProduct.setPrice(BigDecimal.valueOf(80));
        mockProduct.setName("Test product");
        mockProduct.setStockQuantity(50);
        mockProduct.setDescription("test description");
        mockProduct.setId(1);

        Product mockStoredProduct = new Product();

        when(productService.getById(1)).thenReturn(mockStoredProduct);
        when(productService.save(mockProduct)).thenReturn(new Product());

        String body = this.convertToJSON(mockProduct);

        this.mvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testEditProductInvalidBody () throws Exception {
        Product mockProduct = new Product();
        mockProduct.setSalePrice(BigDecimal.ZERO);
        mockProduct.setPrice(BigDecimal.valueOf(80));
        mockProduct.setDescription("test description");
        mockProduct.setId(1);
        String body = this.convertToJSON(mockProduct);


        this.mvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getCustomerByIdTest() throws Exception {
        when(customerService.getById(1)).thenReturn(new Customer());
        this.mvc.perform(MockMvcRequestBuilders.get("/api/customers/1")
        .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void saveCustomerTest() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("test address");
        customer.setEmail("email@gmail.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setPhoneNumber("86568989");
        customer.setPassword("dev-qa-test-123");

        when(customerService.save(any(Customer.class))).thenReturn(new Customer());
        this.mvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.convertToJSON(customer))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void saveCustomerWrongBody() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("emailgmail.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(customerService.save(any(Customer.class))).thenReturn(new Customer());
        this.mvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.convertToJSON(customer))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getOrdersTest() throws Exception {
        List<Order> orders = new ArrayList<>();
        Order mockOrder = new Order();
        mockOrder.setCustomer(new Customer());
        mockOrder.setStatus(new Status());
        mockOrder.setOrderNumber("or-test-1");
        mockOrder.setTotal(BigDecimal.valueOf(58));
        List<OrderDetail> mockOrderDetails = new ArrayList<>();
        mockOrder.setDetails(mockOrderDetails);

        orders.add(mockOrder);

        when(orderService.findAll()).thenReturn(orders);

        this.mvc.perform(MockMvcRequestBuilders.get("/api/orders")
        .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /*@Test
    void saveOrderTest() {
        try {
            Status mockStatus = new Status();
            mockStatus.setDescription("");
            mockStatus.setName("Mock status");
            mockStatus.setId(1);
            Order order = new Order(
                    "OR-TEST-2020",
                    new Customer(
                            "John",
                            "Doe",
                            "email@test.com",
                            "45568956",
                            "Address test",
                            "qa-test-1"
                    ),
                    mockStatus,
                    LocalDateTime.now(),
                    BigDecimal.ZERO
            );
            List<OrderDetail> orderDetails = new ArrayList<>();
            orderDetails.add(new OrderDetail(
                    order,
                    new Product(
                            "P-Test-1",
                            "",
                            "http://localhost/test/jpg.jpg",
                            5,
                            BigDecimal.valueOf(23),
                            BigDecimal.valueOf(15),
                            BigDecimal.ZERO
                    ),
                    1,
                    BigDecimal.valueOf(23)
            ));
            orderDetails.add(new OrderDetail(
                    order,
                    new Product(
                            "P-Test-2",
                            "",
                            "http://localhost/test/jpg1.jpg",
                            5,
                            BigDecimal.valueOf(45),
                            BigDecimal.valueOf(15),
                            BigDecimal.ZERO
                    ),
                    1,
                    BigDecimal.valueOf(45)
            ));
            order.setDetails(orderDetails);

            when(orderService.save(any(Order.class))).thenReturn(order);

            this.mvc.perform(MockMvcRequestBuilders.post("/api/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.convertToJSON(order))
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print());
            System.out.println("PASO");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getClass());
            System.out.println(ex.getMessage());
            System.out.println("NO PASO");
        }
    }*/

    private String convertToJSON(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(obj);
    }

}
