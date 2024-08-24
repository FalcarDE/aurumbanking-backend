# support-service


## Support Service Backend

### Support Domain Model


    data class SupportDomainModel(
        var id : Long = 0,
        var customerId : Long = 0,
        var dateTime: LocalDateTime,
        var type : String,
        var message: String
    ) {
        constructor() : this(
            id = 0,
            customerId = 0,
            dateTime = LocalDateTime.now(),
            type = "",
            message = ""
        )
    }

The SupportDomainModel class represents a support request in the system, containing details such as the request ID, customer ID, timestamp, type of inquiry, and the support message

### Support Interface Repository

    interface SupportInterfaceRepository {
    
        fun findSupportRequestByCustomerId(customerId: Long): Uni<List<SupportDomainModel>>
        fun findSupportRequestById(id: Long): Uni<SupportDomainModel?>
    
        fun persistNewSupportInformation(Support: SupportDomainModel): Uni<SupportDomainModel>
    
        fun deleteSupportRequestById(id: Long): Uni<Boolean>
        fun deleteAllSupportRequestByCustomerId(customerId: Long): Uni<Long>
    
        fun getAllRequestsByType(type: String): Uni<List<SupportDomainModel>>
    
    }

The SupportInterfaceRepository defines a contract for managing support requests in the system, providing methods for retrieving, persisting, and deleting support requests based on various criteria, such as customer ID, request ID, and type of request. All operations return a reactive Uni type to support asynchronous, non-blocking operations.

### Important Use Cases for Support Requests
    @ApplicationScoped
    class GetAllSupportRequestsByType(val supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(type: String): Uni<List<SupportDomainModel>> =
    supportInterfaceRepository.getAllRequestsByType(type)
    }
    
    @ApplicationScoped
    class GetSupportRequestByCustomerId(var supportInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(customerId: Long): Uni<List<SupportDomainModel>> =
    supportInterfaceRepository.findSupportRequestByCustomerId(customerId)
    }
    
    @ApplicationScoped
    class DeleteRequestByCustomerIdUc(var depotInterfaceRepository: SupportInterfaceRepository) {
    operator fun invoke(customerId: Long ): Uni<Long> =
    depotInterfaceRepository.deleteAllSupportRequestByCustomerId(customerId)
    }



The following are some key use cases within the AurumBanking system for managing customer support requests. These use cases interact with the `SupportInterfaceRepository` to perform essential business operations related to customer inquiries and support.

#### Use Case: Get All Support Requests by Type

This use case is responsible for retrieving all support requests that match a given type. For example, it can be used to fetch all technical support requests or account-related inquiries. It utilizes the `getAllRequestsByType` method from the repository to perform this operation.

---

#### Use Case: Get Support Request by Customer ID

This use case fetches all the support requests associated with a particular customer, identified by their customer ID. This is useful for providing a comprehensive view of all the issues or inquiries submitted by a specific customer. The `findSupportRequestByCustomerId` method in the repository is used to gather this information.

---

#### Use Case: Delete All Support Requests by Customer ID

This use case handles the deletion of all support requests submitted by a specific customer. This is typically used when a customer's records need to be cleared from the system for a given reason. The operation leverages the `deleteAllSupportRequestByCustomerId` method in the repository to ensure that all related requests are removed.

### Model Mapper: Data Conversion Functions: Domain to Entity and Entity to Domain

    fun SupportDomainModel.toEntity() =
    SupportEntityModel().apply {
    id                      = this@toEntity.id
    customerId              = this@toEntity.customerId
    dateTime                = this@toEntity.dateTime
    type                    = this@toEntity.type
    message                 = this@toEntity.message
    }
    
    
    fun SupportEntityModel.toDomain() =
    SupportDomainModel().apply {
    id                      = this@toDomain.id
    customerId              = this@toDomain.customerId
    dateTime                = this@toDomain.dateTime
    type                    = this@toDomain.type
    message                 = this@toDomain.message
    }


The following functions handle the conversion between the `SupportDomainModel` and `SupportEntityModel`. These conversions are essential in scenarios where we need to translate data between the domain layer (business logic) and the entity layer (typically linked to the database).

#### Domain to Entity Conversion

The `toEntity()` function converts a `SupportDomainModel` into a `SupportEntityModel`. This is usually done when preparing data for persistence in the database.

- **Purpose**: Converts a domain model into its corresponding entity model.
- **Usage**: This function is used when saving or updating records in the database, translating the domain-level object into an entity that can be stored.

#### Entity to Domain Conversion

The `toDomain()` function converts a `SupportEntityModel` into a `SupportDomainModel`. This conversion is necessary when retrieving data from the database and preparing it for use in the application's business logic.

- **Purpose**: Converts an entity model back into its corresponding domain model.
- **Usage**: This function is typically used when loading data from the database, transforming the stored entity into a domain model that the application can work with.

### Support Entity Model
    @Data
    @Entity
    @Table(name = "Support", schema = "public")
    class SupportEntityModel() {
    
        @Id
        @GeneratedValue
        var id: Long = 0
    
        @Column(name = "customer_id")
        var customerId : Long = 0
    
        @Column(length = 2500)
        lateinit var dateTime: LocalDateTime
        lateinit var type : String
        lateinit var message: String
    }

The `SupportEntityModel` class represents the entity model used for persisting support request data in the database. This model is mapped to a database table named `Support` in the `public` schema.

#### Annotations

- **@Data**: This annotation generates standard getter and setter methods, as well as `toString()`, `hashCode()`, and `equals()` methods.
- **@Entity**: Marks the class as a JPA entity, indicating that it is a representation of a database table.
- **@Table(name = "Support", schema = "public")**: Specifies the table name and schema in the database that this entity maps to.

#### Fields

- **id** (`Long`): This is the primary key for the entity. It is automatically generated using the `@GeneratedValue` annotation.
- **customerId** (`Long`): This field stores the ID of the customer associated with the support request. It is mapped to the `customer_id` column in the database.
- **dateTime** (`LocalDateTime`): The timestamp when the support request was made. This field is required and mapped to a column with a length constraint of 2500 characters.
- **type** (`String`): A string representing the type of support request (e.g., technical issue, account inquiry). It is a required field.
- **message** (`String`): The message or content of the support request, containing the details of the inquiry or issue. This field is also required.

### Support Repository Implementation: three important examples

    override fun deleteAllSupportRequestByCustomerId(customerId: Long): Uni<Long> =
        this.delete("delete from SupportEntityModel p where p.customerId = ?1", customerId)

    override fun getAllRequestsByType(type: String): Uni<List<SupportDomainModel>> {
        return this.find("type", type).list<SupportEntityModel>()
            .onItem().transform { entities -> entities.map { it.toDomain() } }
    }

    override fun findSupportRequestByCustomerId(customerId: Long): Uni<List<SupportDomainModel>> {
        return this.find("customerId", customerId).list<SupportEntityModel>()
            .onItem().transform { entities -> entities.map { it.toDomain() } }

The following methods represent the implementation of core support request operations in the repository. These methods utilize reactive programming concepts provided by `Uni` to handle non-blocking, asynchronous database interactions.

#### deleteAllSupportRequestByCustomerId

This method deletes all support requests associated with a specific customer ID.

- **Method Signature**: `deleteAllSupportRequestByCustomerId(customerId: Long): Uni<Long>`
- **SQL Query**: Executes a `DELETE` statement targeting all support requests where the `customerId` matches the provided ID.
- **Return Type**: Returns a `Uni<Long>` representing the number of deleted records.
- **Usage**: Used when clearing all support requests tied to a specific customer.

#### getAllRequestsByType

This method retrieves all support requests that match a specific request type.

- **Method Signature**: `getAllRequestsByType(type: String): Uni<List<SupportDomainModel>>`
- **Implementation**:
  - Finds all support requests in the database where the `type` matches the provided value.
  - Transforms the resulting list of `SupportEntityModel` into a list of `SupportDomainModel` using the `toDomain()` conversion function.
- **Return Type**: Returns a `Uni<List<SupportDomainModel>>`, a list of domain models for further business logic use.
- **Usage**: Employed when filtering support requests by a particular type (e.g., technical issues or inquiries).

#### findSupportRequestByCustomerId

This method retrieves all support requests associated with a specific customer ID.

- **Method Signature**: `findSupportRequestByCustomerId(customerId: Long): Uni<List<SupportDomainModel>>`
- **Implementation**:
  - Finds all support requests where the `customerId` matches the provided value.
  - Transforms the resulting list of `SupportEntityModel` into a list of `SupportDomainModel` using the `toDomain()` conversion function.
- **Return Type**: Returns a `Uni<List<SupportDomainModel>>`, allowing for further processing or display in the application.
- **Usage**: Useful for fetching all support inquiries made by a specific customer.

### Support Resource

    @GET
    @Path("/get-all-support-reqeuests-by-type/{requestType}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getAllSupportRequestsByType(@PathParam("requestType") requestType: String): Uni<RestResponse<List<SupportDomainModel>>> {
    return getSupportRequestsByTypeUc(requestType)
    .onItem().transform { RestResponse.ok(it) }
    .onFailure().recoverWithItem(RestResponse.serverError())
    }

    @GET
    @Path("/get-all-support-reqeuests-by-customer-id/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getAllSupportRequestsByCustomerId(@PathParam("customerId") customerId: Long): Uni<RestResponse<List<SupportDomainModel>>> {
        return getSupportRequestByCustomerIdUc(customerId)
            .onItem().transform { RestResponse.ok(it) }
            .onFailure().recoverWithItem(RestResponse.serverError())
    }

    @DELETE
    @Path("/deleteRequestByCustomerId/{customerId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun deleteRequestByCustomerId(@PathParam("customerId") customerId: Long): Uni<RestResponse<Void>> {
        return deleteRequestByCustomerIdUc(customerId).replaceWith{ RestResponse.ok()}
    }

### REST Endpoints: Support Request Management

The following REST endpoints provide functionality for managing support requests in the AurumBanking system. These endpoints handle operations such as retrieving support requests by type or customer ID, and deleting support requests for a specific customer.

#### Get All Support Requests by Type

- **Endpoint**: `GET /get-all-support-requests-by-type/{requestType}`
- **Produces**: `application/json`
- **Functionality**: Retrieves all support requests that match the specified request type.
- **Path Parameter**:
  - `requestType` (String): The type of support requests to retrieve (e.g., "technical", "account").
- **Return Type**: Returns a `Uni<RestResponse<List<SupportDomainModel>>>`, which is a list of support requests in JSON format.
- **Error Handling**: If the retrieval fails, the method recovers by returning a server error response.

#### Get All Support Requests by Customer ID

- **Endpoint**: `GET /get-all-support-requests-by-customer-id/{customerId}`
- **Produces**: `application/json`
- **Functionality**: Retrieves all support requests associated with a specific customer ID.
- **Path Parameter**:
  - `customerId` (Long): The ID of the customer whose support requests are being retrieved.
- **Return Type**: Returns a `Uni<RestResponse<List<SupportDomainModel>>>`, which is a list of support requests for the specified customer in JSON format.
- **Error Handling**: If the retrieval fails, the method recovers by returning a server error response.

#### Delete All Support Requests by Customer ID

- **Endpoint**: `DELETE /deleteRequestByCustomerId/{customerId}`
- **Produces**: `application/json`
- **Functionality**: Deletes all support requests associated with the specified customer ID.
- **Path Parameter**:
  - `customerId` (Long): The ID of the customer whose support requests are being deleted.
- **Return Type**: Returns a `Uni<RestResponse<Void>>`, indicating the success of the operation.
- **Error Handling**: If the deletion fails, the method recovers by returning a server error response.
- **Notes**: The path parameter is validated to ensure it is a numeric value using the regex pattern `\d+`.


## Connection to the Backend

The `SupportViewModel` manages communication between the UI and backend services, specifically for retrieving customer information and submitting support requests. It uses Retrofit for making asynchronous API calls and coroutines to handle background tasks efficiently.

### Functions

#### 1. getCustomerInformationByCustomerId

**Purpose:**

This function retrieves customer information from the backend based on a provided customer ID.

**Parameters:**

- `customerId: Long`: The ID of the customer whose information needs to be fetched.
- `callback: (SimpleCustomerInfo?) -> Unit`: A callback function that processes the result of the customer information fetch. It returns a `SimpleCustomerInfo` object on success or `null` on failure.

**Implementation:**

This function is executed within a coroutine on the `Dispatchers.IO` context to ensure the network operation runs in the background. It uses Retrofit's `enqueue()` method to handle the asynchronous API response. On a successful HTTP response (status `200`), the customer information is mapped to a `SimpleCustomerInfo` object containing the customer's name and email. If the response is unsuccessful (non-200 status or null body), an error is logged, and the callback is invoked with `null`.

#### 2. submitSupportRequest

**Purpose:**

This function submits a support request to the backend.

**Parameters:**

- `request: SupportRequestSendToBackend`: The support request data that is sent to the backend.
- `callback: (Boolean) -> Unit`: A callback function that processes the result of the submission. It returns `true` on success or `false` on failure.

**Implementation:**

This function is executed within a coroutine and uses Retrofit's `enqueue()` method to handle the asynchronous submission of the support request. If the backend responds with HTTP status `201 Created`, the callback is triggered with `true`. If the submission fails (due to network issues or a non-successful response code), the callback is triggered with `false`, and an error is logged.

### Error Handling

All API calls handle errors via Retrofit's `onFailure()` method. When a failure occurs, an error message is logged, and the appropriate failure response is returned (`null` for `getCustomerInformationByCustomerId` and `false` for `submitSupportRequest`). Any exceptions, such as network issues, are caught, logged, and handled to ensure the stability of the ViewModel.

### Coroutine Scope

The ViewModel uses `viewModelScope` to manage coroutines, ensuring that ongoing network operations are automatically canceled if the ViewModel is cleared. This prevents memory leaks and ensures that operations are tied to the lifecycle of the ViewModel.

### Dependencies

- **`CustomerInformationService`**: Used to retrieve customer information based on a customer ID.
- **`SupportService`**: Used to submit support requests to the backend.
- **`Retrofit`**: Handles asynchronous network requests via the `enqueue()` method.
- **`Dispatchers.IO`**: Ensures that network operations run on a background thread, keeping the UI responsive.


##  SupportRequestScreen Backend Interactions

### 1. **Fetching Customer Information**

- **Functionality:**

  When the screen is loaded, it automatically fetches the customer's information (name and email) from the backend based on the `customerId` stored in the session (`SessionManager.customerId`).

- **Backend Call:**

  The function `getCustomerInformationByCustomerId(customerId)` from the `SupportViewModel` is used to make this call to the backend. This function retrieves customer data via an asynchronous API call, which populates the `legalFullName` and `email` fields of the form.

- **Trigger:**

  This backend call is triggered within a `LaunchedEffect` block when the composable is first rendered.

### 2. **Submitting a Support Request**

- **Functionality:**

  After the user fills out the form, the support request is submitted to the backend. The data sent includes the `customerId`, the `type` of the support request, and the user's `message`.

- **Backend Call:**

  The function `submitSupportRequest` from the `SupportViewModel` is responsible for sending the support request to the backend. It makes an asynchronous API call, encapsulating the request data in a `SupportRequestSendToBackend` object.

- **Trigger:**

  The submission is triggered by a button click, where the form data (customer ID, type, and message) is gathered and sent to the backend.

### Data Model: `SupportRequestSendToBackend`

- **Purpose:**

  This data class represents the payload sent to the backend when submitting a support request.

- **Fields:**

  - `customerId`: The ID of the customer sending the request.
  - `dateTime`: The timestamp of when the request is made.
  - `type`: The type of the support request (e.g., "Überweisung", "Benutzerdaten").
  - `message`: The message content provided by the user.

### Error Handling and Callbacks

- Both backend calls (fetching customer information and submitting a support request) handle errors and success via callbacks. On success, the data is processed accordingly (e.g., populating form fields or providing feedback on submission). On failure, appropriate error messages are logged or displayed.

