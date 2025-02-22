package org.api.events.service;

import org.api.events.service.emailservice.IEmailService;
import org.api.events.service.goldapi.IGoldSilverService;
import org.api.events.service.investmentservice.IInvestmentService;
import org.api.events.service.presentationservice.IPresentationService;
import org.api.events.service.receivingservice.IReceivingService;
import org.api.events.service.relativeservice.IRelativeService;
import org.api.events.service.userservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestServices {


    @Autowired
    private IUserService userService;

    @Autowired
    private IRelativeService relativeService;
    @Autowired
    private IReceivingService receivingService;
    @Autowired
    private IPresentationService presentationService;
    @Autowired
    private IInvestmentService investmentService;
    @Autowired
    private IGoldSilverService goldSilverService;
    @Autowired
    private IEmailService emailService;








}





//@SpringBootTest
//class ProductControllerTest {
//    @Autowired
//    private ProductController productController;
//
//    @MockBean
//    private IProductService productService;
//
//    @Captor
//    private ArgumentCaptor<Long> idCaptor;

//@Test
//@DisplayName("get product with id 4 will run fine")
//public void Test_GetProductById_ReturnsProductSuccessfully() {
//    //Arrange
//    Long productId = 4L;
//    Product product = new Product();
//    product.setId(productId);
//    product.setName("Iphone");
//    when(productService.getProductById(productId)).thenReturn(product);
//
//
//    //Act
//    ResponseEntity<ProductDto> response =  productController.findProductById(productId);
//
//    //Assert
//    assertNotNull(response);
//    assertNotNull(response.getBody());
//    assertEquals(productId,response.getBody().getId());
//    assertEquals("Iphone",response.getBody().getName());
//    verify(productService,times(1)).getProductById(productId);
//}
//
//
//@Test
//public void Test_GetProductById_CalledWithInvalidId_ResultsInIllegalArgumentException() {
//
//    //Act and Assert
//    Exception exception = assertThrows(IllegalArgumentException.class,
//            () -> productController.findProductById(-1L));
//
//    assertEquals("Please try with productId > 0",exception.getMessage());
//    //verify(productService,times(0)).getProductById(-1L);
//}
//
//@Test
//public void Test_GetProductById_ProductServiceCalledWithCorrectArguments_RunSuccessfully() {
//    //Arrange
//    Long productId = 4L;
//    Product product = new Product();
//    product.setId(productId);
//    product.setName("Iphone");
//    when(productService.getProductById(productId)).thenReturn(product);
//
//
//    //Act
//    productController.findProductById(productId);
//
//    //Assert
//    verify(productService).getProductById(idCaptor.capture());
//    assertEquals(4L,idCaptor.getValue());
//}
//
//}