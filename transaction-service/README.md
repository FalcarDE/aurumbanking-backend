# transaction-service

## Transaction Service Backend

### Transaction Domain Model


    data class TransactionDomainModel(
         var id: Long = 0,
         var depotId: Long = 0,
         var created: LocalDateTime,
         var country: String = "",
         var recipient: String = "",
         var iban: String = "",
         var bic: String = "",
         var moneyValue: BigDecimal = BigDecimal.ZERO,
         var purposeOfUse: String = "",
         var standingOrder: Boolean?,
         var transactionType: String,
         var transactionClassification: String,
         var dateTimeOfFirstExecution: Date,
         var dateTimeOfLastExecution: Date
         ) {
         constructor() : this(
         id = 0,
         depotId = 0,
         created = LocalDateTime.now(),
         country = "",
         recipient = "",
         iban = "",
         bic = "",
         moneyValue = BigDecimal.ZERO,
         purposeOfUse = "",
         standingOrder = false,
         transactionType = "",
         transactionClassification = "",
         dateTimeOfFirstExecution = Date(),
         dateTimeOfLastExecution = Date()
         )
    }

The `TransactionDomainModel` is a data class representing a financial transaction with various attributes such as ID, recipient details, transaction type, and execution dates. It includes a primary constructor for full initialization and a secondary constructor that provides default values for all fields.

### Transaction Interface Repository

      interface TransactionInterfaceRepository {
         fun findAllTransactionByDepotId(id: Long): Uni<List<TransactionDomainModel>>
         fun insertNewTransaction(transactionDomainModel: TransactionDomainModel) : Uni<TransactionDomainModel>
         fun getThreeLatestTransactionByDepotId(depotId: Long): Uni<List<TransactionDomainModel>>
         fun updateTransactionById(transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel>
         fun getTransactionById(id: Long): Uni<TransactionDomainModel>
         fun deleteTransactionById(id: Long): Uni<Long>
      }

The `TransactionInterfaceRepository` interface defines methods for interacting with transaction data in a repository. It includes functions for finding, inserting, updating, and deleting transactions, as well as retrieving specific transactions by ID and fetching the latest three transactions associated with a given depot ID. All methods return a `Uni` type, indicating they are asynchronous and non-blocking.

### Imprtant Use Cases for Support Requests


      @ApplicationScoped
         class GetAllTransactionsByDepotIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
         operator fun invoke(id: Long): Uni<List<TransactionDomainModel>> =
         transactionInterfaceRepository.findAllTransactionByDepotId(id)
      }
      
      @ApplicationScoped
         class UpdateTransactionsByIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
         operator fun invoke(transactionDomainModel: TransactionDomainModel): Uni<TransactionDomainModel>
         = transactionInterfaceRepository.updateTransactionById(transactionDomainModel)
      }
      
      @ApplicationScoped
         class GetTransactionsByIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
         operator fun invoke(id: Long): Uni<TransactionDomainModel> =
         transactionInterfaceRepository.getTransactionById(id)
      }
      
      @ApplicationScoped
         class DeleteTransactionByIdUc(var transactionInterfaceRepository: TransactionInterfaceRepository) {
         operator fun invoke(id: Long): Uni<Long> =
         transactionInterfaceRepository.deleteTransactionById(id)
      }

Below are key use cases within the AurumBanking system for handling transactions. These use cases work with the TransactionInterfaceRepository to execute crucial operations related to financial transactions.

#### Use Case: Retrieve All Transactions by Depot ID

This use case focuses on retrieving all transactions linked to a specific depot ID. It is useful for obtaining a complete list of transactions associated with a customer's account. The findAllTransactionByDepotId method from the repository is employed to gather this data.

---

#### Use Case: Modify Transaction by ID

This use case is designed to update an existing transaction by its ID. It plays a critical role in situations where transaction details need to be altered, such as updating transaction information or correcting errors. The updateTransactionById method within the repository facilitates this update, ensuring the transaction remains current.

---

#### Use Case: Retrieve Transaction by ID

This use case retrieves a particular transaction based on its ID. It is useful for accessing detailed information about a single transaction, whether for audit, verification, or support purposes. The repository’s getTransactionById method is utilized to fetch this specific transaction.

#### Use Case: Remove Transaction by ID

This use case deals with the removal of a transaction identified by its ID. It is typically invoked when a transaction needs to be deleted from the system, such as in cases of cancellation or error correction. The deleteTransactionById method in the repository ensures the transaction is effectively removed.

### Model Mapper: Data Conversion Functions: Domain to Entity and Entity to Domain

The following functions manage the conversion between `TransactionDomainModel` and `TransactionEntityModel`, facilitating the transition of data between the business logic layer and the persistence layer.

#### Domain to Entity Conversion

The `toEntity()` function converts a `TransactionDomainModel` into a `TransactionEntityModel`. This conversion is essential for preparing data for storage in a database or other persistence mechanisms.

- **Purpose**: Converts a domain model into its corresponding entity model, including handling special cases for transaction types and classifications.
- **Usage**: Used when saving or updating transaction records in the database. It translates domain-level attributes into a format suitable for storage, ensuring accurate representation of transaction types and classifications.

#### Entity to Domain Conversion

The `toDomain()` function converts a `TransactionEntityModel` into a `TransactionDomainModel`. This is necessary when loading data from storage and transforming it into a format suitable for business logic operations.

- **Purpose**: Converts an entity model back into its corresponding domain model, including converting transaction types and classifications to their display names.
- **Usage**: Used when retrieving data from the database, transforming the stored entity into a domain model that can be processed by the application’s business logic.

### Transaction Entity Model

The `TransactionEntityModel` class represents the entity model for storing transaction data in the database. This model maps to a database table named `Transaction` within the `public` schema.

#### Annotations

- **@Entity**: Designates the class as a JPA entity, indicating that it corresponds to a database table.
- **@Table(name = "Transaction", schema = "public")**: Specifies the name of the database table and schema that this entity maps to.
- **@Id**: Marks the field as the primary key for the entity.
- **@GeneratedValue**: Indicates that the primary key value is automatically generated.
- **@CreationTimestamp**: Automatically sets the creation timestamp when the entity is first persisted.
- **@Column(length = 2500)**: Specifies the maximum length of the column for the `depotId` field.
- **@Enumerated(EnumType.STRING)**: Specifies that the `transactionType` and `transactionClassification` fields are stored as strings in the database.

#### Fields

- **id** (`Long`): The primary key for the transaction entity, automatically generated.
- **created** (`LocalDateTime`): Timestamp of when the transaction record was created, set automatically.
- **depotId** (`Long`): Identifier for the depot associated with the transaction.
- **country** (`String`): The country related to the transaction.
- **recipient** (`String`): The name of the recipient for the transaction.
- **iban** (`String`): International Bank Account Number involved in the transaction.
- **bic** (`String`): Bank Identifier Code for the transaction.
- **moneyValue** (`BigDecimal`): The amount of money involved in the transaction.
- **purposeOfUse** (`String`): The purpose or reason for the transaction.
- **standingOrder** (`Boolean?`): Indicates if the transaction is a standing order; nullable.
- **transactionType** (`TransactionType`): The type of transaction, represented as a string.
- **transactionClassification** (`TransactionClassification`): The classification of the transaction, also represented as a string.
- **dateTimeOfFirstExecution** (`Date`): The date and time when the transaction is first executed.
- **dateTimeOfLastExecution** (`Date`): The date and time of the last execution of the transaction.

### Additional Data Transfer Objects (DTOs)

#### TransactionDTO

A data class representing a transaction with additional fields for customer and date details.

- **id** (`Long`): Unique identifier for the transaction.
- **depotId** (`Long`): Identifier for the associated depot.
- **customerId** (`Long`): Identifier for the customer associated with the transaction.
- **dateTime** (`Date`): Date and time when the transaction occurred.
- **flag** (`Boolean?`): An optional flag for additional transaction information.
- **moneyValue** (`BigDecimal`): Amount of money involved.
- **iban** (`String`): International Bank Account Number.
- **bic** (`String`): Bank Identifier Code.
- **type** (`String`): Type of transaction.

#### TransactionKafkaDTO

A data class used for Kafka messaging, representing key transaction details.

- **depotId** (`Long`): Identifier for the depot related to the transaction.
- **moneyValue** (`BigDecimal`): Amount of money involved in the transaction.
- **transactionType** (`String`): Type of transaction.

### Transaction Repository Implementation: important example


    @ApplicationScoped
    class TransactionRepositoryImp : PanacheRepositoryBase<TransactionEntityModel, Long>, TransactionInterfaceRepository {

    override fun findAllTransactionByDepotId(id: Long): Uni<List<TransactionDomainModel>> {
        return list("depotId", id)
            .onItem().transform { transactionListToDomain(it) }
    }

    override fun getTransactionById(id: Long): Uni<TransactionDomainModel> {
        return this.findById(id).onItem().ifNotNull().transform { it.toDomain() }
    }

    override fun deleteTransactionById(id: Long): Uni<Long> {
        return this.delete("delete from TransactionEntityModel p where p.id = ?1", id)
    }

#### findAllTransactionByDepotId

This method retrieves all transactions associated with a specified depot ID.

- **Method Signature**: `findAllTransactionByDepotId(id: Long): Uni<List<TransactionDomainModel>>`
- **Implementation**:


    - Uses a query to fetch all transactions where the `depotId` matches the given ID.
    - Converts the list of `TransactionEntityModel` into `TransactionDomainModel` using the `transactionListToDomain()` function.

- **Return Type**: Returns a `Uni<List<TransactionDomainModel>>`, which provides a list of transactions in the domain model format.
- **Usage**: Ideal for fetching a comprehensive list of transactions for a specific depot.

#### getTransactionById

This method retrieves a single transaction identified by its unique ID.

- **Method Signature**: `getTransactionById(id: Long): Uni<TransactionDomainModel>`
- **Implementation**:


    - Finds a transaction by its ID using the `findById()` method.
    - Transforms the `TransactionEntityModel` to `TransactionDomainModel` if found.

- **Return Type**: Returns a `Uni<TransactionDomainModel>`, representing the transaction in the domain model format.
- **Usage**: Useful for fetching detailed information about a specific transaction.

#### deleteTransactionById

This method deletes a transaction by its unique ID.

- **Method Signature**: `deleteTransactionById(id: Long): Uni<Long>`
- **Implementation**:


    - Executes a `DELETE` SQL statement to remove the transaction with the specified ID.

- **Return Type**: Returns a `Uni<Long>`, which indicates the number of records deleted.
- **Usage**: Utilized when there is a need to remove a specific transaction from the database.

### Transaction Resource

    @GET
    @Path("/getAllTransactionsByDepotId/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithSession
    fun getAllTransactionsByDepotId(@PathParam("id") id: Long): Uni<RestResponse<List<TransactionDomainModel>>> =
    getAllTransactionsByDepotIdUc(id)
    .onItem().ifNotNull().transform { RestResponse.ok(it) }
    .onItem().ifNull().continueWith(RestResponse.notFound())
      

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateTransactionById")
    @WithTransaction
    fun updateTransactionById(transactionDomainModel: TransactionDomainModel): Uni<RestResponse<Void>> {
        return getTransactionByIdUc.invoke(transactionDomainModel.id)
            .flatMap { transaction ->
                if (transaction != null) {
                    updateTransactionsByIdUc.invoke(transactionDomainModel)
                        .map { updatedTransaction ->
                            RestResponse.created(URI("/transactions/${updatedTransaction.id}"))
                        }
                } else {
                    Uni.createFrom().failure(IllegalStateException("Transaction not found"))
                }
            }
    }

    @DELETE
    @Path("/deleteTransactionBy/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    fun deleteById(@PathParam("id") id: Long): Uni<RestResponse<Void>> {
        return deleteTransactionByIdUc(id).replaceWith{ RestResponse.ok()}
    }

### REST Endpoints: Transaction Management

The following REST endpoints provide functionalities for managing transactions within the system. They handle operations such as retrieving, updating, and deleting transactions based on various parameters.

#### Get All Transactions by Depot ID

- **Endpoint**: `GET /getAllTransactionsByDepotId/{id}`
- **Produces**: `application/json`
- **Functionality**: Fetches all transactions associated with a specific depot ID.
- **Path Parameter**: `id` (Long): The ID of the depot whose transactions are to be retrieved.
- **Return Type**: Returns a `Uni<RestResponse<List<TransactionDomainModel>>>`, representing the list of transactions in JSON format.
- **Error Handling**: If transactions are found, they are returned in the response. If no transactions are found for the given ID, a `notFound` response is returned.

#### Update Transaction by ID

- **Endpoint**: `POST /updateTransactionById`
- **Consumes**: `application/json`
- **Functionality**: Updates an existing transaction using the provided `TransactionDomainModel`.
- **Request Body**: `TransactionDomainModel`: Contains the details of the transaction to be updated.
- **Return Type**: Returns a `Uni<RestResponse<Void>>`, indicating the result of the update operation.
- **Behavior**:

  - Checks if the transaction with the specified ID exists.
  - If the transaction is found, it is updated, and a `created` response with the URI of the updated transaction is returned.
  - If the transaction is not found, an error is returned indicating that the transaction does not exist.

- **Error Handling**: In case of failure, such as if the transaction is not found, an appropriate error response is returned.

#### Delete Transaction by ID

- **Endpoint**: `DELETE /deleteTransactionBy/{id}`
- **Produces**: `application/json`
- **Functionality**: Deletes the transaction identified by the specified ID.
- **Path Parameter**: `id` (Long): The ID of the transaction to be deleted.
- **Return Type**: Returns a `Uni<RestResponse<Void>>`, indicating the success of the deletion operation.
- **Error Handling**: If the deletion operation is successful, a successful response is returned. If there is an issue, an appropriate error response is provided.

## Connection to the Backend

The `TransferViewModel` facilitates the creation of new transaction requests and manages interactions with the backend through the `TransactionService`. It leverages Retrofit for asynchronous network calls and coroutines to handle background operations efficiently.

### Functions

#### `createNewTransactionRequest`

**Purpose:**

Creates a new transaction request and submits it to the backend.

**Parameters:**

- `country: String`: The country where the transaction is being initiated. Defaults to "Deutschland" if empty.
- `recipient: String`: The recipient of the transaction.
- `iban: String`: The International Bank Account Number (IBAN) for the transaction.
- `bic: String`: The Bank Identifier Code (BIC) for the transaction.
- `amount: String`: The monetary value of the transaction, converted to `BigDecimal`.
- `purpose: String`: The purpose or reason for the transaction.
- `dateTimeOfFirstExecution: String`: The timestamp for the first execution of the transaction.
- `dateTimeOfLastExecution: String`: The timestamp for the last execution of the transaction.
- `transactionClassification: String`: The classification of the transaction (e.g., "Überweisung").
- `callback: (Boolean) -> Unit`: A callback function to handle the result of the request. It returns `true` on success and `false` on failure.

**Implementation:**

This function operates within the `viewModelScope` to ensure that the network operation is conducted in the background. It constructs a `TransactionRequestModel` object using the provided parameters and the `SessionManager`'s depot ID. The `createNewTransaction` method from the `TransactionService` is then called to submit the request asynchronously.

The function uses Retrofit's `enqueue()` method to handle the API call:

- **On Success**: If the HTTP response status is `201 Created`, the callback is triggered with `true`.
- **On Failure**: If the response is unsuccessful or if an exception occurs, an error message is logged, and the callback is triggered with `false`.

### Error Handling

The function includes robust error handling:

- **Network Errors**: Handled by Retrofit's `onFailure()` method. Logs the error and returns `false` through the callback.
- **Response Errors**: If the response code is not `201 Created`, an error is logged, and the callback is set to `false`.
- **Exceptions**: Any exceptions thrown during the execution are caught, logged, and the callback is triggered with `false`.

### Coroutine Scope

The `TransferViewModel` uses `viewModelScope` to manage coroutines. This ensures that ongoing network operations are canceled when the ViewModel is cleared, preventing potential memory leaks and ensuring that operations are tied to the ViewModel’s lifecycle.

### Dependencies

- **`TransactionService`**: Responsible for interacting with backend APIs to create new transactions.
- **`Retrofit`**: Manages asynchronous network requests through the `enqueue()` method.
- **`viewModelScope`**: Ensures that network operations are managed efficiently and tied to the ViewModel’s lifecycle.
- **`SessionManager`**: Provides access to the current depot ID, which is necessary for creating the transaction request.

### TransactionRequestScreen Backend Interactions

#### 1. **Creating a Transaction**

- **Functionality:**

  The screen allows users to fill out a form to create a new transaction. Upon form submission, a new transaction request is created and sent to the backend.

- **Backend Call:**

  The `createNewTransactionRequest` function from the `TransferViewModel` is used to handle this operation. It sends the transaction data to the backend and handles the response.

- **Trigger:**

  The creation of the transaction is triggered by the user submitting the form, which gathers the transaction details and sends them to the backend.

#### 2. **Error Handling and User Feedback**

- **Functionality:**

  Handles success and failure scenarios for transaction creation. Provides feedback to the user based on the result of the network request.

- **Backend Call:**

  Success or failure of the transaction creation request is communicated back to the user through the callback function.

- **Trigger:**

  Feedback is provided immediately after the backend responds, based on the result of the transaction creation attempt.
