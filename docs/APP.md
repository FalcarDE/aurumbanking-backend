# App Frontend


## Login-Service

<div style="display: flex; justify-content: center; align-items: center;">

    <figure style="margin: 0 20px; text-align: center;">
        <img src="images/app/login-screen/login-screen.png" width="60%">
        <figcaption>Screenshot of login screen</figcaption>
    </figure>

    <figure style="margin: 0 20px; text-align: center;">
        <img src="images/app/login-screen/login-screen-wrong-credentials.png" width="60%">
        <figcaption>Screenshot of failed login</figcaption>
    </figure>

</div>

The **Login Screen** in the **AurumBanking** app provides users with a simple interface to enter their email and password for authentication.

### Screen Elements

- **Penguin Mascot**: A friendly penguin mascot is displayed prominently at the top of the screen, providing a welcoming visual.
- **Email Field**: A text input field where the user can enter their email address.
- **Password Field**: A password input field where the user can enter their account password. The password is masked for security.
- **Login Button**: A button labeled "Login" that submits the email and password for authentication.


### Behavior

- **Successful Login**: If the user enters the correct email and password, they are authenticated and redirected to the app's main screen.

- **Failed Login**: If the user enters incorrect login credentials, a small error message is displayed at the bottom of the screen, informing the user that their credentials are incorrect. The message helps guide the user to try again with the correct information.

### Error Handling

- **Incorrect Credentials**: When the login attempt fails due to incorrect email or password, a small, clear error message is shown to the user below the login fields, helping them understand the issue and try again.


## Depot-Service

## Transaction-Service

### Overview

The **Transaction Overview** screen in the AurumBanking app allows users to choose between different types of transactions. The interface is designed for ease of use, allowing users to perform standard, international, or permanent transactions.

<div style="text-align: center;">

    <figure>
        <img src="images/app/transaction-screen/transaction-option-view.png" width="40%">
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
        <img src="images/app/transaction-screen/transaction-standart-transaction-screen.png" width="100%">
        <figcaption>Screenshot of the standard transaction screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-international-transaction-screen.png" width="100%">
        <figcaption>Screenshot of the international transaction screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-permanent-transaction.png" width="100%">
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

<div style="text-align: center;">

    <figure>
        <img src="images/app/transaction-screen/Standard-Ueberweisung.gif" width="40%">
        <figcaption>Visual demo of the standard transaction view</figcaption>
    </figure>

</div>

### International Transfer

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

<div style="text-align: center;">

    <figure>
        <img src="images/app/transaction-screen/Internationale-Ueberweisung.gif" width="40%">
        <figcaption>Visual demo of the internationale transaction view</figcaption>
    </figure>

</div>


### Permanentorder Transfer

### Function Definition

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

<div style="text-align: center;">

    <figure>
        <img src="images/app/transaction-screen/Dauerauftrag.gif" width="40%">
        <figcaption>Visual demo of the Dauerauftrag view</figcaption>
    </figure>

</div>

### Handling Incomplete or Invalid Form Inputs

In the transfer interaction screen, proper validation of user inputs is crucial to ensure that all required fields are completed and valid before proceeding. When a user attempts to submit the form with incomplete or invalid information, the system performs a series of checks.

If the form fields are not fully filled out or contain invalid entries, the following actions are taken:

1. Validation Check: The system checks all required fields to verify their completeness. This includes fields such as country, recipient, IBAN, BIC, amount, purpose, and execution dates.
2. Error Notification: If any fields are empty or invalid, the user is notified through a snackbar message. This message typically prompts the user to correct the missing or incorrect information.
3. UI Feedback: The interface provides visual feedback to guide the user in correcting the errors. This often involves highlighting the problematic fields or displaying specific error messages.

To better illustrate this process, here is a GIF showing the form's behavior when incomplete or invalid information is provided. In the GIF, you will observe the following:

- The form submission button is disabled or inactive until all fields are correctly filled.
- Upon an attempt to submit with missing or invalid data, a snackbar message appears, alerting the user to complete or correct the required fields.

### GIF Example

This GIF demonstrates how the application handles form validation and provides feedback to ensure a smooth user experience.


<div style="text-align: center;">

    <figure>
        <img src="images/app/transaction-screen/Standard-Ueberweisung-fehler.gif" width="40%">
        <figcaption>Visual demo of handling incomplete or invalid form inputs</figcaption>
    </figure>

</div>

### Screens in White Mode

The app offers the option to switch to White Mode, providing a bright and clear user interface.

<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-standard-transaction-screen-whitemode.png" width="100%">
        <figcaption>Screenshot of the standard transaction screen in Whitemode</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-international-transaction-screen-check-entries-whitemode.png" width="100%">
        <figcaption>Screenshot of the international transaction screen in Whitemode</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-permanent-transaction-screen-with-calendar-whitemode.png" width="100%">
        <figcaption>Screenshot of the permanent transaction screen in Whitemode</figcaption>
    </figure>

</div>

## Support Service
### Support Option View


<div style="text-align: center;">

<figure>
    <img src="images/app/support-screen/support_main_view.png" width="40%">
    <figcaption>Screenshot of the main support view</figcaption>
</figure>

</div>

### Support Option View

The **Support Option View** in the AurumBanking app is designed to provide users with various options to resolve any issues or answer questions quickly and efficiently.

#### Options:

1. **Support Inquiry**:  
   Tap the mail icon to send a direct support request.

2. **Phone Support**:  
   Users can choose to contact customer support by phone. Tapping opens the integrated telephone app on the used mobile device. The Number ist automatically inserted.

3. **FAQ Section**:  
   The information icon leads users to the frequently asked questions (FAQ) section.


### Support Request View
<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view.png" width="100%">
        <figcaption>Screenshot of the support request screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view-success.png" width="100%">
        <figcaption>Screenshot of the error message</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/suppot-request-view-fail.png" width="100%">
        <figcaption>Screenshot of the success message</figcaption>
    </figure>

</div>

#### Support Request Screen

This screen allows users to submit a support request by filling out the necessary information. The user can enter their legal name, the type of inquiry, their email address, and a message detailing their issue.

- **Legal Name**: The user's legal name, which is provided from the session manager.
- **Type of Inquiry**: Users can choose from a dropdown-menu (e.g., Überweisung, Konto, Benutzerdaten).
- **Email Address**: The user's contact email address, which also is provided.
- **Message**: A text field where the user can enter their specific issue or question.

At the bottom, there is a **Send** button that the user can tap to submit the support request. When the button is clicked, the user inputs get checked. For example the message field must be filled and the type must be selected.
At the top is a small menu bar with a check icon with the same functionality as the "senden" button

#### Support Request Submitted (Success) Screen

Once the user successfully submits their support request, a confirmation popup appears to inform them that their request has been received. This dialog reassures the user that their inquiry is being processed.

- **Confirmation Message**: "Anfrage gesendet" — informs the user that the support request has been sent.
- **Details**: The message also states that the request will be processed as quickly as possible.

The user can tap **OK** to dismiss the popup and return to the app.

#### Support Request Error (Failure) Screen

If there are any issues with the support request submission (e.g., missing required fields), the user is shown an error popup. This message prompts the user to complete the missing information.

- **Error Message**: "Fehler" — indicates that an error occurred during the submission process.
- **Details**: The message encourages the user to "Bitte füllen Sie alle Felder korrekt aus." (Please fill out all fields correctly).
- **Sending Error**: If there is an error from sending there is a different error message to inform the user.

The user can tap **OK** to dismiss the error and return to the form to correct the mistakes.

### Support Sub Screens

<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/FAQ-Screen.png" width="100%">
        <figcaption>Screenshot of the FAQ screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-support-screen.png" width="100%">
        <figcaption>Screenshot of the telephone support</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-app-screen.png" width="100%">
        <figcaption>Screenshot of telephone app screen</figcaption>
    </figure>

</div>

#### FAQ Screen


This screen provides users with a Frequently Asked Questions (FAQ) section, helping them resolve common issues and inquiries. Users can expand the questions to read answers about setting up their account, the security of the app, and how to transfer money to another account.

**Key Elements:**

- **FAQ Cards:** The screen displays collapsible cards for each FAQ topic.
- **Questions Addressed:**
    - Setting up a new account in the app.
    - Security measures to protect the account.
    - Transferring money to another account.

---

#### Telephone Support Screen


This screen allows users to access AurumBanking's telephone support. The app provides the contact information, including the phone number and business hours (Monday to Friday, 8:00 - 20:00), enabling users to get direct support from customer service.

**Key Elements:**

- **Phone Number Display:** The contact number for customer service.
- **Support Hours:** Operating hours are clearly displayed for user reference.

---

#### Telephone App Screen


This screen shows the user's telephone dialer with the AurumBanking support number pre-filled. From this screen, the user can directly call the support line, create a new contact, add the number to an existing contact, or send an SMS.

**Key Elements:**

- **Dial Pad:** The dialer pad with the pre-filled AurumBanking support number.
- **Options Menu:** Additional options to create a new contact, add to an existing contact, or send an SMS.

### Screens in White Mode
<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

     <figure style="margin: 10px;">
        <img src="images/app/support-screen/support_main_view-whitemode.png" width="100%">
        <figcaption>Screenshot the support main view in whitemode</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view-whitemode.png" width="100%">
        <figcaption>Screenshot of the support request screen in whitemode</figcaption>
    </figure>   

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-support-screen-whitemode.png" width="100%">
        <figcaption>Screenshot of the telephone support in whitemode</figcaption>
    </figure>

</div>




## Settings Screen

### Settings Option Screen in AurumBanking App

<div style="display: flex; justify-content: center;">

    <figure style="margin-right: 20px;">
        <img src="images/app/settings-screen/settings-option-screen.png" width="60%">
        <figcaption>Screenshot of the settings option screen</figcaption>
    </figure>
    
    <figure>
        <img src="images/app/settings-screen/settings-option-screen-whitemode.png" width="60%">
        <figcaption>Screenshot of the settings option screen in white mode</figcaption>
    </figure>

</div>


The **Settings Option Screen** in the **AurumBanking** app serves as the main hub for users to manage their personal settings. From this screen, the user can access various important functions related to their account and the app's appearance.

#### Overview of the Settings Option Screen

Upon accessing the settings screen, the user is greeted by the app's mascot and a friendly prompt: **"Hier können Sie Einstellungen vornehmen!"**, which translates to "Here you can make settings!" in English. Below this prompt, the user is presented with three main options:

- **Personal Data (Persönliche Daten)**: Allows the user to view and edit their personal information, such as their name, address, and contact details.
- **Change Password (Passwort ändern)**: Provides a secure method for the user to change their account password.
- **Change Design (Design ändern)**: Enables the user to switch between different design modes (e.g., light and dark mode) for the app's appearance.

#### Additional Notes

- This screen acts as a central location for managing account settings and appearance options within the app.
- The user can quickly access this screen by tapping the gear icon located at the top right corner of the main screen.


### Change To Whitemode Screen

<div style="text-align: center;">

    <figure>
        <img src="images/app/settings-screen/settings-whitemode-screen-record.gif" width="40%">
        <figcaption>gif of changing to whitemode</figcaption>
    </figure>

</div>



The **AurumBanking** app provides a setting option to change the visual design of the app. This allows the user to switch between different themes, such as a light or dark mode, depending on their preference.

#### Overview of the White Mode Setting

The white mode (or light mode) setting is accessible from the **Settings** screen within the AurumBanking app. Upon selecting this option, the user interface of the app transitions to a lighter color palette, making it easier to view in well-lit environments.

By selecting the **Möchten Sie auf White-Mode/Dark-Mode wechseln** option, the user can alter the appearance of the app. The app will aks for confirmation and after clicking "verstanden", the app will switch to the wanted mode.

### Change Password Screen in AurumBanking App

<div style="display: flex; justify-content: center;">
    
    <figure style="margin-right: 20px;">
        <img src="images/app/settings-screen/settings-new-password-screen.png" width="60%">
        <figcaption>Screenshot of the new password screen</figcaption>
    </figure>
    
    <figure>
        <img src="images/app/settings-screen/settings-new-password-screen-whitemode.png" width="60%">
        <figcaption>Screenshot of the new password screen in white mode</figcaption>
    </figure>
    
</div>

#### Change Password Screen in AurumBanking App

The **Change Password Screen** in the **AurumBanking** app allows users to securely update their account password. This screen provides an intuitive and user-friendly interface for changing the password in three simple steps.

#### Overview of the Change Password Screen

Upon accessing this screen, the user is prompted with the message **"Hier können Sie ihr Passwort ändern!"**, which translates to "Here you can change your password!" in English.

The user is presented with three fields:
- **Old Password (Altes Passwort)**: The user enters their current password.
- **New Password (Neues Passwort)**: The user enters a new password they would like to set.
- **Repeat New Password (Neues Passwort wiederholen)**: The user repeats the new password for confirmation.

At the bottom, the user can tap the **"Bestätigen"** (Confirm) button to complete the password change process.


#### Additional Notes

- The change password process is designed to ensure security, requiring the user to input their old password before confirming the new one.
- This screen can be accessed from the **Settings** menu by selecting the **Change Password** option.



### Personal Data Screen in AurumBanking App

<div style="display: flex; justify-content: center;">
    
    <figure style="margin-right: 20px;">
        <img src="images/app/settings-screen/settings-personal-data-screen.png" width="60%">
        <figcaption>Screenshot of settings personal data</figcaption>
    </figure>
    
    <figure>
        <img src="images/app/settings-screen/settings-personal-data-screen-whitemode.png" width="60%">
        <figcaption>Screenshot of settings personal data in whitemode</figcaption>
    </figure>
    
</div>



The **Personal Data Screen** in the **AurumBanking** app allows the user to view their account information in a detailed and structured format. This screen provides essential details about the user's account, such as their legal name, address, contact information, and account number.

#### Overview of the Personal Data Screen

On this screen, the user can see their information displayed clearly under the following categories:
- **Profile Picture**: Profile Picture of the user.
- **Legal Name**: The full legal name of the account holder.
- **Address**: The account holder's address, including street, postal code, city, and country.
- **Phone Number**: The user's contact phone number.
- **Email Address**: The registered email associated with the user's account.
- **Account Number**: The unique account number assigned to the user.

#### Additional Notes

- The user can access this screen by navigating through the **Settings** menu and selecting the **Personal Data** option.
- All the data shown here is private and secure, ensuring that only the account holder has access to their personal details.
