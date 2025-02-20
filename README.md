# Event's Project

* Monolithic architecture
* Schema Design (Tables)
* Class Design (Classes and entitys)
* Cardinality
*

# ToDo

* [ ]  Schema Design
* [ ]  Class Design
* [ ]  Api Design and Documentation
* [ ]  Basic application build with frontend
  * [ ]  Create a new user with presentations
* [ ]  Add more Features
  * [ ]  Filters (Querys last 1,2,3,6 months Presentations and Recevings)
  * [ ]  Filters (Querys last 1,2, 3, 4, and Life Time Presentations and Recevings)
  * [ ]  Filter Upcoming Presentation worth
  * [ ]  Filter Total Recevied presentations worth
* [ ]  Charts
  * [ ]  Showing charts with events
* [ ]  My Event management
  * [ ]  Total worth

# Querying

1. GetAllRelatives --
2. GetAllPresentations --
3. GetAllRecevicings --
4. GetAllCitys --
5. GetAllGoldRecevings
6. GetAllGoldPresentations
7. GetAllSiliverRecevings
8. GetAllSiliverPresentations
9. GetAllAmountRecevings
10. GetAllAmountPresentations
11. GetAllGiftsRecevings    ---- Total Number
12. GetAllGiftPresentations ---- Total Number
13. GetRelativesBasedOnCity
14. GetTotalGoldPresentations --
15. GetTotalGoldReciving --
16. GetTotalAmountRecving --
17. GetTotalAmountPresentation --
18. GetTotalSiliverPresentation --
19. GetTotalSiliverReceiving --
20. GetTotalNumberOfGiftsPresentated --
21. GetTotalNumberOfGiftsRecevings --

```sql
 //  @Query("SELECT new org.api.events.dto.AllCitysDto(r.city) FROM relative  r WHERE r.city = :city")
    @Query("SELECT DISTINCT new org.api.events.dto.AllCitysDto(r.city) FROM relative r")
    List<AllCitysDto> findAllByCity();
# Above Query Return the Dto type, By filtering Dto constructor params with absoulte path
```

# Auth

1. SignUp through Email
2. Singup Through PhoneNumber
3. Email and PhoneNumber verification (make email verfication only)

# OTP

![image.png](assets/image.png)

# Schema Design

# Class design

Log trace print thick bold

```
log.info("\u001B[1;32m :: Testing relative :: \u001B[0m");
```

![image.png](assets/classdesign.png)

Architected a Microservices based ECommerce Platform with functionalities like Login, Authorization, Product Catalog, Payment Gateway Integration, Service Discovery, Notification Service etc.
Deployed whole Ecosystem on AWS Elastic Beanstalk leveraging RDS for Data Persistence, CloudWatch for efficient Logging and Monitoring
Implemented Event Driven Email Service using Kafka to allow sending emails at large scale across different services within the Platform.
Implemented powerful sorting, filtering and paging to allow for efficient discovery of products.
Optimized the response time of APIs from ~500 ms to ~20 ms by making effective usage of Caching for static data using Redis Cache.
Tools and Frameworks Used: Spring Boot, Spring Cloud, MySQL, Hibernate, Redis, Razorpay Payment Gateway, Stripe Payment Gateway, JUnit, Kafka.
