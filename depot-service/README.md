# depot-service

## Depot Service Backend

### Depot Service Domain Model


      data class DepositDomainModel(
          var id: Long = 0,
          var customerId : Long = 0,
          var currencyArea: String,
          var depositAmount: BigDecimal = BigDecimal.ZERO,
          var fallbackDepositAmount: BigDecimal,
      ) {
        constructor() : this(
            id = 0,
            customerId = 0,
            currencyArea = "",
            depositAmount = BigDecimal(0.00),
            fallbackDepositAmount = BigDecimal(1500.75),
        )
      }

      data class IncomingTransactionDTO(
          val depotId: Long, 
          val moneyValue: BigDecimal, 
          var transactionType: String
      )


+ The DepositDomainModel contains all relevant depot information such as Depot Id, Customer Id, currency area, deposit amount
  and fallback amount.
+ The IncomingTransactionDTO works as a transfer model to be able to update the deposit value once
  a new transaction was created and contains the basic transaction information such as depot Id, transaction value (moneyValue)
  and transaction type.


### Depot Interface Repository

    interface DepotInterfaceRepository {
    
        fun findDepotByCustomerId(id: Long): Uni<DepotDomainModelDTO>
    
        fun findDepotById(id: Long): Uni<DepositDomainModel?>
    
        fun findCurrentDepotValueById(id: Long): Uni<DepotDTO>
    
        fun findCurrentDepotValueByCustomerId(CustomerId: Long): Uni<DepotDTO>
    
        fun findCurrentDepotFallBackValueById(id: Long): Uni<FallbackDepositAmountDTO>
    
        fun persistNewDepotInformation(depotDomainModel: DepositDomainModel): Uni<DepositDomainModel>
    
        fun updateDepositValueByDepotId(id: Long, value: BigDecimal): Uni<DepotDTO>
    
        fun updateFallbackDepositAmount(id: Long): Uni<FallbackDepositAmountDTO>
    
        fun deleteDepotById(id: Long): Uni<Long>
    
    }

The SupportInterfaceRepository defines a contract for managing depot requests in the system, providing methods
for retrieving, persisting, and deleting depots based on the two main criteria depot ID and customer ID. All operations
return a reactive Uni type to support asynchronous, non-blocking operations.


### Important Use Cases for Depot Requests


      @ApplicationScoped
      class GetCurrentDepotValueByIdUc(var depotInterfaceRepository: DepotInterfaceRepository) {
      operator fun invoke(id: Long): Uni<DepotDTO> =
      depotInterfaceRepository.findCurrentDepotValueById(id)
      }
      
      
      @ApplicationScoped
      class InsertNewDepotUc(var depotInterfaceRepository: DepotInterfaceRepository) {
      operator fun invoke(depositDomainModel : DepositDomainModel): Uni<DepositDomainModel> =
      depotInterfaceRepository.persistNewDepotInformation(depositDomainModel)
      }
      
      
      @ApplicationScoped
      class UpdateDepositValueByIdUc(var depotInterfaceRepository: DepotInterfaceRepository) {
      operator fun invoke(id: Long, depositValue : BigDecimal ): Uni<DepotDTO> =
      depotInterfaceRepository.updateDepositValueByDepotId(id, depositValue)
      }
      
      @ApplicationScoped
      class GetDepotByCustomerIdUc(var depotInterfaceRepository: DepotInterfaceRepository) {
      operator fun invoke(id: Long): Uni<DepotDomainModelDTO> =
      depotInterfaceRepository.findDepotByCustomerId(id)
      }

The following use cases are central elements to use the AurumBanking business logic. They interact with the
**DepotInterfaceRepository** to perform key operations to handle all activities related to the target object of
every transaction, the depot.

#### Use Case: Get Current Depot Value By ID

This use case retrieves the current value of a depot by its ID. It interacts with the repository to fetch the required
DepotDTO object. It is mainly used to display the current depot value within the **DepotOverviewBox**.

#### Use Case: Insert New Depot

This use case handles the insertion of a new depot. It takes a DepositDomainModel object as input and persists it through
the repository. An existing depot is a key element to interact with the AurumBanking app and to use all key functions.

#### Use Case: Update Deposit Value By ID

This use case updates the deposit value of an existing depot identified by its ID. It accepts a new deposit value (BigDecimal)
and returns an updated DepotDTO. It is used to update the depot value after every new transaction.

#### Use Case: Get Depot By Customer ID
This use case retrieves depot information based on a customer's ID. It queries the repository to obtain a DepotDomainModelDTO
corresponding to the specified customer ID.


### Model Mapper: Data Conversion Functions: Domain to Entity and Entity to Domain

    fun DepositDomainModel.toEntity() =
      DepotEntityModel().apply {
        id = this@toEntity.id
        customerId = this@toEntity.customerId
        currencyArea = this@toEntity.currencyArea
        depositAmount = this@toEntity.depositAmount
        fallbackDepositAmount = this@toEntity.fallbackDepositAmount
    }
    
    fun DepotEntityModel.toDomain() =
      DepositDomainModel().apply {
        id = this@toDomain.id
        customerId = this@toDomain.customerId
        currencyArea = this@toDomain.currencyArea
        depositAmount = this@toDomain.depositAmount
        fallbackDepositAmount = this@toDomain.fallbackDepositAmount
    }

The following functions handle the conversion between the `DepositDomainModel` and `DepotEntityModel`. These
conversions are essential in scenarios where we need to translate data between the domain layer (business logic) and
the entity layer (typically linked to the database).


#### Domain to Entity Conversion

The `toEntity()` function converts a `DepositDomainModel` into a `DepotEntityModel`. This is usually done when
preparing data for persistence in the database.

- **Purpose**: Converts a domain model into its corresponding entity model.
- **Usage**: This function is used when saving or updating records in the database, translating the domain-level object
  into an entity that can be stored.

#### Entity to Domain Conversion

The `toDomain()` function converts a `DepotEntityModel` into a `DepositDomainModel`. This conversion is necessary
when retrieving data from the database and preparing it for use in the application's business logic.

- **Purpose**: Converts an entity model back into its corresponding domain model.
- **Usage**: This function is typically used when loading data from the database, transforming the stored entity into
  a domain model that the application can work with.

### Depot Entity Model

      @Data
      @Entity
      @Table(name = "Depot", schema = "public")
      class DepotEntityModel : PanacheEntityBase() {
      
          @Id
          @GeneratedValue(strategy = GenerationType.SEQUENCE)
          var id: Long = 0
      
          @Column(length = 2500)
          var customerId: Long = 0
          lateinit var currencyArea: String
          lateinit var depositAmount: BigDecimal
          lateinit var fallbackDepositAmount: BigDecimal
      
      }
      
      @RegisterForReflection
      data class DepotDTO(val depositAmount: BigDecimal)
      
      @RegisterForReflection
      data class FallbackDepositAmountDTO(val fallbackDepositAmount: BigDecimal)
      
      
      @RegisterForReflection
      data class DepotDomainModelDTO(
      val id: Long,
      val customerId: Long,
      val currencyArea: String,
      val depositAmount: BigDecimal,
      val fallbackDepositAmount: BigDecimal
      )


The `DepotEntityModel` class represents the entity model used for persisting depot data in the database.
This model is mapped to a database table named `Depot` in the `public` schema.

#### Annotations

- **@Data**: This annotation generates standard getter and setter methods, as well as `toString()`, `hashCode()`,
  and `equals()` methods.
- **@Entity**: Marks the class as a JPA entity, indicating that it is a representation of a database table.
- **@Table(name = "Depot", schema = "public")**: Specifies the table name and schema in the database that this entity
  maps to.

#### Fields

- **id** (`Long`): This is the primary key for the entity. It is automatically generated using the `@GeneratedValue` annotation.
- **customerId** (`Long`): This field stores the ID of the customer associated with the support request. It is mapped to the `customer_id` column in the database.
- **currencyArea** (`String`): A String to define the base currency for depot transactions (e.g. "EUR" for €-transaction).
- **depositAmount** (`BigDecimal`): A BigDecimal Value to define the current depot value.
- **fallbackDepositAmount** (`BigDecimal`): A BigDecimal Value to define the last deposit value before a new transaction
  to have a fallback value in case of operational inconsistancies.


### Depot Repository Implementation: examples

      override fun findCurrentDepotValueById(id: Long): Uni<DepotDTO> {
          return find("select depositAmount from DepotEntityModel p where p.id = ?1", id).project(DepotDTO::class.java)
          .firstResult()
      }
      
      
      override fun updateDepositValueByDepotId(id: Long, value: BigDecimal): Uni<DepotDTO> {
          return this.update(
              "update DepotEntityModel set depositAmount = :depositAmount where id = :id",
              Parameters.with("id", id).and("depositAmount", value)
          ).flatMap {
              findCurrentDepotValueById(id)
          }
      }


The following methods represent the implementation of core support request operations in the repository. These methods
utilize reactive programming concepts provided by `Uni` to handle non-blocking, asynchronous database interactions.


#### findCurrentDepotValueById

This method delivers the deposit amount for a given depot Id.

- **Method Signature**: `findCurrentDepotValueById(id: Long): Uni<DepotDTO>`
- **SQL Query**: Executes a `SELECT` statement targeting all the depot where the `Id` matches the provided ID.
- **Return Type**: Returns a `Uni<DepotDTO>` representing the depot that includes the searched Id to access the deposit amount.
- **Usage**: Used to display the current deposit value on Overview- and DepotScreen.

#### updateDepotsitValueById

This method delivers the deposit amount for a given depot Id.

- **Method Signature**: `updateDepositValueByDepotId(id: Long, value: BigDecimal): Uni<DepotDTO>`
- **SQL Query**: Executes a `UPDATE` statement targeting all the depot where the `Id` matches the provided ID and delivers the
  new deposit amount for the given depot.
- **Return Type**: Returns a `Uni<DepotDTO>` representing the depot that includes the searched Id to access the updated depot.
- **Usage**: Used to update the effected depot after every new successful transaction.


### Depot Resource

+ List of all endpoint:

      @GET
      @Path("/{id:\\d+}")
      @Produces(MediaType.APPLICATION_JSON)
      @WithSession
      fun getDepotById(@PathParam("id") id: Long) = getDepotByIdUc(id)
      .onItem().ifNotNull().transform { RestResponse.ok(it) }
      .onItem().ifNull().continueWith(RestResponse.notFound())

      @GET
      @Path("/getDepotByCustomerId/{id:\\d+}")
      @Produces(MediaType.APPLICATION_JSON)
      @WithSession
      fun getDepotByCustomerId(@PathParam("id") id: Long) = getDepotByCustomerIdUc(id)
          .onItem().ifNotNull().transform { RestResponse.ok(it) }
          .onItem().ifNull().continueWith(RestResponse.notFound())
  
      @GET
      @Path("/getCurrentDepotValueById/{id:\\d+}")
      @Produces(MediaType.APPLICATION_JSON)
      @WithSession
      fun getCurrentDepotById(@PathParam("id") id: Long) = getCurrentDepotValueByIdUc(id)
          .onItem().ifNotNull().transform { RestResponse.ok(it) }
          .onItem().ifNull().continueWith(RestResponse.notFound())
  
  
      @POST
      @Consumes(MediaType.APPLICATION_JSON)
      @WithTransaction
      fun insert(depositDomainModel: DepositDomainModel): Uni<RestResponse<Void>> {
          return insertNewDepotUc(depositDomainModel).map {
              RestResponse.created(URI("/depot/${it.id}"))
          }
      }
  
      @PUT
      @Consumes(MediaType.APPLICATION_JSON)
      @Path("/insertNewDepositValueById/{id:\\d+}/{value:\\d+([,\\.]\\d+)}")
      @WithTransaction
      fun insertNewDepositValueById(
          @PathParam("id") id: Long,
          @PathParam("value") value: BigDecimal
      ): Uni<RestResponse<DepotDTO>>? {
          return updateDepositValueByIdUc(id, value)
              .onItem().ifNotNull().transform { RestResponse.ok(it) }
              .onItem().ifNull().continueWith(RestResponse.notFound())
      }
  
      @DELETE
      @Path("/deleteDepotBy/{id:\\d+}")
      @Produces(MediaType.APPLICATION_JSON)
      @WithTransaction
      fun deleteById(@PathParam("id") id: Long): Uni<RestResponse<Void>> {
          return deleteDepotByIdUc(id).replaceWith { RestResponse.ok() }
      }


### REST Endpoints: Support Request Management

The following REST endpoints provide functionality for managing depot functions in the AurumBanking system. These
endpoints handle operations such as retrieving depots by ID or customer ID.


#### Get Depot by ID

- **Endpoint**: 'GET /depot/{id}`
- **Produces**: `application/json`
- **Functionality**: Delivers a specific depot by for a given depot Id.
- **Path Parameter**:
    - `Id` (Long): The Id of the searched depot.
- **Return Type**: Returns a `Uni<DepositDomainModel>>>`, which is the depot object in JSON format.
- **Response:**
    - `200 OK`: The depot was called successfully.
    - `404 Not Found`: No depot with the corresponding ID found.
- **Error Handling**: If the retrieval fails, the method recovers by returning a server error response.


#### Get Current Depot Value By Id

- **Endpoint**: 'GET /GetCurrentDepotValueById/{id:\\d+}'`
- **Produces**: `application/json`
- **Functionality**: Delivers a specific depot value by for a given depot Id.
- **Path Parameter**:
    - `Id` (Long): The Id of the searched depot.
- **Return Type**: Returns a `Uni<DepotDTO>>>`, which is the specific object in JSON format to return the depot value.
- **Response:**
    - `200 OK`: The depot was called successfully.
    - `404 Not Found`: No depot with the corresponding ID found.
- **Error Handling**: If the retrieval fails, the method recovers by returning a server error response.


#### Insert New Deposit Value By Id

- **Endpoint**: 'PUT /insertNewDepositValueById/{id:\\d+}/{value:\\d+([,\\.]\\d+)}`
- **Produces**: `application/json`
- **Functionality**: Updates the deposit value for a given depot and delivers the updated depot.
- **Path Parameter**:
    - `Id` (Long): The Id of the searched depot.
    - `value` (BigDecimal): The new deposit value.
- **Return Type**: Returns a `Uni<DepositDomainModel>>>`, which is the depot object in JSON format.
- **Response:**
    - `200 OK`: The depot was called successfully.
    - `404 Not Found`: No depot with the corresponding ID found.
- **Error Handling**: If the retrieval fails, the method recovers by returning a server error response.

## Connection Frontend Backend

### DepotViewModel - AurumBanking Backend Connection

The `DeptoViewModel` connects the **AurumBanking** app's UI with backend services, facilitating depot key interactions.

#### Backend Interactions

- **`getDepotValueToScreen(overviewViewModel: OverviewViewModel, customerId: Long): BigDecimal?`**
  - **Purpose**: gets the deposit value to display it on DepotOverviewBox.
  - **Backend Service**: `depot-service`
    - **Description**: Sends a request to the backend to get the deposit value for the given Id using coroutines to 
      manage the asynchronous task. The result of the operation is returned via a callback function, handling any 
      exceptions that may occur.


#### Key Concepts

- **Retrofit**: Used to manage HTTP requests for fetching and updating depot information from the backend services.
- **Coroutines**: Employed for asynchronous execution, ensuring that UI operations are non-blocking and efficient.
- **Callbacks**: Provide the UI layer with feedback on the success or failure of the backend operations.

