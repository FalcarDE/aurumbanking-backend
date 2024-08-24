# transaction-service

## Transaction Overview

### Overview

The **Transaction Overview** screen in the AurumBanking app allows users to choose between different types of transactions. The interface is designed for ease of use, allowing users to perform standard, international, or permanent transactions.

<div style="text-align: center;">

    <figure>
        <img src="../images/transaction-option-view.png" width="40%">
        <figcaption>Screenshot of the main transaction view</figcaption>
    </figure>

</div>

#### Transaction Options:

On this screen, users can select from the following transaction types:

1. **Standard Transfer**:  
   This option allows users to perform a regular domestic bank transfer.

2. **International Transfer**:  
   Users can choose this option to send money to an account in a different country.

3. **Permanent Order**:  
   This option lets users set up a permanent order, which will regularly send a specified amount of money to a designated account.


<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="../images/transaction-standart-transaction-screen.png" width="100%">
        <figcaption>Screenshot of the standard transaction screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="../images/transaction-international-transaction-screen.png" width="100%">
        <figcaption>Screenshot of the international transaction screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="../images/transaction-permanent-transaction.png" width="100%">
        <figcaption>Screenshot of the permanent transaction screen</figcaption>
    </figure>

</div>

### Standard Transfer

The `StandardTransferScreen` is specifically designed for domestic transfers. It offers a straightforward form where users can enter the necessary details to complete their transaction.

**Key Features**:

- **Recipient**: Users must enter the name of the person or entity they are transferring money to.
- **IBAN**: The International Bank Account Number (IBAN) of the recipient is required for the transaction.
- **BIC**: The Bank Identifier Code (BIC) is another crucial piece of information for ensuring the transfer reaches the correct destination.
- **Amount**: Users specify the amount they wish to transfer.
- **Purpose**: An optional field where users can describe the reason for the transfer.
- **Execution Date**: Users can select the date on which they want the transaction to be executed. This is particularly useful for scheduling future payments.

**Validation and Error Handling:**

- The screen includes robust validation to ensure that all required fields are correctly filled out before the user can proceed.
- If a field is incorrectly filled or left blank, the user receives immediate feedback via highlighted fields and an error message.
- This validation process is crucial for preventing errors that could delay or fail the transaction.

**Form Submission:**

- Once all required fields are validated, the user can submit the form to initiate the transfer.
- If the submission is successful, the user is redirected to a confirmation screen, affirming that their transaction is being processed.
- In case of a failure, a clear error message is displayed, allowing the user to understand what went wrong and take corrective action.

**Edit and Review Mode:**

- Allows users to review and edit input details before final submission.

## International Transfer

The `International Transfer` screen is tailored for cross-border transactions, requiring additional input:

**Key Features:**

- **Country**: Users must select the country to which they are transferring money.
- **Recipient**: Users enter the name of the person or entity they are transferring money to.
- **IBAN**: The International Bank Account Number (IBAN) of the recipient, which is crucial for the transaction.
- **BIC**: The Bank Identifier Code (BIC) necessary to route the transfer to the correct bank.
- **Amount**: Users specify the amount they wish to transfer.
- **Purpose**: An optional field where users can describe the reason for the transfer.
- **Execution Date**: The date on which the transaction is to be executed. This field is required and allows for future scheduling.

**State Management:**

- **Editable State**: The screen includes a toggle for editing mode, allowing users to either review or modify the details before confirming the transaction.
- **Error Handling**: The `validateInput` function checks all fields for correctness, highlighting any issues directly in the UI by changing the border color of the problematic input fields.
- **Snackbar Notifications**: If a user tries to submit the form with errors or if the transaction fails, a Snackbar message appears to inform the user.

**Transaction Logic:**

- **Form Validation**: Before submitting, the form is validated to ensure that all required fields are filled out. This is managed by checking the state of each input field and applying necessary validation rules.
- **ViewModel Interaction**: The `TransferViewModel` handles the logic of creating and submitting a new transaction request. If the request is successful, the user is navigated to a success screen; otherwise, an error message is displayed via Snackbar.
- **Navigation**: Upon successful validation and submission, the screen navigates the user to the `SuccessfulTransaction` route.


## Permanentorder Transfer


## Function Definition

The `Permanent Order` screen allows users to set up recurring transactions. Key features include:

**Key Features:**

- **country**: Stores the selected country for the transfer. Defaults to "Deutschland".
- **recipient**: Stores the name of the recipient of the transfer.
- **iban**: Stores the IBAN (International Bank Account Number) of the recipient.
- **bic**: Stores the BIC (Bank Identifier Code) of the recipient's bank.
- **amount**: Stores the amount of money to be transferred.
- **purpose**: Stores the purpose or reference of the transfer.
- **dateTimeOfFirstExecution**: Stores the date for the first execution of the transfer.
- **dateTimeOfLastExecution**: Stores the date for the last execution of the transfer.
- **transactionClassification**: Stores the classification of the transaction, defaulted to "Dauerauftrag" (standing order).

**State Management and Validation:**

- **isEditable**: Controls whether the input fields are editable.
- **showSnackbar**: Controls the visibility of the Snackbar for displaying messages.
- **snackbarMessage**: Stores the message to be displayed in the Snackbar.
- **errorFields**: Keeps track of the fields that have validation errors.

**User Interface Elements:**

- **TransferInteractionScreenBar**: A custom app bar that provides options to toggle edit mode and confirm the transaction.
- **OutlinedTextField**: Used for input fields like recipient, country, IBAN, BIC, amount, and purpose.
- **DateChoiceButton**: Custom button to select the first and last execution dates.
- **CreateBackOrEditButton**: A button that either allows users to go back or toggle the edit mode.
- **CreateConfirmSendButton**: A button that confirms the transaction and sends the data to the ViewModel for processing.

**Flow:**

1. **Editing**: Users can fill out or modify the input fields.
2. **Validation**: When the user attempts to confirm the transaction, `validateInput` checks all fields.
3. **Confirmation**: If the form is valid, the input is either locked for review or submitted.
4. **Submission**: The `transferViewModel` handles the transaction submission, and the user is navigated to the `SuccessfulTransaction` screen upon success.
5. **Error Handling**: If validation fails or submission encounters an issue, the user is informed via a Snackbar.

