# customer-information-service

## Backend

### JwtTokenUtils - JWT Token Utility for AurumBanking Application

The `JwtTokenUtils` class in the **AurumBanking** application is a utility class designed to generate JSON Web Tokens (JWT) using a private RSA key. This class is marked as `@ApplicationScoped`, meaning that it is treated as a singleton and managed by the container in a CDI (Contexts and Dependency Injection) environment.

#### Key Methods and Responsibilities

1. **`generateToken()`**:
    - This method is responsible for generating a JWT token.
    - It accepts four parameters:
        - `username`: The subject or the user for whom the token is generated.
        - `role`: The role or group the user belongs to.
        - `duration`: The duration (in seconds) for which the token is valid.
        - `issuer`: The issuer of the token, typically representing the application or service.
    - The method constructs the token claims, including the issuer, subject, issue time, expiration time, and user roles.
    - It uses a private key (loaded from a PEM file) to sign the JWT and returns the signed token.

2. **`readPrivateKey()`**:
    - This method reads the private RSA key from a `.pem` file.
    - It uses Java's `KeyFactory` and `PKCS8EncodedKeySpec` to decode the private key and convert it into a usable `PrivateKey` object.

3. **`decodePrivateKey()`**:
    - This method decodes the private key from its PEM-encoded format into a byte array and then constructs the `PrivateKey` object.

4. **`toEncodedBytes()`**:
    - This method removes the `BEGIN` and `END` headers/footers from the PEM file and decodes the base64-encoded string into bytes.

5. **`removeBeginEnd()`**:
    - This method strips the PEM string of its header and footer, as well as any extraneous newline or whitespace characters.

6. **`currentTimeInSecs()`**:
    - This helper method returns the current time in seconds, which is used to set the issued at (`iat`) and expiration (`exp`) claims in the JWT.


### User Authentification Configuration

The **Application** class manages a static list of authorized users (`AuthUserList`) for the AurumBanking application. It is marked as `@ApplicationScoped`, meaning it's a singleton managed by the CDI container.

#### Key Features

- **User Management**:
    - Provides methods to add and retrieve `AuthUser` objects from the in-memory list.
    - Users are added at application startup via the `loadAuthUsers` method.

#### Methods

- **`addAuthUser(AuthUser: AuthUser)`**:
    - Adds a new `AuthUser` object to the `AuthUserList`.

- **`findAuthUser(AuthUsername: String)`**:
    - Searches for a user by username in the `AuthUserList`.

- **`loadAuthUsers(@Observes evt: StartupEvent?)`**:
    - Populates the `AuthUserList` at application startup with predefined users.

#### Considerations

- **Hardcoded Users**: The initial users are hardcoded, suitable for development but not ideal for production environments.
- **Security**: Passwords are stored in plain text. This should be improved by using hashing and salting methods in a production setting.

### CustomerInformationDomainModel - AurumBanking Domain

The `CustomerInformationDomainModel` is a data class representing customer-related information in the AurumBanking application. It stores personal details, account credentials, and other relevant metadata for customers.

#### Fields

- **`id: Long`**: Unique identifier for the customer.
- **`firstname: String`**: Customer's first name.
- **`lastname: String`**: Customer's last name.
- **`birthDate: Date`**: Customer's birth date.
- **`created: LocalDateTime`**: Timestamp when the customer was created in the system.
- **`lastestLogin: LocalDateTime`**: Timestamp of the customer's most recent login.
- **`streetName: String`**: Customer's street address.
- **`housenumber: String`**: House number for the address.
- **`city: String`**: Customer's city.
- **`country: String`**: Customer's country.
- **`zipcode: String`**: Zip code of the customer's address.
- **`username: String`**: Account username for the customer.
- **`email: String`**: Customer's email address.
- **`phoneNumber: String`**: Customer's phone number.
- **`password: String`**: Account password (to be securely stored).
- **`profileImage: ByteArray?`**: Optional profile image for the customer.

#### Constructors

- **Primary Constructor**: Initializes the model with user-provided values.
- **Secondary Constructor**: Provides default test data for all fields, useful for testing purposes.

#### Considerations

- **Sensitive Data**: Fields such as `password` should be stored securely (e.g., hashed) rather than in plain text.
- **Default Constructor**: The secondary constructor provides test data for development purposes, which should be replaced with real data in production environments.


### CustomerInformationInterfaceRepository - AurumBanking Domain

The `CustomerInformationInterfaceRepository` is an interface that defines the contract for managing customer information within the AurumBanking application. It provides several methods for retrieving, updating, and deleting customer data using reactive programming with **SmallRye Mutiny**.

#### Methods

- **`findCustomerInformationById(id: Long): Uni<CustomerInformationDomainModel?>`**  
  Retrieves a customer's information based on their ID. Returns a `Uni` that emits the `CustomerInformationDomainModel` if found, or `null` if not found.

- **`persistCustomerInformation(customerInformationDomainModel: CustomerInformationDomainModel): Uni<CustomerInformationDomainModel>`**  
  Persists a new customer information record in the database. Returns a `Uni` that emits the saved `CustomerInformationDomainModel`.

- **`updateCustomerEmailById(id: Long, email: String): Uni<CustomerLoginDTO>`**  
  Updates the email address of a customer by their ID. Returns a `Uni` that emits the updated `CustomerLoginDTO`.

- **`updateCustomerPasswordById(id: Long, password: String): Uni<CustomerLoginDTO>`**  
  Updates the password of a customer by their ID. Returns a `Uni` that emits the updated `CustomerLoginDTO`.

- **`deleteCustomerInformationById(id: Long): Uni<Long>`**  
  Deletes the customer information record by their ID. Returns a `Uni` that emits the ID of the deleted customer if successful.

#### Considerations

- **Reactive Programming**: The methods return `Uni<T>`, a type from the SmallRye Mutiny reactive programming library, ensuring non-blocking and asynchronous operations.
- **Error Handling**: All methods use reactive streams, allowing them to handle errors efficiently by emitting error signals instead of throwing exceptions.


### Use Cases - AurumBanking Domain

This package contains several **Use Case** classes that interact with the `CustomerInformationInterfaceRepository` to perform operations on customer data. Each use case class is marked as `@ApplicationScoped`, ensuring that they are managed by the CDI container as singletons.

#### Classes

- **`GetCustomerInformationByIdUc`**  
  Retrieves customer information based on the customer's ID.
    - **Method**: `operator fun invoke(id: Long): Uni<CustomerInformationDomainModel?>`
    - Returns a `Uni` that emits the `CustomerInformationDomainModel` if found, or `null` if not.

- **`AddNewCustomerUc`**  
  Adds a new customer to the system.
    - **Method**: `operator fun invoke(customerInformationDomainModel: CustomerInformationDomainModel): Uni<CustomerInformationDomainModel>`
    - Returns a `Uni` that emits the saved `CustomerInformationDomainModel`.

- **`UpdateCustomerEmailUc`**  
  Updates the email of a customer by their ID.
    - **Method**: `operator fun invoke(id: Long, email: String): Uni<CustomerLoginDTO>`
    - Returns a `Uni` that emits the updated `CustomerLoginDTO`.

- **`UpdateCustomerPasswordUc`**  
  Updates the password of a customer by their ID.
    - **Method**: `operator fun invoke(id: Long, password: String): Uni<CustomerLoginDTO>`
    - Returns a `Uni` that emits the updated `CustomerLoginDTO`.

- **`DeleteCustomerInformationUc`**  
  Deletes a customer's information based on their ID.
    - **Method**: `operator fun invoke(id: Long): Uni<Long>`
    - Returns a `Uni` that emits the ID of the deleted customer if successful.

#### Considerations

- **Reactive Programming**: Each method uses `Uni` from SmallRye Mutiny for reactive programming, enabling non-blocking and asynchronous operations.
- **CDI Integration**: The use cases are `@ApplicationScoped` beans managed by the CDI container, ensuring they are reused throughout the application.
- **Separation of Concerns**: These use cases abstract away the business logic from the repository layer, providing a cleaner architecture.

### AuthUser Class - AurumBanking Model

The `AuthUser` class represents a user in the **AurumBanking** application with authentication details such as username, password, and role. It is a simple data model that is primarily used for handling user authentication and role management within the application.

#### Fields

- **`username: String`**  
  The username of the user, used for login and identification purposes.

- **`password: String`**  
  The password of the user, used for authentication. (Should be securely hashed in production environments.)

- **`role: String`**  
  The role assigned to the user (e.g., "admin", "user"), which determines the user's permissions within the system.

#### Constructors

- **Primary Constructor**:  
  A no-argument constructor that initializes the object. This constructor uses the `lateinit` keyword, meaning the properties are initialized later.

- **Secondary Constructor**:  
  Initializes the `username`, `password`, and `role` fields directly when creating the object. This constructor is useful when instantiating the class with specific values.

#### Methods

- **`toString(): String`**  
  Returns a string representation of the `AuthUser` object, displaying the username, password, and role. Primarily used for debugging purposes.

#### Considerations

- **Late Initialization**:  
  The `lateinit` modifier is used for the fields, which means these fields must be initialized before being accessed. Failing to do so will result in an exception.

- **Password Security**:  
  The `password` field is stored in plain text in this model, which is not secure for production environments. Consider hashing and salting passwords before storing them.


### CustomerInformationEntityModel - AurumBanking Model

The `CustomerInformationEntityModel` class represents a customer entity in the **AurumBanking** application. It is annotated with JPA and Hibernate annotations to map the class to a database table. This model stores comprehensive customer information including personal details, address, and login credentials.

#### Annotations

- **`@Entity`**: Marks this class as a JPA entity to be mapped to a database table.
- **`@Table(name = "CustomerInformation", schema = "public")`**: Specifies the table and schema for the entity in the database.
- **`@Id`**: Denotes the primary key field.
- **`@GeneratedValue`**: Specifies that the `id` is automatically generated by the database.
- **`@Column`**: Used to customize the mapping of entity attributes to database columns, such as specifying column length.

#### Fields

- **`id: Long`**  
  The unique identifier for the customer (primary key, auto-generated).

- **`firstname: String`**  
  The first name of the customer.

- **`lastname: String`**  
  The last name of the customer.

- **`birthDate: Date`**  
  The birth date of the customer.

- **`created: LocalDateTime`**  
  The timestamp when the customer entity was created.

- **`lastestLogin: LocalDateTime`**  
  The timestamp of the customer's most recent login.

- **`streetName: String`**  
  The street name of the customer's address.

- **`housenumber: String`**  
  The house number of the customer's address.

- **`city: String`**  
  The city where the customer resides.

- **`country: String`**  
  The customer's country.

- **`zipcode: String`**  
  The zip code of the customer's address.

- **`username: String`**  
  The username for the customer account.

- **`email: String`**  
  The customer's email address.

- **`phoneNumber: String`**  
  The customer's phone number.

- **`password: String`**  
  The password for the customer's account. (Should be securely hashed.)

- **`profileImage: ByteArray?`**  
  Optional field for storing the customer's profile image.

#### Considerations

- **Late Initialization**: Most fields are marked with `lateinit`, meaning they must be initialized before being accessed.
- **Password Security**: As with other models, passwords should be stored securely using hashing and salting techniques, rather than in plain text.
- **Database Mapping**: This model relies on JPA and Hibernate for database persistence, ensuring that customer data is stored and retrieved correctly.

---

### CustomerLoginDTO - AurumBanking Model

The `CustomerLoginDTO` is a data transfer object (DTO) used for transferring login credentials within the **AurumBanking** application.

#### Fields

- **`email: String`**  
  The customer's email address used for login.

- **`password: String`**  
  The customer's password used for login. (Should be securely handled.)

#### Annotations

- **`@RegisterForReflection`**: Ensures that the `CustomerLoginDTO` is registered for reflection, which is useful in native image builds.

#### Usage

The `CustomerLoginDTO` is typically used in login requests where only the email and password are required. It is not persisted to the database but serves as a transient object for validation and authentication.


### CustomerInformationRepositoryImpl - AurumBanking Repository

The `CustomerInformationRepositoryImpl` class is an implementation of the `CustomerInformationInterfaceRepository` and is responsible for managing customer information entities in the **AurumBanking** application. It extends Quarkus's reactive `PanacheRepository` to provide asynchronous operations using **SmallRye Mutiny**.

#### Key Methods

- **`findCustomerInformationById(id: Long): Uni<CustomerInformationDomainModel?>`**  
  Retrieves a customer’s information by their ID and transforms it from an entity model to a domain model.

- **`findCustomerLoginInformationById(id: Long): Uni<CustomerLoginDTO>`**  
  Fetches a customer's login information (email and password) by their ID.

- **`persistCustomerInformation(customerInformationDomainModel: CustomerInformationDomainModel): Uni<CustomerInformationDomainModel>`**  
  Persists a new customer information record and transforms it back to a domain model after it has been saved.

- **`updateCustomerEmailById(id: Long, email: String): Uni<CustomerLoginDTO>`**  
  Updates a customer's email by their ID and returns the updated login information.

- **`updateCustomerPasswordById(id: Long, password: String): Uni<CustomerLoginDTO>`**  
  Updates a customer's password by their ID and returns the updated login information.

- **`deleteCustomerInformationById(id: Long): Uni<Long>`**  
  Deletes a customer’s information by their ID.

#### Considerations

- **Reactive Programming**: All methods return a `Uni` from **SmallRye Mutiny**, ensuring that operations are non-blocking and asynchronous.
- **Transformation**: The class handles transformation between the **entity model** (`CustomerInformationEntityModel`) and the **domain model** (`CustomerInformationDomainModel`), ensuring a separation of concerns between database interaction and business logic.

---

### CustomerInformationRepository - AurumBanking Repository

The `CustomerInformationRepository` class is a simple repository class that extends `PanacheRepository<CustomerInformationEntityModel>`. This class can be used for basic CRUD operations on `CustomerInformationEntityModel`.

#### Features

- **PanacheRepository**: Provides reactive CRUD operations for the `CustomerInformationEntityModel` using Quarkus's Panache ORM framework.
- **ApplicationScoped**: Managed as a singleton bean within the CDI container, ensuring reuse throughout the application.

#### Use Cases

This class is generally used in scenarios where simple database operations are needed without the additional logic or transformations required in the `CustomerInformationRepositoryImpl`.

### Extension Functions for Model Conversion - AurumBanking

The **AurumBanking** application uses extension functions to facilitate the conversion between domain models and entity models. These functions are critical for handling the transformation of customer information when interacting with the database.

#### Extension Functions

- **`CustomerInformationDomainModel.toEntity()`**  
  Converts a `CustomerInformationDomainModel` instance to a `CustomerInformationEntityModel`.  
  This function maps each field from the domain model to the corresponding field in the entity model, ensuring that the data can be persisted to the database using JPA.

    - **Fields Mapped**:
        - `id`, `firstname`, `lastname`, `birthDate`, `created`, `lastestLogin`, `streetName`, `housenumber`, `city`, `country`, `zipcode`, `username`, `email`, `phoneNumber`, `password`, `profileImage`

- **`CustomerInformationEntityModel.toDomain()`**  
  Converts a `CustomerInformationEntityModel` instance to a `CustomerInformationDomainModel`.  
  This function maps each field from the entity model to the corresponding field in the domain model, ensuring that the data is ready for business logic processing.

    - **Fields Mapped**:
        - `id`, `firstname`, `lastname`, `birthDate`, `created`, `lastestLogin`, `streetName`, `housenumber`, `city`, `country`, `zipcode`, `username`, `email`, `phoneNumber`, `password`, `profileImage`

#### Purpose and Usage

- These conversion functions ensure that the domain model (used for business logic) and the entity model (used for persistence) are kept separate, following a clean architecture approach.
- The functions are used in repository implementations where data needs to be transformed before being persisted to the database or returned to the business layer.

#### Considerations

- **Data Consistency**: These conversions ensure that all necessary fields are consistently mapped between the domain and entity models.
- **Null Handling**: The conversion functions rely on Kotlin's `lateinit` and nullable types. Care should be taken to handle nulls appropriately in the domain and entity models, especially when the database or business logic allows missing data.


### CustomerInformationResource - AurumBanking Resources

The `CustomerInformationResource` class provides a RESTful API for managing customer information in the **AurumBanking** application. This resource handles various customer-related operations such as fetching, inserting, updating, and deleting customer data. It also includes an endpoint for generating JWT tokens for authentication purposes.

#### Annotations

- **`@RequestScoped`**: The class is request-scoped, meaning a new instance is created for each HTTP request.
- **`@Path("/customers")`**: Defines the base path for all endpoints in this resource as `/customers`.
- **`@Produces` and `@Consumes`**: Specifies that the API consumes and produces `application/json` data.

#### Endpoints

- **`@GET @Path("/createJwtToken")`**  
  **Purpose**: Generates a JWT token based on basic authentication credentials.  
  **Response**: Returns the JWT token if authentication is successful, or a `401 Unauthorized` status if it fails.

- **`@GET @Path("/{id:\\d+}")`**  
  **Purpose**: Fetches customer information by their ID.  
  **Response**: Returns the customer information as a `200 OK` response if found, or a `404 Not Found` response if not.

- **`@POST`**  
  **Purpose**: Inserts a new customer into the system.  
  **Roles Allowed**: Only accessible to users with the `"admin"` role.  
  **Response**: Returns a `201 Created` response with the URI of the created customer.

- **`@PUT @Path("/updateCustomerEmailBy/{id}/{email}")`**  
  **Purpose**: Updates the email address of a customer by their ID.  
  **Response**: Returns the updated customer login data as a `200 OK` response, or a `404 Not Found` if the customer does not exist.

- **`@PUT @Path("/updateCustomerPasswordBy/{id}/{password}")`**  
  **Purpose**: Updates the password of a customer by their ID.  
  **Response**: Returns the updated customer login data as a `200 OK` response, or a `404 Not Found` if the customer does not exist.

- **`@DELETE @Path("/deleteCustomerInformationBy/{id:\\d+}")`**  
  **Purpose**: Deletes a customer’s information by their ID.  
  **Roles Allowed**: Only accessible to users with the `"admin"` role.  
  **Response**: Returns a `200 OK` response if the deletion is successful, or a `404 Not Found` if the customer does not exist.

#### Key Considerations

- **JWT Authentication**: The `/createJwtToken` endpoint generates a JWT token using basic authentication and a predefined issuer and duration. It verifies the credentials and issues a token with an `admin` role if successful.
- **Roles-Based Access Control**: The insert and delete operations are restricted to users with the `admin` role, ensuring that only authorized users can perform critical actions.
- **Reactive Programming**: All operations return a `Uni` from **SmallRye Mutiny**, ensuring non-blocking and asynchronous execution of database operations.
- **Session and Transaction Management**: The use of `@WithSession` and `@WithTransaction` annotations ensures that database operations are performed within a session and transaction context.

#### Configuration Properties

- **`de.fhe.ai.jwt.duration`**: The duration of the generated JWT token.
- **`mp.jwt.verify.issuer`**: The issuer of the JWT token, as configured in the application properties.

This resource class provides the necessary API endpoints for managing customer information while ensuring security through JWT and roles-based access control.


## Connection Frontend Backend

### SettingsViewModel - AurumBanking Backend Connection

The `SettingsViewModel` connects the **AurumBanking** app's UI with backend services, facilitating customer data retrieval and password updates.

#### Backend Interactions

- **`getCustomerInformationByCustomerId(customerId: Long, callback: (CustomerInformationResponse?) -> Unit)`**
    - **Purpose**: Fetches customer information by ID.
    - **Backend Service**: `CustomerInformationService`
    - **Description**: Sends an asynchronous HTTP request using Retrofit to retrieve customer data from the backend. The response is handled via Retrofit's `enqueue()` method, with the result passed back through a callback function. If successful, the customer data is returned; otherwise, `null` is returned.

- **`setNewPassword(customerId: Long, newPassword: String, callback: (CustomerInformationCredentialsResponse?) -> Unit)`**
    - **Purpose**: Updates the customer's password.
    - **Backend Service**: `CustomerInformationChangeNewPasswordService`
    - **Description**: Sends a request to the backend to update the password using coroutines to manage the asynchronous task. The result of the operation is returned via a callback function, handling any exceptions that may occur.

#### Key Concepts

- **Retrofit**: Used to manage HTTP requests for fetching and updating customer data from the backend services.
- **Coroutines**: Employed for asynchronous execution, ensuring that UI operations are non-blocking and efficient.
- **Callbacks**: Provide the UI layer with feedback on the success or failure of the backend operations.

This ViewModel ensures efficient and non-blocking communication with the backend, using modern Android development patterns.
