package com.is4tech.salesapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.is4tech.salesapi.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
class SalesapiApplicationTests {

	private MockMvc mvc;
	@Autowired
	private  final WebApplicationContext context;

	public SalesapiApplicationTests(WebApplicationContext context) {
		this.context = context;
	}

	@BeforeEach
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	private String convertToJSON(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(obj);
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
	void testGetCustomer() throws Exception {
		this.mvc.perform(
				MockMvcRequestBuilders.get("/api/customers/1")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
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

		this.mvc.perform(MockMvcRequestBuilders.post("/api/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().is4xxClientError())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void testGetProductById() throws Exception {
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

}
